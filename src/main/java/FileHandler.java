import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    public static Task parseFile(String s) throws DukeException {
        String[] q = s.trim().split(",>");
        try {
            switch (q[0]) {
            case "deadline":
                return new Deadline(q[1], q[2].equals("X"), LocalDate.parse(q[3]));
            case "event":
                return new Event(q[1], q[2].equals("X"), LocalDate.parse(q[3]), LocalDate.parse(q[4]));
            case "todo":
                return new ToDo(q[1], q[2].equals("X"));
            }
        } catch (DateTimeException e) {
            throw new DukeException("Error parsing date in file");
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
