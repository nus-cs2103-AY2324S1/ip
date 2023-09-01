package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage for the list of Tasks.
 */
public class Storage {
    private File file;

    public Storage(String path) {
        this.file = new File(path);
        if (!this.file.getParentFile().exists()) {
            this.file.getParentFile().mkdirs();
        }
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error: Unable to create local file");
            }
        }
    }

    /**
     * Returns an ArrayList of Tasks from local data file.
     *
     * @return ArrayList consisting of Task read from the data file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();

        Scanner sc = null;

        try {
            sc = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to load local file");
        }
        while (sc != null && sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.charAt(0) == 'D') {
                Deadline deadline = Parser.parseLoadDeadline(input);
                list.add(deadline);
            } else if (input.charAt(0) == 'E') {
                Event event = Parser.parseLoadEvent(input);
                list.add(event);
            } else if (input.charAt(0) == 'T') {
                ToDo todo = Parser.parseLoadToDo(input);
                list.add(todo);
            }
        }

        return list;
    }

    /**
     * Save the given TaskList into a local data file.
     *
     * @param taskList The given TaskList to be saved locally.
     */
    public void save(TaskList taskList) {
        String newData = Parser.parseTaskListToData(taskList);

        try {
            FileWriter fw = new FileWriter(file);
            fw.write(newData);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to save list");
        }
    }

}
