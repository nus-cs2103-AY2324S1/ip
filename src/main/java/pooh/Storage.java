package pooh;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadTasks() throws LoadTasksException {
        File file = new File(this.filePath);
        List<Task> taskList = new ArrayList<Task>();
        try {
            if (file.exists()) {
                BufferedReader fileReader = new BufferedReader(new FileReader(file));
                String line = fileReader.readLine();

                while (line != null) {
                    Task task = Task.readTaskFromFile(line);
                    taskList.add(task);
                    line = fileReader.readLine();
                }

                fileReader.close();
            } else {
                Ui.generalRespond(
                        "      Looks like this is the first time here! Say hi to POOH!\n      No worries, Pooh will " +
                                "save your tasks to pooh.txt");
                boolean fileCreated = file.createNewFile();
            }
        } catch (IOException ex) {
            throw new LoadTasksException();
        }
        return taskList;
    }

    public void writeTask(TaskList taskList) throws WriteTasksException {
        try (FileWriter fileWriter = new FileWriter("pooh.txt")) {
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                fileWriter.write(task.writeTaskToFile() + "\n");
            }
        } catch (IOException ex) {
            throw new WriteTasksException();
        } catch (InvalidTaskException e) {
            throw new RuntimeException(e);
        }
    }

}
