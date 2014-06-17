package nisbet.andrew.bmp;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//import nisbet.andrew.util.LineReader;



/**
 * BMPReader reads bmps and presents the image data in usable format(s).
 * @author andrew
 *
 */
public class BMP implements Image
{
	/* The BMP header -- this one is a version 3x */
	private Header header = null;
	/* Raw bitmap from the BMP */
    private BitMap bitMap = null; // don't create this until you know what the header says.

    /* BMP file stream. */
    private DataInputStream fileIn;
    private String name;
    
	



	/**
	 * Takes the name of the file and tries to read it.
	 * @param bmpName TODO
	 * @throws IOException 
	 * @throws EOFException 
	 */
	public BMP(String bmpName) throws EOFException, IOException
	{
		this.name = bmpName;
		File fi = new File(bmpName);
		this.fileIn = new DataInputStream(new BufferedInputStream(new FileInputStream(fi)));
		this.header = new HeaderVersion3X();
		this.bitMap = new BGRBitMap(header);
		this.header.readerHeader(fileIn);
		this.bitMap.readBitMap(fileIn);
		fileIn.close();
	}
	
	@Override
	public void rename(String newName)
	{
		this.name = newName;
	}
	
	@Override
	public String getName()
	{
		return name;
	}
	
	/** 
	 * Writes the Bitmap data to Windows BMP file format.
	 * @return true if the file was written and false if the user refused to overwrite 
	 * an existing file.
	 */
	@Override
	public boolean write()
	{
		File f = new File(this.name);
		if (f.exists())
		{
//			System.err.println("The file already exists, do you want to replace it? [y/n]");
//			LineReader answer = new LineReader();
//			if (answer.getAnswer().startsWith("n", 0))
//			{
//				System.out.println("preserving original file. Pick another name.");
//				return false;
//			}
//			else
//			{
			f.delete();
//			}
		}
		
		DataOutputStream fileOut = null;
		try
		{
			fileOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
			header.writeHeader(fileOut);
			bitMap.writeBGRP(fileOut);
			fileOut.close();
			System.out.println("Finished writing file.");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			System.err.println("Error while writing BMP data to file.");
			e.printStackTrace();
		}
		return true;
	}


	/**
	 * Gets the size of the bmp in bytes.
	 * @param asString true or false returns a string. Done so method doesn't collide with
	 * {@link #getSize()}.
	 * @return
	 */
	@Override
	public String getSize(boolean asString)
	{
	    return String.valueOf(header.getSize());
	}
	
	/**
	 * @return Size of bmp data in bytes.
	 */
	@Override
	public int getSize()
	{
		return header.getSize();
	}


	/**
	 * @return height if bmp in pixels
	 */
	@Override
	public int getHeight()
	{
		return header.getImageHeight();
	}



	/**
	 * @return image type of the bmp data. Used for JPanel and IOImage.
	 */
	@Override
	public ImageType getImageType()
	{
		return ImageType.BMP;
	}



	/**
	 * @return width of the image in pixels.
	 */
	@Override
	public int getWidth()
	{
		return header.getWidth();
	}
	
	
	/**
	 * Use this method to interact with the bitmap data of the image. It is
	 * already split into blue array green and red array.
	 * @return BitMap.
	 */
	@Override
	public BitMap getBitMap()
	{
		return this.bitMap;
	}


	/**
	 * @return Number of pixels in an image.
	 */
	@Override
	public int getNumberPixels()
	{
		return header.getColourArraySize();
	}

}
