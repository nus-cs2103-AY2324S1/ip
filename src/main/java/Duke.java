import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

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
            }

            public void unmark(){
                isDone = false;
            }

            public String printTask() {
                return (isDone) ? "[X] " + name + "\n": "[ ] " + name + "\n";
            }

            public void addToFile() {
                try {
                    FileWriter Writer
                            = new FileWriter("data/data.txt", true);
                    Writer.write(
                            "  | 0 | " + name);
                    Writer.close();
                }
                catch (IOException e) {
                    System.out.println("An error has occurred.");
                    e.printStackTrace();
                }
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

            @Override
            public void addToFile() {
                try {
                    FileWriter Writer
                            = new FileWriter("data/data.txt", true);
                    Writer.write("T | 0 | " + name + "\n");
                    Writer.close();
                }
                catch (IOException e) {
                    System.out.println("An error has occurred.");
                    e.printStackTrace();
                }
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

            @Override
            public void addToFile() {
                try {
                    FileWriter Writer
                            = new FileWriter("data/data.txt", true);
                    Writer.write("D | 0 | " + name + " | " + due + "\n");
                    Writer.close();
                }
                catch (IOException e) {
                    System.out.println("An error has occurred.");
                    e.printStackTrace();
                }
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
                        : "[E] [ ] " + name + "(from: " + start + " to: " + end + ") \n";
            }

            @Override
            public void addToFile() {
                try {
                    FileWriter Writer
                            = new FileWriter("data/data.txt", true);
                    Writer.write("D | 0 | " + name + " | " + start + " | " + end + "\n");
                    Writer.close();
                }
                catch (IOException e) {
                    System.out.println("An error has occurred.");
                    e.printStackTrace();
                }
            }
        }

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        int count = 0;
        File file = new File("data/data.txt");

        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String input = fileReader.nextLine();
                String[] parts = input.split("\\|");

                if (parts[0].trim().equalsIgnoreCase("t")) {
                    Task task = new ToDos(parts[2]);
                    tasks.add(task);
                    count++;
                    if (parts[1].trim().equalsIgnoreCase("1")) {
                        task.mark();
                    }
                }
                else if (parts[0].trim().equalsIgnoreCase("d")) {
                    String due = parts[3].trim();
                    Task task = new Deadline(parts[2], due);
                    tasks.add(task);
                    count++;
                    if (parts[1].trim().equalsIgnoreCase("1")) {
                        task.mark();
                    }
                }
                else if (parts[0].trim().equalsIgnoreCase("e")) {
                    String start = parts[3].trim();
                    String end = parts[4].trim();
                    Task task = new Events(parts[2], start, end);
                    tasks.add(task);
                    count++;
                    if (parts[1].trim().equalsIgnoreCase("1")) {
                        task.mark();
                    }
                }
            }
            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }

        System.out.println(div + "Hello! I'm CarrotCake\nWhat can I do for you?\n" + div);

        String input = scanner.nextLine();

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
                System.out.println("Nice! I've marked this task as done:\n [X] " + tasks.get(taskNumber).name +"\n" + div);
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
                System.out.println("OK, I've marked this task as not done yet:\n [ ] "
                        + tasks.get(taskNumber).name + "\n" + div);
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
                ToDos todo = new ToDos(input);
                tasks.add(todo);
                todo.addToFile();
                count++;
            }
            else if (input.toLowerCase().startsWith("deadline ")) {
                String[] parts = input.split("/");
                String due = parts[1];
                input = parts[0].substring(8);
                System.out.println("Got it. I've  added this task: \n[D] [ ]" + input + "(" + due + ")" +
                        "\nNow you have "+ (count+1) + " tasks in the list.\n" +div);
                Deadline deadline = new Deadline(input, due);
                tasks.add(deadline);
                deadline.addToFile();
            }
            else if (input.toLowerCase().startsWith("event ")) {
                String[] parts = input.split("/");
                String start = parts[1];
                String end = parts[2];
                input = parts[0].substring(5);
                System.out.println("Got it. I've  added this task: \n[E] [ ]" + input + "(" + start + " " + end + ")" +
                        "\nNow you have "+ (count + 1) + " tasks in the list.\n" +div);
                Events event = new Events(input, start, end);
                tasks.add(event);
                event.addToFile();
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
