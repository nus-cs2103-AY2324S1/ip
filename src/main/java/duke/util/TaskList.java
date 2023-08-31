package duke.util;

import duke.exception.EmptyDescriptionException;
import duke.task.Task;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TaskList {
    protected Storage storage;
    protected ArrayList<Task> listOfTasks;
    protected static String HORIZONTAL_LINE = "    ____________________________________________________________"; //60 underscores.


    public TaskList(Storage storage) {
        this.storage = storage;
        this.listOfTasks = storage.listOfTasks;
    }

    protected void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * List out all tasks.
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
     * Mark a given Task as done.
     * @param taskIndex the index of the Task to be marked as done.
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
     * Function to mark a given Task as NOT done.
     * @param taskIndex the index of the Task to be marked as not done yet.
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

}
