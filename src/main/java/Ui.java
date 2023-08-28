import java.util.Scanner;

public class Ui {
    private final String line = "_____________________________________________________";

    private Scanner sc = new Scanner(System.in);


    public void greet() {
        System.out.println("Hello! I'm Gerald_Bot\n" + "What can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println(line);
    }

    public String readInput() {
        String input = this.sc.nextLine();
        return input;
    }
}
