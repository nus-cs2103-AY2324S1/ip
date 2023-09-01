package duke.util;

import duke.exception.EmptyDescriptionException;
import duke.task.Task;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Represents a Task List which is just a list of Tasks.
 *
 * <p>CS2103T AY23/24 Semester 1
 * Individual Project
 * SeeWhyAre Bot
 * 31 Aug 2023
 *
 * @author Freddy Chen You Ren
 */
public class TaskList {
    protected Storage storage;
    protected ArrayList<Task> listOfTasks;
    protected static String HORIZONTAL_LINE = "    ____________________________________________________________"; //60 underscores.

    /**
     * Constructs a TaskList with the given Storage.
     *
     * @param storage the storage from which the list of tasks is to be created.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.listOfTasks = storage.listOfTasks;
    }

    protected void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * List out all tasks available for the user.
     */
    protected void listAllTasks() {
        if (listOfTasks.isEmpty()) {
            printHorizontalLine();
            System.out.println("    No tasks for now!");
            printHorizontalLine();
        } else {
            printHorizontalLine();
            System.out.println("    Your current task list:");
            for (int i = 0; i < listOfTasks.size(); i++) {
                System.out.printf("     %d.%s\n", i + 1, listOfTasks.get(i).toString());
            }
            printHorizontalLine();
        }
    }

    /**
     * Delete a Task when given valid task index.
     */
    public void deleteTask(String deleteInput)
            throws EmptyDescriptionException, IOException {
        String[] words = deleteInput.split("\\s+"); // Split input by space, put into array
        //Check for valid length
        if (words.length <= 1) {
            throw new EmptyDescriptionException("Please provide the ask index to be deleted.");
        }

        //Try parsing into integer to get deleteIndex
        try {
            int deleteIndex = Integer.parseInt(words[1]) - 1; // Potential Error cannot parse to integer
            printHorizontalLine();

            if (deleteIndex >= 0 && deleteIndex < listOfTasks.size()) {
                Task removedTask = listOfTasks.remove(deleteIndex); //Actual ask can be todo, deadline, or event
                System.out.println("     Noted. I've removed this Task:");
                System.out.printf("       %s\n", removedTask.toString());
                System.out.printf("     Now you have %d task(s) in the list.\n", listOfTasks.size());
            } else {
                System.out.println("     OOPS!!! The task index is invalid.");
                System.out.printf("     You currently have %d task(s).\n", listOfTasks.size());
            }
            printHorizontalLine();

        } catch (NumberFormatException e) {
            printHorizontalLine();
            System.out.println("     OOPS!!! Please enter the index after 'delete' command.");
            System.out.println("     For example: delete 5");
            System.out.println("     This will remove Task 5 from your Task List, assuming you have at least 5 tasks.");
            printHorizontalLine();
        }
        storage.clearAllData();
        storage.updateData();
    }

    /**
     * Marks a given Task as done.
     * Updates the list of tasks in the storage file.
     * Prints out an error message if index of the task given is out of range or invalid.
     *
     * @param taskIndex the index of the Task to be marked as done.
     * @throws IOException if there is an issue with updating the data in the storage file.
     */
    protected void markTask(int taskIndex) throws IOException {
        printHorizontalLine();
        if (taskIndex < 0 || taskIndex >= listOfTasks.size()) {
            System.out.printf("     Invalid Index of Task. You currently have %d Task(s)\n", listOfTasks.size());
        } else {
            Task task = listOfTasks.get(taskIndex);
            task.markAsDone();
            System.out.println("     Nice! I've marked this Task as done:");
            System.out.printf("       [%s] %s\n", task.getStatusIcon(), task.description);
        }
        printHorizontalLine();
        storage.clearAllData();
        storage.updateData();
    }

    /**
     * Marks a given Task as NOT done.
     * Updates the list of tasks in the storage file.
     * Prints out an error message if index of the task given is out of range or invalid.
     *
     * @param taskIndex the index of the Task to be marked as not done yet.
     * @throws IOException if there is an issue with updating the data in the storage file.
     */
    protected void unmarkTask(int taskIndex) throws IOException {
        printHorizontalLine();
        if (taskIndex < 0 || taskIndex >= listOfTasks.size()) {
            System.out.printf("    Invalid Index of task. You currently have %d Task(s)\n", listOfTasks.size());
        } else {
            Task task = listOfTasks.get(taskIndex);
            task.markAsNotDone();
            System.out.println("     OK, I've marked this Task as not done yet:");
            System.out.printf("       [%s] %s\n", task.getStatusIcon(), task.description);
        }
        printHorizontalLine();
        storage.clearAllData();
        storage.updateData();
    }

    /**
     * Checks if the line representing task details saved in memory is valid.
     * This is used when we are loading the list of tasks from user's past data.
     *
     * @param line The String representing one task that we are checking.
     * @return True if this line is a valid Task, False otherwise.
     */
    protected static boolean isValidTaskLine(String line) {
        String[] tokens = line.split("\\|");

        if (tokens.length >= 3 && tokens.length <= 5) { // Valid number of segments: 3-5 (Todo-Event)
            String taskType = tokens[0].trim();
            String completionStatus = tokens[1].trim();
            String description = tokens[2].trim();

            return taskType.matches("[TDE]") && completionStatus.matches("[01]") && !description.isEmpty(); // Line matches expected format
        }

        return false; // Line is not valid
    }

    /**
     * Checks if the date provided is a valid date and in the correct date format.
     * This is used during task creation and task loading from storage file.
     *
     * @param testDate The Date from a task that we are checking
     * @return True if this Date is a valid Date with the correct date Format "yyyy-MM-dd", False otherwise.
     */
    public static boolean isValidDate(String testDate) {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        simpleDate.setLenient(false);
        try {
            simpleDate.parse(testDate);
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    // Find task
    // Get keyword from user. Use StringBuilder: use contains(), for loop all available tasks.
    protected void findTask(String matchingKeyword) {
        try {
            printHorizontalLine();
            if (listOfTasks.isEmpty()) {
                System.out.println("\t You currently have no tasks so I can't find any matching tasks :/.");
                return;
            }

            int taskCount = 0;
            StringBuilder matchingTasks = new StringBuilder(String.format(
                    "\t Here are your tasks that contains '%s':", matchingKeyword));
            for (int i = 0; i < listOfTasks.size(); i++) {
                if (listOfTasks.get(i).getDescription().contains(matchingKeyword)) {
                    matchingTasks.append("\n\t ").append(listOfTasks.get(i));
                    taskCount++;
                }
            }

            //Output matching tasks
            if (taskCount > 0) {
                String output = matchingTasks.toString();
                System.out.println(output);
            } else {
                String output = String.format("\t Hm there are no matching tasks with '%s'. " +
                        "Try with another keyword.", matchingKeyword);
                System.out.println(output);
            }
        } finally {
            printHorizontalLine();
        }

    }

}
