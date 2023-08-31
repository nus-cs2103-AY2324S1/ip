package DukePackage;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.ArrayList;


public class Storage {
    protected ArrayList<Task> taskList;

    public Storage() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
    public void addList(Task t) {
        this.taskList.add(t);
    }

    public void delete(int id) {
        System.out.println("     Noted. I've removed this task:");
        Task t = this.taskList.get(id);
        t.printMarking(false);
        this.taskList.remove(id);
        int size = this.taskList.size();
        System.out.printf("\n     Now you have %d tasks in the list.\n", size);
    }

    public void write() {
        try {
            FileWriter fileWriter = new FileWriter("data/duke.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Task tasking : this.taskList) {
                // format the string

                String formattedString = "";
                Integer priority = tasking.isDone
                        ? 1
                        : 0;
                switch (tasking.type) {
                    case TODO:
                        formattedString = String.format("%c|%d|%s",
                                'T', priority, tasking.description);
                        break;
                    case DEADLINE:
                        formattedString = String.format("%c|%d|%s|%s",
                                'D', priority, tasking.description,
                                tasking.start.toString().replace("T", " "));
                        break;
                    case EVENT:
                        formattedString = String.format("%c|%d|%s|%s|%s",
                                'E', priority, tasking.description,
                                tasking.start.toString().replace("T", " "),
                                tasking.end.toString().replace("T", " "));
                        break;
                }
                bufferedWriter.write(formattedString);
                bufferedWriter.newLine(); // Move to the next line
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/duke.txt"))) {

            File file = new File("data/duke.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileReader fileReader = new FileReader(file); // append mode
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = reader.readLine()) != null) {
                // Assuming your line contains comma-separated values
                String[] values = line.split("\\|");
                // Create your Java object based on the parsed values
                TaskType type = Objects.equals(values[0], "T")
                        ? TaskType.TODO
                        : Objects.equals(values[0], "D")
                        ? TaskType.DEADLINE
                        : TaskType.EVENT;
                String start = "", end = "";
                try {
                    start = values[3];
                } catch (Exception e) {
                    start = "";
                }
                try {
                    end = values[4];
                } catch (Exception e) {
                    end = "";
                }
                Task obj = new Task(values[2], type, start, end); // Instantiate with appropriate arguments
                obj.marking(!Objects.equals(values[1], "0"));
                // Store the object in your storage instance
                addList(obj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listPrinter() {
        for (int i = 0; i < this.taskList.size(); i++) {
            int index = i + 1;
            Task t = this.taskList.get(i);
            System.out.printf("     %d.[%s][%s] %s", index, t.getTypeIcon(), t.getStatusIcon(), t.description);
            if (!Objects.isNull(t.start) && !Objects.isNull(t.end)) {
                System.out.printf(" (from: %s to: %s)%n", t.start.toString().replace("T", " "), t.end.toString().replace("T", " "));
            } else if (!Objects.isNull(t.start)) {
                System.out.printf(" (by: %s)%n", t.start.toString().replace("T", " "));
            } else {
                System.out.print("\n");
            }
        }
    }

    public void printMarking(int i) throws DukeException {
        try {
            Task t = this.taskList.get(i);
            t.printMarking(true);
            System.out.println();
        } catch (Exception e) {
            throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public void changeMarking(int i, boolean isDone) throws DukeException {
        try {
            Task t = this.taskList.get(i);
            t.marking(isDone);
        } catch (Exception e) {
            throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public void printEntry(Task t) {
        t.descriptionString();
        int size = this.taskList.size();
        System.out.printf("\n     Now you have %d tasks in the list.\n", size);
    }
}
