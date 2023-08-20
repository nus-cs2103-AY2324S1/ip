public class Duke {
    static String HORIZONTAL_LINE = "________________________________________";

    /**
     * Function to greet the User.
     * @return A Horizontal Line and a greeting statement.
     */
    public static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm SeeWhyAre Bot!");
        System.out.println("What can I do for you?");
    }

    /**
     * Function to say goodbye to the User and end the program.
     * @return A Horizontal Line and a farewell statement.
     */
    public static void farewell() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        greet();
        farewell();
        System.out.println(HORIZONTAL_LINE);

    }
}

/**
 * String logo = " ____        _        \n"
 *                 + "|  _ \\ _   _| | _____ \n"
 *                 + "| | | | | | | |/ / _ \\\n"
 *                 + "| |_| | |_| |   <  __/\n"
 *                 + "|____/ \\__,_|_|\\_\\___|\n";
 *         System.out.println("Hello from\n" + logo);
 */
