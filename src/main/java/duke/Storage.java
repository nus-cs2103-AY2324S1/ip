package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String folder;
    private String text;

    public Storage(String folder, String text) {
        this.folder = folder;
        this.text = text;
    }

    public ArrayList<String> loadFile() throws DukeException {
        ArrayList<String> list = new ArrayList<>();
        try {
            File directory = new File(folder);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(directory, text);
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                list.add(fileScanner.nextLine());
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found.");
        } catch (IOException e) {
            throw new DukeException("IOException occurred.");
        } catch (Exception e) {
            throw new DukeException("An error occurred.");

        }
        return list;
    }

    public void saveFile(TaskList taskList) throws DukeException {
        File directory = new File(folder);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directory, text);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            taskList.save(writer);
        } catch (Exception e) {
            throw new DukeException("An error occurred with saving.");
        }
    }
}
