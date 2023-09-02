import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Bot {
    private final String botName;
    private final TaskList list;
    private final String HORIZONTAL_LINE
            = "    ____________________________________________________________";
    private final File file;

    public Bot(String botName, File file) {
        this.botName = botName;
        this.file = file;
        this.list = new TaskList();

        try {
            loadData();
        } catch (IOException e) {
            System.out.println("Cannot read file");
        }
    }

    public void greeting() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     Hello! I'm " + this.botName);
        System.out.println("     What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    public void markTask(int taskId) {
        this.list.mark(taskId, true);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + this.list.showTask(taskId));
        System.out.println(HORIZONTAL_LINE);
        System.out.println();

        try {
            updateFile();
        } catch (IOException e) {
            System.out.println("Cannot write to file!");
        }
    }

    public void unmarkTask(int taskId) {
        this.list.mark(taskId, false);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + this.list.showTask(taskId));
        System.out.println(HORIZONTAL_LINE);
        System.out.println();

        try {
            updateFile();
        } catch (IOException e) {
            System.out.println("Cannot write to file!");
        }
    }

    private void notifyTaskAdded(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("     Now you have " + this.list.size() +  " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    public void addTask(String str) {
        Task task = new Todo(str);
        this.list.addTask(task);
        notifyTaskAdded(task);

        try {
            updateFile();
        } catch (IOException e) {
            System.out.println("Cannot write to file!");
        }
    }

    public void addTask(String str, String deadline) {
        Task task = new Deadline(str, deadline);
        this.list.addTask(task);
        notifyTaskAdded(task);

        try {
            updateFile();
        } catch (IOException e) {
            System.out.println("Cannot write to file!");
        }
    }

    public void addTask(String str, String startTime, String endTime) {
        Task task = new Event(str, startTime, endTime);
        this.list.addTask(task);
        notifyTaskAdded(task);

        try {
            updateFile();
        } catch (IOException e) {
            System.out.println("Cannot write to file!");
        }
    }

    public void deleteTask(int taskId) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + this.list.deleteTask(taskId));
        System.out.println("     Now you have " + this.list.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);

        try {
            updateFile();
        } catch (IOException e) {
            System.out.println("Cannot write to file!");
        }
    }

    public void showTask() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     Here are the tasks in your list:");
        System.out.println(this.list);
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    public void goodBye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    // Updates the text file whenever there is a change to the TaskList
    private void updateFile() throws IOException {
        FileWriter fw = new FileWriter(this.file.getAbsolutePath());
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            content.append(this.list.showTask(i + 1));
            if (i < this.list.size() - 1) {
                content.append('\n');
            }
        }
        fw.write(content.toString());
        fw.close();
    }

    // Loads data from text file to the TaskList
    private void loadData() throws IOException {
        Scanner sc = new Scanner(this.file);
        String task;

        while (sc.hasNext()) {
            task = sc.nextLine();
            if (task.charAt(1) == 'T') {
                this.list.addTask(new Todo(task.substring(7)));
                if (task.charAt(4) == 'X') {
                    this.list.mark(this.list.size(), true);
                }
            } else if (task.charAt(1) == 'D') {
                int idx = 0;
                while (task.charAt(idx) != ':') {
                    idx++;
                }
                this.list.addTask(new Deadline(task.substring(7, idx - 4),
                        task.substring(idx + 2, task.length() - 1)));
                if (task.charAt(4) == 'X') {
                    this.list.mark(this.list.size(), true);
                }
            } else {
                int idx1 = 0;
                while (task.charAt(idx1) != ':') {
                    idx1++;
                }

                int idx2 = idx1 + 1;
                while (task.charAt(idx2) != ':') {
                    idx2++;
                }

                this.list.addTask(new Event(task.substring(7, idx1 - 6),
                        task.substring(idx1 + 2, idx2 - 3),
                        task.substring(idx2 + 2, task.length() - 1)));
                if (task.charAt(4) == 'X') {
                    this.list.mark(this.list.size(), true);
                }
            }
        }
    }
}
