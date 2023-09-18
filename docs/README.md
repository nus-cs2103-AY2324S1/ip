# User Guide

## Features 

1. Add 3 different type of tasks: Todo, Deadline, Event.
2. View the current added tasks.
3. Marking existing tasks as done/not done.
4. Rescheduling existing tasks.
5. Delete existing tasks

# Chat Bot

Chat Bot is a chat bot that allows you to add, manange and view tasks to be done in an interactive manner

## Basics

1. Run ChatBot.jar to start up ChatBot

2. Enter commands into the chat box displayed to and click send to interact with Chat Bot.

3. Enter command "bye" and send to end Chat Bot session.


## Commands to Add/Show Tasks

### Listing Tasks
> list

This will list of all the current tasks added to Chat Bot.


### Adding a Todo task
> todo { description of task }

This will add a Todo task into the current list of tasks in Chat Bot.


### Adding a Deadline task
> deadline { description of task } /by { deadline of the task }

This will add a Event task with specified deadline into the current list of tasks in Chat Bot.

### Adding an Event task
> event { description of task } /from { start time of the task } /to { end time of the task }

This will add a deadline task with specified start and end time into the current list of tasks in Chat Bot.




## Commands to edit existing Tasks

### Deleting a task
> delete { index of the task }

This will delete a task of a specifed index. To find out a index of a task use the list command.

### Rescheduling a task

#### To reschedule Deadline:
> reschedule { index of task } /by { deadline of the task }

#### To reschedule Event:
> reschedule { index of task } /from { start time of the task } /to { end time of the task }

This change the time that is previously set when creating the Event or Deadline.

### Mark a task as done
> mark { index of the task }

This will mark an existing task of that index to be done.

The task will now be displayed as:
> [T][X] example

### Mark a task as not done
> unmark { index of the task }

This will mark an existing task of that index to be not done.

The task will now be displayed as:
> [T][ ] example

## Datetime Format to follow

If you want the Deadline/Event timing to be in Datetime format, follow this structure when adding/rescheduling the tasks:

> DD/MM/YYYY TTTT

The following example

> deadline return book /by 2/12/2019 1800

will be added and diaplayed as:

> [D][ ] return book (by: 02 December 2019 18:00)

## Things to Note

Do not edit data/list.txt as it will affect the loading of the existing data on next start up.