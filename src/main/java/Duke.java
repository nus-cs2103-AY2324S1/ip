import java.util.Scanner;

public class Duke {

    public static String dash = "\t-------------------------------------------------------------";

    public static void welomeMessage() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(dash);
        System.out.println("\t Hello! I'm YOLO \n\t What can I do for you? \n");
        System.out.println(dash);
        System.out.println();
    }

    public static void farewell() {

        System.out.println(dash);
        System.out.println("\t Bye. Hope to see you again soon! \n");
        System.out.println(dash);
    }

    public static void echo(String message) {

        System.out.println(dash);
        System.out.println("\t" + message + "\n");
        System.out.println(dash);
    }

    public static void main(String[] args) {

        welomeMessage();
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();

        while (!message.equals("bye")) {

            echo(message);
            message = sc.nextLine();
        }

        farewell();
    }
}
