import java.util.Scanner;
public class Duke {
    public static String line = "____________________________________________________________\n";
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String greeting =
                line +
                "Hello! I'm DukeBot\n" +
                "What can I do for you?\n" +
                line;

        String exitMessage = "Bye. Hope to see you again\n" + line;
        System.out.println(greeting);

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String echo = myObj.nextLine();  // Read user input

        while (!echo.equalsIgnoreCase("bye")) {
            System.out.println("");
            System.out.println(line + echo + "\n" + line);
            echo = myObj.nextLine();  // Read user input
        }
        System.out.println(exitMessage);
    }
}
