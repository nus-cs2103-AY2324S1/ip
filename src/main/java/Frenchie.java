import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Frenchie {
    public List<Task> tasks = new ArrayList<>();

    //constructor
    public Frenchie(){
        List<Task> tasks = new ArrayList<>();
    }

    public void addTask(String TASK_NAME) {
        Task NEW_TASK = new Task(TASK_NAME);
        tasks.add(NEW_TASK);
    }

    public void listTasks() {
        int counter = 1;
        for (Task task: tasks) {
            System.out.println(counter + ". " + task.toString());
            counter += 1;
        }
    }
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo); */
        Frenchie frenchie = new Frenchie();
        String skeleton = "____________________________________________________________\n" +
                " Hello! I'm Frenchie\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n"
                /* " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n" */;
        System.out.println(skeleton);



        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if(input.equals("bye")){
                System.out.println("____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
                break;

            } else if (input.equals("list")) {
                frenchie.listTasks();
            } else if (input.contains("mark")){
                System.out.println(input);
            } else {
                frenchie.addTask(input);
                /*user_input += counter + ". " + input + "\n";
                counter += 1;
                input += "\n"; */
                System.out.println("____________________________________________________________\n" +
                        "added: " + input + "\n" +
                        "____________________________________________________________\n");
            }
        }
    }
}
