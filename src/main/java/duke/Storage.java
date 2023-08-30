package duke;

import java.io.*;
import java.util.ArrayList;
import duke.task.Task;

public class Storage {
    protected File file;
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

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