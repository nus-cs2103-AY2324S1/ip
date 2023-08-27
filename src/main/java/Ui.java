import java.util.Scanner;

public class Ui {

    public void welcome() {
        System.out.println("Hello! I'm BoxBox\nWhat can I do for you?");
    }
    public void farewell() {
        System.out.println("Bye! Hope to see you again!");
    }
    public void print(String s) {
        System.out.println(s);
    }

    public void print(Task t) {
        System.out.println(t);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            return "";
        }
    }
}
