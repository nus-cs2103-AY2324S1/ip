import java.util.Scanner;

class InputHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final Duke.Model model;
    private final Duke.TaskStorage taskStorage = new Duke.TaskStorage();

    private boolean isExit = false;

    public InputHandler(Duke.Model model) {
        this.model = model;
    }

    // TODO: Implement a parser instead of hard coding if-elses here
    public void handleInput() {
        String input = scanner.nextLine();
        if (input.equals("bye")) {
            isExit = true;
            return;
        }

        if (input.equals("list")) {
            System.out.println(taskStorage);
            return;
        }

        if (input.startsWith("mark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            model.echo(taskStorage.markAsDone(index));
            return;
        }

        if (input.startsWith("unmark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            model.echo(taskStorage.unmarkAsDone(index));
            return;
        }

        if (input.startsWith("delete")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            model.echo(taskStorage.delete(index));
            return;
        }

        model.echo(taskStorage.save(input));
    }

    public boolean isExit() {
        return isExit;
    }
}
