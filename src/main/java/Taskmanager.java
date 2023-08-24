import java.util.Scanner;

public class Taskmanager {
    int counter = 0;
    private Task[] tasks = new Task[100];

    public void manageTasks() {
        Scanner sn = new Scanner(System.in);
        String input = sn.next();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                list();
            } else if (input.equals("mark")) {
                mark(sn.nextInt());
            } else if (input.equals("unmark")) {
                unmark(sn.nextInt());
            } else {
                if (input.equals("todo")) {
                    String description = sn.nextLine();
                    addTask(new ToDos(description));
                } else if (input.equals("deadline")) {
                    String description = sn.next();
                    String temp = sn.next();
                    while (!temp.equals("/by")) {
                        description = description + " " + temp;
                        temp = sn.next();
                    }
                    String time = sn.nextLine();
                    addTask(new Deadlines(description, time));
                } else if (input.equals("event")) {
                    String description = sn.next();
                    String temp = sn.next();
                    while (!temp.equals("/from")) {
                        description = description + " " + temp;
                        temp = sn.next();
                    }
                    String start = sn.next();
                    temp = sn.next();
                    while (!temp.equals("/to")) {
                        start = start + " " + temp;
                        temp = sn.next();
                    }
                    String end = sn.nextLine();  //need handle
                    addTask(new Events(description, start, end));
                } else {
                    System.out.println("undefined keyword, please try again");
                }
            }
            input = sn.next();
        }
    }

    public void mark(int i) { //need handling
        tasks[i-1].setDone();
        System.out.println("Nice! I've marked this task as done:\n  " + tasks[i-1].toString());
    }

    public void unmark(int i) { //need handling
        tasks[i-1].setNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[i-1].toString());
    }

    public void list() {
        if (counter == 0) {
            System.out.println("There is no task in your list yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= counter; i++) {
                System.out.println(i + "." + tasks[i - 1].toString());
            }
        }
    }

    public void addTask(Task t) {
        tasks[counter] = t;
        counter += 1;
        System.out.println("Got it. I've added this task:\n  " + t + "\nNow you have " + counter + " tasks in the list.");
    }
}
