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
            Path saveFilepath = path;
            File saveFile = saveFilepath.toFile();
            Scanner scanner = new Scanner(saveFile);
            // Declaring loop variable
            int i;
            // Holds true till there is nothing to read
            while (scanner.hasNextLine()) {
                String info = scanner.nextLine();
                // check if index 5 is blank space
                boolean marked = info.charAt(5) != ' ';
                // check if index 1 is T D or E
                int startidx = 8;
                if (info.charAt(1) == 'T') {
                    TaskList.listItems.add(new Todos(info.substring(startidx)));
                } else if (info.charAt(1) == 'D') {
                    // find first ( in info
                    int idx = info.indexOf('(') - 1;
                    int end = info.indexOf(')');
                    TaskList.listItems.add(new Deadlines(info.substring(startidx, idx), info.substring(idx + 6, end)));
                } else if (info.charAt(1) == 'E') {
                    // find first ( in info
                    int idx = info.indexOf('(') - 1;
                    int end = info.indexOf(')');
                    int fromend = info.indexOf("from: ") + 6;
                    int toend = info.indexOf("to: ") + 4;
                    int tostart = info.indexOf("to: ") - 1;
                    TaskList.listItems.add(new Events(info.substring(startidx, idx), info.substring(fromend, tostart), info.substring(toend, end)));
                }
                TaskList.listItems.get(TaskList.listItems.size() - 1).setDone(marked);

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