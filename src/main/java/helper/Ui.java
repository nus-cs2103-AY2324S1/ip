package helper;

import java.util.Scanner;

public class Ui {
    public static final String dDIVIDER = "____________________________________________________________";

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm BanterBot");
        System.out.println("What can I do for you lmao?");
        showLine();
    }

    public void showLine() {
        System.out.println(dDIVIDER);
    }

    public void print(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        return input;
    }
}
