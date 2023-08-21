import java.util.Scanner;
public class Duke {

    enum EventType {Event, Deadline, Todo};
    Task[] Tasklist;
    int counter;
    String lines;

    public Duke() {
        this.Tasklist = new Task[100];
        this.counter = 0;
        this.lines = "______________________________";
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
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
            if (command.equals("list")) {
                System.out.println(lines);
                for (int i = 1; i < meowBot.counter + 1; i++) {
                    System.out.println(i + ". " + meowBot.Tasklist[i-1]);
                }
                System.out.println(lines);
            }
            else if (command.startsWith("mark ")){
                int tasknumber = Integer.parseInt(command.substring(5));
                Task wantedtask = meowBot.Tasklist[tasknumber - 1]; //account for 0 indexing
                wantedtask.markCompleted();
                System.out.println("Nice! I've meowrked this task as done: ");
                System.out.println("   " + wantedtask);
                System.out.println(lines);

            }
            else if (command.startsWith("unmark ")) {
                int tasknumber = Integer.parseInt(command.substring(7));
//                System.out.println("tasknumber is"+ tasknumber);
                Task wantedtask = meowBot.Tasklist[tasknumber - 1]; //account for 0 indexing
                wantedtask.markUncompleted();
                System.out.println("Ok, get your task done soon, I'll be waiting!");
                System.out.println(" " + wantedtask);
                System.out.println(lines);
            }

            // solve what tasks are to be added here
            else{
                if (command.startsWith("todo ")) {
                    String taskInput = command.substring(5);
                    meowBot.addTask(taskInput, EventType.Todo);




                } else if (command.startsWith("deadline ")) {
                    String taskInput = command.substring(8);
                    String[] ans = taskInput.split("/");
                    String deadline = ans[0].substring(1);
                    meowBot.addTask(taskInput, EventType.Deadline);



                }

                else if (command.startsWith("event ")) {
                    String taskInput = command.substring(5);
                    String[] ans = taskInput.split("/");
                    String startdate = ans[0];
                    String enddate = ans[0];
                    meowBot.addTask(taskInput, EventType.Event);


                }
            }

            // asks for the next keyword
            command = scan.nextLine();

        }


        System.out.println(lines);
        System.out.println("Bye. Hope to see you again soon!");
    }

    void addTask(String taskname, EventType eType){
        Task task = null;
        if (eType == EventType.Todo) {
            task = new Todo(taskname);
        } else if (eType == EventType.Event) {
            task = new Event(taskname);
        } else if (eType == EventType.Deadline) {
            task = new Deadline(taskname);

        }
        this.Tasklist[counter] = task;
        counter ++;
        System.out.println(lines);
        // cast the task to the right subtype

        System.out.println("MEOW got it. I've added this task:\n   " + task);
        System.out.println("Now you have " + counter + " meow-tasks in the list.");
        System.out.println(lines);
    }
}
