public class Pedidos {

    private double peso;
    private int quantidade;
    private String tipoCarga;
    private String nome;
    private Endereco enderecoEntrega;
    private String dataMaxEntrega;
    private double valor;
    private double frete;
    private Endereco enderecoColeta;
    private String status;

    public Pedidos(double peso, int quantidade, String tipoCarga, String nome,
                   Endereco enderecoEntrega, String dataMaxEntrega, double valor, double frete, Endereco enderecoColeta, String status){
        
    }
    public double calcularFrete(){
        return 0.0;
    }

    public boolean analisarEntrega( ){

        return false;
    }

    public void cadastrarColeta(){

    }

}
