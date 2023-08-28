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
     */
    public void parseInput(String input) {
        if (input.equals("list")) {
            ui.printTaskList(taskList);
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
                ui.printErrorMessage(e.getMessage());
                return;
            } catch (InvalidTaskIndexException e) {
                ui.printErrorMessage(e.getMessage());
                return;
            }
            if(index != -1) {
                Task task = taskList.getTask(index);
                if (input.startsWith("mark")) {
                    task.markAsDone();
                    ui.printMarkTask(task);
                } else {
                    task.markAsUndone();
                    ui.printUnmarkTask(task);
                }
            }
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            Task task;
            try {
                if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
                    throw new EmptyTaskException();
                }
            } catch (EmptyTaskException e) {
                ui.printErrorMessage(e.getMessage());
                return;
            }
            if (input.startsWith("todo")) {
                task = new Todo(input.substring(5));
                taskList.addTask(task);
                ui.printNewTask(taskList, task);
            } else if (input.startsWith("deadline")) {
                try {
                    if (!input.contains("/by") || input.trim().split("/by").length == 1) {
                        throw new InvalidDeadlineException();
                    }
                } catch (InvalidDeadlineException e) {
                    ui.printErrorMessage(e.getMessage());
                    return;
                }
                try {
                    LocalDate deadlineDate = LocalDate.parse(input.split("/")[1].substring(3).trim());
                    task = new Deadline(input.split("/")[0].substring(9),
                            deadlineDate);
                    taskList.addTask(task);
                    ui.printNewTask(taskList, task);
                } catch (DateTimeParseException e) {
                    ui.printErrorMessage(e.getMessage());
                }

            } else {
                try {
                    if (!input.contains("/from") || !input.contains("/to")) {
                        throw new InvalidEventException();
                    }
                } catch (InvalidEventException e) {
                    ui.printErrorMessage(e.getMessage());
                    return;
                }
                LocalDate fromDate = LocalDate.parse(input.split("/")[1].substring(5).trim());
                LocalDate toDate = LocalDate.parse(input.split("/")[2].substring(3).trim());
                task = new Event(input.split("/")[0].substring(6),
                        fromDate, toDate);
                taskList.addTask(task);
                ui.printNewTask(taskList, task);
            }

        } else if (input.startsWith("delete")) {
            try {
                if (input.split(" ").length < 2) {
                    throw new NoTaskIndexException();
                }
            } catch (NoTaskIndexException e) {
                ui.printErrorMessage(e.getMessage());
                return;
            }
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            try {
                if (index < 0 || index >= taskList.getSize()) {
                    throw new InvalidTaskIndexException();
                }
            } catch (InvalidTaskIndexException e) {
                ui.printErrorMessage(e.getMessage());
                return;
            }
            Task task = taskList.getTask(index);
            taskList.removeTask(index);
            ui.printRemoveTask(task, taskList);
        } else {
            ui.printUnknownMessage();
        }
    }
}
