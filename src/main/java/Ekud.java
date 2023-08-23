import extensions.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Ekud {
    protected String horizontalLine = "##***~-~-~-~-~-~--~-~-~-~-~-~***##";
    protected String intro = "Hello there! I'm Ekud. :)\nWhat can I do for you? :O";
    protected String outro = "Goodbye, have a nice day! :p";
    protected List<Task> tasks = new ArrayList<>();
    public void intro() {
        this.echo(this.intro);
    }
    public void outro() {
        this.echo(this.outro);
    }
    public void echo(String message) {
        System.out.println(String.format("%s\n%s\n%s",
                this.horizontalLine,
                message,
                this.horizontalLine));
    }
    public void addToTasks(String text) {
        tasks.add(new Task(text));
        this.echo("added: " + text);
    }
    public void showTasks() {
        System.out.println(horizontalLine);
        System.out.println("Here is your to-do list: d(･∀･○)");
        int len = tasks.size();
        for (int i = 0; i < len; i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks.get(i).toString()));
        }
        System.out.println(horizontalLine);
    }
    public void markAsDone(int index) {
        Task task = this.tasks.get(index);
        task.markAsDone();
        String message = "The following task is marked done, sheeesh:\n" + task.toString();
        this.echo(message);
    }
    public void markAsNotdone(int index) {
        Task task = this.tasks.get(index);
        task.markAsNotDone();
        String message = "The following task is marked as not done yet:\n" + task.toString();
        this.echo(message);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ekud chatbot = new Ekud();
        chatbot.intro();
        // process user input
        String userInput = scanner.nextLine();
        int firstSpace = userInput.indexOf(' ');
        String command = firstSpace == -1 ? userInput : userInput.substring(0, firstSpace);
        // main chatbot functionality
        while (!command.equals("end")) {
            if (command.equals("list")) {
                chatbot.showTasks();
            } else if (command.equals("mark") || command.equals("unmark")) {
                int index = Integer.valueOf(userInput.substring(firstSpace + 1)) - 1;
                if (command.equals("mark")) chatbot.markAsDone(index);
                else chatbot.markAsNotdone(index);
            } else {
                chatbot.addToTasks(userInput);
            }
            userInput = scanner.nextLine();
            firstSpace = userInput.indexOf(' ');
            command = firstSpace == -1 ? userInput : userInput.substring(0, firstSpace);
        }
        chatbot.outro();
    }
}
