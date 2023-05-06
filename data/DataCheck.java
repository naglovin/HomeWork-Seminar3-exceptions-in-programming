package data;

import myException.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataCheck {
    public static int dataCount = 6;
    private String firstName;
    private String lastName;
    private String fatherName;
    private LocalDate birthDate;
    private int phone;
    private Gender gender;
    public DataCheck(){
    }
    public void CheckData(String[] splitString) throws InputDataException {
        if (splitString ==null){
            throw new NullPointerException("вы ничего не ввели");
        }
        StringBuilder errorsMessage = new StringBuilder();  //StringBuilder представляет собой альтернативу классу String,
        // поскольку он создает изменяемую последовательность символов.
        for (String string : splitString){
            if (Character.isLetter(string.charAt(0))){   //для определения в Java, является ли переданный символ буквой.
                if (string.length() == 1){
                    if (this.gender == null){
                        try {
                            this.gender = checkGender(string);
                        }catch (GenderException e){
                            errorsMessage.append(e.getMessage());
                        }
                    }else {
                        errorsMessage.append("Гендер нужно указать только 1 раз");
                    }
                }else {
                    if (this.lastName == null){
                        try {
                            this.lastName = checkName(string);
                        }catch (NameException e){
                            errorsMessage.append(e.getMessage());
                        }
                    }else if (this.firstName == null){
                        try {
                            this.firstName = checkName(string);
                        }catch (NameException e){
                            errorsMessage.append(e.getMessage());
                        }
                    }else if (this.fatherName == null){
                        try {
                            this.fatherName = checkName(string);
                        }catch (NameException e){
                            errorsMessage.append(e.getMessage());
                        }
                    }else {
                        errorsMessage.append("много лишних данных");
                    }
                }
            }else {
                if (string.matches("[0-9]{2}\\.[0-9]\\.[0-9]{4}")){  //поиска соответствия по (шаблону)
                    if(this.birthDate == null){
                        try {
                            this.birthDate = checkBirthDate(string);
                        }catch (BirthDayException e){
                            errorsMessage.append(e.getMessage());
                        }
                    }else {
                        errorsMessage.append("Введите только одну дату");
                    }
                }else {
                    if (this.phone == 0){
                        try {
                            this.phone = (int) checkPhone(string);
                        }catch (PhoneException e){
                            errorsMessage.append(e.getMessage());
                        }
                    }else {
                        errorsMessage.append("номер должен быть один");
                    }
                }
            }
        }
        if (!errorsMessage.isEmpty()){      // IsEmpty - проверяет, является ли строка пустой ("") или значение null.
            throw new InputDataException(errorsMessage.toString()); //Метод toString () — используется в Java для получения строкового объекта,
            // представляющего значение числового объекта, другими словами — преобразует число в строку
        }
    }
    public String getLastName(){
        return lastName;
    }
    private String checkName(String inputString) throws NameException {
        if (inputString.toLowerCase().matches("^[a-za-яё]*$")) { //ToLowerCase-для преобразования символов в строке в нижний регистр.
            return inputString;
        } else {
            throw new NameException(inputString);
        }
    }
    private long checkPhone(String inputString) throws PhoneException{
        if (inputString.length() == 10){
            try {
                return Long.parseLong(inputString);   //Long.parseLong преобразования строки в длинное значение.
            }catch (NumberFormatException e){
                throw new PhoneException(inputString);
            }
        }else {
            throw new PhoneException(inputString);
        }
    }
    private Gender checkGender(String inputString) throws GenderException{
        try {
            return Gender.valueOf(inputString);
        }catch (RuntimeException e){
            throw new GenderException(inputString);
        }
    }
    private LocalDate checkBirthDate(String inputString) throws BirthDayException{
        try {
            return LocalDate.parse(inputString, DateTimeFormatter.ofPattern("dd.MM.yyyy"));//метод parse() класса LocalTime,
            // используемый для получения экземпляра LocalTime из строки,
            // такой как ‘2018-10-23’, переданной в качестве параметра
        }catch (DateTimeException e){
            throw new BirthDayException(inputString);
        }
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(lastName).append(">")
                .append("<").append(firstName).append(">")
                .append("<").append(fatherName).append(">")
                .append("<").append(birthDate.toString()).append(">")
                .append("<").append(phone).append(">")
                .append("<").append(gender).append(">");
        return sb.toString();
    }
}
