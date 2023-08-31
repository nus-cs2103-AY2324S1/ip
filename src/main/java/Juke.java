import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.function.Consumer;

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

            public Task(String desc, boolean isDone) {
                this.desc = desc;
                this.isDone = isDone;
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

            public String toData() {
                String done = isDone ? "1" : "0";
                return "|" + isDone + "|" + desc;
            }
        }

        class Todo extends Task {
            public Todo(String desc) {
                super(desc);
            }

            public Todo(String desc, boolean isDone) {
                super(desc, isDone);
            }

            @Override
            public String toString() {
                return "[T]" + super.toString();
            }

            @Override
            public String toData() { return "T" + super.toData(); }
        }

        class Deadline extends Task {
            protected String by;

            public Deadline(String description, String by) {
                super(description);
                this.by = by;
            }

            public Deadline(String description, boolean isDone, String by) {
                super(description, isDone);
                this.by = by;
            }

            @Override
            public String toString() {
                return "[D]" + super.toString() + " (by: " + by + ")";
            }

            @Override
            public String toData() { return "D" + super.toData() + "|" + this.by; }
        }

        class Event extends Task {
            protected String start;
            protected String end;
            public Event(String description, String start, String end) {
                super(description);
                this.start = start;
                this.end = end;
            }

            public Event(String description, boolean isDone, String start, String end) {
                super(description, isDone);
                this.start = start;
                this.end = end;
            }

            @Override
            public String toString() {
                return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
            }

            @Override
            public String toData() { return "E" + super.toData() + "|" + start + "|" + end; }
        }

        Consumer<Task> write = task -> {
            File savefile = new File("./savefile.txt");
            if (!savefile.exists()) {
                try {
                    savefile.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                FileWriter writer = new FileWriter(savefile, true);
                writer.write(task.toData() + "\n");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Consumer<ArrayList<Task>> updateAll = tasks -> {
            File savefile = new File("./savefile.txt");
            if (!savefile.exists()) {
                try {
                    savefile.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                FileWriter deleter = new FileWriter(savefile, false);
                deleter.write("");
                for (Task task : tasks) {
                    FileWriter writer = new FileWriter(savefile, true);
                    writer.write(task.toData() + "\n");
                    writer.flush();
                    writer.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        ArrayList<Task> tasks = new ArrayList<>();

        Function<String, Boolean> parseIsDone = str -> {
            return str.equals("true");
        };

        Function<String, Task> parse = data -> {
            String[] parts = data.split("\\|");
            if (Objects.equals(parts[0], "T")) {
                return new Todo(parts[2], parseIsDone.apply(parts[1]));
            }
            if (Objects.equals(parts[0], "D")) {
                return new Deadline(parts[2], parseIsDone.apply(parts[1]), parts[3]);
            }
            if (Objects.equals(parts[0], "E")) {
                return new Event(parts[2], parseIsDone.apply(parts[1]), parts[3], parts[4]);
            }
            return null;
        };

        Consumer<File> loadAll = savefile -> {
            if (!savefile.exists()) {
                try {
                    savefile.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                BufferedReader reader = new BufferedReader(new FileReader(savefile));
                // Read the lines from the file one by one.
                String line;
                while ((line = reader.readLine()) != null) {
                    tasks.add(parse.apply(line));
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        loadAll.accept(new File("./savefile.txt"));
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
                    updateAll.accept(tasks);
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
                    updateAll.accept(tasks);
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
                    updateAll.accept(tasks);
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
                        write.accept(newTask);
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
                            write.accept(newTask);
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
                            write.accept(newTask);
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
