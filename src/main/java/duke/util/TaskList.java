package duke.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

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
    //60 underscores.
    protected static String HORIZONTAL_LINE = "    ____________________________________________________________";
    protected Storage storage;
    protected ArrayList<Task> listOfTasks;

    /**
     * Instantiates a TaskList with the given storage.
     *
     * @param storage The storage from which the list of tasks is to be created.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.listOfTasks = Storage.listOfTasks;
    }

    protected void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Lists out all tasks available for the user.
     *
     * @return The string representation of the list of tasks.
     */
    protected String listAllTasks() {
        if (listOfTasks.isEmpty()) {
            System.out.println("    No tasks for now!");
            return "No tasks for now!";
        } else {
            StringBuilder message = new StringBuilder();
            message.append(String.format("You have %d tasks now. Here is your task list:\n", listOfTasks.size()));

            System.out.printf("     You have %d tasks now. Here is your task list:\n", listOfTasks.size());
            for (int i = 0; i < listOfTasks.size(); i++) {
                System.out.printf("     %d.%s\n", i + 1, listOfTasks.get(i).toString());
                message.append("\t").append(i + 1).append(".").append(listOfTasks.get(i)).append("\n");
            }
            return message.toString();
        }
    }

    /**
     * Deletes a task when given valid task index.
     *
     * @param deleteInput The input representing the delete command.
     *                    It should contain task index to be deleted.
     * @return The message to indicate which task has been deleted after successful deletion.
     * @throws EmptyDescriptionException If there is no task index provided.
     * @throws IOException If there is an error updating the data file.
     */
    public String deleteTask(String deleteInput) throws EmptyDescriptionException, IOException {
        String[] words = deleteInput.split("\\s+"); // Split input by space, put into array
        StringBuilder message = new StringBuilder();

        //Check for valid length
        if (words.length <= 1) {
            throw new EmptyDescriptionException("Please provide the task index to be deleted.");
        }

        try {
            int deleteIndex = Integer.parseInt(words[1]) - 1;
            if (deleteIndex >= 0 && deleteIndex < listOfTasks.size()) {
                Task removedTask = listOfTasks.remove(deleteIndex);
                storage.clearAllData();
                storage.updateData();
                assert !listOfTasks.contains(removedTask) : "Task should have been removed!";
                message.append("Noted. I've removed this task:\n");
                message.append(String.format("%s\n", removedTask.toString()));
                message.append(String.format("Now you have %d task(s) in the list.\n", listOfTasks.size()));
            } else {
                message.append("OOPS!!! The task index is invalid.\n");
                message.append(String.format("You currently have %d task(s).\n", listOfTasks.size()));
            }
            return message.toString();
        } catch (NumberFormatException e) {
            message.append("OOPS!!! Please enter the index after 'delete' command.\nFor example: delete 5");
            message.append("This will remove Task 5 from your Task List, assuming you have at least 5 tasks.");
            return message.toString();
        }
    }

    /**
     * Marks a given task as done.
     * Updates the list of tasks in the storage file.
     * Prints out an error message if index of the task given is out of range or invalid.
     *
     * @param taskIndex The index of the Task to be marked as done.
     * @return The message to indicate that the task is marked as done.
     * @throws IOException If there is an issue with updating the data in the storage file.
     */
    protected String markTask(int taskIndex) throws IOException {
        StringBuilder message = new StringBuilder();
        if (taskIndex < 0 || taskIndex >= listOfTasks.size()) {
            message.append(String.format("Invalid Index of Task. You currently have %d Task(s)\n", listOfTasks.size()));
        } else {
            Task task = listOfTasks.get(taskIndex);
            task.markAsDone();
            assert listOfTasks.get(taskIndex).getStatus() : "Task must be marked done.";
            storage.clearAllData();
            storage.updateData();

            message.append("Nice! I've marked this Task as done:\n");
            message.append(String.format("\t[%s] %s\n", task.getStatusIcon(), task.getDescription()));
        }
        return message.toString();
    }

    /**
     * Marks a given task as not done.
     * Updates the list of tasks in the storage file.
     * Prints out an error message if index of the task given is out of range or invalid.
     *
     * @param taskIndex The index of the Task to be marked as not done yet.
     * @return The message to indicate that the task is marked as not done yet.
     * @throws IOException If there is an issue with updating the data in the storage file.
     */
    protected String unmarkTask(int taskIndex) throws IOException {
        StringBuilder message = new StringBuilder();
        if (taskIndex < 0 || taskIndex >= listOfTasks.size()) {
            message.append(String.format("Invalid Index of Task. You currently have %d Task(s)\n", listOfTasks.size()));
        } else {
            Task task = listOfTasks.get(taskIndex);
            task.markAsNotDone();
            assert !listOfTasks.get(taskIndex).getStatus() : "Task must be marked NOT done yet.";
            storage.clearAllData();
            storage.updateData();

            message.append("Ok. I've marked this Task as NOT done yet:\n");
            message.append(String.format("\t[%s] %s\n", task.getStatusIcon(), task.getDescription()));
            System.out.println("     Ok. I've marked this Task as NOT done yet:");
            System.out.printf("       [%s] %s\n", task.getStatusIcon(), task.getDescription());
        }
        return message.toString();
    }

    /**
     * Checks if the line representing task details saved in memory is valid.
     * This is used when we are loading the list of tasks from user's past data.
     *
     * @param line The string representing one task that we are checking.
     * @return True if this line is a valid task, False otherwise.
     */
    protected static boolean isValidTaskLine(String line) {
        String[] tokens = line.split("\\|");

        if (tokens.length >= 3 && tokens.length <= 5) { // Valid number of segments: 3-5 (Todo-Event)
            String taskType = tokens[0].trim();
            String completionStatus = tokens[1].trim();
            String description = tokens[2].trim();

            // Line matches expected format
            return taskType.matches("[TDE]") && completionStatus.matches("[01]") && !description.isEmpty();
        }

        return false;
    }

    /**
     * Checks if the date provided is a valid date and in the correct date format.
     * This is used during task creation and task loading from storage file.
     *
     * @param testDate The date from a task that we are checking
     * @return True if this date is a valid Date with the correct date Format "yyyy-MM-dd", False otherwise.
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

    /**
     * Finds all tasks that contain a given keyword.
     * The keyword provided is case-sensitive.
     *
     * @param matchingKeyword The keyword given by the user to find all tasks containing it.
     * @return The message representing the tasks found using the keyword provided.
     */
    protected String findTask(String matchingKeyword) {
        if (matchingKeyword.trim().isEmpty()) {
            return "Please provide the keyword you wish to find :)";
        }
        if (listOfTasks.isEmpty()) {
            System.out.println("\t You currently have no tasks so I can't find any matching tasks :/.");
            return "\t You currently have no tasks so I can't find any matching tasks :(";
        }
        assert !listOfTasks.isEmpty() : "There is no tasks to find.";

        int taskCount = 0;
        StringBuilder matchingTasks = new StringBuilder(String.format(
                "Here are your tasks that contains '%s':", matchingKeyword));
        for (Task task : listOfTasks) {
            if (task.getDescription().contains(matchingKeyword)) {
                matchingTasks.append("\n\t ").append(task);
                taskCount++;
            }
        }

        //Output matching tasks
        if (taskCount > 0) {
            return matchingTasks.toString();
        } else {
            assert (taskCount == 0) : "There should be 0 matching tasks";
            return String.format("\t Hm there are no matching tasks with '%s'. "
                    + "Try with another keyword.", matchingKeyword);
        }
    }

    protected String viewSchedule(String userInput) throws InvalidDateException {
        String[] details = userInput.split("\\s+"); // Split by space
        if (details.length <= 1) {
            return "Invalid input. Please key in `view YYYY-MM-DD` format.";
        }
        String requestedDate = details[1].trim(); // Get requested date

        if (!isValidDate(requestedDate)) {
            throw new InvalidDateException();
        }

        String requestedDateString = LocalDate.parse(requestedDate).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        ArrayList<Task> scheduledTasks = printSchedule(LocalDate.parse(requestedDate));
        if (scheduledTasks.size() == 0) {
            return String.format("There is no tasks happening on %s. ", requestedDateString)
                    + "Please double check your list of tasks.";
        }

        StringBuilder message = new StringBuilder(
                String.format("Here are your scheduled tasks happening on %s :", requestedDateString));

        for (Task task : scheduledTasks) {
            message.append("\n\t ").append(task);
        }
        return message.toString();
    }

    protected ArrayList<Task> printSchedule(LocalDate requestedDate) {
        ArrayList<Task> scheduledTasks = new ArrayList<>();

        for (Task task : listOfTasks) {
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                LocalDate dueDate = deadlineTask.getDueDate();
                if (requestedDate.equals(dueDate)) {
                    scheduledTasks.add(task);
                }
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                LocalDate startDate = eventTask.getStartTime();
                LocalDate endDate = eventTask.getEndTime();
                if (!requestedDate.isBefore(startDate) && !requestedDate.isAfter(endDate)) {
                    scheduledTasks.add(task);
                }
            }
        }

        return scheduledTasks;
    }
}
