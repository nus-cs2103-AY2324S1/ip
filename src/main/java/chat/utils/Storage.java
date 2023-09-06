package chat.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import chat.commands.Command;
import chat.exceptions.ChatException;
import chat.tasks.TaskList;

/**
 * @author juzzztinsoong
 */
public class Storage {

    private Path path;

    /**
     * Constructor method for Storage.
     * @param filepath chat.txt filepath.
     */
    public Storage(String filepath) {
        String homedir = new File(System.getProperty("user.dir")).getPath();
        String[] splitFilepath = Parser.parseFilePath(filepath);
        this.path = Paths.get(homedir, splitFilepath[0], splitFilepath[1]);
    }

    /**
     * Loads saved data and returns the tasklist.
     * @return the tasklist that was loaded from the file.
     */
    public TaskList loadFromFile() throws ChatException {
        if (Files.exists(path)) {
            TaskList tasklist = new TaskList();
            try {
                List<String> contents = Files.readAllLines(path);
                for (String content : contents) {
                    Command c = Parser.parseFileContent(content);
                    c.load(tasklist);
                }
                return tasklist;
            } catch (FileNotFoundException e) {
                throw new ChatException(e.getMessage());
            } catch (IOException e) {
                throw new ChatException(e.getMessage());
            } catch (ChatException e) {
                throw e;
            }
        } else {
            throw new ChatException("File not found");
        }
    }

    /**
     * Writes tasklist to file. This method is used to write a file to the file
     * system. The file is created if it does not exist.
     * @param tasklist the tasklist to write to file.
     */
    public void writeToFile(TaskList tasklist) throws ChatException {
        try {
            // Creates a file if it doesn t exist.
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
            BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
                    StandardOpenOption.TRUNCATE_EXISTING);
            // This method is used to flush the tasklist into the file.
            String[] tempList = tasklist.returnList();
            for (String i : tempList) {
                writer.write(i + "\n");
                writer.flush();
            }
        } catch (Exception e) {
            throw new ChatException(e.getMessage());
        }
    }

}
