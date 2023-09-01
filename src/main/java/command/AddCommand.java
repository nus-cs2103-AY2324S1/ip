package Command;

import duke.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AddCommand extends Command {
    public static final String COMMAND_ADD_TODO = "todo";
    public static final String COMMAND_ADD_DEADLINE = "deadline";
    public static final String COMMAND_ADD_EVENT = "event";

    private static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HHmm";
    public static final DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);

    public AddCommand(ArrayList<String> params) {
        super(params);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws StorageException {
        switch (super.params.get(0)) {
            case AddCommand.COMMAND_ADD_TODO:
                ToDo newTodo = new ToDo(params.get(1));
                Task task = tasks.add(newTodo);
                ui.printTaskAddedMessage(task, tasks.getTaskCount());
                tasks.saveState(storage);
                break;
            case AddCommand.COMMAND_ADD_DEADLINE:
                Deadline newDeadline = new Deadline(params.get(1),
                        LocalDateTime.parse(params.get(2), dateTimeInputFormatter));
                task = tasks.add(newDeadline);
                ui.printTaskAddedMessage(task, tasks.getTaskCount());
                tasks.saveState(storage);
                break;
            case AddCommand.COMMAND_ADD_EVENT:
                Event newEvent = new Event(params.get(1),
                        LocalDateTime.parse(params.get(2), dateTimeInputFormatter),
                        LocalDateTime.parse(params.get(3), dateTimeInputFormatter));
                task = tasks.add(newEvent);
                ui.printTaskAddedMessage(task, tasks.getTaskCount());
                tasks.saveState(storage);
                break;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
