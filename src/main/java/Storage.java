import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String dirPath;
    private String fileName;

    public Storage(String dirPath, String fileName) {
        this.dirPath = dirPath;
        this.fileName = fileName;
        File directory = new File(this.dirPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public void writeToFile() {
        try {
            FileWriter fileWriter = new FileWriter(this.dirPath + this.fileName);
            for (Task task : TaskList.list) {
                fileWriter.write(task.getFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadFile() throws BrunoException {
        try {
            File file = new File(this.dirPath + this.fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String[] task = s.split("\\|");
                if (task[0].equals("T")) {
                    TaskList.list.add(new ToDo(task[2]));
                } else if (task[0].equals("D")) {
                    TaskList.list.add(new Deadline(task[2], task[3]));
                } else if (task[0].equals("E")) {
                    TaskList.list.add(new Event(task[2], task[3], task[4]));
                } else {
                    throw new BrunoIncorrectFormatException();
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
