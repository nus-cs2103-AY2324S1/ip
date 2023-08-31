package duke;

import duke.messages.Messages;

/**
 * Shows all appropriate messages to the user.
 */

public class Ui {

    public void showWelcome(){
        // Introduction Message
        System.out.println(Messages.INTRODUCTION.getMessage());
        System.out.println();
    }

    public void showListMessage(){
        System.out.println(Messages.LIST_COMMAND.getMessage());
    }

    public void showMarkMessage(){
        System.out.println(Messages.MARK_COMMAND.getMessage());
    }

    public void showUnmarkMessage(){
        System.out.println(Messages.UNMARK_COMMAND.getMessage());
    }

    public void showDeletedMessage(){
        System.out.println(Messages.DELETED_COMMAND.getMessage());
    }

    public void showAddedMessage() {
        System.out.println(Messages.TASK_ADDED.getMessage());
    }

    public void showTaskListSize(TaskList taskList){
        System.out.println("Now you have " + taskList.getTaskList().size() + " tasks in the list.");
    }
}
