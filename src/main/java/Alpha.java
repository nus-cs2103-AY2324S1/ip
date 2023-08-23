import java.util.Objects;
import java.util.Scanner;
import java.util.Arrays;

public class Alpha {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //End trigger to end the program
        String END = "bye";

        // List trigger word to display a log of stored text
        String LIST = "list";

        String CHECK = "mark";

        String UNCHECK = "unmark";

        String TODO = "todo";

        String DEADLINE = "deadline";

        String EVENT = "event";

        // Intro message
        String intro = "____________________________________________________________\n" +
                " Hello! I'm Alpha\n" +
                " What can I do for you?\n";

        //Outro message
        String end = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";

        // Create a List class that performs all list operations
        TaskList taskList = new TaskList();
        System.out.println(intro);
        String input = sc.nextLine();
        String[] splitInput = input.split(" "); // Splits string to check for "mark" or "unmark"

        while (!input.equals(END)) {
            if (input.equals(LIST)) {
                taskList.display();
            } else if (splitInput[0].equals(CHECK)) {
                taskList.mark(Integer.parseInt(splitInput[1]));
            } else if (splitInput[0].equals(UNCHECK)) {
                taskList.unmark(Integer.parseInt(splitInput[1]));
            } else if (splitInput[0].equals(DEADLINE)) {
                String[] splitDeadline = input.split("/");
                Deadline deadline = new Deadline(splitDeadline[0].split(" ")[1], splitDeadline[1]);
                taskList.add(deadline);
            } else if (splitInput[0].equals(EVENT)) {
                String[] splitEvent = input.split("/");
                Event event = new Event(splitEvent[0].split(" ")[1], splitEvent[1], splitEvent[2]);
                taskList.add(event);
            } else if (splitInput[0].equals(TODO)) {
                ToDo todo = new ToDo(splitInput[1]);
                taskList.add(todo);
            }
            input = sc.nextLine();
            splitInput = input.split(" "); // Splits string to check for "mark" or "unmark"
        }

        System.out.println(end);
    }
}
