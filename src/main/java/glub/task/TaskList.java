package glub.task;

import glub.GlubException;
import glub.Storage;
import glub.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private Storage storage;

    public TaskList(Storage storage) throws GlubException {
        this.storage = storage;
        this.taskList = new ArrayList<>();
        loadTasks();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void loadTasks() throws GlubException {
        ArrayList<String> taskDetails = storage.getTaskDetails();
        for (int i = 0; i < taskDetails.size(); i++) {
            String task = taskDetails.get(i);
            String[] data = task.split("\\|");
            boolean isDone = data[1].equals("X");
            switch (data[0]) {
                case "T":
                    addTask(String.format("%s", data[2]), TaskType.TODO, isDone);
                    break;
                case "D":
                    addTask(String.format("%s /by %s", data[2], data[3]),
                            TaskType.DEADLINE, isDone);
                    break;
                case "E":
                    addTask(String.format("%s /from %s /to %s", data[2], data[3], data[4]),
                            TaskType.EVENT, isDone);
                    break;
            }
        }

    }
    public void addTask(String task, TaskType taskType, boolean isDone) throws GlubException {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        if (task.equals("")) {
            throw new GlubException(String.format("OOPS!! The description of a %s cannot be empty.\n", taskType));
        }
        switch (taskType) {
            case TODO:
                taskList.add(new ToDo(task, isDone));
                break;
            case DEADLINE:
                String[] deadlinePortions = task.split("/");
                String deadlineDesc = deadlinePortions[0];
                try {
                    String deadline = deadlinePortions[1].split(" ", 2)[1];
                    LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, dateTimeFormat);
                    taskList.add(new Deadline(deadlineDesc, isDone, deadlineDateTime));
                } catch (ArrayIndexOutOfBoundsException ex) {
                    throw new GlubException("OOPS!! Please provide a deadline for your deadline task.\n");
                } catch (DateTimeParseException ex) {
                    throw new GlubException("Invalid deadline format! Please ensure it is in dd-MM-yyyy HHmm format!\n");
                }
                break;
            case EVENT:
                String[] eventPortions = task.split("/");
                String eventDesc = eventPortions[0];
                try {
                    String[] startParts = eventPortions[1].split(" ");
                    String start = startParts[1] + " " + startParts[2];
                    String[] endParts = eventPortions[2].split(" ");
                    String end = endParts[1] + " " + endParts[2];
                    LocalDateTime startDateTime = LocalDateTime.parse(start, dateTimeFormat);
                    LocalDateTime endDateTime = LocalDateTime.parse(end, dateTimeFormat);
                    taskList.add(new Event(eventDesc, isDone, startDateTime, endDateTime));
                } catch (ArrayIndexOutOfBoundsException ex) {
                    throw new GlubException("OOPS!! Ensure that your event has a start and end!\n");
                } catch (DateTimeParseException ex) {
                    throw new GlubException(
                            "Invalid start/end format! Please ensure they are in dd/MM/yyyy HH:mm format!\n");
                }
                break;
        }
        storage.saveTasks(this.taskList);
    }

    public void deleteTask(int taskNum) throws GlubException {
        try {
            Task deleted = taskList.remove(taskNum - 1);
            Ui.printDeleteMsg(taskList, deleted);
        } catch (IndexOutOfBoundsException ex) {
            throw new GlubException(String.format("OOPS!! Glub.Task %d does not exist!\n", taskNum));
        }
        storage.saveTasks(this.taskList);
    }

    public String showList() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            list.append(String.format(" %d. %s\n", i + 1, taskList.get(i)));
        }
        return list.toString();
    }

    public void mark(int taskNum) {
        Task task = taskList.get(taskNum - 1);
        task.setDone();
        Ui.printMarkMsg(task);
        storage.saveTasks(this.taskList);
    }

    public void unmark(int taskNum) {
        Task task = taskList.get(taskNum - 1);
        task.setUndone();
        Ui.printUnmarkMsg(task);
        storage.saveTasks(this.taskList);
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
