import java.util.Scanner;
import java.util.ArrayList;
public class CR7 {
    // Function to print a horizontal line
    public static void printHorizontalLine() {
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println(); // Move to the next line
    }

    public static class CR7Exception extends Throwable {}

    public static class EmptyDescriptionException extends CR7Exception {}

    public static class EmptyDeadlineException extends CR7Exception {}

    public static class EmptyTimingException extends CR7Exception {}

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

        public void markAsDone() {
            isDone = true;
        }

        public void unmarkAsDone() {
            isDone = false;
        }

        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }

    public static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Event extends Task {

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


    public static void main(String[] args) {
            printHorizontalLine();
            System.out.println("Hello! I'm CR7\n" + "What can I do for you?\n");
            String input = "";
            Scanner myObj = new Scanner(System.in);
            ArrayList<Task> tasks = new ArrayList<>();
            while (!input.equals("bye")) {
                try {
                    input = myObj.nextLine();
                    if (input.equals("bye")) {
                        printHorizontalLine();
                        System.out.println("Bye! Hope to see you again soon! SIUUUU\n");
                        printHorizontalLine();
                        break;
                    }
                    if (input.equals("list")) {
                        printHorizontalLine();
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 1; i < tasks.size() + 1; i++) {
                            Task x = tasks.get(i-1);
                            System.out.println(i + "." + x.toString());
                        }
                        System.out.println();
                        printHorizontalLine();
                    } else {
                        String[] words = input.split(" ");
                        String first = words[0];
                        if (first.equals("delete")) {
                            int s = Integer.valueOf(words[1]);
                            Task k = tasks.remove(s-1);
                            printHorizontalLine();
                            System.out.println("Noted. I've removed this task:");
                            System.out.println("  " + k.toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
                            printHorizontalLine();
                        } else if (first.equals("mark")) {
                            int s = Integer.valueOf(words[1]);
                            Task k = tasks.get(s-1);
                            k.markAsDone();
                            printHorizontalLine();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("  " + k.toString() + "\n");
                            printHorizontalLine();
                        } else if (first.equals("unmark")) {
                            int s = Integer.valueOf(words[1]);
                            Task k = tasks.get(s-1);
                            k.unmarkAsDone();
                            printHorizontalLine();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("  " + k.toString() + "\n");
                            printHorizontalLine();
                        } else if (first.equals("todo")) {
                            if (input.length() <= 5) {
                                throw new EmptyDescriptionException();
                            } else {
                                String desc = input.substring(5);
                                Task t = new ToDo(desc);
                                tasks.add(t);
                                printHorizontalLine();
                                System.out.println("Got it. I've added this task:");
                                System.out.println("  " + t.toString());
                                System.out.println("Now you have " + tasks.size() + " tasks in the list\n");
                                printHorizontalLine();
                            }
                        } else if (first.equals("deadline")) {
                            if (input.length() <= 9) {
                                throw new EmptyDescriptionException();
                            } else {
                                int y = input.indexOf("/by ");
                                if (y == -1) {
                                    throw new EmptyDeadlineException();
                                } else {
                                    String desc = input.substring(9, y - 1);
                                    String by = input.substring(y + 4);
                                    Task t = new Deadline(desc, by);
                                    tasks.add(t);
                                    printHorizontalLine();
                                    System.out.println("Got it. I've added this task:");
                                    System.out.println("  " + t.toString());
                                    System.out.println("Now you have " + tasks.size() + " tasks in the list\n");
                                    printHorizontalLine();
                                }
                            }
                        } else if (first.equals("event")) {
                            if (input.length() <= 6) {
                                throw new EmptyDescriptionException();
                            } else {
                                int fromIndex = input.indexOf("/from ");
                                int toIndex = input.indexOf("/to ");
                                if (fromIndex == -1 || toIndex == -1) {
                                    throw new EmptyTimingException();
                                } else {
                                    String desc = input.substring(6, input.indexOf("/") - 1);
                                    String start = input.substring(fromIndex + 6, toIndex).trim();
                                    String end = input.substring(toIndex + 4).trim();
                                    Task t = new Event(desc, start, end);
                                    tasks.add(t);
                                    printHorizontalLine();
                                    System.out.println("Got it. I've added this task:");
                                    System.out.println("  " + t.toString());
                                    System.out.println("Now you have " + tasks.size() + " tasks in the list\n");
                                    printHorizontalLine();
                                }
                            }
                        } else {
                            printHorizontalLine();
                            System.out.println("OOPS!! I'm sorry, but I don't know what that means :(\n");
                            printHorizontalLine();
                        }
                    }
                } catch (EmptyDescriptionException e) {
                    printHorizontalLine();
                    System.out.println("OOPS!!! The description of a task cannot be empty.\n");
                    printHorizontalLine();
                } catch (EmptyDeadlineException e) {
                    printHorizontalLine();
                    System.out.println("OOPS!!! Please enter the deadline of the task in the correct format.\n");
                    printHorizontalLine();
                } catch (EmptyTimingException e) {
                    printHorizontalLine();
                    System.out.println("OOPS!!! Please enter the start and end time" +
                            " of the task in the correct format.\n");
                    printHorizontalLine();
                }
            }
    }
}
