import java.util.ArrayList;
import java.util.Scanner;

public class Dre {
    private ArrayList<Task> list;

    public Dre() {
        list = new ArrayList<>();
    }
    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Dre");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void add(String next) {
        if (next.startsWith("todo")) {
            ToDo task = new ToDo(next.substring(5));
            list.add(task);

            System.out.println("Got it. I've added this task:");
            System.out.println(task.toString());
        } else if (next.startsWith("deadline")) {
            Deadline task = new Deadline(next.substring(9, next.lastIndexOf('/') - 1), next.substring(next.lastIndexOf("/by") + 4));
            list.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task.toString());
        } else if (next.startsWith("event")) {
            Event task = new Event(next.substring(6, next.indexOf("/from") - 1),
                                    next.substring(next.indexOf("/from") + 6, next.indexOf("/to") - 1),
                                    next.substring(next.indexOf("/to") + 4));
            list.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task.toString());
        } else {
            //throw error
        }
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void list() {
        for(int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).toString());
        }
    }

    public void mark(String next) {
        int start = next.lastIndexOf(' ');
        int taskIndex = Integer.parseInt(next.substring(start + 1));
        //error handling for non integer
        Task currTask = list.get(taskIndex - 1);
        currTask.done();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(currTask.toString());
    }

    public void unmark(String next) {
        int start = next.lastIndexOf(' ');
        int taskIndex = Integer.parseInt(next.substring(start + 1));
        //error handling for non integer
        Task currTask = list.get(taskIndex - 1);
        currTask.done();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(currTask.toString());
    }

    public static void main(String[] args) {
        Dre dre = new Dre();
        dre.greet();
        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        while (!next.equals("bye")) {
            if (next.equals("list")) {
                dre.list();
            } else if (next.startsWith("mark")) {
                dre.mark(next);
            } else if (next.startsWith("unmark")) {
                dre.unmark(next);
            } else {
                dre.add(next);
            }
            next = sc.nextLine();
        }
        sc.close();
        dre.exit();
    }
}
