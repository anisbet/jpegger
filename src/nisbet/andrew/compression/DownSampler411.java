/**
 * 
 */
package nisbet.andrew.compression;

import nisbet.andrew.bmp.Image;


/**
 * Due to the densities of color- and brightness-sensitive receptors in the human eye, 
 * humans can see considerably more fine detail in the brightness of an image (the Y' component) 
 * than in the hue and color saturation of an image (the Cb and Cr components). Using 
 * this knowledge, encoders can be designed to compress images more efficiently.
 * The transformation into the Y′CBCR color model enables the next usual step, which is 
 * to reduce the spatial resolution of the Cb and Cr components (called "downsampling" 
 * or "chroma subsampling"). The ratios at which the downsampling is ordinarily done for 
 * JPEG images are 4:4:4 (no downsampling), 4:2:2 (reduction by a factor of 2 in the 
 * horizontal direction), or (most commonly) 4:2:0 (reduction by a factor of 2 in both 
 * the horizontal and vertical directions). For the rest of the compression process, Y', 
 * Cb and Cr are processed separately and in a very similar manner.
 * @author andrew
 *
 */
public class DownSampler411 implements Compressor
{

	public DownSampler411(Image compressionImage)
	{
		// TODO Here I want to take a plane from a planar image and down
		// sample it. That means that I have to tell the tile to average 
		// itself and then restructure the tile set to accomdate the smaller 
		// plane size.
	}

	@Override
	public String getCompressionRatio()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean compress()
	{
		// TODO Auto-generated method stub
		return false;
	}

}
