package smolbrain;

import static java.lang.Integer.parseInt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import smolbrain.exception.InvalidDateTimeException;
import smolbrain.exception.InvalidNumberException;
import smolbrain.exception.InvalidRangeException;
import smolbrain.exception.MissingDescriptionException;
import smolbrain.exception.MissingTimeException;
import smolbrain.task.Task;

/**
 * Manages the save file with creation, editing and loading functionalities.
 */
public class Storage {

    private String filePath;
    private Ui ui;

    /**
     * Creates a storage object.
     *
     * @param filePath Filepath of the save file.
     * @param ui Ui object used for this application.
     */
    public Storage(String filePath, Ui ui) {
        assert !filePath.equals("") : "No file path was provided for data storage";
        this.filePath = filePath;
        this.ui = ui;
    }

    /**
     * Appends the text passed into the save file.
     *
     * @param text Text to append to save file.
     * @throws IOException If there is problems accessing the save file.
     */
    public void appendToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(text);
        fw.close();
    }

    /**
     * Writes the text passed into the save file, overwriting any previous data.
     *
     * @param text Text to write to save file.
     * @throws IOException If there is problems accessing the save file.
     */
    public void writeToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(text);
        fw.close();
    }


    /**
     * Loads the save file contents for the chatbot.
     *
     * @return ArrayList of tasks from the save file.
     */
    public ArrayList<Task> load() {
        File f = new File(filePath);
        ArrayList<Task> tasklist = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                strings.add(s.nextLine());
            }
            processString(strings, tasklist);
        } catch (FileNotFoundException e) {
            try {
                f.createNewFile();
                ui.showMessage("Welcome new user!!!");
            } catch (IOException e1) {
                ui.showError(e1);
            }
        }
        return tasklist;
    }

    private void processString(ArrayList<String> strings, ArrayList<Task> tasklist) {
        for (int i = 0; i < strings.size(); i++) {
            String type = strings.get(i).substring(0, 1);
            String marked = strings.get(i).substring(1, 2);
            int priorityLevel = parseInt(strings.get(i).substring(2, 3));
            String remain = strings.get(i).substring(3);
            String txt = "";
            switch (type) {
            case "T":
                txt = "todo " + remain;
                break;
            case "D":
                txt = "deadline " + remain;
                break;
            case "E":
                txt = "event " + remain;
                break;
            default:
                break;
            }
            run(txt, tasklist, marked, priorityLevel);
        }
    }

    private void run(String txt, ArrayList<Task> tasklist, String marked, int priorityLevel) {
        try {
            Task task = Parser.parseLoading(txt);
            tasklist.add(task);
            if (marked.equals("1")) {
                task.mark();
            }
            task.setPriorityLevel(priorityLevel);
        } catch (InvalidNumberException | InvalidRangeException | MissingTimeException
                 | InvalidDateTimeException | MissingDescriptionException e) {
            ui.showError(e);
        }
    }
}
