package duke;

import java.io.*;
import java.util.ArrayList;
import duke.task.Task;

/**
 * Handles the textfile provided by the user and loads all tasks from previous sessions.
 */

public class Storage {
    protected File file;
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Load textfile into an arraylist of the tasks in String format.
     * @return an ArrayList of the existing tasks in String format
     * @throws DukeException if the filepath is incorrectly inputted
     */

    public ArrayList<String> load() throws DukeException {
        ArrayList<String> taskList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(this.filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                taskList.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new DukeException("Unable to Load");
        }
        return taskList;
    }

    /**
     * At the end of the session, the textfile is properly updated.
     * @param taskList the final task list before ending the session
     * @param filePath the path of the updated textfile
     * @throws DukeException if the filepath is incorrectly inputted
     */
    public void update(TaskList taskList, String filePath) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            for (Task task : taskList.getTaskList()) {
                fileWriter.write(task.toString() + '\n');
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Unable to locate file.");
        }
    }
}