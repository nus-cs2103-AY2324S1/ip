import java.util.Scanner;

public class Input {

    private static Scanner scanner = new Scanner(System.in);

    public static void echo() {
        while (Input.scanner.hasNext()) {
            String input = Input.scanner.nextLine();

            if (input.equals("bye")) {
                Printing.bye();
                return;
            } else if (input.equals("list")) {
                Printing.list();
            } else if (input.startsWith("mark")) {
                int index = Character.getNumericValue(input.charAt(5)) - 1;
                Storage.markAsDone(index);
                Printing.printMarkAsDone(index);
            } else if (input.startsWith("unmark")) {
                int index = Character.getNumericValue(input.charAt(7)) - 1;
                Storage.unmark(index);
                Printing.printUnmark(index);
            } else if (input.startsWith("todo")) {
                String[] arr = input.split(" ", 2);
                Task newTask = new Todo(arr[1]);
                Storage.addToStorage(newTask);
                Printing.printAdded(newTask);
            } else if (input.startsWith("deadline")) {
                String[] arr = input.split("/by");
                String taskDesc = arr[0].trim().split(" ", 2)[1];
                String by = arr[1].trim();
                Task newTask = new Deadline(taskDesc, by);
                Storage.addToStorage(newTask);
                Printing.printAdded(newTask);
            } else if (input.startsWith("event")) {
                String[] arr = input.split("/from");
                String taskDesc = arr[0].trim().split(" ", 2)[1];
                String[] arrBack = arr[1].split(" /to ");
                String from = arrBack[0].trim();
                String to = arrBack[1];
                Task newTask = new Event(taskDesc, from, to);
                Storage.addToStorage(newTask);
                Printing.printAdded(newTask);
            } else {
                Task newTask = new Todo(input);
                Storage.addToStorage(newTask);
                Printing.printAdded(newTask);
            }
        }
    }
}
