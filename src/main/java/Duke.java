import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String line = "---------------------------------------\n";
        String intro = "Hey there! I'm BUTTER.\n" +
                "How can I help you today?\n";
        String bye = "Signing off, see you later!\n";

        System.out.println(line + intro + line); //greeting
        String command = "";
        ArrayList<Task> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in); //create Scanner object

        while (true) {
            command = scanner.next();

            if (command.equals("bye")) {
                break;

            } else if (command.equals("list")) {
                String result = "";
                for (int i = 0; i < list.size(); i++) {
                    int index = i + 1;
                    Task task = list.get(i);
                    result += index + ". " + task.toString() + "\n";
                }
                System.out.println("Here are your tasks for the day: ");
                System.out.println(result + line);

            } else if (command.equals("mark")) {
                int taskID = scanner.nextInt() - 1; //because input index starts from 1
                list.get(taskID).mark();
                System.out.println(line);

            } else if (command.equals("unmark")) {
                int taskID = scanner.nextInt() - 1;
                list.get(taskID).unmark();
                System.out.println(line);

            } else if (command.equals("todo")) {
                String rest = scanner.nextLine().substring(1); //get rid of space
                ToDo toDo = new ToDo(0, rest);
                list.add(toDo);
                System.out.println("New task added: \n" + toDo);
                System.out.println("Now you have " + list.size() + " tasks in the list!\n" + line);

            } else if (command.equals("deadline")) {
                String rest = scanner.nextLine().substring(1);
                String[] arr = rest.split("/by ", 2);
                String task = arr[0];
                String date = arr[1];
                Deadline deadline = new Deadline(0, task, date);
                list.add(deadline);
                System.out.println("New task added: \n" + deadline);
                System.out.println("Now you have " + list.size() + " tasks in the list!\n" + line);

            } else if (command.equals("event")) {
                String rest = scanner.nextLine().substring(1);
                String[] arr = rest.split("/from ", 2);
                String task = arr[0];
                String start = arr[1].split(" /to ", 2)[0];
                String end = rest.split("/to ", 2)[1];
                Event event = new Event(0, task, start, end);
                list.add(event);
                System.out.println("New task added: \n" + event);
                System.out.println("Now you have " + list.size() + " tasks in the list!\n" + line);

            } else {
                System.out.println("Sorry, I don't understand what you mean ><");
            }
        }
        System.out.println(line + bye + line);
        scanner.close();
    }
}
