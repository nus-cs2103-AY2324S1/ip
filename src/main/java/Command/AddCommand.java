package Command;

import java.time.LocalDate;

import Exception.*;
import Helper.*;
import Task.*;


public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    private String task;

    public AddCommand(int index, String task) {
        super(index);
        this.task = task;
    }

    private Task parseTask(String input) {
        if (input.startsWith("todo")) {
            String description = input.split("todo", 2)[1].strip();

            return new ToDo(description);
        } else if (input.startsWith("deadline")) {
            String[] parts = input.split("deadline", 2);
            String description = parts[1].split("/by", 2)[0].strip();
            String by = parts[1].split("/by", 2)[1].strip();
            LocalDate date = LocalDate.parse(by);

            return new Deadline(description, date);
        } else {
            String[] parts = input.split("event", 2);
            String description = parts[1].split("/from", 2)[0].strip();
            String time = parts[1].split("/from", 2)[1];
            String from = time.split("/to", 2)[0].strip();
            String to = time.split("/to", 2)[1].strip();

            return new Event(description, from, to);
        }
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String message = list.add(parseTask(this.task));
        ui.print(message);
    }
}
