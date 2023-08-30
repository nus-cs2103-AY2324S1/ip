import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static TaskList tasks;
    private static final String NAME = "DEREK";
    private static UI ui = new UI(NAME);
    private static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HHmm";
    public static final DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);

    public static void main(String[] args) {
        Duke.tasks = new TaskList();
        Scanner in = new Scanner(System.in);

        ui.printWelcomeMessage();

        while (true) {
            String line = in.nextLine();
            try {
                ArrayList<String> parsedInput = Parser.parseUserInput(line);
                String command = parsedInput.get(0);

                if (command.equals(Command.MARK.getCommand())) {
                    int index = Integer.parseInt(parsedInput.get(1)) - 1;
                    tasks.mark(index);
                    continue;
                }
                if (command.equals(Command.UNMARK.getCommand())) {
                    int index = Integer.parseInt(parsedInput.get(1)) - 1;
                    tasks.unmark(index);
                    continue;
                }
                if (command.equals(Command.LIST.getCommand())) {
                    tasks.printContents();
                    continue;
                }
                if (command.equals(Command.BYE.getCommand())) {
                    Duke.tasks.saveState();
                    ui.printGoodbyeMessage();
                    break;
                }
                if (command.equals(Command.TODO.getCommand())) {
                    ToDo newTodo = new ToDo(parsedInput.get(1));
                    tasks.add(newTodo);
                    continue;
                }
                if (command.equals(Command.DEADLINE.getCommand())) {
                    Deadline newDeadline = new Deadline(parsedInput.get(1),
                            LocalDateTime.parse(parsedInput.get(2), dateTimeInputFormatter));
                    tasks.add(newDeadline);
                    continue;
                }
                if (command.equals(Command.EVENT.getCommand())) {
                    Event newEvent = new Event(parsedInput.get(1),
                            LocalDateTime.parse(parsedInput.get(2), dateTimeInputFormatter),
                            LocalDateTime.parse(parsedInput.get(3), dateTimeInputFormatter));
                    tasks.add(newEvent);
                    continue;
                }
                if (command.equals(Command.DELETE.getCommand())) {
                    int index = Integer.parseInt(parsedInput.get(1)) - 1;
                    Duke.tasks.remove(index);
                    continue;
                }
                if (command.equals(Command.ON.getCommand())) {
                    LocalDate date = LocalDate.parse(parsedInput.get(1));
                    tasks.printTasksOn(date);
                    continue;
                }
                throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
        in.close();
    }
}
