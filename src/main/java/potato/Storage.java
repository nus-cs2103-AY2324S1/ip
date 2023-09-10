package potato;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import potato.task.*;

public class Storage {
    private String path;

    public Storage(String txt) {
        path = txt;
    }

    public void saveTask(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(path);
        for (Task t : tasks) {
            writer.write(t.toSave() + "\n");
        }
        writer.close();
    }

    public ArrayList<Task> loadTask() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(path);

        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    tasks.add(Task.savedParse(scanner.nextLine()));
                }
                scanner.close();
            } catch (IOException e) {
                // e.printStackTrace();
                System.out.println("no file");
            }
        }
        return tasks;
    }
}
