import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        this.showLine();
        System.out.println("Hello I'm Sana!\nWhat can I do for you today?");
        this.showLine();
    }

    public String readCommand() {
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
    }

    public void showLine() {
        System.out.println("_______________________________________");
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

}
