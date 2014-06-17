package nisbet.andrew.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class LineReader
{
	private BufferedReader line = new BufferedReader(new InputStreamReader(System.in));
	private String answer;
	
	public LineReader()
	{
		try
		{
			answer = line.readLine();
		}
		catch (IOException e)
		{
			answer = "";
			System.err.println("Sorry, error while reading from keyboard.");
		}
	}
	
	/**
	 * @return answer -- whatever the user entered on the keyboard.
	 */
	public String getAnswer()
	{
		return answer;
	}
	
}
