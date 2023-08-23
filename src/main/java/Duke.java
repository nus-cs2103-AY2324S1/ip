import java.util.Scanner;

public class Duke {

    public static void taskCounter(int counter) {
        System.out.println("Now you have " + (counter + 1) + " tasks in the list.");
        System.out.println("___________________________________\n");
    }

    public static void main(String[] args) {

        String name = "Duke!!!!!";
        String line = "___________________________________\n";
        String exit = line + "Bye Bye. Hope to see you again soon!\n" + line;

        System.out.println(line + "Hello! I'm " + name + "\nWhat can I do for you?\n" + line);

        Scanner scan = new Scanner(System.in);
        int counter = 0;
        Task[] list = new Task[100];

        while (true) {

            String input = scan.nextLine();
            String[] arr = input.split(" ", 2);
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
                String[] deadline = arr[1].split("/by", 2);
                Task newTask = new Deadline(deadline[0], deadline[1]);
                list[counter] = newTask;
                System.out.println(line);
                System.out.println("Got it. I've added the Deadline:\n\t" + newTask.toString());
                taskCounter(counter);
                counter++;

            } else if (type.equals("event")) {
                String[] event = arr[1].split("/from |/to ");
                Task newTask = new Event(event[0], event[1], event[2]);
                list[counter] = newTask;
                System.out.println(line);
                System.out.println("Got it. I've added the Event:\n\t" + newTask.toString());
                taskCounter(counter);
                counter++;

            } else if (type.equals("todo")) {
                Task newTask = new ToDo(arr[1]);
                list[counter] = newTask;
                System.out.println(line);
                System.out.println("Okay! I added a new TODO:\n\t" + newTask.toString());
                taskCounter(counter);
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
        }
    }
}
