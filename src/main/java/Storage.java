import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    private final String filePath = "./data/duke.txt";

    public void createFile() {
        File dataFile = new File(filePath);
        File dataDirectory = new File("./data");
        dataDirectory.mkdir();
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to create file to save commands");
        }
    }

    public void saveCommandsToFile(TaskList list) throws DukeException {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(list.toLogString());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Unable to save command to file");
        }
    }

    public ArrayList<Task> loadFile() throws DukeException {
        File dataFile = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(dataFile);
            while (fileReader.hasNextLine()) {
                String[] commandArray = fileReader.nextLine().split("\\|");
                Task newTask;
                switch (commandArray[0]) {
                case "T":
                    newTask = new Todo(commandArray[2]);
                    break;
                case "D":
                    newTask = new Deadline(commandArray[2], commandArray[3]);
                    break;
                case "E":
                    newTask = new Event(commandArray[2], commandArray[3], commandArray[4]);
                    break;
                default:
                    throw new DukeException("Something is wrong with the data file, delete the data file to reset");
                }
                if (Objects.equals(commandArray[1], "X")) {
                    newTask.markDone();
                }
                taskList.add(newTask);
            }
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException("Data file is corrupted, cannot load from file");
        } catch (FileNotFoundException e) {
            createFile();
        }
        return taskList;
    }
}
