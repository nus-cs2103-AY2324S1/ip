import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    String filePath;
    File file;

    /**
     * Constructs a storage object.
     * @param filePath Path of file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a directory and the file inside it.
     * @param file the file object.
     * @throws IOException
     */
    public void createFile(File file) throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Loads a list of tasks from the file. If the directory/file is not present, it will be created,
     * and then an exception will be thrown.
     * @return a list of tasks from the file.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public List<Task> load() throws FileNotFoundException, IOException {
        file = new File(filePath);
        if (!file.exists()) {
            try {
                createFile(file);
            } catch(IOException e) {
                throw new IOException();
            }
            throw new FileNotFoundException();
        }
        Scanner s = new Scanner(file);
        List<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            String str = s.nextLine();
            String[] arr = str.split(" \\| ");
            switch (arr[0]) {
                case "T":
                    tasks.add(new ToDo(arr[2], arr[1].equals("1")));
                    break;

                case "D":
                    tasks.add(new Deadline(arr[2], arr[1].equals("1"), arr[3]));
                    break;

                case "E":
                    String[] tmp = arr[3].split(" - ");
                    String from = tmp[0];
                    String to = tmp[1];
                    tasks.add(new Event(arr[2], arr[1].equals("1"), from, to));
                    break;
            }
        }
        return tasks;
    }

    /**
     * Writes a list of tasks to the file.
     * @param tasks the list of tasks.
     * @throws IOException
     */
    public void writeToFile(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for(Task task: tasks) {
            fw.write(task.toStringStorage() + "\n");
        }
        fw.close();
    }
}
