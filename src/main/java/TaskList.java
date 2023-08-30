import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addToList(Task s) {
        this.list.add(s);
    }

    public Task getTaskAt(int index) {
        return this.list.get(index);
    }

    public int getNumberOfTasks() {
        return this.list.size();
    }

    public void deleteTaskAt(int index) {
        this.list.remove(index);
    }

    public void readFromFile() {
        try {
            File f = new File("data/duke.txt");
            Scanner s = new Scanner(f);

            this.list = new ArrayList<>();

            System.out.println("Scanning");
            while (s.hasNext()) {
                String line = s.nextLine();
                System.out.println(line);
                if (line == null || line.equals("")) {
                    break;
                }
                String[] params = line.split(" \\| ");
                for (String param: params) {
                    System.out.println(params);
                }
                boolean isDone = params[1].equals("1");
                switch (params[0]) {
                case "T":
                    this.addToList(new Todo(params[2], isDone));
                    break;

                case "D":
                    this.addToList(new Deadline(params[2], isDone, params[3]));
                    break;

                case "E":
                    this.addToList(new Event(params[2], isDone, params[3], params[4]));
                    break;

                default:
                    isDone = params[0].equals("1");
                    this.addToList(new Task(params[1].trim(), isDone));
                    break;
                }
            }

            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("\n" + "OOPS!!! Could not find the file duke.txt");
        }
    }

    public void writeToFile() {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");

            StringBuffer write = new StringBuffer("");
            for (Task t: this.list) {
                write.append(t.getTextRepresentation() + "\n");
            }
            fw.write(write.toString());

            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("\n" + "OOPS!!! Could not find the file duke.txt");
        } catch (IOException e) {
            System.out.println("\n" + "OOPS!!! " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("Here are the tasks in your list:\n");
        for (int i = 0; i < this.list.size(); i++) {
            s.append((i + 1) + "." + this.list.get(i));
            if (i < this.list.size() - 1) {
                s.append("\n");
            }
        }
        String display = s.toString();
        return display;
    }
}
