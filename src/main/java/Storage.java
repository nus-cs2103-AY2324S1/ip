import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {

    public static final String FILE_DIRECTORY = "./data";
    public static final String FILE_NAME = "TaskData.txt";
    public static final String FILE_LOCATION = FILE_DIRECTORY + File.separator + FILE_NAME;

    /**
     * Creates directory and txt file for storing task data if they do not exist, else does nothing.
     */
    private static void openFile() {
        File directory = new File(FILE_DIRECTORY);
        File dataFile = new File(FILE_LOCATION);
        try {
            directory.mkdir();
            dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write data from ArrayList of tasks to a file.
     * @param taskList An ArrayList of tasks.
     */
    public static void writeToFile(ArrayList<Task> taskList) {
        try {
            FileWriter file = new FileWriter(FILE_LOCATION);
            StringBuilder fileData = new StringBuilder();
            for (Task task : taskList) {
                String taskString = task.toFileString() + "\n";
                fileData.append(taskString);
            }
            file.write(fileData.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads task data from txt file to chatbot.
     */
    public static void loadData() {
        Storage.openFile();

        try {
            File dataFile = new File(FILE_LOCATION);
            Scanner reader = new Scanner(dataFile);

            // Add error checking for wrong data format (probably as long as split works shld be fine)
            // Maybe add command to just delete data file and create new one if can't read
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] splitInput = data.split(" \\| ");
                boolean isDone = splitInput[1].equals("X");
                // Data is in format [type, isDone, title, from/by, to], from/by/to are only present depending on type.
                switch(splitInput[0]) {
                case "T":
                    Todo.addTodo(splitInput[2]).changeStatus(isDone);
                    break;
                case "D":
                    Deadline.addDeadline(splitInput[2], splitInput[3]).changeStatus(isDone);
                    break;
                case "E":
                    Event.addEvent(splitInput[2], splitInput[3], splitInput[4]).changeStatus(isDone);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
