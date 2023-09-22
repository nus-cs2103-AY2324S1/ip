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
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/** This class implements the {@link IStorage} interface. */
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
            assert dataFile.exists() : "data file should exist";
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
            List<Task> tasks = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String encodedTask = scanner.nextLine();
                assert encodedTask.length() > 0 : "encoded task should not be empty";
                Task task = decode(encodedTask);
                tasks.add(task);
            }
            scanner.close();
            return tasks;
        } catch (IOException e) {
            throw new ReadFromFileException();
        }
    }

    private static Task decode(String encodedTask) throws InvalidArgumentException {
        String[] varargs = encodedTask.split(" \\| ");
        assert varargs.length >= 4 : "encoded task should have at least 3 parts "
                + "(type, isDone, tags, description)";
        Task task;

        // decode the task type.
        String taskType = varargs[0];
        assert taskType.equals("T") || taskType.equals("D") || taskType.equals("E")
                : "encoded task should start with T, D or E";
        switch (taskType) {
        case "T":
            task = new Todo(varargs[3]);
            break;
        case "D":
            task = new Deadline(varargs[3], varargs[4]);
            break;
        case "E":
            task = new Event(varargs[3], varargs[4], varargs[5]);
            break;
        default:
            // the program should never reach this point.
            return null;
        }

        // decode whether the task is done.
        String isDone = varargs[1];
        if (isDone.equals("1")) {
            task.setDone();
        }

        // decode the tags associated with the task.
        String stringOfTags = varargs[2];
        if (!stringOfTags.equals("")) {
            // the tags currently have a leading "#" character.
            String[] tagsWithHashes = stringOfTags.split(" ");
            // remove the leading "#" character.
            String[] tags = Arrays.stream(tagsWithHashes).map(s -> s.substring(1)).toArray(String[]::new);
            task.addTags(tags);
        }

        return task;
    }
}
