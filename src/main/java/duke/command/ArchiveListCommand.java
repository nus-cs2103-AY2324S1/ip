package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class ArchiveListCommand extends Command {


	@Override
	public String execute(TaskList taskList, Ui ui, Storage storage) {
			TaskList archiveList = new TaskList(storage.getArchiveList());
			System.out.println(archiveList.listAsString(ui));
			return archiveList.listAsString(ui);
	}
}
