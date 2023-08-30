package Storage;

import Parser.FileParser;
import Parser.QueryObject;
import TaskList.Task;
import TaskList.ToDo;
import TaskList.Event;
import TaskList.Deadline;
import TaskList.TaskList;
import Exception.KevinException;

import java.util.ArrayList;
import java.util.Arrays;

public class FileStorage {
    private final Storage storage;
    public static final String FILE_LOCATION = "./data/kevin.txt";

    public FileStorage() {
        this.storage = new Storage();
    }

    public void initialize() throws KevinException {
        storage.createFile(FILE_LOCATION);
    }

    public void addToDo(ToDo newToDo) throws KevinException {
       storage.appendFile(FILE_LOCATION, newToDo.toText());
    }

    public void addEvent(Event newEvent) throws KevinException {
        storage.appendFile(FILE_LOCATION, newEvent.toText());
    }

    public void overwriteTask(Task newTask, int index) throws KevinException {
        storage.overwriteLine(FILE_LOCATION, newTask.toText(), index);
    }

    public void deleteTask(int index) throws KevinException {
        storage.overwriteLine(FILE_LOCATION, "", index);
    }

    public void addDeadline(Deadline newDeadline) throws KevinException {
        storage.appendFile(FILE_LOCATION, newDeadline.toText());
    }

    public ArrayList<String> makeStringToList(String tasks) {
        String[] tasksSplit = tasks.split(System.lineSeparator());
        return new ArrayList<>(Arrays.asList(tasksSplit));
    }

    public ArrayList<String> getTasksFromFile() throws KevinException {
        String fileContent = storage.readFile(FILE_LOCATION);
        return makeStringToList(fileContent);
    }
}
