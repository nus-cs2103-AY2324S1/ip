package Iris;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTask() {
        File file = new File(this.filePath);
        ArrayList<Task> toDoList = new ArrayList<Task>();
        try {
            if (file.exists()) {
                BufferedReader fileReader = new BufferedReader(new FileReader(file));
                String line = fileReader.readLine();

                while (line != null) {
                    Task task = Task.readTaskFromFile(line);
                    toDoList.add(task);
                    line = fileReader.readLine();
                }

                fileReader.close();
            } else {
                System.out.println("Looks like this is your first time here!");
                System.out.println("Iris will save your files in iris.txt.");
                boolean fileCreated = file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toDoList;
    }

    public void writeTask(ToDoList toDoList) {
        try (FileWriter fileWriter = new FileWriter("iris.txt")) {
            for (int i = 1; i <= toDoList.size(); i++) {
                Task task = toDoList.get(i);
                fileWriter.write(task.writeTaskToFile() + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error has occurred whilst writing to file. " +
                    "Error:" + e.getMessage());
        }
    }

}
