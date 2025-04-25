# Student-Records-Management-System
Hello!

This code is a Student Records Management System that:
	1. Uses text processing to validate and standardize user input.
	2. Implements inheritance to extend the Student class into UndergraduateStudent and GraduateStudent classes.
	3. Saves and loads student records from a binary file using object serialization.
	4. Searches for students using random access file operations.
	5. Implements exception handling to manage:
		- Invalid inputs (e.g., improperly formatted names or negative GPA).
		- File errors (e.g., FileNotFoundException).
		- Serialization issues (ClassNotFoundException).
	6. Uses generic types to build reusable classes.
	7. Creates and manipulate linked lists without using Java’s built-in collections
	8. Implements selection sorting and merge sorting and searching algorithms.
		
How to use:
	When you run the code you will be introduced to the program, and given these options:
		1. Add Student
		2. Save Student Records
		3. Load Student Records
		4. Search Student by ID
		5. Sorts student by GPA
		6. Sorts student by name
		7. Display all students
		8. Display honor students
		9. Display average gpa
		10. Exit program
		

Choosing option 1 will allow you to add a student to the student records. 
It will ask if the student is a graduate or undergraduate, the name of the student, the student's ID, and the student's GPA.
If student is a graduate it will additionally ask what their thesis topic is. And if the student is an undergraduate it will ask their year level.
If user enters a letter that is not u or g, it will tell the user they have entered an incorrect option, and will ask them to enter a u or g.
It also makes sure the user adds a first and last name, and that the ID is 7 digits long.

Choosing option 2 will allow the user to save the student added to the student records.

Choosing option 3 will allow the user to load the student records, displaying whether they are a graduate or undergraduate,
name, ID, GPA, and additional fields like thesis topic and year level. 

Choosing option 4 will allow the user to search for students by ID, and then will display said student.

Choosing option 5 will sort students by GPA.

Choosing option 6 will sort students by name.

Choosing option 7 will display all students.

Choosing option 8 will display honor students.

Choosing option 9 will display average gpa.

Choosing option 10 will exit the program.
