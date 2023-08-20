import java.util.Scanner;

public class Duke {
    private static Todo tasks = new Todo();
    private static Reply reply = Reply.init();
    public static void main(String[] args) {
        //Start user interaction
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                reply.printDialog("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                tasks.printTasks();
            } else if (input.startsWith("mark")) {
                markTask(input, true);
            } else if (input.startsWith("unmark")) {
                markTask(input, false);
            } else {
                tasks.addTask(input);
                reply.printDialog("added: " + input);
            }
        }
    }

    public static void markTask(String input, boolean mark) {
        String[] split = input.split(" ");
        if (split.length > 2) {
            reply.printDialog("Invalid input");
        } else {
            try {
                if (mark) {
                    tasks.markDone(Integer.parseInt(split[1]));
                } else {
                    tasks.unmarkDone(Integer.parseInt(split[1]));
                }
            } catch (NumberFormatException e ) {
                reply.printDialog("Invalid number");
            } catch (IndexOutOfBoundsException e) {
                reply.printDialog("Index number does not exist");
            }
        }
    }
}
