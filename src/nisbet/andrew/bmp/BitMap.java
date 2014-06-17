/**
 * 
 */
package nisbet.andrew.bmp;

import java.io.DataInputStream;
import java.io.DataOutputStream;


/**
 * @author andrew
 *
 */
public interface BitMap
{

	public abstract int getSize();

	public abstract byte[] getRed();

	public abstract byte[] getGreen();

	public abstract byte[] getBlue();

	public abstract boolean setRGB(int offset, byte r, byte g, byte b);
	
	public abstract boolean readBitMap(DataInputStream in);

	public abstract void writeRGB(DataOutputStream output);

	public abstract boolean writeBGRP(DataOutputStream output);

	public abstract byte[] getY();

	public abstract byte[] getU();

	public abstract byte[] getV();

	public abstract byte[] getChannelC();

	public abstract byte[] getChannelB();

	public abstract byte[] getChannelA();

}
