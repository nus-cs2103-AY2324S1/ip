import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
public class Storage {
    private String savePath;
    private File saveFile;
    public Storage(String path) {
        this.savePath = path;
        String directory = path.substring(0, path.lastIndexOf("/"));
        try {
            File dir = new File(directory);
            if (!dir.exists()) {
                dir.mkdir();
            }

            File file = new File(path);
            this.saveFile = file;

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (java.io.IOException e) {
            System.out.println("An error occurred when initializing: " + e.getMessage());
        }
    }

    public void save(TaskList taskList) throws SavingException{
        ArrayList<Task> tasks = taskList.getTasks();
        try {
            FileWriter writer = new FileWriter(this.saveFile);
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (java.io.IOException e) {
            throw new SavingException("An error occurred when saving: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() throws LoadingException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Scanner reader = new Scanner(this.saveFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                Task task = loadTask(data);
                tasks.add(task);
            }
            reader.close();
        } catch (java.io.FileNotFoundException e) {
            throw new LoadingException("An error occurred when loading: " + e.getMessage());
        }
        return tasks;
    }
    public Task loadTask(String s) {
        Character taskType = s.charAt(1); // T, D, E
        Boolean isDone = s.charAt(4) == 'X';
        String description = s.substring(7);
        Task task = null;
        if (taskType == 'T') {
            task = new ToDoTask(description);
        } else if (taskType == 'D') {
            String[] params = description.split("\\(by: ");
            String time = params[1].substring(0, params[1].length() - 1);
            task = new DeadlineTask(params[0], time);
        } else if (taskType == 'E') {
            // example [E][ ] project meeting (from: Aug 06 2022 14:00 to: Aug 06 2022 18:00)
            String[] params = description.split("\\(from: ");
            String startTime = description.split("from: ")[1].split(" to: ")[0];
            String endTime = description.split("to: ")[1].substring(0, description.split("to: ")[1].length() - 1);
            task = new EventTask(params[0], startTime, endTime);
        }
        if (isDone) {
            task.mark();
        }
        return task;
    }

}
