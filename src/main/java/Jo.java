import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jo {

    protected static List<Task> taskList = new ArrayList<>();
    protected enum TASK {
        todo {
            public void perform(String input) {
                addTask(new Task(input));
            }
        },
        deadline {
            public void perform(String input) throws JoException {
                if (!input.contains("/by")) {
                    throw new JoException("OOPS!!! Please specify a deadline.");
                } else {
                    String[] description = input.split("/by", 2);
                    String deadline = description[1].trim();
                    String taskName = description[0].trim();
                    addTask(new Deadline(taskName, deadline));
                }
            }
        },
        event {
            public void perform(String input) throws JoException {
                if (!input.contains("/from") || !input.contains("/to")) {
                    throw new JoException("OOPS!!! Please specify a start AND end date.");
                } else {
                    String[] description = input.split("/from", 2);
                    String[] dates = description[1].split("/to", 2);
                    String start = dates[0].trim();
                    String end = dates[1].trim();
                    String taskName = description[0].trim();
                    addTask(new Event(taskName, start, end));
                }
            }
        };

        public abstract void perform(String s) throws JoException;
    }

    protected enum COMMAND {
        mark {
            @Override
            public void perform(int taskIndex) {
                markDone(taskList.get(taskIndex));
            }
        },
        unmark {
            @Override
            public void perform(int taskIndex) {
                markNotDone(taskList.get(taskIndex));
            }
        },
        delete {
            @Override
            public void perform(int taskIndex) {
                deleteTask(taskIndex);
            }
        };

        public abstract void perform(int taskIndex);

    }


    public static void markDone(Task task) {
        task.mark();
        System.out.println("> Nice! I've marked this task as done:");
        System.out.println("\t" + task);
    }

    public static void markNotDone(Task task) {
        task.unmark();
        System.out.println("> OK, I've marked this task as not done yet:");
        System.out.println("\t" + task);
    }

    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println("> Got it. I've added this task:");
        System.out.println("\t" + task.toString());
        System.out.println(String.format("> Now you have %d tasks in the list.", taskList.size()));
    }

    public static void deleteTask(int index) {
        Task removedTask = taskList.get(index);
        taskList.remove(index);
        System.out.println("> Noted. I've removed this task:");
        System.out.println("\t" + removedTask.toString());
        System.out.println(String.format("> Now you have %d tasks in the list.", taskList.size()));
    }

    public static <E extends Enum<E>> boolean isInEnum(String input, Class<E> enumClass) {
        for (E enumValue : enumClass.getEnumConstants()) {
            if (enumValue.name().equals(input)) {
                return true;
            }
        }
        return false;
    }

    public static void processInput(String input) throws JoException {
        if (input.trim().isEmpty()) {
            throw new JoException("OOPS!!! The command cannot be empty.");
        } else if (input.equals("list")) {
            System.out.println("> Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                System.out.println("\t" + (i+1) + ". " + t.toString());
            }
        } else if (isInEnum(input, TASK.class)) {
            throw new JoException(String.format("OOPS!!! The description of a %s cannot be empty.", input));
        } else if (isInEnum(input, COMMAND.class)) {
            throw new JoException(String.format("OOPS!!! Please specify a valid task number to %s.", input));
        } else {
            String instruction = input.split(" ", 2)[0].trim();
            if (isInEnum(instruction, TASK.class)) {
                for (TASK t : TASK.values()) {
                    if (t.name().equals(instruction)) {
                        t.perform(input.split(" ", 2)[1].trim());
                    }
                }
            } else if (isInEnum(instruction, COMMAND.class)) {
                for (COMMAND c : COMMAND.values()) {
                    if (c.name().equals(instruction)) {
                        int taskIndex = Character.getNumericValue(input.charAt(input.length() - 1)) - 1;
                        if (taskIndex < 0 || taskIndex >= taskList.size()) {
                            throw new JoException("OOPS!!! This task does not exist.");
                        } else {
                            c.perform(taskIndex);
                        }
                    }
                }
            } else {
                throw new JoException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("> Hello! I'm Jo.\n> What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            try {
                processInput(input);
            } catch (JoException e) {
                System.out.println("> ☹ " + e.getMessage());
            } finally {
                input = scanner.nextLine();
            }
        }

        System.out.println("> Bye. Hope to see you again soon!");
        scanner.close();

    }
}
