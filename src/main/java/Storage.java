import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    private String filepath;

    public Storage(String filepath){

        this.filepath = filepath;
    }
    public void save(ArrayList<Task> tasklist) {
        try {
            FileWriter writer = new FileWriter(filepath);
            for (Task task : tasklist) {
                // Convert task to string format and write to the file
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> load() {
        try {
            ArrayList<Task> taskList = new ArrayList<Task>();
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the line and create tasks, then add to the list
                Task task = Task.parseFromString(line);
                taskList.add(task);
            }
            reader.close();
            return taskList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


