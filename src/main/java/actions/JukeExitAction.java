package main.java.actions;

import java.util.Optional;

/**
 * Action that is invoked when the user wishes to quit the assistant.
 */
public class JukeExitAction extends JukeAction {
    /** The farewell statement used by the assistant when the user decides to quit the assistant. */
    private static final String EXIT = "Goodbye!";

    /**
     * JukeExitAction Constructor.
     */
    public JukeExitAction() {}

    /**
     * Necessary method that is invoked when the action is carried out.
     * @return Optional<? extends JukeAction> object, which contains further action objects,
     * made this way to ensure that actions can call other actions and thus lead to chains
     * of actions for added complexity
     */
    @Override
    public Optional<? extends JukeAction> complete() {
        System.out.print(EXIT);
        return Optional.empty();
    }
}
