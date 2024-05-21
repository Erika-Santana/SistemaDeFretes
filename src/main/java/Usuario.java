import Excecoes.ValorInvalidException;

public class Usuario {

    private String nome;
    private String endereco;
    private int idade;
    private String CPF;
    private String telefone;
    private String login;
    private String senha;


    public Usuario(String name, String address, int age, String SSN, String telefone, String login, String senha){
        setCPF(SSN);
        setNome(name);
        setEndereco(address);
        setIdade(age);
        setTelefone(telefone);
      //  login(login, senha);
    }

    public Usuario(Usuario objUsuario){
        setCPF(objUsuario.getCPF());
        setNome(objUsuario.getNome());
        setEndereco(objUsuario.getEndereco());
        setIdade(objUsuario.getIdade());
        setTelefone(objUsuario.getTelefone());
        //login(login, senha);
    }
    public Usuario(){

    }
    //----------------------SET-------------------------//

    public boolean setCPF(String SSN) {

        try {
            if (SSN.matches("[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}\\s*")){
                CPF = SSN;
                return true;
            }else{
                throw new ValorInvalidException("CPF inválido! Por favor digite conforme essas recomendações (XXX.XXX.XXX-XX).");
            }
        }
        catch(ValorInvalidException CPFInvalid)
        {
            System.out.println(CPFInvalid.getMessage());
                return false;
        }
    }

    public boolean setIdade(int age){
        try {
            if (age < 0 || age > 120){
                throw new ValorInvalidException("Por valor insira um idade válida!");

            }else{
                  idade = age;
                  return true;
            }
        }
        catch (ValorInvalidException ageInvalid){
            ageInvalid.getMessage();
            return false;
        }

    }

    public boolean setTelefone(String fone){
        try{
            if (fone.matches("\\([0-9]{2}\\)[0-9]{5}\\-[0-9]{4}")){
                telefone = fone;
                return true;
            }else {
                throw new ValorInvalidException("Valor Inválido! Por favor siga a orientação - (XX)XXXXX-XXXX");
            }
        }catch( ValorInvalidException telefoneException){
            telefoneException.getMessage();
            return false;
        }
    }

    public void setEndereco(String adress) {
        endereco = adress;
    }

    public void setNome(String name){
        nome = name;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public void setSenha(String password){
        senha = password;
    }

    //----------------------GET-------------------------//


    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getIdade() {
        return idade;
    }

    public String getNome() {
        return nome;
    }

    public String getCPF() {
        return CPF;
    }


    //----------------------MÉTODOS-------------------------//

    //Talvez seja melhor validar no ARQUIVO XML mesmo

    private void login(String login, String senha){
        //VERIFICA SE O LOGIN E A SENHA EXISTE NO Arquivo XML
    }

    public void singOut(){
        // ESSE MÉTODO NÃO SEI AINDA O QUE FAZ ...
    }


}
