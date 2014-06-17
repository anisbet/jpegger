/**
 * 
 */
package nisbet.andrew.util;

import java.util.Vector;
import nisbet.andrew.util.Tile;


/**
 * @author andrew
 *
 */
public class TileSet implements Comparable<Object>
{
	private int width = 0;
	private int height= 0;
	private Vector<Tile> tileSet = null;
	private int tileSize = 0;
	
	public TileSet(final Tile[][] tiles, final int width, final int height)
	{
		if (tiles == null || tiles[0][0] == null)
		{
			throw new IllegalArgumentException("TileSet: The array of tiles is null or empty.");
		}
		this.width 		= width;
		this.height 	= height;
		this.tileSet	= new Vector<Tile>();
		this.tileSize	= tiles[0][0].getTileSize();
		for (int row = 0; row < this.height; row++)
		{
			for (int col = 0; col < this.width; col++)
			{
				this.tileSet.add(tiles[row][col]);
			}
		}
	}

	/**
	 * @return the width in pixels.
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * @return the height in pixels
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * @return size of tile set in pixels.
	 */
	public int getSize()
	{
		return (width * tileSize) * (height * tileSize);
	}

	/**
	 * @return the tileSet
	 */
	public Tile[][] getTiles()
	{
		Tile[][] tiles = new Tile[height][width];
		int i = 0;
		for (int row = 0; row < this.height; row++)
		{
			for (int col = 0; col < this.width; col++, i++)
			{
				tiles[row][col] = this.tileSet.get(i);
			}
		}
		return tiles;
	}
	
	
	/**
	 * Reconstitutes the tiles back into an 1D array representation of the bytes it 
	 * originally received when it was first instantiated.
	 * @return Array of bytes of the bit plane suitable for writing to file. The size of 
	 * the return array need not be known at run-time Java will size it appropriately.
	 */
	public byte[] reconstitute()
	{
		// I am worried that we double spatially double the size of any plane.
		Vector<Byte> array = new Vector<Byte>();
		for (int rowInBitMap = 0; rowInBitMap < this.height; rowInBitMap++)
		{
    		for (int rowWithinTile = 0; rowWithinTile < tileSize; rowWithinTile++)
    		{
    			for (int colOfTiles = 0; colOfTiles < this.width; colOfTiles++)
    			{
    				array.addAll(tileSet.get(colOfTiles).getRow(rowWithinTile));
    			}
    		}
		}
		
		// now convert the rows back into bytes.
		byte[] bitmapPlane = new byte[array.size()];
		for (int j = 0; j < array.size(); j++)
		{
			bitmapPlane[j] = (array.get(j)).asByte();
		}
	
		return bitmapPlane;
	}


	@Override
	public int compareTo(Object o)
	{
		int result = 0;
		for (int i = 0; i < tileSet.size(); i++)
		{

			if((result = tileSet.get(i).compareTo(((TileSet)o).tileSet.get(i))) != 0)
			{
				return result;
			}
		}
		
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuffer out = new StringBuffer();
		
		for (int i = 0; i < this.tileSet.size(); i++)
		{
			out.append(this.tileSet.get(i).toString());
		}
		return out.toString();
	}
}
