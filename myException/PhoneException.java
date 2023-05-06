package myException;

public class PhoneException extends Exception{
    String data;
    public PhoneException(String inputData){
        this.data = inputData;
    }
    @Override
    public String getMessage(){
        return "Не удалось преобразовать " + data + " в телефонный номер. \n"+
                "Формат номера должен быть из 10 цифр, без пробела";
    }
}
