import java.util.Scanner;

//deals with interactions with the user (user input and chat output)
public class Ui {

    public void showWelcome() {
        System.out.println("Hello! I'm Nicole");
        System.out.println("What can I do for you?");
    }
//    public boolean blank(String input) {
//        return input.trim().isEmpty();
//    }

    //gets the input as a string
    public String getUserCommand() {
        Scanner scan = new Scanner(System.in);
        String inData = scan.nextLine();
//        while (blank(inData)) {
//            inData = scan.nextLine();
//        }
        return inData;
    }
    //checks if input is bye
    public boolean isExit(String input) {
        return input.equals("bye");
    }

}
