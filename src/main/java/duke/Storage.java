package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * Loads and saves data into files.
 */
public class Storage {
    public final String filePath;

    /**
     * Constructs a duke.Storage that loads and saves ChatBot data into files
     *
     * @param filePath The file path to read and write from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves data input from user into file. User input when using
     * Chat Bot will be written into the file specified.
     *
     * @param tasks The task.Task List to be written into file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void writeFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(tasks.toFileString());
        fw.close();
    }

    /**
     * Loads data from the previously saved file. Previous user input written in the file
     * will be processed so user can view and make changes to the data using the Chat Bot.
     *
     * @return task.Task List that was saved by user.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();

        File f = new File(filePath);

        if (f.exists()) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                addFileTask(taskList, s.nextLine());
            }
        } else {
            f.createNewFile();
        }

        return taskList;
    }

    /**
     * Process text from the file to be used by Chat Bot. Process data stored in the
     * txt file and adds the respective tasks to the duke.TaskList object.
     *
     * @param taskList The array list of task stored by files.
     * @param input The text input from the file being read.
     */
    private void addFileTask(ArrayList<Task> taskList, String input) {
        String[] task = input.split("\\|");
        String taskName = task[0].trim();
        String isMarked = task[1].trim();
        String taskDesc = task[2].trim();

        switch (taskName) {
        case "T":
            if (isMarked.equals("1")) {
                taskList.add(new ToDo(taskDesc, true));
            } else {
                taskList.add(new ToDo(taskDesc));
            }

            break;
        case "D":
            String taskDate = task[3].trim();

            if (isMarked.equals("1")) {
                taskList.add(new Deadline(taskDesc, true, taskDate));
            } else {
                taskList.add(new Deadline(taskDesc, taskDate));
            }

            break;
        case "E":
            String[] taskEvent = task[3].split("-");

            if (isMarked.equals("1")) {
                taskList.add(new Event(taskDesc, true, taskEvent[0].trim(), taskEvent[1].trim()));
            } else {
                taskList.add(new Event(taskDesc, taskEvent[0].trim(), taskEvent[1].trim()));
            }

            break;
        }
    }
}
