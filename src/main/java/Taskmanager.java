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
                String description = input + sn.nextLine();
                addTask(description);
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
        for (int i = 1; i <= counter; i++) {
            System.out.println(i + "." + tasks[i-1].toString());
        }
    }

    public void addTask(String description) {
        Task t = new Task(description);
        tasks[counter] = t;
        counter += 1;
    }
}
