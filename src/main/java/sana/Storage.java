package sana;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String load() {
        StringBuilder tasks = new StringBuilder();
        try {
            Scanner scan = new Scanner(new File(Paths.get(
                    filePath).toString()));
            while(scan.hasNextLine()) {
                String task = scan.nextLine();
                tasks = tasks.append(task + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return tasks.toString();
    }

    public void save(String folderPath, String filePath, Task task) {
        File folder = new File(folderPath);

        // Check if folder exists, if not create one
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Check if file exists, if not create one
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        // write to file
        try {
            FileWriter writer = new FileWriter(Paths.get(filePath).toString(), true);
            writer.write(task.formatTask() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void clear(String filePath) {
        File file = new File(filePath);
        file.delete();
    }

}
