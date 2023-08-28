import emiyaexception.CreateDirectoryFailException;
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

/*
    need to create method to parse through storage file
    follow given format:
    T | 1 | read book
    D | 0 | return book | June 6th
    E | 0 | project meeting | Aug 6th 2-4pm
    T | 1 | join sports club
 */
    public void writeToFileFromTaskList (ArrayList<Task> taskArrayList, String fileName, String dirName) {
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

}
