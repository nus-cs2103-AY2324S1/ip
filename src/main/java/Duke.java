import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    private static String ANSI_RESET = "\u001B[0m";
    private static String ANSI_PURPLE = "\u001B[35m";

    private Task[] tasks = new Task[100];
    private int count = 0;

    private void parseCommand(String command) {
        // Ignore empty user input
        if (command.equals("")) return;

        String[] parse = command.split(" ");

        if (parse[0].equals("list")) {
            this.listTasks();
        } else if (parse[0].equals("mark")) {
            if (parse.length < 2) return;
            this.markTask(Integer.parseInt(parse[1]), true);
        } else if (parse[0].equals("unmark")) {
            if (parse.length < 2) return;
            this.markTask(Integer.parseInt(parse[1]), false);
        } else {
            this.appendTask(command);
        }
    }

    private void listTasks() {
        if (this.count == 0) {
            this.speak("Nothing stored.");
            return;
        }

        String[] formatTasks = new String[count + 1];
        formatTasks[0] = "Here are the tasks in your list:";
        for (int i = 0; i < count; i++) {
            formatTasks[i + 1] = String.format(
                "%d.%s", i + 1, this.tasks[i].toString()
            );
        }
        this.speak(formatTasks);
    }

    private void appendTask(String task) {
        tasks[count] = new Task(task);
        count += 1;
        this.speak(String.format("task added: %s", task));
    }

    private void markTask(int taskCount, boolean done) {
        if (taskCount < 1 || taskCount > count) {
            this.speak(String.format(
                "Unable to %s task %d. You have %d tasks stored.",
                done ? "mark" : "unmark", taskCount, count
            ));
            return;
        }

        Task task = this.tasks[taskCount - 1];
        if (done) task.mark(); 
        else      task.unmark();

        String success = done
            ? "Nice, I've marked this task as done:"
            : "OK, I've marked this task as not done yet:";

        this.speak(new String[] {
            success,
            "  " + task.toString()
        });
    }

    private void speak(String text) {
        String msg = String.format("\n    %s", text);
        System.out.println(msg + "\n");
    }

    private void speak(String[] text) {
        String msg = "\n";
        for (String stub : text) {
            msg += String.format("    %s\n", stub);
        }
        System.out.println(msg);
    }

    public static void main(String[] args) throws IOException {
        Duke chatbot = new Duke();
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in)
        );

        chatbot.speak(new String[] {
            "Hi. I'm " + ANSI_PURPLE + "Bryan" + ANSI_RESET,
            "What can I do for you?"
        });

        String command = "";
        while (true) {
            System.out.print("> ");
            command = reader.readLine();
            if (command.equals("bye")) break;
            chatbot.parseCommand(command);
        }

        chatbot.speak("Bye~ Come back soon :)");
    }
}
