import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {

    private boolean active = true;
    private List<Task> list = new ArrayList<>();

    private void greet() {
        System.out.println("Hello! I'm Aikent\n" + "What can I do for you?");
    }

    private void exit() {
        active = false;
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void echo(String msg) {
        if (msg.equals("bye")) {
            exit();
        } else {
            System.out.println(msg);
        }
    }

    private void printListSize() {
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
    }

    private void respond(String msg) {
        String[] input = msg.split(" ", 2);
        String command = input[0];
        String specifications = input.length > 1 ? input[1] : "";
        switch (command) {
            case "bye":
                exit();
                break;
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int index = 0; index < this.list.size(); index++) {
                    Task item = this.list.get(index);
                    System.out.println((index + 1) + ". " + item.toString());
                }
                break;
            case "mark":
                int index = Integer.parseInt(specifications) - 1;
                this.list.get(index).markAsDone();
                System.out.println(this.list.get(index).toString());
                break;
            case "unmark":
                int i = Integer.parseInt(specifications) - 1;
                this.list.get(i).markAsUndone();
                System.out.println(this.list.get(i).toString());
                break;
            case "todo":
                Task toDoTask = new ToDo(specifications);
                this.list.add(toDoTask);
                toDoTask.notice();
                this.printListSize();
                break;
            case "deadline":
                String[] splits = specifications.split("/by", 2);
                String description = splits[0];
                String date = splits[1];
                Task deadlineTask = new Deadline(description, date);
                this.list.add(deadlineTask);
                deadlineTask.notice();
                this.printListSize();
                break;
            case "event":
                String[] split = specifications.split("/from", 2);
                String event = split[0];
                String[] timings = split[1].split("/to", 2);
                String start = timings[0];
                String end = timings[1];
                Task eventTask = new Event(event, start, end);
                this.list.add(eventTask);
                eventTask.notice();
                this.printListSize();
                break;
            default:
                this.list.add(new Task(msg));
                System.out.println("added: " + msg);
        }
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        Scanner sc = new Scanner(System.in);

        bot.greet();

        while (bot.active) {
            String input = sc.nextLine();
            bot.respond(input);
        }
    }
}
