import java.util.*;

public class Duke {
    public static String partition = "------------------------------------------------------------";
    public static ArrayList<Task> taskList = new ArrayList<Task>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    
        System.out.println(partition);
        System.out.println("Hello! I'm Edna-Duke.");
        System.out.println("What can I do for you?");
        System.out.println(partition);
        
        String input = sc.next();
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                print();
            } else if (input.equals("mark")) {
                int item = sc.nextInt();
                taskList.get(item - 1).markDone();
            } else if (input.equals("unmark")) {
                int item = sc.nextInt();
                taskList.get(item - 1).markUndone();
            } else {
                add(new Task(input));
            }
            System.out.println(partition);
            input = sc.next();
        }
        exit();
    }

    public static void add(Task input) {
        taskList.add(input);
        System.out.println("Hi! This task has been added: " + input.getTask());
    }

    public static void print() {
        int index = 1;
        for (Task task: taskList) {
            System.out.println(index + ". " + task.getStatus());
            index ++;
        }
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(partition);
    }
}
