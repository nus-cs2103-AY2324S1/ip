import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    public EventCommand(String fullCommand) {

        super(fullCommand);
    }
    @Override
    public void execute(TaskList tasks ,Ui ui, Storage storage) {
        String[] words = this.fullCommand.split(" ", 2);
        if(words.length < 2) {
            throw new InvalidArgumentException("event");
        } else {
            String[] splitCommand = words[1].split("/", 2);
            if(splitCommand.length < 2) {
                throw new InvalidArgumentException("event");
            }
            String c = splitCommand[0];
            String[] splitDeadline = splitCommand[1].split("/", 2);
            if(splitDeadline.length < 2) {
                throw new InvalidArgumentException("event");
            } else {
                if(splitDeadline[0].split(" ", 2).length < 2
                        || splitDeadline[1].split(" ", 2).length < 2) {
                    throw new InvalidArgumentException("event");
                } else {
                    try {
                        LocalDateTime from = LocalDateTime.parse(splitDeadline[0].split(" ", 2)[1].strip(), Storage.dateTimeInputFormatter);
                        LocalDateTime to = LocalDateTime.parse(splitDeadline[1].split(" ", 2)[1].strip(), Storage.dateTimeInputFormatter);
                        Event t = new Event(c, from, to);
                        tasks.addTask(t);
                    } catch(DateTimeParseException e) {
                        throw new InvalidDateException();
                    }
                }
            }
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
