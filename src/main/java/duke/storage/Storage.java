package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.task.Task;

public class Storage {
    private String filepath;
    private static final String LINE = "___________________________________\n";

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> temp = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(this.filepath))) {
            while (fileScanner.hasNextLine()) {
                String taskString = fileScanner.nextLine();
                String[] arr = taskString.split("\\|");
                Task.readListFromFile(arr, temp);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Looks like this is your first time!\nLets start with a new list!\n" + LINE);
        }
        return temp;
    }

    public void saveDataToFile(ArrayList<Task> list) {
        File folder = new File("./data/");
        if (!folder.exists()) folder.mkdirs();
        try (PrintWriter writer = new PrintWriter(this.filepath)) {
            for (Task task : list) {
                writer.println(task.toStringFile());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
