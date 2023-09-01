package duke.storage;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import duke.task.*;
import duke.parser.TaskParser;

public class SaveData {
    private static final String SAVE_FILE_LOCATION = "./SaveFile.txt";
    public static void saveData(String taskData) {
        File f = new File(SAVE_FILE_LOCATION);
        try {
            if (f.createNewFile()) {
                FileWriter fw = new FileWriter(SAVE_FILE_LOCATION);
                fw.write(taskData);
                fw.close();
            } else {
                if (f.delete()) {
                    FileWriter fw = new FileWriter(SAVE_FILE_LOCATION);
                    fw.write(taskData);
                    fw.close();
                } else {
                    throw new Exception("The file cannot be deleted!");
                }
            }
        } catch (Exception e) {

        }
    }

    public static ArrayList<Task> loadData() {
        File f = new File(SAVE_FILE_LOCATION);
        ArrayList<Task> tasks = new ArrayList();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                Optional<Task> optionalTask = TaskParser.parseSave(sc.nextLine());
                if (!optionalTask.isEmpty()) {
                    optionalTask.ifPresent(x -> tasks.add(x));
                }
            }
            sc.close();
        } catch (Exception e) {

        }
        return tasks;
    }

}
