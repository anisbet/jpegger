package nisbet.andrew.bmp;

/**
 * Thrown if you try to read a BMP of a significantly different version number.
 * @author andrew
 *
 */
public class InvalidBMPException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -941817410712185746L;
	
	public InvalidBMPException(String msg)
	{
		System.err.println(msg);
	}
	
}