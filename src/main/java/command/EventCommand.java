package command;

import task.TaskList;
import task.Event;

public class EventCommand extends Command {

    private final TaskList taskList;
    private final Event event;

    public EventCommand(TaskList taskList, String taskDetail, String timeFrom, String timeTo) {
        this.taskList = taskList;
        this.event = new Event(taskDetail, timeFrom, timeTo);
    }

    public void execute() {
        this.taskList.add(this.event);
        System.out.println(this);
    }

    @Override
    public String toString() {
        if (this.taskList.length() <= 1) {
            return Command.SPACER + "\n" +
                    "Got it. I've added this task:\n" +
                    this.event + "\n" +
                    "Now you have " + this.taskList.length() + " task in the list.\n" +
                    Command.SPACER;
        } else {
            return Command.SPACER + "\n" +
                    "Got it. I've added this task:\n" +
                    this.event + "\n" +
                    "Now you have " + this.taskList.length() + " tasks in the list.\n" +
                    Command.SPACER;
        }
    }
}

