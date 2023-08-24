import java.util.Scanner;


public class Duke {

    protected static String[] handleInput(String input) throws DukeException {
        String[] arr = input.split(" ", 2);
        return arr.length == 1 ? new String[]{arr[0], ""} : arr;
    }

    public static void main(String[] args) {

        String name = "Duke but btr!";
        String line = "___________________________________\n";
        String exit = line + "Bye Bye. Hope to see you again soon!\n" + line;

        System.out.println(line + "Hello! I'm " + name + "\nWhat can I do for you?\n" + line);

        Scanner scan = new Scanner(System.in);
        int counter = 0;
        Task[] list = new Task[100];

        while (true) {

            try {
                String input = scan.nextLine();
                String[] arr = handleInput(input);
                String type = arr[0];

                if ("bye".equals(input)) {
                    System.out.println(exit);
                    break;

                } else if (type.equals("mark")) {
                    int amt = Integer.parseInt(arr[1].strip()) - 1;
                    System.out.println(line);
                    list[amt].mark();
                    System.out.println(line);

                } else if (type.equals("unmark")) {
                    int amt = Integer.parseInt(arr[1].strip()) - 1;
                    System.out.println(line);
                    list[amt].unMark();
                    System.out.println(line);

                } else if (type.equals("deadline")) {
                    Deadline.addDeadline(arr[1], list, counter);
                    counter++;

                } else if (type.equals("event")) {
                    Event.addEvent(arr[1], list, counter);
                    counter++;

                } else if (type.equals("todo")) {
                    ToDo.addTodo(arr[1], list, counter);
                    counter++;

                } else if ("list".equals(input.strip())) {
                    System.out.println(line + "Here are the tasks in your list:");
                    for (int i = 0; i < counter; i++) {
                        System.out.println(i + 1 + ". " + list[i].toString());
                    }
                    System.out.println(line);

                } else {
                    System.out.println(line);
                    System.out.println("That's not a valid action! Try again");
                    System.out.println(line);
                }

            } catch (DukeException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            }

        }
    }
}
