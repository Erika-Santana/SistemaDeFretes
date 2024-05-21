import java.util.Date;

public class Pedidos {

    private double peso;
    private int quantidade;
    private String tipoCarga;
    private String nome;
    private Endereco enderecoEntrega;
    private Date dataMaxEntrega;
    private double valor;
    private double frete;
    private Endereco enderecoColeta;
    private String status;

    public Pedidos(double peso, int quantidade, String tipoCarga, String nome,
                   Endereco enderecoEntrega, Date dataMaxEntrega, double valor, double frete, Endereco enderecoColeta, String status){
        
    }

    public Pedidos(){};

    //--------------------------------------GETS E SETS--------------------------------//


    public String getNome() {
        return nome;
    }

    public double getFrete() {
        return frete;
    }

    public double getPeso() {
        return peso;
    }

    public double getValor() {
        return valor;
    }

    public Endereco getEnderecoColeta() {
        return enderecoColeta;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Date getDataMaxEntrega() {
        return dataMaxEntrega;
    }

    public String getStatus() {
        return status;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDataMaxEntrega(Date dataMaxEntrega) {
        this.dataMaxEntrega = dataMaxEntrega;
    }

    public void setEnderecoColeta(Endereco enderecoColeta) {
        this.enderecoColeta = enderecoColeta;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

    //-------------------------------------MÉTODOS--------------------------------//






    public double calcularFrete(){
        return 0.0;
    }

    public boolean analisarEntrega( ){

        return false;
    }

    public void cadastrarColeta(){

    }

}
