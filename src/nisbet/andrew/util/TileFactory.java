/**
 * 
 */
package nisbet.andrew.util;


/**
 * A tile is an nxnx1 matrix of bitmap values.
 * @author andrew
 *
 */
public class TileFactory
{

	private int stripWidth 		= 0;  // Width of the image, same for all tiles.
	private int numberRows 		= 0;
	private int tileSize   		= 0;
	private int numTilesWide	= 0;
	private int numTilesHigh	= 0;

	/**
	 * Creates tiles of a certain dimension and then populates them with 
	 * the information from a one dimensional array.
	 * @param size
	 * @param stripWidth
	 * @param numberRows
	 */
	public TileFactory(int size, int stripWidth, int numberRows)
	{
		this.tileSize = size;
		this.stripWidth = stripWidth;
		this.numberRows = numberRows;
		this.numTilesWide = this.stripWidth/this.tileSize;
		this.numTilesHigh = this.numberRows/this.tileSize;
	}
	

	/**
	 * Use this method to get tiles. You don't have to get them in order, tile 
	 * factory will create any of them.
	 * @param tileX tile position x.
	 * @param tileY tile position y.
	 * @param data one dimensional array to get data from.
	 * @return
	 */
	public Tile getTile(int tileX, int tileY, byte[] data)
	{
		Tile tile = getNewTile(this.tileSize);
		int rowNumber = stripWidth * (tileY * tileSize);
		int columnNumber = tileX * tileSize;
		int dataIndex = 0;
		for (int row = 0; row < tileSize; row++)
		{
			for (int col = 0; col < tileSize; col++)
			{
				dataIndex = rowNumber + col + columnNumber + row * stripWidth;
				if (dataIndex < stripWidth * numberRows)
				{
					tile.setTile(row, col, new Byte(data[dataIndex]));
				}
				else // the image wasn't an even number of tiles wide.
				{
					tile.setTile(row, col, new Byte(0));
				}
			}
		}
		return tile;
	}
	
	/**
	 * @param size the size (square) of the required tile.
	 * @return new Tile object of the specified size.
	 * @throws IllegalArgumentException if the specified size is not 1, 2 or 8 (so far).
	 */
	public static Tile getNewTile(int size)
	{
		switch (size)
		{
		case 8: return new EightByEightTile();
		case 2: return new TwoByTwoTile();
		case 1: return new OneByOneTile();
		default: throw new IllegalArgumentException("Unknown TileType " + size);
		}
	}


	/**
	 * @param data
	 * @return a 2 d array of tiles made from the supplied data.
	 */
	public Tile[][] tilize(byte[] data)
	{
		Tile[][] tiles = new Tile[getTilesHigh()][getTilesWide()];
		
		for (int row = 0; row < getTilesHigh(); row++)
		{
			for (int col = 0; col < getTilesWide(); col++)
			{
				tiles[row][col] = this.getTile(col, row, data);
			}
		}
		return tiles;
	}
	
	/**
	 * @param data array of bytes.
	 * @return a TileSet of n x n tiles.
	 */
	public TileSet getTileSet(byte[] data)
	{
		Tile[][] tiles = this.tilize(data);
		return new TileSet(tiles, getTilesWide(), getTilesHigh());
	}
	
	/**
	 * @return the tileSize
	 */
	public int getTileSize()
	{
		return tileSize;
	}


	public int getTilesHigh()
	{
		return this.numTilesHigh;
	}


	public int getTilesWide()
	{
		return this.numTilesWide;
	}

	
}
