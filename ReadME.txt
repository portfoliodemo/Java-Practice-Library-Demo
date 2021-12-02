
===========================
= Book Management System =
===========================

The Book Management System is a dynamic catalog of books.

The user has five options when the program is launched:

Option 1: Checkout a book 

User is prompted to checkout a book based on an ISBN number. 
If it is an incorrect ISBN, the user will return to the main menu.
If the ISBN has a match, the user will be prompted and asked if they
would like to check out a book. If they select no, they return to the 
main menu, if they select yes then the book is checked out and the 
number of available books for that specific book is decremented by one. 

Option 2: Find books by title 

User is prompted to enter in all or part of a book title, and if there
is a match then all books with all or part of the user search are displayed. 

Option 3: Display books by type

User is prompted to specify which sub-category of book they would like to 
search. Children's Book, Cook-book, Paperback or Periodical.

After selecting the sub-category, the user is prompted to further specify
which format, diet, genre or frequency they would like to search by 
specific to whichever type of book they chose. 
	
Option 4: Produce a random list of books 

The user is prompted to specify a random number, and based upon that number
a list of random books is displayed, formatted specifically for each
sub-category of book.
	
Option 5: Save & Exit the program

The program writes the updated Book information back to the original file
and terminates the program. 

This read me was created during the months of May and June, 2020. 

Running this program:
=====================

To run this program the user needs to access the runnable jar file from 
their root file directory where this program is saved on their local machine.

For example if the jar file is located in C:\users\java

- Navigate to that directory through the command prompt
  or by typing in 'cmd' in your window explorer,
  to launch the command prompt with the root directory loaded.
- From the root directory type in 

	java -jar LibraryApp.jar

Program is launched! 


