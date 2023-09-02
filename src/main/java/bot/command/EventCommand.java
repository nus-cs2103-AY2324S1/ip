package bot.command;

import bot.exception.DateTimeParseBotException;
import bot.exception.FileErrorBotException;
import bot.task.TaskList;
import bot.task.Event;
import bot.storage.Storage;

import java.io.IOException;

public class EventCommand extends Command {

    private final TaskList taskList;
    private final Event event;

    public EventCommand(TaskList taskList, String taskDetail, String timeFrom,
                        String timeTo) throws DateTimeParseBotException {
        this.taskList = taskList;
        this.event = new Event(taskDetail, timeFrom, timeTo);
    }

    @Override
    public void execute() throws FileErrorBotException, IOException {
        this.taskList.add(this.event);
        Storage.save(this.taskList);
        System.out.println(this);
    }

    @Override
    public String toString() {
        if (this.taskList.length() <= 1) {
            return Command.SPACER + "\n" +
                    "Got it. I've added this bot.task:\n" +
                    this.event + "\n" +
                    "Now you have " + this.taskList.length() + " bot.task in the list.\n" +
                    Command.SPACER;
        } else {
            return Command.SPACER + "\n" +
                    "Got it. I've added this bot.task:\n" +
                    this.event + "\n" +
                    "Now you have " + this.taskList.length() + " tasks in the list.\n" +
                    Command.SPACER;
        }
    }
}

