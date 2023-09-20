package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * This class encapsulates the array list that contains the tasks and operations that operates on the array list.
 */
public class TaskList {

    protected final ArrayList<Task> taskArray;
    private String[] dateFormats = { "dd-MM-yyyy", "yyyy-MM-dd", "dd/MM/yyyy", "yyyy/MM/dd" };
    private String[] timeFormats = { "HHmm", "HH:mm" };

    /**
     * Constructor for TaskList.
     *
     * @param taskArray the corresponding array list that contains the tasks.
     */
    public TaskList(ArrayList<Task> taskArray) {
        assert taskArray != null;

        this.taskArray = taskArray;
    }

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        taskArray = new ArrayList<>();
    }

    private LocalDateTime findDateFormatInput(String input) throws DukeException {
        for (int i = 0; i < dateFormats.length; i++) {
            for (int j = 0; j < timeFormats.length; j++) {
                try {
                    return LocalDateTime.parse(input,
                            DateTimeFormatter.ofPattern(dateFormats[i] + " " + timeFormats[j]));
                } catch (DateTimeParseException e) {
                    // Keep trying other formats, hence no throwing exceptions here
                }
            }
        }
        throw new DukeException("HOHOHO! The date/time format seems to be wrong!"
                + "\nPermitted formats for date: dd-mm-yyyy | yyyy-mm-dd | dd/mm/yyyy | yyyy/mm/dd"
                + "\nPermitted formats for time (Only 24-hours format): HH:MM | HHMM"
                + "\nE.g. 22/09/2023 22:00 | 2023-08-30 0100");
    }
    /**
     * Adds a Todo task into the array list containing the tasks.
     *
     * @param description the description of the task to be added.
     */
    public void addTodoTask(String description) {
        assert description != null && !description.isEmpty();
        taskArray.add(new Todo(description.trim()));
    }

    /**
     * Displays the contents of the array list containing the tasks.
     *
     * @param taskList the TaskList object that contains the array list containing the tasks.
     * @param isMatch true if the user command is a "find" command, false otherwise.
     */
    public String listTasks(TaskList taskList, boolean isMatch) {
        return Ui.showTaskList(taskList.taskArray, isMatch);
    }

    /**
     * Marks or unmarks a task in the array list containing the tasks.
     *
     * @param action the string to determine it is to mark or unmark the task.
     * @param taskNum the task number to be marked or unmarked.
     * @throws DukeException if task number does not exist in the array list.
     */
    public void markOrUnmarkTask(String action, int taskNum, StringBuilder stringBuilder) throws DukeException {
        assert action.equals("mark") || action.equals("unmark");
        try {
            if (action.equals("mark")) {
                stringBuilder.append(Ui.getTaskMarked(taskArray.get(taskNum - 1).isDone));
                taskArray.get(taskNum - 1).markDone();
            } else {
                stringBuilder.append(Ui.getTaskNotMarked(!taskArray.get(taskNum - 1).isDone));
                taskArray.get(taskNum - 1).markNotDone();
            }
            stringBuilder.append(taskArray.get(taskNum - 1).toString());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("WARBLE WARBLE! This task number does not exist!");
        }
    }

    /**
     * Adds a Deadline or Event task into the array list containing the tasks.
     *
     * @param action the string to determine where is a deadline or event task.
     * @param remainLine the string array from splitting that will have the date/time string representation
     * @throws DukeException if the string doesn't have a date/time string representation or in an invalid format.
     */
    public void addDeadlineOrEventTask(String action, String[] remainLine)
            throws DukeException {
        assert action.equals("deadline") || action.equals("event");
        if (action.equals("deadline")) {
            try {
                String dateTime = remainLine[1].substring(3);
                taskArray.add(new Deadline(remainLine[0], findDateFormatInput(dateTime)));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("BEEPBEEP! You forgot to give a \"/by date/time\" for the deadline!");
            }
        } else {
            if (!remainLine[1].contains("from ")) {
                throw new DukeException("BEEPBEEP! You forgot to give a \"/from date/time\" for the event");
            }
            try {
                String[] splitTo = remainLine[1].split("/to ", 2);
                String fromDateTime = splitTo[0].substring(5, splitTo[0].length() - 1);
                taskArray.add(new Event(remainLine[0],
                        findDateFormatInput(fromDateTime), findDateFormatInput(splitTo[1])));
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                throw new DukeException("BEEPBEEP! You forget to give a \"/to date/time\" for the event!");
            } catch (DateTimeParseException e) {
                throw new DukeException("HOHOHO! The date/time format seems to be wrong! "
                        + "Please use the format yyyy-mm-dd HH:MM instead! E.g. 2023-08-21 01:00");
            }
        }
    }

    /**
     * Deletes the task from the array list containing the tasks.
     *
     * @param taskNum the corresponding task number to be deleted.
     * @throws DukeException if the task number does not exist.
     */
    public void deleteTasks(int taskNum, StringBuilder stringBuilder) throws DukeException {
        try {
            Task taskDeleted = taskArray.get(taskNum - 1);
            taskArray.remove(taskNum - 1);
            stringBuilder.append(Ui.deleteTaskOutput(taskDeleted.toString(), taskArray.size()));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("WARBLE WARBLE, this task number does not exist!");
        }
    }

    /**
     * Displays the tasks that contains the keyword given by the user.
     *
     * @param keyword the keyword to find in the tasks.
     */
    public void findTasks(String keyword, StringBuilder stringBuilder) {
        assert keyword != null;

        ArrayList<Task> foundTasksArray = new ArrayList<>();
        for (int i = 0; i < taskArray.size(); i++) {
            if (taskArray.get(i).toString().toUpperCase().contains(keyword)) {
                foundTasksArray.add(taskArray.get(i));
            }
        }
        stringBuilder.append(Ui.showTaskList(foundTasksArray, true));
    }

    public void doReminder(StringBuilder stringBuilder) {
        ArrayList<Task> dueTasks = new ArrayList<>();

        for (int i = 0; i < taskArray.size(); i++) {
            if ((taskArray.get(i) instanceof Deadline || taskArray.get(i) instanceof Event)
                    && taskArray.get(i).isWithinDue()) {
                dueTasks.add(taskArray.get(i));
            }
        }

        stringBuilder.append(Ui.taskWithinOrPassedDueDate(dueTasks));
    }
}
