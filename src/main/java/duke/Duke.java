package duke;

import java.io.IOException;

import java.util.Scanner;

import duke.assets.storage.Storage;

/**
 * The main class for the ChadGPT chatbot application.
 */
public class Duke {
    static final String LOGO = "\n   _____ _    _          _____   _____ _____ _______ \n" +
            "  / ____| |  | |   /\\   |  __ \\ / ____|  __ \\__   __|\n" +
            " | |    | |__| |  /  \\  | |  | | |  __| |__) | | |   \n" +
            " | |    |  __  | / /\\ \\ | |  | | | |_ |  ___/  | |   \n" +
            " | |____| |  | |/ ____ \\| |__| | |__| | |      | |   \n" +
            "  \\_____|_|  |_/_/    \\_\\_____/ \\_____|_|      |_|   \n";
    static final String HORIZONTAL = "----------------------------------------------------------" +
            "-----------------------------";

    static final Storage storage = new Storage();

    /**
     * The main method for the Duke chatbot application.
     *
     * @param args the command-line arguments
     * @throws IOException if there is an error reading or writing to the storage file
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        while (!storage.readFromFile()) {
            System.out.print("User: ");
            String nextLine = sc.nextLine();
            if (nextLine.equals("exit")) {
                sc.close();
                System.exit(0);
            } else if (nextLine.equals("\n")) {
                continue;
            }
        }

        System.out.println("ChadGPT: Welcome to ChadGPT, What would you like to do today?\n" + HORIZONTAL);
        System.out.print("User: ");
        while (!sc.hasNext("bye")) {
            String nextLine = sc.nextLine();
            storage.passUserCommand(nextLine);
            System.out.print(HORIZONTAL + "\nUser: ");
        }
        sc.close();

        storage.passUserCommand("bye");
    }

    /**
     * Print the logo and necessary formatting into terminal at startup
     */
    private void printAtStartup() {
        System.out.println(HORIZONTAL + LOGO + HORIZONTAL);
    }
}