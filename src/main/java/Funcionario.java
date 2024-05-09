import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Funcionario {

    private String dataAdmissao;
    private String dataDemissao;

    public Funcionario(String dataAdmissao, String dataDemissao){

    }

    public void setDataAdmissao(String dataAdmissao) {
        try{
            if (dataAdmissao.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}")){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); //Primeiro a gente faz um formatter que vai dizer o formato padrão das datas
                LocalDate.parse(dataAdmissao, formatter); //Depois a gente passa pro LocalDate pra validar
            }else{
                throw new DateTimeParseException("A data não está no formato esperado: "+ dataAdmissao, dataAdmissao, 0);
            }
        }catch (DateTimeParseException dateException){
            System.out.println( dateException.getMessage());
        }
    }

    public void setDataDemissao(String dataDemissao) {
        try{
            if (dataDemissao.matches("[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}")){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); //Primeiro a gente faz um formatter que vai dizer o formato padrão das datas
                LocalDate.parse(dataDemissao, formatter); //Depois a gente passa pro LocalDate pra validar
            }else{
                throw new DateTimeParseException("A data não está no formato esperado: "+ dataDemissao, dataDemissao, 0);
            }
        }catch (DateTimeParseException dateException){
            System.out.println( dateException.getMessage());
        }
    }
}
