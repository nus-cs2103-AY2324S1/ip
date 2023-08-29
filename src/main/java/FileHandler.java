import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    public static Task parseFile(String s) throws DukeException {
        String[] queries = s.trim().split("\\s+");
        switch (queries[0]) {
        case "deadline":
            return Deadline.create(queries);
        case "event":
            return Event.create(queries);
        case "todo":
            return ToDo.create(queries);
        }
        throw new DukeException("Error parsing file data");
    }

    public static ArrayList<Task> read() throws DukeException {
        String home = System.getProperty("user.dir");
        Path path = Paths.get(home, "data", "duke.txt");
        String filePath = path.toString();
        ArrayList<Task> tasks = new ArrayList<Task>();
        if (Files.exists(path)) {
            File f = new File(filePath);
            try {
                Scanner s = new Scanner(f);
                while (s.hasNextLine()) {
                    String nextLine = s.nextLine();
                    if (nextLine.equals("")) {
                        continue;
                    }
                    tasks.add(parseFile(nextLine));
                }
                s.close();
            } catch (FileNotFoundException e) {
                throw new DukeException(e.getMessage());
            }
        }
        return tasks;
    }

    private static void writeToFile(String textToAdd) throws DukeException {
        String home = System.getProperty("user.dir");
        Path folder = Paths.get(home, "data");
        if (!Files.exists(folder)) {
            try {
                Files.createDirectories(folder);
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        }
        Path path = Paths.get(home, "data", "duke.txt");
        String filePath = path.toString();
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static void write(ArrayList<Task> tasks) throws DukeException {
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
