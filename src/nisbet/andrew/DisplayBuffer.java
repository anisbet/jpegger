package nisbet.andrew;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import nisbet.andrew.bmp.BMP;

/**
 * This class represents the display buffer used by the GUI to show what image is
 * @author andrew
 *
 */
public class DisplayBuffer extends JPanel
{

	private static final long serialVersionUID = -7304760746612701370L;
	private BufferedImage image = null;

	
	/**
	 * @param bmp
	 */
	public void setDisplayBuffer(File bmp)
	{
		try
		{
			this.image = ImageIO.read(bmp);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}
	
	/**
	 * Sets the image to a named bmp.
	 * @param bmp
	 */
	public void setDisplayBuffer(String bmp)
	{
		this.setVisible(false);
		try
		{
			this.image = ImageIO.read(new File(bmp));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		this.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.drawImage(image, 0, 0, null);
	}

}
