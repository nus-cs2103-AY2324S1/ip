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
        System.out.println(line + "     " + message + "\n" + line);
    }

    public static void main(String[] args) {
        Duke.output("Hey its Jyuuni, your helpful assistant.\n     How can I help you?");

        Scanner userInput = new Scanner(System.in);
        boolean isRun = true;

        // Waits for input until program stops running
        while (isRun) {
            String input = userInput.nextLine();
            String[] splitInput = input.split(" ");

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
                case "list":
                    StringBuilder allTasks = new StringBuilder("Here are your tasks:");
                    for (int i = 0; i < taskTotal; i++) {
                        allTasks.append("\n     ").append(i + 1).append(".").append(taskList[i].toString());
                    }
                    Duke.output(allTasks.toString());
                    break;
                case "end":
                    isRun = false;
                    Duke.output("Come back if you need anything else!");
                    break;
                default:
                    taskList[taskTotal++] = new Task(input);
                    Duke.output("added: " + input);
            }
        }
    }
}
