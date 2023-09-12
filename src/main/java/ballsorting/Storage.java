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
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            int stat = Integer.parseInt(input.substring(2, 3));
            StringBuilder desc = new StringBuilder();
            StringBuilder start = new StringBuilder();
            Task curr;
            if (input.charAt(0) == 'T') {
                curr = new Todo(input.substring(4));
            } else if (input.charAt(0) == 'D') {
                int i = "D | ".length(); //4
                while (input.charAt(i) != '|') {
                    desc.append(input.charAt(i));
                    i++;
                }
                i++;
                LocalDateTime endDateTime = LocalDateTime.parse(input.substring(i), Ballsorter.inputFormatter);
                curr = new Deadline(desc.toString(), endDateTime);
            } else {
                assert input.charAt(0) == 'E';
                int i = "E | ".length(); //4
                while (input.charAt(i) != '|') {
                    desc.append(input.charAt(i));
                    i++;
                }
                i++;
                while (input.charAt(i) != '|') {
                    start.append(input.charAt(i));
                    i++;
                }
                i++;
                LocalDateTime startDateTime = LocalDateTime.parse(start.toString(), Ballsorter.inputFormatter);
                LocalDateTime endDateTime = LocalDateTime.parse(input.substring(i), Ballsorter.inputFormatter);
                assert startDateTime.isBefore(endDateTime);
                curr = new Event(desc.toString(), startDateTime, endDateTime);
            }
            if (stat == 1) {
                curr.markDone();
            }
            assert curr != null;
            taskList.addTask(curr);
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
