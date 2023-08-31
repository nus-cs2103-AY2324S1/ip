package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;
import duke.exception.InvalidDateException;
import duke.task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command{
    public DeadlineCommand(String fullCommand) {

        super(fullCommand);
    }
    @Override
    public void execute(TaskList tasks , Ui ui, Storage storage) {
        String[] words = this.fullCommand.split(" ", 2);
        if(words.length < 2) {
                    throw new InvalidArgumentException("deadline");
                } else {
                    String[] taskWithDeadline = words[1].split("/", 2);
                    String c = taskWithDeadline[0];
                    if(taskWithDeadline.length < 2) {
                        throw new InvalidArgumentException("deadline");
                    }
                    String[] splitTask =  taskWithDeadline[1].split(" ", 2);
                    if(splitTask.length < 2) {
                        throw new InvalidArgumentException("deadline");
                    } else {
                        try {
                            LocalDateTime deadline = LocalDateTime.parse(splitTask[1], Storage.dateTimeInputFormatter);
                            Deadline t = new Deadline(c, deadline);
                            tasks.addTask(t);
                        } catch(DateTimeParseException e) {
                            throw new InvalidDateException();
                        }
                    }
                }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
