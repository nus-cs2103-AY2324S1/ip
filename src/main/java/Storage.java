import java.io.*;
import java.util.Scanner;

public class Storage {
    private final File file;
    private static Storage instance;

    private Storage(String filePath) {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }
            this.file = f;
        } catch (IOException e) {
            throw new exceptions.IOException("Failed to create a new data file!");
        }
    }

    public static Storage getInstance(String filepath) {
        if (instance != null) {
            return instance;
        } else {
            instance = new Storage(filepath);
            return instance;
        }
    }

    public static Storage getInstance() {
        return instance;
    }

    public void loadData() {
        try (Scanner s = new Scanner(this.file)){
            String input;
            while (s.hasNextLine()) {
                input = s.nextLine();
                String[] taskLine = input.split("\\|");
                Tasklist.addTask(taskLine);
            }
        } catch (FileNotFoundException e) {
            String msg = String.format("I cannot find %s! May be accidental deletion, " +
                    "try restart Cheems!", file.getName());
            throw new exceptions.IOException(msg);
        }
    }

    public void saveData(String taskLine) {
        try {
            FileWriter fw = new FileWriter(this.file.getPath(), true);
            fw.write(taskLine + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new exceptions.IOException("I cannot open the file for writing!");
        }
    }

    public void delete(int lineToDelete) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            String content = "";
            int currentLine = 1;
            String line;

            while ((line = reader.readLine()) != null) {
                if (currentLine != lineToDelete + 1) {
                    content += line + System.lineSeparator();
                }
                currentLine++;
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));
            writer.write(content);
            writer.close();

            System.out.println("Task deleted successfully from storage.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mark(int lineToModify, boolean done) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            String content = "";
            int currentLine = 1;
            String line;

            while ((line = reader.readLine()) != null) {
                if (currentLine == lineToModify + 1) {
                    if (!line.isEmpty()) {
                        line = (done ? "1" : "0") + line.substring(1);
                    }
                }
                content += line + System.lineSeparator();
                currentLine++;
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));
            writer.write(content);
            writer.close();

            System.out.println("Task udpated successfully from storage.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}