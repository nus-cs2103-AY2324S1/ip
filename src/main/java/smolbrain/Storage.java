package smolbrain;

import smolbrain.exception.*;
import smolbrain.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the save file with creation, editing and loading functionalities.
 */
public class Storage {

    private String FILE_PATH;

    /**
     * Creates a storage object.
     *
     * @param filePath Filepath of the save file.
     */
    public Storage(String filePath) {
        FILE_PATH = filePath;
    }

    /**
     * Appends the text passed into the save file.
     *
     * @param text Text to append to save file.
     * @throws IOException If there is problems accessing the save file.
     */
    public void appendToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
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
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(text);
        fw.close();
    }


    /**
     * Loads the save file contents for the chatbot.
     *
     * @return ArrayList of tasks from the save file.
     */
    public ArrayList<Task> load() {

        File f = new File(FILE_PATH);
        ArrayList<Task> tasklist = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();

        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                strings.add(s.nextLine());
            }

            for (int i = 0; i < strings.size(); i++) {
                String type = strings.get(i).substring(0, 1);
                String marked = strings.get(i).substring(1, 2);
                String remain = strings.get(i).substring(2);
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
                }

                try {
                    Task task =  Parser.parseLoading(txt);
                    tasklist.add(task);
                    if (marked.equals("1")) {
                        task.mark();
                    }
                } catch (InvalidNumberException | InvalidRangeException | MissingTimeException |
                         InvalidDateTimeException | MissingDescriptionException e) {
                    new Ui().showError(e);
                }
            }
        } catch (FileNotFoundException e) {
            try {
                f.createNewFile();
            } catch (IOException e1) {
                new Ui().showError(e1);
            }
        }
        return tasklist;
    }

}
