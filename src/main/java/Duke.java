import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Introduction
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Cleon\n" +
                " What can I do for you?\n");

        //Initialising Scanner
        Scanner scanner = new Scanner(System.in);
        boolean notEnd = true;

        //Initialising array to store list items
        DukeList ItemList = new DukeList();
        while (notEnd) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                notEnd = false;
                System.out.println("Bye. Hope to see you again soon!");
            }
            if (input.equals("list")) {
                ItemList.displayList();
            }
            if (input.startsWith("mark")) {
                String[] splitted = input.split(" ");
                int taskNum = Integer.parseInt(splitted[1]);
                ItemList.setTaskAsDone(taskNum);
            }
            if (input.startsWith("unmark")) {
                String[] splitted = input.split(" ");
                int taskNum = Integer.parseInt(splitted[1]);
                ItemList.setTaskAsUndone(taskNum);
            }
            if (input.startsWith("todo")) {
                //add items to the array
                String[] splitted = input.split(" ", 2);
                ItemList.addToDo(splitted[1]);
            }
            if (input.startsWith("deadline")) {
                String[] splitted = input.split(" ", 2);
                String[] deadline = splitted[1].split("/by", 2);
                ItemList.addDeadline(deadline[0], deadline[1]);
            } else if (input.startsWith("event")) {
                String[] splitted = input.split(" ", 2);
                String[] from = splitted[1].split("/from", 2);
                String[] to = from[1].split("/to", 2);
                ItemList.addEvent(from[0], to[0], to[1]);
            }
        }
    }
}
