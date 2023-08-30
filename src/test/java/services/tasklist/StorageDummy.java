package services.tasklist;

import services.bizerrors.InvalidArgumentException;
import services.bizerrors.ReadFromFileException;
import services.bizerrors.SaveToFileException;
import services.tasklist.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class StorageDummy implements IStorage {

    public StorageDummy() {
    }

    @Override
    public void save(List<Task> taskList) throws SaveToFileException {
    }

    @Override
    public List<Task> load() throws ReadFromFileException, InvalidArgumentException {
        return new ArrayList<Task>();
    }
}
