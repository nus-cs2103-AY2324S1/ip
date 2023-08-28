import java.io.*;
import java.util.ArrayList;

public class List {
    private ArrayList<Task> taskList = new ArrayList<>();

    public int size() {
        return taskList.size();
    }
    public void add(Task task, boolean flag) {
        taskList.add(task);
        if (flag) {
            System.out.println("Got it. I've added this task:\n" + task.toString());
            System.out.println(this);
        }
    }

    public void writeToFile() throws DukeException {
        try {
            File file = new File("./data/duke.txt");
            file.delete();
            FileWriter writer = new FileWriter("./data/duke.txt");
            for (Task task : taskList) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("OOPS! I cannot write to the file.");
        }
    }

    public List loadFromFile() throws IOException {
        List returnList = new List();
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("./data/duke.txt")));
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                return returnList;
            }
            else {
                Character done = line.charAt(4);
                boolean isDone = done.equals('X');

                if (line.startsWith("[T]")) {
                    Todo todo = new Todo(line.substring(7));
                    if (isDone) {
                        todo.mark(false);
                    }
                    returnList.add(todo, false);
                }

                else if (line.startsWith("[D]")) {
                    int index = line.indexOf("(");
                    int endIndex = line.indexOf(")");
                    Deadline deadline = new Deadline(line.substring(7, index - 1), line.substring(index + 5, endIndex));
                    if (isDone) {
                        deadline.mark(false);
                    }
                    returnList.add(deadline, false);
                }

                else {
                    int index = line.indexOf("(");
                    int midIndex = line.indexOf("to:");
                    int endIndex = line.indexOf(")");
                    Event event = new Event(line.substring(7, index - 1), line.substring(index + 7, midIndex - 1), line.substring(midIndex + 4, endIndex));
                    if (isDone) {
                        event.mark(false);
                    }
                    returnList.add(event, false);
                }
            }
        }
        return returnList;
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public void delete(Task task) {
        System.out.println("Noted. I've removed this task:\n" + task.toString());
        taskList.remove(task);
        System.out.println(this);
    }

    public void list() {
        String returnString = "";
        for (int i = 0; i < taskList.size(); i++) {
            returnString += i+1 + ". " + taskList.get(i).toString() + "\n";
        }
        System.out.println(returnString);
    }

    @Override
    public String toString() {
        return "Now you have " + taskList.size() + " task(s) in the list";
    }
}
