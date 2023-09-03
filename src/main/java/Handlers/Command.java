package Handlers;

public interface Command {

    /**
     * Parses the content of the input, that succeeds the command.
     *
     * @param commandContent The content of the input.
     */
    void parseCommandContent(String commandContent);
}
