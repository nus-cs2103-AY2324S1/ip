import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileStorage {

    private File fileData;

    public FileStorage(String filePath) {
        this.fileData = new File(filePath);
    }
    public File getFile() {
        return fileData;
    }

    public void write(ArrayList<Task> userList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.fileData);
            for (int i = 0; i < userList.size(); i++) {
                fw.write(userList.get(i).toString());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Writing Error");
        }
    }
    public ArrayList<Task> read() throws DukeException {
        try {
            ArrayList<Task> dataList = new ArrayList<>();
            Scanner scanner = new Scanner(this.fileData);
            Task task;
            int count = 0;
            //System.out.println("reading");
            while (scanner.hasNext()) {
                count++;
                String inputs = scanner.nextLine();
                if (inputs.startsWith("  [T]")) {
                    String info = inputs.substring(8);
                    task = new Todo(info);
                    if (inputs.substring(6).startsWith("X")) task.markDone();
                    dataList.add(task);
                } else if (inputs.startsWith("  [D]")) {
                    String info = inputs.substring(8);
                    String[] split = info.split("\\(by: |\\)");
                    task = new Deadline(split[0], split[1]);
                    if (inputs.substring(6).startsWith("X")) task.markDone();
                    dataList.add(task);
                } else if (inputs.startsWith("  [E]")) {
                    String info = inputs.substring(8);
                    String[] split = info.split("\\(from: | to: |\\)");
                    task = new Event(split[0], split[1], split[2]);
                    if (inputs.substring(6).startsWith("X")) task.markDone();
                    dataList.add(task);
                }
            }
            scanner.close();
            //System.out.println(count);
            return dataList;
        } catch (FileNotFoundException e) {
            throw new DukeException("Invalid file");
        }
    }

}
