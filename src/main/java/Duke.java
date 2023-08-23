import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Chatbot named YONG that responds to user input using CLI
 */
public class Duke {

    static String termination_word = "BYE";
    static ArrayList<Task> history = new ArrayList<>();

    public static void main(String[] args) {
        String action;
        Duke.sayHi();
        Scanner reader = new Scanner(System.in);
        action = reader.nextLine().toString();
        String[] parts = action.split(" ");
        String check = parts[0].toUpperCase();
        while (!check.equals(termination_word)) {
            try {
                Duke.actions(action);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                action = reader.nextLine().toString();
                parts = action.split(" ");
                check = parts[0].toUpperCase();
            }

        }
        Duke.sayBye();

    }

    /**
     * Method to say Hi to users
     */
    private static void sayHi() {
        String logo = "YONG";
        System.out.println("Hello I'm " + logo + "\n" +
                "What can I do for you? \n" +
                "------------------------------------- \n"
        );
    }

    /**
     * Method to say bye upon termination of program
     */
    private static void sayBye() {
        System.out.println("Thank you and have a good day!");
    }


    /**
     * Method that takes in user input and runs the suitable function
     * @param inp User input in a string format
     */
    private static void actions(String inp) {
        inp = inp.toUpperCase();
        String [] parts = inp.split(" ", 2);
        String check = parts[0];
        String numberString = parts.length > 1 ? parts[1] : "";
        switch (check) {
            case "BYE":
                System.out.println();
                break;
            case "HI":
                System.out.println("How are you doing today! \n");
                break;
            case "LIST":
                Duke.list();
                break;
            case "MARK":
                Duke.mark(numberString);
                break;
            case "UNMARK":
                Duke.unmark(numberString);
                break;
            case "DELETE":
                Duke.delete(numberString);
                break;
            case "TODO":
                ToDo toDo = Duke.toDo(inp);
                history.add(toDo);
                System.out.println("Okay! Task added \n" + toDo + "\n");
                break;
            case "EVENT":
                Event event = Duke.event(inp);
                history.add(event);
                System.out.println("Okay! Task added \n" + event + "\n");
                break;
            case "DEADLINE":
                Deadline deadLine = Duke.deadline(inp);
                history.add(deadLine);
                System.out.println("Okay! Task added \n" + deadLine + "\n");
            default:
                throw new DukeException("I do not know what you are saying.");
        }
    }

    /**
     * Method that displays the list of tasks stored
     */
    private static void list() {
        for (int i = 0; i < history.size(); i++) {
            System.out.println(i+1 + ": " + history.get(i));
        }
    }

    /**
     * Method that marks a specific task as being done
     * @param numberString User input number corresponding to the task that should be marked
     */
    private static void mark(String numberString) {
        Integer number = Integer.parseInt(numberString);
        if (number > history.size() || number < 0) {
            throw new DukeException("Wrong Param");
        }
        Task task = history.get(number-1);
        task.markAsDone();
        System.out.println("YONG has marked this task as completed! \n" + task.toString() + "\n");
    }

    /**
     * Method that marks a specific task as being undone
     * @param numberString User input number corresponding to the task that should be marked
     */
    private static void unmark(String numberString) {
        Integer number = Integer.parseInt(numberString);
        if (number > history.size() || number < 0) {
            throw new DukeException("Wrong Param");
        }
        Task task = history.get(number-1);
        task.unmarkAsDone();
        System.out.println("YONG has unmarked this task! \n" + task.toString() + "\n");
    }

    /**
     * Method that deletes a specific task
     * @param numberString User input number corresponding to the task that should be deleted
     */
    private static void delete(String numberString) {
        Integer number = Integer.parseInt(numberString);
        if (number > history.size() || number < 0) {
            throw new DukeException("Wrong Param");
        }
        Task task = history.remove(number-1);
        System.out.println("YONG has deleted this task for you! \n" + task.toString() + "\n");
    }

    /**
     * Method to create a Todo task and add it to the list of tasks
     * @param inp User input of the entire string corresponding to the TODO task
     * @return ToDo object with the description provided
     */
    private static ToDo toDo(String inp) {
        try {
            String[] type_description = inp.split(" ", 2);
            String type = type_description[0];
            String description = type_description[1];
            return new ToDo(description);
        } catch (Exception e) {
            throw new DukeException("Please give a valid description for a ToDo task!");
        }
    }

    /**
     * Method to create a Deadline task and add it to the list of tasks
     * @param inp User input of the entire string corresponding to the Deadline task
     * @return Deadline object with the description provided
     */
    private static Deadline deadline(String inp) {
        try {
            String[] parts = inp.split("/", 2);
            String[] type_description = parts[0].split(" ", 2);
            String type = type_description[0];
            String description = type_description[1];
            return new Deadline(description, parts[1]);
        } catch (Exception e) {
            throw new DukeException("Please give a valid description for a Deadline task!");
        }
    }

    /**
     * Method to create an Event task and add it to the list of tasks
     * @param inp User input of the entire string corresponding to the Event task
     * @return Event object with the description provided
     */
    private static Event event(String inp) {
        try {
            String[] parts = inp.split("/", 3);
            String[] type_description = parts[0].split(" ", 2);
            String type = type_description[0];
            String description = type_description[1];
            return new Event(description, parts[1], parts[2]);
        } catch (Exception e) {
            throw new DukeException("Please give a valid description for an Event task!");
        }
    }


}
