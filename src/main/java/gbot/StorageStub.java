package gbot;

import tasks.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StorageStub extends Storage {
    public StorageStub() {
        super();
    }

    @Override
    public ArrayList<Task> load() {
        return new ArrayList<>();
    }

    @Override
    public void appendFile(String message) {

    }

    @Override
    public void updateFile(ArrayList<Task> list) {

    }
}
