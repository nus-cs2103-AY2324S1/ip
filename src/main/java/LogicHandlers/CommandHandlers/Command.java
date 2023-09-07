package LogicHandlers.CommandHandlers;

public interface Command {

    /**
     * Parses the content of the input, that succeeds the command.
     *
     * @param commandContent The content of the input.
     */
    String parseCommandContent(String commandContent);
}
