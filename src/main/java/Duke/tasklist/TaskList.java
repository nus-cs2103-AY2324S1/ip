package Duke.tasklist;

import Duke.exception.DukeException;
import Duke.storage.Storage;
import Duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList = new ArrayList<>();

    private final Storage storage;

    public TaskList(Storage storage) throws DukeException {
        this.storage = storage;
        LoadTaskList();
    }
    public void AddTask(Task task) {
        storage.AddLine(task.toSaveFormat());
        taskList.add(task);
    }

    public void RemoveTask(Task task) {
        storage.RemoveLine(taskList.indexOf(task) + 1);
        taskList.remove(task);
    }

    public void LoadTaskList() throws DukeException {
        taskList = new ArrayList<>();
        String line;
        int currentLine = 1;
        while(!(line = storage.GetLine(currentLine)).equals("")) {
            taskList.add(Task.Parse(line));
            currentLine++;
        }
    }

    public int Size() {
        return taskList.size();
    }

    public Task GetTask(int i) {
        return taskList.get(i);
    }
}
