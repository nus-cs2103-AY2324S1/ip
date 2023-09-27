package chatterbot.parser;

import chatterbot.data.TaskList;
import chatterbot.exceptions.InvalidDeadlineException;
import chatterbot.exceptions.NoTaskDescriptionException;
import chatterbot.storage.Storage;
import chatterbot.ui.Ui;

import java.io.IOException;

/**
 * Represents the interpretation of entered user inputs.
 */
public class Parser {

    /**
     * Evaluates entered user inputs and calls the corresponding method.
     * @param userMessage This is the entered user input.
     * @param ui This is what will be returned to the user.
     * @param storage This is where the file and list contents are edited.
     * @param taskList This is the list of tasks that have been added.
     */

    public static String evaluateCommand(String userMessage, Ui ui, Storage storage,
                                       TaskList taskList) {

        String response = "";

        assert userMessage != null && !userMessage.isEmpty() : "User message cannot be null or empty.";

        if (userMessage.toLowerCase().equals("bye")) {
            System.exit(0);
        } else if (userMessage.toLowerCase().equals("list")) {
            response = ui.showTaskList(taskList.getList());
        } else if (userMessage.startsWith("mark") && userMessage.length() > 5) {
            String toMark = userMessage.substring(5);
            try {
                taskList.setDone(Integer.parseInt(toMark) - 1, storage);
                response = ui.showMarked(toMark);
            } catch (IndexOutOfBoundsException e) {
                response = "No task to mark! Try an index within range instead.";
            }
        } else if (userMessage.startsWith("unmark") && userMessage.length() > 7) {
            String toUnmark = userMessage.substring(7);
            try {
                taskList.unsetDone(Integer.parseInt(toUnmark) - 1, storage);
                response = ui.showUnmarked(toUnmark);
            } catch (IndexOutOfBoundsException e) {
                response = "No task to unmark! Try an index within range instead.";
            }
        } else if (userMessage.startsWith("find") && userMessage.length() > 5) {
            String toFind = userMessage.substring(5);
            response = ui.showFoundTasks(toFind);
        } else {
            if (userMessage.startsWith("deadline")) {
                try {
                    response = taskList.initialiseDeadlineTask(userMessage, taskList, storage, ui);
                } catch (NoTaskDescriptionException | InvalidDeadlineException e) {
                    response = ui.showError(e);
                }
            } else if (userMessage.startsWith("todo")) {
                try {
                    response = taskList.initialiseTodoTask(userMessage, taskList, storage, ui);
                } catch (NoTaskDescriptionException e) {
                    response =  ui.showError(e);
                } catch (InvalidDeadlineException e) {
                    response = "Error! Task not added.";
                } catch (IOException e) {
                    response = "File was not found! Task not added. Check for typos and try again.";
                }
            } else if (userMessage.startsWith("event")) {
                try {
                    response = taskList.initialiseEventTask(userMessage, taskList, storage, ui);
                } catch (NoTaskDescriptionException e) {
                    response = ui.showError(e);
                } catch (InvalidDeadlineException e) {
                    response = "Error! Task not added. Check for typos and try again.";
                } catch (IOException e) {
                    response = "File was not found! Task not added.";
                }
            } else if (userMessage.startsWith("delete") && userMessage.length() > 7) {
                try {
                    response = ui.showDeleted(userMessage);
                    taskList.deleteTask(((Integer.parseInt(userMessage.substring(7))) - 1), storage);
                } catch (IndexOutOfBoundsException e) {
                    response = "No task to delete! Try an index within range instead.";
                }
            } else {
                response = ui.showUnknownCommand(userMessage);
            }
        }
        return response;
    }

    /**
     * Returns whether an entered string is an integer.
     * @param str This is the entered string.
     * @return boolean This is representative of whether the string is an integer or not.
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}