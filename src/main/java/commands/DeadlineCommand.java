package commands;

import tasks.TaskList;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    private String description;
    private String endTime;

    public DeadlineCommand(String description, String endTime) {
        this.description = description;
        this.endTime = endTime;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.addDeadlineTask(description, endTime);
    }
}
