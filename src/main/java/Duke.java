import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        class DukeException extends Exception {
            public DukeException(String message) {
                super(message);
            }
        }

        class Task {

            final protected String description;
            protected boolean isDone;

            public Task(String description) {
                this.description = description;
                this.isDone = false;
            }

            public void mark() throws DukeException {
                if (this.isDone) {
                    throw new DukeException("Task already done");
                }
                this.isDone = true;
            }

            public void unmark() throws DukeException {
                if (!this.isDone) {
                    throw new DukeException("Task still undone");
                }
                this.isDone = false;
            }

            private String getStatusIcon() {
                return (isDone ? "X" : " "); // mark done task with X
            }

            @Override
            public String toString() {
                return ("[" + this.getStatusIcon() + "] " + this.description);
            }
        }

        class Todo extends Task {
            public Todo (String desc) throws DukeException {
                super(desc.substring(5));
                if (desc.substring(5).isEmpty()) {
                    throw new DukeException("You forgot to enter the task!");
                }
            }

            @Override
            public String toString() {
                return "[T]" + super.toString();
            }
        }

        class Deadline extends Task {
            final String by;
            public Deadline(String desc) throws DukeException {
                super(desc.substring(9, desc.indexOf("/by")));
                this.by = desc.substring(desc.indexOf("/by") + 4);
            }

            @Override
            public String toString() {
                return "[D]" + super.toString() + "(by: " + this.by + ")";
            }
        }

        class Event extends Task {
            final String from;
            final String to;
            public Event(String desc) throws DukeException {
                super(desc.substring(6, desc.indexOf("/from")));
                int fromIndex = desc.indexOf("/from");
                int toIndex = desc.indexOf("/to");
                try {
                    this.from = desc.substring(fromIndex + 6, toIndex);
                    this.to = desc.substring(toIndex + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("You didn't enter the event in the correct format!");
                }
                if (from.isEmpty() || to.isEmpty()) {
                    throw new DukeException("One of the fields are empty!");
                }
            }

            @Override
            public String toString() {
                return "[E]" + super.toString() + "(from: " + this.from + "to: " + this.to +")";
            }
        }

        Scanner scan = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String name = "Chaty";
        System.out.println("Hello! I'm " + name + "\n" +
                "What can I do for you?" + "\n\n");
        String next = scan.nextLine();
        while (!next.equals("bye")) {
            try {
            if (next.equals("list")) {
                for (int i = 0; i < tasks.size() ; i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
            } else if (next.split(" ").length < 2) {
                throw new DukeException("You forgot to enter the task!");
            }else if (next.startsWith("mark")) {
                int tasknum = Integer.parseInt(next.split(" ")[1]) - 1;
                tasks.get(tasknum).mark();
                System.out.println("Nice! I've marked this task as done:\n" + tasks.get(tasknum));
            } else if (next.startsWith("unmark")) {
                int tasknum = Integer.parseInt(next.split(" ")[1]) - 1;
                tasks.get(tasknum).unmark();
                System.out.println(" OK, I've marked this task as not done yet:\n" + tasks.get(tasknum));
            } else if (next.startsWith("deadline")){
                if (!next.contains("/by") || next.length() <= next.indexOf("/by") + 4) {
                    throw new DukeException("You forgot to specify when the deadline ends!");
                }
                    Task nextTask = new Deadline(next);
                    tasks.add(nextTask);
                    System.out.println("Got it. I've added this task: \n" + nextTask + "\nnow you have " + tasks.size() + " tasks in the list");
            } else if (next.startsWith("event")){
                if (!next.contains("/from")) {
                    throw new DukeException("You forgot to specify when the event starts!");
                }
                if (!next.contains("/to")) {
                    throw new DukeException("You forgot to specify when the event ends!");
                }
                Task nextTask = new Event(next);
                tasks.add(nextTask);
                System.out.println("Got it. I've added this task: \n" + nextTask + "\nnow you have " + tasks.size() + " tasks in the list");
            } else if (next.startsWith("todo")){
                Task nextTask = new Todo(next);
                tasks.add(nextTask);
                System.out.println("Got it. I've added this task: \n" + nextTask + "\nnow you have " + tasks.size() + " tasks in the list");
            } else if (next.startsWith("delete")){
                if (tasks.size() <= 0) {
                    throw new DukeException("There are no tasks to delete");
                }
                int deleteIndex = Integer.parseInt(next.split(" ")[1]) - 1;
                Task deleted = tasks.remove(deleteIndex);
                System.out.println("Noted. I've removed this task:\n" + deleted + "\nNow you have " + tasks.size() + " tasks in the list");
            } else {
                throw new DukeException("Sorry I don't understand your input!");
            }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            next = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}