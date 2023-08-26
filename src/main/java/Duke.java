import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    /**
     * Collection used to store all tasks
     */
    private static final ArrayList<Task> taskList= new ArrayList<>(1);

    /**
     * Formats chatbot output with borders, first line indentation and new line character at the end
     * @param message message to be displayed to user
     */
    private static void output(String message) {
        String line = "    ____________________________________________________________\n";
        String template = line + "     %s\n" + line;
        System.out.printf((template) + "%n", message);
    }

    /**
     * Formats chatbot output specifically for adding/removing tasks
     * @param task task added to list
     */
    private static void taskOutput(Task task, String action) {
        String taskMessage = "I've %s the following task as requested:\n       %s\n     "
                + "There are currently %d tasks in your list.";
        Duke.output(String.format(taskMessage, action, task.toString(), taskList.size()));
    }

    /**
     * Runs the chatbot, terminates when chatbot receives "end" command
     */
    public static void runJyuuni() {
        Duke.output("Hey its Jyuuni, your helpful assistant.\n     How can I help you?");

        Scanner userInput = new Scanner(System.in);
        boolean isRun = true;

        // Requests user input until program is instructed to end
        while (isRun) {
            String input = userInput.nextLine();
            String[] splitInput = input.split(" ", 2);

            switch (splitInput[0]) {
            case "mark":
                try {
                    int index = Integer.parseInt(splitInput[1]);
                    Task mark = taskList.get(index - 1);
                    mark.markAsDone();
                    Duke.output("Good job on completing this task:\n       " + mark);
                } catch (NumberFormatException e) {                    // If argument of "mark" is not a number
                    Duke.output("You need to provide a valid number");
                } catch (IndexOutOfBoundsException e) {                // If argument is not a number from list
                    Duke.output("I can't mark something that doesn't exist...\n     Try a number from the list");
                }
                break;

            case "unmark":
                try {
                    int index = Integer.parseInt(splitInput[1]);
                    Task unmark = taskList.get(index - 1);
                    unmark.markNotDone();
                    Duke.output("Unmarked task as requested:\n       " + unmark);
                } catch (NumberFormatException e) {                    // If argument of "unmark" is not a number
                    Duke.output("You need to provide a valid number");
                } catch (IndexOutOfBoundsException e) {                // If argument is not a number from list
                    Duke.output("I can't unmark something that doesn't exist...\n     Try a number from the list");
                }
                break;

            case "delete":
                try {
                    Task delete = taskList.remove(Integer.parseInt(splitInput[1]) - 1);
                    Duke.taskOutput(delete, "removed");
                } catch (NumberFormatException e) {                    // If argument of "delete" is not a number
                    Duke.output("You need to provide a valid number");
                } catch (IndexOutOfBoundsException e) {                // If argument is not a number from list
                    Duke.output("I can't remove a task that doesn't exist...\n     Try a number from the list");
                }
                break;

            case "todo":
                // Checks for description before creating task
                if (splitInput.length != 2) {
                    Duke.output("Wrong format, try \"todo [DESCRIPTION]\"");
                } else {
                    Task todo = new Todo(splitInput[1]);
                    taskList.add(todo);
                    Duke.taskOutput(todo, "added");
                }
                break;

            case "deadline":
                // ArrayIndexOutOfBounds only thrown when the string is not split -> /by not present
                try {
                    String[] deadVar = splitInput[1].split(" /by ", 2);
                    Task deadline = new Deadline(deadVar[0], deadVar[1]);
                    taskList.add(deadline);
                    Duke.taskOutput(deadline, "added");
                } catch (ArrayIndexOutOfBoundsException e) {
                    Duke.output("Wrong format, try \"deadline [DESCRIPTION] /by [TIME]\"");
                }
                break;

            case "event":
                // Using 1 split statement that splits by /(from|to) allows wrong combos like "/to x /from x"
                try {
                    String[] eventVar = splitInput[1].split(" /from ", 2);
                    String[] times = eventVar[1].split(" /to ", 2);
                    Task event = new Event(eventVar[0], times[0], times[1]);
                    taskList.add(event);
                    Duke.taskOutput(event, "added");
                } catch (ArrayIndexOutOfBoundsException e) {
                    Duke.output("Wrong format, try \"event [DESCRIPTION] /from [START] /to [END]\"");
                }
                break;

            case "list":
                StringBuilder allTasks = new StringBuilder("Here are your tasks:");
                int taskNo = taskList.size();
                for (int i = 0; i < taskNo; i++) {
                    allTasks.append(String.format("\n     %d.%s", (i + 1), taskList.get(i).toString()));
                }
                Duke.output(allTasks.toString());
                break;

            case "end":
                isRun = false;
                Duke.output("Come back if you need anything else!");
                userInput.close();
                break;

            default:
                Duke.output("Sorry, I don't recognise this comment :(");
                break;
            }
        }
    }

    public static void main(String[] args) {
        Duke.runJyuuni();
    }
}
