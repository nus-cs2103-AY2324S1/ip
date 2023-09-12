package duke;

import java.util.Scanner;

public class Ui {

    /**
     * Displays the information of the application and greet the user.
     */
    public static String welcomeMessage() {

        String logo = "____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";

        String result = "\nHello from\n" + logo + showLine() + "\n Hello! I'm YOLO \n What can I do for you? \n" + showLine();
        return result;
    }

    /**
     * Displays the farewell message when the application is terminated.
     */
    public static String farewell() {

        String result = showLine() + "\n Bye. Hope to see you again soon! \n" + showLine();
        return result;
    }

    /**
     * Returns a string of '-' that acts a divider.
     *
     * @return A string of dashes that looks like a line.
     */
    public static String showLine() {
        return "--------------------------------------------";
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
