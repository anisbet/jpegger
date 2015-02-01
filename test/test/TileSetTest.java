/**
 * 
 */
package nisbet.andrew.test;

import static org.junit.Assert.*;

import nisbet.andrew.util.TileFactory;
import nisbet.andrew.util.Tile;
import nisbet.andrew.util.TileSet;

import org.junit.Test;


/**
 * @author andrew
 *
 */
public class TileSetTest
{
	/**
	 * Test method for {@link nisbet.andrew.util.TileSet#compareTo()}.
	 */
	@Test
	public void testCompareTo()
	{
		// set the image dimensions
		TileFactory tileFactory = new TileFactory(8, 24, 16);
		
		byte[] bitMap = new byte[24*16];
		byte count = 1;
		for (int i = 0; i < 24*16; i++)
		{
			if (count % 97 == 0) // to compensate for max value of a signed byte.
				count = 1;
			bitMap[i] = (byte) (count++);
		}
		
		Tile[][] tile = new Tile[2][3];
		tile[0][0] = tileFactory.getTile(0, 0, bitMap);
		tile[0][1] = tileFactory.getTile(1, 0, bitMap);
		tile[0][2] = tileFactory.getTile(2, 0, bitMap);
		tile[1][0] = tileFactory.getTile(0, 1, bitMap);
		tile[1][1] = tileFactory.getTile(1, 1, bitMap);
		tile[1][2] = tileFactory.getTile(2, 1, bitMap);
		
		TileSet tileSet = new TileSet(tile,3,2);
		
		Tile[][] tile1 = new Tile[2][3];
		tile1[0][0] = tileFactory.getTile(0, 0, bitMap);
		tile1[0][1] = tileFactory.getTile(1, 0, bitMap);
		tile1[0][2] = tileFactory.getTile(2, 0, bitMap);
		tile1[1][0] = tileFactory.getTile(0, 1, bitMap);
		tile1[1][1] = tileFactory.getTile(1, 1, bitMap);
		tile1[1][2] = tileFactory.getTile(2, 1, bitMap);
		
		TileSet tileSet1 = new TileSet(tile1,3,2);
		assertNotNull(tileSet1);
		
		assertTrue(tileSet.compareTo(tileSet1) == 0);
	}

	/**
	 * Test method for {@link nisbet.andrew.util.TileSet#hasNext()}.
	 */
	@Test
	public void testHasNext()
	{
		// set the image dimensions
		TileFactory tileFactory = new TileFactory(8, 24, 16);
		
		byte[] bitMap = new byte[24*16];
		byte count = 1;
		for (int i = 0; i < 24*16; i++)
		{
			if (count % 97 == 0) // to compensate for max value of a signed byte.
				count = 1;
			bitMap[i] = (byte) (count++);
		}
		
		Tile[][] t = new Tile[1][1];
		TileSet ts = null;
		try
		{
			ts = new TileSet(t,0,0);
		}
		catch (Exception e)
		{
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		Tile[][] tile = new Tile[2][3];
		tile[0][0] = tileFactory.getTile(0, 0, bitMap);
		tile[0][1] = tileFactory.getTile(1, 0, bitMap);
		tile[0][2] = tileFactory.getTile(2, 0, bitMap);
		tile[1][0] = tileFactory.getTile(0, 1, bitMap);
		tile[1][1] = tileFactory.getTile(1, 1, bitMap);
		tile[1][2] = tileFactory.getTile(2, 1, bitMap);
		
		TileSet tileSet = new TileSet(tile,3,2);
		assertNotNull(tileSet);
		
	}
	
	@Test
	public void testGetTileSet()
	{
		TileFactory tileFactory = new TileFactory(1, 24, 16);
		
		byte[] bitMap = new byte[24*16];
		byte count = 1;
		for (int i = 0; i < 24*16; i++)
		{
			if (count % 97 == 0) // to compensate for max value of a signed byte.
				count = 1;
			bitMap[i] = (byte) (count++);
		}

		Tile[][] myTiles = tileFactory.tilize(bitMap);
		assertNotNull(myTiles);
		
		
		TileSet tileSet = tileFactory.getTileSet(bitMap);
		
		//System.out.println("Tile set contains: \n" + tileSet);
		
		TileSet tileSet2 = new TileSet(myTiles,24,16);
		

		assertTrue(tileSet.compareTo(tileSet2)==0);
		
	}
	
	@Test
	public void testReconstitute()
	{
		// set the image dimensions
		TileFactory tileFactory = new TileFactory(8, 24, 16);
		
		byte[] bitMap = new byte[24*16];
		byte count = 1;
		for (int i = 0; i < 24*16; i++)
		{
			if (count % 97 == 0) // to compensate for max value of a signed byte.
				count = 1;
			bitMap[i] = (byte) (count++);
		}
		
		TileSet tileSet = tileFactory.getTileSet(bitMap);
		byte[] bitMap2 = new byte[tileSet.getSize()];
//		System.out.println(bitMap.length + "<-b1 and b2->" + bitMap2.length);
		assertTrue(bitMap.length == bitMap2.length);
		bitMap2 = tileSet.reconstitute();
		assertTrue(bitMap.length == bitMap2.length);
		for (int j = 0; j < tileSet.getSize(); j++)
		{
//			System.out.print(bitMap[j] + "<-b1 ");
//			System.out.println("and b2->" + bitMap2[j]);
			assertTrue(bitMap[j] == bitMap2[j]);
		}
	}

}
