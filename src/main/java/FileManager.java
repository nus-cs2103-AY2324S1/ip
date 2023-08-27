import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.io.IOException;

public class FileManager {
    private final static String DEFAULT_TASK_FILE_PATH = "src/main/data/duke.txt";
    public FileManager(){
    }

    public void saveFile(String content) {
        try {
            FileWriter myWriter = new FileWriter(DEFAULT_TASK_FILE_PATH);
            myWriter.write(content);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (FileNotFoundException e) {
            File f = new File("src/main/data");
            if (f.mkdir()) {
                saveFile(content);
            } else {
                System.out.println("An error occurred. File is not written.");
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred. File is not written.");
            e.printStackTrace();
        }
    }

    public String readFile() throws FileNotFoundException {
        try {
            File myObj = new File(DEFAULT_TASK_FILE_PATH);
            Scanner myReader = new Scanner(myObj);
            StringBuilder data = new StringBuilder();
            while (myReader.hasNextLine()) {
                data.append(myReader.nextLine()).append("\n");
            }
            myReader.close();
            return data.toString();
        } catch (FileNotFoundException e) {
            return "";
        }
    }

    public void loadFileToTaskManager(TaskManager taskManager) {
        try {
            String savedText = readFile();
            Scanner scanner = new Scanner(savedText);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // line = "A. [B][C] Details" where A is index of task, B is type of task, C is mark status
                String[] lineSplit = line.split("\\[");
                int taskIndex = Integer.parseInt(lineSplit[0].split("\\.")[0]);
                String taskType = lineSplit[1].substring(0,1);
                boolean markStatus = lineSplit[2].charAt(0) == 'X';
                String taskDetails = lineSplit[2].split("] ")[1];
                if (taskType.equals("T")) {
                    // create to do task
                    ToDo td = new ToDo(taskDetails, markStatus);
                    taskManager.addTask(td);
                } else if (taskType.equals("D")) {
                    // create deadline task
                    String[] taskDetailsSplit = taskDetails.split(" \\(by: ");
                    String description = taskDetailsSplit[0];
                    String by = taskDetailsSplit[1].substring(0, taskDetailsSplit[1].length()-1);
                    Deadline d = new Deadline(description, markStatus, by);
                    taskManager.addTask(d);
                } else if (taskType.equals("E")) {
                    // Extract description from task details
                    String[] taskDetailsSplit = taskDetails.split(" \\(from: ");
                    String description = taskDetailsSplit[0];

                    // Split details into from and to
                    String[] taskDetailsSplit2 = taskDetailsSplit[1].split(" to: ");
                    String from = taskDetailsSplit2[0];
                    String to = taskDetailsSplit2[1]
                                .substring(0, taskDetailsSplit[1].split(" to: ")[1].length()-1);
                    Event e = new Event(description, markStatus, from, to);
                    taskManager.addTask(e);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
