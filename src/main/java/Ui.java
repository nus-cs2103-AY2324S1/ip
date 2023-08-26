import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        String logo = "    ____        __              __ \n"
                + "   / __ \\____  / /_  ___  _____/ /_\n"
                + "  / /_/ / __ \\/ __ \\/ _ \\/ ___/ __/\n"
                + " / _, _/ /_/ / /_/ /  __/ /  / /_  \n"
                + "/_/ |_|\\____/_.___/\\___/_/   \\__/  \n"
                + "\n";

        String text = "Hello! I'm Robert, your Personal Assistant Chatbot.\n"
                + "What can I do for you today?";

        this.showLine();
        System.out.print(formatOutput(logo + text));
        this.showLine();
    }
    public void showLoadingError() {
        System.out.print(formatOutput("WARNING: Your previously stored tasks seems to be corrupted.\n"
                + "As a result, your previous lists of tasks will now be cleared. Apologies!"));
    }

    public void showLine() {
        System.out.print("\t____________________________________________________________\n");
    }

    public void showMessage(String message) {
        System.out.print(formatOutput(message));
    }

    public void showError(String errorMessage) {
        System.out.print(formatOutput(errorMessage));
    }

    public String readCommand() {
        return new Scanner(System.in).nextLine();
    }

    private static String formatOutput(String output) {
        String[] outputLines = output.split("\n");
        for (int i = 0; i < outputLines.length; i++) {
            outputLines[i] = "\t" + outputLines[i] + "\n";
        }
        return String.join("", outputLines);
    }
}
