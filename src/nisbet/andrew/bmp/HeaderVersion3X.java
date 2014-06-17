/**
 * 
 */
package nisbet.andrew.bmp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

import nisbet.andrew.util.WindowsDataTypeReader;


/**
 * @author andrew
 *
 */
public class HeaderVersion3X implements Header
{
	public final static int BITMAP_HEADER_SIZE = 14;
    public final static int BITMAP_INFO_SIZE = 40;
    public enum Compression { NONE, RLE8, RLE4 };

    /* Bitmap file header fields */
    private short type = 0;
    private int size = 0;
    private short reserved1 = 0; // reserved one and two always 0 so read it as one int value. */
    private short reserved2 = 0;
    private int offBits = BITMAP_HEADER_SIZE + BITMAP_INFO_SIZE; // starting position of image data in bytes.
    
    /* fields in BMP 3.x type. */
    private int headerSize = 0;
    private int imageWidth = 0;
    private int imageHeight = 0;
    private short planes = 1;
    private short bitsPerPixel = 24;   // should be 24 for this version of BMP but could be different so throw e.
    private int compression = 0;
    private int bitMapSize = 0; // in bytes.
    private int horizontalResolution = 0;
    private int verticalResolution = 0;
    private int colourUsed = 0;
    private int importantColours = 0;
    
    private WindowsDataTypeReader wdtReader = null;
    
    public HeaderVersion3X()
    {
    	wdtReader = new WindowsDataTypeReader();
    }
    
    @Override
	public boolean readerHeader(DataInputStream in) throws EOFException, IOException
    {
    	this.type = wdtReader.getShort(in);
		this.size = wdtReader.getInt(in);
	    // reserved one and two always 0 so read it as one int value.
		this.reserved1 = in.readShort();
	    // reserved one and two always 0 so read it as one int value.
		this.reserved2 = in.readShort();
	    // starting position of image data in bytes.
		this.offBits = wdtReader.getInt(in);
		if (offBits != BITMAP_HEADER_SIZE + BITMAP_INFO_SIZE) throw new InvalidBMPException("Wrong offset size.");
	    
	    /* fields in BMP 3.x type. */
		this.headerSize = wdtReader.getInt(in);
		this.imageWidth = wdtReader.getInt(in);
		this.imageHeight = wdtReader.getInt(in);
		System.out.println("image Width read: " + this.imageWidth + " image height: " + this.imageHeight);
		this.planes = wdtReader.getShort(in);
	    // should be 24 for this version of BMP but could be different so throw e.
		this.bitsPerPixel = wdtReader.getShort(in);
		this.compression = wdtReader.getInt(in);
		if (this.compression > Compression.NONE.ordinal()) throw new InvalidBMPException("This version can't decompress BMPs.");
		this.bitMapSize = wdtReader.getInt(in);
		this.horizontalResolution = wdtReader.getInt(in);
		this.verticalResolution = wdtReader.getInt(in);
		this.colourUsed = wdtReader.getInt(in);
		this.importantColours = wdtReader.getInt(in);
		return false;
    	
    }
    
    @Override
	public boolean writeHeader(DataOutputStream fileOut) throws IOException
    {
		
    	wdtReader.writeToWORD(this.type, fileOut);
    	wdtReader.writeToDWORD(this.size, fileOut);
    	wdtReader.writeToWORD(this.reserved1, fileOut);
    	wdtReader.writeToWORD(this.reserved2, fileOut);
    	wdtReader.writeToDWORD(this.offBits, fileOut);
	    // byte 14
	    /* fields in BMP 3.x type. */
    	wdtReader.writeToDWORD(this.headerSize, fileOut);
		// byte 18
    	wdtReader.writeToDWORD(this.imageWidth, fileOut);
		// byte 19-22
    	wdtReader.writeToDWORD(this.imageHeight, fileOut);
		// byte 26
    	wdtReader.writeToWORD(this.planes, fileOut);
    	wdtReader.writeToWORD(this.bitsPerPixel, fileOut);
		// byte 30
    	wdtReader.writeToDWORD(this.compression, fileOut);
    	wdtReader.writeToDWORD(this.bitMapSize, fileOut);
    	wdtReader.writeToDWORD(this.horizontalResolution, fileOut);
    	wdtReader.writeToDWORD(this.verticalResolution, fileOut);
    	wdtReader.writeToDWORD(this.colourUsed, fileOut);
    	wdtReader.writeToDWORD(this.importantColours, fileOut);
		
    	return true;
    }
    
	/**
	 * @return Size of the array you should use to store all red, green or blue values.
	 */
	@Override
	public int getColourArraySize()
	{
		return this.bitMapSize / (this.bitsPerPixel / 8); // 24 / 8 is 3  for the padding.
	}

	@Override
	public int getImageHeight()
	{
		return this.imageHeight;
	}

	@Override
	public int getSize()
	{
		return this.size;
	}

	@Override
	public int getWidth()
	{
		return this.imageWidth;
	}
}
