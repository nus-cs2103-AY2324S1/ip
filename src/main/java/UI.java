import java.util.ArrayList;

public class UI {

    public UI() {}

    void divider() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    void welcomeMessage() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        this.divider();
        System.out.println(" Hello! I'm JARVIS");
        System.out.println("What can I do for you?");
        this.divider();
    }

    public void list(ArrayList<Task> taskArrayList) {
        this.divider();
        if (taskArrayList.size() == 0) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskArrayList.size(); i++) {
                int index = i + 1;
                Task t = taskArrayList.get(i);
                System.out.println(index + "." + t.toString());
            }
        }
        this.divider();
    }

    public void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        this.divider();
    }

    public void deleteMessage() {

    }
}
