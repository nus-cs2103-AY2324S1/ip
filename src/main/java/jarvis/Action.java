package jarvis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidDateTimeFormatException;
import exceptions.InvalidIndexException;
import exceptions.InvalidTaskFormatException;

public class Action {

    private TaskList taskList;
    private Ui ui;

    public Action(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public void listTasks() {
        ArrayList<Task> tasks = taskList.getTask();
        if (tasks.isEmpty()) {
            ui.printResponse("Congratulations Master! There is no task at the moment!");
        } else {
            ui.printTasks(tasks);
        }
    }

    public void updateTask(int index, boolean isCompleted) throws InvalidIndexException {
        ArrayList<Task> tasks = taskList.getTask();
        if (index >= 1 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);

            if (isCompleted) {
                task.markCompleted();
            } else {
                task.unmarkCompleted();
            }

            ui.printTaskStatus(task);
        } else {
            throw new InvalidIndexException(null);
        }
    }

    public void addTask(String taskDetails, String taskType) throws InvalidTaskFormatException, InvalidDateTimeFormatException {

        if (taskType.equalsIgnoreCase("todo")) {
            if (taskDetails.equalsIgnoreCase("todo")) {
                throw new InvalidTaskFormatException(null);
            }
            String taskTitle = taskDetails.substring(5).trim();
            Todo todo = new Todo(taskTitle, false);
            taskList.addTask(todo);
            ui.printResponse("Yes Master! I've added this task: \n" + "\t" + todo.toString() + "\n" +
                    "    Master, you have " + taskList.getTaskCount() + " tasks in the list.");
        } else if (taskType.equalsIgnoreCase("deadline")) {
            if (taskDetails.equalsIgnoreCase("deadline")) {
                throw new InvalidTaskFormatException(null);
            }
            int indexOfBy = taskDetails.indexOf("by");

            if (indexOfBy != 1 && indexOfBy <= taskDetails.length()) {
                String taskTitle = taskDetails.substring(9, indexOfBy).trim();
                String dueDate = taskDetails.substring(indexOfBy + 2).trim();
                LocalDateTime formattedDueDate = parseDateTime(dueDate);
                Deadline deadline = new Deadline(taskTitle, formattedDueDate, false);
                taskList.addTask(deadline);
                ui.printResponse("Yes Master! I've added this task: \n" + "\t" + deadline.toString() + "\n" +
                        "    Master, you have " + taskList.getTaskCount() + " tasks in the list.");
            }
        } else if (taskType.equalsIgnoreCase("event")) {
            if (taskDetails.equalsIgnoreCase("event")) {
                throw new InvalidTaskFormatException(null);
            }
            int indexOfFrom = taskDetails.indexOf("from");
            int indexOfTo = taskDetails.indexOf("to");

            if (indexOfFrom != 1 && indexOfFrom <= taskDetails.length() ||
                    indexOfTo != 1 && indexOfTo <= taskDetails.length()) {
                String taskTitle = taskDetails.substring(6, indexOfFrom).trim();
                String fromDateTime = taskDetails.substring(indexOfFrom + 4, indexOfTo).trim();
                String toDateTime = taskDetails.substring(indexOfTo + 2).trim();
                LocalDateTime formattedFromTime = parseDateTime(fromDateTime);
                LocalDateTime formattedToTime = parseDateTime(toDateTime);
                Event event = new Event(taskTitle, formattedFromTime, formattedToTime, false);
                taskList.addTask(event);
                ui.printResponse("Yes Master! I've added this task: \n" + "\t" + event.toString() + "\n" +
                        "    Master, you have " + taskList.getTaskCount() + " tasks in the list.");
            }
        }
    }

    public void deleteTask(int index) throws InvalidIndexException {
        ArrayList<Task> tasks = taskList.getTask();
        if (index >= 1 && index <= tasks.size()) {
            Task removedTask = tasks.remove(index - 1);

            ui.printResponse("Noted Master! I've removed this task:\n" + "\t" + removedTask.toString() + "\n" +
                    "    Master, you have " + taskList.getTaskCount() + " tasks in the list.");
        } else {
            throw new InvalidIndexException(null);
        }
    }

    public static Task stringToTask(String line) throws InvalidTaskFormatException {
        try {
            String[] lineSplit = line.split("\\|");
            String taskType = lineSplit[0].trim();
            boolean isCompleted = Integer.parseInt(lineSplit[1].trim()) == 1 ? true : false;
            String taskDetails = lineSplit[2].trim();
    
            switch (taskType) {
                case "T":
                    return new Todo(taskDetails, isCompleted);
                case "D":
                    String deadlineByString = lineSplit[3].trim();
                    LocalDateTime formattedDeadlineBy = parseDateTime(deadlineByString);
                    return new Deadline(taskDetails, formattedDeadlineBy, isCompleted);
                case "E":
                    String fromTime = lineSplit[3].trim();
                    String toTime = lineSplit[4].trim();
                    LocalDateTime formattedFromTime = parseDateTime(fromTime);
                    LocalDateTime formattedToTime = parseDateTime(toTime);
                    return new Event(taskDetails, formattedFromTime, formattedToTime, isCompleted);
                default:
                    throw new InvalidTaskFormatException("Unknown Task Type: " + taskType);
            }
        } catch (Exception e) {
            throw new InvalidTaskFormatException("Invalid task format: " + line);
        }
    }

    public static LocalDateTime parseDateTime(String inputDateTime) throws InvalidDateTimeFormatException {
        List<String> inputFormats = new ArrayList<>();
        inputFormats.add(Ui.DATE_TIME_FORMAT);
        inputFormats.add("dd MMM yyyy HHmm");
        inputFormats.add("yyyy-MM-dd HHmm");
        inputFormats.add("dd/MM/yyyy HHmm");

        LocalDateTime result = null;
        for (String inputFormat : inputFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(inputFormat);
                LocalDateTime parsedDateTime = LocalDateTime.parse(inputDateTime, formatter);
                result = parsedDateTime;
                break;
            } catch (Exception e) {
                continue;
            }
        }
        
        if (result != null) {
            return result;
        } else {
            throw new InvalidDateTimeFormatException(inputDateTime);
        }
    }
}
