import java.util.ArrayList;

/**
 * A chatbot to interact with. Provides methods to allow for users to easily
 * interact with the bot and for clients to subscribe to chat messages.
 */
public class Chatbot extends EventEmitter<ChatMessage> {

    /** The default name of the chatbot. */
    public static String DEFAULT_NAME = "Todoify";

    /**
     * Returns a global singleton for the chatbot with the default name.
     * To start talking with it, call {@link Chatbot#openConversation()}.
     */
    public static Chatbot getInstance() {
        return new Chatbot(Chatbot.DEFAULT_NAME);
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
            switch (command.getName()) {
                case "mark":
                case "unmark":
                case "delete":
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
                        if (command.getName().equals("delete")) {

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
                            boolean completed = command.getName().equals("mark");
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

                case "todo":
                case "deadline":
                case "event":
                    if (!command.getData().isBlank()) {
                        TaskManager.Task task = null;
                        switch (command.getName()) {
                            case "todo":
                                task = new TaskManager.Todo(command.getData());
                                break;
                            case "deadline":
                                if (!command.hasParamWithUsefulValue("by")) {
                                    throw new ChatbotException(
                                            "The 'deadline' command requires supplying '/by <deadline>'!"
                                    );
                                }
                                task = new TaskManager.Deadline(
                                        command.getData(),
                                        command.getParam("by")
                                );
                                break;
                            case "event":
                                if (!command.hasParamWithUsefulValue("from") ||
                                        !command.hasParamWithUsefulValue("to")) {
                                    throw new ChatbotException(
                                            "The 'event' command requires supplying both '/from <date>' and '/to <date>'!"
                                    );
                                }
                                task = new TaskManager.Event(
                                        command.getData(),
                                        command.getParam("from"),
                                        command.getParam("to")
                                );
                                break;
                        }
                        if (task != null) {
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
                            throw new ChatbotException("Unexpected error occurred - task could not be created.");
                        }
                    } else {
                        throw new ChatbotException(String.format(
                                "The command '%s' to create a task requires some title content, but none was found!",
                                command.getName()
                        ));
                    }
                    break;

                case "list":
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
                case "bye":
                    if (command.getData().isBlank()) {
                        this.closeConversation();
                        break;
                    }
                    throw new ChatbotException(FAILURE_MESSAGE_REPLY);
                default:
                    throw new ChatbotException(FAILURE_MESSAGE_REPLY);
            }
        } catch (ChatbotException e) {
            this.sendMessage(ChatMessage.SenderType.CHATBOT, "Oops! " + e.getMessage());
        } catch (Exception e) {
            this.sendMessage(ChatMessage.SenderType.CHATBOT, "Oh no, something unexpectedly went wrong! :(");
        }
    }


}
