package penguin;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;

/**
 * Storage handles the save and load functionality of Penguin.
 */
public class Storage {

    private String file;

    public Storage(String file) {
        this.file = file;
    }

    /**
     * Saves tasks into storage.
     *
     * @param taskList the list of Tasks to be saved.
     */
    public void save(TaskList taskList) {
        try {
            File file = new File(this.file);

            // create directory if it does not exist
            file.getParentFile().mkdirs();

            // create txt file if it does not exist
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter taskSaver = new BufferedWriter(new FileWriter(file));
            String tasks = "";
            for (Task task : taskList.list) {
                tasks += task.getSaveDisplay() + "\n";
            }
            taskSaver.write(tasks);
            taskSaver.close();
        } catch (IOException e) {
            System.out.println("Honk? My brain isn't working or is missing! " + e.getMessage());
        }
    }

    /**
     * Loads tasks from storage.
     *
     * @return the list of Tasks that was saved.
     */
    public TaskList load() {
        TaskList taskList = new TaskList();
        Scanner sc = null;

        try {
            File file = new File(this.file);
 
            // create directory if it does not exist
            file.getParentFile().mkdirs();

            // create txt file if it does not exist
            if (!file.exists()) {
                file.createNewFile();

                return taskList; 
            }

            assert file.exists();

            sc = new Scanner(file);
        }
        catch (IOException e) {
            System.out.println("Honk, something went wrong with my brain..." + e.getMessage());
            return taskList;
        }

        while (sc.hasNext()) {
            String taskString = sc.nextLine();
            String[] spl = taskString.split(" \\| ");
            Task newTask = new Task("");
            switch (spl[0]) {
                case "T":
                    newTask = new ToDo(spl[2]);
                    break;
                case "D":
                    newTask = new Deadline(spl[2], spl[3]);
                    break;
                case "E":
                    newTask = new Event(spl[2], spl[3], spl[4]);
                    break;
                default:
                    System.out.println("My memory seems corrupted!");
                    break;
            }
            if (spl[1].equals("1")) {
                newTask.done = true;
            }
            taskList.list.add(newTask);
        }

        return taskList;
    }
}
