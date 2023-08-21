import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String divider = "____________________________________________________________";
        System.out.println(divider);
        System.out.println("Hello! I'm BanterBot");
        System.out.println("What can I do for you lmao?");
        System.out.println(divider);

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            System.out.println(divider);
            System.out.println(command);
            System.out.println(divider);
            command = scanner.nextLine();
        }
        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon lol!");
        System.out.println(divider);
    }
}
