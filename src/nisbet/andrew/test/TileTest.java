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
public class TileTest
{
	
	@Test
	public void testGetRow()
	{
		TileFactory tileFactory = new TileFactory(8, 24, 16);
		
		byte[] bitMap = new byte[24*16];
		byte count = 1;
		for (int i = 0; i < 24*16; i++)
		{
			if (count % 97 == 0) // to compensate for max value of a signed byte.
				count = 1;
			bitMap[i] = (byte) (count++);
		}
		
		Tile[] tile = new Tile[6];
		tile[0] = tileFactory.getTile(0, 0, bitMap);
		tile[1] = tileFactory.getTile(1,0,bitMap);
		tile[2] = tileFactory.getTile(2,0,bitMap);
		tile[3] = tileFactory.getTile(0,1,bitMap);
		tile[4] = tileFactory.getTile(1,1,bitMap);
		tile[5] = tileFactory.getTile(2,1,bitMap);
		assertNotNull(tile);
		System.out.println("tile[0].row(3)::" + tile[0].getRow(3) + "\ntile[3].row(3)::" + tile[3].getRow(3));
		System.out.println("tile[0].row(4)::" + tile[0].getRow(4) + "\ntile[3].row(4)::" + tile[3].getRow(4));
		System.out.println("tile[0].row(5)::" + tile[0].getRow(5) + "\ntile[3].row(5)::" + tile[3].getRow(5));
	}

	/**
	 * Test method for {@link nisbet.andrew.util.TileEight#Tile(int, int, byte[])}.
	 */
	@Test
	public void testTile()
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
		
		Tile[] tile = new Tile[6];
		tile[0] = tileFactory.getTile(0, 0, bitMap);
		
		tile[1] = tileFactory.getTile(1,0,bitMap);
		tile[2] = tileFactory.getTile(2,0,bitMap);
		tile[3] = tileFactory.getTile(0,1,bitMap);
		tile[4] = tileFactory.getTile(1,1,bitMap);
		tile[5] = tileFactory.getTile(2,1,bitMap);
		
		assertNotNull(tile);
		assertTrue(tile[0].toString().compareTo(tile[3].toString()) == 0);
		assertTrue(tile[1].toString().compareTo(tile[4].toString()) == 0);
		assertTrue(tile[2].toString().compareTo(tile[5].toString()) == 0);
//		System.out.println(tile[0].toString() + "\n\n" + tile[3].toString());
		
		
		tileFactory = new TileFactory(8, 24, 15);
		
		bitMap = new byte[24*15];
		count = 1;
		for (int i = 0; i < 24*15; i++)
		{
			if (count % 97 == 0) // to compensate for max value of a signed byte.
				count = 1;
			bitMap[i] = (byte) (count++);
		}
		
		tile[0] = tileFactory.getTile(0,0,bitMap);
		tile[1] = tileFactory.getTile(1,0,bitMap);
		tile[2] = tileFactory.getTile(2,0,bitMap);
		tile[3] = tileFactory.getTile(0,1,bitMap);
		tile[4] = tileFactory.getTile(1,1,bitMap);
		tile[5] = tileFactory.getTile(2,1,bitMap);
		
		assertNotNull(tile);
//		System.out.println(tile[0].toString() + "\n\n" + tile[3].toString());
		
		assertNotNull(tile);
		// These should all have a 0 as the last value in the row because the matrix is short.
		assertTrue(tile[0].toString().compareTo(tile[3].toString()) == 7);
		assertTrue(tile[1].toString().compareTo(tile[4].toString()) == 8);
		assertTrue(tile[2].toString().compareTo(tile[5].toString()) == 8);
		System.out.println("tile[0] and tile[3]");
		System.out.println(tile[0].toString() + "\n\n" + tile[3].toString());
		System.out.println("tile[1] and tile[4]");
		System.out.println(tile[1].toString() + "\n\n" + tile[4].toString());
		System.out.println("tile[2] and tile[5]");
		System.out.println(tile[2].toString() + "\n\n" + tile[5].toString());
		
		// set the image dimensions
		tileFactory = new TileFactory(8, 23, 16);
		
		bitMap = new byte[23*16];
		count = 1;
		for (int i = 0; i < 23*16; i++)
		{
			if (count % 97 == 0) // to compensate for max value of a signed byte.
				count = 1;
			bitMap[i] = (byte) (count++);
		}
		
		tile = new Tile[6];
		tile[0] = tileFactory.getTile(0, 0, bitMap);
		
		tile[1] = tileFactory.getTile(1,0,bitMap);
		tile[2] = tileFactory.getTile(2,0,bitMap);
		tile[3] = tileFactory.getTile(0,1,bitMap);
		tile[4] = tileFactory.getTile(1,1,bitMap);
		tile[5] = tileFactory.getTile(2,1,bitMap);
		
		assertNotNull(tile);
//		System.out.println(tile[0].toString() + "\n\n" + tile[3].toString());
		
		assertNotNull(tile);
		// These should all have a 0 as the last value in the row because the matrix is short.
		System.out.println("tile[0] and tile[3]");
		System.out.println(tile[0].toString() + "\n\n" + tile[3].toString());
		System.out.println("tile[1] and tile[4]");
		System.out.println(tile[1].toString() + "\n\n" + tile[4].toString());
		System.out.println("tile[2] and tile[5]");
		System.out.println(tile[2].toString() + "\n\n" + tile[5].toString());

		tileFactory = new TileFactory(1, 23, 16);
		Tile[][] myTiles = tileFactory.tilize(bitMap);
		assertNotNull(myTiles);
		

	}

}
