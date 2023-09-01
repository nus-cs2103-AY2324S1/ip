package duke;

import java.util.Scanner;

public class Ui {

    /**
     * Displays the information of the application and greet the user.
     */
    public void welcomeMessage() {

        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\tHello from\n" + logo);
        System.out.println(showLine());
        System.out.println("\t Hello! I'm YOLO \n\t What can I do for you? \n");
        System.out.println(showLine());
        System.out.println();
    }

    /**
     * Displays the farewell message when the application is terminated.
     */
    public void farewell() {

        System.out.println(showLine());
        System.out.println("\t Bye. Hope to see you again soon! \n");
        System.out.println(showLine());
    }

    /**
     * Returns a string of '-' that acts a divider.
     *
     * @return A string of dashes that looks like a line.
     */
    public static String showLine() {
        return "\t---------------------------------------------------------------------------";
    }

    /**
     * Reads and returns input of the user.
     *
     * @return A string that contains the input of the user.
     */
    public String readCommand() {

        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
