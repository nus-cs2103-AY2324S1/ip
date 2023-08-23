import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello friend :> My name is John, nice to meet you!\n" +
                "Feel free to ask me anything.\n");

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Reply John: ");
            String command = sc.nextLine(); // using .next() is wrong - only reads first word

            if (command.equals("bye")) {
                System.out.println("Bye for now, hope to see you soon.");
                sc.close();
                break;
            } else {
                System.out.println(command);
            }
        }

    }
}

/*
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
 */

