import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greeting = " Hello! I'm Somebodyhaha\n" +
                " What can I do for you?";
        String exiting = " Bye. Hope to See you again soon!";

        System.out.println("Hello from\n" + logo);
        Printer.print(greeting);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye")) {
            Printer.print(input);
            input = sc.nextLine();
        }

        Printer.print(exiting);
    }
}
