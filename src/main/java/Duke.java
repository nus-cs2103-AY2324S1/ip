import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "____________________________________________________________\n"
                + "Hello! I'm Lorem\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";

        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        String ending = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";

        System.out.println(greeting);
        while (!userInput.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(userInput);
            System.out.println("____________________________________________________________");
            userInput = sc.nextLine();
        }
        sc.close();
        System.out.println(ending);
    }
}
