# URBOI Chatbot User Guide

## Introduction

URBOI    is a simple command-line chatbot designed to help you manage your tasks. It allows you to add, list, mark as done, delete, and search for tasks. This user guide will provide you with step-by-step instructions on how to use URBOI effectively.

## Table of Contents
- [Getting Started](#getting-started)
- [Adding Tasks](#adding-tasks)
    - [Adding a To-Do](#adding-a-to-do)
    - [Adding a URBOI_PACKIN.TaskTypes.Deadline](#adding-a-deadline)
    - [Adding an URBOI_PACKIN.TaskTypes.Event](#adding-an-event)
- [Listing Tasks](#listing-tasks)
- [Marking Tasks as Done](#marking-tasks-as-done)
- [Deleting Tasks](#deleting-tasks)
- [Searching for Tasks](#searching-for-tasks)
- [Exiting URBOI_PACKIN.ResponseController](#exiting-duke)
- [Saving Your Tasks](#saving-your-tasks)

## Getting Started<a name="getting-started"></a>

1. Clone or download the URBOI project code from [GitHub](https://github.com/TyrusLye/ip/releases/tag/A-Release).
2. Run the `URBOI.java` file to start the URBOI_PACKIN.ResponseController chatbot.

## Adding Tasks<a name="adding-tasks"></a>

URBOI_PACKIN.ResponseController allows you to add three types of tasks: To-Do, URBOI_PACKIN.TaskTypes.Deadline, and URBOI_PACKIN.TaskTypes.Event.

### Adding a To-Do<a name="adding-a-to-do"></a>

To add a To-Do task, use the following command:

```plaintext
todo [description]
```

Replace `[task description]` with a brief description of the to-do task you want to add.

### Adding a URBOI_PACKIN.TaskTypes.Deadline <a name="adding-a-deadline"></a>

To add a deadline task, use the following command:

```plaintext
deadline [task description] /by [date and time]
```

Replace `[task description]` with a description of the deadline task and `[date and time]` with the date and time the task is due in the format `d/M/yyyy HHmm`.

### Adding an URBOI_PACKIN.TaskTypes.Event <a name="adding-an-event"></a>

To add an event task, use the following command:

```plaintext
event [event description] /from [start time] /to [end time]
```

Replace `[event description]` with a description of the event, `[start time]` with the event's start time, and `[end time]` with the event's end time.

## 3. Listing Tasks <a name="listing-tasks"></a>

To see a list of all your tasks, simply enter:

```plaintext
list
```


## 4. Marking Tasks <a name="marking-tasks"></a>

You can mark tasks as done or undone using the following commands:

- To mark a task as done, use:

```plaintext
mark [task index]
```

Replace `[task index]` with the index of the task you want to mark as done.

- To mark a task as not done (undone), use:

```plaintext
unmark [task index]
```

Replace `[task index]` with the index of the task you want to mark as not done.

## 5. Deleting Tasks <a name="deleting-tasks"></a>

You can delete a task from your list using the following command:

```plaintext
delete [task index]
```

Replace `[task index]` with the index of the task you want to delete.

## 6. Searching for Tasks <a name="searching-for-tasks"></a>

To search for tasks containing a specific keyword, use the following command:

```plaintext
find [keyword]
```

Replace `[keyword]` with the keyword you want to search for. URBOI_PACKIN.ResponseController will display a list of tasks matching your keyword.

## 7. Exiting URBOI_PACKIN.ResponseController <a name="exiting-duke"></a>

To exit URBOI_PACKIN.ResponseController, simply enter:

```plaintext
bye
```

URBOI_PACKIN.ResponseController will bid you farewell and close.