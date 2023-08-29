import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void writeFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(tasks.toFileString());
        fw.close();
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();

        File f = new File(filePath);

        if (f.exists()) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                addFileTask(taskList, s.nextLine());
            }
        } else {
            f.createNewFile();
        }

        return taskList;
    }

    private void addFileTask(ArrayList<Task> taskList, String input) {
        String[] task = input.split("\\|");
        String taskName = task[0].trim();
        String isMarked = task[1].trim();
        String taskDesc = task[2].trim();

        switch (taskName) {
        case "T":
            if (isMarked.equals("1")) {
                taskList.add(new ToDo(taskDesc, true));
            } else {
                taskList.add(new ToDo(taskDesc));
            }

            break;
        case "D":
            String taskDate = task[3].trim();

            if (isMarked.equals("1")) {
                taskList.add(new Deadline(taskDesc, true, taskDate));
            } else {
                taskList.add(new Deadline(taskDesc, taskDate));
            }

            break;
        case "E":
            String[] taskEvent = task[3].split("-");

            if (isMarked.equals("1")) {
                taskList.add(new Event(taskDesc, true, taskEvent[0].trim(), taskEvent[1].trim()));
            } else {
                taskList.add(new Event(taskDesc, taskEvent[0].trim(), taskEvent[1].trim()));
            }

            break;
        }
    }
}
