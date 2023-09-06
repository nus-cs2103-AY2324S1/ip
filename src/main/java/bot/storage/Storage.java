package bot.storage;

import bot.exception.DateTimeParseBotException;
import bot.task.Task;
import bot.exception.FileErrorBotException;
import bot.task.TaskList;
import bot.parsers.StorageParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Iterator;

public class Storage {
    private static final String FILE_NAME = "task.txt";
    private static final String FILE_RELATIVE_PATH = "data" + File.separator;
    private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
    private static final String ABSOLUTE_FILE_PATH = Storage.WORKING_DIRECTORY + File.separator +
            Storage.FILE_RELATIVE_PATH;

    /**
     * Creates a data/task.txt if the file is not yet generated. The contents in the file
     * is then overwritten with the tasks in the taskList argument.
     *
     * @param taskList a list of tasks in their most updated state
     * @throws FileErrorBotException if the file or directory is missing or corrupted
     * @throws IOException if an I/O error occurred
     */
    public static void save(TaskList taskList) throws FileErrorBotException, IOException {
        File file = new File(Storage.ABSOLUTE_FILE_PATH +
                File.separator + Storage.FILE_NAME);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            Iterator<Task> iterator = taskList.iterator();
            while(iterator.hasNext()) {
                bw.write(iterator.next().fileWriteFormatted());
                bw.newLine();
            }
        } catch (IOException exception) {
            throw new FileErrorBotException("File cannot be saved...");
        }
    }

    /**
     * Returns a TaskList object containing the tasks found in the data/task.txt file. If the
     * file has yet to be generated, it will be generated before an empty TaskList object is
     * returned
     *
     * @return a list of tasks saved in the data/task.txt file
     * @throws FileErrorBotException if file or directory is corrupted and unreadable
     * @throws DateTimeParseBotException if DataTime is formatted incorrectly in the data/task.txt
     * @throws IOException if an I/O error occurred
     */
    public static TaskList read() throws FileErrorBotException,
            DateTimeParseBotException, IOException {
        File file = new File(Storage.ABSOLUTE_FILE_PATH +
                File.separator + Storage.FILE_NAME);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }
        TaskList taskList = new TaskList();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                taskList.add(StorageParser.parseFileInput(br.readLine()));
            }
        } catch (IOException exception) {
            throw new FileErrorBotException("File cannot be saved...");
        }
        return taskList;
    }
}
