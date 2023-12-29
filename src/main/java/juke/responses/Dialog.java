package juke.responses;

import juke.ui.components.DialogBox;
import juke.ui.components.JukeDialogBox;
import juke.ui.components.UserDialogBox;

/**
 * Defines an abstract class that encapsulates the dialog of the user and Juke.
 */
public abstract class Dialog {
    /** Represents the dialog text to be printed. */
    private final String dialogText;

    /**
     * Constructs a {@code Dialog} object with the necessary dialog text.
     *
     * @param text Input dialog text
     */
    public Dialog(String text) {
        this.dialogText = text;
    }

    /**
     * Constructs a {@code Dialog} object that corresponds to the user's inputs.
     *
     * @param text Input dialog text
     */
    public static Dialog ofUser(String text) {
        return new UserDialog(text);
    }

    /**
     * Constructs a {@code Dialog} object that corresponds to Juke's inputs.
     *
     * @param text Input dialog text
     */
    public static Dialog ofJuke(String text) {
        return new JukeDialog(text);
    }

    /**
     * Returns the text associated with this {@code Dialog} object.
     *
     * @return String representing the dialog contained in this object
     */
    public String getDialogText() {
        return dialogText;
    }

    /**
     * Returns the {@code DialogBox} representation of this {@code Dialog} object.
     *
     * @return String representing the dialog contained in this object
     */
    public abstract DialogBox getDialogBoxRepresentation();

    /**
     * Static nested class that represents a User dialog.
     */
    private static class UserDialog extends Dialog {
        /**
         * Constructs a {@code UserDialog} object.
         *
         * @param text Input dialog text
         */
        public UserDialog(String text) {
            super(text);
        }

        /**
         * Returns the {@code UserDialogBox} representation of this {@code UserDialog} object.
         *
         * @return String representing the dialog contained in this object
         */
        @Override
        public DialogBox getDialogBoxRepresentation() {
            return new UserDialogBox(this);
        }
    }

    /**
     * Static nested class that represents a Juke dialog.
     */
    private static class JukeDialog extends Dialog {
        /**
         * Constructs a {@code JukeDialog} object.
         *
         * @param text Input dialog text
         */
        public JukeDialog(String text) {
            super(text);
        }

        /**
         * Returns the {@code JukeDialogBox} representation of this {@code UserDialog} object.
         *
         * @return String representing the dialog contained in this object
         */
        @Override
        public DialogBox getDialogBoxRepresentation() {
            return new JukeDialogBox(this);
        }
    }
}
