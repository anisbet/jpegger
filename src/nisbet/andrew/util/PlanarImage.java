package nisbet.andrew.util;

import java.util.HashMap;

import nisbet.andrew.bmp.BMP;
import nisbet.andrew.bmp.BitMap;
import nisbet.andrew.bmp.Image;
import nisbet.andrew.bmp.ImageType;
import nisbet.andrew.bmp.UnsupportedFeatureException;


/** 
 * Planar Image is a collection of tile sets. A tile set is an nxn set of tiles themselves m^2 in size.
 * Planar Image can save p planes of them, thus resulting in a nxnxp set of tiles.
 * It sounds confusing but each plane can contain a dissimilar number of tiles to each other. Access
 * each plane by a `z' parameter and each tile within the tile set by its `x' and `y' coordinate.
 * 
 * You store planes by name R or G or B, or xyz what-have-you.
 * @author andrew
 *
 */
public class PlanarImage
{
	public final static String RED		= "r";
	public final static String GREEN	= "g";
	public final static String BLUE		= "b";
	public final static String Y 		= "y";
	public final static String U 		= "u";
	public final static String V 		= "v";
	
	private HashMap<String, TileSet> planeSet = null;
	private Image image = null;

	public PlanarImage(Image image)
	{
		this.planeSet = new HashMap<String, TileSet>();
		this.image = image;
		if (image.getImageType() == ImageType.BMP)
		{
			// extract and convert each channel to a TileSet of Bytes.
			BitMap imageBitMap = image.getBitMap();
			// get Red channel
			convertToPlaneOfTiles(PlanarImage.RED, imageBitMap.getChannelA());
			convertToPlaneOfTiles(PlanarImage.GREEN, imageBitMap.getChannelB());
			convertToPlaneOfTiles(PlanarImage.BLUE, imageBitMap.getChannelC());
		}
		else
		{
			throw new UnsupportedFeatureException("PlanarImage: Unsupported image type.");
		}
	}
	
	/**
	 * Writes the image back to file.
	 * @param type
	 * @param name
	 * @return true if successful and false otherwise.
	 */
	public boolean writeImage(ImageType type, String name)
	{
		// Steps to do that
		// determine type of image to write.
		if (type == ImageType.BMP)
		{
			//reconstituteChannel();
		}
		else
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * Converts the byte[] to a TileSet of Bytes named `name'.
 	 * @param key
	 * @param channel
	 */
	private void convertToPlaneOfTiles(String key, byte[] channel)
	{
		// convert to bytes and store as a bit plane.
		TileFactory tileFactory = new TileFactory(image.getSize(), image.getWidth(), image.getHeight());
		TileSet tileSet 		= tileFactory.getTileSet(channel);
		this.planeSet.put(key, tileSet);
	}
	
	/**
	 * @param plane
	 * @return TileSet of the named plane of an image, and null if the plane doesn't exist.
	 */
	public TileSet getTileSet(String plane)
	{
		return this.planeSet.get(plane);
	}
}
