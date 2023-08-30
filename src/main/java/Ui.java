public class Ui {
    void addTask(TaskList tasks, Task task) {
        tasks.add(task);
        print("Got it. I've added this task:");
        print(String.format("  %s\n", task.toString()));
        print(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    void bye() {
        print("Bye. Hope to see you again soon!");
    }

    void date(TaskList tasks, String date) throws DukeException {
        int idx = 0;
        String dmy = Parser.convertToDMY(date);
        for (Task t: tasks) {
            if (t.isToday(date)) {
                if (idx == 0) {
                    print(String.format("Here are the tasks on %s:", dmy));
                }
                print(String.format("%d.%s", ++idx, t.toString()));
            }
        }
        if (idx == 0) {
            print(String.format("There is no task on %s.", dmy));
        }
    }
    
    void deleteTask(TaskList tasks, int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task index");
        }
        Task task = tasks.remove(index);
        print("Noted. I've removed this task:");
        print(String.format("  %s\n", task.toString()));
        print(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    void hello() {
        print("Hello! I'm AdaBot.\nWhat do you want to do today?");
    }

    void list(TaskList tasks) {
        if (tasks.size() == 0) {
            print("There is no task in your list.");
            return;
        }
        print("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            print(String.format("%d.%s", i + 1, tasks.get(i).toString()));
        }
    }

    void mark(TaskList tasks, int index) throws DukeException {
        Task task = tasks.markDone(index);
        print("Nice! I've marked this task as done:");
        print("  " + task.toString());
    }    

    void print(String s) {
        System.out.println(s);
    }

    void printError(String s) {
        System.out.println("OOPS!!! " + s);
    }

    void unmark(TaskList tasks, int index) throws DukeException {
        Task task = tasks.unmarkDone(index);
        print("OK, I've marked this task as not done yet:");
        print("  " + task.toString());
    }
}
