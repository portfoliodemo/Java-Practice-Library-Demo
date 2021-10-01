/**
 * 
 */
package sait.bms.problemdomain;

/**
 * This class is a subclass of the Books class, and contains unique attributes of authors and format.
 * @author Nathan Ryan
 *
 */
public class ChildrensBook extends Books {

	/**
	 * The String authors represents the author(s) of a ChildrensBook.
	 */
	private String authors;
	
	/**
	 * The String format represents the format of a specific type of ChildrensBook.
	 */
	private String format;
	
	/**
	 * This constructor builds a ChildrensBook object with the five attributes of the book super class
	 * as well as two unique attributes, authors and format. 
	 * @param isbn - Represents the ISBN number of a Book
	 * @param callNumber - Represents the Call Number of a Book
	 * @param availableBooks - Represents to number of a specific type of book that are available.
	 * @param totalBooks - Represents the total number of a specific type of book.
	 * @param title - Represents the title of a Book
	 * @param authors - Represents the publisher(s) of a Book
	 * @param format - Represents the diet type of a Book
	 */
	public ChildrensBook(String isbn, String callNumber, int availableBooks, int totalBooks, String title, String authors, String format) {
		super(isbn, callNumber, availableBooks, totalBooks, title);
		this.authors = authors;
		this.format = format;
	}

	/**
	 * Gets the authors for a specific type of Book.
	 * @return the authors
	 */
	public String getAuthors() {
		return authors;
	}

	/**
	 * Sets the authors for a specific type of Book.
	 * @param authors the authors to set
	 */
	public void setAuthors(String authors) {
		this.authors = authors;
	}

	/**
	 * Gets the format for a specific type of Book.
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * Sets the format for a specific type of Book.
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	
//	@Override
//	public String toString() {
//		return "ChildrensBook [authors=" + authors + ", format=" + format + ", getIsbn()=" + getIsbn()
//				+ ", getCallNumber()=" + getCallNumber() + ", getAvailableBooks()=" + getAvailableBooks()
//				+ ", getTotalBooks()=" + getTotalBooks() + ", getTitle()=" + getTitle() + ", getClass()=" + getClass()
//				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
//	}
	@Override
	public String toString() {
		return String.format("ISBN: \t\t%s\nCall Number: \t%s\nAvailable: \t%d\nTotal Copies: \t%d\nTitle: \t\t\"%s\"\nAuthor(s): \t%s\nFormat: \t%s\n", 
								getIsbn(), getCallNumber(), getAvailableBooks(), getTotalBooks(), getTitle(), getAuthors(), getFormat());
	}
	
//	public String formatToFile() {
//		return String.format(format, args)
//	}
	/**
	 * This method formats the display of the attributes of a Children's Book object,
	 * @return Returns a Children's book object displayed in a specific format. 
	 */
	public String formatToScreen() {
		return String.format("ISBN: \t\t%s\nCall Number: \t%s\nAvailable: \t%d\nTotal Copies: \t%d\nTitle: \t\t\"%s\"\nAuthor(s): \t%s\nFormat: \t%s\n", 
								getIsbn(), getCallNumber(), getAvailableBooks(), getTotalBooks(), getTitle(), getAuthors(), getFormat());
	}
	
	/**
	 * This method is used to write an updated list of the book catalog to file. 
	 * @return Returns a formatted string in accordance with the initial format of the book file.
	 */
	public String formatToFile() {
		return String.format("%s;%s;%d;%d;%s;%s;%s", 
								getIsbn(), getCallNumber(), getAvailableBooks(), getTotalBooks(), getTitle(), getAuthors(), getFormat());
	}

}
