import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    private static String COLOR_RESET = "\u001B[0m";
    private static String GREEN = "\033[0;32m";
    private static String YELLOW = "\033[0;33m";
    private static String BLUE = "\033[0;34m";
    private static String PURPLE = "\u001B[35m";
    private static String RED = "\033[0;31m";

    private static String SAVE_TASKS_DIR = "./data/data.txt"; 

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

    public void start() throws IOException {
        this.speak("Initalizing... Please be patient");
        // Load tasks from local file (if it exists)
        this.readTasksFromFile();

        this.speak(new String[] {
            "Hi. I'm " + cTxt("Bryan", PURPLE),
            "What can I do for you?"
        });

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in)
        );

        // Begin chatbot's main event loop
        String input = "";
        while (true) {
            System.out.print("> ");
            input = reader.readLine();
            if (input.equals("bye")) break;
            try {
                this.parseInput(input);
            } catch (DukeException e) {
                this.error(e.toString());
            }
        }

        this.exit();
    }

    private void exit() {
        this.speak("Bye~ Come back soon :)");
    }

    private void parseInput(String input) throws DukeException {
        // Ignore empty user input
        if (input.equals("")) return;

        // Extract main command first
        String[] parse = input.split(" ");
        String command = parse[0];

        if (command.equals("list")) {
            this.listTasks();
        } 
        
        else if (command.equals("mark")) {
            if (parse.length < 2) {
                throw new DukeException(new String[] {
                    "Looks like you're missing a number:",
                    "Try " + cTxt("mark", PURPLE) + " 1"
                });
            }
            this.markTask(parse[1], true);
        } 
        
        else if (command.equals("unmark")) {
            if (parse.length < 2) {
                throw new DukeException(new String[] {
                    "Looks like you're missing a number:",
                    "Try " + cTxt("unmark", PURPLE) + " 1"
                });
            }          
            this.markTask(parse[1], false);
        } 
        
        else if (command.equals("todo")) {
            if (parse.length < 2) {
                throw new DukeException(new String[] {
                    "Looks like you're missing a description:",
                    "Try " + cTxt("todo", PURPLE) + " read a book"
                });
            }
            this.addTodo(extractTail(parse));
        } 
        
        else if (command.equals("deadline")) {
            // Split by the "/by" to separate the first and second part. 
            String[] dlParse = input.split("/by "); 

            // Extract the header (command + description).
            String[] header = dlParse[0].split(" ");
            
            // Check if task descripton exists.
            if (header.length < 2) {
                throw new DukeException(new String[] {
                    "Looks like you're missing a description:",
                    "Try " + cTxt("deadline", PURPLE) + " submit essay /by Monday, 4pm"
                });
            }
            
            // Check if a date was provided and the "/by" delimiter was supplied.
            if (dlParse.length < 2) {
                throw new DukeException(new String[] {
                    "Looks like you're missing a date:",
                    "<- Remember to include /by ->",
                    "Try " + cTxt("deadline", PURPLE) + " submit essay /by Monday, 4pm"
                });
            }

            // Extract the date and add a new deadline to the task list.
            LocalDateTime date = DateParser.parseDateString(dlParse[1]);
            if (date == null) {
                throw new DukeException(
                    "Oops, looks like your date is in an invalid format..."
                );
            }
            this.addDeadline(
                extractTail(header),
                date
            );
        } 
        
        else if (command.equals("event")) {
            // Split by "/from" to separate the first and (second + third) part. 
            String[] evParse = input.split("/from ");

            // Extract the header (command + description).
            String[] header = evParse[0].split(" ");

            // Check if task descripton exists.
            if (header.length < 2) {
                throw new DukeException(new String[] {
                    "Looks like you're missing a description:",
                    "Try " + cTxt("event", PURPLE) + " NUS carnival /from Aug 21st /to Aug 25th"
                });
            }

            if (evParse.length < 2) {
                throw new DukeException(new String[] {
                    "Looks like you're missing " + cTxt("/from", PURPLE),
                    "Try " + cTxt("event", PURPLE) + " NUS carnival /from Aug 21st /to Aug 25th"
                });
            }

            // Split by "/to" to separate the second and third part. 
            String[] dateParse = evParse[1].split("/to ");

            if (dateParse.length < 2) {
                throw new DukeException(new String[] {
                    "Looks like you're missing " + cTxt("/to", PURPLE),
                    "Try " + cTxt("event", PURPLE) + " NUS carnival /from Aug 21st /to Aug 25th"
                });
            }

            String fromDate = dateParse[0];
            String toDate = dateParse[1];
            this.addEvent(
                extractTail(header), 
                fromDate,
                toDate
            );
        } 

        else if (command.equals("delete")) {
            if (parse.length < 2) {
                throw new DukeException(new String[] {
                    "Looks like you're missing a number:",
                    "Try " + cTxt("delete", PURPLE) + " 1"
                });
            }
            this.delete(parse[1]);
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

    private void readTasksFromFile() {
        try {
            BufferedReader br = new BufferedReader(
                new FileReader(SAVE_TASKS_DIR)
            );

            String line;
            while((line = br.readLine()) != null) {
                String[] parse = line.strip().split("\\|");
                // Shortest length is 3 for any task type
                if (parse.length < 3) continue;
                String taskType = parse[0];
                boolean isDone = parse[1].equals("1");

                // Add task based on type given
                switch(taskType) {
                case "T":
                    this.addTask(
                        new Todo(parse[2], isDone)
                    );
                    break;
                case "D":
                    if (parse.length < 4) break;
                    this.addTask(
                        new Deadline(parse[2], DateParser.parseDateString(parse[3]), isDone)
                    );
                    break;
                case "E":
                    if (parse.length < 5) break;
                    this.addTask(
                        new Event(parse[2], parse[3], parse[4], isDone)
                    );
                    break;
                default:
                    break;
                }
            }
            this.speak("Tasks successfully loaded!"); 
            // Close the reader after parsing the file
            br.close();
        } catch (FileNotFoundException f) {
            // Create a new data directory if it cannot be found
            if (new File("./data").mkdir()) {
                this.speak(
                    "I've created a new folder to store your tasks :)"
                );
            }
        } catch (IOException e) {
            this.error(
                "Oops, there was an issue retrieving your saved tasks"
            );
        }
    }

    private String taskListToString() {
        String taskListString = "";
        for (Task task : this.tasks) {
            taskListString += task.toFileFormatString() + "\n";
        }
        return taskListString.strip();
    }

    private void updateSavedTasks() {
        try {
            FileWriter fw = new FileWriter(SAVE_TASKS_DIR);
            fw.write(this.taskListToString());
            fw.close();
        } catch (IOException e) {
            this.error("Sorry error with saving tasks!");
        }
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

        // Write modified task list to file
        this.updateSavedTasks();
    }

    private void addTodo(String description) {
        Task todo = new Todo(description);
        this.addTask(todo);
        this.speak(new String[] {
            "Okie! I've added a new " + cTxt("TODO", GREEN) + ":",
            "  " + todo.toString(),
            "Total no. of tasks stored: " + tasks.size()
        });
        this.updateSavedTasks();
    }

    private void addDeadline(String description, LocalDateTime by) {
        Task deadline = new Deadline(description, by);
        this.addTask(deadline);
        this.speak(new String[] {
            "Okie! I've added a new " + cTxt("DEADLINE", BLUE) + ":",
            "  " + deadline.toString(),
            "Total no. of tasks stored: " + tasks.size()
        });
        this.updateSavedTasks();
    }

    private void addEvent(String description, String start, String end) {
        Task event = new Event(description, start, end);
        this.addTask(event);
        this.speak(new String[] {
            "Okie! I've added a new " + cTxt("EVENT", YELLOW) + ":",
            "  " + event.toString(),
            "Total no. of tasks stored: " + tasks.size()
        });
        this.updateSavedTasks();
    }

    private void addTask(Task task) {
        tasks.add(task);
    }

    private void markTask(String taskCount, boolean isDone) throws DukeException {
        int index;
        // User passes a non-integer argument.
        try {
            index = Integer.parseInt(taskCount);
        } catch (NumberFormatException e) {
            this.error(
                cTxt((isDone ? "mark" : "unmark"), PURPLE)
                + " takes in a number. Try " + cTxt("mark", PURPLE) + " 1."
            );
            return;
        }

        // User tries to mark/unmark a task that is out of bounds.
        if (index < 1 || index > tasks.size()) {
            throw new DukeException(String.format(
                "Unable to %s task %d :( You have %d task(s) stored.",
                isDone ? "mark" : "unmark", index, tasks.size()
            ));
        }

        // Mark or unmark the task if the taskCount given is correct.
        Task task = this.tasks.get(index - 1);
        if (isDone) task.mark(); 
        else      task.unmark();

        String success = isDone
            ? "Nice, I've marked this task as done:"
            : "Okie, I've marked this task as not done yet:";

        this.speak(new String[] {
            success,
            "  " + task.toString()
        });

        this.updateSavedTasks();
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
        chatbot.start();
    }
}
