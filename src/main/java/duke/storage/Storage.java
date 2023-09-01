package duke.storage;

import duke.exception.DukeNotTaskException;
import duke.task.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String pathFile = "./data/TaskStorage.txt";

    private File file = new File(pathFile);
    public ArrayList<Task> readFile() throws Exception {
        Scanner sc = new Scanner(file);
        ArrayList<Task> readFile = new ArrayList<>();

        while (sc.hasNextLine()) {
            String scanned = sc.nextLine();
            String[] singleTaskArray = scanned.split(" / ");

            //do a checking <------

            Task task;
            switch (singleTaskArray[0]) {
                case "T":
                    task  = new Todo(singleTaskArray[2]);
                    break;
                case "D":
                    task = new Deadline(singleTaskArray[2], singleTaskArray[3]);
                    break;
                case "E":
                    task = new Event(singleTaskArray[2], singleTaskArray[3], singleTaskArray[4]);
                    break;
                default:
                    throw new DukeNotTaskException(singleTaskArray[0]);
            }

            if (singleTaskArray[1].equals("1")) {
                task.mark();
            }

            readFile.add(task);
        }

        return readFile;
    }

    public void writeFile(TaskList taskList) throws Exception {
        String newData = arrayToDataString(taskList);

        try {
            FileWriter fileWriter = new FileWriter(pathFile, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(newData);

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void checkFile() throws Exception {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(pathFile);
            if (!file.exists()) {
                boolean created = file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String arrayToDataString(TaskList taskList) {
        String dataString = "";

        for (int i = 0; i < taskList.size(); i++) {
            dataString += taskList.get(i).toDataString() + "\n";
        }
        return dataString;
    }
}
