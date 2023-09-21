package com.ducky.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.ducky.task.Task;
import com.ducky.util.DuckyFileParseException;
import com.ducky.util.Parser;

/**
 * Storage module that handles file I/O for Ducky chatbot.
 */
public class Storage {

    private static final String LOAD_TASK_SUCCESS_MSG = "Successfully loaded saved tasks.";
    private static final String NO_FILE_LOADED_MSG = "No task file found. No tasks were loaded.";
    private final String filePath;

    /**
     * Constructs a Storage instance that saves and loads the specified file path.
     *
     * @param filePath File path to load persistent data from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves data in the specified TaskList to previously specified file path.
     *
     * @param taskList TaskList with data to be stored.
     */
    public void save(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(taskList.getSaveableList());
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads data (if any) in previously specified file path into specified TaskList.
     * @param taskList TaskList for persistent data to be loaded into.
     */
    public String load(TaskList taskList) {
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                Task parsedTask = Parser.parseSavedTask(s.nextLine());
                taskList.addTask(parsedTask);
            }
            return LOAD_TASK_SUCCESS_MSG;
        } catch (FileNotFoundException e) {
            return NO_FILE_LOADED_MSG;
        } catch (DuckyFileParseException e) {
            return e.getMessage();
        }
    }
}
