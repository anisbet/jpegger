/**
 * 
 */
package nisbet.andrew.bmp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;


/**
 * @author andrew
 *
 */
public interface Header
{

	/** 
	 * Reads the BMP's header.
	 * @param in
	 * @throws EOFException
	 * @throws IOException
	 */
	public abstract boolean readerHeader(DataInputStream fileIn) throws EOFException, IOException;

	public abstract boolean writeHeader(DataOutputStream fileOut) throws IOException;

	public abstract int getColourArraySize();

	public abstract int getImageHeight();

	/**
	 * @return the size of the image in bytes.
	 */
	public abstract int getSize();

	public abstract int getWidth();

}
