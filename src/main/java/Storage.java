import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File file;
    private String path;

    public Storage(String filePath) {
        file = new File(filePath);
        path = filePath;
    }

    public ArrayList<Task> load() throws IOException{
            if (file.createNewFile()) {
                return new ArrayList<>();
            }

            Scanner fScanner = new Scanner(file);

            ArrayList<Task> arrTask = new ArrayList<>();

            while (fScanner.hasNextLine()) {
                String s = fScanner.nextLine();
                String[] arr = s.split("--");
                if (arr[0].equals("T")) {
                    Todo t = new Todo(arr[2]);
                    if (arr[1].equals("1")) {
                        t.markAsDone();
                    }
                    arrTask.add(t);
                } else if (arr[0].equals("D")) {
                    Deadline dl = new Deadline(arr[2], LocalDate.parse(arr[3]));
                    if (arr[1].equals("1")) {
                        dl.markAsDone();
                    }
                    arrTask.add(dl);
                } else if (arr[0].equals("E")){
                    Event e = new Event(arr[2], LocalDate.parse(arr[3]), LocalDate.parse(arr[4]));
                    if (arr[1].equals("1")) {
                        e.markAsDone();
                    }
                    arrTask.add(e);
                }
            }
            fScanner.close();
            return arrTask;
    }

    public void save(TaskList list) throws IOException {
        if (file.delete()) {
            file = new File(path);
            for (int i = 1; i <= list.total(); i++) {
                Task t = list.get(i - 1);
                writeToFile(path, t.toFileString());
            }
        }
    }

    private void writeToFile(String pathname, String textToAdd) throws IOException{
        FileWriter fw = new FileWriter(pathname, true);
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }
}
