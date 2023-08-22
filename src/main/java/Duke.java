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

    // Use inheritance
    private class ToDo extends Task {
        public ToDo (String taskName) {
            super(taskName);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private class Deadline extends Task {
        public Deadline (String taskName) {
            super(taskName);
        }

        @Override
        public String toString() {
            return "[D]" + super.toString();
        }
    }

    private class Event extends Task {
        public Event (String taskName) {
            super(taskName);
        }

        @Override
        public String toString() {
            return "[E]" + super.toString();
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

    private void addToTaskList(Task task) {
        if (taskListSize < taskList.length) {
            taskList[taskListSize] = task;
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
        // there are only 3 types of tasks.
        // need override toString() method for each task type.
        } else if (inputString.startsWith("todo")) {
            // this excludes the space after todo as well
            String taskName = inputString.substring(5);
            Task newTask = new ToDo(taskName);
            addTaskOutputText(newTask);
        } else if (inputString.startsWith("deadline")) {
            // stop before /by
            String taskName = inputString.substring(9, inputString.indexOf("/by") - 1);
            // get day
            String deadline = inputString.substring(inputString.indexOf("/by") + 4);
            Task newTask = new Deadline(taskName + " (by: " + deadline + ")");
            addTaskOutputText(newTask);
        } else if (inputString.startsWith("event")) {
            String taskName = inputString.substring(6, inputString.indexOf("/from") - 1);
            String from = inputString.substring(inputString.indexOf("/from") + 6, inputString.indexOf("/to") - 1);
            String to = inputString.substring(inputString.indexOf("/to") + 4);
            Task newTask = new Event(taskName + " (from: " + from + " to: " + to + ")");
            addTaskOutputText(newTask);
        } else {
            // System.out.println("\tadded: " + inputString);
            // addToTaskList(inputString);
            System.out.println("\tCommand not found. Try again using either mark, unmark, todo, deadline, event, or bye.");
        }
        System.out.println(DIVIDER);
    }

    private void addTaskOutputText(Task newTask) {
        addToTaskList(newTask);
        System.out.println("\tGot it. I've added this task:");
        // can use -1 because we just added it
        System.out.println("\t\t" + taskList[taskListSize - 1]);
        String taskWord = taskListSize == 1 ? "task" : "tasks";
        System.out.println("\tNow you have " + taskListSize + " " + taskWord + " in your list.");
    }

    private void sendFarewell() {
        System.out.println(DIVIDER);
        System.out.println(FAREWELL);
        System.out.println(DIVIDER);
    }
}
