package duke.command;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
public abstract class Command {
	// only inheritors can use
	public abstract void execute(TaskList t, Ui u, Storage s);

}
