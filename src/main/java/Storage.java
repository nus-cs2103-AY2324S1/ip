import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public ArrayList<Task> load() throws MaxException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                loadTask(line, tasks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
    public void loadTask(String task, ArrayList<Task> tasks) throws MaxException {
        String type = task.substring(0, 1).trim(); // T, D, E
        boolean done = task.substring(3, 5).trim().equals("1");
        System.out.println(task.substring(3, 5).trim().equals("1"));
        switch (type) {
        case "T":
            String description = task.substring(7).trim();
            tasks.add(new Todo(description, done));
            break;
        case "D": {
            int byIndex = task.indexOf("by:");
            String item = task.substring(7, byIndex - 1).trim();
            String by = task.substring(byIndex + 3).trim();
            LocalDate byDate = LocalDate.parse(by);
            tasks.add(new Deadline(item, byDate, done));
            break;
        }
        case "E": {
            int fromIndex = task.indexOf("from:");
            int toIndex = task.indexOf("to:");
            String item = task.substring(7, fromIndex - 1).trim();
            String from = task.substring(fromIndex + 5, toIndex - 1).trim();
            String to = task.substring(toIndex + 3).trim();
            LocalDate fromDate = LocalDate.parse(from);
            LocalDate toDate = LocalDate.parse(to);
            tasks.add(new Event(item, fromDate, toDate, done));
            break;
        }
        default:
            throw new MaxException("Hey! Your storage file is corrupted.");
        }
    }
    public void writeToFile(TaskList tasks) {
        // Save the task list to the system upon change
        File file = new File("./data/max.txt");
        File dataDirectory = new File("./data/");
        // Handle case where the data file doesn't exist at the start
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs(); // Create the directory if it doesn't exist
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            // Write tasks to file
            for (int i = 0; i < tasks.getList().size(); i++) {
                bufferedWriter.write(tasks.getList().get(i).saveItem());
            }
            // Close the writer
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Uh oh! There was an error writing to file: " + e.getMessage());
        }
    }
}
