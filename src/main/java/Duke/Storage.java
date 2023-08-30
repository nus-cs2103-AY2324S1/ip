package Duke;

import Duke.Tasks.Task;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads task list closed when bot was last used
     *
     * @return TaskList
     * @throws IOException
     */
    public TaskList load() throws IOException {
        try {
            FileInputStream file = new FileInputStream(filepath);
            ObjectInputStream output = new ObjectInputStream(file);
            TaskList taskList = (TaskList) output.readObject();
            output.close();
            return taskList;
        } catch (Exception error) {
            TaskList taskList = new TaskList();
            return taskList;
        }
    }

    /**
     * Saves task list into hard disk
     *
     * @param taskList
     * Takes in Task list from current instance and save it into the hard disk
     *
     * @throws IOException
     */
    public void save(TaskList taskList) throws IOException{
        FileOutputStream file = new FileOutputStream(filepath);
        ObjectOutputStream output = new ObjectOutputStream(file);
        output.writeObject(taskList);
        output.close();
    }
}
