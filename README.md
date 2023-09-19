# DICK chatbot (by wj331)

## Quick start
1. Ensure you have Java 11 or above installed in your Computer.
2. Setup your DICK chatbot [here](https://github.com/wj331/ip/tree/master)

## Features
1. Adding **Todo** Tasks `todo`
2. Adding **Deadline** Tasks `deadline`
3. Adding **Events** `event`
4. Listing all tasks `list`
5. Deleting a Task `delete`
6. Marking a Task `mark`
7. Unmarking a Task `unmark`
8. Finding a Task `find`
9. Updating a Task `update`

## Usage

### 1. Adding a Todo task `todo`
Description: Adds a todo task named read book to the chatbot

Example of usage: 

`todo read book`

You will receive a successful message (like below) if done correctly.

`Got it. I've added this task`

### 2. Adding a Deadline task `deadline`
Description: Adds a deadline task named **return book** with sunday as deadline

Example of usage: 

`deadline return book /by sunday`

You will receive a successful message (like below) if done correctly.

`Got it. I've added this task`

### 3. Adding a Event task `event`
Description: Adds a `taylor swift concert` event with start time `12pm` and end time `6pm`

Example of usage: 

`event taylor swift concert /from 12pm /to 6pm`

You will receive a successful message (like below) if done correctly.

`Got it. I've added this task`

### 4. Listing all tasks `list`
Description: Returns all tasks stored in the chatbot

Example of usage: 

`list`

Output (if you have tasks in the list stored) :

`1. [T][ ] read book`

`2. [D][ ] return book`

### 5. Deleting a Task `delete`
Description: Deletes the task at the `INDEX` in the Chatbot's list

Example usage: 

`delete INDEX`

You will receive a successful message (like below) if done correctly.

`Noted. I've removed this task`

### 6. Marking a Task `mark`
Description: Marks a task at `INDEX` completed in the Chatbot's list

Example usage: 

`mark INDEX`

You will receive a successful message (like below) if done correctly.

`Nice! I've marked this task as done`

### 7. Unmarking a Task `unmark`
Description: Unmarks a task at `INDEX` as uncompleted in the Chatbot's list

Example usage: 

`unmark INDEX`

You will receive a successful message (like below) if done correctly.

`OK, I've marked this task as not done yet`

### 8. Finding a Task `find`
Description: Returns a list of all tasks with names containing `KEYWORD`in the Chatbot's list

Syntax: `find KEYWORD`

Example usage: `find book`

You will receive a list of all the tasks in list matching `book`
1. read book
2. return book


### 9. Updating a Task `update`
Description: Updates the task at `INDEX` to the given `FULL DETAILS`

Syntax: `update INDEX <FULL DETAILS>`

Example usage:

Assuming `1. todo read book` in list

`update 1 todo read cinderella` to change to `1. todo read cinderella`

output:
`Task read cinderella has been updated successfully`

# FAQ
**Q**: What happens if I do not get the output as expected?

**A**: It is probably due to the syntax not typed properly. 
Please retry with the expected syntax as seen in the usage
section of this README

# Known Issues
1. If chatbot Ui fails to launch with error message `JavaFX runtime components are missing`, ensure that you are running from the Launcher class

# Command Summary
| Action            | Format, Examples                  |
|-------------------|-----------------------------------|
| Add Todo task     | `todo read book`                  |
| Add Deadline task | `deadline return book /by Sunday` |
| Add Event         | `event kpop /from 12pm /to 2pm`   |
| List all tasks    | `list`                            |
| Deleting a task   | `delete 2`                        |
| Marking a task    | `mark 3`                          |
| Unmark a task     | `unmark 4`                        |
| Find a task       | `find book`                       |
| Update a task     | `update 2 read cinderella`        |

