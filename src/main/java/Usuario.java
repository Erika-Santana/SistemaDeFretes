import Excecoes.ValorInvalidException;

public class Usuario {

    private String nome;
    private String endereco;
    private int idade;
    private String CPF;
    private String telefone;
    private String login;
    private String senha;


    public Usuario(String name, String address, int age, String SSN){
        setCPF(SSN);
        setNome(name);
        setEndereco(address);
        setIdade(age);
    }
    //----------------------SET-------------------------//

    public void setCPF(String SSN) {

        try {
            if (CPF.matches("[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}")){
                CPF = SSN;
            }else{
                throw new ValorInvalidException("CPF inválido! Por favor digite conforme essas recomendações (XXX.XXX.XXX-XX).");
            }
        }
        catch(ValorInvalidException CPFInvalid)
        {
                CPFInvalid.getMessage();
        }
    }

    public void setIdade(int age){
        try {
            if (age < 0 || age > 120){
                throw new ValorInvalidException("Por valor insira um idade válida!");
            }else{
                  idade = age;
            }
        }
        catch (ValorInvalidException ageInvalid){
            ageInvalid.getMessage();
        }
    }

    public void setTelefone(String fone){
        try{
            if (fone.matches("\\([0-9]{2}\\)[0-9]{5}\\-[0-9]{4}")){
                telefone = fone;
            }else {
                throw new ValorInvalidException("Valor Inválido! Por favor siga a orientação - (XX)XXXXX-XXXX");
            }
        }catch( ValorInvalidException telefoneException){
            telefoneException.getMessage();
        }
    }

    public void setEndereco(String adress) {
        endereco = adress;
    }

    public void setNome(String name){
        nome = name;
    }

    //----------------------GET-------------------------//


    public String getEndereco() {
        return endereco;
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


    public void login(){
        //VERIFICA SE O LOGIN E A SENHA EXISTE NO BANCO DE DADOS
    }

    public void singOut(){
        // ESSE MÉTODO NÃO SEI AINDA O QUE FAZ ...
    }


}
