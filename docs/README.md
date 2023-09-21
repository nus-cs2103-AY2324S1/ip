# Evo

## User Guide

## Features

1. ### `bye` - Exiting the program

Exits the program.

2. ### `list` - Listing all tasks

Shows all tasks in the list.

3. ### `find` - Finding a task

Finds a task by searching for a keyword.

4. ### `mark` - Marking a task

Marks the status of a task as done.

5. ### `unmark` - Unmarking a task

Changes the status of a marked task back to not done.

6. ### `delete` - Deleting a task

Deletes the specified task from the taskslist.

7. ### `do` - Doing after a specific time/task

Adds a 'DoAfter' task that needs to be done after a specific time/task.

8. ### `todo` - Adding a todo Task

Adds a 'todo' task with description of the task.

9. ### `deadline` - Adding a deadline Task

Adds a 'deadline' task that needs to be done before a specific date/time.

10. ### `event` - Adding an event Task

Adds an 'event' task that starts at a specific date/time and ends at a specific date/time.

## Usage

1. `bye`

Exits the program.

2. `list`

Expected outcome:
```
Here are the tasks in your list:
1. [T][] buy ice cream
2. [T][] read book
```

All tasks in the list was shown.

3. `find book`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][] read book
```

Tasks with the keyword 'book' was listed out.

4. `mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
 [T][X] buy ice cream
```

The status of task 1 was marked as done.

5. `unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:
 [T][] buy ice cream
```

The status of task 1 was changed to not done.

6. `delete 1`

Expected outcome:
```
Noted. I've removed this task:
 [T][] buy ice cream
Now you have 1 task in the list.
```

Task 1 was deleted from the list.

7. `do return book after the exam is over`

Expected outcome:
```
Got it. I've added this task:
 [DA][] return book (after the exam is over)
Now you have 2 tasks in the list.
```

A 'DoAfter' task that needs to be done after the exam is over was added to the list.

8. `todo buy ice cream`

Expected outcome:
```
Got it. I've added this task:
 [T][] buy ice cream
Now you have 3 tasks in the list.
```

A 'todo' task with description was added to the list.

9. `deadline homework /by 2023-09-21 1800`

Expected outcome:
```
Got it. I've added this task:
 [D][] homework (by: Sep 21 2023 6PM)
Now you have 4 tasks in the list.
```

A 'deadline' task that needs to be done before Sep 21 2023 6PM was added to the list.

10. `event project meeting /from 2023-09-21 1800 /to 2000`

Expected outcome:
```
Got it. I've added this task:
 [E][] project meeting (from: Sep 21 2023 6PM to: 8PM)
Now you have 5 tasks in the list.
```
An 'event' task called project meeting that starts on Sep 21 2023 6PM and ends at 8PM was added to the list.

