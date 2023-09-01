package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Save the task list into a file located at the certain path.
     * @param taskList Task list that going to be saved.
     * @throws DukeException If catch any IOException.
     */
    public void save(TaskList taskList) throws DukeException{
        try {
            FileWriter fw = new FileWriter(path);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.getTask(i).saveString() + '\n');
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Duke! " + e.getMessage());
        }
    }

    /**
     * Load the save file from the save file and convert it into array list.
     * @return Array list that store the task located in the save file.
     * @throws DukeException If the save file not found.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File(path);
        Scanner sc;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            File folder = new File(System.getProperty("user.dir"), "data");
            folder.mkdir();
            throw new DukeException("Hi, you are the first time using this app right");
        }
        while (sc.hasNext()) {
            String input = sc.nextLine();
            Task temp = Parser.parseSaveFile(input);
            taskList.add(temp);
        }
        sc.close();
        return taskList;
    }
}
