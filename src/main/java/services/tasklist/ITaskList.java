package services.tasklist;

import command.CommandType;
import services.bizerrors.JarvisException;

public interface ITaskList {
    void add(String description, CommandType taskType, String... args) throws JarvisException;
    void delete(int taskNumber) throws JarvisException;
    void find(String keyword) throws JarvisException;
    void markDone(int taskNumber) throws JarvisException;
    void markUndone(int taskNumber) throws JarvisException;
    void show();
}
