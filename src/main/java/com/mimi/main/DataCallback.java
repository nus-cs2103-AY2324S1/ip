package com.mimi.main;

/**
 * A method used to send data between the Ui and classes that implement this interface.
 */
@FunctionalInterface
public interface DataCallback {
    void onDataReceived(String data);
}
