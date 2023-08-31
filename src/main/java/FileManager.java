import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private final String pathName;
    ArrayList<Task> taskList = new ArrayList<>();

    public FileManager(String pathName) {
        this.pathName = pathName;
    }
    public ArrayList<Task> loadFile() throws ChatException{
        try {
            File f = new File(this.pathName);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String pastTaskLine = sc.nextLine();
                String[] pastTaskDetails = pastTaskLine.split(" \\| ");
                Task pastTask;
                switch (pastTaskDetails[0]) {
                    case "T":
                        pastTask = new Task(pastTaskDetails[2]);
                        taskList.add(pastTask);
                        if (pastTaskDetails[1].equals("1")) {
                            pastTask.setTaskState(true);
                        }
                        break;
                    case "D":
                        pastTask = new Deadline(pastTaskDetails[2], pastTaskDetails[3]);
                        taskList.add(pastTask);
                        if (pastTaskDetails[1].equals("1")) {
                            pastTask.setTaskState(true);
                        }
                        break;
                    case "E":
                        pastTask = new Event(pastTaskDetails[2], pastTaskDetails[3], pastTaskDetails[4]);
                        taskList.add(pastTask);
                        if (pastTaskDetails[1].equals("1")) {
                            pastTask.setTaskState(true);
                        }
                        break;
                    default:
                        throw new ChatException("☹ OOPS!!! The file is corrupted");
                }
            }
            return taskList;
        } catch (IOException e) {
            throw new ChatException("☹ OOPS!!! There are errors locating the file.");
        }
    }
    public void saveList(ArrayList<Task> taskList) throws ChatException {
        try {
            FileWriter fw = new FileWriter(this.pathName);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Task task : taskList) {
                bw.write(task.fileString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new ChatException("☹ OOPS!!! Tasks could not be saved.");
        }
    }

}
