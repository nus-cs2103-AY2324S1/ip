package duke;

import duke.exception.UnknownCommandException;
import duke.task.Task;
import duke.task.TaskList;

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
        String name = "PikaKing";
        String welcome = "Hello! I'm " + name + "\nWhat can I do for you?\n";
        welcome += "For new users, type help to see the list of commands you can use. Pika Pika!!";
        System.out.println(welcome);
        return welcome;
    }

    /**
     * Describes what the bot say when you end the program
     */
    public String bye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon! Pika Pika!!");
        printLine();
        return "Bye. Hope to see you again soon! Pika Pika!!";
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
        String markingTask = "Pika Pika!! I've marked this task as done:";
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
        String markingTask = "Pika Pika!! I have unmarked the task :";
        String output = String.format("%s\n%s", markingTask, task);
        printLine();
        System.out.println(output);
        return output;
    }

    /**
     * Describes what the bot say when task is deleted successfully
     * 
     * @param tasks       Take in the taskList to delete the task from it
     * @param taskNumber Take in the task to be deleted from the taskList
     */
    public String deleteTask(TaskList tasks, int taskNumber) throws UnknownCommandException {
        String deletingTask = "Pika Pika!! I've removed this task:";
        int tasksInArray = tasks.size();
        if (taskNumber > tasksInArray || taskNumber < 0) {
            throw new UnknownCommandException("task should be in the list");
        }
        Task removedTask = tasks.deleteTask(taskNumber);
        tasksInArray -= 1;
        String numberOfTask = "Now you have " + tasksInArray + " tasks in the list. Pika Pika!!";
        String output = String.format("%s\n  %s\n%s", deletingTask, removedTask, numberOfTask);
        printLine();
        System.out.println(output);
        return output;
    }

    /**
     * Describes what the bot say when you successfully adds in a task
     * 
     * @param task Take in the task to be added
     * @param tasks Takes in the TaskList that the task is added to
     */
    public String addTask(Task task, TaskList tasks) {
        assert task != null : "task should not be null";
        assert tasks != null : "taskList should not be null";
        String addingTask = "Pika Pika!! I've added this task:";
        String numberOfTask = "Now you have " + tasks.size() + " tasks in the list. Pika Pika!!";
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
        System.out.println("PIKA PIKA!!! There is no file to load.");
        return "PIKA PIKA!!! There is no file to load.";
    }

    /**
     * Describes the case when the file is not found
     * 
     * @param keyword
     * @param tasks
     * @return
     */
    public String findTask(String keyword, TaskList tasks) {
        assert keyword != null : "keyword should not be null";
        assert tasks != null : "taskList should not be null";
        printLine();
        String output = "Pika Pika!! Here are the matching tasks in your list: \n";
        output += tasks.findTaskFromTaskList(keyword);
        System.out.println(output);
        return output;
    }

    /**
     * Prints out the help method which is the list of commands you can use
     * 
     * @return the helplist
     */
    public String helpList() {
        printLine();
        String output = "";
        output += "Pika Pika!! Here are the list of commands you can use: \n";
        output += "1. todo <task name>\n";
        output += "2. deadline <task name> /by <date>\n";
        output += "3. event <task name> /from <start time> /to <end time>\n";
        output += "4. list\n";
        output += "5. mark <task number>\n";
        output += "6. unmark <task number>\n";
        output += "7. delete <task number>\n";
        output += "8. find <keyword>\n";
        output += "9. bye\n";
        output += "For more details on each command, type help <number> (eg. help 1) " +
                "for example on todo command\n";
        output += "Pika Pika!! We aim to improve everyone productivity!!";
        System.out.println(output);
        return output;
    }

    public String detailedHelp(int helpNumber) {
        String output = "";
        switch (helpNumber) {
        case 1:
            output += Ui.todoExample();
            break;
        case 2:
            output += Ui.deadlineExample();
            break;
        case 3:
            output += Ui.eventExample();
            break;
        case 4:
            output += Ui.listExample();
            break;
        case 5:
            output += Ui.markExample();
            break;
        case 6:
            output += Ui.unMarkExample();
            break;
        case 7:
            output += Ui.deleteExample();
            break;
        case 8:
            output += Ui.findExample();
            break;
        case 9:
            output += Ui.byeExample();
            break;
        default:
            output += "Please enter a valid help number from 1 to 9";
            break;
        }
        return output;
    }

    public static String todoExample() {
        String output = "todo <task name> : add a todo task to the list\n";
        String correctExample = "Example: todo read book\n";
        output += correctExample;
        return output;
    }

    public static String deadlineExample() {
        String output = "deadline <task name> /by <end time> : add a deadline task to the list\n";
        String correctExample = "Example: deadline return book /by 2pm\n";
        output += correctExample;
        return output;
    }

    public static String eventExample() {
        String output = "event <task name> /from <start time> /to <end time> : add an event task to the list\n";
        String correctExample = "Example: event project meeting /from 2pm /to 4pm\n";
        output += correctExample;
        return output;
    }

    public static String listExample() {
        String output = "list : list out all the tasks in the list\n";
        String correctExample = "Example: list\n";
        output += correctExample;
        return output;
    }

    public static String markExample() {
        String output = "mark <task number> : mark the task as done\n";
        String correctExample = "Example: mark 1\n";
        output += correctExample;
        return output;
    }

    public static String unMarkExample() {
        String output = "unmark <task number> : unmark the task as done\n";
        String correctExample = "Example: unmark 1\n";
        output += correctExample;
        return output;
    }

    public static String deleteExample() {
        String output = "delete <task number> : delete the task from the list\n";
        String correctExample = "Example: delete 1\n";
        output += correctExample;
        return output;
    }

    public static String findExample() {
        String output = "find <keyword> : find the task with the keyword\n";
        String correctExample = "Example: find book\n";
        output += correctExample;
        return output;
    }

    public static String byeExample() {
        String output = "bye : end the program\n";
        String correctExample = "Correct: bye\n";
        output += correctExample;
        return output;
    }
}
