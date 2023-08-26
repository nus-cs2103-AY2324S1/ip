import java.util.Scanner;

public class Bot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm [YOUR CHATBOT NAME]\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");
        String str = sc.nextLine();
        Storage<Task> storage = new Storage<>();
        while (!str.equals("bye")) {
            Task task;
            if (str.equals("list")) {
                System.out.println(storage.list());
            } else if (str.contains("mark")) {
                int idx = Integer.parseInt(str.substring(str.length() - 1)) - 1;
                Task update = storage.get(idx).complete();
                storage = storage.update(idx, update);
                System.out.println("____________________________________________________________\n" +
                        "Nice! I've marked this task as done:\n" +
                        update +
                        "____________________________________________________________\n");
            } else if (str.contains("unmark")) {
                int idx = Integer.parseInt(str.substring(str.length() - 1)) - 1;
                Task update = storage.get(idx).incomplete();
                storage = storage.update(idx, update);
                System.out.println("____________________________________________________________\n" +
                        "OK, I've marked this task as not done yet:" +
                        update +
                        "____________________________________________________________\n");
            } else {
                task = new Task(str);
                System.out.println("____________________________________________________________\n" +
                        "added: " + str + "\n" +
                        "____________________________________________________________");
                storage = storage.save(task);
            }
            str = sc.nextLine();
        }
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
