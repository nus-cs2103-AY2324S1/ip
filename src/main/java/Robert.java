import java.util.Scanner;

public class Robert {
    private static Task[] tasks = new Task[100];

    private static void greetUser() {
        String logo = "    ____        __              __ \n"
                + "   / __ \\____  / /_  ___  _____/ /_\n"
                + "  / /_/ / __ \\/ __ \\/ _ \\/ ___/ __/\n"
                + " / _, _/ /_/ / /_/ /  __/ /  / /_  \n"
                + "/_/ |_|\\____/_.___/\\___/_/   \\__/  \n"
                + "\n";

        String text = "Hello! I'm Robert, your Personal Assistant Chatbot.\n"
                + "What can I do for you today?";

        String output = Robert.formatOutput(logo + text);
        System.out.println(output);
    }

    private static void runChatbot() {
        Scanner scanner = new Scanner(System.in);
        Boolean isUnderExecution = true;

        String[] userInput;
        while (isUnderExecution) {
            userInput = scanner.nextLine().split(" ", 2);

            String classification = userInput[0]; // First word indicates the type of action
            String parameters = ""; // Second word onwards are parameters for different actions
            if (userInput.length > 1) {
                parameters = userInput[1];
            }
            
            switch (classification) {
            case "list":
                Robert.listTasks();
                break;
            
            case "mark":
                int markingTaskIndex = Integer.parseInt(parameters) - 1;
                Robert.markTask(markingTaskIndex);
                break;

            case "unmark":
                int unmarkingTaskIndex = Integer.parseInt(parameters) - 1;
                Robert.unmarkTask(unmarkingTaskIndex);
                break;
            
            case "todo":
                ToDo newToDo = new ToDo(parameters);
                Robert.addTask(newToDo);
                break;

            case "event":
                String[] eventParameters = parameters.split(" /from ");

                String eventDescription = eventParameters[0];
                String from = eventParameters[1].split(" /to ")[0];
                String to = eventParameters[1].split(" /to ")[1];

                Event newEvent = new Event(eventDescription, from, to);
                Robert.addTask(newEvent);
                break;

            case "deadline":
                String[] deadlineParameters = parameters.split(" /by ");

                String deadlineDescription = deadlineParameters[0];
                String by = deadlineParameters[1];

                Deadline newDeadline = new Deadline(deadlineDescription, by);
                Robert.addTask(newDeadline);
                break;    

            case "bye":
                isUnderExecution = false;
                break;

            default:
                Robert.outputMessage("Invalid command. Try again!");
                break;
            }
        }

        scanner.close();
    }

    private static void listTasks() {
        String taskListing = "Here are the tasks in your list:\n";
        for (int i = 0; i < Task.taskCount; i++) {
            taskListing += String.format("%d. %s\n", i + 1, Robert.tasks[i]);
        }
        Robert.outputMessage(taskListing);
    }

    private static void addTask(Task task) {
        Robert.tasks[Task.taskCount++] = task;

        String taskPlurality = "task";
        if (Task.taskCount > 1) {
            taskPlurality = "tasks";
        }
        String text = "Got it. I have added this task:\n  " + task
                + "\nNow you have " + Task.taskCount + " " + taskPlurality + " in the list.";
        Robert.outputMessage(text);
    }

    private static void markTask(int taskIndex) {
        Robert.tasks[taskIndex].markAsDone();
        String text = "Nice! I've marked this task as done:\n  " + Robert.tasks[taskIndex];
        Robert.outputMessage(text);
    }

    private static void unmarkTask(int taskIndex) {
        Robert.tasks[taskIndex].markAsUndone();
        String text = "Ok, I've marked this task as not done yet:\n  " + Robert.tasks[taskIndex];
        Robert.outputMessage(text);
    }

    private static void exitChatbot() {
        Robert.outputMessage("Goodbye. Hope to see you again soon!");
    }

    private static void outputMessage(String message) {
        String output = Robert.formatOutput(message);
        System.out.println(output);
    }

    private static String formatOutput(String output) {
        final String HORIZONTAL_LINE = "\t____________________________________________________________\n";

        String[] outputLines = output.split("\n");
        for (int i = 0; i < outputLines.length; i++) {
            outputLines[i] = "\t" + outputLines[i] + "\n";
        }
        String indentedOutput = String.join("", outputLines);
        String formattedOutput = HORIZONTAL_LINE + indentedOutput + HORIZONTAL_LINE;

        return formattedOutput;
    }

    public static void main(String[] args) {
        Robert.greetUser();
        Robert.runChatbot();
        Robert.exitChatbot();
    }
}
