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
         * Whether the task is completed or not.
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
         * Task's completion status.
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
         * Signifies the specific type of task.
         */
        protected String type = "T";

        /**
         * Constructor for the Todo task type.
         * @param description String describing what the Todo task is about
         */
        public Todo(String description) {
            super(description);
        }

        /**
         * Mark this Todo task as done.
         */
        @Override
        public void mark() {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:\n"
                    + this);
        }

        /**
         * Unmark this Todo task as not done yet.
         */
        @Override
        public void unmark() {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:\n"
                    + this);
        }

        /**
         * Description of this Todo task.
         * @return String containing all relevant information about this Todo task
         */
        @Override
        public String toString() {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "]" + this.description;
        }
    }

    /**
     * Special kind of task that has a description and a deadline
     */
    public class Deadline extends Task {
        /**
         * Signifies the specific type of task.
         */
        protected String type = "D";
        /**
         * The date where the task should be completed by.
         */
        protected String date;

        /**
         * Constructor for the Deadline class.
         * @param description Describes the task
         * @param date When the task should be completed by
         */
        public Deadline(String description, String date) {
            super(description);
            this.date = date;
        }

        /**
         * Mark this Deadline task as done.
         */
        @Override
        public void mark() {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:\n"
            + this);
        }

        /**
         * Unmark this Deadline task as not done yet.
         */
        @Override
        public void unmark() {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:\n"
            + this);
        }

        /**
         * Description of this Deadline task.
         * @return String containing the all the information of this Deadline task
         */
        @Override
        public String toString() {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description
                    + " (by:" + this.date + ")";
        }
    }

    /**
     * Special type of task that has a description, 'from' date and 'to' date.
     */
    public class Event extends Task {
        /**
         * Signifies the 'Event' task.
         */
        protected String type = "E";
        /**
         * Starting date of this task.
         */
        protected String from;
        /**
         * Ending date of this task.
         */
        protected String to;

        /**
         * Constructor for Event class.
         * @param description Describes the event
         * @param from Start of the event
         * @param to End of the event
         */
        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        /**
         * Mark this Event task as done.
         */
        @Override
        public void mark() {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:\n"
                    + this);
        }

        /**
         * Unmark this Event task as not done yet.
         */
        @Override
        public void unmark() {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:\n"
                    + this);
        }

        /**
         * Description of this Event task.
         * @return String containing all the relevant information of this Event task
         */
        @Override
        public String toString() {
            return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description
                    + " (from: " + this.from + " to:" + this.to + ")";
        }
    }

    /**
     * Special exceptions that could be encountered by the chatbot.
     */
    public static class DukeException extends Exception {
        /**
         * Constructor for the DukeException class.
         * @param errorMessage Message about the error.
         */
        public DukeException(String errorMessage) {
            super(errorMessage);
        }

        /**
         * Exception when no description is found.
         */
        public static class EmptyDescription extends DukeException {
            /**
             * Constructor for the EmptyDescription class.
             */
            public EmptyDescription() {
                super("-------------------------------\n"
                        + "☹ OOPS!!! The description of a todo cannot be empty.\n"
                        + "-------------------------------\n");
            }
        }

        /**
         * Exception when an illegal argument is found.
         */
        public static class WrongInput extends DukeException {
            /**
             * Constructor for the WrongInput class.
             */
            public WrongInput() {
                super("-------------------------------\n"
                      +  "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                      +  "-------------------------------\n");
            }
        }
    }

    /**
     * Creates a Todo task in taskArr.
     * @param desc Description of the Todo task
     */
    public void addTodo(String desc) {
        Todo curr = new Todo(desc);
        taskArr.add(curr);
        System.out.println("-------------------------------\n"
                + "Got it. I've added this task:\n"
                + curr.toString()
                + totalTasks()
                + "\n-------------------------------\n");
    }

    /**
     * Creates a Deadline task in taskArr.
     * @param desc Description of the Deadline task
     * @param date Date to complete the Deadline task by
     */
    public void addDeadline(String desc, String date) {
        Deadline curr = new Deadline(desc, date);
        taskArr.add(curr);
        System.out.println("-------------------------------\n"
                + "Got it. I've added this task:\n"
                + curr.toString()
                + totalTasks()
                + "\n-------------------------------\n");
    }

    /**
     * Creates an Event task in taskArr.
     * @param desc Description of the Event task
     * @param from Date when the Event task starts
     * @param to Date when the Event tasks end
     */
    public void addEvent(String desc, String from, String to) {
        Event curr = new Event(desc, from, to);
        taskArr.add(curr);
        System.out.println("-------------------------------\n"
                + "Got it. I've added this task:\n"
                + curr.toString()
                + totalTasks()
                + "\n-------------------------------\n");
    }

    /**
     * Remove a task from taskArr.
     * @param num Indicates the task number to be deleted
     */
    public void delete(int num) {
        Task toRemove = taskArr.get(num);
        taskArr.remove(num);
        System.out.println("-------------------------------\n"
        + "Noted, I've removed this task:\n"
        + toRemove.toString()
        + totalTasks()
        + "\n-------------------------------\n");
    }

    /**
     * Lists out all the tasks in taskArr.
     */
    public void listOut() {
        int size = taskArr.size();
        System.out.println("-------------------------------\n"
        + "Here are the tasks in your list:");
        for (int i = 0; i < size; i++) {
            System.out.println(i + 1 + "." + taskArr.get(i).toString());
        }
        System.out.println("-------------------------------\n");
    }

    /**
     * Number of tasks in taskArr currently.
     * @return String containing the number of tasks added to taskArr
     */
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

        while (bot.sc.hasNext()) {
            try {
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
                } else {
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
            } catch (DukeException.EmptyDescription e) {
                    System.out.println(e.getMessage());
            } catch (DukeException.WrongInput e) {
                    System.out.println(e.getMessage());
            }
        }
    }
}
