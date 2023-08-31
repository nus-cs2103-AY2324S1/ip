import java.io.*;
import java.util.ArrayList;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws MaxException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
//                tasks.add(loadTask(line));
//                TaskList.loadTask(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }
    public void writeToFile() {
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
            for (int i = 0; i < TaskList.numOfItems; i++) {
                int index = i + 1;
//                bufferedWriter.write(TaskList.myList.get(i));
            }

            // Close the writer
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Uh oh! There was an error writing to file: " + e.getMessage());
        }
    }
}
