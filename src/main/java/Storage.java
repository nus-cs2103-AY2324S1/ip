import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String home;
    private Path path;
    private String filePath;

    Storage() {
        this.home = System.getProperty("user.dir");
        this.path = Paths.get(home, "data", "duke.txt");
        this.filePath = this.path.toString();
    }

    Storage(String filePath) {
        this.home = System.getProperty("user.dir");
        this.path = Paths.get(home, filePath);
        this.filePath = filePath;
    }

    TaskList read() throws DukeException {
        TaskList tasks = new TaskList();
        if (Files.exists(this.path)) {
            File f = new File(this.filePath);
            try {
                Scanner s = new Scanner(f);
                while (s.hasNextLine()) {
                    String nextLine = s.nextLine();
                    if (nextLine.equals("")) {
                        continue;
                    }
                    tasks.add(Parser.parseFile(nextLine));
                }
                s.close();
            } catch (FileNotFoundException e) {
                throw new DukeException("Error reading file");
            }
        }
        return tasks;
    }

    private void writeToFile(String textToAdd) throws DukeException {
        Path folder = this.path.getParent();
        if (!Files.exists(folder)) {
            try {
                Files.createDirectories(folder);
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error writing to file");
        }
    }

    void write(ArrayList<Task> tasks) throws DukeException {
        String data = "";
        for (int i = 0; i < tasks.size(); i++) {
            data += tasks.get(i).exportToText();
            if (i < tasks.size() - 1) {
                data += System.lineSeparator();
            }
        }
        writeToFile(data);
    }
}
