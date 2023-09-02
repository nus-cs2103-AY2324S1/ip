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
