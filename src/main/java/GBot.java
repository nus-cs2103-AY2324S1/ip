import exceptions.GBotException;
import java.io.IOException;
import java.util.Scanner;

public class GBot {
    private Commands commands;
    private Disk disk;
    enum Keyword {
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE
    }

    public GBot(String path) throws IOException {
        this.disk = new Disk(path);
        this.commands = new Commands(this.disk);
    }

    private void start() throws Exception {
        Commands.print("Hello I'm GBot!\nWhat can I do for you?");
        Scanner inputScanner = new Scanner(System.in);

        while (true) {
            String message = inputScanner.nextLine();
            if (message.equals("bye")) {
                inputScanner.close();
                Commands.print("Bye. Hope to see you again soon!");
                return;
            } else if (message.equals("list")) {
                this.commands.listTasks();
                continue;
            }

            Keyword prefix = Keyword.valueOf(message.split(" ")[0].toUpperCase());
            String str = message.substring(prefix.toString().length() + 1);
            switch (prefix) {
            case MARK:
                this.commands.markTask(message);
                break;
            case UNMARK:
                this.commands.unmarkTask(message);
                break;
            case TODO:
                this.commands.addTodo(str);
                break;
            case DEADLINE:
                this.commands.addDeadline(str);
                break;
            case EVENT:
                this.commands.addEvent(str);
                break;
            case DELETE:
                this.commands.deleteTask(message);
                break;
            default:
                throw new GBotException();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new GBot("./data/tasks.txt").start();
    }
}

