import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        int itemsAdded = 0;
        // do not put line 7 in the while loop
        String[] listToDisplay = new String[100]; // assume there will be <= 100 tasks
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
            } else if (command.equals("list")) {
                for (int i = 1; i <= itemsAdded; i++) {
                    System.out.println(i + ". " + listToDisplay[i - 1]);
                }
            } else {
                listToDisplay[itemsAdded] = command; // add new command
                System.out.println("added: " + command); // show user that it is added
                itemsAdded++; // increment number of items
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

