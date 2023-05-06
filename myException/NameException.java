package myException;

public class NameException extends Exception{
    String data;
    public NameException(String inputData){
        this.data = inputData;
    }
    @Override
    public String getMessage(){
        return "неправильный формат ФИО";
    }
}
