package com.mimi.main;

/**
 * An interface used to send data between the Ui and classes that implement this interface.
 *
 * @author Yuheng
 */
@FunctionalInterface
public interface DataCallback {
    void onDataReceived(String data);
}
