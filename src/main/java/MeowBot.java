import java.util.Scanner;
import java.util.ArrayList;
public class MeowBot {

    enum TaskType {Event, Deadline, Todo};
    ArrayList<Task> Tasklist;
    int counter;
    String lines,command;

    Scanner scan;

    public MeowBot() {
        this.scan = new Scanner(System.in);
        this.Tasklist = new ArrayList<>();
        this.lines = "______________________________";
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
    }

    public void unmarkTask(String command) {
        int tasknumber = Integer.parseInt(command.substring(7));
        Task wantedtask = this.Tasklist.get(tasknumber - 1); //account for 0 indexing
        wantedtask.markUncompleted();
        System.out.println("Ok, get your task done soon, I'll be waiting!");
        System.out.println(" " + wantedtask);
        System.out.println(lines);
    }

    public void deleteTask(String command) {
        int tasknumber = Integer.parseInt(command.substring(7));
        Task wantedtask = this.Tasklist.get(tasknumber - 1);
        this.removeTask(tasknumber - 1);
        System.out.println("Meow... ok, I've removed this task: ");
        System.out.println(" " + wantedtask);
        System.out.println("Now you have " + this.Tasklist.size() + " meow-tasks in the list.");
        System.out.println(lines);
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
        String taskInput = command.substring(4).trim();
        this.addTask(taskInput, TaskType.Todo);
    }

    public void addDeadlineTask(String command) throws DukeException {
        String taskInput = command.substring(8);
        String[] ans = taskInput.split("/");
        String deadline = ans[0].substring(1);
        this.addTask(taskInput, TaskType.Deadline);
    }

    public void addEventTask(String command) throws DukeException {
        String taskInput = command.substring(5);
        String[] ans = taskInput.split("/");
        String startdate = ans[0];
        String enddate = ans[0];
        this.addTask(taskInput, TaskType.Event);
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
    public static void main(String[] args) {
        MeowBot meowBot = new MeowBot();
        meowBot.greet();
        meowBot.processCommand();
        meowBot.bye();
    }

    void addTask(String taskname, TaskType eType) throws DukeException{
        Task task = null;
        if (eType == TaskType.Todo) {
            task = new Todo(taskname);
        } else if (eType == TaskType.Event) {
            task = new Event(taskname);
        } else if (eType == TaskType.Deadline) {
            task = new Deadline(taskname);
        }
        this.Tasklist.add(task);
        System.out.println(lines);

        System.out.println("MEOW got it. I've added this task:\n   " + task);
        System.out.println("Now you have " + this.Tasklist.size() + " meow-tasks in the list.");
        System.out.println(lines);
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
}
