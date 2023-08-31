package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) {
        try {
            String[] splited = filePath.split("/");
            File dir = new File(splited[0]);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File content = new File(filePath);
            if (!content.exists()) {
                content.createNewFile();
           }
            this.file = content;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public ArrayList<Task> getData() throws DukeException {
        try {
            ArrayList<Task> oldTasks = new ArrayList<>();
            Scanner fileScanner = new Scanner(this.file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] splited = line.split(" ", 6);
                if (splited[2].equals("[T]")) {
                    Task todo = new Todo(splited[5]);
                    oldTasks.add(todo);
                    if (splited[0].equals("1")) {
                        todo.markDone();
                    } else {
                        todo.markUndone();
                    }
                }
                if (splited[2].equals("[D]")) {
                    String[] desc = splited[5].split("\\(by:", 2);
                    String time = desc[1].split("\\)", 2)[0];
                    LocalDateTime by = formatData(time);
                    Task deadline = new Deadline(desc[0], by);
                    oldTasks.add(deadline);
                    if (splited[0].equals("1")) {
                        deadline.markDone();
                    } else {
                        deadline.markUndone();
                    }
                }
                if (splited[2].equals("[E]")) {
                    String[] description = splited[5].split("\\(from:");
                    String start = description[1].split("to:", 2)[0];
                    String end = description[1].split("to:", 2)[1];
                    LocalDateTime from = formatData(start);
                    LocalDateTime to = formatData(end);
                    Task event = new Event(description[0], from, to);
                    oldTasks.add(event);
                    if (splited[0].equals("1")) {
                        event.markDone();
                    } else {
                        event.markUndone();
                    }
                }
            }
            return oldTasks;

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void saveData(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(this.file);
            for (Task task : tasks) {
                int isDone = task.getStatus() ? 1 : 0;
                String description = task.toString();
                String line = String.format("%d | %s%n", isDone, description);
                writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public LocalDateTime formatData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'of' MMMM uuuu, h:mma", Locale.ENGLISH);
        LocalDateTime localDateTime = LocalDateTime.parse(data, formatter);
        return localDateTime;
    }
}
