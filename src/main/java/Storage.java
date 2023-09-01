import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// deals with loading tasks from the file and saving tasks in the file
public class Storage {
    private final File file;

    // finds the file at filePath, creates the file if is not already there
    public Storage(String filePath) throws DukeException {
        this.file = new File(filePath);
        boolean isNewDir = this.file.getParentFile().mkdirs();
        try {
            boolean isNewFile = this.file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Error creating new file to store the task list");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] parts = line.split(" \\| ");
                tasks.add(Parser.parseTask(parts));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error while loading tasks from storage");
        }
        return tasks;
    }


    public void save(String content) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
    }
}
