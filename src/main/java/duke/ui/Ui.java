package duke.ui;

import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_LINE = "----------------------------(≧▽≦)----------------------------";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void showMessage(String msg) {
        showHorizontalLine();
        System.out.println(msg);
        showHorizontalLine();
    }

    public void helloGreeting() {
        showMessage("Hello! I'm ForsakenX\nWhat can I do for you?");
    }

    public void byeGreeting() {
        showMessage("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        showMessage(" ☹ Loading error! File may be corrupted.");
    }

    public void showError(String errorMsg) {
        showMessage(errorMsg);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
