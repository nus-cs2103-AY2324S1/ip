import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidTaskException;
import duke.exceptions.TaskIndexOutOfBoundsException;

public class Duke {
    protected static String line = "----------------------------------------------------\n";
    protected static List<Task> list = new ArrayList<>();
    protected static Scanner myObj = new Scanner(System.in);

    public static String format_response(String response) {
        return response + "\n\n" + line;
    }

    public static void greet() {
        System.out.println(line);
        System.out.println(format_response(
                "Hello I'm Project54\n" +
                "What can I do for you?"
        ));
    }

    public static Matcher regexParse(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher;
    }

    public static String getCommand(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null; // Return null for empty input or whitespace
        }

        input = input.trim(); // Remove leading and trailing whitespace
        int spaceIndex = input.indexOf(' '); // Find the index of the first space

        if (spaceIndex == -1) {
            return input; // If no space found, the whole string is the first word
        } else {
            return input.substring(0, spaceIndex); // Extract the first word using substring
        }
    }

    public static void printList(List<Task> list){
        String response = "";
        for (int i = 0; i < list.size(); i++) {
            response += String.format("%d. %s\n", i+1, list.get(i));
        }
        System.out.println(format_response(response.substring(0, response.length() - 1)));
    }

    public static void reply() {
        String response = myObj.nextLine();
        while (!response.equals("bye")) {
            processResponse(response);
            response = myObj.nextLine();
        };
    }

    public static void processResponse(String response) {
        Matcher matcher;

        switch(response.split(" ")[0]) {
            case "list":
                printList(list);
                break;
            case "mark":
            case "unmark":
                try {
                    boolean done = response.split(" ")[0].equals("mark") ? true : false;
                    int taskIndex = Integer.parseInt(response.split(" ")[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= list.size()) {
                        throw new TaskIndexOutOfBoundsException("Invalid task index");
                    }
                    list.get(taskIndex).setDone(done);
                    printList(list);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println(format_response("Invalid input. Usage: mark <task_index>"));
                } catch (TaskIndexOutOfBoundsException e) {
                    System.out.println(format_response("Invalid task index. Please provide a valid index."));
                }
                break;
            case "todo":
                try {
                    matcher = regexParse("^todo\\s([\\w\\s]*)$", response);
                    if (!matcher.find() || matcher.groupCount() != 1) {
                        throw new InvalidTaskException("Invalid use of todo. Usage: todo <task description>");
                    }
                    list.add(new ToDo(matcher.group(1)));
                    printList(list);
                } catch (InvalidTaskException e) {
                    System.out.println(format_response(e.getMessage()));
                }
                break;
            case "deadline":
                try {
                    matcher = regexParse("^deadline\\s([\\w\\s]*)\\s\\/by\\s([\\w\\s]*)$", response);
                    if (!matcher.find() || matcher.groupCount() != 2) {
                        throw new InvalidTaskException(
                                "Invalid use of deadline. Usage: deadline <task description> /by <date & time>"
                        );
                    }
                    list.add(new Deadlines(matcher.group(1), matcher.group(2)));
                    printList(list);
                } catch (InvalidTaskException e) {
                    System.out.println(format_response(e.getMessage()));
                }
                break;
            case "event":
                try {
                    matcher = regexParse("^event\\s([\\w\\s]*)\\s\\/from\\s([\\w\\s]*)\\s\\/to\\s([\\w\\s]*)$", response);
                    if (!matcher.find() || matcher.groupCount() != 3) {
                        throw new InvalidTaskException(
                                "Invalid use of event. Usage: event <task description> /from <date & time> /to <date & time>"
                        );
                    }
                    list.add(new Event(matcher.group(1), matcher.group(2), matcher.group(3)));
                    printList(list);
                } catch (InvalidTaskException e) {
                    System.out.println(format_response(e.getMessage()));
                }
                break;
            case "delete":
                try {
                    int taskIndex = Integer.parseInt(response.split(" ")[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= list.size()) {
                        throw new TaskIndexOutOfBoundsException("Invalid task index");
                    }
                    list.remove(Integer.parseInt(response.split(" ")[1])-1);
                    printList(list);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println(format_response("Invalid input. Usage: delete <task_index>"));
                } catch (TaskIndexOutOfBoundsException e) {
                    System.out.println(format_response("Invalid task index. Please provide a valid index."));
                }
                break;
            default: {
                System.out.println(format_response(
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                ));
            }
        }
    }

    public static void bye() {
        System.out.println(format_response(
                "Bye. Hope to see you again soon"
        ));
    }
    public static void main(String[] args) {
        greet();
        reply();
        bye();
    }
}
