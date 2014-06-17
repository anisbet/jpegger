package nisbet.andrew.compression;

/**
 * Basic interface for any compressor.
 * @author andrew
 *
 */
public interface Compressor
{
    public String getCompressionRatio();
    public boolean compress();
}
