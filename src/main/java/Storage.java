import tasks.Task;

import java.io.*;
import java.util.ArrayList;


public class Storage {

    private static final String FILE_PATH = "data/duke.txt";

    public Storage() {
        File directory = new File("data");
        File file = new File(FILE_PATH);

        try {
            if (!directory.exists()) {
                directory.mkdir();
                file.createNewFile();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String load() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            StringBuilder content = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            reader.close();

            return content.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void save(ArrayList<Task> taskList) {
        File file = new File(FILE_PATH);

        try {
            FileWriter writer = new FileWriter(file, false);

            for (int i = 0; i < taskList.size(); i++) {
                String content = taskList.get(i).toText();
                writer.write(content);
                writer.write("\n");
            }

            writer.close();
            System.out.println("Updated task list saved under data/duke.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
