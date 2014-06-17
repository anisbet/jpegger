package nisbet.andrew.transform;

import nisbet.andrew.bmp.BitMap;
import nisbet.andrew.bmp.Image;


public class YUVToRGBTransformer implements Transformer
{
	
	private Image bmp = null;

//	convert  yuv to rgb from http://msdn.microsoft.com/en-us/library/ms893078
//	R = clip(( 298 * C           + 409 * E + 128) >> 8)
//	G = clip(( 298 * C - 100 * D - 208 * E + 128) >> 8)
//	B = clip(( 298 * C + 516 * D           + 128) >> 8)
	
	public YUVToRGBTransformer(Image bmp)
	{
		this.bmp = bmp;
	}
	
    @Override
    public boolean applyTransform()
    {
    	int size = bmp.getSize();
    	BitMap bitMap = bmp.getBitMap();
    	byte[] r = bitMap.getRed();
    	byte[] g = bitMap.getGreen();
    	byte[] b = bitMap.getBlue();
    	byte R = 0, G = 0, B = 0;
    	short C = 0, D = 0, E = 0;
    	
    	for (int i = 0; i < size; i++)
    	{
    		C = (byte) (r[i] - 16); // TODO check that this will stay unsigned.
        	D = (byte) (g[i] - 128);
        	E = (byte) (b[i] - 128);
    		R = (byte)(( 298 * C           + 409 * E + 128) >> 8);
    		G = (byte)(( 298 * C - 100 * D - 208 * E + 128) >> 8);
    		B = (byte)(( 298 * C + 516 * D           + 128) >> 8);
    		r[i] = R;
    		g[i] = G;
    		b[i] = B;
    	}
    	System.out.println("transformation from RGB to YUV complete.");
    	return true;
    }

}
