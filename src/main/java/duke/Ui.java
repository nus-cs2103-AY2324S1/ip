package duke;

import java.util.Scanner;

import storage.TaskList;
import taskmanager.Deadline;
import taskmanager.Event;
import taskmanager.Task;
import taskmanager.ToDo;

/**
 * The Ui class handles user interaction and displays messages to the user.
 * It provides methods for input and output.
 */
public class Ui {
    private static final String NAME = "Yours"; // The name of the chatbot.
    private TaskList taskList; // The list of task.
    private String word; //The singular or plural form of "task".

    /**
     * Constructs an Ui instance with a reference to the task list.
     *
     * @param taskList The task list used for displaying tasks' information.
     */
    public Ui(TaskList taskList) {
        this.taskList = taskList;
        if (taskList.size() < 1) {
            this.word = "task";
        } else {
            this.word = "tasks";
        }
    }

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        String logo = "____    ____  ________   ___    ___   __________    _____\n"
                + "\\   \\  /   / |  ____  |  |  |   |  |  |  _____  |  / ____|\n"
                + " \\   \\/   /  | |    | |  |  |   |  |  |  |___|  |  | (___\n"
                + "  \\      /   | |    | |  |  |   |  |  |   ______|  \\ ___ \\\n"
                + "   |    |    | |    | |  |  |   |  |  |  \\  \\           | |\n"
                + "   |    |    | |____| |  |  |   |  |  |  | \\  \\     ____) |\n"
                + "   |____|    |________|  \\_________/  |__|   \\__\\  |_____/\n";

        System.out.printf("Hello! I'm %s%n", NAME);
        System.out.println(logo);
        System.out.println("__________________________________________________________________________________");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The user's input command as a string.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        return command;
    }

    /**
     * A prompt message when a new to-do task is added to the tasklist.
     *
     * @param newTodo The newly added ToDos task.
     */
    public void addedTodo(ToDo newTodo) {
        System.out.println("   __________________________________________________________________________________");
        System.out.println("   " + NAME + ": Help you added a new to-do.\n            " + newTodo.toString());
        System.out.println("          Now you have " + taskList.size() + String.format(" %s in the list.", word));
        System.out.println("   __________________________________________________________________________________");
    }

    /**
     * A prompt message when a new Deadlines task is added to the tasklist.
     *
     * @param newDeadLine The newly added Deadlines task.
     */
    public void addedDeadlines(Deadline newDeadLine) {
        System.out.println("   __________________________________________________________________________________");
        System.out.println("   " + NAME + ": Help you added a new deadline.\n           " + newDeadLine.toString());
        System.out.println("          Now you have " + taskList.size() + String.format(" %s in the list.", word));
        System.out.println("   __________________________________________________________________________________");
    }

    /**
     * A prompt message when a new Events task is added to the tasklist.
     *
     * @param newEvent The newly added Events task.
     */
    public void addedEvent(Event newEvent) {
        System.out.println("   __________________________________________________________________________________");
        System.out.println("   " + NAME + ": Help you added a new deadline.\n           " + newEvent.toString());
        System.out.println("          Now you have " + taskList.size() + String.format(" %s in the list.", word));
        System.out.println("   __________________________________________________________________________________");
    }

    /**
     * Displays all the task in the tasklist.
     */
    public void list() {
        System.out.println("   __________________________________________________________________________________");
        System.out.println("   " + NAME + ": Here are the tasks in your list.");
        taskList.display();
        System.out.println("   __________________________________________________________________________________");
    }

    /**
     * Displays all the task that match the keyword.
     *
     * @param matchedList The list of task that matched the keyword
     */
    public void find(TaskList matchedList) {

        if (matchedList.isEmpty()) {
            System.out.println("   __________________________________________________________________________________");
            System.out.println("   " + NAME + ": There is no task matched with the given keyword!");
            System.out.println("   __________________________________________________________________________________");
        } else {
            System.out.println("   __________________________________________________________________________________");
            System.out.println("   " + NAME + ": Here are the tasks that matches your keyword.");
            matchedList.display();
            System.out.println("   __________________________________________________________________________________");
        }
    }

    /**
     * A prompt message when a task is deleted.
     *
     * @param deleted The deleted task.
     */
    public void delete(Task deleted) {
        System.out.println("   __________________________________________________________________________________");
        System.out.println("   " + NAME + ": Helped you deleted this task\n           " + deleted.toString());
        System.out.println("          Now you have " + taskList.size() + String.format(" %s in the list.", word));
        System.out.println("   __________________________________________________________________________________");
    }

    /**
     * A prompt message when a task is marked as done.
     *
     * @param index The index of the task marked as done.
     */
    public void mark(int index) {
        if (taskList.get(index - 1).isDone()) {
            System.out.println("   __________________________________________________________________________________");
            System.out.println("   " + NAME + ": The task has been marked as done.");
            System.out.println("   __________________________________________________________________________________");
        } else {
            System.out.println("   __________________________________________________________________________________");
            System.out.println("   " + NAME + ": Well done! I've marked this task as done :");
            System.out.println("   " + taskList.get(index - 1).toString());
            System.out.println("   __________________________________________________________________________________");
        }
    }

    /**
     * A prompt message when a task is marked as not done.
     *
     * @param index The index of the task marked as not done.
     */
    public void unmark(int index) {
        if (!taskList.get(index - 1).isDone()) {
            System.out.println("   __________________________________________________________________________________");
            System.out.println("   " + NAME + ": The task has been unmarked");
            System.out.println("   __________________________________________________________________________________");
        } else {
            System.out.println("   __________________________________________________________________________________");
            System.out.println("   " + NAME + ": Alright, I've marked this task as not done yet");
            System.out.println("   " + taskList.get(index - 1).toString());
            System.out.println("   __________________________________________________________________________________");
        }
    }

    /**
     * A goodbye message when the application exits.
     */
    public void bye() {
        System.out.println("   __________________________________________________________________________________");
        System.out.println("   " + NAME + ": Bye. Hope to see you again soon!");
        System.out.println("   __________________________________________________________________________________");
    }

    /**
     * A prompt message when the user enters an empty command.
     */
    public void empty() {
        System.out.println("   __________________________________________________________________________________");
        System.out.println("   " + NAME + ": Please enter something!");
        System.out.println("   __________________________________________________________________________________");
    }

    /**
     * A prompt message when the user enters invalid command.
     */
    public void misc() {
        System.out.println("   __________________________________________________________________________________");
        System.out.println("   " + NAME + ": I don't really understand what you mean.");
        System.out.println("   __________________________________________________________________________________");
    }

    /**
     * Displays a message for handling an IndexOutOfBoundsException.
     */
    public void ioobExceptionMessage() {
        System.out.println("Please enter the correct task's index number.");
    }
}
