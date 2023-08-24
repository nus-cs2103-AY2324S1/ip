import java.util.*;
public class Edna {
    public static ArrayList<Task> taskList = new ArrayList<Task>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;

        String partition = "--------------------------------------------------";

        System.out.println(partition);
        System.out.println("Hello! I'm Edna.");
        System.out.println("What can I do for you?");
        System.out.println(partition);

        input = sc.next();

        while(input.equals("bye") == false) {
            if (input.equals("list")) {
                print();
                System.out.println(partition);
            } else if (input.equals("mark")) {
                int num = sc.nextInt();
                taskList.get(num - 1).markDone();
                System.out.println(partition);
            } else if (input.equals("unmark")) {
                int num = sc.nextInt();
                taskList.get(num - 1).unmarkDone();
                System.out.println(partition);
            } else if (input.equals("todo")){
                ToDo temp = new ToDo(sc.nextLine().substring(1));
                add(temp);
                System.out.println(partition);
            } else if (input.equals("deadline")){
                String ddl = sc.nextLine();
                String[] ddlCommand = ddl.split(" /by ");
                Deadline temp = new Deadline(ddlCommand[0].substring(1), ddlCommand[1]);
                add(temp);
                System.out.println(partition);
            } else if (input.equals("event")){
                String event = sc.nextLine();
                String[] eventCommand = new String[3];
                eventCommand[0] = event.substring(1, event.indexOf(" /"));
                eventCommand[1] = event.substring(event.indexOf("/from") + 6, event.indexOf(" /to"));
                eventCommand[2] = event.substring(event.indexOf("/to") + 4);
                Event temp = new Event(eventCommand[0], eventCommand[1],eventCommand[2] );
                add(temp);
                System.out.println(partition);
            } else {
                add(new Task(input));
                System.out.println(partition);
            }
            input = sc.next();
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(partition);
    }

    public static void add(Task input) {
        taskList.add(input);
        System.out.println("Got it. I've added this task:");
        System.out.println(input.getStatus());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void print() {
        int num = 1;
        for(Task temp: taskList) {
            System.out.println(num + ". " + temp.getStatus());
            num++;
        }
    }
}
