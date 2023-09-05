package duke;

import java.util.Scanner;

/**
 * Acts as the user interface by printing messages and reading input.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Indents each line of a message.
     *
     * @param message The message to be indented.
     * @return The indented message.
     */
    private static String indent(String message) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] lines = message.split("\n");

        for (int i = 0; i < lines.length; i++) {
            stringBuilder.append("\t").append(lines[i]);
            if (i < lines.length - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public String getInput() {
        return this.scanner.nextLine();
    }

    public void displayMessage(String message) {
        this.displayMessage(message, true);
    }

    public void displayMessage(String message, boolean hasLowerLine) {
        this.displayMessage(message, hasLowerLine, true);
    }

    /**
     * Displays a message to the user, formatted for the application.
     *
     * @param message      The message to be displayed.
     * @param hasLowerLine Whether to display a bottom line.
     * @param hasUpperLine Whether to display a top line.
     */
    public void displayMessage(String message, boolean hasLowerLine, boolean hasUpperLine) {
        StringBuilder stringBuilder = new StringBuilder();
        if (hasUpperLine) {
            stringBuilder.append(Messages.HORIZONTAL_LINE + "\n");
        }
        stringBuilder.append(message);
        if (hasLowerLine) {
            stringBuilder.append("\n" + Messages.HORIZONTAL_LINE);
        }
        System.out.println(Ui.indent(stringBuilder.toString()));
    }


    /**
     * Displays an error to the user, formatted for the application.
     *
     * @param message The error message to be displayed.
     */
    public void displayError(String message) {
        System.out.println(Ui.indent(Messages.HORIZONTAL_LINE + "\n" +
                String.format(Messages.ERROR_PREFIX, message) + "\n" + Messages.HORIZONTAL_LINE));
    }
}
