package gbot;

import exceptions.GBotException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import tasks.Task;

/**
 * The storage class of the chatbot that deals with file operations.
 *
 * @author Gallen Ong
 */
public class Storage {
    private File fList;
    private String path;

    /**
     * Creates a Storage object that refers to the filepath provided.
     *
     * @param path The filepath provided by user.
     */
    public Storage(String path) {
        this.path = path;

        File dir = new File("./data");
        if (!dir.exists()) {
            try {
                dir.mkdir();
            } catch (Exception e) {
                Ui.showError("File cannot be created.");
            }
        }

        fList = new File(this.path);
        if (!fList.exists()) {
            try {
                fList.createNewFile();
            } catch (IOException e) {
                Ui.showError("File cannot be created.");
            }
        }
    }

    /**
     * Empty constructor for the testing purposes.
     */
    public Storage() {
        // no effect
    }

    /**
     * Returns an ArrayList object with the tasks present in the existing file.
     *
     * @return An ArrayList object with existing tasks in file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        Scanner fScanner = null;
        try {
            fScanner = new Scanner(this.fList);
        } catch (FileNotFoundException e) {
            Ui.showError("File not found. Please check.");
            return list;
        }
        while (fScanner.hasNextLine()) {
            Parser.loadTaskFromFile(fScanner.nextLine(), list);
        }

        fScanner.close();
        return list;
    }

    /**
     * Adds new tasks to the file.
     *
     * @param message The task to be added in string format.
     */
    public void appendFile(String message) {
        try {
            FileWriter fWriter = new FileWriter(this.fList, true);
            fWriter.write(message + System.lineSeparator());
            fWriter.close();
        } catch (IOException e) {
            throw new GBotException("File Append Error!");
        }
    }

    /**
     * Updates (with overwriting) tasks in the file.
     *
     * @param list The list of tasks to update the file.
     */
    public void updateFile(ArrayList<Task> list) {
        try {
            FileWriter fWriter = new FileWriter(this.fList);
            for (int i = 0; i < list.size(); i++) {
                Task curr = list.get(i);
                fWriter.write(curr.toStringForFile() + System.lineSeparator());
            }
            fWriter.close();
        } catch (IOException e) {
            throw new GBotException("File Update Error!");
        }
    }
}
