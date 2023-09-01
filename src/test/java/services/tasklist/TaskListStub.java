package services.tasklist;

import command.CommandType;

import java.util.Arrays;

public class TaskListStub implements ITaskList {

    protected String status;

    public TaskListStub() {
        status = "";
    }

    @Override
    public void add(String description, CommandType taskType, String... args) {
        status = "add method called with description: " + description
                + " and taskType: " + taskType + " and args: " + Arrays.toString(args) + ".";
    }

    @Override
    public void delete(int taskNumber) {
        status = "delete method called with taskNumber: " + taskNumber + ".";
    }

    @Override
    public void find(String keyword) {
        status = "find method called with keyword: " + keyword + ".";
    }

    @Override
    public void markDone(int taskNumber) {
        status = "markDone method called with taskNumber: " + taskNumber + ".";
    }

    @Override
    public void markUndone(int taskNumber) {
        status = "markUndone method called with taskNumber: " + taskNumber + ".";
    }

    @Override
    public void show() {
        status = "show method called";
    }

    public String getStatus() {
        return status;
    }
}
