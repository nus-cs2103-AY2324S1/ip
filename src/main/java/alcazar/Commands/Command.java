package alcazar.Commands;

import alcazar.Exceptions.AlcazarException;
import alcazar.Exceptions.InvalidArgumentException;
import alcazar.Storage;
import alcazar.TaskList;

public abstract class Command {
    public abstract String execute(TaskList tasks,
                                   Storage storage) throws AlcazarException;
    public abstract boolean isExit();
}
