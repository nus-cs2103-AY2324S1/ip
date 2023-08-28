import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    private static String COLOR_RESET = "\u001B[0m";
    private static String GREEN = "\033[0;32m";
    private static String YELLOW = "\033[0;33m";
    private static String BLUE = "\033[0;34m";
    private static String PURPLE = "\u001B[35m";
    private static String RED = "\033[0;31m";

    private ArrayList<Task> tasks = new ArrayList<Task>();

    private static String extractTail(String[] item) {
        return String.join(" ",
            Arrays.copyOfRange(
                item, 1, item.length
            )
        );
    }

    private static String cTxt(String text, String color) {
        return color + text + COLOR_RESET;
    }

    private void parseInput(String input) throws DukeException {
        // Ignore empty user input
        if (input.equals("")) return;

        String[] parse = input.split("/");
        String[] header = parse[0].strip().split(" ");
        String command = header[0];

        if (parse[0].equals("list")) {
            this.listTasks();
        } 
        
        else if (command.equals("mark")) {
            if (header.length < 2) {
                throw new DukeException(new String[] {
                    "Looks like you're missing a number:",
                    "Try " + cTxt("mark", PURPLE) + " 1"
                });
            }
            this.markTask(header[1], true);
        } 
        
        else if (command.equals("unmark")) {
            if (header.length < 2) {
                throw new DukeException(new String[] {
                    "Looks like you're missing a number:",
                    "Try " + cTxt("unmark", PURPLE) + " 1"
                });
            }          
            this.markTask(header[1], false);
        } 
        
        else if (command.equals("todo")) {
            if (header.length < 2) {
                throw new DukeException(new String[] {
                    "Looks like you're missing a description:",
                    "Try " + cTxt("todo", PURPLE) + " read a book"
                });
            }
            this.addTodo(extractTail(header));
        } 
        
        else if (command.equals("deadline")) {
            if (header.length < 2) {
                throw new DukeException(new String[] {
                    "Looks like you're missing a description:",
                    "Try " + cTxt("deadline", PURPLE) + " submit essay /by Monday, 4pm"
                });
            }
            
            String[] date;
            if (parse.length < 2
                    || (date = parse[1].split(" ")).length < 2) {
                throw new DukeException(new String[] {
                    "Looks like you're missing a date:",
                    "<- Remember to include /by ->",
                    "Try " + cTxt("deadline", PURPLE) + " submit essay /by Monday, 4pm"
                });
            }
            this.addDeadline(
                extractTail(header),
                extractTail(date)
            );
        } 
        
        else if (command.equals("event")) {
            if (header.length < 2) {
                throw new DukeException(new String[] {
                    "Looks like you're missing a description:",
                    "Try " + cTxt("event", PURPLE) + " NUS carnival /from Aug 21st /to Aug 25th"
                });
            } 
            
            String[] fromDate, toDate;
            if (parse.length < 3
                    || (fromDate = parse[1].split(" ")).length < 2
                    || (toDate   = parse[2].split(" ")).length < 2) {
                throw new DukeException(new String[] {
                    "Looks like you're missing a date range:",
                    "<- Remember to include /from and /to ->",
                    "Try " + cTxt("event", PURPLE) + " NUS carnival /from Aug 21st /to Aug 25th"
                });
            }
            this.addEvent(
                extractTail(header), 
                extractTail(fromDate),
                extractTail(toDate)
            );
        } 

        else if (command.equals("delete")) {
            if (header.length < 2) {
                throw new DukeException(new String[] {
                    "Looks like you're missing a number:",
                    "Try " + cTxt("delete", PURPLE) + " 1"
                });
            }
            this.delete(header[1]);
        } 
        
        else {
            throw new DukeException(new String[] {
                "Unrecognized command " + cTxt(command, PURPLE),
                "Maybe create a new TODO with " + cTxt("todo", PURPLE) + " read a book?"
            });
        }
    }

    private void listTasks() {
        if (this.tasks.size() == 0) {
            this.speak("Nothing stored.");
            return;
        }

        String[] formatTasks = new String[tasks.size() + 1];
        formatTasks[0] = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            formatTasks[i + 1] = String.format(
                "%d.%s", 
                i + 1, this.tasks.get(i).toString()
            );
        }
        this.speak(formatTasks);
    }

    private String taskListToString() {
        String taskListString = "";
        for (Task task : this.tasks) {
            taskListString += task.toFileFormatString() + "\n";
        }
        System.out.println(taskListString);
        return taskListString.strip();
    }

    private void delete(String taskCount) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(taskCount);
        } catch (NumberFormatException e) {
            this.error(
                cTxt("delete", PURPLE)
                + " takes in a number. Try delete 1."
            );
            return;
        }

        // User tries to delete a task that is out of bounds.
        if (index < 1 || index > tasks.size()) {
            throw new DukeException(String.format(
                "Unable to " 
                    + cTxt("delete", PURPLE) 
                    + " task %d :( You have %d task(s) stored.",
                index, tasks.size()
            ));
        }

        Task removedTask = this.tasks.remove(index - 1);
        this.speak(new String[] {
            "Okie! I've deleted task " + taskCount + ":",
            "  " + removedTask.toString(),
            "Total no. of tasks stored: " + tasks.size()
        });

        // TODO: Rewrite to file?
    }

    private void addTodo(String description) {
        Task todo = new Todo(description);
        this.addTask(todo);
        this.speak(new String[] {
            "Okie! I've added a new " + cTxt("TODO", GREEN) + ":",
            "  " + todo.toString(),
            "Total no. of tasks stored: " + tasks.size()
        });
    }

    private void addDeadline(String description, String by) {
        Task deadline = new Deadline(description, by);
        this.addTask(deadline);
        this.speak(new String[] {
            "Okie! I've added a new " + cTxt("DEADLINE", BLUE) + ":",
            "  " + deadline.toString(),
            "Total no. of tasks stored: " + tasks.size()
        });
    }

    private void addEvent(String description, String start, String end) {
        Task event = new Event(description, start, end);
        this.addTask(event);
        this.speak(new String[] {
            "Okie! I've added a new " + cTxt("EVENT", YELLOW) + ":",
            "  " + event.toString(),
            "Total no. of tasks stored: " + tasks.size()
        });
    }

    private void addTask(Task task) {
        tasks.add(task);
        // TODO: Write to file
    }

    private void markTask(String taskCount, boolean done) throws DukeException {
        int index;
        // User passes a non-integer argument.
        try {
            index = Integer.parseInt(taskCount);
        } catch (NumberFormatException e) {
            this.error(
                cTxt((done ? "mark" : "unmark"), PURPLE)
                + " takes in a number. Try " + cTxt("mark", PURPLE) + " 1."
            );
            return;
        }

        // User tries to mark/unmark a task that is out of bounds.
        if (index < 1 || index > tasks.size()) {
            throw new DukeException(String.format(
                "Unable to %s task %d :( You have %d task(s) stored.",
                done ? "mark" : "unmark", index, tasks.size()
            ));
        }

        // Mark or unmark the task if the taskCount given is correct.
        Task task = this.tasks.get(index - 1);
        if (done) task.mark(); 
        else      task.unmark();

        String success = done
            ? "Nice, I've marked this task as done:"
            : "Okie, I've marked this task as not done yet:";

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
            cTxt("Erm... error :(", RED),
            text
        );
        System.out.println(msg);
    }

    // private void error(String[] text) {
    //     String msg = String.format(
    //         "\n    %s\n", 
    //         cTxt("Erm... error :(", RED)
    //     );
    //     for (String stub : text) {
    //         msg += String.format("    %s\n", stub);
    //     }
    //     System.out.println(msg);
    // }

    public static void main(String[] args) throws IOException {
        Duke chatbot = new Duke();
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in)
        );

        chatbot.speak(new String[] {
            "Hi. I'm " + cTxt("Bryan", PURPLE),
            "What can I do for you?"
        });

        String input = "";
        while (true) {
            System.out.print("> ");
            input = reader.readLine();
            if (input.equals("bye")) break;
            try {
                chatbot.parseInput(input);
            } catch (DukeException e) {
                chatbot.error(e.toString());
            }
        }

        chatbot.speak("Bye~ Come back soon :)");
    }
}
