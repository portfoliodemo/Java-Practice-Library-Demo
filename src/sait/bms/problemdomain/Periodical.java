package sait.bms.problemdomain;
/**
 *  This class is a subclass of the Books class, and contains a unique attribute of frequency.
 * @author Nathan Ryan
 *
 */
public class Periodical extends Books {

	/**
	 * The String frequency represents the specific frequency in which a Periodical is printed.
	 */
	private String frequency;
	
	public Periodical(String isbn, String callNumber, int availableBooks, int totalBooks, String title, String frequency) {
		super(isbn, callNumber, availableBooks, totalBooks, title);
		this.frequency = frequency;
	}

	/**
	 * Gets the frequency for a specific type of Book.
	 * @return the frequency
	 */
	public String getFrequency() {
		return frequency;
	}

	/**
	 * Sets the frequency for a specific type of Book.
	 * @param frequency the frequency to set
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	@Override
	public String toString() {
		return String.format("ISBN: \t\t%s\nCall Number: \t%s\nAvailable: \t%d\nTotal Copies: \t%d\nTitle: \t\t\"%s\"\nFrequency: \t%s\n", 
								getIsbn(), getCallNumber(), getAvailableBooks(), getTotalBooks(), getTitle(), getFrequency());
	}
	
	/**
	 * This method is used to write an updated list of the book catalog to file. 
	 * @return Returns a formatted string in accordance with the initial format of the book file.
	 */
	public String formatToFile() {
		return String.format("%s;%s;%d;%d;%s;%s;", 
								getIsbn(), getCallNumber(), getAvailableBooks(), getTotalBooks(), getTitle(), getFrequency());
	}

//	@Override
//	public String toString() {
//		// ISBN;Call number;Available;Total;Title;Authors;Year;Genre
//		return "Periodical - ISBN#: " + getIsbn() + ", Call Number: " + getCallNumber() + ", Copies Available: " + getAvailableBooks()
//				+ ", Total Copies: " + getTotalBooks() + ", Title: " + getTitle() + ", Frequency: " + getFrequency();
//	}
	
	
	
	
}
