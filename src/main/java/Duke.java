import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String div = "____________________________________________________________\n";
        class Task {
            protected String name;
            protected boolean isDone = false;

            public Task(String name) {
                this.name = name;
            }

            public void mark(){
                isDone = true;
                System.out.println("Nice! I've marked this task as done:\n [X] " + name +"\n" + div);
            }

            public void unmark(){
                isDone = false;
                System.out.println("OK, I've marked this task as not done yet:\n [ ] " + name + "\n" + div);
            }

            public String printTask() {
                return (isDone) ? "[X] " + name + "\n": "[ ] " + name + "\n";
            }
        }

        class ToDos extends Task {
            public ToDos(String name) {
                super(name);
            }

            @Override
            public String printTask() {
                return (isDone) ? "[T] [X] " + name + "\n": "[T] [ ] " + name + "\n";
            }
        }

        class Deadline extends Task {
            String due;
            public Deadline(String name, String due) {
                super(name);
                this.due = due;
            }

            @Override
            public String printTask() {
                return (isDone) ? "[D] [X] " + name + "(" + due + ") \n"
                        : "[D] [ ] " + name + "(" + due + ") \n";
            }
        }

        class Events extends Task {
            String start;
            String end;
            public Events(String name, String start, String end) {
                super(name);
                this.start = start;
                this.end = end;
            }

            @Override
            public String printTask() {
                return (isDone) ? "[E] [X] " + name + "(" + start + " " + end + ") \n"
                        : "[E] [ ] " + name + "(" + start + " " + end + ") \n";
            }
        }

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println(div + "Hello! I'm CarrotCake\nWhat can I do for you?\n" + div);

        String input = scanner.nextLine();
        int count = 0;

        while (!input.equalsIgnoreCase("bye")) {
            System.out.println(div);
            if (input.equalsIgnoreCase("list")) {
                //Print tasks
                if (count < 1) {
                    System.out.println("List is empty.");
                    System.out.println(div);
                    input = scanner.nextLine();
                    continue;
                }
                for (int i = 0; i < count; i++) {
                    System.out.print((i+1) + ". " + tasks.get(i).printTask());
                }
                System.out.println(div);
                input = scanner.nextLine();
                continue;
            }
            if (input.toLowerCase().startsWith("mark ")) {
                String[] parts = input.split(" ");
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                if (taskNumber > count) {
                    System.out.println("OOPS!!! Such a task doesn't exist :-(\n" + div);
                    input = scanner.nextLine();
                    continue;
                }
                tasks.get(taskNumber).mark();
                input = scanner.nextLine();
                continue;
            }
            if (input.toLowerCase().startsWith("unmark ")) {
                String[] parts = input.split(" ");
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                if (taskNumber > count) {
                    System.out.println("OOPS!!! Such a task doesn't exist :-(\n" + div);
                    input = scanner.nextLine();
                    continue;
                }
                tasks.get(taskNumber).unmark();
                input = scanner.nextLine();
                continue;
            }
            if (input.toLowerCase().startsWith("delete ")) {
                String[] parts = input.split(" ");
                int taskNumber = Integer.parseInt(parts[1]) - 1;
                if (taskNumber > count) {
                    System.out.println("OOPS!!! Such a task doesn't exist :-(\n" + div);
                    input = scanner.nextLine();
                    continue;
                }
                count--;
                System.out.println("Noted. I've removed this task:\n" + tasks.get(taskNumber).printTask() +
                        "\nNow you have " + count +" tasks in the list.\n" + div);
                tasks.remove(taskNumber);
                input = scanner.nextLine();
                continue;
            }

            if (input.toLowerCase().startsWith("todo ")) {
                input = input.substring(4);
                System.out.println("Got it. I've  added this task: \n[T] [ ]" + input +
                        "\nNow you have "+ (count+1) + " tasks in the list.\n" +div);
                tasks.add(new ToDos(input));
                count++;
            }
            else if (input.toLowerCase().startsWith("deadline ")) {
                String[] parts = input.split("/");
                String due = parts[1];
                input = parts[0].substring(8);
                System.out.println("Got it. I've  added this task: \n[D] [ ]" + input + "(" + due + ")" +
                        "\nNow you have "+ (count+1) + " tasks in the list.\n" +div);
                tasks.set(count, new Deadline(input, due));
            }
            else if (input.toLowerCase().startsWith("event ")) {
                String[] parts = input.split("/");
                String start = parts[1];
                String end = parts[2];
                input = parts[0].substring(5);
                System.out.println("Got it. I've  added this task: \n[E] [ ]" + input + "(" + start + " " + end + ")" +
                        "\nNow you have "+ (count + 1) + " tasks in the list.\n" +div);
                tasks.set(count, new Events(input, start, end));
            }
            else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n" + div);
                input = scanner.nextLine();
                continue;
            }

            input = scanner.nextLine();
        }

        System.out.println(div + "Bye. Hope to see you again soon!\n" + div);
    }
}
