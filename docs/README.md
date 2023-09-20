# User Guide
**Rion** is a chatbot that helps to keep track of tasks (To-Do, Deadlines, Events), while also providing various features to help in the managing of tasks.

## Getting started

1. Ensure you have Java 11 installed on your device.
1. Download `duke.jar` v0.2 from [this repository](https://github.com/rionshocker/ip).
1. Navigate to the directory containing `duke.jar`.
1. Run the command in terminal/command prompt: ```java -jar duke.jar```
1. Enjoy using Rion!

## Features 

### Adding of tasks

Tasks can be easily added by using the keywords - **todo, deadline, event**.

To add a ToDo:
```
todo [description]
```

To add a Deadline task:
```
deadline [decription] /by [time]
```

To add an Event task:
```
event [description] /from [start date and time] /to [end date and time]
```

Example of usage:

```
todo read book
deadline return book /by 2023-10-15 1800
event project meeting /from 2023-10-08 1600 /to 2023-10-08 1800
```

### Marking of tasks: `mark`

Tasks can be marked as completed just by using the command mark, followed by the index of the task.
```
mark [task index number]
```

Example of usage:

`mark 1`

Expected outcome:
Marks the first task on the list.

### Unmarking of tasks: `unmark`

Tasks can be unmarked in the case it was accidentally marked as completed.
```
unmark [task index number]
```

Example of usage:

`unmark 1`

Expected outcome:
Unmarks the first task on the list.

### Deleting of tasks: `delete`

Tasks can be deleted just by using the command - `delete`, followed by the index of the task.
```
delete [task index number]
```

### Listing of tasks: `list`

Unsure of what tasks you have? The chatbot can list the tasks that it is currently tracking.

### Updating of tasks: `update`

Tasks can be updated in the case the task has some changes.

For updating of **ToDo** task:
```
update [todo index number] [description]
```

For updating of **Deadline** task:
```
update [deadline index number] [description]
update [deadline index number] /by [deadline time]
update [deadline index number] [description] /by [deadline time]
```

For updating of **Event** task:
```
update [event index number] [description]
update [event index number] /from [start date]
update [event index number] /to [end date]
update [event index number] [description] /from [start date]
update [event index number] [description] /to [end date]
update [event index number] /from [start date] /to [end date]
update [event index number] [description] /from [start date] /to [end date]
```

Example of usage:

`update 1 return book`

Expected outcome:
Task has been updated with return book.

### Finding of tasks: `find`

Tasks can be found easily by keying in keywords.

Example of usage:

`find read`

Expected outcome:
List of tasks containing read will be displayed.

### Exiting: `bye`

This is to close the chatbot.