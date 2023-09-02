package MattBot;

import MattBot.task.Task;
import MattBot.task.Todo;
import MattBot.task.Deadline;
import MattBot.task.Event;
import MattBot.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;   // Import the FileWriter class

public class Storage {

    protected TaskList data;
    private BufferedReader myReader;
    private Path dataPath;
    private static final String SAVE_NAME = "mattbot.txt";
    private static final String SAVE_FOLDER = "data/";

    public Storage() throws IOException {
        dataPath = Paths.get(SAVE_FOLDER + SAVE_NAME);
        if (!Files.exists(dataPath)) {
            Files.createDirectory(Paths.get(SAVE_FOLDER));
            Files.createFile(dataPath);
        }
        myReader = Files.newBufferedReader(dataPath);
        // myReader.close();
    }

    /*
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

    public TaskList getTaskList() {
        return data;
    }

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
