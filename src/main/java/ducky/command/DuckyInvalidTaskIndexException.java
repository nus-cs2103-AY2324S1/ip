package ducky.command;

import ducky.DuckyException;

public class DuckyInvalidTaskIndexException extends DuckyException {

    public DuckyInvalidTaskIndexException(int index, int size) {
        super(String.format("Could not update task %d! There are %d tasks available.", index, size));
    }
}
