package cringebot.tasks;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;

import cringebot.exceptions.CringeBotException;
import cringebot.parser.Parser;
import cringebot.ui.Ui;

/**
 * Temporary storage for the user's tasks.
 */
public class TaskList implements Serializable {

    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     *
     * @param tasks tasks loaded from the storage.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor when there are no tasks loaded from the storage.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Removes an item from the list of tasks.
     *
     * @param input input from the user.
     *
     * @return String representation of the task that has been deleted.
     * @throws CringeBotException Lets the user know if task cannot be removed.
     */
    public String deleteItem(String input) throws CringeBotException {

        assert input != null : "input should not be null";
        int index = this.getIndex(input) - 1;

        if (this.checkOutOfBounds(index)) {
            throw new CringeBotException("OOPS!!! I'm sorry, but the index you have inputted is out of bounds. :(( ");
        } else {
            Task deletedTask = this.tasks.remove(index);
            return Ui.deleteItem(this.tasks.size(), deletedTask);
        }
    }

    /**
     * Adds item to the list of tasks.
     *
     * @param task type of task.
     * @param input input from the user.
     *
     * @return String representation of the task that has been added.
     * @throws CringeBotException Lets the user know if the task cannot be added.
     */
    public String addItem(Parser.TaskType task, String input) throws CringeBotException {

        assert input != null : "input should not be null";
        String[] splitSentence = input.split(" /");
        String taskName = getRestOfSentence(splitSentence[0]).strip();
        Task newTask;

        switch(task) {
        case DEADLINE:
            newTask = addDeadline(splitSentence, taskName);
            break;
        case EVENT:
            newTask = addEvent(splitSentence, taskName);
            break;
        case TODO:
            checkEmpty(taskName, "todo");
            newTask = new Todo(taskName);
            break;
        default:
            throw new CringeBotException("OOPS!!! I'm sorry, but I don't know what that means. :((");
        }

        this.tasks.add(newTask);

        return String.format(
                "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                newTask,
                this.tasks.size()
        );
    }

    /**
     * Handles adding deadline to the taskList
     *
     * @param splitSentence input from the user.
     * @param taskName content for the task.
     *
     * @return Deadline task.
     * @throws CringeBotException Lets the user know if the description is invalid.
     */
    public Deadline addDeadline(String[] splitSentence, String taskName) throws CringeBotException {
        checkEmpty(taskName, "deadline");

        if (splitSentence.length < 2 || !splitSentence[1].contains("by")) {
            throw new CringeBotException("OOPS!!! Please indicate a deadline with the /by keyword. :(( ");
        }

        String date = splitSentence[1].replaceAll("by", "").strip();
        return new Deadline(taskName, date);
    }

    /**
     * Handles adding event to the taskList.
     *
     * @param splitSentence array of strings from the user input.
     * @param taskName content of the task.
     *
     * @return Event task.
     * @throws CringeBotException Lets the user know if the description is invalid.
     */
    public Event addEvent(String[] splitSentence, String taskName) throws CringeBotException {
        checkEmpty(taskName, "event");

        if (splitSentence.length < 3 || (!splitSentence[1].contains("from") && !splitSentence[2].contains("to"))) {
            throw new CringeBotException(
                    "OOPS!!! Please indicate a duration for the event with the /from and /to keywords. :(("
            );
        }

        String fromDatetime = splitSentence[1].replaceAll("from", "from:");
        String toDatetime = splitSentence[2].replaceAll("to", "to:");
        taskName = String.format("%s (%s %s)", taskName, fromDatetime, toDatetime);
        return new Event(taskName);
    }

    /**
     * Adds multiple items to the list of tasks.
     *
     * @param task type of task.
     * @param input input from the user.
     *
     * @return String representation of the task.
     * @throws CringeBotException Lets the user know if the task cannot be added.
     */
    public String addRecurringItems(Parser.TaskType task, String input) throws CringeBotException {
        assert input != null : "input should not be null";
        assert Objects.requireNonNull(task) == Parser.TaskType.DEADLINE : "task should be deadline";

        ArrayList<Task> newTask = new ArrayList<>();
        String[] splitSentence = input.split(" /");
        String taskName = getRestOfSentence(splitSentence[0]).strip();
        checkEmpty(taskName, "deadline");

        if (splitSentence.length < 2 || !splitSentence[1].contains("by")) {
            throw new CringeBotException("OOPS!!! Please indicate a deadline with the /by keyword. :(( ");
        }

        String date = splitSentence[1].replaceAll("by", "").strip();
        String endDate = splitSentence[2].replaceAll("recurring", "").strip();
        LocalDate targetDate;
        LocalDate endLocalDate;
        try {
            endLocalDate = LocalDate.parse(endDate);
            targetDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new CringeBotException("OOPS!!! Please a date with the format yyyy-mm-dd. :((");
        }
        ArrayList<LocalDate> weeklyDates = findWeeklyDates(targetDate, endLocalDate);
        for (LocalDate localDate : weeklyDates) {
            newTask.add(new Deadline(taskName, localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        }

        StringBuilder returnString = new StringBuilder("Got it. I've added these task:\n");
        for (Task task1 : newTask) {
            this.tasks.add(task1);
            returnString.append(String.format("%s\n", task1));
        }
        return returnString.append(String.format("Now you have %d tasks in the list.", this.tasks.size())).toString();
    }

    /**
     * Takes in a input date end date and finds all possible dates between input date and the end date.
     *
     * @param targetDate input date.
     * @param endDate end date.
     *
     * @return list of dates between the input date and end date.
     */
    public static ArrayList<LocalDate> findWeeklyDates(LocalDate targetDate, LocalDate endDate) {
        ArrayList<LocalDate> weeklyDates = new ArrayList<>();
        LocalDate currentDate = targetDate;

        while (!currentDate.isAfter(endDate)) {
            weeklyDates.add(currentDate);
            currentDate = currentDate.plusWeeks(1);
        }
        return weeklyDates;
    }

    /**
     * Finds all items with the keyword from the list, then prints them.
     *
     * @param input input from the user.
     *
     * @return String representation of the task.
     */
    public String findItems(String input) {
        assert input != null : "input should not be null";

        String keyword = input.split(" ")[1].strip();
        StringBuilder tasksFound = new StringBuilder("Here are the matching tasks in your list:");

        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).toString().contains(keyword)) {
                tasksFound.append(String.format("\n%d.%s", i + 1, this.tasks.get(i)));
            }
        }
        return tasksFound.toString();
    }

    /**
     * Marks and Un marks the task.
     *
     * @param status To mark of un mark the task.
     * @param input input from the user.
     *
     * @return String representation of the task.
     * @throws CringeBotException Lets the user know if the task cannot be modified.
     */
    public String modifyStatus(Parser.ModifyStatus status, String input) throws CringeBotException {

        assert input != null : "input should not be null";
        int index = getIndex(input) - 1;
        this.checkOutOfBounds(index);

        switch(status) {
        case MARK:
            this.tasks.get(index).markTask();
            return String.format("Nice! I've marked this task as done:\n%s", this.tasks.get(index));
        case UNMARK:
            this.tasks.get(index).unMarkTask();
            return String.format("OK, I've marked this task as not done yet:\n%s", this.tasks.get(index));
        default:
            throw new CringeBotException("OOPS!!! I'm sorry, but an error occurred when modifying your task. :((");
        }
    }

    /**
     * Returns the task when given an index.
     *
     * @param index index of the task.
     *
     * @return task that has been found.
     */
    public Task getTaskWithIndex(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the size of the task.
     *
     * @return size of the task.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the list of task.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets the index given by from the user input.
     *
     * @param input Input from the user.
     *
     * @return Index given by the user.
     */
    public int getIndex(String input) {
        assert input != null : "input should not be null";

        String[] parts = input.split(" ");

        if (parts.length >= 2) {
            String secondPart = parts[1];
            return Integer.parseInt(secondPart);
        }
        return -1;
    }

    /**
     * Checks of the index given exceeds the size of the list.
     *
     * @param index index to check.
     *
     * @return true if index is out of bounds.
     */
    public boolean checkOutOfBounds(int index) {
        return index > this.tasks.size() - 1;
    }

    /**
     * Checks if the input is empty.
     *
     * @param input input from the user.
     * @param taskName content for the task.
     * @throws CringeBotException Lets the user know if the description is invalid.
     */
    public static void checkEmpty(String input, String taskName) throws CringeBotException {
        if (input.isEmpty()) {
            throw new CringeBotException(
                    String.format("OOPS!!! The description of a %s cannot be empty. :((", taskName)
            );
        }
    }

    /**
     * Gets the rest of the sentence, excluding the first word.
     *
     * @param input Input from the user.
     *
     * @return rest of the sentence.
     */
    public static String getRestOfSentence(String input) {
        assert input != null : "input should not be null";

        String[] parts = input.split(" ");
        StringBuilder result = new StringBuilder();

        if (parts.length > 1) {
            for (int i = 1; i < parts.length - 1; i++) {
                result.append(parts[i]).append(" ");
            }
            result.append(parts[parts.length - 1]);
        }
        return result.toString();
    }
}
