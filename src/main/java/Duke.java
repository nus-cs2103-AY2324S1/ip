import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class for the ChatBot
 */
public class Duke {
    public Scanner sc = new Scanner(System.in).useDelimiter("[\\s,\\s/]+");
    public static ArrayList<Task> taskArr = new ArrayList<>();
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
                        throw new WrongInput();
                    } else {
                        int num = bot.sc.nextInt();
                        bot.taskArr.get(num - 1).mark();
                    }
                } else if (str.equals("unmark")) {
                    if (!bot.sc.hasNextInt()) {
                        throw new WrongInput();
                    } else {
                        int num = bot.sc.nextInt();
                        bot.taskArr.get(num - 1).unmark();
                    }
                } else if (str.equals("delete")) {
                    if (!bot.sc.hasNextInt()) {
                        throw new WrongInput();
                    } else {
                        int num = bot.sc.nextInt();
                        if (num > taskArr.size()) {
                            throw new WrongInput();
                        } else {
                            bot.delete(num - 1);
                        }
                    }
                } else {
                    // check for task type first
                    if (str.equals("todo")) {
                        String desc = bot.sc.nextLine();
                        if (desc.equals("") || desc.equals(" ")) {
                            throw new EmptyDescription();
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
                        throw new WrongInput();
                    }
                }
            } catch (EmptyDescription e) {
                    System.out.println(e.getMessage());
            } catch (WrongInput e) {
                    System.out.println(e.getMessage());
            }
        }
    }
}
