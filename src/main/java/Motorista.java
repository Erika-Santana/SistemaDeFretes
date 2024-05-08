public class Motorista {

    private String cnh;
    private String[] categorias; //Categoria do carro, ou o peso de carga?

    public Motorista(String carteiraMotorista){
        cnh = carteiraMotorista;
    }


    public void visualizarPedido(){

    }
    public void visualizarStatusPedido(){

    }

    public void alterarStatusPedido(){

    }
    public void marcarRecusaEntrega(){

    }

 /*
 //----------------------- POR ENQUANTO NÃO CONSIGO VALIDAR POIS NÃO ENCONTREI O ALGORITMO DE VERIFICAÇÃO DOS DOIS ULTIMOS DÍGITOS -----------------//

   public void setCnh(String carteiraMotorista){
        if (carteiraMotorista.matches())
    }


    public boolean validaCNH(String codigoValidacao){
        int soma = 0, digitoFinal_1, digitoFinal_2, incrementaDig2 = 0;
        int multi= 9;
        String cnhForn, digForn, digEnc;

        if (codigoValidacao.length() > 11 ){
            return false;
        }

        cnhForn = codigoValidacao.substring(0,8);
        digForn = codigoValidacao.substring(9,11);

        for (int i = 0; i < cnhForn.length(); i++){
            soma += (Integer.parseInt(String.valueOf(cnh.charAt(i)))* multi);
            multi--;

        }

    }*/
}
