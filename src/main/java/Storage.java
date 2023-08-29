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
            String[] input = taskContent.split(" \\| ");
            String category = input[0];
            String marked = input[1];
            String description = input[2];

            switch (category) {
            case "T":
                ToDos todo = new ToDos(description);
                taskList.add(todo);
                if (!marked.equals("0")) {
                    todo.mark();
                }
                break;
            case "D":
                String by = input[3];
                Deadlines deadline = new Deadlines(description, by);
                taskList.add(deadline);
                if (!marked.equals("0")) {
                    deadline.mark();
                }
                break;
            case "E":
                String from = input[3];
                String to = input[4];
                Events event = new Events(description, from, to);
                taskList.add(event);
                if (!marked.equals("0")) {
                    event.mark();
                }
                break;
            }
        }
        return taskList;
    }

}
