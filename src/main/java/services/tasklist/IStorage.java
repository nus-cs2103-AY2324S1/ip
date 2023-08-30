package services.tasklist;

import services.bizerrors.InvalidArgumentException;
import services.bizerrors.ReadFromFileException;
import services.bizerrors.SaveToFileException;
import services.tasklist.tasks.Task;

import java.util.List;

public interface IStorage {
    void save(List<Task> taskList) throws SaveToFileException;
    List<Task> load() throws ReadFromFileException, InvalidArgumentException;
}
