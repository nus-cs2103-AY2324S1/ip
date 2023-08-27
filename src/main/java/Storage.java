import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    void createFile() throws IOException {
        String[] temp = filePath.split("/");
        String directoryPath = "";
        for (int i = 0; i < temp.length - 1; i++) {
            directoryPath += temp[i];
            File directory = new File(directoryPath);
            if (!directory.isDirectory()) {
                directory.mkdir();
            }
            directoryPath += "/";
        }
        File file = new File(filePath);
        file.createNewFile();
    }

    File loadFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            createFile();
        }
        return file;
    }

    void writeFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.stringToFile() + "\n");
        }
        fw.close();
    }

    void appendFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(task.stringToFile() + "\n");
        fw.close();
    }

    ArrayList<Task> createList() throws DukeException {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            Scanner sc = new Scanner(loadFile());
            while (sc.hasNext()) {
                String[] temp = sc.nextLine().split(" \\| ");
                switch (temp[0].strip()) {
                case "T":
                    taskList.add(new ToDo(temp[2].strip()));
                    break;
                case "D":
                    taskList.add(new Deadline(temp[2].strip(), temp[3].strip()));
                    break;
                case "E":
                    taskList.add(new Event(temp[2].strip(), temp[3].strip(), temp[4].strip()));
                    break;
                default:
                    break;
                }
                if (temp[1].strip().equals("1")) {
                    taskList.get(taskList.size() - 1).markIsDone();
                }
            }
            return taskList;
        } catch (IOException e) {
            throw new DukeException("Loading error!");
        }
    }
}
