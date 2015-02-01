package nisbet.andrew.test;

import nisbet.andrew.util.Byte;

import org.junit.Test;

import junit.framework.TestCase;


public class ByteTest extends TestCase
{
	@Test
	public void testAsByte()
	{
		Byte f = null;
		int j = 0;
		for (byte i = 0; i < 255; i++, j++)
		{
			f = new Byte(i);
			//System.out.println("i = " + i + " f.asByte()=" + f.asByte());
			assertTrue(f.asByte() == i);
			if (j == 255) break; // this stops the loop because byte wraps to negative value and is endless
		}
	}

	@Test
	public void testFlyteByte()
	{
		Byte f = null;
		for (int i = 0; i < 257; i++)
		{
			//System.out.print("i =" + i + " (byte)i " + (byte)i);
			f = new Byte((byte)i);
			//System.out.println(" stored value =" + f.asByte());
			if (i < 256) // because 256 == 0 in unsigned byte
			{
				assertTrue(f.asShortByte() == (short)i);
			}
		}
	}

	@Test
	public void testFlyteFloat()
	{
		
		Byte f = null;
		for (float i = 0f; i < 255.0; i++)
		{
			f = new Byte(i);
			assertTrue(f.asShortByte() == (short)i);
		}
	}

	@Test
	public void testGetFloat()
	{
		Byte f = null;
		for (float i = 0f; i < 255.0; i++)
		{
			f = new Byte(i);
			assertTrue(f.asFloat() == i);
		}
	}


	@Test
	public void testNormalize()
	{
		Byte f = new Byte(0);
		f.normalize();
		assertTrue(f.asShortByte() == -128);
		assertTrue(f.asFloat() == -128f);
		
		f = new Byte(-257.232f);
		assertTrue(f.asShortByte() == 0);
		assertTrue(f.asFloat() == 0);
		f.normalize();
		assertTrue(f.asShortByte() == -128);
		assertTrue(f.asFloat() == -128f);
		
		f = new Byte(128f);
		assertTrue(f.asShortByte() == 128);
		assertTrue(f.asFloat() == 128f);
		f.normalize();
		assertTrue(f.asShortByte() == 0);
		assertTrue(f.asFloat() == 0f);
		
		f = new Byte(0f);
		assertTrue(f.asShortByte() == 0);
		assertTrue(f.asFloat() == 0f);
		f.normalize();
		assertTrue(f.asShortByte() == -128);
		assertTrue(f.asFloat() == -128f);
	}
}
