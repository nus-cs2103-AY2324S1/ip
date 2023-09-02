import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Storage {
    private File file;
    public Storage(File file) {
        this.file = file;
    }
    public ArrayList<Task> readFromTxtFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String s = reader.nextLine();
                String[] sArray = s.split("\\|");
                String description = sArray[2].trim();
                boolean isDone = (sArray[1].trim().equals("1")) ? true : false;

                switch (sArray[0]) {
                case "T ":
                    tasks.add(new ToDos(description, isDone));
                    break;
                case "D ":
                    String by = sArray[3].trim();
                    tasks.add(new Deadline(description, by, isDone));
                    break;
                case "E ":
                    String[] duration = sArray[3].split("-");
                    String start = duration[0].trim();
                    String end = duration[1].trim();
                    tasks.add(new Event(description, start, end, isDone));
                    break;
                default:
                    break;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return tasks;
    }
    public void save(TaskList tasks) { // overwrite 'tasklist.txt' with 'tasks' arraylist
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Task task : tasks.get()) {
                bw.write(task.toString() + "\n");
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
