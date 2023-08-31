import emiyaexception.CreateDirectoryFailException;
import emiyaexception.EmiyaException;
import emiyaexception.InvalidDateException;
import emiyaexception.WrongDateFormatException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    public Storage() {

    }

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

    public void createDirectory(String dirName) throws CreateDirectoryFailException {
        String path = Paths.get("").toAbsolutePath().toString();
        String pathToDataDir = Paths.get(path, dirName).toString();
        File dataDir = new File(pathToDataDir);

        if (!dataDir.exists()) {
            boolean result = dataDir.mkdirs();
            if (result) {
                System.out.println("Directory was created successfully");
                // return "Directory was created successfully";
            } else {
                throw new CreateDirectoryFailException();
                // return "Failed to create directory";
            }
        } else {
            System.out.println("Directory already exists");
            // return "Directory already exists";
        }
    }

    public void createFileInDirectory(String fileName, String dirName) {
        String path = Paths.get("").toAbsolutePath().toString();
        String pathToDataDir = Paths.get(path, dirName).toString();
        Path pathToDataDoc = Paths.get(pathToDataDir, fileName);
        String pathToDataDocStr = pathToDataDoc.toString();

        File dataDoc = new File(pathToDataDocStr);

        if (!dataDoc.exists()) {
//            System.out.println("File does not exist");
            String testData = "";
            try {
                Files.write(pathToDataDoc, testData.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File exists");
        }
    }

    public void writeToFileFromTaskList(ArrayList<Task> taskArrayList, String fileName, String dirName) {
        String path = Paths.get("").toAbsolutePath().toString();
        String pathToDataDir = Paths.get(path, dirName).toString();
        Path pathToDataDoc = Paths.get(pathToDataDir, fileName);

        StringBuilder str = new StringBuilder();
        for (Task task : taskArrayList) {
            str.append(task.typeOfString());
            str.append("| ");
            str.append(task.statusString());
            str.append("| ");
            str.append(task.taskDetailsString());
            str.append("\n");
        }

        try {
            Files.write(pathToDataDoc, str.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void fillListWithFileContent(ArrayList<Task> taskArrayList, String fileContent) throws WrongDateFormatException, InvalidDateException {
        String[] tasksStrArr = fileContent.split("\n");

        for (String tasksStr : tasksStrArr) {
            if (tasksStr.isEmpty()) {
                continue;
            }
            String[] tasksStrParts = tasksStr.split(" \\| ");
            String taskType = tasksStrParts[0];
            int completedInt = Integer.parseInt(tasksStrParts[1]);
            boolean completedBool = (completedInt == 1);
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
                taskArrayList.add(new ToDo(completedBool, taskDetails));
            } else if (taskType.equals("D")) {
                taskArrayList.add(new Deadline(completedBool, taskDetails, firstDate));
            } else {
                taskArrayList.add(new Event(completedBool, taskDetails, firstDate, secondDate));
            }
        }
    }
}
