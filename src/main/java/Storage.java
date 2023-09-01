import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Storage class handles reading and writing tasks data to/from a file.
 * It provides methods for saving tasks to a file and loading tasks from a file.
 */
public class Storage {
    /**
     * The file path where tasks data is stored.
     */
    protected String filePath;

    /**
     * The list of tasks where the tasks stored.
     */
    protected ArrayList<Task> tasksList;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks data will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks from a TaskList to a file.
     *
     * @param tasksList The TaskList containing tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveTasksInFile(TaskList tasksList) throws IOException {
        // Creates a File object representing the target file path
        File file = new File("./data/evo.txt");
        // Creates a BufferedWriter to write to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        try {
            // Iterates through the taskList and write each task's output message to the file
            for (int i = 0; i < tasksList.tasksListLength(); i++) {
                writer.append(tasksList.get(i).outputMsg());
                writer.append("\n");
            }
        } catch (InvalidDateAndTimeInputException invalidDateAndTimeInputException) {
            System.out.println("Please type in a valid date/time input. Eg: event project meeting /from " +
                    "2019-12-15 1800 /to 2000.\n");
        } catch (IOException ioException) {
            System.out.println("Something went wrong: " + ioException.getMessage() + "\n");
        } finally {
            // Close the writer
            writer.close();
        }
    }

    /**
     * Loads tasks from a file and returns them as an ArrayList of Task objects.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws EvoException If an error occurs during the loading process, including file not found or unexpected task type.
     */
    public ArrayList<Task> loadTasksFromFile() throws EvoException {
        try {
            this.tasksList = new ArrayList<Task>();
            // Loads the data from the file when the chatbot starts up
            File folder = new File(this.filePath);
            if (!folder.exists()) {
                throw new NoFolderFoundException();
            }
            File file = new File(this.filePath);
            if (!file.exists()) {
                throw new NoDataFileFoundException();
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] type = line.split(" \\| ");
                switch (type[0]) {
                case "T":
                    Task toDo = new ToDo(type[2]);
                    if (Integer.parseInt(type[1]) == 1) {
                        toDo.markAsDone();
                    }
                    this.tasksList.add(toDo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(type[2], type[3]);
                    if (Integer.parseInt(type[1]) == 1) {
                        deadline.markAsDone();
                    }
                    this.tasksList.add(deadline);
                    break;
                case "E":
                    Event event = new Event(type[2], type[3]);
                    if (Integer.parseInt(type[1]) == 1) {
                        event.markAsDone();
                    }
                    this.tasksList.add(event);
                    break;
                default:
                    throw new UnexpectedTaskTypeException();
                }
            }
        } catch (NoFolderFoundException noFolderFoundException) {
            // Catches the exception when the required folder does not exist
            System.out.println("The folder does not exist.\n");
        } catch (NoDataFileFoundException noDataFileFoundException) {
            // Catches the exception when the data file does not exist while you run
            System.out.println("The required data file does not exist.\n");
        } catch (UnexpectedTaskTypeException unexpectedTaskTypeException) {
            // Catches the exception when an unexpected task type was encountered when loading the task from txt file
            System.out.println("Unexpected task type encountered when loading the task from txt file.\n");
        } catch (IOException ioException) {
            System.out.println("Something went wrong: " + ioException.getMessage() + "\n");
        }
        return this.tasksList;
    }
}
