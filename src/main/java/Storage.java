import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final ArrayList<Task> tasks;
    public Storage() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task mark(int index) throws InvalidTaskIndexException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidTaskIndexException(index + 1, this.tasks.size());
        }
        Task toMark = this.tasks.get(index);
        toMark.complete();
        return toMark;
    }

    public Task unmark(int index) throws InvalidTaskIndexException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidTaskIndexException(index + 1, this.tasks.size());
        }
        Task toUnmark = this.tasks.get(index);
        toUnmark.complete();
        return toUnmark;
    }

    public Task delete(int index) throws InvalidTaskIndexException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidTaskIndexException(index + 1, this.tasks.size());
        }
        Task toDelete = this.tasks.get(index);
        this.tasks.remove(index);
        return toDelete;
    }

    public int getStorageSize() {
        return this.tasks.size();
    }

    public String list() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                builder.append(String.format("%d.%s", i + 1, this.tasks.get(i)));
            } else {
                builder.append(String.format("%d.%s\n", i + 1, this.tasks.get(i)));
            }
        }
        return builder.toString();
    }

    public void save() {
        try {
            FileWriter fw = new FileWriter("tasks.txt");
            StringBuilder text = new StringBuilder();
            for (Task t : tasks) {
                text.append(String.format("%s\n", t.getSaveFormat()));
            }
            fw.write(text.toString());
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load() {
        try {
            File f = new File("tasks.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] line = s.nextLine().trim().split(" \\| ");
                boolean taskIsDone = line[1].equals("1");
                switch (line[0]) {
                    case "T":
                        TodoTask newTodo = new TodoTask(line[2]);
                        if (taskIsDone) {
                            newTodo.complete();
                        }
                        this.tasks.add(newTodo);
                        break;
                    case "D":
                        LocalDate deadline = LocalDate.parse(line[3]);
                        DeadlineTask newDeadline = new DeadlineTask(line[2], deadline);
                        if (taskIsDone) {
                            newDeadline.complete();
                        }
                        this.tasks.add(newDeadline);
                        break;
                    case "E":
                        EventTask newEvent = new EventTask(line[2], line[3], line[4]);
                        if (taskIsDone) {
                            newEvent.complete();
                        }
                        this.tasks.add(newEvent);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Task file not created yet.");
        }
    }
}
