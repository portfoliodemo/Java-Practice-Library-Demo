package sait.bms.problemdomain;

/**
 * This class is a subclass of the Books class, and contains unique attributes of publisher and diet.
 * @author Nathan Ryan
 *
 */
public class Cookbook extends Books {
	
	/**
	 * The String publisher represents the publisher(s) or a specific type of Cookbook.
	 */
	private String publisher;
	
	/**
	 * The String diet represents the specific diet type of a Cookbook.
	 */
	private String diet;
	
	/* 
	 * Constructor
	 */
	/**
	 * This constructor builds a Cookbook object with the five attributes of the book super class
	 * as well as two unique attributes. 
	 * @param isbn - Represents the ISBN number of a Book
	 * @param callNumber - Represents the Call Number of a Book
	 * @param availableBooks - Represents to number of a specific type of book that are available.
	 * @param totalBooks - Represents the total number of a specific type of book.
	 * @param title - Represents the title of a Book
	 * @param publisher - Represents the publisher(s) of a Book
	 * @param diet - Represents the diet type of a Book
	 */
	public Cookbook(String isbn, String callNumber, int availableBooks, int totalBooks, String title, String publisher, String diet) {
		super(isbn, callNumber, availableBooks, totalBooks, title);
		this.publisher = publisher;
		this.diet = diet;
	}

	/**
	 * Gets the publisher for a specific type of Book.
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * Sets the publisher for a specific type of Book.
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * Gets the diet for a specific type of Book.
	 * @return the diet
	 */
	public String getDiet() {
		return diet;
	}

	/**
	 * Sets the diet for a specific type of Book.
	 * @param diet the diet to set
	 */
	public void setDiet(String diet) {
		this.diet = diet;
	}
	
	@Override
	public String toString() {
		return String.format("ISBN: \t\t%s\nCall Number: \t%s\nAvailable: \t%d\nTotal Copies: \t%d\nTitle: \t\t\"%s\"\nPublisher(s): \t%s\nDiet: \t\t%s\n", 
								getIsbn(), getCallNumber(), getAvailableBooks(), getTotalBooks(), getTitle(), getPublisher(), getDiet());
	}
	
	/**
	 * This method is used to write an updated list of the book catalog to file. 
	 * @return Returns a formatted string in accordance with the initial format of the book file.
	 */
	public String formatToFile() {
		return String.format("%s;%s;%d;%d;%s;%s;%s", 
								getIsbn(), getCallNumber(), getAvailableBooks(), getTotalBooks(), getTitle(), getPublisher(), getDiet());
	}

//	@Override
//	public String toString() {
//		return "Cookbook [publisher=" + publisher + ", diet=" + diet + ", getPublisher()=" + getPublisher()
//				+ ", getDiet()=" + getDiet() + ", getIsbn()=" + getIsbn() + ", getCallNumber()=" + getCallNumber()
//				+ ", getAvailableBooks()=" + getAvailableBooks() + ", getTotalBooks()=" + getTotalBooks()
//				+ ", getTitle()=" + getTitle() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
//				+ ", toString()=" + super.toString() + "]";
//	}



	
}
