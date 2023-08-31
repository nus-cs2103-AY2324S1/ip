package Helpers;

import Tasks.Deadline;
import Tasks.Events;
import Tasks.Task;
import Tasks.Todo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Storage {
    private final String path;
    private final static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Storage(String path) {
        this.path = path;
    }

    public ArrayList<Task> read() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File("data\\" + this.path);
            if (!file.getParentFile().exists()) {
                if (file.getParentFile().mkdirs()) {
                    file.createNewFile();
                }
            }
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String readLine;

            while ((readLine = bufferedReader.readLine()) != null) {
                //E | 0 | project meeting | 2pm | 4pm
                String[] lines = readLine.split(" \\| ");
                //System.out.println(Arrays.toString(lines));
                String type = lines[0];
                try {
                    switch (type) {
                        case "T":
                            taskList.add(new Todo(lines[2], !lines[1].equals("0")));
                            break;
                        case "D":
                            taskList.add(new Deadline(lines[2], !lines[1].equals("0"),
                                    LocalDateTime.parse(lines[3], dateTimeFormat)));
                            break;
                        case "E":
                            taskList.add(new Events(lines[2], !lines[1].equals("0"),
                                    LocalDateTime.parse(lines[3], dateTimeFormat),
                                    LocalDateTime.parse(lines[4], dateTimeFormat)));
                            break;
                        default:
                            break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    e.getMessage();
                }
            }
            reader.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);

        }
        return taskList;
    }

    public void write(ArrayList<Task> taskList) {

        try {
            FileWriter writer = new FileWriter("data\\" + this.path, false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Task task : taskList) {
                bufferedWriter.write(task.getData());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
