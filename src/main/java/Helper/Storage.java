import Task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;
    private final String directoryPath = "./data";

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
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            } else {
                File file = new File(filePath);
                try {
                    file.createNewFile();
                } catch (IOException ioException) {
                    System.out.println(ioException.getMessage());
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
