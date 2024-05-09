import Excecoes.ValorInvalidException;

public class Cliente {

    private String CNPJ;

    public Cliente (String CNPJ){
        setCNPJ(CNPJ);
    }

    //----------------------SET-------------------------//


    public void setCNPJ(String CNPJ) {

        try {
            if (CNPJ.matches("[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}/0001\\.[0-9]{2}")){
                this.CNPJ = CNPJ;
            }else{
                throw new ValorInvalidException("CNPJ Inválido! Por favor siga a orientação - XX.XXX.XXX/0001-XX");
            }
        }catch (ValorInvalidException valorException){
            valorException.getMessage();
        }

    }

    //----------------------MÉTODOS-------------------------//

    public void cadastroCliente(){

    }

    public void atualizarCliente(){

    }

    public void deletarCliente(){

    }

    public void criarPedido(){

    }

    public void reservarPedido(){

    }

    public void requererColeta(){

    }

    public void visualizarPedidos(){

    }
    public void statusPedido(){

    }
    public void alterarPedido(){

    }
    public void cancelarPedido(){

    }
}
