/**
 * 
 */
package sait.bms.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import sait.bms.problemdomain.Books;
import sait.bms.problemdomain.ChildrensBook;
import sait.bms.problemdomain.Cookbook;
import sait.bms.problemdomain.Paperback;
import sait.bms.problemdomain.Periodical;


/**
 * This class is used to manage the operations of the Book Management System program.
 * @author Nathan Ryan
 *
 */
public class BookManagementSystem {

	// Constants
	/**
	 * This constant is used to store the relative path
	 * of the text file that is to be loaded into an
	 * ArrayList. 
	 */
	private static final String FILE_NAME = "res//books.txt";
//	private static final String FILE_NAME = "res//booksTest.txt";
	
	// Attributes
	/**
	 * This private ArrayList of Books objects contains the entire
	 * catalog of books, once loaded upon initialization of the program. 
	 */
	public ArrayList<Books> bookList;
	/**
	 * A scanner object to read input from the user. 
	 */
	private Scanner keyboard;
	
	/**
	 * This constructor creates an ArrayList of Books objects, and 
	 * initializes a Scanner object to read keyboard input. 
	 * @throws FileNotFoundException - throws FileNotFoundException
	 * 
	 */
	public BookManagementSystem() throws FileNotFoundException {
		bookList = new ArrayList<Books>();
		keyboard = new Scanner(System.in);
		
		loadBookList();
	}
	
	// ******* Private Methods *******
	
	/**
	 * This method loads a text file into an ArrayList of Book objects. 
	 * The file is read line by line, and for each line, it is split based
	 * upon a specific delimiter pattern, in this case, a comma. The split
	 * line is then parsed (if necessary), and then stored in a temporary 
	 * Book object before it is added to the book catalog ArrayList. 
	 * @throws FileNotFoundException throws FileNotFoundException
	 */
	private void loadBookList() throws FileNotFoundException {
		
		Scanner fileReader = new Scanner(new File(FILE_NAME));
		String currentLine;
		String[] splittedLine;
		Books tempBook;
		
		while (fileReader.hasNextLine()) {
			currentLine = fileReader.nextLine();
			splittedLine = currentLine.split(";");
			
			if(splittedLine[0].endsWith("0") || splittedLine[0].endsWith("1")) {
				// then create a Children's books, add it to book list
				tempBook = new ChildrensBook(splittedLine[0], splittedLine[1], Integer.parseInt(splittedLine[2]),
												Integer.parseInt(splittedLine[3]), splittedLine[4], splittedLine[5], splittedLine[6]);
				bookList.add(tempBook);
			}
			
			else if(splittedLine[0].endsWith("2") || splittedLine[0].endsWith("3")) {
				// Create a cook-book
				tempBook = new Cookbook(splittedLine[0], splittedLine[1], Integer.parseInt(splittedLine[2]),
											Integer.parseInt(splittedLine[3]), splittedLine[4], splittedLine[5], splittedLine[6]);
				
				bookList.add(tempBook);
			}
			
			else if(splittedLine[0].endsWith("4") || splittedLine[0].endsWith("5") || splittedLine[0].endsWith("6") || splittedLine[0].endsWith("7")) {
				// Create a paperback
				tempBook = new Paperback(splittedLine[0], splittedLine[1], Integer.parseInt(splittedLine[2]),
											Integer.parseInt(splittedLine[3]), splittedLine[4], splittedLine[5], 
												Integer.parseInt(splittedLine[6]), splittedLine[7]);
				
				bookList.add(tempBook);
			}
			
			else if (splittedLine[0].endsWith("8") || splittedLine[0].endsWith("9")) {
				// create a periodical
				tempBook = new Periodical(splittedLine[0], splittedLine[1], Integer.parseInt(splittedLine[2]),
											Integer.parseInt(splittedLine[3]), splittedLine[4], splittedLine[5]);
				
				bookList.add(tempBook);
			}

		} // End of file scanning.. 
		
		fileReader.close();
		System.out.println();
		System.out.println("Book Catalog has been loaded successfully!");
		System.out.println();
		
		// Test: Enhanced For to ensure the Books ArrayList is good to go!
//		for (Books list : bookList) {
//			System.out.println(list);
//		}
		
	}

	/**
	 * This method is the main menu display. A user is able to add a movie, generate
	 * a list of movies released in a year, and to generate a random list of movies
	 * based upon a user inputed number. 
	 * 
	 * There is a sentinel value which allows the program to save the updated changes
	 * to the movie catalog (if any), and exit the program. 
	 * @throws IOException throws InputOutputException
	 */
	public void displayMenu() throws IOException {
	
		int option = -1;
		
		while(option != 5) {
			System.out.println();
			System.out.println("Welcome to the ABC Book Company Catalog!");
			System.out.println("========================================");
			System.out.println("How may we assist you today?");
			System.out.println();
			System.out.println("1\tCheckout Book");
			System.out.println("2\tFind Books by Title");
			System.out.println("3\tDisplay Books by Type");
			System.out.println("4\tProduce Random Book List");
			System.out.println("5\tSave & Exit");
			System.out.println();
			
			System.out.print("Enter an option: ");
			option = keyboard.nextInt(); 
			keyboard.nextLine(); // Flush out the \n newline character from the Scanner object. 
			
			switch (option) {
			
			case 1:
				checkoutBook();
				break;
				
			case 2: 
				findByTitle();
				break;
				
			case 3:
				displayByType();
				break;
				
			case 4:
				randomBookList();
				break;
				
			case 5:
				saveChanges();
				System.out.println();
				System.out.println("Updating book catalog (if necessary), and exiting the program..");
				System.out.println("..Goodbye!");
				break;
				
			default:
				System.out.println();
				System.out.println("Invalid entry! Please enter in a valid option between 1-5.");
				System.out.println();
				break;
			}
		}
	}

	/**
	 * This method enables a user to search for a book using an ISBN#. 
	 * If the book is unavailable, the user will be informed and the program goes back to the main menu.
	 * If the book is available, the user will be prompted to see if they would like to check out the book,
	 * if so, available count will be decremented and the book info will be displayed
	 */
	private void checkoutBook() {
		
		/* Allows a patron to checkout a book using its ISBN
		* If the book is unavailable, the user will be informed and the program goes back to the main menu
		* If the book IS available, the available count will be decremented and the book info will be displayed. */ 
		String userIsbn;
		String checkoutAnswer;
		
		System.out.println(); // Blank line for design/readability
		System.out.print("Enter the ISBN# to search for: ");
		// This is where I have to search for a matching ISBN
		// Does the newline character have to be flushed from the scanner? 
		// User enters the ISBN# they would like to look up, then the Books list is looped to find a match
		userIsbn = keyboard.nextLine();
		
		// Search the book catalog
		for (int i = 0; i < bookList.size(); i++) {
			
			// Find a matching ISBN
			if (bookList.get(i).getIsbn().contentEquals(userIsbn)) {
				// A match has been made, now check the availability etc
				System.out.println("You searched for \"" + bookList.get(i).getTitle() + "\".");
				System.out.println("Using the ISBN#: " + userIsbn + ".");
				
				// Check if book is a periodical
				if (bookList.get(i).getIsbn().endsWith("8") || bookList.get(i).getIsbn().endsWith("9"))  {
					System.out.println();
					System.out.println("Please be advised that periodicals cannot be checked out at this time.");
					System.out.println("Enjoy this periodical within our library using the call number shown below.");
					System.out.println("Call number: " + bookList.get(i).getCallNumber() + ".");
				}

				System.out.println();
				// Check that there are copies available..
				if(bookList.get(i).getAvailableBooks() == 0) {
					// System.out.println();
					System.out.println("There are currently " + bookList.get(i).getAvailableBooks() + " copies of " 
										+ bookList.get(i).getTitle() + " available.");
					System.out.println("The library currently has " + bookList.get(i).getTotalBooks() + " total copies " 
										+ "of \"" + bookList.get(i).getTitle() + "\".");
					System.out.println("Please try again later.");
				}
				// If there are copies available, prompt the user to checkout
				else {
					System.out.print("Would you like to check out \"" + bookList.get(i).getTitle() + "\"? (Y/N): " );
					checkoutAnswer = keyboard.nextLine();
					
					if(checkoutAnswer.equalsIgnoreCase("Y")){
						System.out.println();
						System.out.println("You are now checking out: \"" + bookList.get(i).getTitle() + "\".");
						bookList.get(i).setAvailableBooks(bookList.get(i).getAvailableBooks() - 1);
						// Decrement the total available book counter, again minus one here okay?.. 
						//bookList.get(i).setAvailableBooks(bookList.get(i).getTotalBooks() - 1);
						//How to decrement the available books? 
						System.out.println("The book can be located using the call number: " + bookList.get(i).getCallNumber());
						// Print out the new book total -- test
						System.out.println("The number of copies of \"" + bookList.get(i).getTitle() + "\" available is " 
											+ bookList.get(i).getAvailableBooks() + ".");
					}
					else if(checkoutAnswer.equalsIgnoreCase("N")) {
						System.out.println();
						System.out.println("Returning to the Main Menu..");
					}
				}
					
			}
						
		}
				
	}
	
	/**
	 * This method returns a formatted result based upon a user entered query. It runs by scanning the books catalog 
	 * to determine whether or not there is a match between the user query and the title. 
	 */
	private void findByTitle() {
		// Allows a patron to enter a search term and the program will display a list of books that have a 
		// title matching the search term. 
		
		// Local Variables
		String userSearch ="";
		System.out.println();
		
		// Prompt the user to enter their search term, saving as userSearch
		System.out.print("Enter title to search for: ");
		userSearch = keyboard.nextLine();
		
		// Scan the book catalog for any matches in the title..
		for (int i = 0; i < bookList.size(); i++) {
//			if(bookList.get(i).getTitle().contains(userSearch.equalsIgnoreCase(userSearch))) {
//			if(bookList.get(i).getTitle().equalsIgnoreCase(userSearch)) {
//			if(bookList.get(i).getTitle().e
			
			if(bookList.get(i).getTitle().toLowerCase().contains(userSearch.toLowerCase())) {
				System.out.println();
				System.out.println("ISBN: " + bookList.get(i).getIsbn());
				System.out.println("Call Number: " + bookList.get(i).getCallNumber());
				System.out.println("Available: " + bookList.get(i).getAvailableBooks());
				System.out.println("Total: " + bookList.get(i).getTotalBooks());
				System.out.println("Title: " + bookList.get(i).getTitle());
				// Next part dependent upon class of Book!
				
				// If book is a Children's book..
				if(bookList.get(i).getIsbn().endsWith("0") || bookList.get(i).getIsbn().endsWith("1")) {
					System.out.println("Author(s): " + ((ChildrensBook) bookList.get(i)).getAuthors()); // Review casting* //
					System.out.println("Format: " + ((ChildrensBook) bookList.get(i)).getFormat());
					//System.out.println();
				}
				
				// If book is a Cook-book
				if(bookList.get(i).getIsbn().endsWith("2") || bookList.get(i).getIsbn().endsWith("3")) {
					System.out.println("Publisher: " + ((Cookbook) bookList.get(i)).getPublisher());
					System.out.println("Diet: " + ((Cookbook) bookList.get(i)).getDiet());
					//System.out.println();
				}
				
				if(bookList.get(i).getIsbn().endsWith("4") || bookList.get(i).getIsbn().endsWith("5") 
						|| bookList.get(i).getIsbn().endsWith("6") || bookList.get(i).getIsbn().endsWith("7")) {
					System.out.println("Author(s): " + ((Paperback) bookList.get(i)).getAuthors());
					System.out.println("Year: " + ((Paperback) bookList.get(i)).getYear());
					System.out.println("Genre: " + ((Paperback) bookList.get(i)).getGenre());
					//System.out.println();
				}
				
				if(bookList.get(i).getIsbn().endsWith("8") || bookList.get(i).getIsbn().endsWith("9")) {
					System.out.println("Frequency: " + ((Periodical) bookList.get(i)).getFrequency());
					System.out.println();
					System.out.println("-- Please note periodicals cannot be checked out of the library. --");
					//System.out.println();
				}
				
			}
		}
		
	}

	/**
	 * Allows a patron to view a list of books with a specific type. The user will also enter a
	 * format, diet, genre, or frequency (depending on the type of book) and the book list will
 	 * be narrowed down further.
	 */
	private void displayByType() {
		// Allows a patron to view a list of books with a specific type. The user will also enter a
		// format, diet, genre, or frequency (depending on the type of book) and the book list will
		// be narrowed down further.
		
		// User Input
		/**
		 * 
		 */
		String userTypeSelection;
		String userFormatSelection;
		
		// Title for Display by Type Menu
		System.out.println();
		System.out.println("#\t Type");
		System.out.println("====================");
		System.out.println("1\tChildren's Books");
		System.out.println("2\tCookbooks");
		System.out.println("3\tPaperbacks");
		System.out.println("4\tPeriodicals");
		System.out.println();
		System.out.print("Enter type of book: ");
		userTypeSelection = keyboard.nextLine();
		// Depending on what the user enters, prompt to enter a format, etc.. 
		
		switch (userTypeSelection) {
		
		case "1":
			System.out.print("Enter a format: (P for Picture Book, E for Early Readers or C for Chapter Book): ");
			// Now the user enters their choice.. 
			userFormatSelection = keyboard.nextLine().toLowerCase();
			// Search through the books and print out those specific to the user query..
			
			switch (userFormatSelection) {
			
			case "p":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If it's a Children's book..
					if(bookList.get(i).getIsbn().endsWith("0") || bookList.get(i).getIsbn().endsWith("1")) {
						// Print out the Picture Book(s)
						if (((ChildrensBook) bookList.get(i)).getFormat().startsWith("P")) {
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Author(s): \t" + ((ChildrensBook) bookList.get(i)).getAuthors());
							System.out.println("Format: \tPicture Book");
							System.out.println();
						}
					}
				}
				break;
				
			case "e":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If it's a Children's book..
					if(bookList.get(i).getIsbn().endsWith("0") || bookList.get(i).getIsbn().endsWith("1")) {
						// Print out the Early Readers Book(s)
						if (((ChildrensBook) bookList.get(i)).getFormat().startsWith("E")) {
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Author(s): \t" + ((ChildrensBook) bookList.get(i)).getAuthors()); 
							System.out.println("Format: \tEarly Readers");
							System.out.println();
						}
					}
				}
				break;
				
			case "c":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If it's a Children's book..
					if(bookList.get(i).getIsbn().endsWith("0") || bookList.get(i).getIsbn().endsWith("1")) {
						// Print out the Chapter Book(s)
						if (((ChildrensBook) bookList.get(i)).getFormat().startsWith("C")) {
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Author(s): \t" + ((ChildrensBook) bookList.get(i)).getAuthors()); 
							System.out.println("Format: \tChapter Book");
//							System.out.println("Format: \t" + ((ChildrensBook) bookList.get(i)).getFormat());
							System.out.println();
						}
					}
				}
				break;
				
			default:
				System.out.println("Please enter a valid letter to search by format.");
				break;
			}
			break;

		case "2":
			System.out.print("Enter a diet: (D for Diabetic, V for Vegetarian, G for Gluten-free, I for International or N for None): ");
			// Now the user enters their choice.. 
			userFormatSelection = keyboard.nextLine().toLowerCase();
			// Search through the books and print out those specific to the user query..
			switch (userFormatSelection) {
			case "d":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If it's a Cook-book..
					if(bookList.get(i).getIsbn().endsWith("2") || bookList.get(i).getIsbn().endsWith("3")) {
						// Print out the Diabetic Book(s)
						if (((Cookbook) bookList.get(i)).getDiet().startsWith("D")) {
							//Custom toString to reduce redundancy?!.. 
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Publisher: \t" + ((Cookbook) bookList.get(i)).getPublisher()); 
							System.out.println("Format: \tDiet");
							System.out.println();
						}
					}
				}
				break;
				
			case "v":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If it's a Cook-book..
					if(bookList.get(i).getIsbn().endsWith("2") || bookList.get(i).getIsbn().endsWith("3")) {
						// Print out the Vegetarian Book(s)
						if (((Cookbook) bookList.get(i)).getDiet().startsWith("V")) {
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Publisher: \t" + ((Cookbook) bookList.get(i)).getPublisher()); 
							System.out.println("Format: \tVegetarian");
							System.out.println();
						}
					}
				}
				break;
				
			case "g":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If it's a Cook-book..
					if(bookList.get(i).getIsbn().endsWith("2") || bookList.get(i).getIsbn().endsWith("3")) {
						// Print out the Gluten free Book(s)
						if (((Cookbook) bookList.get(i)).getDiet().startsWith("G")) {
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Publisher: \t" + ((Cookbook) bookList.get(i)).getPublisher()); 
							System.out.println("Format: \tGluten Free");
							System.out.println();
						}
					}
				}
				break;
				
			case "i":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If it's an International book..
					if(bookList.get(i).getIsbn().endsWith("2") || bookList.get(i).getIsbn().endsWith("3")) {
						// Print out the International Book(s)
						if (((Cookbook) bookList.get(i)).getDiet().startsWith("G")) {
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Publisher: \t" + ((Cookbook) bookList.get(i)).getPublisher()); 
							System.out.println("Format: \tInternational");
							System.out.println();
						}
					}
				}
				break;
				
			case "n":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If no diet is selected..
					if(bookList.get(i).getIsbn().endsWith("2") || bookList.get(i).getIsbn().endsWith("3")) {
						// Print out the Chapter Book(s)
						if (((Cookbook) bookList.get(i)).getDiet().startsWith("N")) {
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Publisher: \t" + ((Cookbook) bookList.get(i)).getPublisher()); 
							System.out.println("Format: \tNone");
							System.out.println();
						}
					}
				}
				break;
				
			default:
				System.out.println("Please enter a valid letter to search by Diet.");
				break;
			} 
			break;	
			
		case "3":
			System.out.print("Enter a genre: (A for Adventure, D for Drama, E for Education, C for Classic, F for Fantasy or S for Science Fiction): ");
			// Now the user enters their choice.. 
			userFormatSelection = keyboard.nextLine().toLowerCase();
			// Search through the books and print out those specific to the user query..
			switch (userFormatSelection) {
			
			case "a":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If it's a Paperback
					if(bookList.get(i).getIsbn().endsWith("4") || bookList.get(i).getIsbn().endsWith("5")
							|| bookList.get(i).getIsbn().endsWith("6") || bookList.get(i).getIsbn().endsWith("7")) {
						// Print out the Adventure Book(s)
						if (((Paperback) bookList.get(i)).getGenre().startsWith("A")) {
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Author(s): \t" + ((Paperback) bookList.get(i)).getAuthors());
							System.out.println("Year: \t\t" + ((Paperback) bookList.get(i)).getYear());
							System.out.println("Genre: \t\tAdventure");
							System.out.println();
						}
					}
				}
				break;
				
			case "d":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If it's a Paperback
					if(bookList.get(i).getIsbn().endsWith("4") || bookList.get(i).getIsbn().endsWith("5")
							|| bookList.get(i).getIsbn().endsWith("6") || bookList.get(i).getIsbn().endsWith("7")) {
						// Print out the Drama Book(s)
						if (((Paperback) bookList.get(i)).getGenre().startsWith("D")) {
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Author(s): \t" + ((Paperback) bookList.get(i)).getAuthors()); 
							System.out.println("Year: \t\t" + ((Paperback) bookList.get(i)).getYear());
							System.out.println("Genre: \t\tDrama");
							System.out.println();
						}
					}
				}
				break;
				
			case "e":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If it's a Paperback
					if(bookList.get(i).getIsbn().endsWith("4") || bookList.get(i).getIsbn().endsWith("5")
							|| bookList.get(i).getIsbn().endsWith("6") || bookList.get(i).getIsbn().endsWith("7")) {
						// Print out the Education Book(s)
						if (((Paperback) bookList.get(i)).getGenre().startsWith("E")) {
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Author(s): \t" + ((Paperback) bookList.get(i)).getAuthors()); 
							System.out.println("Year: \t\t" + ((Paperback) bookList.get(i)).getYear());
							System.out.println("Genre: \t\tEducation");
							System.out.println();
						}
					}
				}
				break;
				
			case "c":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If it's a Paperback..
					if(bookList.get(i).getIsbn().endsWith("4") || bookList.get(i).getIsbn().endsWith("5")
							|| bookList.get(i).getIsbn().endsWith("6") || bookList.get(i).getIsbn().endsWith("7")) {
						// Print out the Classic paperback(s)
						if (((Paperback) bookList.get(i)).getGenre().startsWith("C")) {
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Author(s): \t" + ((Paperback) bookList.get(i)).getAuthors()); 
							System.out.println("Year: \t\t" + ((Paperback) bookList.get(i)).getYear());
							System.out.println("Genre: \t\tClassic");
							System.out.println();
						}
					}
				}
				break;
				
			case "f":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If no diet is selected..
					if(bookList.get(i).getIsbn().endsWith("4") || bookList.get(i).getIsbn().endsWith("5")
							|| bookList.get(i).getIsbn().endsWith("6") || bookList.get(i).getIsbn().endsWith("7")) {
						// Print out the Fantasy paperback(s)
						if (((Paperback) bookList.get(i)).getGenre().startsWith("F")) {
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Author(s): \t" + ((Paperback) bookList.get(i)).getAuthors()); 
							System.out.println("Year: \t\t" + ((Paperback) bookList.get(i)).getYear());
							System.out.println("Genre: \t\tFantasy");
							System.out.println();
						}
					}
				}
				break;
				
			case "s":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If no diet is selected..
					if(bookList.get(i).getIsbn().endsWith("4") || bookList.get(i).getIsbn().endsWith("5")
							|| bookList.get(i).getIsbn().endsWith("6") || bookList.get(i).getIsbn().endsWith("7")) {
						// Print out the Science Fiction paperback(s)
						if (((Paperback) bookList.get(i)).getGenre().startsWith("S")) {
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Author(s): \t" + ((Paperback) bookList.get(i)).getAuthors()); 
							System.out.println("Year: \t\t" + ((Paperback) bookList.get(i)).getYear());
							System.out.println("Genre: \t\tScience Fiction");
							System.out.println();
						}
					}
				}
				break;
				
			default:
				System.out.println("Please enter a valid letter to search by genre.");
				break;
			}
			break;
				
		case "4":
			System.out.print("Enter a frequency: (D for Daily, W for Weekly,  M for Monthly, B for Bimonthly or Q for Quarterly): ");
			// Now the user enters their choice.. 
			userFormatSelection = keyboard.nextLine().toLowerCase();
			// Search through the books and print out those specific to the user query..
			switch (userFormatSelection) {
			case "d":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If it's a Periodical..
					if(bookList.get(i).getIsbn().endsWith("8") || bookList.get(i).getIsbn().endsWith("9")) {
						// Print out the Daily(s)
						if (((Periodical) bookList.get(i)).getFrequency().startsWith("D")) {
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Frequency: \tDaily");
							System.out.println();
						}
					}
				}
				break;
				
			case "w":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If it's a Periodical..
					if(bookList.get(i).getIsbn().endsWith("8") || bookList.get(i).getIsbn().endsWith("9")) {
						// Print out the Weekly(s)
						if (((Periodical) bookList.get(i)).getFrequency().startsWith("W")) {
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Frequency: \tWeekly");
							System.out.println();
						}
					}
				}
				break;
				
			case "m":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If it's a Periodical..
					if(bookList.get(i).getIsbn().endsWith("8") || bookList.get(i).getIsbn().endsWith("9")) {
						// Print out the Monthly(s)
						if (((Periodical) bookList.get(i)).getFrequency().startsWith("M")) {
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Frequency: \tMonthly");
							System.out.println();
						}
					}
				}
				break;
				
			case "b":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If it's a Periodical..
					if(bookList.get(i).getIsbn().endsWith("8") || bookList.get(i).getIsbn().endsWith("9")) {
						// Print out the Bi-Monthly(s)
						if (((Periodical) bookList.get(i)).getFrequency().startsWith("B")) {
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Frequency: \tBi-Monthly");
							System.out.println();
						}
					}
				}
				break;
				
			case "q":
				System.out.println();
				System.out.println("Matching books: ");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// If it's a Periodical..
					if(bookList.get(i).getIsbn().endsWith("8") || bookList.get(i).getIsbn().endsWith("9")) {
						// Print out the Quarterly(s)
						if (((Periodical) bookList.get(i)).getFrequency().startsWith("Q")) {
							System.out.println("ISBN: \t\t" + bookList.get(i).getIsbn());
							System.out.println("Call Number: \t" + bookList.get(i).getCallNumber());
							System.out.println("Available: \t" + bookList.get(i).getAvailableBooks());
							System.out.println("Total #: \t" + bookList.get(i).getTotalBooks());
							System.out.println("Title: \t\t\"" + bookList.get(i).getTitle() + "\"");
							System.out.println("Frequency: \tQuarterly)");
							System.out.println();
						}
					}
				}
				break;
				
			default:
				System.out.println("Please enter a valid letter to search by frequency.");
				break;
			}
			
//		default:
//			System.out.println("Please enter a valid selection.");
//			break;	
			
		} // *** End of outer switch ***
		
	} // *** End of displayType() that is waaayy too long.. *** 

	/**
	 * This method generates a list of random books the size of which is
	 * dependent upon a user inputed number, formatted individually based
	 * upon the specific type of book that is printed.
	 */
	private void randomBookList() {
		// Allows a patron to print a list of random books. The list of books can contain any type of book. 
		System.out.println();
		System.out.println("Random Book Generator");
		System.out.println("======================");
		System.out.println();
		System.out.print("Enter in a random number to see a list of that many books!: ");
		int randomNumber = keyboard.nextInt();
		keyboard.nextLine();
		System.out.println();
		
		System.out.println("Book List");
		
		Collections.shuffle(bookList);
		
		for (int i = 0; i < randomNumber; i++) {		
			
			//System.out.println(bookList.get(i).formatToScreen());
			// Print out the random number of books.. 
			// Based on what type of book the ISBN, print out the specific book type
			//System.out.println(bookList.get(i).getIsbn());
			System.out.println(bookList.get(i).toString());
			// System.out.println(bookList.get(i).toString()); <-- This works! Clean up to toStrings!!

		}
		
		System.out.println();
		
	}
	
	/**
	 * This method saves the changes made to the movie catalog, and updates 
 	 * the source file accordingly. 
	 * @throws IOException throws an InputOutput exception 
	 */
	private void saveChanges() throws IOException {
		
		/*  Also able to use "eager" writing
		 *  FileWriter movieWriter = new FileWriter(FILE_NAME, true);
		 */
		FileWriter bookWriter = new FileWriter(FILE_NAME);
		PrintWriter outputFile = new PrintWriter(bookWriter);
		
		for (int i = 0; i < bookList.size(); i++) {
			
			if (i == bookList.size() -1) {
				// Don't print a newline character for the last book!
				// Check which type of book it is, then print to file based on that
				if(bookList.get(i).getIsbn().endsWith("0") || bookList.get(i).getIsbn().endsWith("1")) {
					// For a children's book.. etc
					outputFile.print(((ChildrensBook) bookList.get(i)).formatToFile());
				}
				
				else if(bookList.get(i).getIsbn().endsWith("2") || bookList.get(i).getIsbn().endsWith("3")) {
					// For a children's book.. etc
					outputFile.print(((Cookbook) bookList.get(i)).formatToFile());
				}

				else if(bookList.get(i).getIsbn().endsWith("4") || bookList.get(i).getIsbn().endsWith("5") 
						|| bookList.get(i).getIsbn().endsWith("6") || bookList.get(i).getIsbn().endsWith("7")) {
					// For a children's book.. etc
					outputFile.print(((Paperback) bookList.get(i)).formatToFile());
				}
				
				else if (bookList.get(i).getIsbn().endsWith("8") || bookList.get(i).getIsbn().endsWith("9")) {
					outputFile.print(((Periodical) bookList.get(i)).formatToFile());
				}
			}
			// Check which type of book it is, then print to file based on that
			else if(bookList.get(i).getIsbn().endsWith("0") || bookList.get(i).getIsbn().endsWith("1")) {
				// For a children's book.. etc
				outputFile.println(((ChildrensBook) bookList.get(i)).formatToFile());
			}
			
			else if(bookList.get(i).getIsbn().endsWith("2") || bookList.get(i).getIsbn().endsWith("3")) {
				// For a children's book.. etc
				outputFile.println(((Cookbook) bookList.get(i)).formatToFile());
			}

			else if(bookList.get(i).getIsbn().endsWith("4") || bookList.get(i).getIsbn().endsWith("5") 
					|| bookList.get(i).getIsbn().endsWith("6") || bookList.get(i).getIsbn().endsWith("7")) {
				// For a children's book.. etc
				outputFile.println(((Paperback) bookList.get(i)).formatToFile());
			}
			
			else if (bookList.get(i).getIsbn().endsWith("8") || bookList.get(i).getIsbn().endsWith("9")) {
				outputFile.println(((Periodical) bookList.get(i)).formatToFile());
			}
			
		}
		
		outputFile.close();
	}


//	private void saveChanges() {
//		// TODO Auto-generated method stub
//		System.out.println("Saving & Exiting");
//		
//	}
	
//	private void displayBookInfo() {
//		System.out.println("ISBN: " + bookList.get(i).getIsbn());
//		System.out.println("Call Number: " + bookList.get(i).getCallNumber());
//		System.out.println("Available: " + bookList.get(i).getAvailableBooks());
//		System.out.println("Total #: " + bookList.get(i).getTotalBooks());
//		System.out.println("Title: \"" + bookList.get(i).getTitle() + "\"");
//	}


}
