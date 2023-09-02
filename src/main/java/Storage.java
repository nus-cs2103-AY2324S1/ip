import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    private String path;
    public Storage(String path) {
        this.path = path;
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File file = new File(path);
            if (file.exists()) {
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    list.add(this.loadTask(s.nextLine()));
                }
            } else {
                file.createNewFile();
            }
            return list;
        } catch (IOException e) {
            System.out.println("Exception occurred: " + e.getMessage());
            return list;
        }
    }

    private Task loadTask(String input) {
        String[] data = input.split("\\|");
        switch (data[0]) {
        case "todo":
            Todo todo = new Todo(data[2]);
            if (Objects.equals(data[1], "1")) {
                todo.setDone();
            }
            return todo;
        case "deadline":
            Task deadline = new Deadline(data[2], Parser.parseDatetime(data[3]));
            if (Objects.equals(data[1], "1")) {
                deadline.setDone();
            }
            return deadline;
        case "event":
            Event event = new Event(data[2], Parser.parseDatetime(data[3]), Parser.parseDatetime(data[4]));
            if (Objects.equals(data[1], "1")) {
                event.setDone();
            }
            return event;
        default:
            return null;
        }
    }

    public void saveTask(Task task) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(path, true);
            fileWriter.write(task.toStorageString() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("IOException:" + e.getMessage());
        }
    }

    public void editTask(String action, int index) {
        File fileToEdit = new File(this.path);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileToEdit));
            String line;
            StringBuilder updatedContent = new StringBuilder();
            int currentLineNumber = 0;

            while ((line = reader.readLine()) != null) {
                if (currentLineNumber == index) {
                    String[] data = line.split("\\|");
                    switch (action) {
                    case "mark":
                        data[1] = "1";
                        updatedContent.append(String.join("|", data)).append("\n");
                        break;
                    case "unmark":
                        data[1] = "0";
                        updatedContent.append(String.join("|", data)).append("\n");
                        break;
                    case "delete":
                        break;
                    }
                } else {
                    updatedContent.append(line).append("\n");
                }
                currentLineNumber++;
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileToEdit));
            writer.write(updatedContent.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
