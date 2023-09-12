package duke;

import duke.tasks.TaskList;
import duke.tasks.Task;

/**
 * Describes the various possible interaction the bot has when you command
 * it to do a specific task
 */
public class Ui {
    public String name;

    public Ui(String name) {
        this.name = name;
    }

    /**
     * Describes what the bot say when you start it
     */
    public static String welcome() {
        String name = "DukeKing";
        String welcome = "Hello! I'm " + name + "\nWhat can I do for you?";
        System.out.println(welcome);
        return welcome;
    }

    /**
     * Describes what the bot say when you end the program
     */
    public String bye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
        return "Bye. Hope to see you again soon!";
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
    public String markTask(Task task) {
        String markingTask = "Nice! I've marked this task as done:";
        String output = String.format("%s\n%s", markingTask, task);
        printLine();
        System.out.println(output);
        return output;
    }

    /**
     * Describes what the bot say when you unmark a task successfully
     * 
     * @param task Take in the task to be unmarked
     */
    public String unMarkTask(Task task) {
        String markingTask = "Nice! I have unmarked the task :";
        String output = String.format("%s\n%s", markingTask, task);
        printLine();
        System.out.println(output);
        return output;
    }

    /**
     * Describes what the bot say when task is deleted successfully
     * 
     * @param list       Take in the taskList to delete the task from it
     * @param taskNumber Take in the task to be deleted from the taskList
     */
    public String deleteTask(TaskList tasks, int taskNumber) {
        assert taskNumber > 0 : "taskNumber should be more than 0";
        String deletingTask = "Noted. I've removed this task:";
        int taskInArray = tasks.size() - 1;
        assert taskNumber <= taskInArray : "task should be in the list";
        Task removedTask = tasks.deleteTask(taskNumber);
        String numberOfTask = "Now you have " + taskInArray + " tasks in the list.";
        String output = String.format("%s\n  %s\n%s", deletingTask, removedTask, numberOfTask);
        printLine();
        System.out.println(output);
        return output;
    }

    /**
     * Describes what the bot say when you successfully adds in a task
     * 
     * @param task Take in the task to be added
     * @param list Takes in the TaskList that the task is added to
     */
    public String addTask(Task task, TaskList tasks) {
        assert task != null : "task should not be null";
        assert tasks != null : "taskList should not be null";
        String addingTask = "Got it. I've added this task:";
        String numberOfTask = "Now you have " + tasks.size() + " tasks in the list.";
        String output = String.format("%s\n  %s\n%s", addingTask, task, numberOfTask);
        printLine();
        System.out.println(output);
        return output;
    }

    /**
     * Describes the case without a file the required to save the tasks
     */
    public String noFile() {
        printLine();
        System.out.println("OOPS!!! There is no file to load.");
        return "OOPS!!! There is no file to load.";
    }

    public String findTask(String keyword, TaskList tasks) {
        assert keyword != null : "keyword should not be null";
        assert tasks != null : "taskList should not be null";
        printLine();
        String output = "Here are the matching tasks in your list: \n";
        output += tasks.findTaskFromTaskList(keyword);
        System.out.println(output);
        return output;
    }

    public String helpList() {
        printLine();
        String output = "Here are the list of commands you can use: \n";
        output += "1. todo <task name> \n";
        output += "2. deadline <task name> /by <date> \n";
        output += "3. event <task name> /at <date> \n";
        output += "4. list \n";
        output += "5. mark <task number> \n";
        output += "6. unmark <task number> \n";
        output += "7. delete <task number> \n";
        output += "8. find <keyword> \n";
        output += "9. bye \n";
        output += "10. help \n";
        output += "To have examples for the different commands, please type help <command number>. \n";
        output += "Thank you for using DukeKing, We aim to improve everyone productivity! \n";
        System.out.println(output);
        return output;
    }
}
