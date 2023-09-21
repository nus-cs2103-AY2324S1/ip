@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM create classes directory if it doesn't exist
if not exist ..\classes mkdir ..\classes

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile each Java file separately into the classes folder
javac -d ..\classes -cp ..\src\main\java ..\src\main\java\chatterchicken\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file, and redirect the output to ACTUAL.TXT
java -classpath ..\classes chatterchicken.ChatterChicken < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
