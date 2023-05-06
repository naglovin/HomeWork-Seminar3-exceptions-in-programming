package myException;

public class BirthDayException extends Exception{
    String data;
    public BirthDayException(String inputData){
        this.data = inputData;
    }
    @Override
    public String getMessage(){

        return "Ошибка, не правильно введена дата";
    }
}
