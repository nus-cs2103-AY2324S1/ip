package com.nyanbot.duketasks;

/**
 * Encapsulates an invalid class.
 *
 * @author Tan Kerway
 */
public class InvalidTask extends Task {
    public InvalidTask(String errorMessage) {
        super(errorMessage, true, false);
    }
}
