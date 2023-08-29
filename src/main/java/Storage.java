import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String filePath;
    File textFile;

    public Storage() {
        this.filePath = "tasks.txt";
        this.textFile = new File(filePath);
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.saveTaskString() + System.lineSeparator());
        }
        fw.close();
    }

    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<>();
        while (s.hasNext()) {
            String taskContent = s.nextLine();
            String category = taskContent.split(" \\| ")[0];
            String marked = taskContent.split("\\|")[1];
            switch (category) {
            case "T":
                ToDos todo = new ToDos(taskContent.split(" \\| ")[2]);
                if (marked.equals("0")) {
                    taskList.add(todo);
                } else {
                    todo.mark();
                    taskList.add(todo);
                }
                break;
            case "D":
                Deadlines deadline = new Deadlines(taskContent.split(" \\| ")[2], taskContent.split(" \\| ")[3]);
                if (marked.equals("0")) {
                    taskList.add(deadline);
                } else {
                    deadline.mark();
                    taskList.add(deadline);
                }
                break;
            case "E":
                Events event = new Events(taskContent.split(" \\| ")[2],
                        taskContent.split(" \\| ")[3].split("-")[0],
                        taskContent.split("-")[1]);
                if (marked.equals("0")) {
                    taskList.add(event);
                } else {
                    event.mark();
                    taskList.add(event);
                }
                break;
            }
        }
        return taskList;
    }

}
