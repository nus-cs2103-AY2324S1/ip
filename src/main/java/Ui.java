import java.util.Scanner;

public class Ui {


    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void welcomeMsg() {
        System.out.println("Hello! I'm Red\nWhat can I do for you?");
    }

}
