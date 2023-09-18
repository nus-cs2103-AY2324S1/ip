package com.alpha.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.alpha.commands.Command;
import com.alpha.enums.MarkEnum;
import com.alpha.exceptions.InvalidDateTimeException;
import com.alpha.exceptions.InvalidTaskException;
import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;
import com.alpha.utils.TaskParser;

/**
 * The Storage class.
 */
public class Storage {

    private final File file;

    /**
     * Instantiates a new Storage object.
     *
     * @param filePath File path to the local storage file.
     * @throws IOException If an io exception occurs.
     */
    public Storage(String filePath) throws IOException {
        file = new File(filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Loads task list from the local storage file.
     *
     * @return The task list from the storage file.
     * @throws FileNotFoundException    If the storage file cannot be found.
     * @throws InvalidTaskException     If the task in the storage file is invalid.
     * @throws InvalidDateTimeException If the date in the storage file is invalid.
     */
    public TaskList load() throws FileNotFoundException, InvalidTaskException, InvalidDateTimeException {
        Scanner sc = new Scanner(file);
        TaskList taskList = new TaskList();
        while (sc.hasNextLine()) {
            Command command = TaskParser.parse(sc.nextLine(), taskList);
            command.execute();
        }
        return taskList;
    }

    /**
     * Saves current task list into the local storage file.
     *
     * @param taskList The current task list.
     * @throws IOException If an io exception occurs.
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(file);
        int count = 1;
        for (Task task : taskList.getTasks()) {
            StringBuilder row = new StringBuilder();
            row.append(task.toStorageString());
            if (task.getMark() == MarkEnum.DONE) {
                row.append("\nmark ");
                row.append(count);
            }
            row.append("\n");
            fw.write(row.toString());
            ++count;
        }
        fw.close();
    }
}
