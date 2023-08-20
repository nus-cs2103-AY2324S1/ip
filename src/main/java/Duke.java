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
        String check = action.toUpperCase();
        while (!check.equals(termination_word)) {
            history = Duke.actions(check, action, history);
            action = reader.nextLine().toString();
            check = action.toUpperCase();
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



    private static ArrayList<Task> actions(String check, String inp, ArrayList<Task> history) {
        inp = inp.toUpperCase();
        String [] parts = check.split(" ", 2);
        String numberString = parts.length > 1 ? parts[1] : "";
        switch (check) {
            case "BYE":
                System.out.println();
                break;
            case "HI":
                System.out.println("How are you doing today!");
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
                        throw new InvalidParameterException("Wrong Param");
                    }
                    Task task = history.get(number-1);
                    task.markAsDone();
                    System.out.println("YONG has marked this task as completed! \n" + task.toString());
                }
                catch (Exception e) {
                    System.out.println("Invalid integer input");
                }
                break;
            default:
                Task t = Duke.parseInput(inp);
                history.add(t);
                System.out.println(t);
        }
        return history;
    }

    private static Task parseInput(String line) {
        String[] parts = line.split("/", 3);
        String[] type_description = parts[0].split(" ", 2);
        String type = type_description[0];
        String description = type_description[1];
        if (parts.length == 1) {
            return new ToDo(description);
        } else if (parts.length == 2) {
            return new Deadline(description, parts[1]);
        } else if (parts.length == 3) {
            return new Event(description, parts[1], parts[2]);
        } else {
            throw new InvalidParameterException();
        }
    }


}
