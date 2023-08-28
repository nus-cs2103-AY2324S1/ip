
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.io.File;
import java.rmi.activation.ActivationGroup_Stub;
import java.util.Scanner;
import java.util.ArrayList;
public class MeowBot {

    enum TaskType {Event, Deadline, Todo};
    ArrayList<Task> Tasklist;
    String lines, filename;
    Scanner scan;
    FileWriter writer;

    public MeowBot() throws DukeException {
        this.scan = new Scanner(System.in);
        this.Tasklist = new ArrayList<>();
        this.lines = "______________________________";
        this.filename = "src/main/data/meowbot.txt";
        try {
            this.loadTask();
            writer = new FileWriter(this.filename, true);
        } catch (FileNotFoundException e) {
//            System.out.println("Meow?? I cant find your data");
            throw new DukeException("Meow???? I cant fid your data");

        } catch (IOException e) {
            throw new DukeException("Meow???? I cannot write to your files :(");
        }

    }

    public void greet() {
        System.out.println(lines);
        System.out.println("Hello! I'm MeowBot!");
        System.out.println("What can I do for you ?");
        System.out.println(lines);
    }

    public void bye() {
        System.out.println(lines);
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void markTask(String command) {
        int tasknumber = Integer.parseInt(command.substring(5));
        Task wantedtask = this.Tasklist.get(tasknumber - 1); //account for 0 indexing
        wantedtask.markCompleted();
        System.out.println("Nice! I've meowrked this task as done: ");
        System.out.println("   " + wantedtask);
        System.out.println(lines);
        try {
            this.markTaskLocalStorage(tasknumber - 1, 1);
        } catch (IOException e) {
            System.out.println("Cannot make local storage");
        }

    }

    public void unmarkTask(String command) {
        int tasknumber = Integer.parseInt(command.substring(7));
        Task wantedtask = this.Tasklist.get(tasknumber - 1); //account for 0 indexing
        wantedtask.markUncompleted();
        System.out.println("Ok, get your task done soon, I'll be waiting!");
        System.out.println(" " + wantedtask);
        System.out.println(lines);
        try {
            this.markTaskLocalStorage(tasknumber - 1, 0);
        } catch (IOException e) {
            System.out.println("Cannot makr local storage");
        }
    }

    public void deleteTask(String command) {
        int tasknumber = Integer.parseInt(command.substring(7));
        Task wantedtask = this.Tasklist.get(tasknumber - 1);
        this.removeTask(tasknumber - 1); //this would also be the line number to delete in the txt file
        System.out.println("Meow... ok, I've removed this task: ");
        System.out.println(" " + wantedtask);
        System.out.println("Now you have " + this.Tasklist.size() + " meow-tasks in the list.");
        System.out.println(lines);
        try {
            this.deleteFromLocalStorage(tasknumber - 1);
//            System.out.println("Delete from storage");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFromLocalStorage(int num) throws IOException {
        String tempfile = "src/main/data/temp.txt";
        FileWriter tempwriter = new FileWriter(tempfile, true);  // Open the file in append mode
        FileWriter writer = new FileWriter(filename, true);
        Scanner sc = new Scanner(new File(filename));
        int count = 0;
        while (sc.hasNextLine()){
            String l = sc.nextLine();
            if (count == num){
                count ++;
            }
            else {
                count += 1;
                tempwriter.write(l);
            }
        }
        File ogfile = new File(filename);
        File temp = new File(tempfile);
        tempwriter.close();
        writer.close();
        ogfile.delete();
        temp.renameTo(new File(filename));

    }

    public void markTaskLocalStorage(int linenum, int mark) throws IOException {
        String tempfile = "src/main/data/temp.txt";
        FileWriter tempwriter = new FileWriter(tempfile, true);  // Open the file in append mode
        FileWriter writer = new FileWriter(filename, true);
        Scanner sc = new Scanner(new File(filename));
        int count = 0;
        while (sc.hasNextLine()){
            if (count == linenum) {
                String[] arr = sc.nextLine().split("\\|");
                arr[0] = String.valueOf(mark);
                tempwriter.append(String.join("|", arr));
            }
            else {
                tempwriter.append(sc.next());
            }
            count += 1;

        }
        File ogfile = new File(filename);
        File temp = new File(tempfile);
        tempwriter.close();
        writer.close();
        ogfile.delete();
        temp.renameTo(new File(filename));
    }

    public void listTasks() {
        System.out.println(lines);
        System.out.println("Meoowww here are your tasks");
        for (int i = 1; i < this.Tasklist.size() + 1; i++) {
            System.out.println(i + ". " + this.Tasklist.get(i - 1));
        }
        System.out.println(lines);
    }

    public void addTodoTask(String command) throws DukeException {
        String taskInput = command.substring(4);
        Task task = new Todo(taskInput);
        this.Tasklist.add(task);
        this.addTask(task, TaskType.Todo);
    }

    public void addDeadlineTask(String command) throws DukeException {
        String taskInput = command.substring(9);
        Task task = new Deadline(taskInput);
        this.Tasklist.add(task);
        this.addTask(task, TaskType.Deadline);

    }

    public void addEventTask(String command) throws DukeException {
        String taskInput = command.substring(6);
        Task task = new Event(taskInput);
        this.Tasklist.add(task);
        this.addTask(task, TaskType.Event);
    }


    void addTask(Task task, TaskType type) {

        System.out.println("MEOW got it. I've added this task:\n   " + task);
        System.out.println("Now you have " + this.Tasklist.size() + " meow-tasks in the list.");
        System.out.println(lines);

        try {
            // 0 is not completed
            // 1 is completed
            this.writeToLocalStorage(task, type, 0);
//            System.out.println("Yayers! Saved your data to local storage");

        } catch (IOException e) {
            System.out.println("MEOWWW!!! Cannot write to local storage");
        }
    }

    boolean getCommand(String command) throws DukeException{
        String firstword = command.split(" ")[0];
        String[] commands = {"bye","list", "unmark","mark", "todo", "deadline", "event", "delete"};
        for (String c: commands) {
            if(c.equals(firstword)) return true;
        }
        throw new DukeException("Invalid keyword");

    }

    void removeTask(int taskNumber) {
        this.Tasklist.remove(taskNumber);
    }

    void loadTask() throws FileNotFoundException, DukeException {
        Scanner sc = new Scanner(new File(filename));
        int count = 0;
        while (sc.hasNextLine()){
            Task generatedTask = generateTaskFromString(sc.nextLine());
            this.Tasklist.add(generatedTask);
            count += 1;
        }
        if (count == 0) System.out.println("Meow! A new User yay");

        else System.out.println("Meow! Successfully loaded " + count + " tasks from previous session");
    }

    Task generateTaskFromString(String taskname) throws DukeException {
        // check the taskname and its type
        Task generatedTask = null;
        String[] arr = taskname.split("\\|");
        int length = arr.length;

        String ogname = arr[3];
        String tasktype = arr[2];
        String result = arr[1];
        String mark = arr[0];

        if (tasktype.equals("Event")) {
            generatedTask = new Event(ogname);
        } else if (tasktype.equals("Deadline")) {
            generatedTask = new Deadline(ogname);
        } else if (tasktype.equals("Todo")) {
            generatedTask = new Todo(ogname);
        }
        if (mark.equals("1")) generatedTask.markCompleted();
        else if (mark.equals("0")) generatedTask.markUncompleted();

        return generatedTask;

    }

    void writeToLocalStorage(Task task, TaskType type, int completed) throws IOException {
        // 1 mean true
        FileWriter writer = new FileWriter(this.filename, true);  // Open the file in append mode
        writer.write(completed + "|" + task.toString() + "|" + type + "|" + task.ogname + "\n");  // Append the new task and a newline character
        writer.close();
    }
    public void processCommand() {
        String command = this.scan.nextLine();
        while (!command.equals("bye")) {
            try {
                this.getCommand(command);
                if (command.equals("list")) {
                    this.listTasks();

                } else if (command.startsWith("mark")) {
                    this.markTask(command);

                } else if (command.startsWith("unmark")) {
                    this.unmarkTask(command);

                } else if (command.startsWith("delete")) {
                    this.deleteTask(command);
                }

                // solve what tasks are to be added here
                else {
                    if (command.startsWith("todo")) {
                        this.addTodoTask(command);

                    } else if (command.startsWith("deadline")) {
                        this.addDeadlineTask(command);


                    } else if (command.startsWith("event")) {
                        this.addEventTask(command);
                    }
                    // no proper keyword was given
                }

            } catch (DukeException e) {
                System.out.println(e);
            }
            finally {
                command = scan.nextLine();
            }
        }
    }
    public static void main(String[] args) throws DukeException {
        MeowBot meowBot = new MeowBot();
        meowBot.greet();
        meowBot.processCommand();
        meowBot.bye();
    }

}
