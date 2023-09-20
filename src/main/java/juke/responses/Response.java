package juke.responses;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import juke.commons.classes.JukeObject;
import juke.ui.components.DialogBox;

/**
 * Represents the two-sided conversation between Juke and the user. Future implementation of this class
 * may see the use of a history list to capture more conversations between the user and Juke.
 */
public class Response extends JukeObject {
    /** Represents a series of Dialogs to be printed */
    private final List<Dialog> messages;

    /**
     * Constructs an instance of {@code Response}. This method is made private to
     * prevent illegal instantiation from the user.
     */
    private Response() {
        this.messages = new LinkedList<>();
    }

    /**
     * Constructs an instance of {@code Response}. This method is made private to
     * prevent illegal instantiation from the user.
     */
    private Response(List<Dialog> withDialogs) {
        this.messages = new LinkedList<>(withDialogs);
    }

    /**
     * Constructs an instance of {@code Response}. This method is made private to
     * prevent illegal instantiation from the user.
     *
     * @param inputDialog Input dialog message to display
     */
    private Response(Dialog... inputDialog) {
        this();
        this.messages.addAll(List.of(inputDialog));
    }

    /**
     * Returns a {@code Response} with no input or output messages.
     *
     * @return {@code Response} with no input or output messages
     */
    public static Response of() {
        return new Response();
    }

    /**
     * Returns a {@code Response} with existing inputs.
     *
     * @return {@code Response} object pre-populated with the dialog inputs
     */
    public static Response of(Dialog... dialogs) {
        return new Response(dialogs);
    }

    /**
     * Returns the list of dialogs mapped into its corresponding {@code DialogBox} object.
     *
     * @return {@code List} of {@code DialogBox}
     */
    public List<DialogBox> getDialogBoxes() {
        return this.messages.stream().map(Dialog::getDialogBoxRepresentation).collect(Collectors.toList());
    }

    /**
     * Composes the input {@code Dialog} with the existing {@code Dialogs}, and
     * return a new instance of {@code Response} with the input {@code Dialog}.
     *
     * @param inputDialog Input {@code Dialog} object
     * @return {@code Response} object
     */
    public Response with(Dialog inputDialog) {
        List<Dialog> composedDialogs = new LinkedList<>(this.messages);
        composedDialogs.add(inputDialog);
        return new Response(composedDialogs);
    }
}
