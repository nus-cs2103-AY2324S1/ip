package duke.parser;

import duke.Duke;
import duke.tasks.TaskList;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.ToDoTask;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates the parser that parses through the user input received by the chatbot.
 * Handles the main logic of the chatbot, and communicates with the main class Duke and
 * its UI component to respond to user commands.
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
        default:
            return handleInvalidInput();
        }
    }

    private String handleExit(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length > 1) {
            return handleInvalidInput();
        } else {
            new Thread(() -> Duke.duke.exitApplication()).start();
            return ui.playGoodbye();
        }
    }

    private String handleList(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length > 1) {
            return handleInvalidInput();
        } else {
            return ui.printTaskList(Duke.duke.getTaskList());
        }
    }

    private String handleMarkOrUnmark(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length > 2) {
            return handleInvalidInput();
        } else {
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
                return ui.playExceptionMessage(Ui.ExceptionMessage.MarkCommand_NumberFormatException);
            } catch (IndexOutOfBoundsException e) {
                return ui.playExceptionMessage(Ui.ExceptionMessage.TaskList_IndexOutOfBoundsException);
            }
        }
    }

    private String handleAddToDoTask(String userInput) {
        if (!userInput.startsWith("todo ")) {
            return handleInvalidInput();
        }

        String taskDescription = userInput.substring(5);
        if (!taskDescription.isBlank()) {
            ToDoTask newToDoTask = new ToDoTask(taskDescription);
            return handleAddTask(newToDoTask);
        } else {
            return ui.playExceptionMessage(Ui.ExceptionMessage.AddTask_MissingDescription);
        }
    }

    private String handleAddDeadlineTask(String userInput) {
        if (!userInput.startsWith("deadline ")) {
            return handleInvalidInput();
        }

        String taskDescriptionAndDeadline = userInput.substring(9);
        if (!taskDescriptionAndDeadline.isBlank()) {
            String[] taskDescriptionSections = taskDescriptionAndDeadline.split(" /");
            if (taskDescriptionSections.length != 2) {
                return handleInvalidInput();
            } else {
                String taskDescription = taskDescriptionSections[0];
                if (taskDescription.isBlank()) {
                    return ui.playExceptionMessage(Ui.ExceptionMessage.AddTask_MissingDescription);
                }
                String taskDeadlineSegment = taskDescriptionSections[1];
                if (taskDeadlineSegment.startsWith("by ")) {
                    String taskDeadline = taskDeadlineSegment.substring(3);
                    if (!taskDeadline.isBlank()) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDateTime deadline = LocalDateTime.parse(taskDeadline, formatter);
                            DeadlineTask newDeadlineTask = new DeadlineTask(taskDescription, deadline);
                            return handleAddTask(newDeadlineTask);
                        } catch (DateTimeParseException e){
                            return ui.playExceptionMessage(Ui.ExceptionMessage.AddTask_DateTimeParseException);
                        }
                    } else {
                        return ui.playExceptionMessage(Ui.ExceptionMessage.AddTask_MissingDeadline);
                    }
                } else {
                    return handleInvalidInput();
                }
            }
        } else {
            return handleInvalidInput();
        }
    }

    private String handleAddEventTask(String userInput) {
        if (!userInput.startsWith("event ")) {
            return handleInvalidInput();
        }

        String taskDescriptionAndDuration = userInput.substring(6);
        if (!taskDescriptionAndDuration.isBlank()) {
            String[] taskDescriptionSections = taskDescriptionAndDuration.split(" /");
            if (taskDescriptionSections.length != 3) {
                return handleInvalidInput();
            } else {
                String taskDescription = taskDescriptionSections[0];
                if (taskDescription.isBlank()) {
                    return ui.playExceptionMessage(Ui.ExceptionMessage.AddTask_MissingDescription);
                }
                String taskFromSegment = taskDescriptionSections[1];
                String taskToSegment = taskDescriptionSections[2];
                if (taskFromSegment.startsWith("from ") && taskToSegment.startsWith("to ")) {
                    String taskFrom = taskFromSegment.substring(5);
                    String taskTo = taskToSegment.substring(3);
                    if (!taskFrom.isBlank() && !taskTo.isBlank()) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDateTime from = LocalDateTime.parse(taskFrom, formatter);
                            LocalDateTime to = LocalDateTime.parse(taskTo, formatter);
                            EventTask newEventTask = new EventTask(taskDescription, from, to);
                            return handleAddTask(newEventTask);
                        } catch (DateTimeParseException e){
                            return ui.playExceptionMessage(Ui.ExceptionMessage.AddTask_DateTimeParseException);
                        }
                    } else {
                        return ui.playExceptionMessage(Ui.ExceptionMessage.AddTask_MissingStartEndDate);
                    }
                } else {
                    return handleInvalidInput();
                }
            }
        } else {
            return handleInvalidInput();
        }
    }

    private String handleAddTask(Task newTask) {
        Duke.duke.getTaskList().addTask(newTask);
        return ui.playAddTask(newTask.toString(), Duke.duke.getTaskList().getTaskCount());
    }

    private String handleDeleteTask(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length > 2) {
            return handleInvalidInput();
        } else {
            try {
                TaskList taskList = Duke.duke.getTaskList();
                int index = Integer.parseInt(wordsInInput[1]) - 1;
                if (wordsInInput[0].equals("delete")) {
                    Task deletedTask = taskList.getTask(index);
                    taskList.removeTask(index);
                    return ui.playRemoveTask(deletedTask.toString(), taskList.getTaskCount());
                } else {
                    return handleInvalidInput();
                }
            } catch (NumberFormatException e) {
                return ui.playExceptionMessage(Ui.ExceptionMessage.DeleteCommand_NumberFormatException);
            } catch (IndexOutOfBoundsException e) {
                return ui.playExceptionMessage(Ui.ExceptionMessage.TaskList_IndexOutOfBoundsException);
            }
        }
    }

    private String handleFind(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length != 2) {
            return handleInvalidInput();
        } else {
            TaskList tasks = Duke.duke.getTaskList();
            TaskList matchingTasks = tasks.findTasksByKeyword(wordsInInput[1]);
            return ui.printKeywordSearchResults(matchingTasks);
        }
    }

    private String handleInvalidInput() {
        return ui.playExceptionMessage(Ui.ExceptionMessage.InvalidInput);
    }
}
