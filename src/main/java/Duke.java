import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> lst = new ArrayList<>();
        System.out.println(Constants.opening);
        while (true) {
            String input = sc.nextLine();
            String[] arr = input.split(" ", 2);
            try {
                switch (arr[0]) {
                    case "bye":
                        System.out.println(Constants.closing);
                        return;
                    case "mark":
                        int idx1 = Integer.parseInt(arr[1]);
                        lst.get(idx1 - 1).markDone();
                        System.out.println("Nice! I've marked this task as done");
                        System.out.println("\t" + lst.get(idx1 - 1));
                        break;
                    case "unmark":
                        int idx2 = Integer.parseInt(arr[1]);
                        lst.get(idx2 - 1).unMarkDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("\t" + lst.get(idx2 - 1));
                        break;
                    case "list":
                        System.out.println(Constants.divider + "\n" + "Here are the tasks in your list:");
                        for (int i = 0; i < lst.size(); i++) {
                            System.out.println((i + 1) + "." + lst.get(i));
                        }
                        System.out.println(Constants.divider + "\n");
                        break;
                    case "delete":
                        int idx3 = Integer.parseInt(arr[1]);
                        Task temp = lst.get(idx3 -1);
                        lst.remove(idx3 - 1);
                        String output1 = Constants.divider + "\nNoted. I've removed this task:\n" + "    " + temp
                                + "\nNow you have " + Integer.toString(lst.size())
                                + " tasks in the list.\n" + Constants.divider + "\n";
                        System.out.println(output1);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        Task task;
                        if (arr[0].equals("todo")) {
                            if (arr.length == 1) throw new IncompleteDescriptionException();
                            task = ToDo.create(arr[1]);
                            lst.add(task);
                        } else if (arr[0].equals("deadline")) {
                            if (arr.length == 1) throw new IncompleteDescriptionException();
                            String[] arr2 = arr[1].split(" /by ");
                            if (arr2.length == 1) throw new IncompleteDescriptionException();
                            task = Deadline.create(arr2[0], arr2[1]);
                            lst.add(task);
                        } else {
                            if (arr.length == 1) throw new IncompleteDescriptionException();
                            String[] arr3 = arr[1].split(" /from ");
                            if (arr3.length == 1) throw new IncompleteDescriptionException();
                            String taskName = arr3[0];
                            String[] arr4 = arr3[1].split(" /to ");
                            if (arr4.length == 1) throw new IncompleteDescriptionException();
                            String from = arr4[0];
                            String to = arr4[1];
                            task = Event.create(taskName, from, to);
                            lst.add(task);
                        }
                        String output2 = Constants.divider + "\n"
                                + "Got it. I've added this task:\n"
                                + "    " + task.toString()
                                + "\nNow you have " + Integer.toString(lst.size())
                                + " tasks in the list.\n"
                                + Constants.divider + "\n";
                        System.out.println(output2);
                        break;
                    default:
                        throw new UnknownCommandException();
                }
            } catch (IncompleteDescriptionException ex) {
                System.out.println(ex);
            } catch (UnknownCommandException ex) {
                System.out.println(ex);
            }
        }
    }
}
