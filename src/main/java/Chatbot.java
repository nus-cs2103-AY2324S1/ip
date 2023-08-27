import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * A chatbot to interact with. Provides methods to allow for users to easily
 * interact with the bot and for UI clients to subscribe to chat messages.
 */
public class Chatbot extends EventEmitter<ChatMessage> {

    /** The default name of the chatbot. */
    public static String DEFAULT_NAME = "Todoify";


    private static Chatbot sharedInstance = null;
    /**
     * Returns the global singleton for the chatbot with the default name.
     * To start talking with it, call {@link Chatbot#openConversation()}.
     */
    public static Chatbot getSharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new Chatbot(Chatbot.DEFAULT_NAME);
        }
        return sharedInstance;
    }


    private String name;
    private ArrayList<ChatMessage> convoList = new ArrayList<>();
    private TaskManager taskManager = new TaskManager();
    private boolean closed = true;



    /**
     * Creates a new chatbot with the given custom name.
     * To start talking with it, call {@link Chatbot#openConversation()}.
     *
     * @param name The name of the chatbot.
     */
    public Chatbot(String name) {
        this.name = name;
    }

    /**
     * Obtains an iterable representing the current full conversation.
     * @return An iterable of messages for the current conversation in chronological order.
     */
    public Iterable<ChatMessage> getConversation() {
        return convoList;
    }

    /**
     * Starts the chatbot conversation. Messages may be sent to the chatbot after this.
     */
    public void openConversation() {
        if (!this.closed) {
            return;
        }
        this.closed = false;
        this.sendMessage(ChatMessage.SenderType.CHATBOT, String.format("Hello! I'm %s!\nWhat can I do for you?", this.name));
    }

    /**
     * Ends the chatbot conversation. No new messages may be sent after this.
     */
    public void closeConversation() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.sendMessage(ChatMessage.SenderType.CHATBOT, "Bye! Hope to see you again soon! ^-^");
    }

    /**
     * Returns whether a conversation with the chatbot is open, which means messages can be sent.
     * @return true if the conversation is open, false otherwise.
     */
    public boolean isConversationOpen() {
        return !this.closed;
    }

    /**
     * Returns whether the conversation has clased, which means messages can no longer be sent.
     * @return true if the conversation is closed, false otherwise.
     */
    public boolean isConversationClosed() {
        return this.closed;
    }

    /**
     * Method to send a message to the chatbot from the user.
     * @param message The message string to send.
     * @return The resulting message sent.
     * @throws ChatbotRuntimeException if the conversation is closed.
     */
    public ChatMessage sendMessageFromUser(String message) {
        if (this.closed) {
            throw new ChatbotRuntimeException("Conversations are not open, so no messages may be sent!");
        }

        return this.sendMessage(ChatMessage.SenderType.USER, message);
    }




    /**
     * Internal method to send a message.
     * @param message The message to send.
     * @return The resulting message sent.
     */
    private ChatMessage sendMessage(ChatMessage.SenderType sender, String message) {
        ChatMessage msg = new ChatMessage(sender, message);
        convoList.add(msg);
        this.processMessage(msg);
        return msg;
    }

    /**
     * Internal method to process newly received messages.
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
        final String FAILURE_MESSAGE_REPLY = "Sorry, idgi :(";
        final Command command = Command.parse(message.getMessage());

        try {
            switch (command.getOperation()) {
                case MarkComplete:
                case UnmarkComplete:
                case Delete:
                    if (command.getData() != null) {
                        int index;
                        TaskManager.Task task;

                        // Process the input
                        try {
                            index = Integer.parseInt(command.getData()) - 1;
                        } catch (NumberFormatException e) {
                            throw new ChatbotException(String.format(
                                    "The command '%s' must be followed by a number representing the task number!",
                                    command.getName()
                            ));
                        }

                        try {
                            task = this.taskManager.getTask(index);
                        } catch (IndexOutOfBoundsException e) {
                            throw new ChatbotException(String.format(
                                    "There is no task in the list numbered %d!",
                                    index + 1
                            ));
                        }

                        // Let's see what we should do!
                        if (command.getOperation() == Command.Operation.Delete) {

                            // Delete the task accordingly. We already checked the index so it should be correct.
                            this.taskManager.removeTask(index);

                            // Send an appropriate reply.
                            this.sendMessage(
                                    ChatMessage.SenderType.CHATBOT,
                                    String.format(
                                            "Alright, I've deleted this task:\n   %s\nYou're left with %d tasks now! :)",
                                            task,
                                            this.taskManager.getTaskCount()
                                    )
                            );

                        } else {

                            // Mark the task as done or not accordingly
                            boolean completed = command.getOperation() == Command.Operation.MarkComplete;
                            if (task.isCompleted() == completed) {
                                throw new ChatbotException(
                                        completed ? "The task was already done!" : "The task was already not done!"
                                );
                            }
                            task.markCompleted(completed);

                            // Send an appropriate reply
                            if (completed) {
                                this.sendMessage(
                                        ChatMessage.SenderType.CHATBOT,
                                        String.format("Nice! I've marked this task as done:\n   %s", task.toString())
                                );
                            } else {
                                this.sendMessage(
                                        ChatMessage.SenderType.CHATBOT,
                                        String.format("OK, I've marked this task as not done yet:\n   %s", task.toString())
                                );
                            }

                        }
                    }
                    break;

                case AddTodo:
                case AddDeadline:
                case AddEvent:
                    if (!command.getData().isBlank()) {

                        // Create the appropriate task
                        TaskManager.Task task = null;
                        switch (command.getOperation()) {
                            case AddTodo:
                                task = new TaskManager.Todo(command.getData());
                                break;

                            case AddDeadline:
                                if (!command.hasParamWithUsefulValue("by")) {
                                    throw new ChatbotException(
                                            "The 'deadline' command requires supplying '/by <deadline>'!"
                                    );
                                }

                                long byTimestamp;
                                try {
                                    byTimestamp = EpochConverter.getEpochFromISODateString(command.getParam("by"));
                                } catch (DateTimeParseException e) {
                                    throw new ChatbotException(
                                            "The deadline supplied is invalid! It must be a correct date and follow the " +
                                                    "ISO8601 date format (yyyy-MM-dd or yyyy-MM-ddThh:mm).\n" +
                                                    "For example, 2023-01-31T12:34 is one such valid date."
                                    );
                                }

                                task = new TaskManager.Deadline(
                                        command.getData(),
                                        byTimestamp
                                );
                                break;

                            case AddEvent:
                                if (!command.hasParamWithUsefulValue("from") ||
                                        !command.hasParamWithUsefulValue("to")) {
                                    throw new ChatbotException(
                                            "The 'event' command requires supplying both '/from <date>' and '/to <date>'!"
                                    );
                                }

                                long startTimestamp;
                                long endTimestamp;
                                try {
                                    startTimestamp = EpochConverter.getEpochFromISODateString(command.getParam("from"));
                                    endTimestamp = EpochConverter.getEpochFromISODateString(command.getParam("to"));
                                } catch (DateTimeParseException e) {
                                    throw new ChatbotException(
                                            "The date range supplied is invalid! They must be correct dates and follow the " +
                                                    "ISO8601 date format (yyyy-MM-dd or yyyy-MM-ddThh:mm:ss).\n" +
                                                    "For example, 2023-01-31T12:34 is one such valid date."
                                    );
                                }

                                task = new TaskManager.Event(
                                        command.getData(),
                                        startTimestamp,
                                        endTimestamp
                                );
                                break;

                            default:
                                throw new ChatbotException("Unexpected internal error: task type was not implemented.");
                        }

                        // Add the task created
                        this.taskManager.addTask(task);
                        this.sendMessage(
                                ChatMessage.SenderType.CHATBOT,
                                String.format(
                                        "Got it. I've added this task:\n  %s\nYou have %d tasks in your list now! :)",
                                        task,
                                        this.taskManager.getTaskCount()
                                )
                        );

                    } else {
                        throw new ChatbotException(String.format(
                                "The command '%s' to create a task requires some title content, but none was found!",
                                command.getName()
                        ));
                    }
                    break;

                case List:
                    if (command.getData().isBlank()) {
                        StringBuilder builder = new StringBuilder();

                        if (this.taskManager.getTaskCount() > 0) {
                            builder.append("Here are your tasks, glhf! :)");
                        } else {
                            builder.append("Oh nice! You have no tasks! :>");
                        }

                        int count = 1;
                        for (TaskManager.Task task : this.taskManager.getTasks()) {
                            builder.append("\n");
                            builder.append(count);
                            builder.append(". ");
                            builder.append(task.toString());
                            count++;
                        }
                        this.sendMessage(ChatMessage.SenderType.CHATBOT, builder.toString());
                        break;
                    }
                    throw new ChatbotException(FAILURE_MESSAGE_REPLY);
                case Exit:
                    if (command.getData().isBlank()) {
                        this.closeConversation();
                        break;
                    }
                    throw new ChatbotException(FAILURE_MESSAGE_REPLY);
                case Unknown:
                default:
                    throw new ChatbotException(FAILURE_MESSAGE_REPLY);
            }
        } catch (ChatbotException e) {
            this.sendMessage(ChatMessage.SenderType.CHATBOT, "Oops! " + e.getMessage());
        } catch (Exception e) {
            this.sendMessage(ChatMessage.SenderType.CHATBOT, "Oh no, something unexpectedly went wrong! The internal error was: " + e.getLocalizedMessage());
        }
    }


}
