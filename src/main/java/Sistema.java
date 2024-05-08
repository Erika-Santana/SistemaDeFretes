import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import java.io.File;
import java.util.Scanner;

import static java.lang.System.exit;

public class Sistema {
    public static void main(String[] args) {

        int opcao = -1;
        Scanner scanner = new Scanner(System.in);

        //----------------------CRIACAO ARQUIVOS-------------------------//
        String admin = ("ADMIN.xml");
        String cliente = ("CLIENTE.xml");
        String motorista = ("MOTORISTA.xml");

        criarArquivo(admin);
        criarArquivo(cliente);
        criarArquivo(motorista);

        //-------------------FIM CRIACAO ARQUIVOS-------------------------//

        //------------------------INICIO MENU----------------------------//

        System.out.println("|=================================================================|");
        System.out.println("|==================== INICIANDO SISTEMA DE FRETES ================|");
        System.out.println("|=================================================================|");

        while (opcao != 0){

            System.out.println("|                      SELEÇÃO DE LOGIN                            |");
            System.out.println("| 1 | ADMIN");
            System.out.println("| 2 | CLIENTE");
            System.out.println("| 3 | MOTORISTA");
            System.out.println("| 0 | SAIR");
            System.out.println("| DIGITE A OPÇÃO |");
            opcao = scanner.nextInt();

            switch (opcao){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                   // loginAdmin();
                    break;
                case 2:
                  //  loginCliente();
                    break;
                case 3:
                    //loginMotorista();
                    break;

            }

        }

        //------------------------FIM MENU----------------------------//

    }

    public static void criarArquivo(String nomeArquivo){

        try {
            File novoArquivo = new File(nomeArquivo);

            if (novoArquivo.exists()){

                System.out.println("O arquivo já existe!");

            }else{

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();// Uma fábrica que cria instancias da classe DocumentBuiler
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder(); // Document builder são objetos que analizam XML. Você utiliza a fábrica para criar esse objeto
                Document doc = docBuilder.newDocument(); // Esse doc é uma representação do doc xml. ELE é utilizado assim pois com ele é possível manipular de várias formas


                Element rootElement = doc.createElement("RAIZ" );
                doc.appendChild(rootElement);

                TransformerFactory transFactory = TransformerFactory.newInstance(); //O transform depois transforma o doc novamente em XML
                Transformer transformer = transFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "Identação: YES");

                System.out.println("Arquivo criado com sucesso!");
                System.out.println(novoArquivo.getParentFile());
                System.out.println(novoArquivo.exists());
                System.out.println(novoArquivo.getPath());

            }

        }catch(ParserConfigurationException parseConfig){

            System.out.println(parseConfig.getMessage() + "ERROR DOCUMENT BUILDER");

        }catch(TransformerConfigurationException transformError){

            System.out.println(transformError.getMessage() + "ERROR CRIACAO TRANSFORM");

        }

    }

}
