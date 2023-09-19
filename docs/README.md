# Professional notebook OTTO!!!

## Features 

### Adding tasks to do in the future

**There are three types of tasks you can add**

- **Todo**: A task that you want to do in the future
* **Deadline**: A task that you want to do before a specific date
+ **Event**: A task that you want to do during a specific period of time

### List all tasks

Tell bot to list all the tasks you have added

### Mark a task as done or not done

Tell bot which task you want to mark as done or not done

### Snooze a task

Tell bot which task you want to snooze

### Delete a task

Tell bot which task you want to delete

### Find a task

Tell bot to return a list of tasks that contains the keyword you have entered

### Exit the program

Tell bot to exit the program

# Usage

## Adding tasks to do in the future

### `todo` - Add a todo task to the list

**A "[T]" will be added at the start of the task**

>How to use: `todo <taskName>` \
>Example of usage: `todo read book`

### `deadline` - Add a deadline task to the list

**A "[D]" will be added at the start of the task**

>How to use: `deadline <taskName> /by <date>` \
>Example of usage: `deadline read book /by 2023-09-10`

### `event` - Add an event task to the list

**A "[E]" will be added at the start of the task**

>How to use: `event <taskName> /from <date> /to <date>` \
>Example of usage: `event read book /from 2023-09-10 /to 2023-09-11`

> [!NOTE]
> `date` is in the format of `yyyy-mm-dd`

## Get the list of tasks

### `list` - List all the tasks

>How to use: `list`

## Modify the list of tasks

### `mark` - Mark a task as done

**A "[X]" will be added at the start of the task**

> How to use: `mark <taskNumber>` \
> Example of usage: `mark 1`

### `unmark` - Mark a task as not done

**A "[ ]" will be added at the start of the task**

> How to use: `unmark <taskNumber>` \
> Example of usage: `unmark 1`

### `snooze` - Snooze a task

**A "(snoozed)" will be added at the end of the task**

> How to use: `snooze <taskNumber>` \
> Example of usage: `snooze 1`

### `delete` - Delete a task

> How to use: `delete <taskNumber>` \
> Example of usage: `delete 1`

> [!NOTE]
> `taskNumber` is the number of the task in the list

## Other commands

### `find` - Find a task

**A list of tasks that contains the keyword will be returned**

> How to use: `find <keyword>` \
> Example of usage: `find book`

### `bye` - Exit the program

**The program will be closed after displaying "Bye"**

> How to use: `bye`



