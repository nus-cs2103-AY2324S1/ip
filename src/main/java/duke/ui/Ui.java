package duke.ui;

import java.util.Scanner;

public class Ui {
    Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    public void showWelcome() {
        String Introduction = "____________________________________________________________\n" +
                " Hello! I'm FootyCouch\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(Introduction);
    }
    public void showLine() {
        System.out.println("____________________________________________________________");
    }
    public void showError(String message) {
        System.out.println(" ☹ OOPS!!! " + message);
    }
    public String readCommand() {
        return sc.nextLine();
    }
}