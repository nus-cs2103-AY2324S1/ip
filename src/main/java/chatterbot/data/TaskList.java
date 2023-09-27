package chatterbot.data;
import chatterbot.exceptions.InvalidDeadlineException;
import chatterbot.exceptions.NoTaskDescriptionException;
import chatterbot.storage.Storage;
import chatterbot.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a list of tasks that have been added.
 */
public class TaskList {

    private static ArrayList<Task> list;
    private String response;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * This method retrieves tasks from the ChatterBot.txt and loads them up into the task list.
     *
     * @param storage This is where the file and list contents are edited.
     */
    public static void initiateTaskList(Storage storage) {
        try {
            list = storage.copyFileContents("data/ChatterBot.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * This method converts the current task list to a string.
     *
     * @param list This is the current task list.
     * @return String This is the formatted task list.
     */
    public static String convertToString(ArrayList<Task> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : list) {
            stringBuilder.append(task.formatForFile()).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * This method adds task to the task list, as well as the file chatterbot.txt.
     *
     * @param task    This is the task to be added.
     * @param storage This is where the file is accessed.
     * @return String This is the response to the user.
     */
    public String addTask(Task task, Storage storage) throws InvalidDeadlineException, IOException {
        assert task != null : "Task to add cannot be null.";
        int initialSize = list.size();
        if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            String deadlineBy = deadlineTask.by;
            try {
                LocalDate.parse(deadlineBy, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                throw new InvalidDeadlineException();
            }
        }
        list.add(task);
        assert list.size() == initialSize + 1 : "Task list size did not increase after adding a task.";
        storage.appendToFile(task.formatForFile());
        return "Got it. I've added this task:\n" + task.formatForFile() + "\nNow you have " + list.size()
                + " tasks in the list.";
    }

    /**
     * This method deletes tasks from the task list, as well as the file chatterbot.txt.
     *
     * @param taskIndex This is the index to access the task to be deleted.
     * @param storage   This is where the file is accessed.
     * @return String This is the response to the user.
     */
    public String deleteTask(int taskIndex, Storage storage) {
        if (taskIndex <= -1 || taskIndex > getSize()) {
            throw new IndexOutOfBoundsException();
        }
        Task deletedTask = list.get(taskIndex);
        assert taskIndex >= 0 && taskIndex < list.size() : "Task index must be within list range.";
        int initialSize = list.size();
        list.remove(taskIndex);
        assert list.size() == initialSize - 1 : "Task list size did not decrease after removing a task.";
        try {
            storage.writeToFile(convertToString(list));
        } catch (IOException e) {
            return "Error: File not found.";
        }
        return "Noted. I've removed this task:\n" + deletedTask.formatForFile() + "\nNow you have "
                + list.size() + " tasks in the list.";
    }

    /**
     * This method marks the task as done.
     *
     * @param taskIndex This is the location of the task within the list.
     * @param storage   This is where the list is accessed.
     */
    public void setDone(int taskIndex, Storage storage) throws IndexOutOfBoundsException {
        if (taskIndex <= -1 || taskIndex > getSize()) {
            throw new IndexOutOfBoundsException();
        }
        list.get(taskIndex).setDone();
        try {
            storage.writeToFile(convertToString(list));
        } catch (IOException e) {
            System.out.println("Error: File not found.");
        }
    }

    /**
     * This method marks the task as not done.
     * @param taskIndex This is the location of the task within the list.
     * @param storage This is where the list is accessed.
     */
    public void unsetDone(int taskIndex, Storage storage) throws IndexOutOfBoundsException {
        if (taskIndex <= -1 || taskIndex > getSize()) {
            throw new IndexOutOfBoundsException();
        }
        list.get(taskIndex).setUndone();
        try {
            storage.writeToFile(convertToString(list));
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }

    /**
     * This method finds the size of the task list.
     * @return int This is the number of tasks in the list.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * This method finds the task at taskIndex position in the task list.
     * @param taskIndex This is the position the task is located in the task list.
     * @return Task This is the task at the taskIndex position.
     */
    public Task getTask(int taskIndex) {
        return list.get(taskIndex);
    }

    /**
     * This method finds whether the task description already exists in the list.
     * @param description This is the task description.
     * @return boolean This shows whether the task description is duplicated.
     */
    public boolean isDuplicate(String description) {
        for (int i = 0; i < getSize(); i++) {
            if (getTask(i).getTaskDescription().equals(description)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method initialises a deadline task to be added to the list of tasks and the chatterbot.txt file.
     * @param userMessage This is the user input.
     * @param taskList This is the list of tasks.
     * @param storage This is where the file is accessed.
     * @param ui This provides responses to the users.
     * @return String This response is returned to the user.
     */
    public String initialiseDeadlineTask(String userMessage, TaskList taskList, Storage storage, Ui ui) throws NoTaskDescriptionException, InvalidDeadlineException {
        if (userMessage.length() <= 9) {
            throw new NoTaskDescriptionException();
        }
        int slashDeadline = userMessage.indexOf("/");
        String deadlineDescription = userMessage.substring(9, slashDeadline).trim();
        String deadlineBy = userMessage.substring(slashDeadline + 3).trim();
        Deadline d = new Deadline(deadlineDescription, deadlineBy);
        assert d != null : "Deadline to add cannot be null.";
        if (!taskList.isDuplicate(deadlineDescription)) {
            try {
                taskList.addTask(d, storage);
                response = ui.showAddedDeadline(d);
            } catch (InvalidDeadlineException e) {
                response = ui.showError(e);
            } catch (IOException e) {
                response = "File was not found! Task not added.";
            }
        } else {
            response = "Duplicate task entered! Task not added to list.";
        }
        return response;
    }

    /**
     * This method initialises a todo task to be added to the list of tasks and the chatterbot.txt file.
     * @param userMessage This is the user input.
     * @param taskList This is the list of tasks.
     * @param storage This is where the file is accessed.
     * @param ui This provides responses to the users.
     * @return String This response is returned to the user.
     */
    public String initialiseTodoTask(String userMessage, TaskList taskList, Storage storage, Ui ui) throws NoTaskDescriptionException, InvalidDeadlineException, IOException {
        if (userMessage.length() <= 5) {
            throw new NoTaskDescriptionException();
        }
        Todo td = new Todo(userMessage.substring(5));
        assert td != null : "Todo to add cannot be null.";
        if (!taskList.isDuplicate(userMessage.substring(5))) {
            taskList.addTask(td, storage);
            response = ui.showAddedTodo(td);
        } else {
            response = "Duplicate task entered! Task not added to list.";
        }
        return response;
    }

    /**
     * This method initialises an event task to be added to the list of tasks and the chatterbot.txt file.
     * @param userMessage This is the user input.
     * @param taskList This is the list of tasks.
     * @param storage This is where the file is accessed.
     * @param ui This provides responses to the users.
     * @return String This response is returned to the user.
     */
    public String initialiseEventTask(String userMessage, TaskList taskList, Storage storage, Ui ui) throws NoTaskDescriptionException, InvalidDeadlineException, IOException {
        if (userMessage.length() <= 6) {
            throw new NoTaskDescriptionException();
        }
        String[] eventSplit = userMessage.split("/");
        String eventDescription = eventSplit[0].substring(6);
        String eventTo = eventSplit[1].substring(5);
        String eventFrom = eventSplit[2].substring(3);
        Event e = new Event(eventDescription, eventTo, eventFrom);
        assert e != null : "Event to add cannot be null.";
        if (!taskList.isDuplicate(eventDescription)) {
            taskList.addTask(e, storage);
            response = ui.showAddedEvent(e);
        } else {
            response = "Duplicate task entered! Task not added to list.";
        }
        return response;
    }
}