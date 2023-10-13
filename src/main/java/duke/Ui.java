package duke;

import java.util.Scanner;

public class Ui {

    /**
     * Displays the information of the application and greet the user.
     */
    public static String showWelcomeMessage() {
        return "Hello! I'm YOLO \n What can I do for you?";
    }

    /**
     * Displays the showFarewell message when the application is terminated.
     */
    public static String showFarewell() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Reads and returns input of the user.
     *
     * @return A string that contains the input of the user.
     */
    public static String readCommand() {

        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
