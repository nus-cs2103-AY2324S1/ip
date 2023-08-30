package duke;

public class StorageStub extends Storage {
    public StorageStub() {
        super("src/main/data/duke.txt");
    }

    @Override
    public void saveTask(Task task) {
        // do nothing
    }
}
