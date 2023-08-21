import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    enum TaskType {Event, Deadline, Todo};
    ArrayList<Task> Tasklist;
    int counter;
    String lines;

    public Duke() {
        this.Tasklist = new ArrayList<>();
        this.counter = 0;
        this.lines = "______________________________";
    }
    public static void main(String[] args) {
        String lines = "______________________________";
        Scanner scan = new Scanner(System.in);
        Duke meowBot = new Duke();

        System.out.println(lines);
        System.out.println("Hello! I'm MeowBot!");
        System.out.println("What can I do for you ?");
        System.out.println(lines);

        String command = scan.nextLine();

        while (!command.equals("bye")) {
            // Keywords are "list", "mark" and "unmark"
            try {
                meowBot.getCommand(command);
                if (command.equals("list")) {
                    System.out.println(lines);
                    System.out.println("Meoowww here are your tasks");
                    for (int i = 1; i < meowBot.Tasklist.size() + 1; i++) {
                        System.out.println(i + ". " + meowBot.Tasklist.get(i - 1));
                    }
                    System.out.println(lines);
                } else if (command.startsWith("mark")) {
                    int tasknumber = Integer.parseInt(command.substring(5));
                    Task wantedtask = meowBot.Tasklist.get(tasknumber - 1); //account for 0 indexing
                    wantedtask.markCompleted();
                    System.out.println("Nice! I've meowrked this task as done: ");
                    System.out.println("   " + wantedtask);
                    System.out.println(lines);

                } else if (command.startsWith("unmark")) {
                    int tasknumber = Integer.parseInt(command.substring(7));
//                System.out.println("tasknumber is"+ tasknumber);
                    Task wantedtask = meowBot.Tasklist.get(tasknumber - 1); //account for 0 indexing
                    wantedtask.markUncompleted();
                    System.out.println("Ok, get your task done soon, I'll be waiting!");
                    System.out.println(" " + wantedtask);
                    System.out.println(lines);
                } else if (command.startsWith("delete")) {
                    int tasknumber = Integer.parseInt(command.substring(7));
                    Task wantedtask = meowBot.Tasklist.get(tasknumber - 1);
                    meowBot.removeTask(tasknumber - 1);
                    System.out.println("Meow... ok, I've removed this task: ");
                    System.out.println(" " + wantedtask);
                    System.out.println("Now you have " + meowBot.Tasklist.size() + " meow-tasks in the list.");
                    System.out.println(lines);



                }

                // solve what tasks are to be added here
                else {
                    if (command.startsWith("todo")) {
                        String taskInput = command.substring(4).trim();
                        meowBot.addTask(taskInput, TaskType.Todo);


                    } else if (command.startsWith("deadline")) {
                        String taskInput = command.substring(8);
                        String[] ans = taskInput.split("/");
                        String deadline = ans[0].substring(1);
                        meowBot.addTask(taskInput, TaskType.Deadline);


                    } else if (command.startsWith("event")) {
                        String taskInput = command.substring(5);
                        String[] ans = taskInput.split("/");
                        String startdate = ans[0];
                        String enddate = ans[0];
                        meowBot.addTask(taskInput, TaskType.Event);

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

        System.out.println(lines);
        System.out.println("Bye. Hope to see you again soon!");
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
        // cast the task to the right subtype

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
