import java.util.Scanner;
public class Duke {

    private static final Task[] taskList = new Task[100];
    private static int taskTotal = 0;

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
     * Formats chatbot output specifically for task added messages
     * @param task task added to list
     */
    private static void newTaskOutput(Task task) {
        String taskAddMessage = "I've added the following task for you:\n       %s\n     There are currently %d tasks in your list.";
        Duke.output(String.format(taskAddMessage, task.toString(), taskTotal));
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
                        Task item = taskList[index - 1];
                        item.markAsDone();
                        Duke.output("Good job on completing this task:\n       " + item);
                    } catch (NumberFormatException e) {
                        Duke.output("That ain't a number, ya silly");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Duke.output("I can't mark something that doesn't exist...");
                    } catch (NullPointerException e) {
                        Duke.output("You don't have that many tasks");
                    }
                    break;

                case "unmark":
                    try {
                        int index = Integer.parseInt(splitInput[1]);
                        Task item = taskList[index - 1];
                        item.markNotDone();
                        Duke.output("Unmarked task as requested:\n       " + item);
                    } catch (NumberFormatException e) {
                        Duke.output("That ain't a number, ya silly");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Duke.output("I can't mark something that doesn't exist...");
                    } catch (NullPointerException e) {
                        Duke.output("You don't have that many tasks");
                    }
                    break;

                case "todo":
                    // Checks for description before creating task
                    if (splitInput.length != 2) {
                        Duke.output("Wrong format, try \"todo [DESCRIPTION]\"");
                    } else {
                        Task todo = new Todo(splitInput[1]);
                        taskList[taskTotal++] = todo;
                        Duke.newTaskOutput(todo);
                    }
                    break;
                case "deadline":
                    // ArrayIndexOutOfBounds only thrown when the string is not split -> /by not present
                    try {
                        String[] deadVar = splitInput[1].split(" /by ", 2);
                        Task deadline = new Deadline(deadVar[0], deadVar[1]);
                        taskList[taskTotal++] = deadline;
                        Duke.newTaskOutput(deadline);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Duke.output("Wrong format, try \"deadline [DESCRIPTION] /by [TIME]\"");
                    }
                    break;
                case "event":
                    // Using only 1 split statement that splits by /(from|to) allows edge cases like "/to x /from x" combinations
                    try {
                        String[] eventVar = splitInput[1].split(" /from ", 2);
                        String[] times = eventVar[1].split(" /to ", 2);
                        Task event = new Event(eventVar[0], times[0], times[1]);
                        taskList[taskTotal++] = event;
                        Duke.newTaskOutput(event);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Duke.output("Wrong format, try \"event [DESCRIPTION] /from [START] /to [END]\"");
                    }
                    break;
                case "list":
                    StringBuilder allTasks = new StringBuilder("Here are your tasks:");
                    for (int i = 0; i < taskTotal; i++) {
                        allTasks.append(String.format("\n     %d.%s", (i + 1), taskList[i].toString()));
                    }
                    Duke.output(allTasks.toString());
                    break;
                case "end":
                    isRun = false;
                    Duke.output("Come back if you need anything else!");
                    break;
                default:
                    Duke.output("Sorry, I don't recognise this comment :(");
            }
        }
    }

    public static void main(String[] args) {
        Duke.runJyuuni();
    }
}
