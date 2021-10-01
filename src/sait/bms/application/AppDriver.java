package sait.bms.application;

import java.io.IOException;

import sait.bms.managers.BookManagementSystem;

/**
 * The class AppDriver is used to run the Book Management System class. 
 * This program is a book management catalog.
 * 
 * Designed in May 2020. 
 * @author Nathan Ryan
 *
 */
public class AppDriver {

	/**
	 * The main method is the starting point of the program. 
	 * @param args Command line arguments 
	 * @throws IOException throws InputOutput exception
	 */
	public static void main(String[] args) throws IOException {

		BookManagementSystem bookManager = new BookManagementSystem();
		bookManager.displayMenu();
		
	}

}
