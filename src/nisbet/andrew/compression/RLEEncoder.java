package nisbet.andrew.compression;

import java.util.BitSet;

import nisbet.andrew.util.Tile;



/**
 * This class compresses a tile of bitmap byte values using RLE.
 * @author andrew
 *
 */
public class RLEEncoder implements Compressor
{
	private BitSet bits = null;
	private Tile   tile = null;
	
	// The order of encoding pixels (for the i-th block) is
	// Bi(0,0), Bi(0,1), Bi(1,0), Bi(2,0), Bi(1,1), Bi(0,2), Bi(0,3), Bi(1,2) and so on.
    public RLEEncoder(Tile imageTile)
    {
    	this.tile = imageTile;
    	System.out.println("" + bits + tile.toString());
    }
    
    public String getCompressionRatio()
    {
    	return "<N/A>";
    }

    @Override
    public boolean compress()
    {
    	// TODO Go through the tile in the pattern described and order the bits or
    	return false;
    }

}
