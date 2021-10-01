/**
 * 
 */
package sait.bms.problemdomain;

/**
 * 
 * This class is a subclass of the Books class, and contains unique attributes of authors, year and genre.
 * @author Nathan Ryan
 */
public class Paperback extends Books {
	
	/**
	 * The String authors represents the author(s) of a specific Paperback.
	 */
	private String authors;
	
	/**
	 * The int year represents the year of a specific Paperback.
	 */
	private int year;
	
	/**
	 * The String genre represents the genre of a specific type of Paperback.
	 */
	private String genre;

	/**
	 * This constructor builds a Paperback object with the five attributes of the book super class
	 * as well as three unique attributes, authors, year and genre. 
	 * @param isbn - Represents the ISBN number of a Book
	 * @param callNumber - Represents the Call Number of a Book
	 * @param availableBooks - Represents to number of a specific type of book that are available.
	 * @param totalBooks - Represents the total number of a specific type of book.
	 * @param title - Represents the title of a Book
	 * @param authors - Represents the publisher(s) of a Book
	 * @param year - Represents the author(s) of a Book
	 * @param genre - Represents the genre of a Book
	 */
	public Paperback(String isbn, String callNumber, int availableBooks, int totalBooks, String title, String authors, int year, String genre) {
		super(isbn, callNumber, availableBooks, totalBooks, title);
		this.authors = authors;
		this.year = year;
		this.genre = genre;
	}
	
	/** 
	 * Get's the author(s) for a specific type of Book.
	 * @return the authors
	 */
	public String getAuthors() {
		return authors;
	}

	/**
	 * Sets the authors for a specific type of Book.
	 * @param authors the author(s) to set
	 */
	public void setAuthors(String authors) {
		this.authors = authors;
	}

	/**
	 * Gets the year for a specific type of Book.
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets the year for a specific type of Book.
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * Gets the genre for a specific type of Book.
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * Sets the genre for a specific type of Book.
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	@Override
	public String toString() {
		return String.format("ISBN: \t\t%s\nCall Number: \t%s\nAvailable: \t%d\nTotal Copies: \t%d\nTitle: \t\t\"%s\"\n"
								+ "Author(s): \t%s\nYear: \t\t%d\nGenre: \t\t%s\n", 
									getIsbn(), getCallNumber(), getAvailableBooks(), getTotalBooks(), getTitle(), 
										getAuthors(), getYear(), getGenre());
	}
	
	/**
	 * This method is used to write an updated list of the book catalog to file. 
	 * @return Returns a formatted string in accordance with the initial format of the book file.
	 */
	public String formatToFile() {
		return String.format("%s;%s;%d;%d;%s;%s;%d;%s", 
								getIsbn(), getCallNumber(), getAvailableBooks(), getTotalBooks(), getTitle(),
									getAuthors(), getYear(), getGenre());
	}

//	@Override
//	public String toString() {
//		return "Paperback [authors=" + authors + ", year=" + year + ", genre=" + genre + ", getAuthors()="
//				+ getAuthors() + ", getYear()=" + getYear() + ", getGenre()=" + getGenre() + ", getIsbn()=" + getIsbn()
//				+ ", getCallNumber()=" + getCallNumber() + ", getAvailableBooks()=" + getAvailableBooks()
//				+ ", getTotalBooks()=" + getTotalBooks() + ", getTitle()=" + getTitle() + ", getClass()=" + getClass()
//				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
//	}

	
	

}
