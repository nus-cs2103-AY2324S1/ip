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
    public void parse(String userInput) {
        String[] words = userInput.split(" ");
        if (words == null || words.length < 1) {
            handleInvalidInput();
            return;
        }
        String prefix = words[0];

        switch (prefix) {
        case "bye":
            handleExit(userInput);
            break;
        case "list":
            handleList(userInput);
            break;
        case "unmark":
            //Fallthrough
        case "mark":
            handleMarkOrUnmark(userInput);
            break;
        case "todo":
            handleAddToDoTask(userInput);
            break;
        case "deadline":
            handleAddDeadlineTask(userInput);
            break;
        case "event":
            handleAddEventTask(userInput);
            break;
        case "delete":
            handleDeleteTask(userInput);
            break;
        default:
            handleInvalidInput();
            break;
        }
    }

    private void handleExit(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length > 1) {
            handleInvalidInput();
        } else {
            Duke.stopReceivingInput();
        }
    }

    private void handleList(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length > 1) {
            handleInvalidInput();
        } else {
            ui.printTaskList(Duke.getTaskList());
        }
    }

    private void handleMarkOrUnmark(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length > 2) {
            handleInvalidInput();
        } else {
            try {
                TaskList taskList = Duke.getTaskList();
                int index = Integer.parseInt(wordsInInput[1]) - 1;
                if (wordsInInput[0].equals("mark")) {
                    taskList.markTaskAsDone(index, true);
                    ui.playTaskComplete(taskList.getTaskAsString(index));
                } else if (wordsInInput[0].equals("unmark")) {
                    taskList.markTaskAsDone(index, false);
                    ui.playTaskIncomplete(taskList.getTaskAsString(index));
                }
            } catch (NumberFormatException e) {
                ui.playExceptionMessage(Ui.ExceptionMessage.MarkCommand_NumberFormatException);
            } catch (IndexOutOfBoundsException e) {
                ui.playExceptionMessage(Ui.ExceptionMessage.TaskList_IndexOutOfBoundsException);
            }
        }
    }

    private void handleAddToDoTask(String userInput) {
        if (!userInput.startsWith("todo ")) {
            handleInvalidInput();
            return;
        }

        String taskDescription = userInput.substring(5);
        if (!taskDescription.isBlank()) {
            ToDoTask newToDoTask = new ToDoTask(taskDescription);
            handleAddTask(newToDoTask);
        } else {
            ui.playExceptionMessage(Ui.ExceptionMessage.AddTask_MissingDescription);
        }
    }

    private void handleAddDeadlineTask(String userInput) {
        if (!userInput.startsWith("deadline ")) {
            handleInvalidInput();
            return;
        }

        String taskDescriptionAndDeadline = userInput.substring(9);
        if (!taskDescriptionAndDeadline.isBlank()) {
            String[] taskDescriptionSections = taskDescriptionAndDeadline.split(" /");
            if (taskDescriptionSections.length != 2) {
                handleInvalidInput();
            } else {
                String taskDescription = taskDescriptionSections[0];
                if (taskDescription.isBlank()) {
                    ui.playExceptionMessage(Ui.ExceptionMessage.AddTask_MissingDescription);
                    return;
                }
                String taskDeadlineSegment = taskDescriptionSections[1];
                if (taskDeadlineSegment.startsWith("by ")) {
                    String taskDeadline = taskDeadlineSegment.substring(3);
                    if (!taskDeadline.isBlank()) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDateTime deadline = LocalDateTime.parse(taskDeadline, formatter);
                            DeadlineTask newDeadlineTask = new DeadlineTask(taskDescription, deadline);
                            handleAddTask(newDeadlineTask);
                        } catch (DateTimeParseException e){
                            ui.playExceptionMessage(Ui.ExceptionMessage.AddTask_DateTimeParseException);
                        }
                    } else {
                        ui.playExceptionMessage(Ui.ExceptionMessage.AddTask_MissingDeadline);
                    }
                } else {
                    handleInvalidInput();
                }
            }
        } else {
            handleInvalidInput();
        }
    }

    private void handleAddEventTask(String userInput) {
        if (!userInput.startsWith("event ")) {
            handleInvalidInput();
            return;
        }

        String taskDescriptionAndDuration = userInput.substring(6);
        if (!taskDescriptionAndDuration.isBlank()) {
            String[] taskDescriptionSections = taskDescriptionAndDuration.split(" /");
            if (taskDescriptionSections.length != 3) {
                handleInvalidInput();
            } else {
                String taskDescription = taskDescriptionSections[0];
                if (taskDescription.isBlank()) {
                    ui.playExceptionMessage(Ui.ExceptionMessage.AddTask_MissingDescription);
                    return;
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
                            handleAddTask(newEventTask);
                        } catch (DateTimeParseException e){
                            ui.playExceptionMessage(Ui.ExceptionMessage.AddTask_DateTimeParseException);
                        }
                    } else {
                        ui.playExceptionMessage(Ui.ExceptionMessage.AddTask_MissingStartEndDate);
                    }
                } else {
                    handleInvalidInput();
                }
            }
        } else {
            handleInvalidInput();
        }
    }

    private void handleAddTask(Task newTask) {
        Duke.getTaskList().addTask(newTask);
        ui.playAddTask(newTask.toString(), Duke.getTaskList().getTaskCount());
    }

    private void handleDeleteTask(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length > 2) {
            handleInvalidInput();
        } else {
            try {
                TaskList taskList = Duke.getTaskList();
                int index = Integer.parseInt(wordsInInput[1]) - 1;
                if (wordsInInput[0].equals("delete")) {
                    Task deletedTask = taskList.getTask(index);
                    taskList.removeTask(index);
                    ui.playRemoveTask(deletedTask.toString(), taskList.getTaskCount());
                } else {
                    handleInvalidInput();
                }
            } catch (NumberFormatException e) {
                ui.playExceptionMessage(Ui.ExceptionMessage.DeleteCommand_NumberFormatException);
            } catch (IndexOutOfBoundsException e) {
                ui.playExceptionMessage(Ui.ExceptionMessage.TaskList_IndexOutOfBoundsException);
            }
        }
    }

    private void handleInvalidInput() {
        ui.playExceptionMessage(Ui.ExceptionMessage.InvalidInput);
    }
}
