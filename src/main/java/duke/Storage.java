package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.command.Command;
import duke.command.Parser;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Storage handler for duke application.
 */
public class Storage {

    private final String savePath;
    private final boolean isUpdatable = true;

    public Storage(String savePath) {
        this.savePath = savePath;
    }

    /**
     * Updates the savefile to the most current tasks.
     *
     * @param taskList the current list of tasks
     */
    public void updateFile(TaskList taskList) {
        if (isUpdatable) {
            try {
                FileWriter writer = new FileWriter(savePath, false);
                for (Task t : taskList) {
                    writer.write(t.getSaveString() + "\n");
                }
                writer.close();
            } catch (IOException e) {
                UI.sendError(e.getMessage());
            }
        }
    }

    /**
     * Loads a TaskList from the given savefile.
     *
     * @return a TaskList constructed from the savefile
     * @throws DukeException when file is corrupted
     */
    public TaskList load() throws DukeException {
        try {
            File save = new File(this.savePath);
            Scanner scanner = new Scanner(save);
            TaskList taskList = new TaskList();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() != 0) {
                    String[] s = line.split(" ", 2);
                    Command c = Parser.parseCommand(s[1]);
                    c.execute(taskList, null, this);
                    if (Integer.parseInt(s[0]) == 1) {
                        taskList.get(taskList.size() - 1).markAsDone();
                    }
                }
            }
            scanner.close();
            return taskList;
        } catch (IOException e) {
            try {
                UI.sendError(e.getMessage());
                File save = new File(this.savePath);
                save.createNewFile();
                return new TaskList();
            } catch (IOException innerE) {
                throw new Error("Cannot create save file");
            }
        }
    }
}
