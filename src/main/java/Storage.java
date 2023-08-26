import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    Task stringToTask(String str) throws Exception{
        String[] arr = str.split(" | ");
        Task output;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
        switch (arr[0]) {
            case "T":
                output = new Todo(arr[2], arr[1].equals("1"));
                break;
            case "D":
                output = new Deadline(arr[1], LocalDate.parse(arr[3]), arr[2].equals("1"));
                break;
            case "E":
                output = new Event(arr[1], LocalDate.parse(arr[3]), LocalDate.parse(arr[4]), arr[2].equals("1"));
                break;
            default:
                output = null;
                throw new InvalidTypeException(arr[0]);
        }
        return output;
    }

    String taskToString(Task task) throws Exception{
        String output = task.getType() + " | " + task.isMarked() + " | "+ task.getDescription();
        switch (task.getType()) {
            case "T":
                break;
            case "D":
                output += " | " + ((Deadline) task).getDue();
                break;
            case "E":
                output = " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo();
                break;
            default:
                output = null;
                throw new InvalidTypeException(task.getType());
        }
        return output;
    }

    ArrayList<Task> load() throws Exception {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            tasks.add(stringToTask(sc.nextLine()));
        }
        return tasks;
    }

    void save(TaskList tasks) throws Exception {
        FileWriter fw = new FileWriter(filePath);
        String saveTxt = "";
        ArrayList<Task> tasksList = tasks.getTasks();
        for (int i = 0; i < tasksList.size(); i++) {
            saveTxt += taskToString(tasksList.get(i)) + "\n";
        }
        fw.write(saveTxt);
        fw.close();
    }

}
