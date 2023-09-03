import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;
import tasks.Task;

public class Storage {
    protected String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public void writeToFile(TaskList listOfTasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(int i = 0; i < listOfTasks.tasks.size(); i++) {
            if(listOfTasks.tasks.get(i) instanceof Event) {
                Event x = (Event) listOfTasks.tasks.get(i);
                if(listOfTasks.tasks.get(i).getStatus()) {
                    fw.write("E | 0 |" + listOfTasks.printName(i) + "|" + x.getStart() + "-" + x.getEnd() + "\n");
                } else {
                    fw.write("E | 1 |" + listOfTasks.printName(i) + "|" + x.getStart() + "-" + x.getEnd() + "\n");
                }
            }
            if(listOfTasks.tasks.get(i) instanceof ToDo) {
                if(listOfTasks.tasks.get(i).getStatus()) {
                    fw.write("T | 0 |" + listOfTasks.printName(i) + "\n");
                } else {
                    fw.write("T | 1 |" + listOfTasks.printName(i) + "\n");
                }
            }
            if(listOfTasks.tasks.get(i) instanceof Deadline) {
                Deadline x = (Deadline) listOfTasks.tasks.get(i);
                if(listOfTasks.tasks.get(i).getStatus()) {
                    fw.write("D | 0 |" + listOfTasks.printName(i) + "| " + x.getBy() + "\n");
                } else {
                    fw.write("D | 1 |" + listOfTasks.printName(i) + "| " + x.getBy() + "\n");
                }
            }
        }
        fw.close();
    }
    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] parts = line.split("\\|");
            if(parts[0].equals("T ")) {
                ToDo temp = new ToDo(parts[2]);
                tasks.add(temp);
                if(parts[1].equals(" 0 ")) {
                    temp.markDone();
                }
            }
            if (parts[0].equals("D ")) {
                String[] x = parts[3].split(" ");
                Deadline temp = new Deadline(parts[2], x[1]);
                tasks.add(temp);
                if(parts[1].equals(" 0 ")) {
                    temp.markDone();
                }
            }
            if(parts[0].equals("E ")) {
                String[] duration = parts[3].split("-");
                Event temp = new Event(parts[2], duration[0], duration[1]);
                tasks.add(temp);
                if(parts[1].equals(" 0 ")) {
                    temp.markDone();
                }
            }
        }
        return tasks;
    }
}
