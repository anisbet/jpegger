package nisbet.andrew.bmp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;



/**
 * This class represents the raw bmp image data. If you give it a BMP file it will read the RGB data from it
 * however it relies on the open {@link DataInputStream} from {@link BMP} class to accomplish this. The BMP
 * file should have finished reading the header so the file pointer is at the data to read. 
 * Alternatively you can set the bitmap data with the {@link #setRGB(int, byte, byte, byte)} method. 
 * @author andrew
 *
 */
public class BGRBitMap implements BitMap
{
	private byte[] channelA;
	private byte[] channelB;
	private byte[] channelC;
	private int size;
	private Header header;
	
	/**
	 * Constructor from a BMP file.
	 * @param bmp
	 */
	public BGRBitMap(Header header)
	{
		this.header = header;
	}
	
	/**
	 * Outputs all rgb data in the form BRG Pad format of a windows. 
	 * @param output
	 */
	@Override
	public boolean writeBGRP(DataOutputStream output)
	{
		if (header == null)
		{
			System.err.println("Can't write the bitmap because the header is null.");
			return false;
		}
		byte[] triple = new byte[3];
		try
		{
    		for (int i = 0; i < size; i++)
    		{
    			triple[0] = channelC[i];
    			triple[1] = channelB[i];
    			triple[2] = channelA[i];
    			
				output.write(triple);
    		}
    	}
		catch (IOException e)
		{
			System.err.println("Error writing RGB Pad data.");
			e.printStackTrace();
		}
		return true;
	}
	
	@Override
	public void writeRGB(DataOutputStream output)
	{
		byte[] quad = new byte[3];
		try
		{
    		for (int i = 0; i < size; i++)
    		{
    			quad[0] = channelC[i];
    			quad[1] = channelB[i];
    			quad[2] = channelA[i];
    			
				output.write(quad);
    		}
    	}
		catch (IOException e)
		{
			System.err.println("Error writing RGB data.");
			e.printStackTrace();
		}
	}
	
	/**
	 * For experimenting with sending raw RGB data to DisplayBuffer objects.
	 * @param size
	 */
	public BGRBitMap(int size)
	{
		this.size 	= size;
		channelC  	= new byte[this.size];
		channelB 	= new byte[this.size];
		channelA   	= new byte[this.size];
	}
	
	/**
	 * @param offset
	 * @param r
	 * @param g
	 * @param b
	 * @return true if the value was set false if offset exceeds the size of the colour array.
	 */
	@Override
	public boolean setRGB(int offset, byte r, byte g, byte b)
	{
		if (offset >= size) return false;
		channelA[offset]   = r;
		channelB[offset] = g;
		channelC[offset]  = b;
		return true;
	}


	
	@Override
	public byte[] getBlue()
	{
		return channelC;
	}

	@Override
	public byte[] getV()
	{
		return channelC;
	}
	
	@Override
	public byte[] getGreen()
	{
		return channelB;
	}

	@Override
	public byte[] getU()
	{
		return channelB;
	}

	
	@Override
	public byte[] getRed()
	{
		return channelA;
	}
	
	@Override
	public byte[] getY()
	{
		return channelA;
	}

	
	/**
	 * @return the channelA
	 */
	@Override
	public byte[] getChannelA()
	{
		return channelA;
	}

	
	/**
	 * @return the channelB
	 */
	@Override
	public byte[] getChannelB()
	{
		return channelB;
	}

	
	/**
	 * @return the channelC
	 */
	@Override
	public byte[] getChannelC()
	{
		return channelC;
	}

	
	
	/* (non-Javadoc)
	 * returns the size of the BMP in pixels.
	 * @see nisbet.andrew.bmp.BitMap#getSize()
	 */
	@Override
	public int getSize()
	{
		return size;
	}

	@Override
	public boolean readBitMap(DataInputStream in)
	{
		if (header == null) 
		{
			System.err.println("You are trying to read the bitmap but I need a non-null header to do that.");
			return false;
		}
		size = header.getColourArraySize();
		System.out.println("size == " + size);
		channelC  = new byte[size];
		channelB = new byte[size];
		channelA   = new byte[size];
		
		DataInputStream inFile = in;
		// bmp stores the RGB values in Blue Green Red Pad order.
		try
		{
			for (int i = 0; i < size; i++)
			{
			
				channelC[i]  = inFile.readByte();
				channelB[i] = inFile.readByte();
				channelA[i]   = inFile.readByte();
			
			}
		}
		catch (IOException e)
		{
			System.err.println("Error while reading the BMP RGB data.");
			e.printStackTrace();
		}
		System.out.println("finished reading the Bitmap data.");
		
		return true;
	}
}
