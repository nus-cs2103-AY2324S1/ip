package ax.storage;

import ax.task.Deadlines;
import ax.task.Events;
import ax.task.TaskList;
import ax.task.Todos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    /**
     * Reads the save file and stores the previous save into the current list
     */
    public static void readSave() {
        // read save file
        Path path = Paths.get("data/save.txt");
        try {
            File saveFile = path.toFile();
            Scanner scanner = new Scanner(saveFile);
            // Holds true till there is nothing to read
            while (scanner.hasNextLine()) {
                String info = scanner.nextLine();
                // check if index 5 is blank space
                boolean marked = info.charAt(5) != ' ';
                // check if index 1 is T D or E
                int startIdx = 8;
                if (info.charAt(1) == 'T') {
                    TaskList.getListItems().add(new Todos(info.substring(startIdx)));
                } else if (info.charAt(1) == 'D') {
                    // find first ( in info
                    int idx = info.indexOf('(') - 1;
                    int end = info.indexOf(')');
                    TaskList.getListItems().add(new Deadlines(info.substring(startIdx, idx), info.substring(idx + 6, end)));
                } else if (info.charAt(1) == 'E') {
                    // find first ( in info
                    int idx = info.indexOf('(') - 1;
                    int end = info.indexOf(')');
                    int fromEnd = info.indexOf("from: ") + 6;
                    int toEnd = info.indexOf("to: ") + 4;
                    int toStart = info.indexOf("to: ") - 1;
                    TaskList.getListItems().add(new Events(info.substring(startIdx, idx), info.substring(fromEnd, toStart), info.substring(toEnd, end)));
                }
                TaskList.getListItems().get(TaskList.getListItems().size() - 1).setDone(marked);

            }
        } catch (FileNotFoundException e) {
            // create the file
            File saveFile = path.toFile();
            Path dir = Paths.get("data/");
            try {
                Files.createDirectory(dir);
                saveFile.createNewFile();
            } catch (IOException ex) {
                System.out.println("ex = " + ex);
            }
        } catch (Exception e) {
            System.out.println("error = " + e);
        }
    }
}