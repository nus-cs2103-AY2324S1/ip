import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Duke {
    private static String COLOR_RESET = "\u001B[0m";
    private static String GREEN = "\033[0;32m";
    private static String YELLOW = "\033[0;33m";
    private static String BLUE = "\033[0;34m";
    private static String PURPLE = "\u001B[35m";
    private static String RED = "\033[0;31m";

    private Task[] tasks = new Task[100];
    private int count = 0;

    private static String extractTail(String[] item) {
        return String.join(" ",
            Arrays.copyOfRange(
                item, 1, item.length
            )
        );
    }

    private void parseInput(String input) {
        // Ignore empty user input
        if (input.equals("")) return;

        String[] parse = input.split("/");
        String[] header = parse[0].strip().split(" ");
        String command = header[0];

        if (parse[0].equals("list")) {
            this.listTasks();
        } 
        else if (command.equals("mark")) {
            if (header.length < 2) return;
            this.markTask(header[1], true);
        } 
        else if (command.equals("unmark")) {
            if (header.length < 2) return;
            this.markTask(header[1], false);
        } 
        else if (command.equals("todo")) {
            this.addTodo(extractTail(header));
        } 
        else if (command.equals("deadline")) {
            if (parse.length < 2) return;
            this.addDeadline(
                extractTail(header),
                extractTail(parse[1].split(" "))
            );
        } 
        else if (command.equals("event")) {
            if (parse.length < 3) return;
            this.addEvent(
                extractTail(header), 
                extractTail(parse[1].split(" ")),
                extractTail(parse[2].split(" "))
            );
        } else {
            this.error(new String[] {
                "Unrecognized command " + PURPLE + command + COLOR_RESET,
                "Maybe create a new TODO with \"todo read a book\"?"
            });
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

    private void addTodo(String description) {
        Task todo = new Todo(description);
        this.addTask(todo);
        this.speak(new String[] {
            "Okie! I've added a new " + GREEN + "TODO:" + COLOR_RESET,
            "  " + todo.toString(),
            "Total no. of tasks stored: " + count
        });
    }

    private void addDeadline(String description, String by) {
        Task deadline = new Deadline(description, by);
        this.addTask(deadline);
        this.speak(new String[] {
            "Okie! I've added a new " + BLUE + "DEADLINE:" + COLOR_RESET,
            "  " + deadline.toString(),
            "Total no. of tasks stored: " + count
        });
    }

    private void addEvent(String description, String start, String end) {
        Task event = new Event(description, start, end);
        this.addTask(event);
        this.speak(new String[] {
            "Okie! I've added a new " + YELLOW + "EVENT:" + COLOR_RESET,
            "  " + event.toString(),
            "Total no. of tasks stored: " + count
        });
    }

    private void addTask(Task task) {
        tasks[this.count] = task;
        this.count += 1;
    }

    private void markTask(String taskCount, boolean done) {
        int index;
        // User passes a non-integer argument.
        try {
            index = Integer.parseInt(taskCount);
        } catch (NumberFormatException e) {
            this.error(
                PURPLE + (done ? "mark" : "unmark") + COLOR_RESET 
                + " takes in a number. Try mark 1."
            );
            return;
        }

        // User tries to mark/unmark a task that is out of bounds.
        if (index < 1 || index > count) {
            this.error(String.format(
                "Unable to %s task %d :( You have %d tasks stored.",
                done ? "mark" : "unmark", index, count
            ));
            return;
        }

        // Mark or unmark the task if the taskCount given is correct.
        Task task = this.tasks[index - 1];
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

    private void error(String text) {
        String msg = String.format(
            "\n    %s\n    %s\n", 
            RED + "Erm... error :(" + COLOR_RESET,
            text
        );
        System.out.println(msg);
    }

    private void error(String[] text) {
        String msg = String.format(
            "\n    %s\n", 
            RED + "Erm... error :(" + COLOR_RESET
        );
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
            "Hi. I'm " + PURPLE + "Bryan" + COLOR_RESET,
            "What can I do for you?"
        });

        String input = "";
        while (true) {
            System.out.print("> ");
            input = reader.readLine();
            if (input.equals("bye")) break;
            chatbot.parseInput(input);
        }

        chatbot.speak("Bye~ Come back soon :)");
    }
}
