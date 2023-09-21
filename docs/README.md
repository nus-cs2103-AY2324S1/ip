# Monday ChatBot Guide

## Table of Content

- Getting Started
- Features
- Usage

## Getting Started

1. Ensure you are using java 11.
2. Download the latest jar file from [releases](https://github.com/nubnubyas/ip/releases/tag/A-Release).
3. Navigate to the directory containing the jar file.
4. Run java -jar monday.jar to get started.

## Features

### Tasks Management

Monday empowers you to effortlessly manage your tasks, 
deadlines, and events right on your local device. 
Seamlessly keep track of all your tasks, whether 
they're completed or pending, and gain complete 
control over your productivity. Your tasks are 
securely stored locally, ensuring easy access whenever 
you need them.


## Usage
### 1. `☑️todo `

Adds a todo task to your list of tasks.

Format:

`todo (description)`

Example of usage: 

`todo return book`

Expected outcome:

Monday will add the task to the list.

```
Got it. I've added this task:
    [T][ ] return book
Now you have 1 tasks in the list.
```

### 2. `📅deadline `

Adds a deadline task to your list of tasks.

Format:

```
deadline (task) /by (time)
time format: YYYY-MM-dd HH:mm
```

Example of usage:

`deadline 2103T IP /by 2023-09-22 23:59`

Expected outcome:

Monday will add the task to the list.

```
Got it. I've added this task:
    [D][ ] 2103T IP (by: 22-09-2023 23:59)
Now you have 2 tasks in the list.
```
### 3. `📅⏰event`

Adds a event task to your list of tasks.

Format:

```
event (task) /from (start time) /to (end time)
time format: YYYY-MM-dd HH:mm
```

Example of usage:

`event project meeting /from 2023-09-30 17:00 /to 2023-09-30 19:00`

Expected outcome:

Monday will add the task to the list.

```
Got it. I've added this task:
    [E][ ] project meeting (from: 30-09-2023 17:00 to: 30-09-2023 19:00)
Now you have 3 tasks in the list.
```
### 4. `📋list`

Display all the tasks in your task list.

Format:
`list`

Example of usage:

`list`

Expected outcome:

Monday will list all the tasks in the list.

```
Here are the tasks in your list:
1. [T][ ] return book
2. [D][ ] 2103T IP (by: 22-09-2023 23:59)
3. [E][ ] project meeting (from: 30-09-2023 17:00 to: 30-09-2023 19:00)
```
### 5. `✅mark`

Mark the task as done.

Format:
`mark (task number)`

Example of usage:

`mark 1`

Expected outcome:

Monday will mark the corresponding task as done in the task list.

```
Nice! I've marked this task as done:
[T][X] return book
```
### 6. `❎unmark`

Mark the task as not done.

Format:
`unmark (task number)`

Example of usage:

`unmark 1`

Expected outcome:

Monday will mark the corresponding task as not done in the task list.

```
OK, I've marked this task as not done yet:
[T][ ] return book
```
### 7. `❌delete`

Delete the task from the task list.

Format:
`delete (task number)`

Example of usage:

`delete 1`

Expected outcome:

Monday will delete the corresponding task in the task list.

```
Noted. I've removed this task:
[T][ ] return book
Now you have 2 tasks in the list.
```
### 8. `🔍find`

Find tasks that contains the keyword.

Format:
`find (keyword)`

Example of usage:

`find 2103`

Expected outcome:

Monday will list all tasks that contains the keyword, 
including those that only contains part of it.

```
Here are the matching tasks in your list:
1. [D][ ] 2103T IP (by: 22-09-2023 23:59)
```
### 9. `👋bye`

Exits Monday.

Format:
`bye`

Example of usage:

`bye`

Expected outcome:

Monday will exit after a short delay.
```
Bye. Hope to see you again soon!
```
