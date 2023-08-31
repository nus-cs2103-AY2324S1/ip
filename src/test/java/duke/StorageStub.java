package duke;

import duke.task.Task;

import java.util.List;

public class StorageStub extends Storage {

    public StorageStub() {
        super("", "");
    }

    @Override
    public void changeFile(Keyword keyword, int taskNum) {
    }

    @Override
    public List<Task> load() {
        return null;
    }

    @Override
    public void createFile() {
    }

    @Override
    public void appendFile(String text) {
    }
}
