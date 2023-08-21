import java.util.Scanner;
public class Duke {

    private static final Task[] taskList = new Task[100];
    private static int taskTotal = 0;

    /**
     * Formats chatbot output with borders, first line indentation and new line at the end
     * @param message message to be displayed to user
     */
    public static void output(String message) {
        String line = "    ____________________________________________________________\n";
        String template = line + "     %s\n" + line;
        System.out.printf((template) + "%n", message);
    }

    /**
     * Formats chatbot output for task added messages
     * @param task task added to list
     */
    public static void newTaskOutput(Task task) {
        String taskAddMessage = "I've added the following task for you:\n       %s\n     There are currently %d tasks in your list.";
        Duke.output(String.format(taskAddMessage, task.toString(), taskTotal));
    }

    public static void main(String[] args) {
        Duke.output("Hey its Jyuuni, your helpful assistant.\n     How can I help you?");

        Scanner userInput = new Scanner(System.in);
        boolean isRun = true;

        // Waits for input until program stops running
        while (isRun) {
            String input = userInput.nextLine();
            String[] splitInput = input.split(" ", 2);

            switch (splitInput[0]) {
                case "mark":
                    Task item1 = taskList[Integer.parseInt(splitInput[1]) - 1];
                    item1.markAsDone();
                    Duke.output("Good job on completing this task:\n       " + item1);
                    break;
                case "unmark":
                    Task item2 = taskList[Integer.parseInt(splitInput[1]) - 1];
                    item2.markNotDone();
                    Duke.output("Unmarked task as requested:\n       " + item2);
                    break;
                case "todo":
                    Task todo = new Todo(splitInput[1]);
                    taskList[taskTotal++] = todo;
                    Duke.newTaskOutput(todo);
                    break;
                case "deadline":
                    String[] deadVar = splitInput[1].split(" /by ", 2);
                    Task deadline = new Deadline(deadVar[0], deadVar[1]);
                    taskList[taskTotal++] = deadline;
                    Duke.newTaskOutput(deadline);
                    break;
                case "event":
                    String[] eventVar = splitInput[1].split(" /(from|to) ", 3);
                    Task event = new Event(eventVar[0], eventVar[1], eventVar[2]);
                    taskList[taskTotal++] = event;
                    Duke.newTaskOutput(event);
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
}
