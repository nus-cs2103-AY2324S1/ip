package todoify.stdio;

import java.util.Scanner;

import todoify.chatbot.ChatMessage;
import todoify.chatbot.Chatbot;

/**
 * The chatbot CLI entry-point, which provides an interactive input prompt via the CLI.
 *
 * <p>
 * The default name for the chatbot is specified at {@link todoify.chatbot.Chatbot#DEFAULT_NAME}. Custom names can be
 * set by modifying the {@link MainTextUi#run} implementation here.
 * </p>
 */
public class MainTextUi {

    private static final String CHATBOT_PADDING = "  ";
    private static final String USER_PADDING = " > ";

    /**
     * Runs the TUI with the given program arguments.
     *
     * @param args The program arguments.
     */
    public static void run(String[] args) {

        // Initialise with default settings.
        Chatbot chatbot = new Chatbot(null, null);

        // Perform initial setup to listen to new messages.
        chatbot.addEventListener(message -> {
            if (message.getSenderType() == ChatMessage.SenderType.CHATBOT) {
                System.out.printf("[%s]:\n", chatbot.getName());
                System.out.println(
                        CHATBOT_PADDING
                                + message.getMessage().replaceAll("\n", "\n" + CHATBOT_PADDING)
                );
                System.out.println();
            }
        });

        // Start processing standard input to feed into the chatbot.
        Scanner scanner = new Scanner(System.in);

        chatbot.openConversation();

        while (chatbot.isConversationOpen()) {
            System.out.println("[You]:");
            System.out.print(USER_PADDING);
            System.out.flush();

            if (!scanner.hasNextLine()) {
                break;
            }

            String line = scanner.nextLine();
            System.out.println();

            chatbot.sendMessageFromUser(line);
        }

        chatbot.closeConversation();
        scanner.close();

    }
}
