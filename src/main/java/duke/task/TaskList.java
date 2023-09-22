package duke.task;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.DukeException;

/**
 * The TaskList class manages a list of tasks and provides methods for adding, creating, deleting, and modifying tasks.
 *
 * @author selwyn
 */
public class TaskList {
    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HHmm";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    private ArrayList<Task> tasks;

    private int numTasks;

    /**
     * Constructs a TaskList object with the specified list of tasks.
     *
     * @param tasks The list of tasks to be managed.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.numTasks = tasks.size();
    }

    /**
     * Adds a new task of the specified type with the given arguments to the list.
     *
     * @param taskType The type of task to be added.
     * @param args The arguments for creating the task.
     * @return The added Task object.
     * @throws DukeException If there is an issue with task creation or task type is unsupported.
     */
    public Task addTask(TaskType taskType, String args) throws DukeException {
        Task newTask;

        try {
            switch (taskType) {
            case TODO:
                newTask = createTodo(args);
                break;
            case DEADLINE:
                newTask = createDeadline(args);
                break;
            case EVENT:
                newTask = createEvent(args);
                break;
            default:
                throw new DukeException("I can't create this task type!");
            }

            this.tasks.add(newTask);
            this.numTasks++;
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
        return newTask;
    }

    /**
     * Creates a new Todo task with the given description.
     *
     * @param args The description for the Todo task.
     * @return The created Todo task.
     * @throws DukeException If the description is missing.
     */
    public Todo createTodo(String args) throws DukeException {
        if (args == null || args.isEmpty()) {
            throw new DukeException("Todo tasks should be created in this format: todo [name]");
        }
        return new Todo(args);
    }

    /**
     * Creates a new Deadline task with the given description and end date and time.
     *
     * @param args The description and end date/time for the Deadline task.
     * @return The created Deadline task.
     * @throws DukeException If the format is incorrect or the end date/time is invalid.
     */
    public Deadline createDeadline(String args) throws DukeException {
        try {
            String[] detailsAndDeadline = args.split("/by", 2);
            if (detailsAndDeadline.length != 2) {
                throw new DukeException("Deadline tasks should be created in this format: deadline [name] /by [date]");
            }
            String details = detailsAndDeadline[0].trim();
            String endDateTime = detailsAndDeadline[1].trim();
            LocalDateTime formattedEndDateTime = LocalDateTime.parse(endDateTime, DATE_TIME_FORMATTER);
            return new Deadline(details, formattedEndDateTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid deadline time format. Please use " + DATE_TIME_FORMAT + " format!");
        }
    }

    /**
     * Creates a new Event task with the given description and start/end date and time.
     *
     * @param args The description and start/end date/time for the Event task.
     * @return The created Event task.
     * @throws DukeException If the format is incorrect or the date/time is invalid.
     */
    public Event createEvent(String args) throws DukeException {
        try {
            String[] detailsAndStartEnd = args.split("/from", 2);

            String eventErrMsg = "Event tasks should be created in this format: "
                    + "event [name] /from [start time] /to [end time]";
            if (detailsAndStartEnd.length != 2) {
                throw new DukeException(eventErrMsg);
            }

            String details = detailsAndStartEnd[0].trim();

            String[] startAndEnd = detailsAndStartEnd[1].split("/to", 2);
            if (startAndEnd.length != 2) {
                throw new DukeException(eventErrMsg);
            }

            String start = startAndEnd[0].trim();
            String end = startAndEnd[1].trim();
            LocalDateTime formattedStartDateTime = LocalDateTime.parse(start, DATE_TIME_FORMATTER);
            LocalDateTime formattedEndDateTime = LocalDateTime.parse(end, DATE_TIME_FORMATTER);
            return new Event(details, formattedStartDateTime, formattedEndDateTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid event time format. Please use " + DATE_TIME_FORMAT + " format!");
        }
    }

    /**
     * Deletes a task from the list based on the given task number.
     *
     * @param args The task number to be deleted.
     * @return The deleted Task object.
     * @throws DukeException If the task number is invalid or in an incorrect format.
     */
    public Task deleteTask(String args) throws DukeException {
        int taskNumber;
        Task deletedTask;

        try {
            taskNumber = Integer.parseInt(args);

            if (taskNumber <= 0) {
                throw new DukeException("Number must be more 1 or more!");
            } else if (this.exceedsSizeOfTaskList(taskNumber)) {
                throw new DukeException("Number is higher than current size of task list!");
            }

            deletedTask = this.tasks.remove(taskNumber - 1);
            this.numTasks--;

            return deletedTask;
        } catch (NumberFormatException e) {
            throw new DukeException("Deleting task should be in this format: delete [task number]");
        }
    }

    /**
     * Changes the done status of a task based on the given task number and state.
     *
     * @param args The task number to be marked/unmarked and the state.
     * @param toBeDone The desired state (true for done, false for undone).
     * @return The Task object with the modified done status.
     * @throws DukeException If the task number is invalid or in an incorrect format.
     */
    public Task changeTaskDoneStatus(String args, boolean toBeDone) throws DukeException {
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(args);

            if (taskNumber <= 0) {
                throw new DukeException("Number must be more 1 or more!");
            } else if (this.exceedsSizeOfTaskList(taskNumber)) {
                throw new DukeException("Number is higher than current size of task list!");
            }

            Task taskToChange = this.tasks.get(taskNumber - 1);

            if (toBeDone) {
                taskToChange.markDone();
            } else {
                taskToChange.markUndone();
            }
            return taskToChange;
        } catch (NumberFormatException e) {
            throw new DukeException("Marking/unmarking tasks should be in this format: mark/unmark [task number]");
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Displays the list of tasks to the console.
     *
     * @param isMatching Determine which statements to print (true for matching tasks, false for all tasks)
     */
    public String taskListRepresentation(boolean isMatching) {
        String strToReturn = "";
        if (isMatching) {
            if (this.numTasks == 0) {
                strToReturn += "There are no matching tasks in your list!\n";
            } else if (this.numTasks == 1) {
                strToReturn += "Here is the matching task in your list:\n";
            } else {
                strToReturn += "Here are the matching tasks in your list:\n";
            }
        } else {
            if (this.numTasks == 0) {
                strToReturn += "You have no tasks in your list at the moment!\n";
            } else if (this.numTasks == 1) {
                strToReturn += "Here is the task in your list:\n";
            } else {
                strToReturn += "Here are the tasks in your list:\n";
            }
        }

        for (int i = 0; i < this.numTasks; i++) {
            int bullet = i + 1;
            strToReturn += bullet + ". ";
            strToReturn += this.tasks.get(i).toString() + "\n";
        }

        return strToReturn;
    }

    /**
     * Finds the tasks which details contains the given string parameter
     *
     * @param taskToFindDetails The details of the task to find
     * @return The ArrayList of tasks that match the string parameter
     */
    public ArrayList<Task> findTasks(String taskToFindDetails) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task taskInFocus = this.tasks.get(i);
            if (taskInFocus.toString().contains(taskToFindDetails)) {
                foundTasks.add(taskInFocus);
            }
        }

        return foundTasks;
    }

    /**
     * Returns a list of tasks that matches the task type parameter
     *
     * @param taskType The type of the task to list
     * @return The ArrayList of tasks that match the task type parameter
     */
    public ArrayList<Task> listSpecificTaskType(TaskType taskType) throws DukeException {
        ArrayList<Task> specificTaskList = new ArrayList<>();

        Task taskTypeClass;
        switch (taskType) {
        case TODO:
            taskTypeClass = new Todo(null);
            break;
        case DEADLINE:
            taskTypeClass = new Deadline(null, null);
            break;
        case EVENT:
            taskTypeClass = new Event(null, null, null);
            break;
        default:
            throw new DukeException("Task type must be Todo, Deadline or Event!");
        }

        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getClass() == taskTypeClass.getClass()) {
                specificTaskList.add(this.tasks.get(i));
            }
        }

        return specificTaskList;
    }

    /**
     * Returns a list of tasks that are marked done already
     *
     * @return The ArrayList of tasks that are marked done already
     */
    public ArrayList<Task> listDoneTasks() {
        ArrayList<Task> doneTasks = new ArrayList<>();

        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getIsDone()) {
                doneTasks.add(this.tasks.get(i));
            }
        }

        return doneTasks;
    }

    /**
     * Returns the percentage of tasks in the task list which are done already
     *
     * @return The percentage of tasks in the task list which are done already
     */
    public double statePercentOfTasksDone() {
        double totalNumOfTasks = this.getNumTasks();
        double numOfTasksDone = this.listDoneTasks().size();
        double percentValue = numOfTasksDone / totalNumOfTasks * 100;

        if (totalNumOfTasks == 0) {
            return 0;
        }

        if (percentValue == 0) {
            return percentValue;
        } else {
            DecimalFormat df = new DecimalFormat("#.##");
            String formattedNumber = df.format(percentValue);
            double roundedPercentValue = Double.parseDouble(formattedNumber);

            return roundedPercentValue;
        }
    }

    /**
     * Checks if the given index exceeds the size of the task list.
     *
     * @param index The index to be checked.
     * @return True if the index exceeds the task list size, false otherwise.
     */
    public boolean exceedsSizeOfTaskList(int index) {
        return index > numTasks;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The ArrayList of Task objects.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Retrieves the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getNumTasks() {
        return this.numTasks;
    }
}
