import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class for the ChatBot
 */
public class Duke {
    public Scanner sc = new Scanner(System.in).useDelimiter("[\\s,\\s/]+");
    public static ArrayList<Task> taskArr = new ArrayList<>();
    public class Task {
        /**
         * Description of the specific task.
         */
        protected String description;
        /**
         * Boolean for whether the task is completed or not.
         */
        protected boolean isDone;

        /**
         * Constructor for the Task object.
         * @param description The description of what the task is about
         */
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        /**
         * String of the task's completion status.
         * @return An "X" if the task is completed and a blank space otherwise
         */
        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        /**
         * Mark the task as done.
         */
        public void mark() {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:\n"
            + "[X] " + this.desc());
        }

        /**
         * Unmark a task as not done yet.
         */
        public void unmark() {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:\n"
            + "[ ] " + this.desc());
        }

        /**
         * Returns the description of the task.
         * @return Description of the task in a String
         */
        public String desc() {
            return this.description;
        }
    }

    /**
     * Special kind of task that only has a description
     */
    public class Todo extends Task {
        /**
         * String that signifies the specific type of task.
         */
        protected String type = "T";
        public Todo(String description) {
            super(description);
        }
        @Override
        public void mark() {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:\n"
                    + this);
        }
        @Override
        public void unmark() {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:\n"
                    + this);
        }
        @Override
        public String toString() {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "]" + this.description;
        }
    }
    public class Deadline extends Task {
        protected String type = "D";
        protected String date;
        public Deadline(String description, String date) {
            super(description);
            this.date = date;
        }
        @Override
        public void mark() {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:\n"
            + this);
        }
        @Override
        public void unmark() {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:\n"
            + this);
        }
        @Override
        public String toString() {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description
                    + " (by:" + this.date + ")";
        }
    }
    public class Event extends Task {
        protected String type = "E";
        protected String from;
        protected String to;
        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }
        @Override
        public void mark() {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:\n"
                    + this);
        }
        @Override
        public void unmark() {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:\n"
                    + this);
        }
        @Override
        public String toString() {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description
                    + " (from: " + this.from + " to:" + this.to + ")";
        }
    }
    public static class DukeException extends Exception {
        public DukeException(String errorMessage) {
            super(errorMessage);
        }
        public static class EmptyDescription extends DukeException {
            public EmptyDescription() {
                super("-------------------------------\n"
                        + "☹ OOPS!!! The description of a todo cannot be empty.\n"
                        + "-------------------------------\n");
            }
        }
        public static class WrongInput extends DukeException {
            public WrongInput() {
                super("-------------------------------\n"
                      +  "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                      +  "-------------------------------\n");
            }
        }
    }
    public void addTodo(String desc) {
        Todo curr = new Todo(desc);
        taskArr.add(curr);
        System.out.println("-------------------------------\n"
                + "Got it. I've added this task:\n"
                + curr.toString()
                + totalTasks()
                + "\n-------------------------------\n");
    }
    public void addDeadline(String desc, String date) {
        Deadline curr = new Deadline(desc, date);
        taskArr.add(curr);
        System.out.println("-------------------------------\n"
                + "Got it. I've added this task:\n"
                + curr.toString()
                + totalTasks()
                + "\n-------------------------------\n");
    }
    public void addEvent(String desc, String from, String to) {
        Event curr = new Event(desc, from, to);
        taskArr.add(curr);
        System.out.println("-------------------------------\n"
                + "Got it. I've added this task:\n"
                + curr.toString()
                + totalTasks()
                + "\n-------------------------------\n");
    }
    public void delete(int num) {
        Task toRemove = taskArr.get(num);
        taskArr.remove(num);
        System.out.println("-------------------------------\n"
        + "Noted, I've removed this task:\n"
        + toRemove.toString()
        + totalTasks()
        + "\n-------------------------------\n");
    }
    public void listOut() {
        int size = taskArr.size();
        System.out.println("-------------------------------\n"
        + "Here are the tasks in your list:");
        for (int i = 0; i < size; i++) {
            System.out.println(i + 1 + "." + taskArr.get(i).toString());
        }
        System.out.println("-------------------------------\n");
    }
    public String totalTasks() {
        int size = taskArr.size();
        return "\nNow you have " + size + " tasks in the list.";
    }

    public static void main(String[] args) {

        Duke bot = new Duke();

        String greeting = "-------------------------------\n"
                + "Hello! I'm Skog.\n"
                + "What can I do for you?\n"
                + "-------------------------------\n";

        String exit = "-------------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "-------------------------------\n";

        System.out.println(greeting);

        try {
            while (bot.sc.hasNext()) {
                String str = bot.sc.next();
                if (str.equals("bye")) {
                    System.out.println(exit);
                    taskArr.clear();
                    bot.sc.close();
                    break;
                } else if (str.equals("list")) {
                    bot.listOut();
                } else if (str.equals("mark")) {
                    if (!bot.sc.hasNextInt()) {
                        throw new DukeException.WrongInput();
                    } else {
                        int num = bot.sc.nextInt();
                        bot.taskArr.get(num - 1).mark();
                    }
                } else if (str.equals("unmark")) {
                    if (!bot.sc.hasNextInt()) {
                        throw new DukeException.WrongInput();
                    } else {
                        int num = bot.sc.nextInt();
                        bot.taskArr.get(num - 1).unmark();
                    }
                } else if (str.equals("delete")) {
                    if (!bot.sc.hasNextInt()) {
                        throw new DukeException.WrongInput();
                    } else {
                        int num = bot.sc.nextInt();
                        if (num > taskArr.size()) {
                            throw new DukeException.WrongInput();
                        } else {
                            bot.delete(num - 1);
                        }
                    }
                }
                else {
                    // check for task type first
                    if (str.equals("todo")) {
                        String desc = bot.sc.nextLine();
                        if (desc.equals("") || desc.equals(" ")) {
                            throw new DukeException.EmptyDescription();
                        } else {
                            bot.addTodo(desc);
                        }
                    } else if (str.equals("deadline")) {
                        String desc = bot.sc.next();
                        String date = null;
                        while (bot.sc.hasNext()) {
                            String next = bot.sc.next();
                            if (!next.equals("by")) {
                                desc = desc + " " + next;
                            } else {
                                date = bot.sc.nextLine();
                                break;
                            }
                        }
                        bot.addDeadline(desc, date);
                    } else if (str.equals("event")) {
                        String desc = bot.sc.next();
                        String from = null;
                        String to = null;
                        while (bot.sc.hasNext()) {
                            String next = bot.sc.next();
                            if (!next.equals("from")) {
                                desc = desc + " " + next;
                            } else {
                                from = bot.sc.next();
                                while (bot.sc.hasNext()) {
                                    String temp = bot.sc.next();
                                    if (!temp.equals("to")) {
                                        from = from + " " + temp;
                                    } else {
                                        to = bot.sc.nextLine();
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        bot.addEvent(desc, from, to);
                    } else {
                        throw new DukeException.WrongInput();
                    }
                }
            }
        } catch (DukeException.EmptyDescription e) {
            System.out.println(e.getMessage());
        } catch (DukeException.WrongInput e) {
            System.out.println(e.getMessage());
        } finally {
            bot.sc.close();
        }
    }
}
