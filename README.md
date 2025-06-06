# java-quiz-app
📚 Overview
Java console-based Quiz Application.It allows students to register, log in, take quizzes, and view their results.
Admins can add questions and view all students' scores.

🚀 Features
Student Registration & Login
Take Multiple Choice Quizzes
Automatic Score and Grade Calculation
View Your Quiz Results
Admin Panel:
Add New Questions
View All Students’ Scores
Fetch Individual Student Scores


🛠️ Technologies Used
Java (JDK 8+)
MySQL Database
JDBC (Java Database Connectivity)

🏗️ Project Structure
src/
   com/project2/mini
   |-Main.java
   |-Student.java
   |-Admin.java
   |-Quiz.java
   |-Result.java
   |-Question.java
   |-DbManager.java


⚙️ Setup & Running
1.Clone this repository
  git clone https://github.com/75Tejas/java-quiz-app.git
2.Set up MySQL Database
  Create a database(test3)
  Update your src/db.properties file with your database URL, username, and password.
3.Compile and Run
  Open Project in IDE
  javac -d bin src/com/Project2/Mini/*.java
  java -cp bin com.Project2.Mini.Main

📝 How It Works
1.Students can register and log in.
2.After logging in, students can take a quiz (5 random questions), and their score and grade are saved.
3.Students can view their quiz results.
4.Admins can log in (default: username admin, password admin123), add new questions, and view all scores.



  
  

   

 
 
 
