/**
 * 
 */
package nisbet.andrew.bmp;


/**
 * @author andrew
 *
 */
public class UnsupportedFeatureException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -813107411975136244L;
	
	public UnsupportedFeatureException(String msg)
	{
		System.err.println(msg);
	}
}
