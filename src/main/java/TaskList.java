import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>();

    public void add(Task task, Boolean isDisplayMessage) {
        tasks.add(task);
        if (isDisplayMessage) {
            addSuccessMessage(task);
        }
    }
    public void addSuccessMessage(Task task) {
        System.out.println(Ben.HORIZONTAL_LINE + "\nGot It! This task has been added:\n" + task +
                "\nNow you have " + tasks.size() + " items in the list\n" + Ben.HORIZONTAL_LINE);
    }

    public void delete(Task task) {
        tasks.remove(task);
        System.out.println(Ben.HORIZONTAL_LINE + "\n" + "Sure thing! This task has been removed\n" + task +
                "\nNow you have " + tasks.size() + " tasks left\n" + Ben.HORIZONTAL_LINE);
    }

    public boolean isEditListCommand(String message) {
        Pattern pattern = Pattern.compile("(unmark|mark|delete)\\s*(-?\\d+)");
        Matcher matcher = pattern.matcher(message.toLowerCase());

        if (matcher.find()) {
            // extract command
            String command = matcher.group(1);

            // extract task number
            String TaskNumber = matcher.group(2);
            int num = Integer.parseInt(TaskNumber) - 1;

            // check whether number is valid
            if (num < 0 || num >= tasks.size()) {
                System.out.println(Ben.HORIZONTAL_LINE + "\n" +
                        "Please input a valid task number" + "\n" + Ben.HORIZONTAL_LINE);
                return true;
            }

            // if valid, mark or unmark the task
            Task task = tasks.get(num);

            if (Objects.equals(command, "mark")) {
                task.mark();
                System.out.println(Ben.HORIZONTAL_LINE + "\n" +
                        "Nice! This task is completed\n" + task + "\n" + Ben.HORIZONTAL_LINE);
            } else if ((Objects.equals(command, "delete"))) {
                delete(tasks.get(num));
            } else {
                task.unmark();
                System.out.println(Ben.HORIZONTAL_LINE + "\n" +
                        "Okay! This task is not completed\n" + task + "\n" + Ben.HORIZONTAL_LINE);

            }
            return true;
        }
        return false;
    }

    public String saveTasks() {
        StringBuilder s = new StringBuilder();
        for (Task task : tasks) {
            s.append(task.saveString()).append("\n");
        }
        return s.toString();
    }

    @Override
    public String toString() {
        String message = "";
        for (int i = 1; i <= tasks.size(); i++) {
            message += i + ". " + tasks.get(i - 1).toString() + "\n";
        }
        return message;
    }
}
