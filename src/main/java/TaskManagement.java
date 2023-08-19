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

            if (input.equalsIgnoreCase("list")) {
                list_printer();
                continue;
            } else if (input.startsWith("mark ") && input.substring(5).matches("\\d+")) {
                mark_task(input);
                continue;
            } else if (input.startsWith("unmark ") && input.substring(7).matches("\\d+")) {
                unmark_task(input);
                continue;
            } else if (input.equalsIgnoreCase("bye")) {
                break;
            }

            add_task(input);
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

    private void mark_task(String input) {
        int index = Integer.parseInt(input.substring(5));
        if (index > this.taskList.size() || index < 0) {
            System.out.println("Sir, your target index is invalid");
            return;
        }
        Task target = this.taskList.get(index-1);
        target.mark();
        Jarvis.horizontal_line_printer();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(target.toString());
        Jarvis.horizontal_line_printer();
    }

    private void unmark_task(String input) {
        int index = Integer.parseInt(input.substring(7));
        if (index > this.taskList.size() || index < 0) {
            System.out.println("Sir, your target index is invalid");
            return;
        }
        Task target = this.taskList.get(index-1);
        target.unmark();
        Jarvis.horizontal_line_printer();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(target.toString());
        Jarvis.horizontal_line_printer();
    }

    private void add_task(String input) {
        this.taskList.add(new Task(input));
        Jarvis.horizontal_line_printer();
        System.out.println("added: " + input);
        Jarvis.horizontal_line_printer();
    }
}
