import java.util.Scanner;

/**
 * Class for the ChatBot
 */
public class Duke {
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
            System.out.println("-------------------------------\n"
                    + "added: " + this.description
                    + "\n-------------------------------\n");
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
            System.out.println("-------------------------------\n"
                    + "Got it. I've added this task:\n"
                    + this
                    + totalTasks()
                    + "\n-------------------------------\n");
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
            System.out.println("-------------------------------\n"
                    + "Got it. I've added this task:\n"
                    + this
                    + totalTasks()
                    + "\n-------------------------------\n");
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
            System.out.println("-------------------------------\n"
                    + "Got it. I've added this task:\n"
                    + this
                    + totalTasks()
                    + "\n-------------------------------\n");
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
                    + " (from: " + this.from + " to" + this.to + ")";
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
    public Scanner sc = new Scanner(System.in).useDelimiter("[\\s,\\s/]+");
    public Task[] taskArr = new Task[100];
    public void addTodo(String desc) {
        for (int i = 0; i < 100; i++) {
            if (taskArr[i] == null) {
                taskArr[i] = new Todo(desc);
                break;
            }
        }
    }
    public void addDeadline(String desc, String date) {
        for (int i = 0; i < 100; i++) {
            if (taskArr[i] == null) {
                taskArr[i] = new Deadline(desc, date);
                break;
            }
        }
    }
    public void addEvent(String desc, String from, String to) {
        for (int i = 0; i < 100; i++) {
            if (taskArr[i] == null) {
                taskArr[i] = new Event(desc, from, to);
                break;
            }
        }
    }
    public void listOut() {
        System.out.println("-------------------------------"
        + "\nHere are the tasks in your list:");
        for (int i = 0; i < 100; i++) {
            if (this.taskArr[i] != null) {
                Task curr = this.taskArr[i];
                System.out.println(curr.toString());
            }
        }
        System.out.println("-------------------------------\n");
    }
    public String totalTasks() {
        int count = 0;
        int i = 0;
        while (this.taskArr[i] != null) {
            i++;
        }
        count = i + 1;
        return "\nNow you have " + count + " tasks in the list.";
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
                    bot.sc.close();
                    break;
                } else if (str.equals("list")) {
                    bot.listOut();
                } else if (str.equals("mark")) {
                    int num = bot.sc.nextInt();
                    bot.taskArr[num - 1].mark();
                } else if (str.equals("unmark")) {
                    int num = bot.sc.nextInt();
                    bot.taskArr[num - 1].unmark();
                } else {
                    // check for task type first
                    if (str.equals("todo")) {
                        String desc = bot.sc.nextLine();
                        if (desc.equals("")) {
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
