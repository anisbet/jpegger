package nisbet.andrew.compression;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import nisbet.andrew.DisplayBuffer;
import nisbet.andrew.Jpegger;
import nisbet.andrew.bmp.BMP;
import nisbet.andrew.bmp.Image;
import nisbet.andrew.compression.Compressor;
import nisbet.andrew.transform.RGBToYUVTransformer;
import nisbet.andrew.transform.Transformer;
import nisbet.andrew.util.PlanarImage;

/**
 * This class manages the compression to JPEG format.
 * @author andrew
 *
 */
public class CompressionManager
{
	private String baseFileName = null;			// Base name of the file we are useing in the process.
	private DisplayBuffer inputBuffer = null;	// display of the original image for comparison.
	private DisplayBuffer outputBuffer = null;	// display of the image as it compresses.
	private Image compressionImage = null;		// working copy of bmp
	private Jpegger jPegger = null;				// reference to the gui
	private String compressFileName = null;		// name of the compressed file.

    /**
     * @param jPegger
     */
    public CompressionManager(Jpegger jPegger)
	{
    	this.jPegger = jPegger;
    	this.inputBuffer = this.jPegger.getInputDisplayBuffer();
    	this.outputBuffer= this.jPegger.getOutputDisplayBuffer();
	}
    
    /**
     * Sets the image to compress.
     * @param originalBmpName
     * @throws FileNotFoundException 
     */
    public void setCompressionImage(String originalBmpName) throws FileNotFoundException
    {
    	// get a file handle because we are going to read the file.
    	this.baseFileName = originalBmpName;
    	File inputFile = new File(this.baseFileName);
    	
		if (inputFile.exists() == false)
		{
			throw new FileNotFoundException("Unable to find the BMP named '" + this.compressFileName + "'.");
		}
		
		try
		{
			this.compressionImage = new BMP(this.baseFileName);
			// create a new working copy of the compressionImage.
			this.compressFileName = computeOutFileName("_orig.bmp");
			this.compressionImage.rename(compressFileName);
	    	this.compressionImage.write();
		}
		catch (IOException e)
		{
			System.err.println("Error reading BMP '" + this.compressFileName + "'.");
		}
		
    	this.inputBuffer.setDisplayBuffer(this.baseFileName);
    	// Now make a PlanarImage to hold all the data.
    	PlanarImage planarImage = new PlanarImage(compressionImage);
    	
    }

	/**
	 * This method takes the name of the original file strips off the .bmp suffix
	 * and adds the argument suffix. This allows you to keep the original file name base
	 * with path but put an identifier code for each step of the compression process.
	 * The idea is at the end of it you will have different files with various stages of
	 * compression to compare and analyse after the process has completed.
	 * @param suffix This is a unique id to a process. For instance, if this is a 
	 * name for a yuv convertion, it can pass ``_yuv.bmp''.
	 * @return New name with `suffix' as a modifier to distinguish from the original.
	 */
	private String computeOutFileName(String suffix)
	{
		// find the full name without the suffix.
		String name = this.baseFileName.substring(0, this.baseFileName.lastIndexOf("."));
		name = name + suffix;
		return name;
	}

	/**
	 * @return true if the conversion was successful and false otherwise.
	 */
	public boolean convertToYUV()
	{
		if (this.compressionImage == null) return false;
		
		// Transform colour space.
		Transformer yuvTransformer = new RGBToYUVTransformer(this.compressionImage);
		boolean result = yuvTransformer.applyTransform();
		if (result == false) 
		{
			return false;
		}
		
		// down sample 
		Compressor downSample411 = new DownSampler411(this.compressionImage);
		result = downSample411.compress();
		
		// Write the changes to the bmp.
		this.compressFileName = this.computeOutFileName("_yuv.bmp");
		this.compressionImage.write();
		this.outputBuffer.setDisplayBuffer(this.compressFileName);
		// Update our progress on compression the image.
		this.jPegger.setChromaCompressionLabel(downSample411.getCompressionRatio());
		
		return result;
	}

	/**
	 * 
	 */
	public void computeCosineTransform()
	{
		// TODO compute cosine transform and update the imageBuffer.
		Compressor dctCompressor = new DCTCompressor(this.compressionImage);
		dctCompressor.compress();
		this.jPegger.setChromaCompressionLabel(dctCompressor.getCompressionRatio());
		
	}

	/**
	 * 
	 */
	public void decompressImage()
	{
		// TODO decompress the image and update the DisplayBuffer imageBuffer.
		
		
	}

	/**
	 * 
	 */
	public void displayImageDifference()
	{
		// TODO Update the image buffer with the decompressed image to view differences.
		
	}

}
