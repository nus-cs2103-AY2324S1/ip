package mattbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import mattbot.task.Task;

/**
 * Handles Storage I/O for MattBot.
 */
public class Storage {
    private static final String SAVE_NAME = "mattbot.txt";
    private static final String SAVE_FOLDER = "data/";
    protected TaskList data;
    private BufferedReader myReader;
    private Path dataPath;


    /**
     * Constructor for Storage, attempts to read save file.
     * Creates empty save file if none is found.
     */
    public Storage() throws IOException {
        dataPath = Paths.get(SAVE_FOLDER + SAVE_NAME);
        if (!Files.exists(dataPath)) {
            Files.createDirectory(Paths.get(SAVE_FOLDER));
            Files.createFile(dataPath);
        }
        myReader = Files.newBufferedReader(dataPath);
        // myReader.close();
    }

    /**
     * Loads list of tasks from the earlier specified filepath.
     *
     * @return List of tasks
     */
    public TaskList load() throws IOException {
        TaskList taskList = new TaskList();
        while (true) {
            String nextLine = myReader.readLine();
            if (nextLine == null) {
                break;
            }
            taskList.addTask(Parser.parseFile(nextLine));
        }
        return taskList;
    }

    /**
     * Returns all current Tasks as a TaskList.
     *
     * @return TaskList containing all Tasks.
     */
    public TaskList getTaskList() {
        return data;
    }

    /**
     * Writes TaskList to the storage file.
     */
    public void writeBack(TaskList tL) {
        // Format should be in
        // [Type] | [isDone] | Name | startDate | endDate
        data = tL;
        ArrayList<Task> aLTask = tL.getTasks();
        ArrayList<String> toWrite = new ArrayList<String>();
        for (int i = 0; i < aLTask.size(); i++) {
            Task t = aLTask.get(i);
            toWrite.add(t.toFile());
        }
        try {
            Files.delete(dataPath);
            Files.write(dataPath, toWrite);
        } catch (IOException e) {
            System.out.println("Oh no, I've failed to write the save file.");
            System.out.println(e);
        }
    }
}
