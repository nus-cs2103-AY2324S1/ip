import java.time.LocalDate;
import java.util.Comparator;

public class Parser {
    public static Command parse(String command) {
        if (command.equals("bye")) {
            return new ExitCommand();
        }

        if (command.equals("list")) {
            return new ListCommand();
        }

        if (command.startsWith("mark ")) {
            int taskId = Integer.parseInt(command.substring(5));
            return new MarkCommand(taskId);
        }

        if (command.startsWith("unmark ")) {
            int taskId = Integer.parseInt(command.substring(7));
            return new UnmarkedCommand(taskId);
        }

        if (command.startsWith("todo")) {
            String task = command.substring(5);
            return new AddCommand(task);
        }

        if (command.startsWith("deadline")) {
            int idx = 9;
            while (idx < command.length() && command.charAt(idx) != '/') {
                idx++;
            }
            String task = command.substring(9, idx - 1);
            String deadline = command.substring(idx + 4);

            return new AddCommand(task, LocalDate.parse(deadline));
        }

        if (command.startsWith("event")) {
            // find the first slash
            int idx1 = 6;
            while (idx1 < command.length() && command.charAt(idx1) != '/') {
                idx1++;
            }

            // find the second slash
            int idx2 = idx1 + 1;
            while (idx2 < command.length() && command.charAt(idx2) != '/') {
                idx2++;
            }

            String task = command.substring(6, idx1 - 1);
            String startTime = command.substring(idx1 + 6, idx2 - 1);
            String endTime = command.substring(idx2 + 4);
            return new AddCommand(task, startTime, endTime);
        }

        if (command.startsWith("delete")) {
            int taskId = Integer.parseInt(command.substring(7));
            return new DeleteCommand(taskId);
        }

        return new InvalidCommand();
    }
}
