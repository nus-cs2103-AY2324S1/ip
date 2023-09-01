import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.io.FileNotFoundException;

public class Storage {

    private File file;
    private Scanner scanner;
    public Storage(String filePath) {
        this.file = new File(filePath);

        try {
            if (!file.getParentFile().isDirectory()) {
                file.mkdirs();
                file.createNewFile();
            } if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            this.scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("error file not found");
        }
    }

    public void write(String task) {
        FileWriter fw;
        try {
            fw = new FileWriter(file);
            fw.write(task);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String task = scanner.nextLine();
            Task instance = Storage.readFile(task);
            tasks.add(instance);
        }
        return tasks;
    }



    public static Task readFile(String nextLine) {
        String next = nextLine.substring(3);
        Task task;
        if(nextLine.startsWith("[T]")) {
            String desc = next.substring(4);
            task = new Todo(desc);
        } else if (nextLine.startsWith("[D]")) {
            int endDesc = next.indexOf("(by: ");
            String desc = next.substring(4, endDesc);
            int len = next.length();
            String time = next.substring(endDesc + 5, len - 1);
            LocalDate d1 = LocalDate.parse(time);
            task = new Deadline(desc, d1);
        } else {
            int endDesc = next.indexOf("(from: ");
            String desc = next.substring(4, endDesc);
            int endFrom = next.indexOf("to: ");
            String from = next.substring(endDesc + 7, endFrom - 1);
            String to = next.substring(endFrom + 4, next.length() - 1);
            LocalDate d1 = LocalDate.parse(from);
            LocalDate d2 = LocalDate.parse(to);
            task = new Event(desc, d1, d2);
        }
        Task toReturn;
        if (next.startsWith("[X]")) {
            toReturn = task.description(true);
        } else {
            toReturn = task.description(false);
        }
        return toReturn;
    }
}
