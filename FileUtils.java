/*
File Utilities for reading and writing
8/25/22
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileUtils
{
	/**
	 * Opens a file to read using Scanner class
	 * @param fileName	name of the file to open
	 * @return			the Scanner object to the file
	 */
	 public static java.util.Scanner openToRead(String fileName)
	 {
		java.util.Scanner input = null;
		try
		{
			input = new java.util.Scanner(new File(fileName));
		}
		catch (java.io.FileNotFoundException e)
		{
			System.err.println("ERROR: Cannot open " + fileName + " for reading.");
			System.exit(-1);
		}	
		return input;
	}
	
	/** 
	 * Opens a file to write using PrintWriter class
	 * @param fileName	name of the file to open
	 * @return			the PrintWriter object to the file
	 */
	 public static PrintWriter openToWrite(String fileName)
	 {
		PrintWriter output = null;
		try
		{
			output = new PrintWriter(new File(fileName));
		}
		catch (FileNotFoundException e)
		{
			System.err.println("ERROR: Cannot open " + fileName + " for writing.");
			System.exit(-1);
		}	
		return output;
	}
}
