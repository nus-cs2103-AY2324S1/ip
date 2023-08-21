import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo)
        String lines = "______________________________";
        Scanner scan = new Scanner(System.in);

        System.out.println(lines);
        System.out.println("Hello! I'm MeowBot!");
        System.out.println("What can I do for you ?");

        String command = scan.nextLine();

        while (!command.equals("bye")) {
            System.out.println(lines);
            System.out.println(command);
            System.out.println(lines);
            command = scan.nextLine();

        }


        System.out.println(lines);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
