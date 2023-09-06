package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the part of the chatbot that deals with interactions with users.
 */
public class Ui {

    /**
     * Constructor to create an Ui object.
     * Starts the chatbot.
     */
    public Ui() {
    }

    /**
     * Represents the output after successfully adding a task to the list.
     * 
     * @param task The task that got added into the task list.
     * @param size The changed size of the task list.
     */
    public void addToListSuccess(Task task, int size) {
        System.out.println("Got it. I've added this task:\n");
        System.out.println("\t" + task + "\n");
        System.out.println("Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Represents the output after successfully deleting a task from the list.
     * 
     * @param task The task that got deleted.
     * @param size The changed size of the task list.
     */
    public void deleteFromListSuccess(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n");
        System.out.println("\t" + task + "\n");
        System.out.println("Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Represents the output that outputs the list stored.
     * 
     * @param taskList Task list saved within the chatbot.
     */
    public void printList(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            String formattedOutput = String.format("%d. %s\n", index, taskList.get(i));
            System.out.println(formattedOutput);
        }
    }

    public void getGreeting() {
        String greeting = "Hello I'm Rion.\n What can I do for you?";
        System.out.println(greeting);
    }

    /**
     * Represents the output that is printed when exiting the chatbot.
     */
    public void printExitMessage() {
        String exitMsg = "Bye. Hope to see you again soon!";
        System.out.println(exitMsg);
    }

    /**
     * Represents the output for every command.
     * 
     * @param message The command message.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
}
