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
            String[] splited = input.split(" ", 2);


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
            } else if (splited[0].equals("todo")) {
                String[] job = input.split(" ", 2);
                dukelist.addTodo(job[1]);
            } else if (splited[0].equals("deadline")) {
                String[] splitted = input.split(" ", 2);
                String[] deadline = splitted[1].split("/by", 2);
                dukelist.addDeadline(deadline[0], deadline[1]);
            } else if (splited[0].equals("event")) {
                String[] splitted = input.split(" ", 2);
                String[] from = splitted[1].split("/from", 2);
                String[] to = from[1].split("/to", 2);
                dukelist.addEvent(from[0], to[0], to[1]);
            }
        }

        scanner.close();
    }
}
