package glub.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import glub.GlubException;
import glub.Storage;
import glub.Ui;

/**
 * Task list that contains all tasks and reads or writes from a storage.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private Storage storage;

    /**
     * Initialises TaskList object and loads tasks from storage.
     * @param storage Storage object which task list loads tasks from.
     * @throws GlubException If loading of tasks fails.
     */
    public TaskList(Storage storage) throws GlubException {
        this.storage = storage;
        this.taskList = new ArrayList<>();
        loadTasks();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds tasks from storage into task list.
     * @throws GlubException If loading fails.
     */
    public void loadTasks() throws GlubException {
        ArrayList<String> taskDetails = storage.getTaskDetails();
        for (int i = 0; i < taskDetails.size(); i++) {
            String task = taskDetails.get(i);
            String[] data = task.split("\\|");
            boolean isDone = data[1].equals("X");
            switch (data[0]) {
            case "T":
                addTask(String.format("%s", data[2]), "todo", isDone);
                break;
            case "D":
                addTask(String.format("%s /by %s", data[2], data[3]),
                        "deadline", isDone);
                break;
            case "E":
                addTask(String.format("%s /from %s /to %s", data[2], data[3], data[4]),
                        "event", isDone);
                break;
            default:
            }
        }

    }

    /**
     * Parse dateString into required date format for tasks.
     * @param dateString Input date string.
     * @param taskType Type of task to process date for/=.
     * @return Array containing dates.
     * @throws GlubException If task is invalid.
     */
    public LocalDateTime[] parseTaskDateTime(String dateString, String taskType) throws GlubException {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime[] dateTimes = new LocalDateTime[2];
        if (taskType.equals("deadline")) {
            String deadline = dateString.split(" ", 2)[1];
            LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, dateTimeFormat);
            dateTimes[0] = deadlineDateTime;
        } else if (taskType.equals("event")) {
            String[] startParts = dateString.split(" ");
            String start = startParts[1] + " " + startParts[2];
            String[] endParts = dateString.split(" ");
            String end = endParts[1] + " " + endParts[2];
            LocalDateTime startDateTime = LocalDateTime.parse(start, dateTimeFormat);
            LocalDateTime endDateTime = LocalDateTime.parse(end, dateTimeFormat);
            dateTimes[0] = startDateTime;
            dateTimes[1] = endDateTime;
        } else {
            throw new GlubException("Invalid task!\n");
        }
        return dateTimes;
    }

    /**
     * Adds task into task list.
     * @param task Description of the task.
     * @param taskType Type of task.
     * @param isDone Status of task.
     * @throws GlubException If input is invalid.
     */
    public void addTask(String task, TaskType taskType, boolean isDone) throws GlubException {
        assert taskType != null : "taskType should not be null";
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        if (task.equals("")) {
            throw new GlubException(String.format("OOPS!! The description of a %s cannot be empty.\n", taskType));
        }
        switch (taskType) {
        case "todo":
            taskList.add(new ToDo(task, isDone));
            break;
        case "deadline":
            String[] deadlinePortions = task.split("/");
            String deadlineDesc = deadlinePortions[0];
            try {
                LocalDateTime deadlineDateTime = parseTaskDateTime(deadlinePortions[0], "deadline")[0];
                taskList.add(new Deadline(deadlineDesc, isDone, deadlineDateTime));
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new GlubException("OOPS!! Please provide a deadline for your deadline task.\n");
            } catch (DateTimeParseException ex) {
                throw new GlubException("Invalid deadline format! Please ensure it is in dd-MM-yyyy HHmm format!\n");
            }
            break;
        case "event":
            String[] eventPortions = task.split("/");
            String eventDesc = eventPortions[0];
            try {
                LocalDateTime[] dateTimes = parseTaskDateTime(eventPortions[1], "event");
                LocalDateTime startDateTime = dateTimes[0];
                LocalDateTime endDateTime = dateTimes[1];
                taskList.add(new Event(eventDesc, isDone, startDateTime, endDateTime));
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new GlubException("OOPS!! Ensure that your event has a start and end!\n");
            } catch (DateTimeParseException ex) {
                throw new GlubException(
                        "Invalid start/end format! Please ensure they are in dd-MM-yyyy HHmm format!\n");
            }
            break;
        default:
            throw new GlubException("Oops! Task not recognised!");
        }
        storage.saveTasks(this.taskList);
    }

    /**
     * Deletes a task from the task list.
     * @param taskNum Index of task to be deleted.
     * @throws GlubException If taskNum indicated is invalid.
     */
    public String deleteTask(int taskNum) throws GlubException {
        Task deleted;
        try {
            deleted = taskList.remove(taskNum - 1);
        } catch (IndexOutOfBoundsException ex) {
            throw new GlubException(String.format("OOPS!! Task %d does not exist!\n", taskNum));
        }
        storage.saveTasks(this.taskList);
        return Ui.printDeleteMsg(taskList, deleted);
    }

    /**
     * Displays all tasks.
     * @return String of all tasks.
     */
    public String showList() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            list.append(String.format(" %d. %s\n", i + 1, taskList.get(i)));
        }
        return list.toString();
    }

    /**
     * Marks a task from the task list.
     * @param taskNum Index of task to be marked.
     */
    public String mark(int taskNum) {
        Task task = taskList.get(taskNum - 1);
        task.setDone();
        storage.saveTasks(this.taskList);
        return Ui.printMarkMsg(task);
    }

    /**
     * Unmarks a task from the task list.
     * @param taskNum Index of task to be unmarked.
     */
    public String unmark(int taskNum) {
        Task task = taskList.get(taskNum - 1);
        task.setUndone();
        storage.saveTasks(this.taskList);
        return Ui.printUnmarkMsg(task);
    }

    /**
     * Finds all tasks matching search string.
     * @param searchString String to be matched to tasks.
     * @return String representation of matching tasks.
     */
    public String findTasks(String searchString) {
        StringBuilder matchingTasks = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).checkMatch(searchString)) {
                matchingTasks.append(String.format("\t%d. %s\n", i + 1, taskList.get(i)));
            }
        }
        return matchingTasks.toString();
    }
}
