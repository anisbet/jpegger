/**
 * 
 */
package nisbet.andrew.test;

import static org.junit.Assert.*;

import java.awt.Container;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import nisbet.andrew.DisplayBuffer;

import org.junit.Test;


/**
 * @author andrew
 *
 */
public class DisplayBufferTest extends JFrame 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6241012676143772765L;

	public DisplayBufferTest()
	{
		super("CMPUT 414 JPEG Encoding");
	}

	/**
	 * Test method for {@link nisbet.andrew.DisplayBuffer#setDisplayBuffer(nisbet.andrew.bmp.BMP)}.
	 */
	@Test
	public void testSetDisplayBufferBMPReader()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link nisbet.andrew.DisplayBuffer#setDisplayBuffer(java.io.File)}.
	 */
	@Test
	public void testSetDisplayBufferFile()
	{
		
		DisplayBuffer db = new DisplayBuffer();
		db.setDisplayBuffer(
				new File("/home/andrew/workspace/CMPUT 414 Assignment 2/bin/vincent-van-gogh-starry-night.bmp"));
		
		setBounds(100, 100, 600, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = this.getContentPane();
		JPanel pane = new JPanel();
		container.add(pane);
		container.add(db);
		setVisible(true);
		
	}

}
