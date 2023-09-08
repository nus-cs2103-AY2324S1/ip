package milbot;

import taskclasses.*;
import exception.EmptyFindQueryException;
import exception.EmptyTaskException;
import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidTaskIndexException;
import exception.NoTaskIndexException;

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
            return executeByeCommand();
        } else if (input.equals("list")) {
            return executeListCommand();
        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            return executeMarkCommand(input);
        } else if (input.startsWith("todo")) {
            return executeTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return executeDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return executeEventCommand(input);
        } else if (input.startsWith("delete")) {
            return executeDeleteCommand(input);
        } else if(input.startsWith("find")) {
            return executeFindCommand(input);
        }
        return ui.printUnknownMessage();
    }

    public String executeByeCommand() {
        storage.saveTasksToFile(taskList);
        return ui.printGoodbyeMessage();
    }

    public String executeListCommand() {
        return ui.printTaskList(taskList);
    }

    public String executeMarkCommand(String input) {
        try {
            int index = extractTaskIndex(input);
            Task task = taskList.getTask(index);
            if (input.startsWith("mark")) {
                task.markAsDone();
                return ui.printMarkTask(task);
            } else {
                task.markAsUndone();
                return ui.printUnmarkTask(task);
            }
        } catch (NoTaskIndexException e) {
            return ui.printErrorMessage(e.getMessage());
        } catch (InvalidTaskIndexException e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }

    private int extractTaskIndex(String input) throws NoTaskIndexException, InvalidTaskIndexException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new NoTaskIndexException();
        }

        int index = Integer.parseInt(parts[1]) - 1;
        if (index < 0 || index >= taskList.getSize()) {
            throw new InvalidTaskIndexException();
        }

        return index;
    }

    public String executeTodoCommand(String input) {
        try {
            if (input.equals("todo")) {
                throw new EmptyTaskException();
            }
        } catch (EmptyTaskException e) {
            return ui.printErrorMessage(e.getMessage());
        }

        Todo task = new Todo(input.substring(5));
        taskList.addTask(task);

        return ui.printNewTask(taskList, task);
    }

    public String executeDeadlineCommand(String input) {
        try {
            if (input.equals("deadline")) {
                throw new EmptyTaskException();
            }
            if (!input.contains("/by") || input.trim().split("/by").length == 1) {
                throw new InvalidDeadlineException();
            }
        } catch (EmptyTaskException e) {
            return ui.printErrorMessage(e.getMessage());
        } catch (InvalidDeadlineException e) {
            return ui.printErrorMessage(e.getMessage());
        }

        try {
            LocalDate deadlineDate = LocalDate.parse(input.split("/")[1].substring(3).trim());
            Deadline deadline = new Deadline(input.split("/")[0].substring(9),
                    deadlineDate);
            taskList.addTask(deadline);
            return ui.printNewTask(taskList, deadline);
        } catch (DateTimeParseException e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }

    public String executeEventCommand(String input) {
        try {
            if (input.equals("event")) {
                throw new EmptyTaskException();
            }
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new InvalidEventException();
            }
        } catch (EmptyTaskException e) {
            return ui.printErrorMessage(e.getMessage());
        } catch (InvalidEventException e) {
            return ui.printErrorMessage(e.getMessage());
        }

        LocalDate fromDate = LocalDate.parse(input.split("/")[1].substring(5).trim());
        LocalDate toDate = LocalDate.parse(input.split("/")[2].substring(3).trim());
        Event event = new Event(input.split("/")[0].substring(6),
                fromDate, toDate);
        taskList.addTask(event);

        return ui.printNewTask(taskList, event);
    }

    public String executeDeleteCommand(String input) {
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
    }

    public String executeFindCommand(String input) {
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
}
