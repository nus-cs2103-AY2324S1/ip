import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;
import tasks.Task;

/**
 * The Storage class is responsible for reading and writing tasks to/from a file.
 * It provides methods to write a list of tasks to a file and load tasks from a file.
 */
public class Storage {
    protected String filePath;

    /**
     * Constructs a new Storage instance with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes a list of tasks to the file specified in the constructor.
     *
     * @param listOfTasks The list of tasks to be written to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void writeToFile(TaskList listOfTasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < listOfTasks.tasks.size(); i++) {
            // Check the type of each task and write it to the file with appropriate formatting
            if (listOfTasks.tasks.get(i) instanceof Event) {
                Event x = (Event) listOfTasks.tasks.get(i);
                if (listOfTasks.tasks.get(i).getStatus()) {
                    fw.write("E | 0 |" + listOfTasks.printName(i) + "|" + x.getStart() + "-" + x.getEnd() + "\n");
                } else {
                    fw.write("E | 1 |" + listOfTasks.printName(i) + "|" + x.getStart() + "-" + x.getEnd() + "\n");
                }
            }
            if (listOfTasks.tasks.get(i) instanceof ToDo) {
                if (listOfTasks.tasks.get(i).getStatus()) {
                    fw.write("T | 0 |" + listOfTasks.printName(i) + "\n");
                } else {
                    fw.write("T | 1 |" + listOfTasks.printName(i) + "\n");
                }
            }
            if (listOfTasks.tasks.get(i) instanceof Deadline) {
                Deadline x = (Deadline) listOfTasks.tasks.get(i);
                if (listOfTasks.tasks.get(i).getStatus()) {
                    fw.write("D | 0 |" + listOfTasks.printName(i) + "| " + x.getBy() + "\n");
                } else {
                    fw.write("D | 1 |" + listOfTasks.printName(i) + "| " + x.getBy() + "\n");
                }
            }
        }
        fw.close();
    }

    /**
     * Loads tasks from the file specified in the constructor and returns them as an ArrayList.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] parts = line.split("\\|");
            if (parts[0].equals("T ")) {
                ToDo temp = new ToDo(parts[2]);
                tasks.add(temp);
                if (parts[1].equals(" 0 ")) {
                    temp.markDone();
                }
            }
            if (parts[0].equals("D ")) {
                String[] x = parts[3].split(" ");
                Deadline temp = new Deadline(parts[2], x[1]);
                tasks.add(temp);
                if (parts[1].equals(" 0 ")) {
                    temp.markDone();
                }
            }
            if (parts[0].equals("E ")) {
                String[] duration = parts[3].split("-");
                Event temp = new Event(parts[2], duration[0], duration[1]);
                tasks.add(temp);
                if (parts[1].equals(" 0 ")) {
                    temp.markDone();
                }
            }
        }
        return tasks;
    }
}

