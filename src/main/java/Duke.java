import java.util.Scanner;

public class Duke {
    public class Task {
        protected String type;
        protected String description;
        protected boolean isDone;

        public Task(String type, String description) {
            this.type = type;
            this.description = description;
            this.isDone = false;
            System.out.println("-------------------------------\n"
                    + "added: " + this.description
                    + "\n-------------------------------\n");
        }
        public String getTypeIcon() {
            return this.type;
        }
        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        public void mark() {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:\n"
            + "[" + this.getTypeIcon() + "] " + "[X] " + this.desc());
        }

        public void unmark() {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:\n"
            + "[" + this.getTypeIcon() + "] " + "[ ] " + this.desc());
        }
        public String desc() {
            return this.description;
        }
    }
    public class Todo extends Task {
        public Todo(String type, String description) {
            super(type, description);
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
        protected String date;
        public Deadline(String type, String description, String date) {
            super(type, description);
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
        protected String from;
        protected String to;
        public Event(String type, String description, String from, String to) {
            super(type, description);
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
    public Scanner sc = new Scanner(System.in).useDelimiter("[\\s,\\s/]+");
    public Task[] taskArr = new Task[100];
    public void addTodo(String type, String desc) {
        for (int i = 0; i < 100; i++) {
            if (taskArr[i] == null) {
                taskArr[i] = new Todo(type, desc);
                break;
            }
        }
    }
    public void addDeadline(String type, String desc, String date) {
        for (int i = 0; i < 100; i++) {
            if (taskArr[i] == null) {
                taskArr[i] = new Deadline(type, desc, date);
                break;
            }
        }
    }
    public void addEvent(String type, String desc, String from, String to) {
        for (int i = 0; i < 100; i++) {
            if (taskArr[i] == null) {
                taskArr[i] = new Event(type, desc, from, to);
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
                    bot.addTodo("T", desc);
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
                    bot.addDeadline("D", desc, date);
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
                    bot.addEvent("E", desc, from, to);
                } else {
                    // do nothing for now
                    System.out.println("Wrongful input!");
                    break;
                }
            }
        }
    }
}
