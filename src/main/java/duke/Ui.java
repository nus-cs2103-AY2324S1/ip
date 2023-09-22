package duke;

import java.util.ArrayList;

import duke.messages.ErrorMessages;
import duke.messages.HelpMessages;
import duke.messages.Messages;
import duke.task.Task;

/**
 * Shows all appropriate messages to the user.
 */

public class Ui {

    /**
     * Shows the welcome message to the user
     * @return welcome message
     */
    public String showWelcome() {
        // Introduction Message
        return Messages.INTRODUCTION.getMessage();
    }

    /**
     * Shows the exit message to the user
     * @return exit message
     */
    public String showExitMessage() {
        return Messages.GOODBYE_MESSAGE.getMessage();
    }

    /**
     * Shows the list of the tasks to the user
     * @return a string representation of the current task list
     */
    public String showListMessage(TaskList taskList) {
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < taskList.getLengthOfTaskList(); i++) {
            if (i == taskList.getLengthOfTaskList() - 1) {
                tasks.append((i + 1)).append(". ").append(taskList.getTaskList().get(i));
            } else {
                tasks.append((i + 1)).append(". ").append(taskList.getTaskList().get(i)).append("\n");
            }
        }
        return Messages.LIST_COMMAND.getMessage() + "\n" + tasks;
    }

    /**
     * Shows the task that has been marked
     * @return String representation of marked task
     */
    public String showMarkMessage(Task task) {
        return Messages.MARK_COMMAND.getMessage() + "\n" + task;
    }

    /**
     * Shows the task that has been unmarked
     * @return String representation of unmarked task
     */
    public String showUnmarkMessage(Task task) {
        return Messages.UNMARK_COMMAND.getMessage() + "\n" + task;
    }

    /**
     * Shows the task that has been deleted
     * @return String representation of deleted task and updated number of tasks
     */
    public String showDeletedMessage(Task task, TaskList taskList) {
        return Messages.DELETED_COMMAND.getMessage() + "\n" + task + "\n" + showTaskListSize(taskList);
    }

    /**
     * Shows the task that has been added
     * @return String representation of added task and updated number of tasks
     */
    public String showAddedMessage(Task task, TaskList taskList) {
        return Messages.TASK_ADDED.getMessage() + "\n" + task + "\n" + showTaskListSize(taskList);
    }

    /**
     * Shows all tasks that has been found
     * @return String representation of found tasks
     */
    public String showFoundMessages(ArrayList<Task> taskList) {
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (i == taskList.size() - 1) {
                tasks.append((i + 1)).append(". ").append(taskList.get(i));
            } else {
                tasks.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");
            }
        }
        return Messages.FOUND_TASKS.getMessage() + "\n" + tasks;
    }

    /**
     * Shows the number of tasks currently in the list
     * @param taskList the current task list
     * @return the length of the task list.
     */
    public String showTaskListSize(TaskList taskList) {
        return ("Now you have " + taskList.getTaskList().size() + " tasks in the list.");
    }

    /**
     * Shows help message
     * @return String representation of help dialogue
     */
    public String showHelpDialogue() {
        return HelpMessages.HELP_HEADING.getMessage() + "\n" + HelpMessages.HELP_OPTION_1.getMessage() + "\n"
                + HelpMessages.HELP_OPTION_2.getMessage() + "\n" + HelpMessages.HELP_OPTION_3.getMessage()
                + "\n" + HelpMessages.IDENTIFY_HELP_OPTION.getMessage();
    }

    /**
     * Shows the answer of the help option user has chosen
     * @param answerOption option chosen by the user
     * @return the answer to the chosen option
     */
    public String showHelpAnswer(int answerOption) {
        if (answerOption == 1) {
            return HelpMessages.HELP_ANSWER_1.getMessage();
        } else if (answerOption == 2) {
            return HelpMessages.HELP_ANSWER_2.getMessage();
        } else if (answerOption == 3) {
            return HelpMessages.HELP_ANSWER_3.getMessage();
        } else {
            return ErrorMessages.INVALID_HELP_OPTION.getMessage();
        }
    }
}
