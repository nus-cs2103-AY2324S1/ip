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
            String[] splitted = input.split(" ");
            if (input.equals("bye")) {
                notEnd = false;
                System.out.println("Bye. Hope to see you again soon!");
            } else if (input.equals("list")) {
                ItemList.displayList();
            } else if (splitted[0].equals("mark")) {
                int taskNum = Integer.parseInt(splitted[1]);
                ItemList.setTaskAsDone(taskNum);
            } else if (splitted[0].equals("unmark")) {
                int taskNum = Integer.parseInt(splitted[1]);
                ItemList.setTaskAsUndone(taskNum);
            } else {
                //add items to the array
                ItemList.addToList(input);
            }
        }
    }
}
