# Fishron User Guide
Welcome to Fishron. Fishron is a task management 
application that helps you keep track 
of your day-to-day tasks. 

## Features 

### Feature-Todo

Adds a todo task with no specified deadline.

### Feature-Deadline

Adds a deadline task with a date and time to complete it by.

### Feature-Event

Adds an event task with a start and end date.

### Feature-List

Displays the list of tasks in your todo list.

### Feature-Mark

Marks a task as completed.

### Feature-Unmark

Unmarks a task if it is yet to be completed.

### Feature-Delete

Deletes a task from the todo list.

### Feature-Find

Find tasks that matches the inputted keyword.

### Feature-Help

Displays the list of commands available and how to use them.

### Feature-Bye

Saves the current todo list and closes the application.

## Usage

### `Todo` - Adds a todo task

Use the todo command to add a todo task.

Example of usage: 

`todo read book`

Expected outcome:

Adds a "read book" todo task.

```
Got it. I've added this task:
 [T][ ] read book
Now you have 1 tasks in the list.
```

### `Deadline` - Adds a deadline task

Use the deadline command to add a task with a deadline.

Example of usage:

`deadline return book /by 23-09-2023 1200`

Expected outcome:

Adds a return book task with a deadline
by 23rd September 2023 12PM.

```
Got it. I've added this task:
 [D][] return book (by: Sep 23 2023 12:00PM)
Now you have 1 tasks in the list.
```

### `Event` - Adds an event task

Use the event command to add an event task with a start and end date.

Example of usage:

`event Orientation camp /from 23-09-2023 1200 /to 27-09-2023 1800`

Expected outcome:

Adds an orientation camp event with a date from 
23rd September 2023 12PM to 27th September 2023 6pm.

```
Got it. I've added this task:
 [E][] Orientation camp (from: Sep 23 2023 12:00PM to: Sep 27 2023 06:00PM)
Now you have 2 tasks in the list.
```

### `List` - Displays all tasks in the todo list

Use the list command to view all tasks in the todo list.

Example of usage:

`list`

Expected outcome:

Displays the list of tasks in the todo list.

```
Here are the tasks in your list:
 1. [D][] return book (by: Sep 23 2023 12:00PM)
 2. [E][] Orientation camp (from: Sep 23 2023 12:00PM to: Sep 27 2023 06:00PM)
```

### `Mark` - Marks a task as completed

Use the mark command to mark a task as completed.

Example of usage:

`mark 1`

Expected outcome:

Marks the first task in the todo list with an X.

```
Nice! I've marked this task as done:
 1. [D][X] return book (by: Sep 23 2023 12:00PM)
```

### `Unmark` - Marks a task as uncompleted.

Use the unmark command to mark a task as uncompleted.

Example of usage:

`unmark 1`

Expected outcome:

Unmarks the first task in the todo list.

```
I've marked this task as undone:
 1. [D][] return book (by: Sep 23 2023 12:00PM)
```

### `Delete` - Deletes a task from the todo list

Use the delete command to delete a task from the todo list.

Example of usage:

`delete 1`

Expected outcome:

Deletes the first task from the todo list.

```
Noted. I've removed this task:
 [D][] return book (by: Sep 23 2023 12:00PM)
Now you have 1 tasks in the list.
```

### `Find` - Finds matching tasks in the todo list.

Use the find command to search for tasks matching the inputted keyword.

Example of usage:

`find orientation`

Expected outcome:

Displays the list of tasks that contains the keyword "orientation".

```
Here are the matching tasks in your list:
[E][] Orientation camp (from: Sep 23 2023 12:00PM to: Sep 27 2023 06:00PM)
```

### `Help` - Displays the list of commands available.

Use the help command to view the list of commands available and how to use them.

Example of usage:

`help`

Expected outcome:

Displays the list of commands available and how to use them.

```
These are the functions that I support.
Remember to input dates in this format dd-MM-yyyy HHmm.
1. todo (Description)
2. deadline (Description) /by DATE
3. event (Description) /from DATE /to DATE
4. mark (TaskNumber)
5. unmark (TaskNumber)
6. delete (TaskNumber)
7. list
8. bye
9. find (Keyword)
10. help
```

### `Bye` - Saves the current todo list and closes the application.

Use the bye command to save your todo list and exit the application.

Example of usage:

`bye`

Expected outcome:

Saves the current todo list and exits the application.