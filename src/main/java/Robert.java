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

        String userInput;
        while (isUnderExecution) {
            userInput = scanner.nextLine();
            String[] commands = userInput.split(" ");
            
            switch (commands[0]) {
            case "list":
                listTasks();
                break;

            case "bye":
                isUnderExecution = false;
                break;
            
            case "mark":
                int markingTaskIndex = Integer.parseInt(commands[1]) - 1;
                markTask(markingTaskIndex);
                break;

            case "unmark":
                int unmarkingTaskIndex = Integer.parseInt(commands[1]) - 1;
                unmarkTask(unmarkingTaskIndex);
                break;

            default:
                addTask(userInput);
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

    private static void addTask(String taskDescription) {
        Task newTask = new Task(taskDescription);
        Robert.tasks[Task.taskCount++] = newTask;
        String text = String.format("Alright! I have added this task to your list:\n%s", taskDescription);
        Robert.outputMessage(text);
    }

    private static void markTask(int taskIndex) {
        Robert.tasks[taskIndex].markAsDone();
        String text = "Nice! I've marked this task as done:\n" + Robert.tasks[taskIndex];
        Robert.outputMessage(text);
    }

    private static void unmarkTask(int taskIndex) {
        Robert.tasks[taskIndex].markAsUndone();
        String text = "Ok, I've marked this task as not done yet:\n" + Robert.tasks[taskIndex];
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
