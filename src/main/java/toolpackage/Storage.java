package toolpackage;

import dukepackage.DukeException;

import taskpackage.Deadlines;
import taskpackage.Events;
import taskpackage.Task;
import taskpackage.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage used by
 * the Duke bot. A Storage object
 * contains a file represented by a
 * File object, and serves as the
 * interface for reading and writing data
 * into the file.
 */
public class Storage {

    private File file;

    /**
     * Constructs a new Storage space.
     *
     * @param filePath File path of where data will be stored.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Creates a new directory and file to store the data.
     *
     * @return boolean Indicate whether there was already existing data.
     * @throws DukeException if there are issues with the file creation.
     */
    public boolean createStorage() throws DukeException {
        try {
            Files.createDirectories(Paths.get(this.file.getParent()));
            File newFile = new File(this.file.getPath());
            return newFile.createNewFile();
        } catch (IOException e) {
            byte[] emojiByteCode = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0xB1};
            String emoji = new String(emojiByteCode, Charset.forName("UTF-8"));
            throw new DukeException(emoji + " OOPS!!! There was an error saving data into storage.");
        }
    }

    /**
     * Saves data into storage.
     *
     * @param listOfTasks List of tasks that needs to be saved.
     * @return boolean Indicate whether saving was successful.
     * @throws DukeException if there are issues with writing data into the file.
     */
    public boolean saveStorage(TaskList listOfTasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            listOfTasks.saveStorage(fileWriter);
            fileWriter.close();
            return true;
        } catch (IOException | DukeException e) {
            byte[] emojiByteCode = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0xB1};
            String emoji = new String(emojiByteCode, Charset.forName("UTF-8"));
            throw new DukeException(emoji + " OOPS!!! There was an error saving data into storage.");
        }
    }

    /**
     * Reads data from storage into bot.
     *
     * @return ArrayList<Task> List of tasks to be loaded into the bot.
     * @throws DukeException if there is a missing file or corrupt data.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> listOfTasks = new ArrayList<>();;
        Scanner data;
        try {
            data = new Scanner(this.file);
            String[] input;

            while (data.hasNextLine()) {
                input = data.nextLine().split("\\| ");
                if (input[0].equals("T ")) {
                    listOfTasks.add(new ToDos(input[2], input[1]));
                } else if (input[0].equals("D ")) {
                    listOfTasks.add(new Deadlines(input[2], input[3], input[1]));
                } else if (input[0].equals("E ")) {
                    listOfTasks.add(new Events(input[2], input[3], input[4], input[1]));
                }
            }
        } catch (FileNotFoundException | DukeException e) {
            byte[] emojiByteCode = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0xB1};
            String emoji = new String(emojiByteCode, Charset.forName("UTF-8"));
            throw new DukeException(emoji + " OOPS!!! There was an error loading data from the storage.");
        }
        data.close();
        return listOfTasks;
    }
}
