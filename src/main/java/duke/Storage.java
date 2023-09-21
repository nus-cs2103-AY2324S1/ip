package duke;

import duke.task.Task;
import duke.task.TaskList;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;

/**
 * Saves and loads user data from and into the program when
 * program is terminated and intialized
 *
 * @author Lian Zhi Xuan
 */
public class Storage {

    public static Storage instance = new Storage();
    private static final String fileName = "ChewieBrain.json";

    private static final Gson gson = new Gson();

    private boolean isNewSave = false;

    private SaveData save;

    /**
     * Saves the user's task list into a json file.
     *
     * @param list user's task list
     */
    public void save(TaskList list) {
        SaveData save = new SaveData(list.getList().toArray(new Task[0]));
        String json = gson.toJson(save);

        String path = System.getProperty("user.home") + "\\" + fileName;

        assert save != null : "The save data should exists";
        try {
            FileWriter fw = new FileWriter(path);

            fw.write(json);
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads save file into program when program initialize.
     *
     * @param list TaskList to load our save.
     */
    public void load(TaskList list) {
        String path = System.getProperty("user.home") + "\\" + fileName;

        try {
            JsonReader reader = new JsonReader(new FileReader(path));
            save = gson.fromJson(reader, SaveData.class);

        } catch (FileNotFoundException e) {
            save = new SaveData(new Task[0]);

        } finally {
            setupTaskList(list);
        }
    }

    /**
     * Overloaded method of load to use for testing.
     *
     * @param list TaskList to load our save.
     * @param filePath filePath to load from.
     */
    public void load(TaskList list, String filePath) {
        try {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            save = gson.fromJson(reader, SaveData.class);

        } catch (FileNotFoundException e) {
            save = new SaveData(new Task[0]);
            isNewSave = true;

            assert isNewSave : "The application should have new save";
        } finally {
            setupTaskList(list);
        }
    }

    private void setupTaskList (TaskList list) {

        for (int i = 0; i < save.type.length; i++) {
            String s = save.type[i];

            if (s.equals("Task.ToDo")) {
                list.add(save.toDos[i]);
            } else if (s.equals("Task.Events")) {
                list.add(save.events[i]);
            } else {
                list.add(save.deadlines[i]);
            }
        }
    }

    /**
     * Checks if program runs on a new save.
     *
     * @return is the program initialized with a new Save.
     */
    public boolean isNewSave() {
        return isNewSave;
    }
}
