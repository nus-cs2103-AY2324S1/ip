package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(TaskList list) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < list.getSize(); i++) {
                fileWriter.write(list.getTask(i).saveTask() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public  ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String saveFormat = scanner.nextLine();
                    tasks.add(Task.loadData(saveFormat));
                }
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println((e.getMessage()));
        }
        return tasks;
    }
}
