package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

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
     * @param list       Take in the taskList to delete the task from it
     * @param taskNumber Take in the task to be deleted from the taskList
     */
    public String deleteTask(TaskList tasks, int taskNumber) {
        assert taskNumber > 0 : "taskNumber should be more than 0";
        String deletingTask = "Pika Pika!! I've removed this task:";
        int taskInArray = tasks.size() - 1;
        assert taskNumber <= taskInArray : "task should be in the list, PIKA PIKA!!!!";
        Task removedTask = tasks.deleteTask(taskNumber);
        String numberOfTask = "Now you have " + taskInArray + " tasks in the list. Pika Pika!!";
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
        System.out.println("OOPS!!! There is no file to load. Pika Pika!!");
        return "OOPS!!! There is no file to load. Pika Pika!!";
    }

    public String findTask(String keyword, TaskList tasks) {
        assert keyword != null : "keyword should not be null";
        assert tasks != null : "taskList should not be null";
        printLine();
        String output = "Pika Pika!! Here are the matching tasks in your list: \n";
        output += tasks.findTaskFromTaskList(keyword);
        System.out.println(output);
        return output;
    }

    public String helpList() throws FileNotFoundException {
        printLine();
        String output = "";
        File file = new File("./HelpList.txt");
        Scanner sc = new Scanner(new FileReader(file));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println(line);
            output += line + "\n";
        }
        return output;
    }

    public String detailedHelp(int helpNumber) {
        String output = "";
        String correctExample = "";
        switch (helpNumber) {
            case 1:
                output += "todo <task name> : add a todo task to the list\n";
                correctExample += "Example: todo read book\n";
                output += correctExample;
                break;
            case 2:
                output += "deadline <task name> /by <end time> : add a deadline task to the list\n";
                correctExample += "Example: deadline return book /by 2pm\n";
                output += correctExample;
                break;
            case 3:
                output += "event <task name> /from <start time> /to <end time> : add an event task to the list\n";
                correctExample += "Example: event project meeting /from 2pm /to 4pm\n";
                output += correctExample;
                break;
            case 4:
                output += "list : list out all the tasks in the list\n";
                correctExample += "Example: list\n";
                output += correctExample;
                break;
            case 5:
                output += "mark <task number> : mark the task as done\n";
                correctExample += "Example: mark 1\n";
                output += correctExample;
                break;
            case 6:
                output += "unmark <task number> : unmark the task as done\n";
                correctExample += "Example: unmark 1\n";
                output += correctExample;
                break;
            case 7:
                output += "delete <task number> : delete the task from the list\n";
                correctExample += "Example: delete 1\n";
                output += correctExample;
                break;
            case 8:
                output += "find <keyword> : find the task with the keyword\n";
                correctExample += "Example: find book\n";
                output += correctExample;
                break;
            case 9:
                output += "bye : end the program\n";
                correctExample += "Correct: bye\n";
                output += correctExample;
                break;
            default:
                output += "Please enter a valid number from 1 to 9";
                break;
        }
        return output;
    }
}
