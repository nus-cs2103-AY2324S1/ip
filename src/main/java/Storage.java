import java.io.*;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;

public class Storage {
    private File file;
    private String filePath;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                System.out.println(currentLine);
                String[] taskInfo = currentLine.split("\\|");
                String type = taskInfo[0].trim();
                String status = taskInfo[1];
                String description = taskInfo[2].trim();
                if ("T".equals(type)) {
                    Todo todo = new Todo(description);
                    if ("X".equals(status)) {
                        todo.markAsDone();
                    }
                    tasks.add(todo);
                } else if ("D".equals(type)) {
                    String by = taskInfo[3];
                    Deadline deadline = new Deadline(description, by.trim());
                    if ("X".equals(status)) {
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                } else if ("E".equals(type)) {
                    String[] duration = taskInfo[3].split("-");
                    String from = duration[0];
                    String to = duration[1];
                    Event event = new Event(description, from, to);
                    if ("X".equals(status)) {
                        event.markAsDone();
                    }
                    tasks.add(event);
                } else {
                    System.out.println("There are no tasks.");
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
        }

        return tasks;
    }

    public void saveTasks(TaskList taskList) {
        StringBuffer inputString = new StringBuffer();
        for (Task task: taskList.getTasks()) {
            String line = task.getDescription();
            inputString.append(line);
            inputString.append("\n");
        }
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            fileOut.write(inputString.toString().getBytes());
            fileOut.close();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
        }
    }
}
