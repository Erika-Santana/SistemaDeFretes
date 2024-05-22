import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.Normalizer;
import java.util.Scanner;


public class ArquivoRepositorio {

    public static final String ADMIN_ARQUIVO = "ADMIN.xml";
    public static final String CLIENTE_ARQUIVO = "CLIENTE.xml";
    public static final String MOTORISTA_ARQUIVO = "MOTORISTA.xml";
    public static final String VEICULOS_ARQUIVO = "VEICULOS.xml";
    public static final String PEDIDOS_ARQUIVO = "PEDIDOS.xml";

    private File arquivoPedidos;
    private File arquivoVeiculos;
    private File arquivoADM;
    private File arquivoMotorista;
    public File arquivoCliente;

    public ArquivoRepositorio() {
        arquivoADM = criarArquivo(ADMIN_ARQUIVO);
        arquivoPedidos = criarArquivo(PEDIDOS_ARQUIVO);
        arquivoVeiculos = criarArquivo(VEICULOS_ARQUIVO);
        arquivoCliente = criarArquivo(CLIENTE_ARQUIVO);
        arquivoMotorista = criarArquivo(MOTORISTA_ARQUIVO);

    }


    //---------------------------MÉTODOS DE ABRIR E FECHAR OS ARQUIVOS------------------------------/
    public static File criarArquivo(String nomeArquivo) {

        try {
            File novoArquivo = new File(nomeArquivo);

            if (novoArquivo.exists()) {
                System.out.println("O arquivo já existe!");
                return novoArquivo;
            } else {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.newDocument();

                Element rootElement = doc.createElement("Arquivo");
                doc.appendChild(rootElement);

                TransformerFactory transFactory = TransformerFactory.newInstance();
                Transformer transformer = transFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(novoArquivo);
                transformer.transform(source, result);

                System.out.println("ARQUIVO CRIADO COM SUCESSO!");
                return novoArquivo;
            }
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Document abrirDocumento(File nomeArquivo) {
        try {
            if (nomeArquivo == null || !nomeArquivo.exists()) {
                System.out.println("Arquivo não existe!");
                return null;
            }
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            return docBuilder.parse(nomeArquivo);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static void fecharDocumento(Document doc, File nomeArquivo) {
        try {

            if (nomeArquivo == null || !nomeArquivo.exists()) {
                System.out.println("Arquivo não existe!");
            }
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(nomeArquivo);
            transformer.transform(source, result);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    //---------------------------MÉTODOS DE REGISTRO DE USUARIOS----------------------------------------/
    public <T extends Usuario> void cadastrarUsuario(T usuario) {
        Document doc;
        File arquivo = null;

        doc = abrirDocPorTipoUsuario(usuario);

        if (doc == null) {
            throw new RuntimeException("Erro ao abrir o documento XML. Está recebenmdo valor nulo");
        }

        Element rootElement = (Element) doc.getElementsByTagName("Arquivo").item(0);

        Element elementPessoa = doc.createElement("Pessoas");
        elementPessoa.setTextContent(usuario.getCPF());
        rootElement.appendChild(elementPessoa);

        Element elementCPF = doc.createElement("CPF");
        elementCPF.setTextContent(usuario.getCPF());
        elementPessoa.appendChild(elementCPF);

        Element elementNome = doc.createElement("nome");
        elementNome.setTextContent(usuario.getNome());
        elementPessoa.appendChild(elementNome);

        Element elementEndereco = doc.createElement("endereco");
        elementEndereco.setTextContent(usuario.getEndereco());
        elementPessoa.appendChild(elementEndereco);

        Element elementTelefone = doc.createElement("telefone");
        elementTelefone.setTextContent(usuario.getTelefone());
        elementPessoa.appendChild(elementTelefone);

        Element elementIdade = doc.createElement("idade");
        elementIdade.setTextContent(String.valueOf(usuario.getIdade()));
        elementPessoa.appendChild(elementIdade);

        Element elementLogin = doc.createElement("login");
        elementLogin.setTextContent(usuario.getLogin());
        elementPessoa.appendChild(elementLogin);

        Element elementSenha = doc.createElement("senha");
        elementSenha.setTextContent(usuario.getSenha());
        elementPessoa.appendChild(elementSenha);

        if (usuario instanceof Cliente) {
            Element elementCNPJ = doc.createElement("CNPJ");
            elementCNPJ.setTextContent(((Cliente) usuario).getCNPJ());
            elementPessoa.appendChild(elementCNPJ);
        }

        if (usuario instanceof Motorista) {
            Element elementCNH = doc.createElement("CNH");
            elementCNH.setTextContent(((Motorista) usuario).getCnh());
            elementPessoa.appendChild(elementCNH);
        }
        arquivo = retornaNomeArquivo(usuario);
        fecharDocumento(doc, arquivo);
    }

    public <T extends Usuario> void imprimirDados(T usuario) {

        File nomeArquivo = null;
        Document doc;
        doc = abrirDocPorTipoUsuario(usuario);


        if (doc == null) {
            throw new RuntimeException("Erro ao abrir o documento XML. Está recebendo valor nulo");
        }
        NodeList cpfs = doc.getElementsByTagName("CPF");

        for (int i = 0; i < cpfs.getLength(); i++) {
            Element cpfElement = (Element) cpfs.item(i);
            if (cpfElement.getTextContent().equals(usuario.getCPF())) {
                System.out.println("CPF: " + cpfElement.getTextContent());
                System.out.println("NOME: " + cpfElement.getElementsByTagName("nome").item(0).getTextContent());
                System.out.println("ENDERECO: " + cpfElement.getElementsByTagName("endereco").item(0).getTextContent());
                System.out.println("TELEFONE: " + cpfElement.getElementsByTagName("telefone").item(0).getTextContent());
                System.out.println("IDADE: " + cpfElement.getElementsByTagName("idade").item(0).getTextContent());
                System.out.println("LOGIN: " + cpfElement.getElementsByTagName("login").item(0).getTextContent());
                System.out.println("SENHA: " + cpfElement.getElementsByTagName("senha").item(0).getTextContent());

                if (usuario instanceof Cliente) {
                    System.out.println("CNPJ: " + cpfElement.getElementsByTagName("CNPJ").item(0).getTextContent());
                }

                if (usuario instanceof Motorista) {
                    System.out.println("CNH: " + cpfElement.getElementsByTagName("CNH").item(0).getTextContent());
                }
            }
        }

        nomeArquivo = retornaNomeArquivo(usuario);
        fecharDocumento(doc, nomeArquivo);
    }

    public <T> void atualizarArquivo(T classeRecebida) {
        Document docRetornado;

        if (classeRecebida instanceof Cliente) {
            docRetornado = abrirDocumento(arquivoCliente);
            System.out.println("CLASSE CLIENTE RECEBIDA!");
            atualizarDadosUsuario(docRetornado, (Cliente) classeRecebida);
            fecharDocumento(docRetornado, arquivoCliente);

        } else if (classeRecebida instanceof Motorista) {
            docRetornado = abrirDocumento(arquivoMotorista);

            System.out.println("CLASSE MOTORISTA RECEBIDA!");
            atualizarDadosUsuario(docRetornado, (Motorista) classeRecebida);
            fecharDocumento(docRetornado, arquivoMotorista);

        } else if (classeRecebida instanceof Admin) {

            docRetornado = abrirDocumento(arquivoADM);
            System.out.println("CLASSE ADMIN RECEBIDA!");
            atualizarDadosUsuario(docRetornado, (Admin) classeRecebida);
            fecharDocumento(docRetornado, arquivoADM);

        }
    }

    private void atualizarDadosUsuario(Document doc, Usuario usuario) {

        NodeList elementos = doc.getElementsByTagName("CPF");

        for (int i = 0; i < elementos.getLength(); i++) {
            Element elementoUsuario = (Element) elementos.item(i);

            if (elementoUsuario.getElementsByTagName("CPF").item(0).getTextContent().equals(usuario.getCPF())) {

                elementoUsuario.getElementsByTagName("NOME").item(0).setTextContent(usuario.getNome());
                elementoUsuario.getElementsByTagName("ENDERECO").item(0).setTextContent(usuario.getEndereco());
                elementoUsuario.getElementsByTagName("TELEFONE").item(0).setTextContent(usuario.getTelefone());
                elementoUsuario.getElementsByTagName("IDADE").item(0).setTextContent(String.valueOf(usuario.getIdade()));
                elementoUsuario.getElementsByTagName("LOGIN").item(0).setTextContent(usuario.getLogin());
                elementoUsuario.getElementsByTagName("SENHA").item(0).setTextContent(usuario.getSenha());

                if (usuario instanceof Cliente) {
                    elementoUsuario.getElementsByTagName("CNPJ").item(0).setTextContent(((Cliente) usuario).getCNPJ());
                }

                if (usuario instanceof Motorista) {
                    elementoUsuario.getElementsByTagName("CNH").item(0).setTextContent(((Motorista) usuario).getCnh());
                }

                break;
            }
        }
    }

    public <T extends Usuario> Document abrirDocPorTipoUsuario(T usuario) {

        Document docRecebido = null;

        if (usuario instanceof Cliente) {
            docRecebido = abrirDocumento(arquivoCliente);
        }
        if (usuario instanceof Admin) {
            docRecebido = abrirDocumento(arquivoADM);
        }
        if (usuario instanceof Motorista) {
            docRecebido = abrirDocumento(arquivoMotorista);
        }

        return docRecebido;
    }

    public <T extends Usuario> File retornaNomeArquivo(T usuario) {

        File nomeArquivo = null;

        if (usuario instanceof Cliente) {
            nomeArquivo = arquivoCliente;
        }
        if (usuario instanceof Admin) {
            nomeArquivo = arquivoADM;
        }
        if (usuario instanceof Motorista) {
            nomeArquivo = arquivoMotorista;
        }

        return nomeArquivo;
    }

   /* public boolean contaExiste(Usuario userCPF, String CPF){

        String senha;
        Scanner scan = new Scanner(System.in);

        if (userCPF  instanceof Admin){
            Document docRetornado = abrirDocumento(arquivoADM);
            if (docRetornado != null){
                NodeList cpfList = docRetornado.getElementsByTagName("CPF");
                for (int i = 0; i < cpfList.getLength(); i++ ){
                    Element elementosDaLista = (Element) cpfList.item(i);
                    if (CPF.equals(elementosDaLista.getTextContent())){


                       }

                    }
                }
            }
        }
        if (userCPF  instanceof Motorista){
            Document docRetornado = abrirDocumento(arquivoMotorista);
            if (docRetornado != null){
                NodeList cpfList = docRetornado.getElementsByTagName("CPF");
                for (int i = 0; i < cpfList.getLength(); i++ ){
                    Element elementosDaLista = (Element) cpfList.item(i);
                    if (userCPF.getCPF().equals(elementosDaLista.getTextContent())) {
                        while (true) {
                            System.out.println("| DIGITE A SENHA: |");
                            senha = scan.nextLine();

                            String senhaCPF = String.valueOf(elementosDaLista.getElementsByTagName("SENHA").item(0).getTextContent());

                            if (senha.equals(senhaCPF)) {
                                System.out.println("| LOADING... |");
                                return true;
                            } else {
                                System.out.println("| SENHA INCORRETA. TENTE NOVAMENTE. |");
                            }

                        }
                    }
                }
            }
        }

        if (userCPF  instanceof Cliente){
            Document docRetornado = abrirDocumento(arquivoCliente);
            if (docRetornado != null){
                NodeList cpfList = docRetornado.getElementsByTagName("CPF");
                for (int i = 0; i < cpfList.getLength(); i++ ){

                    Element elementosDaLista = (Element) cpfList.item(i);
                    if (userCPF.getCPF().equals(elementosDaLista.getTextContent())){
                        while (true){
                            System.out.println("| DIGITE A SENHA: |");
                            senha = scan.nextLine();

                            String senhaCPF = String.valueOf(elementosDaLista.getElementsByTagName("SENHA").item(0).getTextContent());

                            if (senha.equals(senhaCPF)){
                                System.out.println("| LOADING... |");
                                return true;
                            }else {
                                System.out.println("| SENHA INCORRETA. TENTE NOVAMENTE. |");
                            }

                        }
                    }
                }
            }

        }

        return false;

    }*/

    public <T extends Usuario> boolean contaExiste(T usuario, String CPF) {

        Document document;
        document = abrirDocPorTipoUsuario(usuario);

        if (document != null) {
            NodeList CPFList = document.getElementsByTagName("CPF");
            NodeList senhaList = document.getElementsByTagName("senha");
            NodeList loginList = document.getElementsByTagName("login");
               for (int i = 0; i < CPFList.getLength(); i++) {

                Element elementDaLista = (Element) CPFList.item(i);
                Element elementDaListaSenha = (Element) senhaList.item(i);
                Element elementDaListaLogin = (Element) loginList.item(i);

                if (otimizaCPF(String.valueOf(elementDaLista.getTextContent()), CPF)) {
                    realizarLogin(elementDaListaSenha.getTextContent(), elementDaListaLogin.getTextContent());
                    return true;
                }
            }
        }

        return false;
    }

    public boolean otimizaCPF(String cpfSalvo, String cpfDigitado) {
        cpfSalvo = cpfSalvo.replaceAll("\\D", "");
        cpfDigitado = cpfDigitado.replaceAll("\\D", "");

        //Por que precisa usar o normalizer?
        cpfSalvo = Normalizer.normalize(cpfSalvo, Normalizer.Form.NFC);
        cpfDigitado = Normalizer.normalize(cpfDigitado, Normalizer.Form.NFC);

        System.out.println("DEPURAÇÃO CPF SALVO: " + cpfSalvo);
        System.out.println("DEPURAÇÃO CPF Digitado: " + cpfDigitado);

        return cpfSalvo.equals(cpfDigitado);
    }

    public <T extends Usuario> boolean realizarLogin(String senhaList, String loginList) {
        String senha;
        String login;
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("| DIGITE O LOGIN: |");
            login = scan.nextLine();
            System.out.println("| DIGITE A SENHA: |");
            senha = scan.nextLine();

            if (senha.equals(senhaList) && login.equals(loginList)) {
                System.out.println("| LOADING... |");
                return true;
            } else {
                System.out.println("| SENHA E LOGIN INCORRETOS. TENTE NOVAMENTE. |");
            }
        }

    }

    //---------------------------MÉTODOS DE REGISTRO DE PEDIDOS E VEICULOS------------------------------//

    public void registrarPedido(String CPF, Pedidos pedidoUsuario) {

        Document docRecebido = abrirDocumento(arquivoPedidos);
        Pedidos pedido = new Pedidos();

        Element rootElement = (Element) docRecebido.getElementsByTagName("Pedidos");
        //double peso, int quantidade, String tipoCarga, String nome,
        //                   Endereco enderecoEntrega, Data dataMaxEntrega, double valor, double frete, Endereco enderecoColeta, String status

        Element cpfElement = docRecebido.createElement("CPF");
        cpfElement.setTextContent(CPF);
        rootElement.appendChild(cpfElement);

        Element nome = docRecebido.createElement("NOME");
        nome.setTextContent(pedidoUsuario.getNome());
        cpfElement.appendChild(nome);

        Element quantidade = docRecebido.createElement("QUANTIDADE");
        quantidade.setTextContent(String.valueOf(pedidoUsuario.getQuantidade()));
        cpfElement.appendChild(quantidade);

        Element peso = docRecebido.createElement("PESO");
        peso.setTextContent(String.valueOf(pedidoUsuario.getPeso()));
        cpfElement.appendChild(peso);

        Element tipoCarga = docRecebido.createElement("TIPO");
        tipoCarga.setTextContent(String.valueOf(pedidoUsuario.getTipoCarga()));
        cpfElement.appendChild(tipoCarga);

        Element valor = docRecebido.createElement("VALOR");
        valor.setTextContent(String.valueOf(pedidoUsuario.getValor()));
        cpfElement.appendChild(valor);

        Element frete = docRecebido.createElement("FRETE");
        frete.setTextContent(String.valueOf(pedidoUsuario.getFrete()));
        cpfElement.appendChild(frete);

        Element status = docRecebido.createElement("STATUS");
        status.setTextContent(String.valueOf(pedidoUsuario.getStatus()));
        cpfElement.appendChild(status);

        Element dataMAX = docRecebido.createElement("DATAENTREGA");
        dataMAX.setTextContent(String.valueOf(pedidoUsuario.getDataMaxEntrega()));
        cpfElement.appendChild(dataMAX);

    }
}


