import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveListToDisk(ArrayList<Task> taskList) {
        File data = new File(filePath);
        // checks if data folder and duke.txt exists, else create file
        if (!data.exists()) {
            try {
                data.getParentFile().mkdirs();
                data.createNewFile();
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            System.out.println("Saving list to disk...");
            for (Task task : taskList) {
                fw.write(task.getFileDescriptor() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();

        File f = new File(filePath);
        Scanner s = new Scanner(filePath);
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        while (s.hasNext()) {
            String line = s.nextLine();
            String[] details = line.split("\\|");
            String taskType = details[details.length - 1].trim();
            if (details.length < 2) {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I can't load the data :-(");
            }
            boolean isDone = details[0].trim().equals("true");
            String taskDescription = details[1].trim();
            Task task;

            switch (taskType) {
                case "TODO":
                    task = new ToDoTask(taskDescription);
                    break;
                case "DEADLINE":
                    String by = details[2].trim();
                    task = new DeadlineTask(taskDescription, by);
                    break;
                case "EVENT":
                    String from = details[2].trim();
                    String to = details[3].trim();
                    task = new EventTask(taskDescription, from, to);
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what the loaded command is :-(");
            }
            if (isDone) {
                task.mark();
            }
            taskList.add(task);
        }

        return taskList;
    }
}
