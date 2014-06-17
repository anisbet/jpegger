package nisbet.andrew.transform;

import nisbet.andrew.bmp.BitMap;
import nisbet.andrew.bmp.Image;


/**
 * Converts a BMP from RGB colour space to YUV colour space.
 * @author andrew
 *
 */
public class RGBToYUVTransformer implements Transformer
{
	private Image bmp = null;
	
// convert  rgb to yuv from http://msdn.microsoft.com/en-us/library/ms893078
//	Y = ( (  66 * R + 129 * G +  25 * B + 128) >> 8) +  16
//	U = ( ( -38 * R -  74 * G + 112 * B + 128) >> 8) + 128
//	V = ( ( 112 * R -  94 * G -  18 * B + 128) >> 8) + 128
	
//	with reciprocal versions:
//	R = clip(( 298 * C           + 409 * E + 128) >> 8)
//	G = clip(( 298 * C - 100 * D - 208 * E + 128) >> 8)
//	B = clip(( 298 * C + 516 * D           + 128) >> 8)
	
	public RGBToYUVTransformer(Image bmp)
	{
		this.bmp = bmp;
	}
	
    @Override
    public boolean applyTransform()
    {
    	int size = bmp.getNumberPixels();
    	BitMap bitMap = bmp.getBitMap();
    	byte[] r = bitMap.getRed();
    	byte[] g = bitMap.getGreen();
    	byte[] b = bitMap.getBlue();
    	byte y, u, v;
    	
    	for (int i = 0; i < size; i++)
    	{
    		y = (byte) (( (  66 * r[i] + 129 * g[i] +  25 * b[i] + 128) >> 8) +  16);
    		u = (byte) (( ( -38 * r[i] -  74 * g[i] + 112 * b[i] + 128) >> 8) + 128);
    		v = (byte) (( ( 112 * r[i] -  94 * g[i] -  18 * b[i] + 128) >> 8) + 128);
    		r[i] = y;
    		g[i] = u;
    		b[i] = v;
    	}
    	System.out.println("transformation from RGB to YUV complete.");
    	return true;
    }

}
