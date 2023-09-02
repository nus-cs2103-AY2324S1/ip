package juke.responses;

import juke.core.JukeObject;

/**
 * Simple class that represents the responses from Juke or the user.
 */
public class Response extends JukeObject {
    /** Represents the input message from the user. */
    private final String inputMessage;

    /** Represents the output message from Juke. */
    private final String outputMessage;

    /**
     * Constructs an instance of {@code Response}.
     *
     * @param inputMessage User input
     * @param outputMessage Juke output
     */
    public Response(String inputMessage, String outputMessage) {
        this.inputMessage = inputMessage;
        this.outputMessage = outputMessage;
    }

    /**
     * Returns a {@code Response} containing the response from the user.
     *
     * @param inputMessage User response
     * @return {@code Response} containing the response from the user
     */
    public static Response ofUser(String inputMessage) {
        return new Response(inputMessage, null);
    }

    /**
     * Returns a {@code Response} containing the response from Juke.
     *
     * @param outputMessage Juke response
     * @return {@code Response} containing the response from Juke
     */
    public static Response ofJuke(String outputMessage) {
        return new Response(null, outputMessage);
    }

    /**
     * Composes a {@code Response} with the user's input message. If called with a Response object that
     * already has a user input, then the user input will be overwritten.
     *
     * @param inputMessage User input
     * @return {@code Response} with the user's input message
     */
    public Response withUser(String inputMessage) {
        return new Response(inputMessage, this.outputMessage);
    }

    /**
     * Composes a {@code Response} with Juke's output message. If called with a Response object that
     * already has a Juke output, then the Juke output will be overwritten.
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
