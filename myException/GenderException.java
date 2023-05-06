package myException;

public class GenderException  extends Exception{
    String data;
    public GenderException(String inputData){
        this.data = inputData;
    }

    @Override
    public String getMessage(){
        return "Неправильно указали пол, укажите f или m";
    }
}
