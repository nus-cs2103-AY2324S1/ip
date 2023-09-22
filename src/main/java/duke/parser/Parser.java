package duke.parser;

import duke.Duke;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDoTask;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates the parser that parses through the user input received by the chatbot.
 * Handles the main logic of the chatbot, and communicates with the UI component
 * to respond to user commands.
 *
 * @author Wu Jingya
 */
public class Parser {
    private Ui ui;

    /**
     * Constructs a Parser object connected to the specified Ui object.
     *
     * @param ui The Ui object the Parser is connected to.
     * @see Ui
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Parses through the given user input string.
     * Calls other class methods to respond to the user input accordingly.
     *
     * @param userInput The user input string.
     */
    public String parse(String userInput) {
        String[] words = userInput.split(" ");
        if (words == null || words.length < 1) {
            return handleInvalidInput();
        }

        String prefix = words[0];
        switch (prefix) {
        case "bye":
            return handleExit(userInput);
        case "list":
            return handleList(userInput);
        case "unmark":
            //Fallthrough
        case "mark":
            return handleMarkOrUnmark(userInput);
        case "todo":
            return handleAddToDoTask(userInput);
        case "deadline":
            return handleAddDeadlineTask(userInput);
        case "event":
            return handleAddEventTask(userInput);
        case "delete":
            return handleDeleteTask(userInput);
        case "find":
            return handleFind(userInput);
        case "sort":
            return handleSort(userInput);
        default:
            return handleInvalidInput();
        }
    }

    private String handleExit(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length > 1) {
            return handleInvalidInput();
        } else {
            assert wordsInInput.length == 1 && wordsInInput[0].equals("bye") : "bye: invalid input";
            new Thread(() -> Duke.duke.exitApplication()).start();
            return ui.playGoodbye();
        }
    }

    private String handleList(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length > 1) {
            return handleInvalidInput();
        } else {
            assert wordsInInput.length == 1 && wordsInInput[0].equals("list") : "list: invalid input";
            return ui.printTaskList(Duke.duke.getTaskList());
        }
    }

    private String handleMarkOrUnmark(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length > 2) {
            return handleInvalidInput();
        }

        assert wordsInInput.length == 2 : "mark/unmark: invalid input";
        try {
            TaskList taskList = Duke.duke.getTaskList();
            int index = Integer.parseInt(wordsInInput[1]) - 1;
            if (wordsInInput[0].equals("mark")) {
                taskList.markTaskAsDone(index, true);
                return ui.playTaskComplete(taskList.getTaskAsString(index));
            } else if (wordsInInput[0].equals("unmark")) {
                taskList.markTaskAsDone(index, false);
                return ui.playTaskIncomplete(taskList.getTaskAsString(index));
            }
            return handleInvalidInput();
        } catch (NumberFormatException e) {
            return ui.playExceptionMessage(Ui.ExceptionMessage.MARK_COMMAND_NUMBER_FORMAT_EXCEPTION);
        } catch (IndexOutOfBoundsException e) {
            return ui.playExceptionMessage(Ui.ExceptionMessage.TASK_LIST_INDEX_OUT_OF_BOUNDS_EXCEPTION);
        }
    }

    private String handleAddToDoTask(String userInput) {
        if (!userInput.startsWith("todo ")) {
            return handleInvalidInput();
        }

        String taskDescription = userInput.substring(5);
        if (taskDescription.isBlank()) {
            return ui.playExceptionMessage(Ui.ExceptionMessage.ADD_TASK_MISSING_DESCRIPTION);
        }

        ToDoTask newToDoTask = new ToDoTask(taskDescription);
        return handleAddTask(newToDoTask);
    }

    private String handleAddDeadlineTask(String userInput) {
        if (!userInput.startsWith("deadline ")) {
            return handleInvalidInput();
        }

        String taskDescriptionAndDeadline = userInput.substring(9);
        if (taskDescriptionAndDeadline.isBlank()) {
            return handleInvalidInput();
        }

        String[] taskDescriptionSections = taskDescriptionAndDeadline.split(" /");
        if (taskDescriptionSections.length != 2) {
            return handleInvalidInput();
        }

        String taskDescription = taskDescriptionSections[0];
        if (taskDescription.isBlank()) {
            return ui.playExceptionMessage(Ui.ExceptionMessage.ADD_TASK_MISSING_DESCRIPTION);
        }

        String taskDeadlineSegment = taskDescriptionSections[1];
        if (!taskDeadlineSegment.startsWith("by ")) {
            return handleInvalidInput();
        }

        String taskDeadline = taskDeadlineSegment.substring(3);
        if (taskDeadline.isBlank()) {
            return ui.playExceptionMessage(Ui.ExceptionMessage.ADD_TASK_MISSING_DEADLINE);
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime deadline = LocalDateTime.parse(taskDeadline, formatter);
            DeadlineTask newDeadlineTask = new DeadlineTask(taskDescription, deadline);
            return handleAddTask(newDeadlineTask);
        } catch (DateTimeParseException e) {
            return ui.playExceptionMessage(Ui.ExceptionMessage.ADD_TASK_DATE_TIME_PARSE_EXCEPTION);
        }
    }

    private String handleAddEventTask(String userInput) {
        if (!userInput.startsWith("event ")) {
            return handleInvalidInput();
        }

        String taskDescriptionAndDuration = userInput.substring(6);
        if (taskDescriptionAndDuration.isBlank()) {
            return handleInvalidInput();
        }

        String[] taskDescriptionSections = taskDescriptionAndDuration.split(" /");
        if (taskDescriptionSections.length != 3) {
            return handleInvalidInput();
        }

        String taskDescription = taskDescriptionSections[0];
        if (taskDescription.isBlank()) {
            return ui.playExceptionMessage(Ui.ExceptionMessage.ADD_TASK_MISSING_DESCRIPTION);
        }

        String taskFromSegment = taskDescriptionSections[1];
        String taskToSegment = taskDescriptionSections[2];
        if (!taskFromSegment.startsWith("from ") || !taskToSegment.startsWith("to ")) {
            return handleInvalidInput();
        }

        String taskFrom = taskFromSegment.substring(5);
        String taskTo = taskToSegment.substring(3);
        if (taskFrom.isBlank() || taskTo.isBlank()) {
            return ui.playExceptionMessage(Ui.ExceptionMessage.ADD_TASK_MISSING_START_END_DATE);
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime from = LocalDateTime.parse(taskFrom, formatter);
            LocalDateTime to = LocalDateTime.parse(taskTo, formatter);
            EventTask newEventTask = new EventTask(taskDescription, from, to);
            return handleAddTask(newEventTask);
        } catch (DateTimeParseException e) {
            return ui.playExceptionMessage(Ui.ExceptionMessage.ADD_TASK_DATE_TIME_PARSE_EXCEPTION);
        }
    }

    private String handleAddTask(Task newTask) {
        TaskList taskList = Duke.duke.getTaskList();
        taskList.addTask(newTask);

        String newTaskString = newTask.toString();
        int taskCount = Duke.duke.getTaskList().getTaskCount();

        return ui.playAddTask(newTaskString, taskCount);
    }

    private String handleDeleteTask(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length > 2) {
            return handleInvalidInput();
        }

        try {
            TaskList taskList = Duke.duke.getTaskList();
            int index = Integer.parseInt(wordsInInput[1]) - 1;
            if (!wordsInInput[0].equals("delete")) {
                return handleInvalidInput();
            }

            Task deletedTask = taskList.getTask(index);
            taskList.removeTask(index);
            return ui.playRemoveTask(deletedTask.toString(), taskList.getTaskCount());
        } catch (NumberFormatException e) {
            return ui.playExceptionMessage(Ui.ExceptionMessage.DELETE_COMMAND_NUMBER_FORMAT_EXCEPTION);
        } catch (IndexOutOfBoundsException e) {
            return ui.playExceptionMessage(Ui.ExceptionMessage.TASK_LIST_INDEX_OUT_OF_BOUNDS_EXCEPTION);
        }
    }

    private String handleFind(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length != 2) {
            return handleInvalidInput();
        }

        TaskList tasks = Duke.duke.getTaskList();
        TaskList matchingTasks = tasks.findTasksByKeyword(wordsInInput[1]);
        return ui.printKeywordSearchResults(matchingTasks);
    }

    private String handleSort(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length > 2) {
            return handleInvalidInput();
        }

        if (wordsInInput.length < 2) {
            return ui.playExceptionMessage(Ui.ExceptionMessage.SORT_TASKS_MISSING_SORT_ORDER);
        }

        assert wordsInInput.length == 2 && wordsInInput[0].equals("sort") : "sort: invalid input";
        TaskList tasks = Duke.duke.getTaskList();
        String sortOrderString = wordsInInput[1];
        switch  (sortOrderString) {
        case "description":
            tasks.sort(TaskList.SortOrder.TASK_DESCRIPTION);
            return ui.printSortedList(tasks, TaskList.SortOrder.TASK_DESCRIPTION);
        case "date":
            tasks.sort(TaskList.SortOrder.TASK_DATE);
            return ui.printSortedList(tasks, TaskList.SortOrder.TASK_DATE);
        case "type":
            tasks.sort(TaskList.SortOrder.TASK_TYPE);
            return ui.printSortedList(tasks, TaskList.SortOrder.TASK_TYPE);
        default:
            return ui.playExceptionMessage(Ui.ExceptionMessage.SORT_TASKS_INVALID_SORT_ORDER);
        }
    }

    private String handleInvalidInput() {
        return ui.playExceptionMessage(Ui.ExceptionMessage.INVALID_INPUT);
    }
}
