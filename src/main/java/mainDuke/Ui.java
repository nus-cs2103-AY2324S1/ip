package mainDuke;

import java.util.ArrayList;
import java.util.Scanner;

import mainDuke.exceptions.DukeException;

/**
 * Responsible for printing messages and receiving commands.
 */
public class Ui {
    /**
     * Scanner to read input.
     */
    private static final Scanner SC = new Scanner(System.in);

    /**
     * Read and returns command.
     *
     * @return String of user input.
     */
    public static String readCommand() {
        return SC.nextLine();
    }

    /**
     * Prints a message to the user.
     * @param message String message to be printed.
     */
    public static void print(String message) {
        System.out.println(message);
    }

    /**
     * Greeting to be printed at start of program.
     */
    public static void printGreeting() {
        String name = "Chaty";

        System.out.println("Hello! I'm " + name + "\n"
                + "What can I do for you?" + "\n\n");
    }

    /**
     * Prints an arraylist, a new line for each object in the list.
     * @param list list of objects to be printed.
     */
    public static void printArrayList(ArrayList list) {
        for (int i = 0; i < list.size(); i++) {
            Ui.print((i + 1) + "." + list.get(i));
        }
    }
    public static String getListAsString(ArrayList list) throws DukeException {
        if (list.size() == 0) {
            throw new DukeException("There are no tasks yet");
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Object obj : list) {
            stringBuilder.append(obj.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
