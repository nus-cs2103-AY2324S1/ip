import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String filePath;
    public Storage (String filePath) {
        this.filePath = filePath;
    }
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String data = s.nextLine();
                String[] splitVariables = data.split(" \\| ");
                String taskType = splitVariables[0];
                String taskDescription = splitVariables[1];
                boolean taskIsDone = Boolean.parseBoolean(splitVariables[2]);
                switch (taskType) {
                    case "E":
                        String taskFrom = splitVariables[3];
                        String taskTo = splitVariables[4];
                        tasks.add(new Event(taskDescription, taskFrom, taskTo, taskIsDone));
                        break;
                    case "D":
                        String taskBy = splitVariables[3];
                        tasks.add(new Deadline(taskDescription, taskBy, taskIsDone));
                        break;
                    case "T":
                        tasks.add(new Todo(taskDescription, taskIsDone));
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. No existing tasks.");
        }

        return tasks;
    }

    public void saveTasks(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task task : tasks.getTasks()) {
                String taskData = task.parse();
                fw.write(taskData + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Save tasks failed.");
        }
    }
}
