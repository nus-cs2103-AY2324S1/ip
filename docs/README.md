# User Guide - Bocchi the Note

## Features 

### Viewing: `help`

Shows a message explaining all the available features in Bocchi the Note.
```
Here are the commands for Bocchi-chan:
help: Prints out the help page for various commands.
list: Prints out your list of tasks.
todo <task>: Adds the <task> into the list of tasks as a task to do.
event <task> /from <time> /to <time>: Adds the <task> into the list of tasks as an event with a duration.
deadline /by <yyyy-mm-dd>: Adds the <task> into the list of tasks with a specific deadline.
mark <number>: Marks task <number> in the list of tasks as done.
unmark <number>: Unmarks task <number> in the list of tasks, and it becomes undone.
find <keyword>: Finds the task with the exact <keyword> inputted.
delete <index>: Deletes the task of task number <index> from the task list.
bye: Bocchi chan says bye to you♥
```

Format: `help`

### Listing all tasks: `list`

Prints out your list of tasks.

Format: `list`

### Finding tasks in list by name: `find`

Finds tasks which names contain any of the given keywords.

Format: `find <keyword>` 

+ The search is case-sensitive. For example, `read` will not match `Read`.
+ The order of the keywords does not matter. For example, `read book` will match `book read`.
+ Only the task name is searched.
+ Only full words will be matched. For example, `book` will not match `books`.

Example:
`find read` returns `read book` and `read story`.

### Deleting a task: `delete`
Deletes the specified task from the task list.

Format: `delete <index>`

+ Deletes the task at the specified `<index>`.
+ The index refers to the index number shown in the displayed tasks list.
+ The index must be a **positive integer** 1, 2, 3, ......

Examples:

+ `list` followed by `delete 2` deletes the 2nd task in the task list.
+ `find book` followed by `delete 1` deletes the 1st task in the results of the `find` command.

### Marking a task as done: `mark`

Marks the specified task as done from the task list.

Format: `mark <index>`

+ Marks the task as done at the specified `<index>`.
+ The index refers to the index number shown in the displayed tasks list.
+ The index must be a **positive integer** 1, 2, 3, ......

+ `list` followed by `mark 2` marks the 2nd task as done in the task list.
+ A task mark as done may have the following example outputs after calling `list`, 
depending on their type of tasks:
  + `[T] [X] read book`
  + `[E] [X] read book (from: Tuesday to: Wednesday)`
  + `[D] [X] read book (by: OCT-22-2022)`

### Marking a task as undone: `unmark`

Unmarks the specified task as undone from the task list.

Format: `unmark <index>`

+ Unmarks the task as undone at the specified `<index>`.
+ The index refers to the index number shown in the displayed tasks list.
+ The index must be a **positive integer** 1, 2, 3, ......

+ `list` followed by `unmark 2` unmarks the 2nd task as undone in the task list.
+ A task mark as undone may have the following example outputs after calling `list`,
  depending on their type of tasks:
  + `[T] [ ] read book`
  + `[E] [ ] read book (from: Tuesday to: Wednesday)`
  + `[D] [ ] read book (by: OCT-22-2022)`

### Saying bye to Bocchichan: `bye`

Interacts with Bocchi chan by saying goodbye to her.

Format: `bye`

Example output:
`B... b...bye bye!... And ... see you! （//▽//）`


### Saving the Data
Task list data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

## Adding a task

### `todo` - Adds a task of type Todo

Adds the <task> into the list of tasks as a task to do.

Example of usage: 

`todo read book`

A Todo task with task name `read book` is added to the task list, with its status as undone.

Expected output:
```
Okk... I've... I've added this task:
[T] [ ] read book
N... Now you have... <total number of tasks> tasks in the list. o(>ω<)o
```

### `event` - Adds a task of type Event

Adds the <task> into the list of tasks as an Event.

Example of usage:

`event read book /from Monday /to Tuesday`

An Event task with task name `read book` is added to the task list, with its status as undone.

Expected output:
```
Okk... I've... I've added this task:
[E] [ ] read book (from: Monday to: Tuesday)
N... Now you have... <total number of tasks> tasks in the list. o(>ω<)o
```

### `deadline` - Adds a task of type Deadline

Adds the <task> into the list of tasks as a Deadline, with the date format as `YYYY-MM-DD`.

Example of usage:

`deadline read book /by 2020-10-22`

A Deadline task with task name `read book` is added to the task list, with its status as undone. 
Output date will be in the format of `MMM-DD-YYYY`.

Expected output:
```
Okk... I've... I've added this task:
[D] [ ] read book (by: OCT 22 2020)
N... Now you have... <total number of tasks> tasks in the list. o(>ω<)o
```
