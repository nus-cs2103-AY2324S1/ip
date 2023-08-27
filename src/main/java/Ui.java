import java.util.Scanner;

class Ui {
    private static final String HORIZONTAL_LINE = "----------------------------(≧▽≦)----------------------------";
    private final Scanner scanner;

    Ui() {
        this.scanner = new Scanner(System.in);
    }

    void showHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    void showMessage(String msg) {
        showHorizontalLine();
        System.out.println(msg);
        showHorizontalLine();
    }

    void helloGreeting() {
        showMessage("Hello! I'm ForsakenX\nWhat can I do for you?");
    }

    void byeGreeting() {
        showMessage("Bye. Hope to see you again soon!");
    }

    void showLoadingError() {
        showMessage(" ☹ Loading error! File may be corrupted.");
    }

    void showError(String errorMsg) {
        showMessage(errorMsg);
    }

    String readCommand() {
        return scanner.nextLine();
    }
}
