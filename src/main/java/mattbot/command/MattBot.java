package mattbot.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
// import java.util.ArrayList;
// import java.util.Scanner;

import mattbot.task.Deadline;
import mattbot.task.Event;
import mattbot.task.Task;
import mattbot.task.Todo;
import mattbot.ui.ResponseEngine;

/**
 * Implements a Task management tool.
 */
public class MattBot {
    private static Storage mattmory;
    private static TaskList tasks;
    private static final DateTimeFormatter PRINT_DTF = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");

    private static ResponseEngine resp;

    /**
     * Starts a MattBot instance.
     */
    public MattBot() {
        resp = new ResponseEngine();
        try {
            mattmory = new Storage();
            tasks = mattmory.load();
        } catch (IOException e) {
            System.out.println(e);
            tasks = new TaskList();
        }
    }
    /**
     * Starts the Task management tool.
     */
    public static void main(String[] args) {
        // Load save file
        // List<Task> taskList = new ArrayList<Task>();
        try {
            mattmory = new Storage();
            tasks = mattmory.load();
        } catch (IOException e) {
            System.out.println(e);
            tasks = new TaskList();
        }
    }
    public String getResponse(String userInput) {
        String command = userInput.split(" ", 2)[0];
        switch (command) {
        case "bye":
            return resp.getExitMessage();
        case "list":
            return resp.getTasks(tasks);
        default:
            int len = userInput.split(" ", 2).length;
            if (len <= 1) {
                return resp.errMissingArgs(command);
            }
            String arguments = userInput.split(" ", 2)[1];
            switch (command) {
            case "mark":
                return markTask(arguments);
            case "unmark":
                return unmarkTask(arguments);
            case "todo":
                return newTodo(arguments);
            case "deadline":
                return newDeadline(arguments);
            case "event":
                return newEvent(arguments);
            case "delete":
                return delete(arguments);
            case "find":
                return find(arguments);
            default:
                return resp.errParseUnsure();
            }
        }
    }

    private String markTask(String arguments) {
        int taskId = Integer.parseInt(arguments);
        tasks.markTask(taskId);
        mattmory.writeBack(tasks);
        Task t = tasks.getTask(taskId);
        assert t.showStatus() == "X" : "Task not marked.";
        return resp.getMarked(t);
    }

    private String unmarkTask(String arguments) {
        int taskId = Integer.parseInt(arguments);
        tasks.unmarkTask(taskId);
        mattmory.writeBack(tasks);
        Task t = tasks.getTask(taskId);
        assert t.showStatus() == " " : "Task not unmarked.";
        return resp.getUnmarked(t);
    }

    private String newTodo(String arguments) {
        Task t = new Todo(arguments);
        tasks.addTask(t);
        mattmory.writeBack(tasks);
        return resp.getNewTodo(t);
    }

    private String newDeadline(String arguments) {
        String name = arguments.split(" /by ", 2)[0];
        String dueDate = arguments.split(" /by ", 2)[1];
        try {
            LocalDateTime dtDueDate = LocalDateTime.parse(dueDate, PRINT_DTF);
            Task t = new Deadline(name, dtDueDate);
            // assert t.getDate() == dtDueDate;
            tasks.addTask(t);
            mattmory.writeBack(tasks);
            return resp.getNewDeadline(t);
        } catch (DateTimeParseException e) {
            return resp.errWrongDateFormat();
        }
    }
    private String newEvent(String arguments) {
        String name = arguments.split(" /from ", 2)[0];
        String dates = arguments.split(" /from ", 2)[1];
        String startDate = dates.split(" /to ")[0];
        String endDate = dates.split(" /to ", 2)[1];
        try {
            LocalDateTime dtStartDate = LocalDateTime.parse(startDate, PRINT_DTF);
            LocalDateTime dtEndDate = LocalDateTime.parse(endDate, PRINT_DTF);
            Task t = new Event(name, dtStartDate, dtEndDate);
            tasks.addTask(t);
            mattmory.writeBack(tasks);
            return resp.getNewEvent(t);
        } catch (DateTimeParseException e) {
            return resp.errWrongDateFormat();
        }
    }
    private String delete(String arguments) {
        if (tasks.size() == 0 || tasks.size() < Integer.parseInt(arguments)) {
            return resp.errImpossibleTask();
        }
        Task t = tasks.getTask(Integer.parseInt(arguments));
        tasks.removeTask(Integer.parseInt(arguments));
        mattmory.writeBack(tasks);
        return resp.getDelete(t);
    }
    private String find(String arguments) {
        TaskList found = new TaskList();
        for (int i = 1; i <= tasks.size(); i++) {
            Task t = tasks.getTask(i);
            if (t.showName().contains(arguments)) {
                found.addTask(t);
            }
        }
        if (found.size() == 0) {
            return resp.errFind();
        }
        return resp.getFind(found);
    }

}
