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
            try {
                switch(input) {
                    case "list":
                        print();
                        System.out.println(partition);
                        break;
                    case "mark":
                        int num = sc.nextInt();
                        taskList.get(num - 1).markDone();
                        System.out.println(partition);
                        break;
                    case "unmark":
                        int num1 = sc.nextInt();
                        taskList.get(num1 - 1).unmarkDone();
                        System.out.println(partition);
                        break;
                    case "delete":
                        int del = sc.nextInt();
                        delete(del);
                        System.out.println(partition);
                        break;
                    case "todo":
                        add(new ToDo(sc.nextLine()));
                        System.out.println(partition);
                        break;
                    case "deadline":
                        String ddl = sc.nextLine();
                        String[] ddlCommand = ddl.split(" /by ");
                        Deadline temp1 = new Deadline(ddlCommand[0].substring(1), ddlCommand[1]);
                        add(temp1);
                        System.out.println(partition);
                        break;
                    case "event":
                        String event = sc.nextLine();
                        String[] eventCommand = new String[3];
                        eventCommand[0] = event.substring(1, event.indexOf(" /"));
                        eventCommand[1] = event.substring(event.indexOf("/from") + 6, event.indexOf(" /to"));
                        eventCommand[2] = event.substring(event.indexOf("/to") + 4);
                        Event temp2 = new Event(eventCommand[0], eventCommand[1],eventCommand[2] );
                        add(temp2);
                        System.out.println(partition);
                        break;
                    default:
                        add(new Task(null));
                        System.out.println(partition);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
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

    public static void delete(int num) {
        System.out.println("Noted. I've removed this task:");
        taskList.get(num-1).getStatus();
        taskList.remove(num - 1);
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
