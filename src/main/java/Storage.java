import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> previous_Commands= new ArrayList<>();

    public void add(Task task) {
        this.previous_Commands.add(task);
        System.out.println(
                String.format("Got it. I've added this task: %s\nNow you have %d tasks in the list.",
                        task.toString(), previous_Commands.size())
        );
    }

    public void addWithoutPrinting(Task task) {
        this.previous_Commands.add(task);
    }

    public void listItems() {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < this.previous_Commands.size(); ++i) {
            Task task = this.previous_Commands.get(i);
            System.out.println(
                    String.format("%d. %s",
                            i + 1, task.toString()
                    )
            );
        }
    }

    public void mark(int taskNumber) {
        if (taskNumber > previous_Commands.size() || taskNumber <= 0) {
            System.out.println("☹ OOPS!!! Such a task does not exist!");
            return;
        }

        Task task = previous_Commands.get(taskNumber - 1);

        if (task.isDone()) {
            return;
        }

        task.toggleDone();
        System.out.println(
                String.format(
                        "Nice! I've marked this task as done: %s",
                        task.toString()
                        )
        );
    }

    public void unmark(int taskNumber) {

        if (taskNumber > previous_Commands.size() || taskNumber <= 0) {
            System.out.println("☹ OOPS!!! Such a task does not exist!");
            return;
        }

        Task task = previous_Commands.get(taskNumber - 1);

        if (!task.isDone()) {
            return;
        }

        task.toggleDone();
        System.out.println(
                String.format(
                        "Ok, I've marked this task as not done yet: %s",
                        task.toString()
                )
        );
    }

    public void delete(int taskNumber) {
        if (taskNumber > previous_Commands.size() || taskNumber <= 0) {
            System.out.println("☹ OOPS!!! Such a task does not exist!");
            return;
        }

        Task task = previous_Commands.get(taskNumber -1);
        previous_Commands.remove(taskNumber -1);

        System.out.println(String.format("Noted. I've removed this task: %s", task));
    }

    public void updateALl(ReadWriteData writer) {
        for (Task task: this.previous_Commands) {
            writer.write(task);
        }
    }


}
