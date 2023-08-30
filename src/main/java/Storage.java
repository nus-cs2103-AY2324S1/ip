
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FOLDER_PATH = "./data";
    private static final String FILE_PATH = "./data/duke.txt";


    public ArrayList<Task> readTasks() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File folder = new File(FOLDER_PATH);
        File data = new File(FILE_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        if (!data.exists()) {
            return tasks;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(parseTasks(line));
            }
        } catch (IOException e) {
            throw new DukeException("There's something wrong with your save file! " + e.getMessage());
        }
        return tasks;
    }

    public void writeTasks(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fw.write(task.save() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("There was an issue saving your file! " + e.getMessage());
        }
    }

    public Task parseTasks(String data) {
        String[] parsedData = data.split("\\|");
        Task task = null;
        try{
            switch (parsedData.length) {
            case 3:
                task = new Todo(parsedData[2]);
                break;
            case 4:
                task = new Deadline(parsedData[2], parsedData[3]);
                break;
            default:
                task = new Event(parsedData[2], parsedData[3], parsedData[4]);
                break;
            }
            if (parsedData[1].equals("Y")) {
                task.markAsDone();
            }
            return task;
        } catch (DukeException e) {
            System.out.println("OOPS!" + e.toString().split("DukeException:")[1]);
        }
        return task;
    }
}
