package brandon.chatbot.commands;

import brandon.chatbot.tasks.TaskList;

public class Command {

    protected TaskList tasks;
    public Command(TaskList tasks) {
        this.tasks = tasks;
    }

    protected Command() {

    }

    public CommandResult execute() throws Exception {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }
}
