import java.util.Scanner;

public class Duke {

    public static void echo(String message) {
        String echoed = "\t____________________________________________________________\n"
                + "\t " + message + "\n"
                + "\t____________________________________________________________\n";
        System.out.println(echoed);
    }

    public static void main(String[] args) {
        /**
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        String intro_message = "\t____________________________________________________________\n"
                + "\t Hello! I'm Bob the Chatbot!\n"
                + "\t What can I do for you?\n"
                + "\t____________________________________________________________\n";

        String bye_message = "\t____________________________________________________________\n"
                + "\t Bye. Hope to see you again soon!\n"
                + "\t____________________________________________________________";

        System.out.println(intro_message);
        Scanner sc = new Scanner(System.in);
        String message = "";

        while (true) {
            message = sc.nextLine();
            if (message.equals("bye")) break;
            echo(message);
        }

        System.out.println(bye_message);
    }
}
