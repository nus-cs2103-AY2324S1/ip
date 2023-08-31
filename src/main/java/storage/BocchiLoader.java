package storage;

import task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BocchiLoader {
    private static final String DEFAULT_PATH = "./data/bocchi.txt";
    private static final int INDEX_TYPE = 0;
    private static final int INDEX_MARKED = 1;
    private static final int INDEX_NAME = 2;
    private static final int INDEX_TIME = 3;
    private final String filePath;

    public BocchiLoader() {
        this.filePath = DEFAULT_PATH;
    }

    public BocchiLoader(String filePath) {
        this.filePath = filePath;
    }

    private Task parseDataToEvent(String data) throws Exception {
        String[] tokens = data.split("\\|");
        String type = tokens[INDEX_TYPE].trim();
        String name = tokens[INDEX_NAME].trim();
        Boolean marked = tokens[INDEX_MARKED].trim().equals("1");
        switch (type) {
        case "T":
            return new Todo(name, marked);
        case "D":
            return new Deadline(name, tokens[INDEX_TIME].trim(), marked);
        case "E":
            String[] timeRange = tokens[INDEX_TIME].split("-");
            String start = timeRange[0].trim();
            String end = timeRange[1].trim();
            return new Event(name, start, end, marked);
        default:
            throw new Exception("File might be corrupted, please check input file.");
        }
    }

    public TaskList loadTaskList() throws FileNotFoundException {
        File f = new File(this.filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        TaskList taskList = new TaskList();
        while (s.hasNext()) {
            try {
                Task task = parseDataToEvent(s.nextLine());
                taskList = taskList.addTask(task);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return taskList;
    }
}
