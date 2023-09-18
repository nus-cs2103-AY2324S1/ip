# Luke Chatbot
# User Guide

## Features 

### Add task

You are able to add task to Luke.
The available choice of task include:
* Todo
* Deadline
* Events

### View all task
You are able to view all the task you have saved using the command 'list'.

### Delete task
You are able to delete the task you saved.

### Mark and Unmark task
You are able to mark the task you have done or unmark the task you want.

### Search keyword
You are able to search you task with a keyword of the task.

### Execute the program
You are able to close Luke with the keyword bye.


## Usage


### `list` - view task

By using this keyword, the chatbot will reply you all the tasks in the task list.

Example of usage:

`list`

Expected outcome:


```
1. [T][X] borrow book
2. [E][] project meeting (from: Aug 13 2024 14:00 to: Aug 15 2024 23:00)
3. [E][] comp (from: Feb 12 2023 12:00 to: Dec 13 2023 23:00)
4. [D][] fund (by: Sep 12 2023 12:00)
5. [T][] tutorial /by 12/04/2023 1200
6. [D][] Jump (by: May 12 2023 12:00)
7. [T][] swim
8. [E][] assignment (from: Sep 12 2023 12:00 to: Sep 13 2023 14:00)
```
&ensp;

### `Todo` - add a 'todo' task.

By using this keyword, Luke will add the todo task to the list and reply you to notice you that he had added to the list.

Example of usage:

todo DESCRIPTION

`todo run`

Expected outcome:

```
Got it. I've added this task:
[T][] run
Now you have 9 tasks in the list.
```

### `Deadline` - add a 'deadline' task.

By using this keyword, Luke will add the deadline task to the list and reply you to notice you that he had added to the list.

Example of usage:

deadline TASK_DESCRIPTION /by DD/MM/YYYY HHMM 

`deadline exam /by 12/03/2023 1200`

Expected outcome:

* The detail of the deadline
* The number of task in your list now

```
Got it. I've added this task:
[D][] exam (by: Mar 12 2023 12:00)
Now you have 10 tasks in the list.
```
&nbsp;

### `event` - add a 'event' task.

By using this keyword, Luke will add the event task to the list and reply you to notice you that he had added to the list.

Example of usage:

event TASK_DESCRIPTION /from DD/MM/YYYY HHMM (date and time for start)
/to DD/MM/YYYY HHMM (date and time for end)

`event party /from 13/04/2023 2200 /to 14/04/2023 1400`

Expected outcome:

* The detail for the event added.
* The number of task in the list.

```
Got it. I've added this task:
[E]L] party (from: Apr 13 2023 22:00 to: Apr 14 2023 14:00)
Now you have 11 tasks in the list.
```
&nbsp;

### `mark` - mark a task as done.

By using this keyword, Luke will mark the task with the index you give as done.

Example of usage:

mark INDEX_OF_TASK

`mark 3`

Expected outcome:

* The detail for the task marked as done.


```
Nice! I've marked this task as done:
[E][X] comp (from: Feb 12 2023 12:00 to: Dec 13 2023 23:00)
```
&nbsp;

### `unmark` - unmark a task which has not done.

By using this keyword, Luke will unmark the task with the index you give as not done.

Example of usage:

unmark INDEX_OF_TASK

`unmark 3`

Expected outcome:

* The detail for the unmark task.


```
OK, I've marked this task as not done yet:
[E][] comp (from: Feb 12 2023 12:00 to: Dec 13 2023 23:00)
```
&nbsp;

### `delete` - delete a task

By using this keyword, Luke will delete the task with the index you give as not done.

Example of usage:

delete INDEX_OF_TASK

`delete 3`

Expected outcome:

* The detail for the deleted task.
* number of task left in the list


```
Noted. I've removed this task:
[E][] comp (from: Feb 12 2023 12:00 to: Dec 13 2023 23:00) Now you have 10 tasks in the list.
```
&nbsp;

### `find` - find a task

By using this keyword, Luke will find the tasks containing your keyword.

Example of usage:

find KEYWORD (can be incomplete keyword and case insensitive)

`find SWIM` or `find sw`

Expected outcome:

* The detail for the task.


```
[T][] swim
```
&nbsp;

### `bye` - execute program

By using this keyword, Luke will close himself down after 0.5 second.

Example of usage:

`bye`


Expected outcome:

Window closed after 0.5 second.
```
Bye. Hope to see you again soon!
```








