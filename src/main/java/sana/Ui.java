package sana;
import java.util.Scanner;

public class Ui {

    protected Scanner scanner;

    public Ui(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showWelcome() {
        this.showLine();
        System.out.println("Hello I'm Sana!\nWhat can I do for you today?");
        this.showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("_______________________________________");
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

}
