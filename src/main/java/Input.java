import java.util.Scanner;

public class Input {

    private static Scanner scanner = new Scanner(System.in);

    public static void echo() {
        while (Input.scanner.hasNext()) {
            try {
                String input = Input.scanner.nextLine();

                if (input.equals("bye")) {
                    Printing.bye();
                    return;
                } else if (input.equals("list")) {
                    if (Storage.getLength() == 0) {
                        Printing.printNoTasks();
                    } else {
                        Printing.list();
                    }
                } else if (input.startsWith("mark")) {
                    // Throw error if there is no index
                    if (input.length() < 6 || input.split(" ", 2).length < 2) {
                        throw new InvalidIndexException("☹ OOPS!!! You need to include an index to mark task.");
                    }
                    String strIndex = input.split(" ", 2)[1];
                    // Throw error if index given is not an int
                    if (!Input.isInt(strIndex)) {
                        throw new InvalidIndexException("☹ OOPS!!! You need to include an index to mark task.");
                    }
                    int index = Integer.parseInt(strIndex) - 1;
                    // Throw error if there is no task at index
                    if (index >= Storage.getLength()) {
                        throw new InvalidIndexException("☹ OOPS!!! There is no task at that index.");
                    }
                    Storage.markAsDone(index);
                    Printing.printMarkAsDone(index);
                } else if (input.startsWith("unmark")) {
                    // Throw error if there is no index
                    if (input.length() < 8 || input.split(" ", 2).length < 2) {
                        throw new InvalidIndexException("☹ OOPS!!! You need to include an index to unmark task.");
                    }
                    String strIndex = input.split(" ", 2)[1];
                    // Throw error if index given is not an int
                    if (!Input.isInt(strIndex)) {
                        throw new InvalidIndexException("☹ OOPS!!! You need to include an index to unmark task.");
                    }
                    int index = Integer.parseInt(strIndex) - 1;
                    // Throw error if there is no task at index
                    if (index >= Storage.getLength()) {
                        throw new InvalidIndexException("☹ OOPS!!! There is no task at that index.");
                    }
                    Storage.unmark(index);
                    Printing.printUnmark(index);
                } else if (input.startsWith("todo")) {
                    // Throw error if there is no description
                    if (input.length() < 6) {
                        throw new InvalidParametersException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    String[] arr = input.split(" ", 2);
                    Task newTask = new Todo(arr[1]);
                    Storage.addToStorage(newTask);
                    Printing.printAdded(newTask);
                } else if (input.startsWith("deadline")) {
                    // Throw error if description or by is missing
                    if (input.length() < 10 || !input.contains(" /by ") ||
                            input.split(" /by ").length < 2 ||
                            input.split(" /by ")[0].length() < 10 ||
                            input.split(" /by ")[0].substring(9).trim().equals("") ||
                            input.split(" /by ")[1].trim().equals("")) {
                        throw new InvalidParametersException("☹ OOPS!!! The description and by of a deadline cannot be empty.");
                    }
                    String[] arr = input.split(" /by ");
                    String taskDesc = arr[0].substring(9);
                    String by = arr[1];
                    Task newTask = new Deadline(taskDesc, by);
                    Storage.addToStorage(newTask);
                    Printing.printAdded(newTask);
                } else if (input.startsWith("event")) {
                    // Throw error if description or from or to is missing
                    if (input.length() < 10 || !input.contains(" /from ") ||
                            !input.contains(" /to ") ||
                            input.split(" /from ").length < 2 ||
                            input.split(" /from ")[0].length() < 7 ||
                            input.split(" /from ")[0].substring(6).trim().equals("") ||
                            input.split(" /from ")[1].split(" /to ").length < 2 ||
                            input.split(" /from ")[1].split(" /to ")[0].trim().equals("") ||
                            input.split(" /from ")[1].split(" /to ")[1].trim().equals("")) {
                        throw new InvalidParametersException("☹ OOPS!!! The description and from and to of an event cannot be empty.");
                    }
                    String[] arr = input.split(" /from ");
                    String taskDesc = arr[0].substring(6);
                    String[] arrBack = arr[1].split(" /to ");
                    String from = arrBack[0];
                    String to = arrBack[1];
                    Task newTask = new Event(taskDesc, from, to);
                    Storage.addToStorage(newTask);
                    Printing.printAdded(newTask);
                } else if (input.startsWith("delete")) {
                    // Throw error if there is no index
                    if (input.length() < 8 || input.split(" ", 2).length < 2) {
                        throw new InvalidIndexException("☹ OOPS!!! You need to include an index to delete task.");
                    }
                    String strIndex = input.split(" ", 2)[1];
                    // Throw error if index given is not an int
                    if (!Input.isInt(strIndex)) {
                        throw new InvalidIndexException("☹ OOPS!!! You need to include an index to delete task.");
                    }
                    int index = Integer.parseInt(strIndex) - 1;
                    // Throw error if there is no task at index
                    if (index >= Storage.getLength()) {
                        throw new InvalidIndexException("☹ OOPS!!! There is no task at that index.");
                    }
                    Task removedTask = Storage.delete(index);
                    Printing.printDelete(removedTask);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :(");
                }
            } catch (DukeException e) {
                Printing.printError(e);
            }
        }
    }

    public static boolean isInt(String strInt) {
        if (strInt == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strInt);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
