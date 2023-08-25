import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String SEPARATION_LINE  = "____________________________________________________________";
    private static final String INDENTATION = "    ";
    private static final String GREETING_TEXT = "Hello, I'm Tasket\n" + INDENTATION + "What can I do for you?";
    private static final String GOODBYE_TEXT = "Bye. Hope to see you again soon!";

    private static String prompt = "";
    private static final ArrayList<Task> list = new ArrayList<>();

    public static void showSeparationLine() {
        System.out.println(INDENTATION + SEPARATION_LINE);
    }

    public static void showGreetingText() {
        showSeparationLine();
        System.out.println(INDENTATION + GREETING_TEXT);
        showSeparationLine();
    }

    public static void showGoodbyeText() {
        System.out.println(INDENTATION + GOODBYE_TEXT);
        showSeparationLine();
    }

    public static void showErrorText(String msg) {
        System.out.println(INDENTATION + "OOPS!!! " + msg);
        showSeparationLine();
    }

    public static void showLists() {
        System.out.println(INDENTATION + "Here are the tasks in your list:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.printf("%s%d. %s\n", INDENTATION, i, list.get(i - 1).toString());
        }
        showSeparationLine();
    }

    public static void identifyTaskType(String type, String prompt) throws DukeException {
        Task task = null;
        switch (type) {
        case "todo":
            task = createToDoTask(prompt);
            break;

        case "deadline":
            task = createDeadlineTask(prompt);
            break;

        case "event":
            task = createEventTask(prompt);
            break;
        }

        list.add(task);

        System.out.println(INDENTATION + "Got it, I've added this task:\n" + INDENTATION + task.toString());
        System.out.printf(INDENTATION + "Now you have %d tasks in the list\n", list.size());
        showSeparationLine();
    }

    public static Task createToDoTask(String prompt) throws DukeException {
        String[] descriptions = prompt.split(" ", 2);
        if (descriptions.length != 2 || descriptions[1].replaceAll(" ", "").isEmpty()) {
            throw new DukeException("The description of todo cannot be empty");
        }

        return new ToDo(descriptions[1]);
    }

    public static Task createDeadlineTask(String prompt) throws DukeException {
        String[] descriptions = prompt.split(" ", 2);
        if (descriptions.length != 2 || descriptions[1].replaceAll(" ", "").isEmpty()) {
            throw new DukeException("The description of deadline cannot be empty");
        }

        String[] desSplit = descriptions[1].split(" /by ", 2);
        if (desSplit.length != 2 || desSplit[1].replaceAll(" ", "").isEmpty()) {
            throw new DukeException("The deadline cannot be empty");
        }

        return new Deadline(desSplit[0], desSplit[1]);
    }

    public static Task createEventTask(String prompt) throws DukeException {
        String[] descriptions = prompt.split(" ", 2);
        if (descriptions.length != 2 || descriptions[1].replaceAll(" ", "").isEmpty()) {
            throw new DukeException("The description of event cannot be empty");
        }

        String[] desSplit = descriptions[1].split(" /from ", 2);
        if (desSplit.length != 2 || desSplit[1].replaceAll(" ", "").isEmpty()) {
            throw new DukeException("The start time cannot be empty");
        }

        String[] eventLength = desSplit[1].split(" /to ", 2);
        if (eventLength.length != 2 || eventLength[1].replaceAll(" ", "").isEmpty()) {
            throw new DukeException("The end time cannot be empty");
        }

        return new Event(desSplit[0], eventLength[0], eventLength[1]);
    }

    public static void markTaskDone(String prompt) throws DukeException {
        String[] descriptions = prompt.split(" ", 2);
        if (descriptions.length != 2 || descriptions[1].replaceAll(" ", "").isEmpty()) {
            throw new DukeException("The task index cannot be empty");
        }

        try {
            Task selectedTask = list.get(Integer.parseInt(descriptions[1]) - 1);

            selectedTask.markAsDone();

            System.out.println(INDENTATION + "Nice! I've marked this task as done:\n"
                    + INDENTATION + "  " + selectedTask.toString());
            showSeparationLine();
        } catch (NumberFormatException e) {
            throw new DukeException("The task index must be a number");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task index exceeds the size of list");
        }
    }

    public static void markTaskUndone(String text) throws DukeException {
        String[] descriptions = prompt.split(" ", 2);
        if (descriptions.length != 2 || descriptions[1].replaceAll(" ", "").isEmpty()) {
            throw new DukeException("The task index cannot be empty");
        }

        try {
            Task selectedTask = list.get(Integer.parseInt(descriptions[1]) - 1);

            selectedTask.markAsUndone();

            System.out.println(INDENTATION + "Ok. I've marked this task as not done yet:\n"
                    + INDENTATION + "  " + selectedTask.toString());
            showSeparationLine();
        } catch (NumberFormatException e) {
            throw new DukeException("The task index must be a number");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task index exceeds the size of list");
        }
    }

    public static void parseInput(String text) throws DukeException {
        String[] actions = text.split(" ", 2);

        switch (actions[0]) {
        case "todo":
        case "deadline":
        case "event":
            identifyTaskType(actions[0], text);
            break;

        case "mark":
            markTaskDone(text);
            break;

        case "unmark":
            markTaskUndone(text);
            break;

        case "list":
            showLists();
            break;

        case "bye":
            showGoodbyeText();
            break;

        default:
            throw new DukeException("I'm sorry, but I don't know what it means :(");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        showGreetingText();

        while (!prompt.equals("bye")) {
            try {
                System.out.println();
                prompt = sc.nextLine();
                showSeparationLine();

                parseInput(prompt);
            } catch (DukeException exception) {
                showErrorText(exception.getMessage());
            }
        }

        sc.close();
    }
}
