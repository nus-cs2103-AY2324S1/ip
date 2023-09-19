# Boo🐧

Boo is a Personal Assistant Chatbot that keeps track of the user's daily tasks.

## Features

### _Managing daily tasks_

Boo helps with managing the user's tasks (to-dos, deadlines, events). It allows users to specify the task description, due date/time, start/end date/time and indicate completion status.

### _Displaying your list of tasks_

The list of tasks added can be viewed any time.

### _Showing task completion status and statistics_

Users can keep track of their progress as they work on the tasks.



## Usage

### 1. `todo <description>` - Add a todo task

This command will add a todo task to the list.

Example of usage:

`todo play badminton` where `play badminton` is the task description.

Expected outcome:

A todo task with the specified description will be added to the list.

```
Got it. I've added this task:
[T][] play badminton
Now you have 5 tasks in the list.
```

### 2. `deadline <description> /by <date> (in dd/MM/yyyy HHmm)` - Add a deadline task

This command will add a deadline task to the list.

Example of usage:

`deadline homework /by 22/12/2023 1800` where `homework` is the task description, and `22/12/2023 1800` is the deadline.

Expected outcome:

A deadline task with the specified deadline (in dd/MM/yyyy HHmm) will be added to the list.

```
Got it. I've added this task:
[D][] homework (by: 22 Dec 2023 6:00 PM)
Now you have 5 tasks in the list.
```

### 3. `event <description> /from <start date> /to <end date>` - Add an event task

This command will add an event task to the list.

Example of usage:

`event play piano /from 10pm /to 11pm` where `play piano` is the task description, `10pm` is the start time, and `11pm` is the end time.

Expected outcome:

An event task with the specified start and end date/time will be added to the list.

```
Got it. I've added this task:
[E][] play piano (from: 10pm to: 11pm)
Now you have 5 tasks in the list.
```

### 4. `list` - View all your tasks

This command will show your full list of tasks which are added.

Example of usage:

`list`

Expected outcome:

Complete list of tasks added with their descriptions, task types and completion status clearly shown.

```
Here are the tasks in your list:
1. [E][] project meeting (from: Mon 6pm to: 8pm)
2. [D][] homework (by: 22 Dec 2023 6:00 PM)
3. [T][X] play badminton
4. [T][X] complete assignment 10
5. [E][X] play piano (from: 10pm to: 11pm)
```

### 5. `mark <task number>` - Mark completion status of the task listed at that number

This command will mark a task listed at the number specified, showing that it has been completed.

Example of usage:

`mark 2` where `2` is the task number specified.

Expected outcome:

Task will be successfully marked if the task number specified is valid.

```
Nice! I've marked this task as done:
[X] homework (by: 22 Dec 2023 6:00 PM)
```

### 6. `unmark <task number>` - Unmark completion status of the task listed at that number

This command will unmark a task listed at the number specified, showing that it remains incomplete.

Example of usage:

`unmark 4` where `4` is the task number specified.

Expected outcome:

Task will be successfully unmarked if the task number specified is valid.

```
OK, I've marked this task as not done yet:
[ ] complete assignment 10
```

### 7. `delete <task number>` - Delete the task listed at that number

This command will delete a task listed at the number specified from the user's list.

Example of usage:

`delete 4` where `4` is the task number specified.

Expected outcome:

Task will be successfully deleted if the task number specified is valid, and the new current number of tasks in the list will be shown.

```
Noted. I've removed this task:
[T][] complete assignment 10
Now you have 5 tasks in the list.
```

### 8. `find <keyword>` - View tasks containing a specific keyword

This command will show the list of tasks that match the keyword indicated.

Example of usage:

`find play` where `play` is the keyword specified.

Expected outcome:

A list of tasks which contain the keyword specified. 

```
Here are the matching tasks in your list:
1. [T][X] play badminton
2. [E][X] play piano (from: 10pm to: 11pm)
```

### 9. `status` - View task completion status and statistics

This command will show the list of completed tasks as well as the percentage of tasks completed. 

Example of usage:

`status`

Expected outcome:

An encouraging message will be shown if no task is completed, otherwise, users can view task completion status and statistics with this command.

```
You have completed 3 tasks so far, good job! :-)
                
Here are the completed tasks:
1. [D][X] homework (by: 22 Dec 2023 6:00 PM)
2. [T][X] play badminton
3. [E][X] play piano (from: 10pm to: 11pm)

Progress: 30.00 % completed, keep it up!
```

### 10. `help` - View list of commands

This command will show the list of available commands and details on how to use them.

Example of usage:

`help`

Expected outcome:

A list of available commands with instructions.

```
Available commands:
1. list - Lists all your tasks.
2. mark <task number> - Marks a task as completed.
3. unmark <task number> - Unmarks a task.
4. todo <description> - Adds a todo task.
5. deadline <description> /by <date> (in dd/MM/yyyy HHmm) - Adds a deadline task.
6. event <description> /from <start date> /to <end date> - Adds an event task.
7. delete <task number> - Deletes the task listed at that number.
8. find <keyword> - Finds tasks containing the keyword.
9. status - Shows your completed tasks and progress statistics.
10. bye - Exits the application.
```

### 11. `bye` - Exit the application

This command will close the application.

Example of usage:

`bye`

Expected outcome:

Boo will reply with a farewell message and exit the application.

```
Bye for now, hope to see you soon!
```
