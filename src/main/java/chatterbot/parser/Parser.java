package chatterbot.parser;

import chatterbot.storage.Storage;
import chatterbot.ui.Ui;
import chatterbot.data.*;

import java.io.IOException;
import java.util.ArrayList;

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
            response = ui.showGoodbyeMessage();
        } else if (userMessage.toLowerCase().equals("list")) {
            response = ui.showTaskList(taskList.getList());
        } else if (userMessage.startsWith("mark") && isInteger(userMessage.substring(5))) {
            String toMark = userMessage.substring(5);
            taskList.setDone(Integer.parseInt(toMark) - 1, storage);
            response = ui.showMarked(toMark);
        } else if (userMessage.startsWith("unmark")) {
            String toUnmark = userMessage.substring(7);
            taskList.unsetDone(Integer.parseInt(toUnmark) - 1, storage);
            response = ui.showUnmarked(toUnmark);
        } else if (userMessage.startsWith("find")) {
            String toFind = userMessage.substring(5);
            response = ui.showFoundTasks(toFind);
        } else {
            if (userMessage.startsWith("deadline")) {
                try {
                    if (userMessage.length() <= 9) {
                        throw new IllegalArgumentException("No task description");
                    }
                    response = taskList.initialiseDeadlineTask(userMessage, taskList, storage, ui);
                } catch (IllegalArgumentException e) {
                    response = "OOPS!!! Invalid input!";
                }
            } else if (userMessage.startsWith("todo")) {
                try {
                    if (userMessage.length() <= 5) {
                        throw new IllegalArgumentException("No task description");
                    }
                    response = taskList.initialiseTodoTask(userMessage, taskList, storage, ui);
                } catch (IllegalArgumentException e) {
                    System.out.println("OOPS!!! Invalid input! " + e.getMessage() + ".");
                }
            } else if (userMessage.startsWith("event")) {
                try {
                    if (userMessage.length() <= 6) {
                        throw new IllegalArgumentException("No task description");
                    }
                    return taskList.initialiseEventTask(userMessage, taskList, storage, ui);
                } catch (IllegalArgumentException e) {
                    System.out.println("OOPS!!! Invalid input!");
                }
            } else if (userMessage.startsWith("delete") && isInteger(userMessage.substring(7))) {
                response = ui.showDeleted(userMessage);
                taskList.deleteTask(((Integer.parseInt(userMessage.substring(7))) - 1), storage);
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