package duke;
import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks;

        File file = new File(filePath);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                // Read the taskArrayList from the file
                tasks = (ArrayList<Task>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                // Handle exceptions, e.g., if the file is corrupted
                tasks = new ArrayList<>();
                e.printStackTrace();
            }
        } else {
            tasks = new ArrayList<>();
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        File directory = file.getParentFile();

        try {
            // Create the directory if it doesn't exist
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Create the file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                // Write the taskArrayList to the file
                oos.writeObject(tasks);
            } catch (IOException e) {
                System.out.println("Error saving tasks to file!");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Error creating the file or directory!");
            e.printStackTrace();
        }
    }

}

