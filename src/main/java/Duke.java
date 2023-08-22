import java.util.Scanner;  // Import the Scanner class
public class Duke {
    int TODO = 0, DEADLINE = 1, EVENT = 2;
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void setDone() {
            this.isDone = true;
        }

        public void setNotDone() {
            this.isDone = false;
        }
        public String toString() {
            return "[" + getStatusIcon() + "] " + this.description;
        }
    }
    public static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }
    public static class Deadline extends Task {

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

    public static class Event extends Task {
        protected String from;
        protected String to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
        }
    }

    public static void main(String[] args) {
        Task[] list = new Task[100]; // List to be returned when input is "list"
        int counter = 0; // Items in list
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Good day to you, I'm ButlerBot.\n" +
                "How may I be of service to you?\n"); // Greets user

        String echo = myObj.nextLine(); // Reads user input

        while (!echo.equals("bye")) {
            if (echo.startsWith("mark ")) { // Mark task complete
                int index = Integer.valueOf(echo.substring(5)) - 1; // Index of task
                list[index].setDone(); // Item mark complete
                System.out.println("Congratulations on finishing the task. I will now mark it as complete:\n" +
                        list[index].toString());
            }

            else if (echo.startsWith("unmark ")) { // Mark task incomplete
                int index = Integer.valueOf(echo.substring(7)) - 1; // Index of task
                list[index].setNotDone(); // Item mark complete
                System.out.println("No worries. I will now mark it as incomplete:\n" +
                        list[index].toString());
            }

            else if (echo.equals("list")) { // Returns the list of tasks
                for (int i = 0; i < list.length; i++) {
                    if (list[i] != null) {
                        System.out.println(i + 1 + ". " + list[i]); // Returns the list
                    }
                }
            }

            else if (echo.startsWith("todo ")) { // Task of Todo type
                // Insert Todo into list
                String tasking = echo.substring(5);
                list[counter] = new Todo(tasking);
                String task = list[counter].toString();
                counter += 1;

                String response = "Understood, I will add the following task to your list:\n" + task;
                String listLength = "Please note that there are " + counter + " tasks in the list.";
                System.out.println(response + "\n" + listLength);
            }

            else if (echo.startsWith("deadline ")) { // Task of Deadline type
                // Insert Deadline into list
                String tasking = echo.substring(9);
                String[] taskTime = tasking.split(" /by ");
                list[counter] = new Deadline(taskTime[0], taskTime[1]);
                String task = list[counter].toString();
                counter += 1;

                String response = "Understood, I will add the following task to your list:\n" + task;
                String listLength = "Please note that there are " + counter + " tasks in the list.";
                System.out.println(response + "\n" + listLength);
            }

            else if (echo.startsWith("event ")) { // Task of Event type
                // Insert Event into list
                String tasking = echo.substring(6);
                String[] taskTime = tasking.split(" /from ");
                String event = taskTime[0];
                String[] startEnd = taskTime[1].split(" /to ");
                String start = startEnd[0];
                String end = startEnd[1];

                list[counter] = new Event(event, start, end);
                String task = list[counter].toString();
                counter += 1;

                String response = "Understood, I will add the following task to your list:\n" + task;
                String listLength = "Please note that there are " + counter + " tasks in the list.";
                System.out.println(response + "\n" + listLength);
            }

            else {
                // Echos the input
                String response = "Understood, I will add \"" + echo + "\" to your to do list.";
                System.out.println(response);

                // Insert action into list
                Task add = new Task(echo);
                list[counter] = add;
                counter += 1;
            }
            echo = myObj.nextLine();
        }
        System.out.println("Goodbye and have a nice day.\n"); // Exits the bot
    }
}
