/**
 * 
 */
package nisbet.andrew.bmp;


/**
 * @author andrew
 *
 */
public interface Image
{

	public abstract int getNumberPixels();

	public abstract BitMap getBitMap();

	public abstract int getWidth();

	public abstract ImageType getImageType();

	public abstract int getHeight();

	public abstract int getSize();

	public abstract String getSize(boolean asString);

	public abstract String getName();

	public abstract boolean write();

	public abstract void rename(String newName);

}
