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

    private static final String fileName = "ChewieBrain.json";

    private static final Gson gson = new Gson();

    private boolean isNewSave = false;

    private boolean isFailSave = false;

    private SaveData save;

    /**
     * Save the user's task list into a json file
     *
     * @param list user's task list
     */
    public void save(TaskList list) {
        SaveData save = new SaveData(list.list().toArray(new Task[0]));
        String json = gson.toJson(save);

        // write to save file
        try {
            File saveFile = new File(fileName);
            FileWriter fw = new FileWriter(saveFile);

            fw.write(json);
            fw.close();

        } catch (IOException e) {
            System.out.print("Chewie have difficulty remembering your tasks.");
        }
    }

    /**
     * Overloaded method of save, with additional argument to set directory of save file
     *
     * @param list user's task list
     * @param filePath directory for save file
     */
    public void save(TaskList list, String filePath) {
        SaveData save = new SaveData(list.list().toArray(new Task[0]));
        String json = gson.toJson(save);

        // write to save file
        try {
            File saveFile = new File(filePath);
            FileWriter fw = new FileWriter(saveFile);

            fw.write(json);
            fw.close();

        } catch (IOException e) {
            System.out.print("Chewie have difficulty remembering your tasks.");
            isFailSave = true;
        }
    }

    /**
     * Loads save file into program when program initialize
     *
     * @param list
     */
    public void load(TaskList list) {
        try {
            JsonReader reader = new JsonReader(new FileReader(fileName));
            save = gson.fromJson(reader, SaveData.class);

        } catch (FileNotFoundException e) {
            save = new SaveData(new Task[0]);

        } finally {

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
    }

    /**
     * Overloaded method of load
     *
     * @param list
     * @param filePath
     */
    public void load(TaskList list, String filePath) {
        try {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            save = gson.fromJson(reader, SaveData.class);

        } catch (FileNotFoundException e) {
            save = new SaveData(new Task[0]);
            isNewSave = true;

        } finally {

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
    }

    /**
     * To check if program runs on a new save
     *
     * @return is the program initialized with a new Save
     */
    public boolean isNewSave() {
        return isNewSave;
    }

    /**
     * To check if save is unsuccessful
     *
     * @return Is save unsuccesssull
     */
    public boolean isFailSave() {
        return isFailSave;
    }
}
