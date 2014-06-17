/**
 * 
 */
package nisbet.andrew.util;


/**
 * Use this class for your DCT transform so you can flip the value and restore it in the same object.
 * 
 * @author andrew
 *
 */
public class Byte implements Comparable<Object>
{
	public final static float MAX_SIZE = 255f;
	public final static float MIN_SIZE = 0f;
	public final static float MAX_SIGNED = 127f;
	public final static float MIN_SIGNED = -128f;
	
	private float value;
	
	/**
	 * @param b
	 */
	public Byte(byte b)
	{
		short sb = 0;
		if (b < 0) 
		{
			sb = (short) (128 + (255 - (~b + 0x80))); // convert to unsigned byte.
		}
		else
		{
			sb = (short)b;
		}
		value = (float)sb;
	}
	
	/**
	 * @param f
	 */
	public Byte(float f)
	{
		if (f > MAX_SIZE)
		{
			this.value = MAX_SIZE;
		}
		else if (f < MIN_SIZE)
		{
			this.value = MIN_SIZE;
		}
		else
		{
			this.value = f;
		}
	}
	
	/**
	 * 
	 */
	public Byte()
	{
		this.value = 0.0f;
	}

	/**
	 * @return
	 */
	public float asFloat()
	{
		return this.value;
	}
	
	/**
	 * @return stored value as a rounded byte value. This is an unsigned byte to convert to 
	 * byte when writing to file.
	 */
	public short asShortByte()
	{
		return (short) Math.round(this.value);
	}
	
	public byte asByte()
	{
		return (byte) Math.round(this.value);
	}
	
	/**
	 * Before computing the DCT of the 8×8 block, its values are shifted from a positive
	 * range to one centered around zero. For an 8-bit image, each entry in the original
	 * block falls in the range [0,255]. The mid-point of the range (in this case, the
	 * value 128) is subtracted from each entry to produce a data range that is centered
	 * around zero, so that the modified range is [ − 128,127].
	 */
	public void normalize()
	{
		float f = value - 128f;
		if (f > MAX_SIGNED)
		{
			this.value = MAX_SIGNED;
		}
		else if (f < MIN_SIGNED)
		{
			this.value = MIN_SIGNED;
		}
		else
		{
			this.value = f;
		}
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o)
	{
		return (int) (((Byte) o).value - this.value);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return String.valueOf(this.asShortByte());
	}

}
