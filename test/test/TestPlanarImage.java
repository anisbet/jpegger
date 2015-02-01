/**
 * 
 */
package nisbet.andrew.test;

import static org.junit.Assert.*;

import nisbet.andrew.bmp.BMP;
import nisbet.andrew.bmp.Image;

import org.junit.Before;
import org.junit.Test;


/**
 * @author andrew
 *
 */
public class TestPlanarImage
{
	
	private Image image = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		image = new BMP("/home/andrew/414/assign2/vincent-van-gogh-starry-night.bmp");
	}

	/**
	 * Test method for {@link nisbet.andrew.util.PlanarImage#PlanarImage(nisbet.andrew.bmp.Image)}.
	 */
	@Test
	public void testPlanarImage()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link nisbet.andrew.util.PlanarImage#writeImage(nisbet.andrew.bmp.ImageType, java.lang.String)}.
	 */
	@Test
	public void testWriteImage()
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link nisbet.andrew.util.PlanarImage#getTileSet(java.lang.String)}.
	 */
	@Test
	public void testGetTileSet()
	{
		fail("Not yet implemented"); // TODO
	}

}
