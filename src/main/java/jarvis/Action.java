package jarvis;

import java.util.ArrayList;

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

    public void addTask(String taskDetails, String taskType) throws InvalidTaskFormatException {

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
                Deadline deadline = new Deadline(taskTitle, dueDate, false);
                taskList.addTask(deadline);
                ui.printResponse("Yes Master! I've added this task: \n" + "\t" + deadline.toString() + "\n" +
                        "    Master, you have " + taskList.getTaskCount() + " tasks in the list.");
            }
        } else if (taskType.equalsIgnoreCase("event")) {
            if (taskDetails.equalsIgnoreCase("event")) {
                throw new InvalidTaskFormatException(null);
            }
            int indexOfFrom = taskDetails.indexOf("from");

            if (indexOfFrom != 1 && indexOfFrom <= taskDetails.length()) {
                String taskTitle = taskDetails.substring(6, indexOfFrom).trim();
                String dueDate = taskDetails.substring(indexOfFrom + 4).trim();

                Event event = new Event(taskTitle, dueDate, false);
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
                    String deadlineBy = lineSplit[3].trim();
                    return new Deadline(taskDetails, deadlineBy, isCompleted);
                case "E":
                    String eventTime = lineSplit[3].trim();
                    return new Event(taskDetails, eventTime, isCompleted);
                default:
                    throw new InvalidTaskFormatException("Unknown Task Type: " + taskType);
            }
        } catch (Exception e) {
            throw new InvalidTaskFormatException("Invalid task format: " + line);
        }
    }
}
