# User Guide for Duke

### Duke is a robust desktop application designed for efficiently managing to-do lists, events, deadlines, and task
### completion status. It combines the user-friendly interface of a graphical application with the flexibility of handling 
### various task types, making it a speedy solution for task management, especially for those with fast typing skills.

## Features

### Adding a todo task : `todo`

Add a todo task, which only contains a description. 

Format: `todo DESCRIPTION`

Example Command: 
* `todo homework`
* `todo assignment`

### Adding an event task : `event`

Adds an event task, which contains a description, start time and end time.

Format: `event DESCRIPTION /from STARTTIME /to ENDTIME`

Example Command:
* `event Welcome Tea /from  19th July 2023  1700 /to 19th July 2023 1900`

### Adding a deadline task : `deadline`

Adds a deadline task, which contains a description and a due date.

Format: `deadline DESCRIPTION /by ENDTIME`

Example Command:
* `deadline Individual Project /by  19th July 2023  1700`
* `deadline SEP Submission /by  24th July 2023  1900`


### Listing all tasks : `list`

Lists all the users task. 

Format: `list`

Example Command:
* `list`

### Deleting task : `delete`

Delete the task specified from the task list. 

Format: `delete INDEX`

Example Command:
* `delete 1` - deletes Task 1
* `delete 5` - deletes Task 5

### Marking task as done : `mark`

Marks a task as done, while it retains the task in the task list.

Format: `mark INDEX`

Example Command:
* `mark 1` - marks Task 1 as done
* `mark 5` - marks Task 5 as done

### Marking task as not done : `unmark`

Marks a task as not done, while it retains the task in the task list.

Format: `unmark INDEX`

Example Command:
* `unmark 1` - marks Task 1 as not done
* `unmark 5` - marks Task 5 as not done

### Updating the task description : `update description`

Updates the task description to the new one specified by the user without deleting the task.

Format: `update description INDEX /update UPDATEDDESCRIPTION`

Example Command:
* `update description 1 /update homework` - changes description of Task 1 to 'homework'
* `update description 5 /update birthday party` - changes description of Task 5 to 'birthday party'

## Usage

### Adding a todo task : `todo`

Add a todo task, which only contains a description.

Format: `todo DESCRIPTION`

Example of usage: 

`todo homework`

Expected outcome:

Adds a todo task with the description 'homework'

```
Got it. I've added this task:
[T][] homework
Now you have 1 tasks in the list.
```

### Adding an event task : `event`

Adds an event task, which contains a description, start time and end time.

Format: `event DESCRIPTION /from STARTTIME /to ENDTIME`

Example of usage:

* `event Welcome Tea /from  19th July 2023  1700 /to 19th July 2023 1900`

Expected outcome:

Adds an Event task with the description 'Welcome Tea'

```
Got it. I've added this task:
[E][] Welcome Tea (from: 19th July 2023 1700 to: 19th July 2023 1900)
Now you have 2 tasks in the list.
```

### Adding a deadline task : `deadline`

Adds a deadline task, which contains a description and a due date.

Format: `deadline DESCRIPTION /by ENDTIME`

Example of usage:

* `deadline Individual Project /by  19th July 2023  1700`

Expected outcome:

Adds a deadline task with the description 'Individual Project'

```
Got it. I've added this task:
[D][] Individual Project (by: 19th July 2023 1700)
Now you have 3 tasks in the list.
```

### Listing all tasks : `list`

Lists all the users task.

Format: `list`

Example of usage:

* `list`

Expected outcome:

Lists all tasks.

```
Here are the tasks in your list: 
1.[T][] homework
2.[E][] Welcome Tea (from: 19th July 2023 1700 to: 19th July 2023 1900)
3.[D][] Individual Project (by: 19th July 2023 1700)

-----------------------------------------------------------------------
Now you have 3 tasks in the list.

-----------------------------------------------------------------------
```

### Deleting task : `delete`

Delete the task specified from the task list.

Format: `delete INDEX`

Example of usage:

* `delete 1` 

Expected outcome:

Deletes Task 1

```
Noted. I've removed this task:
[T][] homework
Now you have 2 tasks in the list. 
```

#### Marking task as done : `mark`

Marks a task as done, while it retains the task in the task list.

Format: `mark INDEX`

Example of usage:

* `mark 1`

Expected outcome:

Marks Task 1 as done.

```
Nice! I've marked this task as done:
[E][X] Welcome Tea (from: 19th July 2023 1700 to: 19th July 2023 1900)
Now you have 2 tasks in the list. 
```

### Marking task as not done : `unmark`

Marks a task as not done, while it retains the task in the task list.

Format: `unmark INDEX`

Example of usage:

* `unmark 1`

Expected outcome:

Marks Task 1 as not done

```
OK, I've marked this task as not done yet:
[E][] Welcome Tea (from: 19th July 2023 1700 to: 19th July 2023 1900)
Now you have 2 tasks in the list. 
```

### Updating the task description : `update description`

Updates the task description to the new one specified by the user without deleting the task.

Format: `update description INDEX /update UPDATEDDESCRIPTION`

Example of usage:

* `update description 1 /update birthday party` 

Expected outcome:

Changes description of Task 1 to 'birthday party'.

```
Updated the description of Task 1 from "Welcome Tea" to "birthday party"
Now you have 2 tasks in the list. 
```