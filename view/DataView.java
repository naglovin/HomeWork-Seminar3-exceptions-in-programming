package view;

import java.util.Scanner;

public class DataView implements View {
    private Scanner in = new Scanner(System.in, "IBM866"); //IBM866 кодирование русских буковок

    @Override
    public void printOutPutData(String message) {
        System.out.printf("%s", message);
    }

    @Override
    public String getInputData(String message) {
        System.out.printf("%s", message);
        return in.nextLine();
    }
}
