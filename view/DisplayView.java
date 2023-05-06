package view;

import data.DataCheck;
import myException.InputDataException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DisplayView <V extends View> {
    private DataCheck model;
    private V view;
    public DisplayView(V v){

        view = v;
    }
    public void start(){
        boolean flag = true;
        do {
            String input = view.getInputData(
                    "Введите данные через пробел(Фамилия Имя Отчество Дату рождения Телефон Пол), \n"+
                            "Или введите Exit для выхода: ");
            if (input.equals("Exit")){
                flag = false;
                break;
            }else {
                String[] splitInput = input.replaceAll("\\s+", " ").split(" ");
                int inputDataCount = checkInputDataCount(splitInput.length);
                if (inputDataCount == -1){
                    view.printOutPutData(" Мало данных");
                }else if (inputDataCount == 0){
                    view.printOutPutData(" Слишком много данных");
                }else {
                    try {
                        model = new DataCheck();
                        model.CheckData(splitInput);
                        writePersonData(model);
                    }catch (IOException e){
                        e.printStackTrace();
                    }catch (InputDataException e){
                        view.printOutPutData(e.getMessage());
                    }
                }
            }
        }while (flag);
    }
    private int checkInputDataCount(int inputDataCount){
        if (inputDataCount < DataCheck.dataCount){
            return  -1;
        }else if (inputDataCount > DataCheck.dataCount){
            return 0;
        }else {
            return inputDataCount;
        }
    }
    private void writePersonData(DataCheck data) throws IOException{
        File filepath = new File("A:\\учеба\\Исключения в программировании и их обработка\\Seminar3\\HomeWork\\"
        + data.getLastName());
        try (FileWriter fw = new FileWriter(filepath, true)){
            fw.append(data.toString() + "\n");
        }catch (IOException e){
            throw e;
        }
    }
}
