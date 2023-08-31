package duke;

import duke.tasks.TaskList;
import duke.tasks.Task;

/**
 * Describes the various possible interaction the bot has when you command
 * it to do a specific task
 */
public class UI {
    public String name;

    public UI(String name) {
        this.name = name;
    }

    /**
     * Describes what the bot say when you start it
     */
    public void welcome() {
        String name = "DukeKing";
        String welcome = "Hello! I'm " + name + "\nWhat can I do for you?";
        printLine();
        System.out.println(welcome);
        printLine();
    }

    /**
     * Describes what the bot say when you end the program
     */
    public void bye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Prints a line to segregate between user and bot replies
     */
    public void printLine() {
        System.out.println("_".repeat(40));
    }

    /**
     * Describes what the bot say when you mark a task successfully
     * 
     * @param task Take in the task to be marked
     */
    public void markTask(Task task) {
        String markingTask = "Nice! I've marked this task as done:";
        String output = String.format("%s\n%s", markingTask, task);
        printLine();
        System.out.println(output);
    }

    /**
     * Describes what the bot say when you unmark a task successfully
     * 
     * @param task Take in the task to be unmarked
     */
    public void unMarkTask(Task task) {
        String markingTask = "Nice! I have unmarked the task :";
        String output = String.format("%s\n%s", markingTask, task);
        printLine();
        System.out.println(output);
    }

    /**
     * Describes what the bot say when task is deleted successfully
     * 
     * @param list       Take in the taskList to delete the task from it
     * @param taskNumber Take in the task to be deleted from the taskList
     */
    public void deleteTask(TaskList tasks, int taskNumber) {
        String deletingTask = "Noted. I've removed this task:";
        int taskInArray = tasks.size() - 1;
        Task removedTask = tasks.deleteTask(taskNumber);
        String numberOfTask = "Now you have " + taskInArray + " tasks in the list.";
        String output = String.format("%s\n  %s\n%s", deletingTask, removedTask, numberOfTask);
        printLine();
        System.out.println(output);
    }

    /**
     * Describes what the bot say when you successfully adds in a task
     * 
     * @param task Take in the task to be added
     * @param list Takes in the TaskList that the task is added to
     */
    public void addTask(Task task, TaskList tasks) {
        String addingTask = "Got it. I've added this task:";
        String numberOfTask = "Now you have " + tasks.size() + " tasks in the list.";
        String output = String.format("%s\n  %s\n%s", addingTask, task, numberOfTask);
        printLine();
        System.out.println(output);
    }

    /**
     * Describes the case without a file the required to save the tasks
     */
    public void noFile() {
        printLine();
        System.out.println("OOPS!!! There is no file to load.");
    }

    public void findTask() {
        printLine();
        System.out.println("Here are the matching tasks in your list:");
    }
}
