package command;

import enums.CommandType;
import exception.BobException;
import task.*;
import ui.TextUi;
import storage.StorageFile;

public class AddCommand extends Command {

    CommandType command;
    String description, startDate, endDate;
    public AddCommand(CommandType command, String description, String startDate, String endDate) {
        this.command = command;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void execute(TaskList taskList, StorageFile storageFile, TextUi ui) throws BobException {
        switch (command) {
            case TODO:
                taskList.addTask(new Todo(this.description));
                break;
            case DEADLINE:
                taskList.addTask(new Deadline(this.description, this.endDate));
                break;
            case EVENT:
                taskList.addTask(new Event(this.description, this.startDate, this.endDate));
                break;
            default:
        }
        //TODO: Ui Print Added Task Message
        storageFile.saveTasks(taskList);
    }
}
