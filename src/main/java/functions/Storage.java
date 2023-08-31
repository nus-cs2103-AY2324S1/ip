package functions;

import tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String filepath;
    protected File file;
    public Storage(String filepath) {
        this.filepath = filepath;
        this.file = new File(filepath);
    }
    public ArrayList<String> loadFiles() throws FileNotFoundException {
        ArrayList<String> tasks = new ArrayList<>();
        Scanner s = new Scanner(file);
        while(s.hasNext()) {
            tasks.add(s.nextLine());
        }
        return tasks;
    }
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    public void saveFiles(ArrayList<Task> tasks) throws IOException {
        int i = tasks.size();
        String taskList = "";
        for (int j = 0; j < i; j++) {
            if (j == i - 1) {
                taskList += tasks.get(j).toString();
            } else {
                taskList += tasks.get(j).toString() + System.lineSeparator();
            }
        }
        writeToFile(this.filepath, taskList);
    }
}
