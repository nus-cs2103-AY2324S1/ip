import java.util.List;
import java.util.Scanner;

public class TaskManagement {
    private List<Task> taskList;

    public TaskManagement(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void operate() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            try {
                if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    add_task(input);
                } else if (input.equalsIgnoreCase("list")) {
                    list_printer();
                } else if (input.startsWith("mark ") && input.length() > 5
                        && input.substring(5).matches("\\d+")) {
                    mark_task(input);
                } else if (input.startsWith("unmark ") && input.length() > 7
                        && input.substring(7).matches("\\d+")) {
                    unmark_task(input);
                } else if (input.equalsIgnoreCase("bye")) {
                    break;
                } else {
                    throw new InvalidCommandException();
                }
            } catch (ContentMissingException | InvalidTaskIndexException
                     | InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    private void list_printer() {
        Jarvis.horizontal_line_printer();
        System.out.println("Sir, here is your list:");
        int index = 0;
        for (Task task : this.taskList) {
            System.out.println((index+1) + ". " + task.toString());
            index++;
        }
        Jarvis.horizontal_line_printer();
    }

    private void mark_task(String input) throws InvalidTaskIndexException {
        int index = Integer.parseInt(input.substring(5));
        try {
            Task target = this.taskList.get(index-1);
            target.mark();
            Jarvis.horizontal_line_printer();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(target.toString());
            Jarvis.horizontal_line_printer();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(index);
        }


    }

    private void unmark_task(String input) throws InvalidTaskIndexException {
        int index = Integer.parseInt(input.substring(7));
        try {
            Task target = this.taskList.get(index-1);
            target.unmark();
            Jarvis.horizontal_line_printer();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(target.toString());
            Jarvis.horizontal_line_printer();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(index);
        }
    }

    private void add_task(String input) throws ContentMissingException, InvalidCommandException {
        String keyword = input.split(" ")[0];
        String content = "";
        Task newTask = null;
        try {
            switch (keyword) {
                case "todo":
                    if (!Character.isWhitespace(input.charAt(4))) {
                        throw new InvalidCommandException();
                    }

                    content = input.substring(4).trim();

                    if (content.equals("")) {
                        throw new ContentMissingException("todo");
                    }
                    newTask = new ToDo(content);
                    this.taskList.add(newTask);
                    break;
                case "deadline":
                    int byIndex = input.indexOf("/by");
                    content = input.substring("deadline".length(), byIndex).trim();
                    String time = input.substring(byIndex + 3).trim();
                    if (content.equals("") || time.equals("")) {
                        throw new ContentMissingException("deadline");
                    }
                    newTask = new Deadline(content, time);
                    this.taskList.add(newTask);
                    break;
                case "event":
                    int fromIndex = input.indexOf("/from");
                    int toIndex = input.indexOf("/to");
                    content = input.substring("event".length(), fromIndex).trim();
                    String from = input.substring(fromIndex + 5, toIndex).trim();
                    String to = input.substring(toIndex + 3).trim();
                    if (content.equals("")
                            || from.equals("") || to.equals("")) {
                        throw new ContentMissingException("event");
                    }
                    newTask = new Event(content, from, to);
                    this.taskList.add(newTask);
                    break;
                default:
                    throw new InvalidCommandException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ContentMissingException(keyword);
        }

        Jarvis.horizontal_line_printer();
        System.out.println("Got it, sir. I've added this task: ");
        System.out.println(newTask.toString());
        count_taskList();
        Jarvis.horizontal_line_printer();
    }

    public void count_taskList() {
        int num = taskList.size();
        if (num == 0) {
            System.out.println("Sir, there's nothing on the list currently.");
        } else {
            System.out.println("Now you have " + num + " tasks in the list.");
        }
    }
}
