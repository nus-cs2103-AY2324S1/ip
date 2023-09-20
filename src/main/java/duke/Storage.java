package duke;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Storage
 */
public class Storage {
    protected String filepath;
    protected BufferedReader reader;
    protected BufferedWriter writer;
    protected BufferedWriter saveWriter;
    protected TaskList tasks;

    /**
     * Constructor to create a new Storage object
     * @param filepath The filepath of the .txt file
     * @param tasks The list of tasks
     */
    public Storage(String filepath, TaskList tasks) {
        this.filepath = filepath;
        this.tasks = tasks;
    }

    /**
     * Starts reading the storage
     */
    public void startStorage() throws IOException {
        try {
            reader = new BufferedReader(new FileReader(filepath));
            Window.welcomeMessage();
            String fileLine;

            while ((fileLine = reader.readLine()) != null) {
                String eventType = Character.toString(fileLine.charAt(1));
                assert eventType.equals("T") || eventType.equals("D") || eventType.equals("E");
                String isDone = Character.toString(fileLine.charAt(4));
                assert isDone.equals("X") || isDone.equals(" ");
                String extractedSubstring = fileLine.substring(7, fileLine.length());
                EventTypeHandler handler = new EventTypeHandler(this.tasks, extractedSubstring, isDone);
                handler.handleEventType(eventType);
            }
            reader.close();
        } catch (IOException e) {
            writer = new BufferedWriter(new FileWriter("duke.txt"));
            Window.sendMessage("The file duke.txt does not exist. I've just created that file for you. Please close "
                    + "and reopen the program.");
        } catch (EmptyDescriptionException e) {
            //Already handled by EmptyDescriptionException
        }
    }

    /**
     * Method to write to the storage
     */

    public void writeToStorage() {
        try {
            writer = new BufferedWriter(new FileWriter(filepath, true));
            saveWriter = new BufferedWriter(new FileWriter(filepath));

            for (Task task : this.tasks.getTaskList()) {
                saveWriter.write(task.toString() + "\n");
            }
            writer.close();
            saveWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
