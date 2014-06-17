/**
 * 
 */
package nisbet.andrew.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


/**
 * @author andrew
 *
 */
public class WindowsDataTypeReader
{

	/** 
	 * Reads a Windows DWORD from file.
	 * @param in
	 * @return Java Int
	 * @throws IOException
	 */
	public int getInt(DataInputStream in) throws IOException
	{
		int retValue = (int) (in.readByte() & 0x000000ff);
		retValue = (int) (retValue | ((in.readByte() << 8)  & 0x0000ffff));
		retValue = (int) (retValue | ((in.readByte() << 16) & 0x00ffffff));
		retValue = (int) (retValue | ((in.readByte() << 24) & 0xffffffff));
		return retValue;
	}




	/**
	 * Reads a Windows WORD from the bmp.
	 * @param in
	 * @return Java short
	 * @throws IOException
	 */
	public short getShort(DataInputStream in) throws IOException
	{
		short retValue = (short) (in.readByte() & 0x00ff);
		retValue = (short) (retValue | ((in.readByte() & 0x00ff) << 8));
		return retValue;
	}




	/**
    *
    * intToWord converts an int to a windows word, where the return
    * value is stored in a 2-byte array.
	 * @throws IOException 
    *
    */
	public void writeToWORD(short value, DataOutputStream out) throws IOException 
	{

       byte outValue [] = new byte [2];
       outValue [0] = (byte) (value & 0x00FF);
       outValue [1] = (byte) ((value >> 8) & 0x00FF);
       out.write(outValue);
	}

   /**
    *
    * intToDWord converts an int to a windows double word, where the return
    * value is stored in a 4-byte array.
    * @throws IOException 
    *
    */
	public void writeToDWORD (int value, DataOutputStream out) throws IOException
	{
       byte outValue [] = new byte [4];
       outValue [0] = (byte) (value & 0x00FF);
       outValue [1] = (byte) ((value >> 8) & 0x000000FF);
       outValue [2] = (byte) ((value >> 16) & 0x000000FF);
       outValue [3] = (byte) ((value >> 24) & 0x000000FF);
       out.write(outValue);
	}
}
