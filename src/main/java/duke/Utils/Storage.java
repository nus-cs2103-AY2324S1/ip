package duke.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    enum Type {
        INTEGER,
        STRING
    }
    private final String filepath;

    protected Storage(String filepath, String folderpath) {
        this.filepath = filepath;
        File file = new File(filepath);
        try {
            Files.createDirectories(Paths.get(folderpath));
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected ArrayList<Task> load() {
        ArrayList<String> taskData = new ArrayList<>();
        try {
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                taskData.add(line);
            }
            scanner.close();
            return FileIO.readCsv(taskData);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DukeException e) {
            System.out.println(Response.generate(e.toString()));
        }
        return new ArrayList<>();
    }

    protected void save(ArrayList<String> taskData) {
        try {
            FileWriter writer = new FileWriter(filepath);

            for (String line : taskData) {
                writer.write(line + "\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
