/**
 * 
 */
package nisbet.andrew.test;

import static org.junit.Assert.*;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;

import nisbet.andrew.bmp.BMP;
import nisbet.andrew.bmp.Image;

import org.junit.Test;


/**
 * @author andrew
 *
 */
public class BMPTest
{

	/**
	 * Test method for {@link nisbet.andrew.bmp.BMP#BMP(String)}.
	 */
	@Test
	public void testBMP()
	{
		Image bmp = null;
		String outFileName = "/home/andrew/414/assign2/vincent-van-gogh-starry-night.bmp";
		try
		{
			bmp = new BMP(outFileName);
		}
		catch (EOFException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(bmp);
		bmp.write();
		File fBmp = new File(outFileName);
		assertNotNull(fBmp);
		
	}

}
