# Chatbot Ari
# User Guide

## Features 

### 1. Add tasks
You can add 3 types of tasks: todo, deadline, and event

### 2. View all tasks
You can view all tasks in a numbered list

### 3. Find using keyword
You can find tasks that contain the keyword

### 4. Mark / Unmark tasks
You can mark tasks as done or unmark them if they are unfinished

### 5. Delete tasks
You can delete tasks you no longer want to see

### 6. Reschedule event / Postpone deadline
You can reschedule an event or postpone deadline by editing the dates

## Usage

### `todo` - Adds a todo task

Command format:
```
todo taskDescription
```
Example of usage:
`todo read book`

Expected outcome:
```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
```

Description of the outcome: a todo task (read book) has been added to your task list

### `deadline` - Adds a deadline task

Command format:
```
deadline taskDescription /by yyyymmdd hhmm
```
Example of usage:
`deadline return book /by 20230607 1400`

Expected outcome:
```
Got it. I've added this task:
[D][ ] return book (by: Jun 07 2023 2PM)
Now you have 2 tasks in the list.
```

Description of the outcome: a deadline task (return book) has been added to your task list

### `event` - Adds an event task

Command format:
```
event taskDescription /from yyyymmdd hhmm /to hhmm
```
Example of usage:
`event project meeting /from 20230709 1600 /to 1800`

Expected outcome:
```
Got it. I've added this task:
[E][ ] project meeting (from: Jul 09 2023 4PM to: 6pm)
Now you have 3 tasks in the list.
```

### `list` - Lists out all tasks in the task list

Command format:
```
list
```
Example of usage:
`list`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Jun 07 2023 2PM)
3. [E][ ] project meeting (from: Jul 09 2023 4PM to: 6PM)
```
Description of the outcome: all the tasks in your tasklist has been printed in numbered list

Description of the outcome: an event task (project meeting) has been added to your task list

### `find` - Finds tasks that has matching keywords

Command format:
```
find keyword
```
Example of usage:
`find book`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Jun 07 2023 2PM)
```
Description of the outcome: tasks that contains the keyword has been printed in numbered list

### `mark` - Marks a task as done

Command format:
```
mark index
```
Example of usage:
`mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
[T][X] read book
```

Description of the outcome: a task with index 1 has been marked as done

### `unmark` - Unmarks a task as unfinished

Command format:
```
unmark index
```
Example of usage:
`unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:
[T][ ] read book
```

Description of the outcome: a task with index 1 has been unmarked from done

### `delete` - Deletes a task

Command format:
```
delete index
```
Example of usage:
`delete 1`

Expected outcome:
```
[T][ ] read book
Now you have 2 tasks in the list.
```

Description of the outcome: a task with index 1 has been deleted from your task list

### `reschedule` - Reschedules an event

Command format:
```
reschedule index /from yyyymmdd hhmm /to hhmm
```
Example of usage:
`reschedule 2 /from 20230710 1500 /to 1700`

Expected outcome:
```
Ok, We have rescheduled the following event
[E][ ] project meeting (from: Jul 10 2023 3PM to: 5PM)
```

Description of the outcome: an event with index 2 has been rescheduled (from: Jul 10 2023 3PM to: 5PM).

### `postpone` - Postpones a deadline

Command format:
```
postpone index /by yyyymmdd hhmm
```
Example of usage:
`postpone 1 /by 20230608 1500`

Expected outcome:
```
Ok, We have postponed the following deadline
[D][ ] return book (by: Jun 08 2023 3PM)
```

Description of the outcome: a deadline with index 1 has been postponed (by: Jun 08 2023 3PM).
