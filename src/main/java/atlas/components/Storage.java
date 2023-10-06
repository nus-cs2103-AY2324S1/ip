package atlas.components;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import atlas.tasks.Task;

/**
 * Handler to load tasks from file and save tasks to file
 */
public class Storage {
    final String fileDir;
    final String fileName;

    /**
     * Constructs a storage object
     * @param fileDir Location of file to save to
     * @param fileName Name of file to save to
     */
    public Storage(String fileDir, String fileName) {
        assert fileName.endsWith(".txt");
        this.fileDir = fileDir;
        this.fileName = fileName;
    }

    /**
     * Loads tasks from file
     * @return list of tasks loaded from the file specified by getFilePath()
     */
    public List<Task> load() {
        try {
            Path loadPath = Paths.get(getFilePath());
            return readFromFile(loadPath);
        } catch (InvalidPathException e) {
            System.out.println("You dare set me on a fruitless journey, mortal? There is nothing here: "
                    + getFilePath());
        } catch (IOException e) {
            System.out.println("I cannot read this scroll, mortal");
        } catch (SecurityException e) {
            System.out.println("Zeus has barred me from reading this scroll, mortal.");
        }
        return new ArrayList<>();
    }

    /**
     * Constructs List of tasks from load path provided. This method is
     * abstracted out to utilise a try-block with resources.
     * @param loadPath Path to file containing the save
     * @return List of tasks read from file
     * @throws IOException Thrown if I/O error occurs while opening the file
     * @throws SecurityException Thrown if security violation encountered while
     *      opening the file
     */
    private List<Task> readFromFile(Path loadPath) throws IOException, SecurityException {
        try (Stream<String> fileStream = Files.lines(loadPath)) {
            return fileStream.map(Parser::parseFileTasks)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
    }

    /**
     * Returns relative file path of save file
     * @return Relative file path of save file
     */
    private String getFilePath() {
        return fileDir + fileName;
    }

    /**
     * Saves task list to file. The primary purpose of this function is to catch
     * exceptions (specifically IOExceptions) and add a wrapper to the error message.
     * @param taskList TaskList object containing tasks to write to file
     * @throws IOException I/O problem encountered, unable to save file
     */
    public void save(TaskList taskList) throws IOException {
        try {
            createFileIfNotExists();
            Path savePath = Paths.get(getFilePath());

            Stream<String> taskStringStream = taskList.getTasks()
                    .stream()
                    .map(task -> task.generateSaveString() + "\n");
            Files.write(savePath, (Iterable<String>) taskStringStream::iterator);
        } catch (InvalidPathException | IOException e) {
            throw new IOException("The scroll - " + fileName + " - cannot be produced, mortal.\n"
                    + e.getMessage());
        }
    }

    /**
     * Creates file if file is not found at filepath
     * @throws IOException Thrown if file cannot be created for some reason
     */
    protected void createFileIfNotExists() throws IOException {
        File saveDir = new File(fileDir);

        if (!saveDir.exists()) {
            try {
                boolean hasCreatedDirs = saveDir.mkdirs();
                if (hasCreatedDirs) {
                    System.out.println("Created parent directories");
                }

                File saveFile = new File(getFilePath());

                boolean hasCreatedFile = saveFile.createNewFile();
                if (hasCreatedFile) {
                    System.out.println("Created new scroll");
                }
            } catch (SecurityException e) {
                throw new IOException("Zeus has barred me from making the scroll, mortal.");
            }
        }
    }
}
