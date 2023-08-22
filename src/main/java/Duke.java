import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
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
    public static void evaluate(String command, ArrayList<String> tasks) {
        final String HORIZONTAL = "_____________________________________________________________";
        if(command.equals("bye")) {
            System.out.println(HORIZONTAL);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(HORIZONTAL);
        } else if(command.equals("list")) {
            int length = tasks.size();
            System.out.println(HORIZONTAL);
            for(int i = 0;i < length; i++) {
                int task_number = i + 1;
                System.out.println(task_number + ". " + tasks.get(i));
            }
            System.out.println(HORIZONTAL);
        } else {
            tasks.add(command);
            System.out.println(HORIZONTAL);
            System.out.println("added: " + command);
            System.out.println(HORIZONTAL);
        }
    }
}
