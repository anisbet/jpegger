/**
 * 
 */
package nisbet.andrew.test;

import static org.junit.Assert.*;

import javax.swing.text.html.HTMLDocument.Iterator;

import nisbet.andrew.util.Tile;
import nisbet.andrew.util.TileFactory;
import nisbet.andrew.util.TileSet;

import org.junit.Before;
import org.junit.Test;


/**
 * @author andrew
 *
 */
public class TileFactoryTest
{
	private byte[] bitMap;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{

		bitMap = new byte[24*16];
		byte count = 1;
		for (int i = 0; i < 24*16; i++)
		{
			if (count % 97 == 0) // to compensate for max value of a signed byte.
				count = 1;
			bitMap[i] = (byte) (count++);
		}
	}

	/**
	 * Test method for {@link nisbet.andrew.util.TileFactory#getTile(int, int, byte[])}.
	 */
	@Test
	public void testGetTile()
	{
		TileFactory tileFactory = new TileFactory(1, 24, 16);
		assertTrue(tileFactory.getTileSize() == 1);
		assertTrue(tileFactory.getTilesHigh() == 16);
		assertTrue(tileFactory.getTilesWide() == 24);
		Tile[][] myTiles = tileFactory.tilize(bitMap);
		assertNotNull(myTiles);

		//System.out.print(myTiles[row][col].toString() + " :: ");
		//System.out.println(tileFactory.getTile(col, row, bitMap).toString());
		assertTrue(myTiles[0][0].compareTo(tileFactory.getTile(0, 0, bitMap)) == 0);
		assertTrue(myTiles[1][0].compareTo(tileFactory.getTile(0, 1, bitMap)) == 0);
		assertTrue(myTiles[0][1].compareTo(tileFactory.getTile(1, 0, bitMap)) == 0);
		assertTrue(myTiles[1][1].compareTo(tileFactory.getTile(1, 1, bitMap)) == 0);

//		
//		
//		TileSet tileSet = tileFactory.getTileSet(bitMap);
//		
//		System.out.println("Tile set contains: \n" + tileSet);
//		
//		TileSet tileSet2 = new TileSet(myTiles,24,16);
//		
//
//		assertTrue(tileSet.compareTo(tileSet2)==0);
	}

	/**
	 * Test method for {@link nisbet.andrew.util.TileFactory#tilize(byte[])}.
	 */
	@Test
	public void testTilize()
	{
		TileFactory tileFactory = new TileFactory(8, 24, 16);
		assertTrue(tileFactory.getTileSize() == 8);
		assertTrue(tileFactory.getTilesHigh() == 2);
		assertTrue(tileFactory.getTilesWide() == 3);
		Tile[][] myTiles = tileFactory.tilize(bitMap);
		assertNotNull(myTiles);
		
		for (int row = 0; row < tileFactory.getTilesHigh(); row++)
		{
			for (int col = 0; col < tileFactory.getTilesWide(); col++)
			{
				//System.out.print(myTiles[row][col].toString() + " :: ");
				//System.out.println(tileFactory.getTile(col, row, bitMap).toString());
				assertTrue(myTiles[row][col].compareTo(tileFactory.getTile(col, row, bitMap)) == 0);
			}
		}
	}

	/**
	 * Test method for {@link nisbet.andrew.util.TileFactory#getTileSet(byte[])}.
	 */
	@Test
	public void testGetTileSet()
	{
		TileFactory tileFactory = new TileFactory(8, 24, 16);
		assertTrue(tileFactory.getTileSize() == 8);
		assertTrue(tileFactory.getTilesHigh() == 2);
		assertTrue(tileFactory.getTilesWide() == 3);
		TileSet myTiles = tileFactory.getTileSet(bitMap);
		assertNotNull(myTiles);
		Tile[][] tiles = myTiles.getTiles();
		
		for (int row = 0; row < tileFactory.getTilesHigh(); row++)
		{
			for (int col = 0; col < tileFactory.getTilesWide(); col++)
			{
				Tile t0 = tileFactory.getTile(row, col, bitMap);
				System.out.print("t0 == " + t0.toString() + "\nt1 == " + tiles[row][col].toString() + "\n\n");
				assertTrue(tiles[row][col].compareTo(tileFactory.getTile(col, row, bitMap)) == 0);
			}
		}
	}

}
