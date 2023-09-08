package duke.stubs;

import duke.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;

public class TaskListStub extends TaskList {
    private ArrayList<Task> listStub;
    private UiStub uiStub;
    private StorageStub storageStub;

    public TaskListStub(ArrayList<Task> list, StorageStub storage, UiStub ui) {
        super(list, storage, ui);
        this.listStub = list;
        this.uiStub = ui;
        this.storageStub = storage;
    }

    @Override
    public void addTask(Task task) {
        this.listStub.add(task);
        this.storageStub.updateFile(listStub);
        this.uiStub.showTaskAdded(task, this.listStub.size());
    }

    @Override
    public String listTasks(ArrayList<Task> tasks) {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            Task task = tasks.get(i);
            result += index + ". " + task.toString() + "\n";
        }
        return result;
    }
}
