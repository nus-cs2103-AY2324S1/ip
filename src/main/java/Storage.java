import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        File dir = new File("./data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(filePath);
        f.createNewFile();
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            tasks.add(addTask(s.nextLine()));
        }
        return tasks;
    }

    private Task addTask(String input) {
        String taskType = input.split(" \\| ")[0];
        boolean isComplete = input.split(" \\| ")[1].equals("1");
        String description = input.split(" \\| ")[2];
        if (taskType.equals("T")) {
            return new ToDo(description, isComplete);
        } else if (taskType.equals("D")) {
            return new Deadline(description, isComplete, input.split(" \\| ")[3]);
        } else {
            return new Event(description, isComplete, input.split(" \\| ")[3], input.split(" \\| ")[4]);
        }
    }


    public void addToFile(ArrayList<Task> list) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            if (list.size() == 1) {
                fw.write(list.get(0).toTxt());
            } else {
                fw.write("\n" + list.get(list.size() - 1).toTxt());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write to file.");
        }
    }

    public void rewriteFile(ArrayList<Task> list) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    fw.write(list.get(i).toTxt());
                } else {
                    fw.write(list.get(i).toTxt() + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write to file.");
        }
    }
}

