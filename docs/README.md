# User Guide

## Features & Usage

### 1. List tasks
- **Command word**: `listout`
- **Description**: lists out all the tasks
- **Expected output**:
```
1. [T][] die
2. [T][X] eat and die
```
----------
### 2. Addding tasks
- ### Add todo
  - **Command word**: `todo (task)`
  - **Description**: add task of type "todo"
  - **Expected output**:
```
ADDED todo FOR YOU!!!!
[T][]die
```
- ### Add deadline
  - **Command word**: `deadline (task) /by (yyyy-MM-dd HH:mm)`
  - **Description**: add task with a deadline
  - **Expected output**:
```
ADDED deadline FOR YOU!!!!
[D][]pig (by: 12-December-2023 11:59PM)
```
- ### Add event
  - **Command word**: `event (task) /from (yyyy-MM-dd HH:mm) /to (yyyy-MM-dd HH:mm)`
  - **Description**: add task of type "event" with time period
  - **Expected output**:
```
ADDED event FOR YOU!!!!
[E][]duck (from: 12-December-2023 11:59PM to: 14-December-2023 11:59PM)
```
---
### 3. Find task
- **Command word**: `find (keyword)`
- **Description**: finds all tasks that matches given keyword
- **Expected output**:
```
HERE ARE YOUR MATCHES: 
1. [T][X] eat and die
```
---
### 4. Delete task
- **Command word**: `delete (task number)`
- **Description**: deletes the task corresponding to the task number
- **Expected output**:
```
REMOVED FOR YOU:)
[T][X] eat and die
Now you have: 3 tasks left.
```
---
### 5. Mark task
- **Command word**: `mark (task number)`
- **Description**: marks the given task number as completed
- **Expected output**:
```
you're done. DONEEE
```
---
### 6. Unmark task
- **Command word**: `unmark (task number)`
- **Description**: umarks the given task number as completed
- **Expected output**:
```
Done. Stop being lazy.
[T][]die and eat
```
---
### 7. Undo task
- **Command word**: `undo`
- **Description**: undo the most recent task addition
- **Expected output**:
```
Undid the latest task you edited!!!!!!
```
---
### 8. Exit
- **Command word**: `bye`
- **Description**: exits the system 
