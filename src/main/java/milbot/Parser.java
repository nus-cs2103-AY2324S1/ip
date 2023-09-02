package milbot;

import taskclasses.*;
import exception.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;


    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }
    /**
     * Parses the user input and performs corresponding actions based on the input.
     *
     * @param input The input provided by the user.
     * @return response from the chatbot.
     */
    public String parseInput(String input) {
        if (input.equals("bye")) {
            storage.saveTasksToFile(taskList);
            return ui.printGoodbyeMessage();
        } else if (input.equals("list")) {
            return ui.printTaskList(taskList);
        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            int index = -1;
            try {
                if (input.split(" ").length < 2) {
                    throw new NoTaskIndexException();
                }
                index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index < 0 || index >= taskList.getSize()) {
                    throw new InvalidTaskIndexException();
                }
            } catch (NoTaskIndexException e) {
                return ui.printErrorMessage(e.getMessage());
            } catch (InvalidTaskIndexException e) {
                return ui.printErrorMessage(e.getMessage());
            }
            if(index != -1) {
                Task task = taskList.getTask(index);
                if (input.startsWith("mark")) {
                    task.markAsDone();
                    return ui.printMarkTask(task);
                } else {
                    task.markAsUndone();
                    return ui.printUnmarkTask(task);
                }
            }
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            Task task;
            try {
                if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
                    throw new EmptyTaskException();
                }
            } catch (EmptyTaskException e) {
                return ui.printErrorMessage(e.getMessage());
            }
            if (input.startsWith("todo")) {
                task = new Todo(input.substring(5));
                taskList.addTask(task);
                return ui.printNewTask(taskList, task);
            } else if (input.startsWith("deadline")) {
                try {
                    if (!input.contains("/by") || input.trim().split("/by").length == 1) {
                        throw new InvalidDeadlineException();
                    }
                } catch (InvalidDeadlineException e) {
                    return ui.printErrorMessage(e.getMessage());
                }
                try {
                    LocalDate deadlineDate = LocalDate.parse(input.split("/")[1].substring(3).trim());
                    task = new Deadline(input.split("/")[0].substring(9),
                            deadlineDate);
                    taskList.addTask(task);
                    return ui.printNewTask(taskList, task);
                } catch (DateTimeParseException e) {
                    return ui.printErrorMessage(e.getMessage());
                }

            } else {
                try {
                    if (!input.contains("/from") || !input.contains("/to")) {
                        throw new InvalidEventException();
                    }
                } catch (InvalidEventException e) {
                    return ui.printErrorMessage(e.getMessage());
                }
                LocalDate fromDate = LocalDate.parse(input.split("/")[1].substring(5).trim());
                LocalDate toDate = LocalDate.parse(input.split("/")[2].substring(3).trim());
                task = new Event(input.split("/")[0].substring(6),
                        fromDate, toDate);
                taskList.addTask(task);
                return ui.printNewTask(taskList, task);
            }

        } else if (input.startsWith("delete")) {
            try {
                if (input.split(" ").length < 2) {
                    throw new NoTaskIndexException();
                }
            } catch (NoTaskIndexException e) {
                return ui.printErrorMessage(e.getMessage());
            }
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            try {
                if (index < 0 || index >= taskList.getSize()) {
                    throw new InvalidTaskIndexException();
                }
            } catch (InvalidTaskIndexException e) {
                return ui.printErrorMessage(e.getMessage());
            }
            Task task = taskList.getTask(index);
            taskList.removeTask(index);
            return ui.printRemoveTask(task, taskList);
        } else if(input.startsWith("find")) {
            try {
                if (input.trim().equals("find")) {
                    throw new EmptyFindQueryException();
                }
            } catch (EmptyFindQueryException e) {
                return ui.printErrorMessage(e.getMessage());
            }
            String taskQuery = input.split(" ")[1];
            TaskList tasksResult = new TaskList();
            for(Task task : taskList.getTaskList()) {
                if(task.toString().contains(taskQuery)) {
                    tasksResult.addTask(task);
                }
            }
            return ui.printSearchResult(tasksResult);
        }
        return ui.printUnknownMessage();

    }
}
