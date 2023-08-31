package pippi.storage;


import pippi.parser.DateFormatter;
import pippi.task.Deadline;
import pippi.task.Event;
import pippi.task.Task;
import pippi.task.ToDo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Storage {
    private static final String filePath = "./data/Pippi.txt";
    public static ArrayList<Task> read() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts =line.split("\\s*\\|\\s*");
                if (parts.length == 0) {
                    continue;
                }
                String type = parts[0];
                String status = parts[1];
                switch (type) {
                    case "T":
                        ToDo t = new ToDo(parts[2]);
                        // System.out.println(status);
                        if (status.equals("1")) {
                            // System.out.println("called");
                            t.mark();
                        }
                        tasks.add(t);
                        break;
                    case "D":
                        Deadline d = new Deadline(parts[2],
                                DateFormatter.convertToLocalDate(parts[3].trim()));
                        if (status.equals("1")) {
                            d.mark();
                        }
                        tasks.add(d);
                        break;
                    case "E":
                        String start = parts[3].split("to ")[0];
                        String end = parts[3].split("to ")[1];
                        Event e = new Event(parts[2], start, end);
                        if (status.equals("1")) {
                            e.mark();
                        }
                        tasks.add(e);
                        break;
                }
            }
            // FileReader will open that file from that
            // directory, if there is no file found it will
            // throw an IOException
        } catch (IOException e) {
            System.out.println("No file found exception");
        }
        return tasks;
    }
    public static void update(ArrayList<Task> tasks) {
        try {
            // Creating a FileWriter object
            FileWriter fw = new FileWriter(filePath);
            String all = "";
            for (int i = 0; i < tasks.size(); i++) {
                Task curr = tasks.get(i);
                all = all + curr.toMemory() + "\n";
            }
            fw.write(all);
            fw.close();

        } catch (IOException e) {
            System.out.println("File input/output not found exception");
        }
    }

}
