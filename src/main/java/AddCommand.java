public class AddCommand extends Command {

    public AddCommand(String command) {
        super(command);
    }

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String input = getCommand();
        String[] inputArray = input.split(" ");
        String taskType = inputArray[0];
        String inputSplit[];
        switch (TaskType.valueOf(taskType.toUpperCase())) {
        case TODO:
            try {
                taskList.add(new Todo (inputArray[1]));
            }
            catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Description of todo cannot be empty.");
            }
        case DEADLINE:
            try {
                inputSplit = input.split(" /by ");
                taskList.add(new Deadline(inputSplit[0].substring(9), inputSplit[1]));
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new DukeException("Deadline should follow the format deadline <description> /by <date and time>");
            }
        case EVENT:
            try {
                inputSplit = input.split(" /");
                taskList.add(new Event(inputSplit[0].substring(6), inputSplit[1].substring(5), inputSplit[2].substring(3)));
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Event should follow the format event <description> /from <start date and time> /to <end date and time>");
            }
        default:
            throw new DukeException("Task type is not recognised. Please use todo, deadline or event.");
        }
    }

}
