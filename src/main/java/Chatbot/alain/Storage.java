package chatbot.alain;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles the loading and saving of tasks, as well as processing user inputs for the Alain chatbot.
 */
public class Storage {
    private String filePath;
    private Ui ui = new Ui();
    private Boolean alrBye;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath The path to the file containing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.alrBye = false;
    }

    /**
     * Checks if the chatbot has received a "bye" command.
     *
     * @return True if the chatbot has received a "bye" command, false otherwise.
     */
    public Boolean isBye() {
        return this.alrBye;
    }
    /**
     * turn this.alrBye to true after detecting "bye"
     *
     */
    public void sayBye() {
        this.alrBye = true;
    }

    /**
     * Saves the task list to a file with optional exception message.
     *
     * @param list The task list to save.
     * @param fileName The name of the file to save to.
     * @param except Whether an exception has occurred.
     * @param msg The exception message.
     * @throws IOException If an I/O error occurs.
     */
    public static void saveTasksToFile(TaskList list, String fileName, Boolean except, String msg) throws IOException {
        File listFile = new File(fileName);
        if (!listFile.exists()) {
            listFile.createNewFile();
        }
        FileWriter writer = new FileWriter(listFile, false);
        String filecontent = "";
        if (except) {
            filecontent += "Oops! Seems like there is an exception detected in your input\n";
            filecontent += msg + "\n";
        } else {
            filecontent += "____________________________________________________________\n"
                    + "Here are the tasks in your list:\n";
            for (int i = 0; i < list.size(); i++) {
                filecontent += " " + (i + 1) + ". " + list.getTask(i) + "\n";
            }
            filecontent += "____________________________________________________________\n";
        }
        writer.write(filecontent);
        writer.close();
    }

    /**
     * Loads tasks from a file and processes user input.
     *
     * @return The task list loaded from the file.
     * @throws IOException If an I/O error occurs.
     */
    public TaskList loadTasksFromFile() throws IOException, AlainException {
        ui.showWelcome();
        ArrayList<Task> tasks = new ArrayList<>();
        TaskList list = new TaskList(tasks);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String text;
        while ((text = reader.readLine()) != null) {
            ChatbotAlain.proccessCommands(list, text, this);
        }
        return list;
    }
}
