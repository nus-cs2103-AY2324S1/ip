package duke;

import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        System.out.println("Hello! I'm Nicole");
        System.out.println("What can I do for you?");
    }
//    public boolean blank(String input) {
//        return input.trim().isEmpty();
//    }

    public String getUserCommand() {
        Scanner scan = new Scanner(System.in);
        String inData = scan.nextLine();
//        while (blank(inData)) {
//            inData = scan.nextLine();
//        }
        return inData;
    }

    public boolean isExit(String input) {
        return input.equals("bye");
    }

}
