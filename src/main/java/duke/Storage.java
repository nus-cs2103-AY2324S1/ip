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
     *
     * @return Arraylist of Task objects.
     * @throws DukeException if file is not found.
     */
    public ArrayList<Task> loadTasksFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File("data/duke.txt");
            if (!f.exists()) {
                return tasks;
            }
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
    public void saveTasksToFile(ArrayList<Task> tasks) throws DukeException {

        File file = new File("data/duke.txt");
        if (!file.exists()) {
            file.getParentFile().mkdir();
            try {
                file.createNewFile();
            } catch (IOException e){
                throw new DukeException("Error creating file!", e);
            }
        }

        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (Task i : tasks) {
                fw.write(i.isDoneFormatter());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error writing to file!", e);
        }
    }

}
