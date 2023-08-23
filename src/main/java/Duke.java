import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String logo = "       .__               \n"
                + "  ____ |__| ____   ____  \n"
                + "/    \\|  |/    \\ /  _  \\\n"
                + "|   |  \\  |   |  (  <_> )\n"
                + "|___|  /__|___|  /\\____/ \n"
                + "     \\/        \\/        ";
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
        String words[] = command.split(" ");
        if(command.equals("bye")) {
            System.out.println(HORIZONTAL);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(HORIZONTAL);
        } else if(command.equals("list")) {
            int length = tasks.size();
            System.out.println(HORIZONTAL);
            for(int i = 0;i < length; i++) {
                int task_number = i + 1;
                Task t =  tasks.get(i);
                System.out.println(task_number + ". "
                        + "["
                        + t.getStatusIcon()
                        + "]"
                        + " "
                        + t);
            }
            System.out.println(HORIZONTAL);
        } else if(words[0].equals("mark")) {
            Task t = tasks.get(Integer.parseInt(words[1]) - 1);
            t.markDone();
            System.out.println(HORIZONTAL);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[" + t.getStatusIcon() + "] " + t);
            System.out.println(HORIZONTAL);

        } else if(words[0].equals(("unmark"))){
            Task t = tasks.get(Integer.parseInt(words[1]) - 1);
            t.markUndone();
            System.out.println(HORIZONTAL);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[" + t.getStatusIcon() + "] " + t);
            System.out.println(HORIZONTAL);
        } else{
            Task t = new Task(command);
            tasks.add(t);
            System.out.println(HORIZONTAL);
            System.out.println("added: " + t);
            System.out.println(HORIZONTAL);
        }
    }
}
