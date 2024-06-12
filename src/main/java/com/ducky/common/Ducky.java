package com.ducky.common;

import com.ducky.command.Command;
import com.ducky.util.Parser;

/**
 * Represents a Ducky chatbot instance.
 */
public class Ducky {

    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructs a Ducky chatbot instance with the specified file path for persistent data.
     *
     * @param filePath File path for persistent data to be saved in.
     */
    public Ducky(String filePath) {
        assert !filePath.isEmpty();

        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.taskList, this.storage);
        } catch (DuckyException e) {
            return e.getMessage();
        }
    }

    /**
     * Loads saved tasks from specified file path into Ducky's task list.
     *
     * @return String indicating result of loading operation.
     */
    public String loadSavedTasks() {
        return this.storage.load(this.taskList);
    }

}
