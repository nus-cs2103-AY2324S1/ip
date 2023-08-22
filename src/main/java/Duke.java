import java.util.Scanner;

public class Duke {
    private final String DIVIDER = "\t____________________________________________________________";
    private final String LOGO = 
                            "\t░█████╗░██╗░░██╗░█████╗░████████╗████████╗██╗░░░██╗\n" +
                            "\t██╔══██╗██║░░██║██╔══██╗╚══██╔══╝╚══██╔══╝╚██╗░██╔╝\n" +
                            "\t██║░░╚═╝███████║███████║░░░██║░░░░░░██║░░░░╚████╔╝░\n" +
                            "\t██║░░██╗██╔══██║██╔══██║░░░██║░░░░░░██║░░░░░╚██╔╝░░\n" +
                            "\t╚█████╔╝██║░░██║██║░░██║░░░██║░░░░░░██║░░░░░░██║░░░\n" +
                            "\t░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░░░░╚═╝░░░░░░╚═╝░░░\n";
    private final String GREETING = "\tHello! I'm Chatty.\n\tWhat can I do for you?";
    private final String FAREWELL = "\tBye. Have \"fun\" in school!";
    private Task[] taskList = new Task[100];
    // private String[] taskList = new String[100];
    // private boolean[] taskListCompletion = new boolean[100];
    private int taskListSize = 0;

    private class Task{
        private String taskName;
        private boolean isDone;

        public Task(String taskName) {
            this.taskName = taskName;
            this.isDone = false;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public String getTaskName() {
            return this.taskName;
        }

        public boolean getIsDone() {
            return this.isDone;
        }

        public void unmarkAsDone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[" + (this.isDone ? "X" : " ") + "] " + this.taskName;
        }
    }

    public static void main(String[] args) {
        // get new duke instance
        Duke duke = new Duke();

        // introduction
        System.out.println("\n\tWelcome to Chatty.\n" + duke.LOGO);
        duke.sendGreeting();

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println();
            String input = sc.nextLine().trim();

            while (!input.equals("bye")) {
                duke.handleTextInput(input);
                System.out.println();
                input = sc.nextLine().trim();
            }
        }
        // say bye
        duke.sendFarewell();
    }

    private void addToTextList(String text) {
        if (taskListSize < taskList.length) {
            taskList[taskListSize] = new Task(text);
            taskListSize++;
        } else {
            System.out.println("Error: List is full.");
        }
    }

    private void sendGreeting() {
        System.out.println(DIVIDER);
        System.out.println(GREETING);
        System.out.println(DIVIDER);
    }

    private void handleTextInput(String inputString) {
        System.out.println(DIVIDER);
        // handle key logic here.
        if (inputString.equals("list")) {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < taskListSize; i++) {
                System.out.println("\t" + (i + 1) + ". " + taskList[i]);
            }
        } else if (inputString.startsWith("mark")) {
            int taskIndex = Integer.parseInt(inputString.split(" ")[1]) - 1;
            taskList[taskIndex].markAsDone();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t\t" + taskList[taskIndex]);
        } else if (inputString.startsWith("unmark")) {
            int taskIndex = Integer.parseInt(inputString.split(" ")[1]) - 1;
            taskList[taskIndex].unmarkAsDone();
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t\t" + taskList[taskIndex]);
        } else {
            System.out.println("\tadded: " + inputString);
            addToTextList(inputString);
        }
        System.out.println(DIVIDER);
    }

    private void sendFarewell() {
        System.out.println(DIVIDER);
        System.out.println(FAREWELL);
        System.out.println(DIVIDER);
    }
}
