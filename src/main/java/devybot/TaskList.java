package devybot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import devybot.exceptions.EmptyDescriptionException;
import devybot.exceptions.TaskIndexOutOfBoundsException;
import devybot.tasks.DeadlineTask;
import devybot.tasks.EventTask;
import devybot.tasks.Task;
import devybot.tasks.TodoTask;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void deleteTask(int index) {
        try {
            if (index >= this.taskList.size() || index < 0) {
                throw new TaskIndexOutOfBoundsException(index);
            }
            Task currentTask = this.taskList.get(index);
            taskList.remove(index);
            String outpString = "Noted. I've removed this task:\n  " + currentTask;
            Ui.showMessage(outpString);
        } catch (TaskIndexOutOfBoundsException e) {
            Ui.showMessage(e.getMessage());
        }
    }

    public int size() {
        return this.taskList.size();
    }

    public void listTasks() {
        if (taskList.size() == 0) {
            Ui.showMessage("Currently no tasks available.");
            return;
        }
        String outpString = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            outpString += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        Ui.showMessage(outpString);
    }

    public void addTodoTask(String userInput) throws EmptyDescriptionException {
        String description = userInput.substring(4).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }

        Task newTask = new TodoTask(description);
        taskList.add(newTask);

        String outpString = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + taskList.size()
                + " tasks in the list.";
        Ui.showMessage(outpString);
    }

    public void addDeadlineTask(String userInput) throws EmptyDescriptionException {
        String[] parts = userInput.split(" /by ");

        String description = parts[0].substring(8).trim();

        if (description.isEmpty() || parts.length < 2) {
            // need to edit to be more specific
            throw new EmptyDescriptionException("deadline");
        }

        String by = parts[1].trim();

        Task newTask;

        try {
            if (by.contains(" ")) {
                // Contains time, parse as LocalDateTime
                LocalDateTime dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                newTask = new DeadlineTask(description, dateTime);
            } else {
                // No time, parse as LocalDate
                LocalDate date = LocalDate.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy"));
                newTask = new DeadlineTask(description, date);
            }

            taskList.add(newTask);

            String outpString = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + taskList.size()
                    + " tasks in the list.";
            Ui.showMessage(outpString);
        } catch (DateTimeParseException e) {
            // Handle parsing error
            Ui.showMessage("Invalid date/time format. Please use 'd/M/yyyy' or 'd/M/yyyy HHmm'.");
        }
    }

    public void addEventTask(String userInput) throws EmptyDescriptionException {
        String[] parts = userInput.split(" /from | /to ");

        String description = parts[0].substring(5).trim();
        if (description.isEmpty() || parts.length < 3) {
            // need to edit to be more specific
            throw new EmptyDescriptionException("event");
        }

        String from = parts[1].trim();
        String to = parts[2].trim();

        try {
            // Contains time, parse as LocalDateTime
            LocalDateTime fromDateTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            LocalDateTime toDateTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            Task newTask = new EventTask(description, fromDateTime, toDateTime);

            taskList.add(newTask);

            String outpString = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + taskList.size()
                    + " tasks in the list.";
            Ui.showMessage(outpString);
        } catch (DateTimeParseException e) {
            Ui.showMessage("Invald date/tim frmat. Please use 'd/M/yyyy HHmm'.");
        }
    }

    public void markTaskAsDone(int index) throws TaskIndexOutOfBoundsException {

        if (index >= taskList.size() || index < 0) {
            throw new TaskIndexOutOfBoundsException(index);
        }

        Task currentTask = taskList.get(index);
        currentTask.markTask();

        String outpString = "Nice! I've marked this task as done:\n  " + currentTask;
        Ui.showMessage(outpString);
    }

    public void markTaskAsUndone(int index) throws TaskIndexOutOfBoundsException {

        if (index >= taskList.size() || index < 0) {
            throw new TaskIndexOutOfBoundsException(index);
        }

        Task currentTask = taskList.get(index);
        currentTask.unmarkTask();

        String outpString = "OK, I've marked this task as not done yet:\n  " + currentTask;
        Ui.showMessage(outpString);
    }
}
