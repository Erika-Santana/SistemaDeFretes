import org.w3c.dom.Element;

import java.util.Scanner;

public class Sistema {

    private ArquivoRepositorio instanciacaoArquivos;

    public static void main(String[] args) {
        //----------------------CRIACAO ARQUIVOS-------------------------//

        String admin = "ADMIN.xml";
        String cliente = "CLIENTE.xml";
        String motorista = "MOTORISTA.xml";
        String veiculos = "VEICULOS.xml";
        String pedidos = "PEDIDOS.xml";
        ArquivoRepositorio  instanciacaoArquivos = new ArquivoRepositorio(admin, cliente, motorista, veiculos, pedidos);

        //-------------------FIM CRIACAO ARQUIVOS-------------------------//

        int opcao = -1;
        Scanner scanner = new Scanner(System.in);
        Sistema sistem = new Sistema();

        //------------------------INICIO MENU----------------------------//

        System.out.println("|=================================================================|");
        System.out.println("|==================== INICIANDO SISTEMA DE FRETES ================|");
        System.out.println("|=================================================================|");

        while (opcao != 0) {
            System.out.println("|                      SELEÇÃO DE LOGIN                            |");
            System.out.println("| 1 | ADMIN");
            System.out.println("| 2 | CLIENTE");
            System.out.println("| 3 | MOTORISTA");
            System.out.println("| 0 | SAIR");
            System.out.println("| DIGITE A OPÇÃO |");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    sistem.loginAdminCadastro();
                    break;
                case 2:
                    // loginCliente();
                    break;
                case 3:
                    // loginMotorista();
                    break;
            }
        }
        //------------------------FIM MENU----------------------------//
    }

    public void loginAdminCadastro() {

        Admin testeUsuario = new Admin();
        Scanner scanner = new Scanner(System.in);

        System.out.println("|=================================================================|");
        System.out.println("|                    INSIRA SUAS INFORMAÇÕES                      |");
        System.out.println("|=================================================================|");

        System.out.println("| CPF: |");
           while (!testeUsuario.setCPF(scanner.nextLine())){
            System.out.println("| DIGITE O CPF CORRETAMENTE: |");
            testeUsuario.setCPF(scanner.nextLine());
        }
        if (contaExiste(testeUsuario, testeUsuario.getCPF())){
            opcoesLoginAdmin(testeUsuario);
        }else{
            System.out.println("| NOME: |");
            testeUsuario.setNome(scanner.nextLine());
            System.out.println("| IDADE: |");
                while(!testeUsuario.setIdade(scanner.nextInt())){
                System.out.println("| DIGITE UMA IDADE VÁLIDA: |");
                testeUsuario.setIdade(scanner.nextInt());
            }

            System.out.println("| TELEFONE: |");
            while(!testeUsuario.setTelefone(scanner.nextLine())){
                System.out.println("| DIGITE UM TELEFONE VÁLIDO: |");
                testeUsuario.setTelefone(scanner.nextLine());
            }

            testeUsuario.setTelefone(scanner.nextLine());
            System.out.println("| LOGIN: |");
            testeUsuario.setLogin(scanner.nextLine());
            System.out.println("| SENHA: |");
            testeUsuario.setSenha(scanner.nextLine());
            System.out.println("| ENDEREÇO: |");
            testeUsuario.setEndereco(scanner.nextLine());

            opcoesLoginAdmin(testeUsuario);
        }

    }

    //Tornar generico depois
    public void opcoesLoginAdmin(Admin usuario){
        int opcao = -1;
        Scanner scan = new Scanner(System.in);
        ArquivoRepositorio arquivos = new ArquivoRepositorio();

        while(opcao != 0){

            System.out.println("|=================================================================|");
            System.out.println("|                       INTERFACE ADMIN                           |");
            System.out.println("|=================================================================|");
            System.out.println("|                      SELEÇÃO UMA OPÇÃO                          |");
            System.out.println("|=================================================================|");
            System.out.println("| 1 | CADASTRAR CLIENTE");
            System.out.println("| 2 | CADASTRAR MOTORISTA");
            //System.out.println("| 3 | CADASTRAR PEDIDO");
            System.out.println("| 4 | CADASTRAR VEÍCULO");
            System.out.println("| 5 | ACESSAR CLIENTES CADASTRADOS");
            System.out.println("| 6 | ACESSAR MOTORISTAS CADASTRADOS");
            System.out.println("| 7 | ACESSAR VEÍCULO DISPONÍVEIS");
            System.out.println("| 8 | VISUALIZAR FATURAMENTO");
            System.out.println("| 0 | SAIR");
            System.out.println("| DIGITE A OPÇÃO |");
            opcao = scan.nextInt();

            switch (opcao){
                case 1:
                    Cliente cliente = new Cliente();
                    inserirInformacoes(cliente);
                    break;
                case 2:
                    Motorista motorista = new Motorista();
                    inserirInformacoes(motorista);
                    break;
                case 3:
                    //Cadastrar Pedido
                    break;
                case 4:
                    //Veiculo
                    break;
                case 5:
                    arquivos.imprimirDados(usuario);
                    break;
            }


        }


    }

    public <T extends Usuario>void inserirInformacoes(T Usuario){

        Scanner scanner = new Scanner(System.in);

        System.out.println("|=================================================================|");
        System.out.println("|                  Insira as suas informações                     |");
        System.out.println("|=================================================================|");

        System.out.println("| CPF: |");
        while (!Usuario.setCPF(scanner.nextLine())){
            System.out.println("| DIGITE O CPF CORRETAMENTE: |");
            Usuario.setIdade(scanner.nextInt());
        }

        System.out.println("| NOME: |");
        Usuario.setNome(scanner.nextLine());
        System.out.println("| IDADE: |");

        while(!Usuario.setIdade(scanner.nextInt())){
            System.out.println("| DIGITE UMA IDADE VÁLIDA: |");
            Usuario.setIdade(scanner.nextInt());
        }

        System.out.println("| TELEFONE: |");

        while(!Usuario.setTelefone(scanner.nextLine())){
            System.out.println("| DIGITE UM TELEFONE VÁLIDO: |");
            Usuario.setTelefone(scanner.nextLine());

        }

        Usuario.setTelefone(scanner.nextLine());
        System.out.println("| LOGIN: |");
        Usuario.setLogin(scanner.nextLine());
        System.out.println("| SENHA: |");
        Usuario.setSenha(scanner.nextLine());
        System.out.println("| ENDEREÇO: |");
        Usuario.setEndereco(scanner.nextLine());

        if (Usuario instanceof Motorista){

            System.out.println("| CNH: |");
            ((Motorista) Usuario).setCnh(scanner.nextLine());

        }

        if (Usuario instanceof Cliente){

            System.out.println("| CNPJ: |");
            ((Cliente) Usuario).setCNPJ(scanner.nextLine());

        }



    }

    public <T extends Usuario> boolean contaExiste(T Usuario, String CPFDigitado){

        ArquivoRepositorio arquivoRepositorio = new ArquivoRepositorio();
        if (arquivoRepositorio.contaExiste(Usuario, CPFDigitado)){
            return true;
        }else{
            return false;
        }
    }

}