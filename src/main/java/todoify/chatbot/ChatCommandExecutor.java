package todoify.chatbot;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.util.Pair;
import todoify.chatbot.exception.command.ChatbotCommandException;
import todoify.chatbot.exception.command.ChatbotInvalidCommandFormatException;
import todoify.taskmanager.TaskManager;
import todoify.taskmanager.task.Deadline;
import todoify.taskmanager.task.Event;
import todoify.taskmanager.task.Task;
import todoify.taskmanager.task.Todo;
import todoify.util.EpochConverter;
import todoify.util.StringFormatter;

/**
 * A package helper class for executing {@link ChatCommand} by the user.
 */
class ChatCommandExecutor {

    protected Chatbot chatbot;
    protected TaskManager taskManager;

    /**
     * Constructs the executor with the given chatbot.
     *
     * @param chatbot The chatbot to execute commands on.
     */
    ChatCommandExecutor(Chatbot chatbot) {
        this.chatbot = chatbot;
        this.taskManager = chatbot.getTaskManager();
    }

    /**
     * Executes the given command on the chatbot that was configured on this instance.
     *
     * @param command The command to execute.
     * @throws ChatbotCommandException if there were any errors during the execution.
     */
    void execute(ChatCommand command) throws ChatbotCommandException {
        switch (command.getOperation()) {
        case ADD_TODO:
            this.processAddTodo(command);
            break;
        case ADD_DEADLINE:
            this.processAddDeadline(command);
            break;
        case ADD_EVENT:
            this.processAddEvent(command);
            break;
        case DELETE:
            this.processDelete(command);
            break;
        case MARK_COMPLETE:
            this.processMarkComplete(true, command);
            break;
        case UNMARK_COMPLETE:
            this.processMarkComplete(false, command);
            break;
        case LIST:
            this.processList(command);
            break;
        case SEARCH:
            this.processSearch(command);
            break;
        case HELP:
            this.processHelp(command);
            break;
        case EXIT:
            this.processExit(command);
            break;
        default:
            assert command.getOperation() == ChatCommand.Operation.UNKNOWN
                    : "All known cases should have been handled!";

            throw new ChatbotCommandException("Sorry, idgi :(\nYou might wanna try the 'help' command to let me "
                    + "guide you about all available commands!");
        }
    }



    /**
     * Asserts that the data component of the command is empty, or otherwise throwing an error.
     *
     * @param command The command to process.
     * @throws ChatbotCommandException if the command data is empty.
     */
    private void assertCommandHasNoData(ChatCommand command) throws ChatbotCommandException {
        if (command.getData().isBlank()) {
            return;
        }

        throw new ChatbotInvalidCommandFormatException(String.format(
                "Hmm, the command '%s' should not have anything following it. Is that a typo?",
                command.getName()
        ));
    }

    /**
     * Obtains the data component of the command, while throwing an exception if the data is blank.
     *
     * @param command The command to process.
     * @throws ChatbotCommandException if the command data is empty.
     */
    private String getCommandStringData(ChatCommand command) throws ChatbotCommandException {
        String data = command.getData();

        if (data.isBlank()) {
            throw new ChatbotInvalidCommandFormatException(String.format(
                    "The command '%s' requires some description after the command name, but none was found!",
                    command.getName()
            ));
        }

        return data;
    }

    /**
     * Obtains the task referenced by the command by treating the data input as a number representing the task number.
     *
     * @param command The command to process.
     * @return An index-task pair, representing the task index and the task referenced.
     * @throws ChatbotCommandException if the data field does not have numeric data as input or is of an invalid value.
     */
    private Pair<Integer, Task> getCommandReferencedIndexTaskPair(ChatCommand command) throws ChatbotCommandException {
        int index;
        Task task;

        try {
            index = command.getNumericData() - 1;
        } catch (NumberFormatException | NullPointerException e) {
            throw new ChatbotInvalidCommandFormatException((String.format(
                    "The command '%s' must be followed by a number representing the task number!",
                    command.getName()
            )));
        }

        try {
            task = taskManager.getTask(index);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatbotInvalidCommandFormatException(String.format(
                    "There is no task in the list numbered %d!",
                    index + 1
            ));
        }

        assert index >= 0 && index < taskManager.getTaskCount() : "The index shouldn't have been out of bounds.";
        assert task != null : "The task retrieved shouldn't have been null.";

        return new Pair<>(index, task);
    }

    /**
     * Obtains the given parameters and their corresponding values of the command, while throwing an exception if the
     * command doesn't have the given parameters.
     *
     * @param command The command to process.
     * @param parameters The parameters that should be guaranteed to exist.
     * @return A map that is guaranteed to have set all the given parameters.
     * @throws ChatbotCommandException if any of the parameters provided are missing.
     */
    private Map<String, String> getCommandParameterValues(
            ChatCommand command, String... parameters
    ) throws ChatbotCommandException {

        if (!command.hasParams(parameters)) {
            // Throw an error if there is missing parameters
            String commandName = command.getName();
            String commandParamDesc = StringFormatter.formatParameterListWithPlaceholdersToString(
                    parameters,
                    null
            );

            throw new ChatbotInvalidCommandFormatException(String.format(
                    "The command '%s' requires the parameters %s, but not all are present!",
                    commandName,
                    commandParamDesc
            ));
        }

        // Construct a map of all requested params and return it
        Map<String, String> parameterValueMap = new HashMap<>();
        for (String param : parameters) {
            parameterValueMap.put(param, command.getParam(param));
        }
        return parameterValueMap;
    }


    /**
     * Obtains the given parameters and their corresponding values of the command as Unix epoch timestamps from the
     * ISO8601 date format.
     *
     * @param command The command to process.
     * @param parameters The parameters that should be guaranteed to exist and converted to Unix epoch in seconds.
     * @return A map that is guaranteed to have set all the given parameters and corresponding Unix epoch.
     * @throws ChatbotCommandException if any of the parameters provided are missing.
     */
    private Map<String, Long> getCommandParameterValuesAsTimestamps(
            ChatCommand command, String... parameters
    ) throws ChatbotCommandException {

        if (!command.hasParamsWithUsefulValue(parameters)) {
            // Throw an error if there is missing parameters
            String commandName = command.getName();
            String commandParamDesc = StringFormatter.formatParameterListWithPlaceholderToString(
                    parameters,
                    "date"
            );

            throw new ChatbotInvalidCommandFormatException(String.format(
                    "The command '%s' requires parameters '%s', but not all are present!",
                    commandName,
                    commandParamDesc
            ));
        }

        // Construct a map of all requested params and return it
        Map<String, Long> parameterValueMap = new HashMap<>();
        for (String param : parameters) {
            long epoch;

            try {
                epoch = EpochConverter.getEpochFromIsoDateString(command.getParam(param));
            } catch (DateTimeParseException e) {
                throw new ChatbotInvalidCommandFormatException(
                        "The date supplied is invalid! They must be correct dates and follow the "
                                + "ISO8601 date format (yyyy-MM-dd or yyyy-MM-ddThh:mm).\n"
                                + "For example, 2023-01-31T12:34 is one such valid date.");
            }

            parameterValueMap.put(param, epoch);
        }
        return parameterValueMap;
    }




    private void processAddTodo(ChatCommand command) throws ChatbotCommandException {
        var taskTitle = this.getCommandStringData(command);

        var task = new Todo(taskTitle);
        this.taskManager.addTask(task);

        this.chatbot.sendMessageFromChatbot(String.format(
                "Got it. I've added this todo:\n  %s\nYou have %d tasks in your list now! :)",
                task,
                this.taskManager.getTaskCount()
        ));
    }

    private void processAddDeadline(ChatCommand command) throws ChatbotCommandException {
        var taskTitle = this.getCommandStringData(command);
        var taskDates = this.getCommandParameterValuesAsTimestamps(command, "by");

        var task = new Deadline(taskTitle, taskDates.get("by"));
        this.taskManager.addTask(task);

        this.chatbot.sendMessageFromChatbot(String.format(
                "Got it. I've added this deadline:\n  %s\nYou have %d tasks in your list now! :)",
                task,
                this.taskManager.getTaskCount()
        ));
    }

    private void processAddEvent(ChatCommand command) throws ChatbotCommandException {
        var taskTitle = this.getCommandStringData(command);
        var taskDates = this.getCommandParameterValuesAsTimestamps(command, "from", "to");

        var task = new Event(taskTitle, taskDates.get("from"), taskDates.get("to"));
        this.taskManager.addTask(task);

        this.chatbot.sendMessageFromChatbot(String.format(
                "Got it. I've added this event:\n  %s\nYou have %d tasks in your list now! :)",
                task,
                this.taskManager.getTaskCount()
        ));
    }

    private void processMarkComplete(boolean isCompleted, ChatCommand command) throws ChatbotCommandException {
        // Obtain a reference to the task.
        Task task = this.getCommandReferencedIndexTaskPair(command).getValue();

        // Assert that the task is not already the state that was requested.
        if (task.isCompleted() == isCompleted) {
            throw new ChatbotCommandException(isCompleted
                    ? "The task was already done!"
                    : "The task was already not done!");
        }

        // Mark the task as complete.
        task.setCompleted(isCompleted);

        // Send an update to the user.
        if (isCompleted) {
            this.chatbot.sendMessageFromChatbot(
                    String.format("Nice! I've marked this task as done:\n   %s", task)
            );
        } else {
            this.chatbot.sendMessageFromChatbot(
                    String.format("OK, I've marked this task as not done yet:\n   %s", task)
            );
        }
    }

    private void processDelete(ChatCommand command) throws ChatbotCommandException {
        // Perform the deletion.
        Pair<Integer, Task> indexTaskPair = this.getCommandReferencedIndexTaskPair(command);
        this.taskManager.removeTask(indexTaskPair.getKey());

        // Send an update to the user.
        this.chatbot.sendMessageFromChatbot(String.format(
                "Alright, I've deleted this task:\n   %s\nYou're left with %d tasks now! :)",
                indexTaskPair.getValue(),
                this.taskManager.getTaskCount()
        ));
    }

    private void processList(ChatCommand command) throws ChatbotCommandException {
        // Ensure that no extra data is supplied.
        this.assertCommandHasNoData(command);

        // Build the message from the list of tasks.
        StringBuilder builder = new StringBuilder();

        if (this.taskManager.getTaskCount() > 0) {
            builder.append("Here are your tasks, glhf! :)");
        } else {
            builder.append("Oh nice! You have no tasks! :>");
        }

        builder.append('\n');
        builder.append(StringFormatter.formatTaskIndexedStreamToString(
                this.taskManager.getTaskIndexedStream()
        ));

        // Send the message to the user.
        this.chatbot.sendMessageFromChatbot(builder.toString());
    }

    private void processSearch(ChatCommand command) throws ChatbotCommandException {
        // Obtain the data required for searching.
        String searchString = this.getCommandStringData(command);

        // Build the list of items found
        StringBuilder builder = new StringBuilder();

        builder.append("Alright, here's the matching tasks I found:");

        builder.append('\n');
        builder.append(StringFormatter.formatTaskIndexedStreamToString(
                this.taskManager.getTaskIndexedStream()
                        .filter(pair -> pair.getValue()
                                .getTitle()
                                .toLowerCase()
                                .contains(searchString.toLowerCase())
                        )
        ));

        builder.append("\nThat's it!");

        // Send the results to the user.
        this.chatbot.sendMessageFromChatbot(builder.toString());
    }

    private void processHelp(ChatCommand command) throws ChatbotCommandException {
        // Ensure that no extra data is supplied.
        this.assertCommandHasNoData(command);

        // Prepare the primary message from all available params.
        String primaryMessage;

        StringBuilder primaryMessageBuilder = new StringBuilder();
        primaryMessageBuilder.append(
                "No problem. Here's a summary of all possible command names and their functions!\n\n"
        );

        for (ChatCommand.Operation op : ChatCommand.Operation.values()) {
            var aliases = op.getSupportedNameAliases();
            var quotedAliases = Arrays.stream(aliases)
                    .map(s -> '"' + s + '"')
                    .collect(Collectors.toList());

            if (quotedAliases.size() == 0) {
                continue;
            }

            String commandTitle = String.join(", ", quotedAliases);
            String commandDescription = op.getDescription();
            String commandSyntax = String.format(op.getSyntaxDescriptionAsFormatString(), aliases[0]);

            primaryMessageBuilder.append(String.format("    %s\n", commandTitle));
            primaryMessageBuilder.append(String.format("        %s\n", commandDescription));
            primaryMessageBuilder.append(String.format("        Format: \"%s\"\n", commandSyntax));
            primaryMessageBuilder.append('\n');
        }

        primaryMessageBuilder.append(
                "Commands listed with multiple names above are synonyms and can be used interchangeably."
        );
        primaryMessage = primaryMessageBuilder.toString();

        // Prepare the supplementary message for extra hints.
        String footerMessage = "Hope the above helps! :) "
                        + "If you need additional information, please refer to the User Guide at "
                        + "https://wxwern.github.io/ip/";

        // Send the messages to the user.
        this.chatbot.sendMessageFromChatbot(primaryMessage);
        this.chatbot.sendMessageFromChatbot(footerMessage);
    }

    private void processExit(ChatCommand command) throws ChatbotCommandException {
        // Ensure that no extra data is supplied.
        this.assertCommandHasNoData(command);

        // Trigger a conversation closure.
        this.chatbot.closeConversation();
    }



}
