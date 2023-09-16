package juke.responses;

import juke.Juke;
import juke.commons.classes.JukeObject;
import juke.commons.utils.StringUtils;

/**
 * Represents the two-sided conversation between Juke and the user. Future implementation of this class
 * may see the use of a history list to capture more conversations between the user and Juke.
 */
public class Response extends JukeObject {
    /** Represents the input message from the user. */
    private final String inputMessage;

    /** Represents the output message from Juke. */
    private final String outputMessage;

    /**
     * Constructs an instance of {@code Response}. This method is made private to
     * prevent illegal instantiation from the user.
     *
     * @param inputMessage String representing user input
     * @param outputMessage String representing Juke output
     */
    private Response(String inputMessage, String outputMessage) {
        this.inputMessage = inputMessage;
        this.outputMessage = outputMessage;
    }

    /**
     * Returns a {@code Response} containing the inputs from the user.
     * Note that the user input must be wrapped as the actions do not automatically wrap the
     * user input.
     *
     * @param inputMessage User response
     * @return {@code Response} containing the inputs from the user
     */
    public static Response ofUser(String inputMessage) {
        return new Response(StringUtils.wrap(inputMessage, Juke.MAX_STRING_LENGTH), null);
    }

    /**
     * Composes a {@code Response} with Juke's output message. Be warned that if
     * this method is called with a Response object that already has a Juke output,
     * then the previous Juke output will be overwritten. This behaviour may be changed
     * in a future implementation of the Response class.
     *
     * @param outputMessage Juke output
     * @return {@code Response} with Juke's output message
     */
    public Response withJuke(String outputMessage) {
        return new Response(this.inputMessage, outputMessage);
    }

    /**
     * Returns a {@code Response} with no input or output messages.
     *
     * @return {@code Response} with no input or output messages
     */
    public static Response of() {
        return new Response(null, null);
    }

    /**
     * Returns the input message from the user.
     *
     * @return String representing the input message from the user
     */
    public String getInputMessage() {
        return this.inputMessage;
    }

    /**
     * Returns the output message from Juke.
     *
     * @return String representing the output message from Juke
     */
    public String getOutputMessage() {
        return this.outputMessage;
    }
}
