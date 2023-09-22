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

    /**
     * Creates an instance of EventCommand object
     *
     * @param taskList the list of tasks
     * @param taskDetail task description
     * @param timeFrom start time of task formatted 'd/MM/yyyy HH:mm'
     * @param timeTo end time of task formatted 'd/MM/yyyy HH:mm'
     * @throws DateTimeParseBotException if timeFrom and timeTo argument is not formatted correctly
     */
    public EventCommand(TaskList taskList, String taskDetail, String timeFrom,
                        String timeTo) throws DateTimeParseBotException {
        this.taskList = taskList;
        this.event = new Event(taskDetail, timeFrom, timeTo);
    }

    /**
     * Execute a series of instructions specific to creating adding an Event object
     *
     * @throws FileErrorBotException if the file or directory is missing or corrupted
     * @throws IOException if an I/O error occurred
     */
    @Override
    public void execute() throws FileErrorBotException, IOException {
        this.taskList.add(this.event);
        Storage.save(this.taskList);
        System.out.println(this);
    }

    /**
     * Returns a String representation of EventCommand object
     *
     * @return String representation of EventCommand object
     */
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

