import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Disk {
    private File fList;
    private String path;

    public Disk(String path) throws IOException {
        this.path = path;

        File dir = new File("./data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        this.fList = new File(this.path);
        if (!fList.exists()) {
            fList.createNewFile();
        }
    }

    public ArrayList<Task> openFile() throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<>();
        Scanner fScanner;
        fScanner = new Scanner(this.fList);
        while (fScanner.hasNextLine()) {
            String[] task = fScanner.nextLine().split(" \\| ");
            switch (task[0]) {
            case "T":
                list.add(new Todo(task[1], task[2]));
                break;
            case "D":
                list.add(new Deadline(task[1], task[2], task[3]));
                break;
            case "E":
                list.add(new Event(task[1], task[2], task[3], task[4]));
                break;
            default:
                break;
            }
        }
        fScanner.close();
        System.out.println(list);
        return list;
    }

    public void appendFile(String message) throws IOException {
        FileWriter fWriter = new FileWriter(this.fList, true);
        fWriter.write(message + System.lineSeparator());
        fWriter.close();
    }

    public void updateFile(ArrayList<Task> list) throws IOException {
        FileWriter fWriter = new FileWriter(this.fList);
        for (int i = 0; i < list.size(); i++) {
            Task curr = list.get(i);
            fWriter.write(curr.toStringForFile() + System.lineSeparator());
        }
        fWriter.close();
    }
}
