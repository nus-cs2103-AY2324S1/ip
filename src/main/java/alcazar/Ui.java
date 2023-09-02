package alcazar;
import java.util.Scanner;
public class Ui {
    public void showWelcome() {
        String line = "____________________________________________________________\n";
        System.out.println(line + " Hello! I'm Alcazar\n" +
                " What can I do for you?\n" +
                line);
    }
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine();
        return st;
    }
    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }
    public void showError(String msg) {
        System.out.println(msg);
    }
    public void showLoadingError() {
        System.out.println("Error loading data from the file");
    }

    public void showExitMsg() {
        this.showLine();
        System.out.println(
                " Bye. Hope to see you again soon!\n"
        );
        this.showLine();
    }
}
