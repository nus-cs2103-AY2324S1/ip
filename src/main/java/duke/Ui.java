package duke;

import duke.messages.ErrorMessages;
import duke.messages.HelpMessages;
import duke.messages.Messages;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Shows all appropriate messages to the user.
 */

public class Ui {

    public String showWelcome() {
        // Introduction Message
        return Messages.INTRODUCTION.getMessage();
    }

    public String showExitMessage() {
        return Messages.GOODBYE_MESSAGE.getMessage();
    }

    public String showListMessage(TaskList taskList) {
        StringBuilder tasks = new StringBuilder();
        for (int i = 0 ; i < taskList.getLengthOfTaskList() ; i++){
            if (i == taskList.getLengthOfTaskList() - 1){
                tasks.append((i + 1)).append(". ").append(taskList.getTaskList().get(i));
            } else {
                tasks.append((i + 1)).append(". ").append(taskList.getTaskList().get(i)).append("\n");
            }
        }
        return Messages.LIST_COMMAND.getMessage() + "\n" + tasks ;
    }

    public String showMarkMessage(Task task) {
        return Messages.MARK_COMMAND.getMessage() + "\n" + task;
    }

    public String showUnmarkMessage(Task task) {
        return Messages.UNMARK_COMMAND.getMessage() + "\n" + task;
    }

    public String showDeletedMessage(Task task, TaskList taskList) {
        return Messages.DELETED_COMMAND.getMessage() + "\n" + task + "\n" + showTaskListSize(taskList);
    }

    public String showAddedMessage(Task task, TaskList taskList) {
        return Messages.TASK_ADDED.getMessage() + "\n" + task + "\n" + showTaskListSize(taskList);
    }

    public String showFoundMessages(ArrayList<Task> taskList) {
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (i == taskList.size() - 1){
                tasks.append((i + 1)).append(". ").append(taskList.get(i));
            } else {
                tasks.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");
            }
        }
        return Messages.FOUND_TASKS.getMessage() + "\n" + tasks;
    }

    public String showTaskListSize(TaskList taskList) {
        return ("Now you have " + taskList.getTaskList().size() + " tasks in the list.");
    }

    public String showHelpDialogue(){
        return HelpMessages.HELP_HEADING.getMessage() + "\n" + HelpMessages.HELP_OPTION_1.getMessage() + "\n"
                + HelpMessages.HELP_OPTION_2.getMessage() + "\n" + HelpMessages.HELP_OPTION_3.getMessage()
                + "\n" + HelpMessages.IDENTIFY_HELP_OPTION.getMessage();
    }

    public String showHelpAnswer(int answerOption){
        if (answerOption == 1){
            return HelpMessages.HELP_ANSWER_1.getMessage();
        } else if (answerOption == 2){
            return HelpMessages.HELP_ANSWER_2.getMessage();
        } else if (answerOption == 3){
            return HelpMessages.HELP_ANSWER_3.getMessage();
        } else {
            return ErrorMessages.INVALID_HELP_OPTION.getMessage();
        }
    }
}
