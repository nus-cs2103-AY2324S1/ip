package barbie.commands;

import java.util.ArrayList;

import barbie.Storage;
import barbie.Ui;
import barbie.exceptions.BarbieException;
import barbie.exceptions.BarbieListEmptyException;
import barbie.exceptions.BarbieTaskNumberException;
import barbie.types.Task;


public class DeleteCommand extends Command {
    int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
        this.isExit = false;
    }

    @Override
    public String run(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            return new BarbieListEmptyException().getMessage();
        }
        String taskToDel = taskList.get(taskNumber).toString();


        taskList.remove(this.taskNumber);
        Storage.deleteLine(taskNumber);
        return Ui.del() + "\n" + taskToDel;
    }
}
