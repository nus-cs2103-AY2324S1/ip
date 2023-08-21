import java.util.Scanner;
public class Duke {
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
            else{
                meowBot.addTask(command);
            }

            // asks for the next keyword
            command = scan.nextLine();

        }


        System.out.println(lines);
        System.out.println("Bye. Hope to see you again soon!");
    }

    void addTask(String taskname){
        Task task = new Task(taskname);
        this.Tasklist[counter] = task;
        counter ++;
        System.out.println(lines);
        System.out.println("added: " + task);
        System.out.println(lines);
    }
}
