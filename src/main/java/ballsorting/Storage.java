package ballsorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Encapsulates the storage of the TaskList after user exits the chatbot,
 * and the initialisation of the TaskList on startup.
 */
public class Storage {
    private File tmpDir;

    /**
     * Creates a new instance of Storage.
     * @param file File that stores previous chatbot information and will store current chatbot information.
     */
    public Storage(File file) {
        tmpDir = file;
    }

    /**
     * Loads the tasks stored in storage.
     * @param taskList Lists of tasks in chatbot.
     * @throws FileNotFoundException
     */
    public void loadFile(TaskList taskList) throws FileNotFoundException {
        Scanner sc = new Scanner(tmpDir);
        Parser parser = new Parser();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            switch (input.charAt(0)) {
            case 'T':
                parser.parseStoredTodo(input, taskList);
                break;
            case 'D':
                parser.parseStoredDeadline(input, taskList);
                break;
            case 'E':
                parser.parseStoredEvent(input, taskList);
                break;
            default:

                break;
            }
        }
    }

    /**
     * Writes tasks in list to storage.
     * @param taskList Tasks in chatbot.
     */
    public void storeList(TaskList taskList) {
        try {
            Files.write(tmpDir.toPath(), taskList.storeList().getBytes());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
