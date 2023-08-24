import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = "    ____________________________________________________________\n" +
                       "    Hello! I'm Not A ChatBot\n" + 
                       "    What can I do for you?\n" +
                       "    ____________________________________________________________";
        String end = "    ____________________________________________________________\n" +
                     "    Bye. Hope to see you again soon!\n" +
                     "    ____________________________________________________________";
        System.out.println(intro);
        String message = scanner.nextLine();
        while(!message.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("    " + message);
            System.out.println("    ____________________________________________________________");
            message = scanner.nextLine();
        }
        System.out.println(end);
    }
}
