package sait.bms.problemdomain;

/**
 * This class will be the Super class that serves as a base for all
 * subsequent book type classes. Children's Books, Cookbooks, Paperbacks and Periodicals 
 * @author Nathan Ryan
 *
 */
public class Books {
	
	/*
	 * All books will extend from this class, this is the super class
	 * and cannot be instantiated. 
	 */
	
	// Represents the unique thirteen digit number assigned to books. 
	// The last digit of this primitive data type is super important as 
	// I will need it to create and sort the books.. whoa.. 
	/**
	 * The String isbn represents the ISBN# of a book.
	 */
	private String isbn;
	
	/**
	 * The String callNumber represents the Call Number of a Book.
	 */
	private String callNumber;
	
	/**
	 * The int availableBooks represents the number of a specific book currently available.
	 */
	private int availableBooks;
	
	/**
	 * The int totalBooks represents the total number of copies of a specific book within the library catalog.
	 */
	private int totalBooks;
	
	/**
	 * The String title represents the title of a Book.
	 */
	private String title;
	
	// Create the books constructor
	/**
	 * This constructor creates a Books object with the inputs of isbn, callNumber, availableBooks, totalBooks and title.
	 * @param isbn - Represents the ISBN number of a Book
	 * @param callNumber - Represents the Call Number of a Book
	 * @param availableBooks - Represents to number of a specific type of book that are available.
	 * @param totalBooks - Represents the total number of a specific type of book.
	 * @param title - Represents the title of a Book
	 */
	public Books(String isbn, String callNumber, int availableBooks, int totalBooks, String title) {
		
		this.isbn = isbn;
		this.callNumber = callNumber;
		this.availableBooks = availableBooks;
		this.totalBooks = totalBooks;
		this.title = title;
	}
	
	/**
	 * Gets the ISBN# for a specific type of Book.
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}
	/**
	 * Sets the ISBN# for a specific type of Book.
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	/**
	 * Gets the Call Number for a specific type of Book.
	 * @return the callNumber
	 */
	public String getCallNumber() {
		return callNumber;
	}
	/**
	 * Sets the Call Number for a specific type of Book.
	 * @param callNumber the callNumber to set
	 */
	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}
	/**
	 * Gets the available book number for a specific type of Book.
	 * @return the availableBooks
	 */
	public int getAvailableBooks() {
		return availableBooks;
	}
	/**
	 * Sets the available book number for a specific type of Book.
	 * @param availableBooks the availableBooks to set
	 */
	public void setAvailableBooks(int availableBooks) {
		this.availableBooks = availableBooks;
	}
	/**
	 * Gets the total number of books for a specific type of Book.
	 * @return the totalBooks
	 */
	public int getTotalBooks() {
		return totalBooks;
	}
	/**
	 * Sets the total number of books for a specific type of Book.
	 * @param totalBooks the totalBooks to set
	 */
	public void setTotalBooks(int totalBooks) {
		this.totalBooks = totalBooks;
	}
	/**
	 * Gets the title for a specific type of Book.
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Sets the title for a specific type of Book.
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
}
