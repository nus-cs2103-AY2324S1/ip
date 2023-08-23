import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String logo = "       .__\n"
                + "  ____ |__| ____   ____\n"
                + "/    \\|  |/    \\ /  _  \\\n"
                + "|   |  \\  |   |  (  <_> )\n"
                + "|___|  /__|___|  /\\____/\n"
                + "     \\/        \\/";
        final String HORIZONTAL = "_____________________________________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println(HORIZONTAL);
        System.out.println("Hello! I'm NINO!");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL);
        while(true) {
            String command = scanner.nextLine();
            evaluate(command, tasks);
            if(command.equals("bye")){
                tasks.clear();
                break;
            }
        }
    }
    public static void evaluate(String command, ArrayList<Task> tasks) {
        final String HORIZONTAL = "_____________________________________________________________";
        // split the command into 2(words[0] first word, words[1] the rest)
        String[] words = command.split(" ", 2);
        if(command.equals("bye")) {
            System.out.println(HORIZONTAL);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(HORIZONTAL);
        } else if(command.equals("list")) {
            int length = tasks.size();
            System.out.println(HORIZONTAL);
            System.out.println("Here are the tasks in your list:");
            for(int i = 0;i < length; i++) {
                int task_number = i + 1;
                Task t =  tasks.get(i);
                System.out.println(task_number
                        + ". "
                        + t);
            }
            System.out.println(HORIZONTAL);
        } else if(words[0].equals("mark")) {
            Task t = tasks.get(Integer.parseInt(words[1]) - 1);
            t.markDone();
            System.out.println(HORIZONTAL);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(t);
            System.out.println(HORIZONTAL);

        } else if(words[0].equals(("unmark"))){
            Task t = tasks.get(Integer.parseInt(words[1]) - 1);
            t.markUndone();
            System.out.println(HORIZONTAL);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(t);
            System.out.println(HORIZONTAL);
        } else if(words[0].equals("todo")){
            // no need to split words[1] further since no deadline
            Todo t = new Todo(words[1]);
            tasks.add(t);
            System.out.println(HORIZONTAL);
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + tasks.size() + " in the list.");
            System.out.println(HORIZONTAL);
        } else if(words[0].equals("deadline")) {
            //split words[1] into 2
            String[] taskWithDeadline = words[1].split("/", 2);
            String c = taskWithDeadline[0];
            String deadline = taskWithDeadline[1].split(" " , 2)[1];
            Deadline t =  new Deadline(c, deadline);
            tasks.add(t);
            System.out.println(HORIZONTAL);
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + tasks.size() + " in the list.");
            System.out.println(HORIZONTAL);
        } else{
            //split based on /from first
            String[] splitCommand = words[1].split("/", 2);
            String c = splitCommand[0];
            String[] splitDeadline = splitCommand[1].split("/", 2);
            String from = splitDeadline[0].split(" ", 2)[1];
            String to = splitDeadline[1].split(" ", 2)[1];
            Event t = new Event(c, from, to);
            tasks.add(t);
            System.out.println(HORIZONTAL);
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + tasks.size() + " in the list.");
            System.out.println(HORIZONTAL);
        }
    }
}
