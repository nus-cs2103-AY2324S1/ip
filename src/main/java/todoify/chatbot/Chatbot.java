package todoify.chatbot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import todoify.chatbot.exception.ChatbotException;
import todoify.chatbot.exception.ChatbotRuntimeException;
import todoify.taskmanager.TaskManager;
import todoify.taskmanager.task.Deadline;
import todoify.taskmanager.task.Event;
import todoify.taskmanager.task.Task;
import todoify.taskmanager.task.Todo;
import todoify.util.EpochConverter;
import todoify.util.events.EventEmitter;


/**
 * A chatbot to interact with.
 *
 * <p>
 * This class is an abstraction over a chatbot that also supplies methods to allow users to easily interact with the
 * bot, and for UI clients to subscribe to chat messages.
 * </p>
 */
public class Chatbot extends EventEmitter<ChatMessage> {

    /**
     * The default name of the chatbot.
     */
    public static final String DEFAULT_NAME = "Todoify";


    private final String name;
    private final ArrayList<ChatMessage> convoList;
    private final TaskManager taskManager;
    private boolean isClosed;


    /**
     * Creates a new chatbot with the given custom name. To start talking with it, call
     * {@link Chatbot#openConversation()}.
     *
     * @param name        The name of the chatbot. Uses the default if null.
     * @param taskManager The task manager the chatbot should utilise. Uses the default if null.
     */
    public Chatbot(String name, TaskManager taskManager) {
        this.name = name == null ? DEFAULT_NAME : name;
        this.convoList = new ArrayList<>();
        this.taskManager = taskManager == null ? new TaskManager() : taskManager;
        this.isClosed = true;
    }

    /**
     * Returns an iterable representing the current full conversation.
     *
     * @return An iterable of messages for the current conversation in chronological order.
     */
    public Iterable<ChatMessage> getConversation() {
        return this.convoList;
    }

    /**
     * Returns the name of the chatbot.
     *
     * @return The name of the chatbot as a String.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Starts the chatbot conversation. Messages may be sent to the chatbot after this.
     */
    public void openConversation() {
        if (!this.isClosed) {
            return;
        }
        this.isClosed = false;

        this.sendMessage(
                ChatMessage.SenderType.CHATBOT,
                String.format("Hello! I'm %s, your friendly task helper.", this.name)
        );

        try {
            this.taskManager.loadFromStorage();

            // If successful, prompt about current state.
            if (this.taskManager.getTaskCount() > 0) {
                this.sendMessage(
                        ChatMessage.SenderType.CHATBOT,
                        String.format("You've %d tasks in your list right now! :)", this.taskManager.getTaskCount())
                );
            } else {
                this.sendMessage(ChatMessage.SenderType.CHATBOT, "You have no tasks right now! :)");
            }

        } catch (FileNotFoundException e) {
            // Do nothing.
            this.sendMessage(ChatMessage.SenderType.CHATBOT, "You have no tasks right now! :)");

        } catch (IOException e) {
            // Warn about the error.
            this.sendMessage(ChatMessage.SenderType.CHATBOT, String.format(
                    "Sorry, I couldn't load your tasks.\nThe error was: [%s] %s\n"
                            + "I'll be starting from a blank slate instead.",
                    e.getClass().getSimpleName(),
                    e.getLocalizedMessage()
            ));
        }

        this.sendMessage(ChatMessage.SenderType.CHATBOT, "What can I do for you?");
    }

    /**
     * Ends the chatbot conversation. No new messages may be sent after this.
     */
    public void closeConversation() {
        if (this.isClosed) {
            return;
        }
        this.isClosed = true;
        this.sendMessage(ChatMessage.SenderType.CHATBOT, "Bye! Hope to see you again soon! ^-^");
    }

    /**
     * Returns whether a conversation with the chatbot is open, which means messages can be sent.
     *
     * @return true if the conversation is open, false otherwise.
     */
    public boolean isConversationOpen() {
        return !this.isClosed;
    }

    /**
     * Returns whether the conversation has clased, which means messages can no longer be sent.
     *
     * @return true if the conversation is closed, false otherwise.
     */
    public boolean isConversationClosed() {
        return this.isClosed;
    }

    /**
     * Method to send a message to the chatbot from the user.
     *
     * @param message The message string to send.
     * @return The resulting message sent.
     * @throws ChatbotRuntimeException if the conversation is closed.
     */
    public ChatMessage sendMessageFromUser(String message) {
        if (this.isClosed) {
            throw new ChatbotRuntimeException("Conversations are not open, so no messages may be sent!");
        }

        return this.sendMessage(ChatMessage.SenderType.USER, message);
    }


    /**
     * Internal method to send a message.
     *
     * @param message The message to send.
     * @return The resulting message sent.
     */
    private ChatMessage sendMessage(ChatMessage.SenderType sender, String message) {
        ChatMessage msg = new ChatMessage(sender, message);
        this.convoList.add(msg);
        this.processMessage(msg);
        return msg;
    }

    /**
     * Internal method to process newly received messages.
     *
     * @param message The message to process.
     */
    private void processMessage(ChatMessage message) {
        // Let's notify the listeners.
        this.fireEvent(message);

        if (message.getSenderType() == ChatMessage.SenderType.CHATBOT) {
            // For now, self messages need no further processing.
            return;
        }

        // Let's see what the other users send!
        final ChatCommand command = ChatCommand.parse(message.getMessage());
        this.processCommand(command);
    }


    /**
     * Internal method to process newly received commands.
     *
     * @param chatCommand The command to process.
     */
    private void processCommand(ChatCommand chatCommand) {
        try {
            boolean dataProcessed = false;
            switch (chatCommand.getOperation()) {
            case MARK_COMPLETE:
            case UNMARK_COMPLETE:
            case DELETE:
                dataProcessed = this.processCommandAssertNumericData(chatCommand);
                break;

            case ADD_TODO:
            case ADD_DEADLINE:
            case ADD_EVENT:
            case SEARCH:
                dataProcessed = this.processCommandAssertHasData(chatCommand);
                break;

            case LIST:
            case EXIT:
                dataProcessed = this.processCommandAssertNoData(chatCommand);
                break;

            case UNKNOWN:
            default:
                break;
            }
            if (!dataProcessed) {
                throw new ChatbotException("Sorry, idgi :(");
            }
        } catch (ChatbotException e) {
            this.sendMessage(ChatMessage.SenderType.CHATBOT, "Oops! " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
            this.sendMessage(ChatMessage.SenderType.CHATBOT, String.format(
                    "Oh no, something's wrong! [%s] %s",
                    e.getClass().getSimpleName(),
                    e.getLocalizedMessage()
            ));
        }

        try {
            this.taskManager.saveToStorage();
        } catch (IOException e) {
            this.sendMessage(
                    ChatMessage.SenderType.CHATBOT,
                    "Oops! I'm having problems saving your data to storage. Your data may not be preserved."
                            + String.format(
                            "The error was: [%s] %s",
                            e.getClass().getSimpleName(),
                            e.getLocalizedMessage()
                    )
            );
        }
    }

    /**
     * Internal method to process commands with numeric data as input.
     *
     * @param chatCommand The command to process.
     * @return `true` if the command was processed, `false` otherwise.
     * @throws ChatbotException if the data field does not have numeric data as input or any command-specific error.
     */
    private boolean processCommandAssertNumericData(ChatCommand chatCommand) throws ChatbotException {
        switch (chatCommand.getOperation()) {
        case MARK_COMPLETE:
        case UNMARK_COMPLETE:
        case DELETE:
            break;
        default:
            return false;
        }

        int index;
        Task task;

        // Process the input
        try {
            index = Integer.parseInt(chatCommand.getData()) - 1;
        } catch (NumberFormatException | NullPointerException e) {
            throw new ChatbotException(String.format(
                    "The command '%s' must be followed by a number representing the task number!",
                    chatCommand.getName()
            ));
        }

        try {
            task = this.taskManager.getTask(index);
        } catch (IndexOutOfBoundsException e) {
            throw new ChatbotException(String.format("There is no task in the list numbered %d!", index + 1));
        }

        // Let's see what we should do!
        if (chatCommand.getOperation() == ChatCommand.Operation.DELETE) {

            // Delete the task accordingly. We already checked the index so it should be correct.
            this.taskManager.removeTask(index);

            // Send an appropriate reply.
            this.sendMessage(ChatMessage.SenderType.CHATBOT, String.format(
                    "Alright, I've deleted this task:\n   %s\nYou're left with %d tasks now! :)",
                    task,
                    this.taskManager.getTaskCount()
            ));

        } else {

            // Mark the task as done or not accordingly
            boolean completed = chatCommand.getOperation() == ChatCommand.Operation.MARK_COMPLETE;
            if (task.isCompleted() == completed) {
                throw new ChatbotException(completed ? "The task was already done!" : "The task was already not done!");
            }
            task.setCompleted(completed);

            // Send an appropriate reply
            if (completed) {
                this.sendMessage(
                        ChatMessage.SenderType.CHATBOT,
                        String.format("Nice! I've marked this task as done:\n   %s", task)
                );
            } else {
                this.sendMessage(
                        ChatMessage.SenderType.CHATBOT,
                        String.format("OK, I've marked this task as not done yet:\n   %s", task)
                );
            }

        }

        return true;
    }

    /**
     * Internal method to process commands with no data nor parameters as input.
     *
     * @param chatCommand The command to process.
     * @return `true` if the command was processed, `false` otherwise.
     * @throws ChatbotException if the data field in fact has data as input or any command-specific error.
     */
    private boolean processCommandAssertNoData(ChatCommand chatCommand) throws ChatbotException {
        switch (chatCommand.getOperation()) {
        case LIST:
        case EXIT:
            break;
        default:
            return false;
        }

        if (!chatCommand.getData().isBlank() || chatCommand.hasParams()) {
            throw new ChatbotException(String.format(
                    "Hmm, the command '%s' should not have anything following it. Is that a typo?",
                    chatCommand.getName()
            ));
        }

        switch (chatCommand.getOperation()) {
        case LIST:
            StringBuilder builder = new StringBuilder();

            if (this.taskManager.getTaskCount() > 0) {
                builder.append("Here are your tasks, glhf! :)");
            } else {
                builder.append("Oh nice! You have no tasks! :>");
            }

            int count = 1;
            for (Task task : this.taskManager.getTasks()) {
                builder.append("\n");
                builder.append(count);
                builder.append(". ");
                builder.append(task.toString());
                count++;
            }

            this.sendMessage(ChatMessage.SenderType.CHATBOT, builder.toString());
            break;

        case EXIT:
            this.closeConversation();
            break;

        default:
            break;
        }

        return true;
    }


    /**
     * Internal method to process commands that contain some data as input.
     *
     * @param chatCommand The command to process.
     * @return `true` if the command was processed, `false` otherwise.
     * @throws ChatbotException if the data field does not have data as input or any command-specific error.
     */
    private boolean processCommandAssertHasData(ChatCommand chatCommand) throws ChatbotException {
        switch (chatCommand.getOperation()) {
        case ADD_TODO:
        case ADD_DEADLINE:
        case ADD_EVENT:
        case SEARCH:
            break;
        default:
            return false;
        }

        if (chatCommand.getData().isBlank()) {
            throw new ChatbotException(String.format(
                    "The command '%s' requires some title content after the command name, but none was found!",
                    chatCommand.getName()
            ));
        }

        // Create the appropriate task
        Task newTask = null;
        switch (chatCommand.getOperation()) {
        case ADD_TODO:
            newTask = new Todo(chatCommand.getData());
            break;

        case ADD_DEADLINE:
            if (!chatCommand.hasParamWithUsefulValue("by")) {
                throw new ChatbotException(String.format(
                        "The 'deadline' command requires supplying '%sby <deadline>'!",
                        ChatCommand.PARAMETER_PREFIX
                ));
            }

            long byTimestamp;
            try {
                byTimestamp = EpochConverter.getEpochFromIsoDateString(chatCommand.getParam("by"));
            } catch (DateTimeParseException e) {
                throw new ChatbotException("The deadline supplied is invalid! It must be a correct date and follow the "
                        + "ISO8601 date format (yyyy-MM-dd or yyyy-MM-ddThh:mm).\n"
                        + "For example, 2023-01-31T12:34 is one such valid date.");
            }

            newTask = new Deadline(chatCommand.getData(), byTimestamp);
            break;

        case ADD_EVENT:
            if (!chatCommand.hasParamWithUsefulValue("from") || !chatCommand.hasParamWithUsefulValue("to")) {

                throw new ChatbotException(String.format(
                        "The 'event' command requires supplying both '%sfrom <date>' and '%sto <date>'!",
                        ChatCommand.PARAMETER_PREFIX,
                        ChatCommand.PARAMETER_PREFIX
                ));
            }

            long startTimestamp;
            long endTimestamp;
            try {
                startTimestamp = EpochConverter.getEpochFromIsoDateString(chatCommand.getParam("from"));
                endTimestamp = EpochConverter.getEpochFromIsoDateString(chatCommand.getParam("to"));
            } catch (DateTimeParseException e) {
                throw new ChatbotException(
                        "The date range supplied is invalid! They must be correct dates and follow the "
                                + "ISO8601 date format (yyyy-MM-dd or yyyy-MM-ddThh:mm:ss).\n"
                                + "For example, 2023-01-31T12:34 is one such valid date.");
            }

            newTask = new Event(chatCommand.getData(), startTimestamp, endTimestamp);
            break;

        case SEARCH:
            StringBuilder builder = new StringBuilder();

            builder.append("Alright, here's the matching tasks I found:");

            int count = 1;
            for (Task task : this.taskManager.getTasks()) {
                if (task.getTitle().toLowerCase().contains(chatCommand.getData().toLowerCase())) {
                    builder.append("\n");
                    builder.append(count);
                    builder.append(". ");
                    builder.append(task);
                }
                count++;
            }

            builder.append("\nThat's it!");

            this.sendMessage(ChatMessage.SenderType.CHATBOT, builder.toString());
            break;

        default:
            throw new ChatbotException("Unexpected internal error: task type was not implemented.");
        }

        // Add the task created
        if (newTask != null) {
            this.taskManager.addTask(newTask);
            this.sendMessage(ChatMessage.SenderType.CHATBOT, String.format(
                    "Got it. I've added this task:\n  %s\nYou have %d tasks in your list now! :)",
                    newTask,
                    this.taskManager.getTaskCount()
            ));
        }

        return true;
    }


}
