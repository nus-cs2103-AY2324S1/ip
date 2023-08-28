import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private final String myName = "Quack-NKN";
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Task> taskList = new ArrayList<>();

    public Duke() {
        interact();
    }

    /**
     * Invoked at the start of the interaction, to greet the user.
     */
    private void start() {
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.print("Hello from ");
        System.out.println(this.myName);
        System.out.println("What can I do for you?");
        System.out.println(Duke.HORIZONTAL_LINE);
    }

    /**
     * Invoked at the end of the programme, to leave an exit message to user.
     */
    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Duke.HORIZONTAL_LINE);
    }

    /**
     * Main programme to allow user to input and respond accordingly.
     * Available commands:
     * - bye: to exit the programme
     * - list: to list out the current task list
     * - mark {number}: to mark the task with the corresponding index in the list as done
     * - unmark {number}: to mark the task with the corresponding index in the list as not done
     * - todo {taskname}: to add a new task as a to-do item (no deadline or time)
     * - event {taskname} /from {starttime} /to {endtime}: to add a new task as an event (with start time and end time)
     * - deadline {taskname} /by {time}: to add a new task as a deadline (with deadline time)
     * Error handling is done in the individual functions, not here.
     */
    private void interact() {
        this.start();
        label:
        while (true) {
            // receive input
            System.out.print("In: ");
            String command = this.scanner.nextLine();
            System.out.println(Duke.HORIZONTAL_LINE);
            String[] commandArgs = command.split(" ", 2);

            // exit
            switch (commandArgs[0]) {
                case "bye":
                    break label;


                // show list
                case "list":
                    this.showList();
                    break;

                // mark as done
                case "mark":
                    this.markTaskAsDone(commandArgs);
                    break;

                // mark as not done
                case "unmark":
                    this.markTaskAsNotDone(commandArgs);
                    break;

                // add to-do
                case "todo":
                    this.addToDoToList(commandArgs);
                    break;

                // add event
                case "event":
                    this.addEventToList(commandArgs);
                    break;

                // add deadline
                case "deadline":
                    this.addDeadlineToList(commandArgs);
                    break;

                // delete task
                case "delete":
                    this.deleteTask(commandArgs);
                    break;

                // anything else
                default:
                    this.echo(command);
                    break;
            }

            System.out.println(Duke.HORIZONTAL_LINE);
        }
        this.exit();
    }

    /**
     * Perform input checking and add the to-do task to task list.
     * @param commandArgs the arguments provided in the command
     */
    private void addToDoToList(String[] commandArgs) {
        // number of arguments
        if (commandArgs.length != 2) {
            System.out.println("Quack, you did not provide me with the to-do task!");
            return;
        }
        // no empty to-do task
        if (commandArgs[1].equals("")) {
            System.out.println("Quack, no empty to-do please!");
            return;
        }

        Task newTask = new ToDo(commandArgs[1]);
        this.taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        showTaskCount();
    }

    /**
     * Perform input checking and add the event to the task list.
     * @param commandArgs the arguments provided in the command
     */
    private void addEventToList(String[] commandArgs) {
        // number of arguments
        if (commandArgs.length != 2) {
            System.out.println("Quack, you did not provide me with the event!");
            return;
        }

        // /from keyword
        String[] separateByFrom = commandArgs[1].split("/from", 2);
        // no empty event
        if (separateByFrom[0].equals("")) {
            System.out.println("Quack, no empty event please!");
            return;
        }
        // /from keyword must exist
        if (separateByFrom.length != 2) {
            System.out.println("Quack, keyword '/from' not found. " +
                    "It must be present for me to mark the start time!");
            return;
        }

        // /to keyword
        String[] separateByTo = separateByFrom[1].split("/to", 2);
        // no empty start time
        if (separateByTo[0].equals("")) {
            System.out.println("Quack, no empty start time please!");
            return;
        }
        // /to keyword must exist
        if (separateByTo.length != 2) {
            System.out.println("Quack, keyword '/to' not found. " +
                    "It must be present after the '/from' keyword for me to mark the end time!");
            return;
        }
        // no empty end time
        if (separateByTo[1].equals("")) {
            System.out.println("Quack, no empty end time please!");
            return;
        }

        Task newTask = new Event(separateByFrom[0], separateByTo[0], separateByTo[1]);
        this.taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        this.showTaskCount();
    }

    /**
     * Perform input checking and add a new task as deadline to the current list of tasks.
     * @param commandArgs the list of arguments in the command
     */
    private void addDeadlineToList(String[] commandArgs) {
        // number of arguments
        if (commandArgs.length != 2) {
            System.out.println("Quack, you did not provide me with the deadline!");
            return;
        }

        String[] separateByBy = commandArgs[1].split("/by", 2);
        // /by keyword must exist
        if (separateByBy.length != 2) {
            System.out.println("Quack, keyword '/by' not found." +
                    "It must be present for me to mark the deadline time!");
            return;
        }
        // no empty deadline
        if (separateByBy[0].equals("")) {
            System.out.println("Quack, no empty deadline task please!");
            return;
        }
        // no empty end time
        if (separateByBy[1].equals("")) {
            System.out.println("Quack, no empty deadline time please!");
            return;
        }

        Task newTask = new Deadline(separateByBy[0], separateByBy[1]);
        this.taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        this.showTaskCount();
    }

    /**
     * Notify user of how many tasks are currently in the task list.
     */
    private void showTaskCount() {
        System.out.println("Now you have " + this.taskList.size() + " in the list.");
    }

    /**
     * Show the current list of tasks to user.
     */
    private void showList() {
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + this.taskList.get(i));
        }
    }

    /**
     * Perform input checking and mark a task with corresponding index as done.
     * @param commandArgs the list of arguments in the command
     */
    private void markTaskAsDone(String[] commandArgs) {
        int index = this.getTaskIndexFromCommand(commandArgs);
        if (index == -1) {
            return;
        }
        this.taskList.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.taskList.get(index));
    }

    /**
     * Perform input checking and mark a task with corresponding index as not done.
     * @param commandArgs the list of arguments in the command
     */
    private void markTaskAsNotDone(String[] commandArgs) {
        int index = this.getTaskIndexFromCommand(commandArgs);
        if (index == -1) {
            return;
        }
        this.taskList.get(index).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.taskList.get(index));
    }

    /**
     * Perform input checking and delete the task if input is valid, given a command.
     * @param commandArgs the list of arguments in the command.
     */
    private void deleteTask(String[] commandArgs) {
        int index = this.getTaskIndexFromCommand(commandArgs);
        if (index == -1) {
            return;
        }
        Task taskToDelete = this.taskList.get(index);
        this.taskList.remove(index);
        System.out.println("Noted, I've removed this task:");
        System.out.println(taskToDelete);
        this.showTaskCount();
    }

    /**
     * Get task index from command (second element) and validate input.
     * If command is invalid, return the number. Else, return -1.
     * Print out the relevant error message, if there is error.
     * Else, return
     * @param commandArgs the list of command arguments separated by space
     * @return the task index, or -1 if input is invalid
     */
    private int getTaskIndexFromCommand(String[] commandArgs) {
        // check for number of arguments
        if (commandArgs.length != 2) {
            System.out.println("Quack, you have provided wrong number of arguments!");
            return -1;
        }

        // check if second argument is positive integer
        String indexString = commandArgs[1];
        if (indexString.matches("0+") || !indexString.matches("\\d+")) {
            System.out.println("Quack, you need to provide a positive integer!");
            return -1;
        }

        // check if index is in range
        int index = Integer.parseInt(indexString);
        if (index > this.taskList.size()) {
            System.out.println("Quack, the task number you provide does not exist!");
            return -1;
        }

        return index - 1;
    }

    /**
     * Echo command back to the user.
     * @param command the command from the user
     */
    private void echo(String command) {
        if (command.equals("quack")) {
            System.out.println("Quack quack quack");
        } else {
            System.out.println("Quack, what do you mean when you say " + command);
        }
    }

    public static void main(String[] args) {
        new Duke();
    }
}
