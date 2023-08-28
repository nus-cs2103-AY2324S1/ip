import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        TaskList store = new TaskList();
        String name = "CodeBuddy";
        String horizontal = "----------------------------------------";
        Scanner inputScanner = new Scanner(System.in);
        System.out.println(horizontal);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(horizontal);

        while(true) {
            String input = inputScanner.nextLine();
            System.out.println(horizontal);
            try {
                if(input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon !");
                    System.out.println(horizontal);
                    break;
                } else if(input.equals("list")) {
                    store.listTasks();
                } else if(input.startsWith("mark")) {
                    String removedPrefix = input.substring("mark".length()).trim();
                    int index = Integer.parseInt(removedPrefix);
                    store.markTask(index);
                } else if(input.startsWith("unmark")) {
                    String removedPrefix = input.substring("unmark".length()).trim();
                    int index = Integer.parseInt(removedPrefix);
                    store.unmarkTask(index);
                } else if(input.startsWith("todo")) {
                    String removedPrefix = input.substring("todo".length()).trim();
                    store.addTodo(removedPrefix);
                } else if(input.startsWith("deadline")) {
                    String removedPrefix = input.substring("deadline".length()).trim();
                    store.addDeadline(removedPrefix);
                } else if (input.startsWith("event")) {
                    String removedPrefix = input.substring("event".length()).trim();
                    store.addEvent(removedPrefix);
                } else if(input.startsWith("delete")) {
                    String removedPrefix = input.substring("delete".length()).trim();
                    int index = Integer.parseInt(removedPrefix);
                    store.deleteTask(index);
                } else {
                    throw new DukeException();
            }
                System.out.println(horizontal);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println(horizontal);
            } catch (EmptyTaskException e) {
                System.out.println(e.getMessage());
                System.out.println(horizontal);
            } catch (InvalidIndexException e) {
                System.out.println(e.getMessage());
                System.out.println(horizontal);
            }

        }
    }
}
