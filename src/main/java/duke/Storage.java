package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.exception.DukeBadInputException;
import duke.task.Task;



/**
 * Handles the loading of tasks from the file and saving tasks in the file
 */
public class Storage {

    /**
     * Path of the storage file
     */
    private Path path;

    /**
     * The input string that points to the file
     */
    private String input;

    /**
     * Writer instance
     */
    private BufferedWriter writer;

    /**
     * Constructs a new instance of a duke.Storage object that reads and write to a specific file
     *
     * @param fileName - the file which your reading/writing to
     * @throws IOException           - thrown when there is an IOException
     * @throws DukeBadInputException - thrown when the input provided does not point to a file
     */
    public Storage(String fileName) throws IOException, DukeBadInputException {
        this.input = fileName;
        String home = System.getProperty("user.dir");
        this.path = Paths.get(home, fileName.split("/"));
        this.checkFile();
        this.writer = Files.newBufferedWriter(this.path, StandardOpenOption.APPEND);
    }

    /**
     * Ensures that default data storage is present
     *
     * @throws IOException           if there is an IOException
     * @throws DukeBadInputException if the path does not lead to a file
     */
    private void checkFile() throws IOException, DukeBadInputException {
        String[] dir = this.input.split("/");
        String home = System.getProperty("user.dir");

        if (!this.input.endsWith(".txt")) {
            throw new DukeBadInputException(this.path.toString());
        }

        if (!Files.exists(this.path)) {
            // Handles folder not existing
            for (int i = 1; i < dir.length; i++) {
                Path tmp = Paths.get(home, Arrays.copyOfRange(dir, 0, i));
                if (!Files.exists(tmp)) {
                    Files.createDirectories(tmp);
                }
            }
            // handles file not existing
            Files.createFile(this.path);
        }
    }

    /**
     * Reads and parse the storage file
     *
     * @return a duke.Parser array storing the parsed input
     * @throws IOException thrown if the file cannot be open or read
     */
    public List<String> readFile() throws IOException {
        BufferedReader reader = Files.newBufferedReader(this.path);
        List<String> ret = reader.lines().collect(Collectors.toList());
        reader.close();
        return ret;
    }

    /**
     * Writes the given line to storage
     *
     * @param input string that is you want to write
     * @return true if successfully written
     */
    public boolean writeToFile(String input) {
        try {
            this.writer.append(input);
            this.writer.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Rewrites the storage to contain all the current tasks stored in memory
     *
     * @param tasks - list of task to be written in
     * @return true if carried out successfully
     * @throws IOException if there is an IOException
     */
    public boolean rewriteAll(Task[] tasks) throws IOException {
        this.writer.flush();
        BufferedWriter tmpWriter = Files.newBufferedWriter(this.path,
                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        boolean isSuccessful = true;

        // rewrite for each
        for (Task t : tasks) {
            try {
                tmpWriter.write(t.getStored());
                tmpWriter.newLine();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                isSuccessful = false;
            }
        }
        tmpWriter.close();
        return isSuccessful;
    }

    /**
     * Close the connection to the storage and deallocate all resources
     */
    public void close() {
        try {
            this.writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
