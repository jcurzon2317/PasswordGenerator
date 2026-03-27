@echo off
javac *.java
if errorlevel 1 (
    echo.
    echo Build failed.
    pause
    exit /b 1
)

echo.
echo Running PasswordGeneratorApp...
echo.
java PasswordGeneratorApp

echo.
pause