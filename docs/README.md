# User Guide

## Features 

### Managing Tasks

- Add tasks, including todo, deadline, and event in interactive way
- Delete any unwanted task
- Mark any completed task
- Unmark task that accidentally got marked
- Find task easily based on certain keyword
- View task list

### Managing Notes
- Add notes with title and content
- Delete unwanted note
- View note list

## Usage

### `todo` - Adding todo task

EchoBot will add your todo task into the task list.

Format: `todo DESCRIPTION`

Example of usage:

`todo Buy Groceries`

Expected outcome:

EchBot will response as below,

```
Got it. I've added this task:
[T] [] Buy Groceries
Now you have 1 taks.
```

### `deadline` - Adding deadline task

EchoBot will add your deadline task with due date into the task list.

Format: `deadline DESCRIPTION /by YYYY-MM-DD`

Example of usage: 

`deadline Peer Review /by 2023-09-25`

Expected outcome:

EchBot will response as below,

```
Got it. I've added this task:
[D] [] Peer Review (by: Sep 25 2023)
Now you have 2 taks.
```
### `event` - Adding event task

EchoBot will add your event task with start date-time and end date-time into the task list.

Format: `event DESCRIPTION /from YYYY-MM-DD HHMM /to YYYY-MM-DD HHMM`

Example of usage:

`event Meeting /from 2023-10-10 1100 /to 2023-10-10 1300`

Expected outcome:

EchBot will response as below,

```
Got it. I've added this task:
[E] [] Meeting (from: Oct 10 2023 11:00 AM to: Oct 10 2023 13:00 PM)
Now you have 3 taks.
```
### `delete` - Deleting task

EchoBot will delete the specified task.

Format: `delete INDEX`
- Deletes the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Example of usage:

`delete 1`

Expected outcome:

EchBot will response as below,

```
Noted. I've removed this task:
[T] [] Buy Groceries
Now you have 2 tasks in the list.
```
### `mark` - Marking task

EchoBot will mark the specified task.

Format: `mark INDEX`
- Marks the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Example of usage:

`mark 1`

Expected outcome:

EchBot will response as below,

```
Nice! I've marked this task as done:
[X] Peer Review (by: 2023-09-25)
```
### `unmark` - Unmarking task

EchoBot will unmark the specified task.

Format: `unmark INDEX`
- Unmarks the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Example of usage:

`unmark 1`

Expected outcome:

EchBot will response as below,

```
OK, I've marked this task as not done yet:
[] Peer Review (by: 2023-09-25)
```
### `find` - Listing task by keyword

EchoBot will display the matching task from your task list.

Format: `find KEYWORD`
- The search is case-insensitive. e.g. meeting will match Meeting
- Only full words will be matched e.g. meet will not match meeting

Example of usage:

`find Meeting`

Expected outcome:

EchBot will response as below,

```
Here are the matching tasks in your list:
    1. [E] [] Meeting (from: Oct 10 2023 11:00 AM to: Oct 10 2023 13:00 PM)
```
### `list task` - Listing all tasks

EchoBot will display all of your tasks.

Example of usage:

`list task`

Expected outcome:

EchBot will response as below,

```
Here are the tasks in your list:
    1. [D] [] Peer Review (by: Sep 25 2023)
    2. [E] [] Meeting (from: Oct 10 2023 11:00 AM to: Oct 10 2023 13:00 PM)
```
### `bye` - Ending the interaction with EchoBot

EchoBot will display farewell message and close the application.

Example of usage:

`bye`

Expected outcome:

EchBot will response as below,

```
Bye. Hope to see you again soon!
```
