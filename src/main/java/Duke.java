import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;



public class Duke {

    static String termination_word = "BYE";

    public static void main(String[] args) {
        String action;
        ArrayList<Task> history = new ArrayList<>();
        Duke.sayHi();
        Scanner reader = new Scanner(System.in);
        action = reader.nextLine().toString();
        String[] parts = action.split(" ");
        String check = parts[0].toUpperCase();
        while (!check.equals(termination_word)) {
            try {
                history = Duke.actions(action, history);
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

    private static void sayHi() {
        String logo = "YONG";
        System.out.println("Hello I'm " + logo + "\n" +
                "What can I do for you? \n" +
                "------------------------------------- \n"
        );
    }

    private static void sayBye() {
        System.out.println("Thank you and have a good day!");
    }



    private static ArrayList<Task> actions(String inp, ArrayList<Task> history) {
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
                for (int i = 0; i < history.size(); i++) {
                    System.out.println(i+1 + ": " + history.get(i));
                }
                break;
            case "MARK":
                try {
                    Integer number = Integer.parseInt(numberString);
                    if (number > history.size() || number < 0) {
                        throw new DukeException("Wrong Param");
                    }
                    Task task = history.get(number-1);
                    task.markAsDone();
                    System.out.println("YONG has marked this task as completed! \n" + task.toString() + "\n");
                }
                catch (Exception e) {
                    System.out.println("Invalid integer input");
                }
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
        return history;
    }

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
