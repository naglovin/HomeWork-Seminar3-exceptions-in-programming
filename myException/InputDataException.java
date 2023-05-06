package myException;

public class InputDataException extends Exception{
    String data;
    public InputDataException(String inputData){
        this.data = inputData;
    }
    @Override
    public String getMessage(){
        return "Ошибка ввода данных: " + this.data;
    }
}
