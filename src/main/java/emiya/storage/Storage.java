package emiya.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import emiya.emiyaexception.CreateDirectoryFailException;
import emiya.emiyaexception.InvalidDateException;
import emiya.emiyaexception.WrongDateFormatException;
import emiya.task.Deadline;
import emiya.task.Event;
import emiya.task.Task;
import emiya.task.TaskList;
import emiya.task.ToDo;

/**
 * A class that contains methods that handle reading from a file and writing to a file.
 */
public class Storage {
    public Storage() {

    }

    /**
     * A method that reads a text file and returns the contents of the file as a String.
     * @param fileName The name of the file that is to be accessed.
     * @param dirName The name of the directory that contains the file to be accessed.
     * @return A String containing the contents of the file indicated by fileName.
     */
    public String fileContents(String fileName, String dirName) {
        String path = Paths.get("").toAbsolutePath().toString();
        String pathToDataDir = Paths.get(path, dirName).toString();
        Path pathToDataDoc = Paths.get(pathToDataDir, fileName);
        String res = "";
        try {
            byte[] bytes = Files.readAllBytes(pathToDataDoc);
            String content = new String(bytes);
            res = content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * A method that creates a directory if it does not exist.
     * @param dirName The name of the directory to be created if it does not already exist.
     * @throws CreateDirectoryFailException When the directory cannot be created.
     */
    public void createDirectory(String dirName) throws CreateDirectoryFailException {
        String path = Paths.get("").toAbsolutePath().toString();
        String pathToDataDir = Paths.get(path, dirName).toString();
        File dataDir = new File(pathToDataDir);

        if (!dataDir.exists()) {
            boolean result = dataDir.mkdirs();
            if (!result) {
                throw new CreateDirectoryFailException();
            }
        }
    }

    /**
     * A method that creates an empty text file with a specified name inside a specified directory
     * if it does not already exist.
     * @param fileName The name of the file that is created if it does not already exist.
     * @param dirName The name of the directory in which the file should be created in.
     */
    public void createFileInDirectory(String fileName, String dirName) {
        String path = Paths.get("").toAbsolutePath().toString();
        String pathToDataDir = Paths.get(path, dirName).toString();
        Path pathToDataDoc = Paths.get(pathToDataDir, fileName);
        String pathToDataDocStr = pathToDataDoc.toString();

        File dataDoc = new File(pathToDataDocStr);

        if (!dataDoc.exists()) {
            String testData = "";
            try {
                Files.write(pathToDataDoc, testData.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * A method that writes the contents of a TaskList object into a specified file within a specified
     * directory.
     * @param taskList A TaskList instance that contains Tasks that need to be written into the
     *                 specified file.
     * @param fileName The name of the specific file that the contents of the TaskList has to be written
     *                 into.
     * @param dirName The name of the directory that contains the specified file.
     */
    public void writeToFileFromTaskList(TaskList taskList, String fileName, String dirName) {
        String path = Paths.get("").toAbsolutePath().toString();
        String pathToDataDir = Paths.get(path, dirName).toString();
        Path pathToDataDoc = Paths.get(pathToDataDir, fileName);

        StringBuilder str = new StringBuilder();
        for (Task task : taskList.getTaskArrayList()) {
            str.append(task.typeOfString());
            str.append("| ");
            str.append(task.printStatusString());
            str.append("| ");
            str.append(task.printTaskDetailsString());
            str.append("\n");
        }

        try {
            Files.write(pathToDataDoc, str.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * A method that is able to populate a given TaskList instance with the contents of a file,
     * which is given in a String.
     * @param taskList A TaskList instance in which the Tasks should be populated into.
     * @param fileContent A String that contains all the Tasks that should be written into the
     *                    TaskList instance.
     * @throws WrongDateFormatException When the format of the date received from the fileContent
     * is of the wrong format.
     * @throws InvalidDateException When the date received from the fileContent
     * is invalid.
     */
    public void fillListWithFileContent(TaskList taskList, String fileContent)
            throws WrongDateFormatException, InvalidDateException {
        String[] tasksStrArr = fileContent.split("\n");

        for (String tasksStr : tasksStrArr) {
            if (tasksStr.isEmpty()) {
                continue;
            }
            String[] tasksStrParts = tasksStr.split(" \\| ");
            String taskType = tasksStrParts[0];
            int isCompletedInt = Integer.parseInt(tasksStrParts[1]);
            boolean isCompletedBool = (isCompletedInt == 1);
            String taskDetails = tasksStrParts[2];
            String firstDate = "";
            String secondDate = "";
            if (tasksStrParts.length >= 4) {
                firstDate = tasksStrParts[3];
            }
            if (tasksStrParts.length == 5) {
                secondDate = tasksStrParts[4];
            }

            if (taskType.equals("T")) {
                taskList.add(new ToDo(isCompletedBool, taskDetails));
            } else if (taskType.equals("D")) {
                taskList.add(new Deadline(isCompletedBool, taskDetails, firstDate));
            } else {
                taskList.add(new Event(isCompletedBool, taskDetails, firstDate, secondDate));
            }
        }
    }
}
