import java.util.Scanner;

public class Iris {
    private static ToDoList toDoList = new ToDoList();
    private static void greet() {
        System.out.println("Hello! I'm Iris!");
        System.out.println("What can I do for you?");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void length() {
        int listSize = toDoList.size();
        System.out.println("Now you have " + listSize + " tasks in the list.");
    }

    private static void add(Task task) {
        toDoList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        Iris.length();
    }


    private static void list() {
        System.out.println(toDoList);
    }

    private static void mark(int index) {
        toDoList.mark(index);
        System.out.println("Nice! I've marked this task as done: \n" +
                toDoList.get(index));
    }

    private static void unmark(int index) {
        toDoList.unmark(index);
        System.out.println("OK, I've marked this task as not done yet: \n" +
                toDoList.get(index));
    }

    private static void delete(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(toDoList.get(index).toString());
        toDoList.remove(index);
        Iris.length();
    }

    private static boolean parseCommand(String input) {
        String[] sections = input.split(" ", 2);
        String command = sections[0];
        String rest = sections.length > 1 ? sections[1] : "";
        switch (command) {
            case "bye": {
                Iris.bye();
                return false;
            }

            case "list": {
                Iris.list();
                break;
            }

            case "todo": {
                if (rest.isEmpty()) {
                    throw new IllegalArgumentException("Description is missing.");
                }
                Iris.add(new Todo(rest));
                break;
            }

            case "deadline": {
                if (rest.isEmpty()) {
                    throw new IllegalArgumentException("Description is missing.");
                }
                try {
                    String[] deadlineSections = rest.split(" /by ", 2);
                    String name = deadlineSections[0];
                    String endTime = deadlineSections[1];
                    Iris.add(new Deadline(name, endTime));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Invalid format.");
                }
                break;
            }

            case "event": {
                if (rest.isEmpty()) {
                    throw new IllegalArgumentException("Description is missing.");
                }
                try {
                    String[] eventSections = rest.split(" /from ", 2);
                    String name = eventSections[0];
                    String startAndEnd = eventSections[1];

                    String[] startAndEndSections = startAndEnd.split(" /to ", 2);
                    String startTime = startAndEndSections[0];
                    String endTime = startAndEndSections[1];
                    Iris.add(new Event(name, startTime, endTime));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Invalid format.");
                }
                break;
            }

            case "mark": {
                if (rest.isEmpty()) {
                    throw new IllegalArgumentException("Index is missing.");
                }
                int index = Integer.parseInt(rest);
                Iris.mark(index);
                break;
            }

            case "unmark": {
                if (rest.isEmpty()) {
                    throw new IllegalArgumentException("Index is missing.");
                }
                int index = Integer.parseInt(rest);
                Iris.unmark(index);
                break;
            }

            case "delete": {
                if (rest.isEmpty()) {
                    throw new IllegalArgumentException("Index is missing.");
                }
                int index = Integer.parseInt(rest);
                Iris.delete(index);
                break;
            }

            default: {
                throw new IllegalArgumentException("Unknown command.");
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Iris.greet();
        Scanner scanner = new Scanner(System.in);

        boolean parse = true;
        while (parse) {
            String input = scanner.nextLine();
            try {
                parse = Iris.parseCommand(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Argument Error: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Out of Bounds Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
