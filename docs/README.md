# DUKE_MASTER Chatbot User Guide

Welcome to DUKE_MASTER! DUKE_MASTER is a chatbot which helps 

you to organize your task list.

The purpose of this guide is to brief students of AY 23/24 who are taking

CS2103/T about how should this chatbot be used.

## Task types
### To Do
A todo task is a task without any time limitations.

### Deadline
A deadline task is a task that has to be done before the deadline.

### Event
An event task is an event that has to be attended between 
the starting time and the ending time.

## Features 

### Feature - Add ToDo Task

Adds a todo task to the task list.

Format: todo YOUR_TASK

### Feature - Add Deadline Task

Adds a deadline task to the task list.

Format: deadline YOUR_TASK /by YOUR_DEADLINE

YOUR_DEADLINE format: dd/MM/YYYY HHmm

### Feature - Add Event Task

Adds an event to the task list.

Format: event YOUR_EVENT /from STARTING_TIME /to ENDING_TIME 

STARTING_TIME format: dd/MM/YYYY HHmm

ENDING_TIME format: dd/MM/YYYY HHmm

### Feature - Delete Task

Deletes a task from the task list.

Format: delete TASK_INDEX

Example: delete 5 (deletes the 5th task in the task list)

### Feature - Mark task

Marks a task in the task list as done.

Format: mark TASK_INDEX

Example: mark 5 (marks the 5th task in the task list as done)

### Feature - Unmark Task

Marks a task in the task list as undone.

Format: unmark TASK_INDEX

Example: unmark 5 (marks the 5th task in the task list as undone)

### Feature - List out all the tasks

Lists out all the tasks in the task list.

Format: list

### Feature - Find task

Finds a task in the task list based on a keyword.

Format: find KEY_WORD

### Feature - Farewell

Says bye to the user.

Format: bye
