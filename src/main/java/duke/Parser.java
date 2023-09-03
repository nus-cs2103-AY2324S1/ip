package duke;
public class Parser {

    public static Command parse(String input) {
        if (input.equals("bye")) {
            return new ExitCommand();

        } else if (input.startsWith("mark")) {
            String num = input.substring(5);
            int i = Integer.parseInt(num);
            return new MarkCommand(i - 1);

        } else if (input.startsWith("unmark")) {
            String num = input.substring(7);
            int i = Integer.parseInt(num);
            return new UnmarkCommand(i - 1);

        } else if (input.equals("list")) {
            return new ListCommand();

        } else if (input.startsWith("delete")) {
            String num = input.substring(7);
            int i = Integer.parseInt(num);
            return new DeleteCommand(i - 1);

        } else if (input.startsWith("find")) {
            String keyword = input.substring(5);
            return new FindCommand(keyword);

        } else {
            return new AddCommand(input);
        }
    }

}
