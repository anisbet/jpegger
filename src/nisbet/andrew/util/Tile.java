package nisbet.andrew.util;

import java.util.Vector;


/**
 * @author andrew
 *
 */
public class Tile implements Comparable<Object>
{
	public enum DownSampleMethod { AVERAGE };
	
	private Byte[][] tile	= null;
	private int tileSize	= 0;
	
	public Tile(int tileSize)
	{
		this.tileSize = tileSize;
		tile = new Byte[this.tileSize][this.tileSize];
	}
	/**
	 * @return the tile's Byte value.
	 */
	public Byte getValueAt(int x, int y)
	{
		if (isValid(x, y) )
		{
			return tile[x][y];
		}
		return new Byte(0);
	}

	/**
	 * @param tile the tile to set
	 */
	public void setTile(int x, int y, Byte value)
	{
		if (isValid(x, y) == false)
		{
			throw new ArrayIndexOutOfBoundsException("Tile is " + tileSize + " in size." +
					" There is no coord: x=" + x + " y=" + y + ".");
		}
		this.tile[x][y] = value;
	}
	
	/**
	 * Sets all the values in the tile to the argument byte.
	 * @param value
	 */
	public void setTile(Byte value)
	{
		for (int row = 0; row < tileSize; row++)
		{
			for (int col = 0; col < tileSize; col++)
			{
				this.tile[row][col] = value;
			}
		}
	}
	
	/**
	 * @param x
	 * @param y
	 * @return True if the x value and y value are in range of tile size and false otherwise.
	 */
	private boolean isValid(int x, int y)
	{
		if (x >= tileSize || x < 0)	return false;
		if (y >= tileSize || y < 0)	return false;
		return true;
	}
	
	
	/**
	 * @return the tileSize
	 */
	public int getTileSize()
	{
		return tileSize;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuffer out = new StringBuffer();
		
		for (int row = 0; row < tileSize; row++)
		{
			for (int col = 0; col < tileSize; col++)
			{
				out.append("[" + row + "," + col + "]=" + this.tile[row][col].toString() + " ");
			}
			out.append("\n");
		}
		return out.toString();
	}
	
	
	/**
	 * Returns the byte values in the row of this tile.
	 * @param row number
	 * @return Vector of Byte objects or an empty vector if the row was out of range.
	 */
	public Vector<Byte> getRow(int r)
	{
		Vector<Byte> array = new Vector<Byte>(); // a row sized array of bytes.
		for (int row = 0; row < tileSize; row++)
		{
			if (row == r)
			{
    			for (int col = 0; col < tileSize; col++)
    			{
    				array.add(this.tile[row][col]);
    			}
			}
		}
		return array;
	}
	
	/**
	 * Takes an average of all the bytes in the tile and returns a tile of size 1.
	 * @return new Tile of size of 1 instead of the this tile size or null if it could
	 * not be down sampled.
	 */
	public Tile downSample(DownSampleMethod method)
	{
		// ========= move to DownSampler411 ===========
		Tile tile = null;
		
		if (method == DownSampleMethod.AVERAGE)
		{
			float total = 0.0f;
			for (int row = 0; row < tileSize; row++)
			{
				for (int col = 0; col < tileSize; col++)
				{
					total += this.tile[row][col].asFloat();
				}
			}
			// take average
			total = total / (tileSize * tileSize);
			tile  = new Tile(1);
			tile.setTile(new Byte(total));
			
		}
		return tile;
	}

	@Override
	public int compareTo(Object o)
	{
		int result = 0;
		for (int i = 0; i < tileSize; i++)
		{
			for (int j = 0; j < tileSize; j++)
			{
				if ((result = tile[j][i].compareTo(((Tile)o).tile[j][i])) != 0)
				{
					return result;
				}
			}
		}
		return 0;
	}
}