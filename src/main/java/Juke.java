import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Juke {
    static void printLine() {
        System.out.println("_______________________________________________________");
    }
    public static void main(String[] args) {
        class Task {
            protected final String desc;
            protected boolean isDone;

            public Task(String desc) {
                this.desc = desc;
                this.isDone = false;
            }

            public String getStatusIcon() {
                return (isDone ? "[X] " : "[ ] "); // mark done task with X
            }

            public void markAsDone() {
                this.isDone = true;
            }

            public void markAsUndone() {
                this.isDone = false;
            }

            @Override
            public String toString() {
                return this.getStatusIcon() + desc;
            }
        }

        class Todo extends Task {
            public Todo(String desc) {
                super(desc);
            }

            @Override
            public String toString() {
                return "[T]" + super.toString();
            }
        }

        class Deadline extends Task {
            protected String by;

            public Deadline(String description, String by) {
                super(description);
                this.by = by;
            }

            @Override
            public String toString() {
                return "[D]" + super.toString() + " (by: " + by + ")";
            }
        }

        class Event extends Task {
            protected String start;
            protected String end;
            public Event(String description, String start, String end) {
                super(description);
                this.start = start;
                this.end = end;
            }

            @Override
            public String toString() {
                return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
            }
        }

        ArrayList<Task> tasks = new ArrayList<Task>();

        //Introduce itself to the user
        System.out.println("Hello! I'm Juke!");
        System.out.println("What can I do for you?");
        printLine();
        while(true) {
            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
            try {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("bye")) {
                    //Say goodbye
                    System.out.println("Bye. Hope to see you again soon!");
                    printLine();
                    scanner.close();  // Close the scanner before exiting
                    return;
                }

                if (input.equalsIgnoreCase("list")) {
                    int count = 1;
                    for (Task task : tasks) {
                        System.out.println(count + ": " + task.toString());
                        count++;
                    }
                    printLine();
                    continue;
                }

                if (input.contains("unmark ")) {
                    int index = Integer.parseInt(input.substring(7));
                    if (index > tasks.size()) {
                        throw new JukeError("That task does not exist!");
                    }
                    Task currTask = tasks.get(index - 1);
                    currTask.markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet: \n" + currTask.toString());
                    printLine();
                    continue;
                }

                if (input.contains("mark ")) {
                    int index = Integer.parseInt(input.substring(5));
                    if (index > tasks.size()) {
                        throw new JukeError("That task does not exist!");
                    }
                    Task currTask = tasks.get(index - 1);
                    currTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done: \n" + currTask.toString());
                    printLine();
                    continue;
                }

                if (input.startsWith("delete ")) {
                    int index = Integer.parseInt(input.substring(7));
                    if (index > tasks.size()) {
                        throw new JukeError("That task does not exist!");
                    }
                    Task currTask = tasks.get(index - 1);
                    tasks.remove(index - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("\t" + currTask.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    printLine();
                }

                else { //check for task creation
                    if (input.startsWith("todo")) {
                        if (input.length() < 5 || input.substring(5).length() == 0) {
                            throw new JukeError("The description of a todo cannot be empty.");
                        }
                        Task newTask = new Todo(input.substring(5));
                        tasks.add(newTask);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("\t" + newTask.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        printLine();
                    } else if (input.startsWith("deadline")) {
                        final Pattern deadlinePattern = Pattern.compile(
                                "^deadline\\s+(.*)\\s+/by\\s+(.*)$");
                        Matcher matcher = deadlinePattern.matcher(input);
                        if (matcher.matches()) {
                            Task newTask = new Deadline(matcher.group(1), matcher.group(2));
                            tasks.add(newTask);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("\t" + newTask.toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                            printLine();
                        }
                    } else if (input.startsWith("event")) {
                        final Pattern eventPattern = Pattern.compile(
                                "^event\\s+(.*)\\s+/from\\s+(.*)\\s+/to\\s+(.*)$");
                        Matcher matcher = eventPattern.matcher(input);
                        if (matcher.matches()) {
                            Task newTask = new Event(matcher.group(1), matcher.group(2), matcher.group(3));
                            tasks.add(newTask);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("\t" + newTask.toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                            printLine();
                        }
                    } else {
                        throw new JukeError("I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (JukeError error) {
                System.out.println("☹ OOPS!!! " + error.getMessage());
                printLine();
            }
        }
    }
}
