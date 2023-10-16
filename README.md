[![CI Status](https://github.com/Kurtyjlee/ip/workflows/Java%20CI/badge.svg)](https://github.com/Kurtyjlee/ip/actions)

# CringeBot - The chatbot to manage your tasks

This is a project derived from the  starter project `Duke`. CringeBot has been given a GUI skin and additional CLI features to help you with your everyday tasks

## User Guide

Follow the User Guide to download CringeBot and use it on your computer! More information on CringeBot can be found [here](https://kurtyjlee.github.io/ip/).

## Setting up a development environment with Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/CringeBot/Main.java` file, right-click it, and choose `Run CringeBot.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something similar to the image below:

![Ui](docs/Ui.png)
