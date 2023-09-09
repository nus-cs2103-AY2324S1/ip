package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * Class that handle fetch of data from file and save data to file.
 *
 * @author syamfarh
 */
public class Storage {

    /**
     * Read task from duke.txt file and copy to ArrayList list.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File("data/duke.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] task = s.nextLine().split("\\|");
                stringToList(tasks, task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found", e);
        }

        return tasks;
    }

    /**
     * Copy every line of task from file to ArrayList list.
     * @param task String array that store task in file.
     */
    private void stringToList(ArrayList<Task> tasks, String[] task) {

        switch (task[0]) {
        case "T":
            tasks.add(new ToDo(task[1]));
            break;
        case "D":
            tasks.add(new Deadline(task[1], task[3]));
            break;
        case "E":
            tasks.add(new Event(task[1], task[3], task[4]));
            break;
        default:
            break;
        }

        if (task[2].equals("1")) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }

    /**
     * Write the task list to file.
     */
    public void save(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (Task i : tasks) {
                fw.write(i.fileFormat());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error writing to file!", e);
        }
    }

}
