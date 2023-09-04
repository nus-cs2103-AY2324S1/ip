package duke;

import duke.task.Task;
import duke.task.TaskList;
import duke.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
        makeDataDir();
        this.createFile();
    }

    private static void makeDataDir() {
        File dataDirectory = new File("./data/");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }
    }

    private boolean createFile()  {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

    public TaskList loadIntoList(TaskList tasks) throws DukeException {
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                tasks.add(Parser.dataToTask(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Your Chatbot will start from clean slate.");
        }
        return tasks;
    }

    public void writeListToFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toData());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void appendToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(task.toData());
        fw.write("\n");
        fw.close();
    }
}
