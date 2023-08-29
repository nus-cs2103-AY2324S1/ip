package rayshawn.chatbot.commands;

import static rayshawn.chatbot.messages.Messages.LIST_COUNT_MESSAGE;

import rayshawn.chatbot.tasks.Task;
import rayshawn.chatbot.tasks.TaskList;

public class Command {
    protected TaskList taskList;
    private int targetIndex = -1;

    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    protected Command() {
    }

    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    protected Task getTask(int index) {
        return taskList.getTask(index);
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public int getIndex() {
        return this.targetIndex;
    }

    public String getTaskListCount() {
        return String.format(LIST_COUNT_MESSAGE ,taskList.count());
    }
}