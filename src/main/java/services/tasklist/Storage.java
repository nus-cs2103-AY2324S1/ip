package services.tasklist;

import services.bizerrors.CreateNewFileException;
import services.bizerrors.InvalidArgumentException;
import services.bizerrors.ReadFromFileException;
import services.bizerrors.SaveToFileException;
import services.tasklist.tasks.Deadline;
import services.tasklist.tasks.Event;
import services.tasklist.tasks.Task;
import services.tasklist.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage implements IStorage {
    /** The file that stores the list of tasks. */
    private File dataFile;

    /**
     * Creates a new Storage object with the data file at the given path.
     * If the file does not exist, a new file is created.
     *
     * @param dataFilePath the path of the file that stores the task list.
     * @throws CreateNewFileException if there is an error creating a new file.
     */
    public Storage(String dataFilePath) throws CreateNewFileException {
        try {
            File file = new File(dataFilePath);
            File parentDir = file.getParentFile();
            if (!parentDir.exists() && !parentDir.mkdirs()) {
                throw new CreateNewFileException();
            }
            file.createNewFile();
            dataFile = file;
        } catch (IOException e) {
            throw new CreateNewFileException();
        }
    }

    @Override
    public void save(List<Task> tasks) throws SaveToFileException {
        try {
            FileWriter fileWriter = new FileWriter(dataFile);
            for (Task task : tasks) {
                fileWriter.write(task.encode() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new SaveToFileException();
        }
    }

    @Override
    public List<Task> load() throws ReadFromFileException, InvalidArgumentException {
        try {
            Scanner scanner = new Scanner(dataFile);
            List<Task> taskList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String encodedTask = scanner.nextLine();
                Task task;
                String[] varargs = encodedTask.split(" \\| ");
                switch (encodedTask.charAt(0)) {
                case 'T':
                    task = new Todo(varargs[2]);
                    break;
                case 'D':
                    task = new Deadline(varargs[2], varargs[3]);
                    break;
                case 'E':
                    task = new Event(varargs[2], varargs[3], varargs[4]);
                    break;
                default:
                    return null;
                }

                if (varargs[1].equals("1")) {
                    task.setDone();
                }
                taskList.add(task);
            }
            scanner.close();
            return taskList;
        } catch (IOException e) {
            throw new ReadFromFileException();
        }
    }
}
