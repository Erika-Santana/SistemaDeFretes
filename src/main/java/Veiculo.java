public class Veiculo {

    private String placa;
    private String modelo;
    private int ano;
    private String dataCompra;
    private String dataVenda;

    public Veiculo(String placa, String modelo, String dataCompra, String dataVenda, int ano){

    }

    //----------------------SET-------------------------//

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
