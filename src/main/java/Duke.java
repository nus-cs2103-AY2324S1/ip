import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm froggie! ");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        DukeList dukelist = new DukeList();


        boolean notBye = true;

        while (notBye) {
            String input = scanner.nextLine();
            String[] splited = input.split(" ");

            if (splited[0].equals("bye")) {
                notBye = false;
                System.out.println("Bye. Hope to see you again soon!");
            } else if (splited[0].equals("list")) {
                dukelist.printList();
            } else if (splited[0].equals("mark")) {
                int number = Integer.parseInt(splited[1]);
                dukelist.setDone(number);
            } else if (splited[0].equals("unmark")) {
                int number = Integer.parseInt(splited[1]);
                dukelist.setUndone(number);
            }
            else {
                dukelist.addToList(input);

            }
        }

        scanner.close();
    }
}
