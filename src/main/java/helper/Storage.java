package helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import task.Task;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() {
        List<Task> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Task task = Task.fromString(line);
                list.add(task);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            String directoryPath = "./data";
            File directory = new File(directoryPath);

            if (!directory.exists()) {
                directory.mkdirs();
            } else {
                File file = new File(filePath);
                try {
                    file.createNewFile();
                } catch (IOException e1) {
                    System.out.println(e1.getMessage());
                }
            }

            System.out.println("File created at " + filePath + "^.^");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            return list;
        }
    }
}
