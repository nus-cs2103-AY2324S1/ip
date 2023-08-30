import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Storage {
    private static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void createFile(File f) throws IOException {
        f.getParentFile().mkdirs();
        f.createNewFile();
    }

    public ArrayList<Task> loadTasks(String filePath) throws IOException {
        File f = new File(filePath);
        // if the files doesn't exist, we make the file
        if (!f.exists()) {
            createFile(f);
            throw new FileNotFoundException("The file is not found, try again!");
        }
        else {
            return readTasks(filePath);
        }
    }

    public ArrayList<Task> readTasks(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner tasks = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<>();
        while (tasks.hasNext()) {
            String str = tasks.nextLine();
            String[] taskDetails = str.split("\\|"); // Escape the pipe character
            String taskType = taskDetails[0].trim();
            String completion = taskDetails[1].trim();
            String description = taskDetails[2].trim();

            switch (taskType) {
                case "T":
                    Todo newTodo = new Todo(description);
                    newTodo.isDone = completion.equals("1");
                    taskList.add(newTodo);
                    break;
                case "D":
                    String by = taskDetails[3].trim();

                    // Parse the 'by' string into LocalDateTime using a formatter
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy ha");

                    LocalDateTime dateTime = LocalDateTime.parse(by, formatter);

                    Deadline newDeadline = new Deadline(description, dateTime);
                    newDeadline.isDone = completion.equals("1");
                    taskList.add(newDeadline);
                    break;
                case "E":
                    String from = taskDetails[3].trim();
                    String to = taskDetails[4].trim();
                    Event newEvent = new Event(description, from, to);
                    newEvent.isDone = completion.equals("1");
                    taskList.add(newEvent);
                    break;
            }
        }
        tasks.close();
        return taskList;
    }


    public void saveTasks(ArrayList<Task> taskList) throws IOException {
        File f = new File(filePath);
        FileWriter fw = new FileWriter(f);

        for (Task task : taskList) {
            String taskString = task.toFileString();
            fw.write(taskString + System.lineSeparator());
        }

        fw.close();
    }


}