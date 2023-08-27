import java.util.ArrayList;
import java.util.List;

public class OutputService {
    private final Duke dukeBot;
    private static final int indentLength = 4;

    public OutputService(Duke dukeBot) {
        this.dukeBot = dukeBot;
    }


    public void echo(String input) {
        echo(input, "");
    }

    public void echo(String input, String prefix) {
        echo(List.of(prefix + input));
    }

    public void echo(List<String> inputs) {
        String divider = indentLeft(String.format("%80s", "").replace(" ", "-"));
        System.out.println(divider);
        inputs.stream().map(this::indentLeft)
                .forEach(System.out::println);
        System.out.println(divider);
    }

    public String indentLeft(String input) {
        String indent = String.format("%" + indentLength + "s", "");
        String[] lines = input.split(System.lineSeparator()); // handle Unix and Windows new lines.
        for (int i = 0; i < lines.length; i++) {
            lines[i] = indent + lines[i];
        }
        return String.join(System.lineSeparator(), lines);
    }

    public void printTasks() {
        List<String> tasksWithNumber = new ArrayList<>();
        for (int i = 0; i < dukeBot.getTaskList().size(); i ++) {
            String taskNumber = String.format("%s. ", i + 1);
            tasksWithNumber.add(taskNumber + dukeBot.getTaskList().get(i));
        }
        echo(tasksWithNumber);
    }
}
