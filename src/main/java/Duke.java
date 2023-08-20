import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Start user interaction
        Scanner scanner = new Scanner(System.in);
        Todo tasks = new Todo();
        Reply reply = Reply.init();
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                reply.printDialog("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                tasks.printTasks();
            } else {
                tasks.addTask(input);
                reply.printDialog("added: " + input);
            }
        }
    }
}
