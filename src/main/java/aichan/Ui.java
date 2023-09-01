package aichan;

import java.util.Scanner;
public class Ui {
    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public void showLine() {
        System.out.println("_______________________________________________________________________");
    }
    public void showWelcome() {
        String greet = "Hiya! I'm Ai-chan~\n" +
                "Hey there, dear viewer, what's on your mind?\n" +
                "Is there anything I can do to sprinkle some magic into your day?";
        this.showLine();
        System.out.println(greet);
        this.showLine();
    }
    public void showError(String errMsg) {
        System.out.println(errMsg);
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
