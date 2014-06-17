package nisbet.andrew;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nisbet.andrew.compression.CompressionManager;

/**
 * This is the GUI for the app. It opens and closes the bit map manages the screen data and 
 * 
 * @author andrew
 *
 */
public class Jpegger extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 3091723933322990316L;
	private final static String CHROMA_COMPRESSION_LABEL = "Chroma Compression Rate: ";
	private final static String TOTAL_COMPRESSION_LABEL	 = "Total Compression Rate: ";
	
	private final static String FILE_NAME = "/home/andrew/414/assign2/vincent-van-gogh-starry-night.bmp";
	
	private JLabel chromaCompressionJL 	= new JLabel(Jpegger.CHROMA_COMPRESSION_LABEL);
	private JLabel totalCompressionJL	= new JLabel(Jpegger.TOTAL_COMPRESSION_LABEL);
	private JPanel pane 				= new JPanel();
	private JButton openB 				= new JButton("Open");
	private JButton closeB				= new JButton("Close");
	private DisplayBuffer inputImage    = new DisplayBuffer();
	private DisplayBuffer outputImage	= new DisplayBuffer();
	
	// buttons to do conversions
	private JButton convertToYuvB		= new JButton("Convert to YUV");
	private JButton displayCosineTB		= new JButton("Display Cosine T.");
	private JButton displayDecompressB 	= new JButton("Display Decompress");
	private JButton displayDiffB		= new JButton("Display Diff");
	
	private CompressionManager compressionManager = null;
	
	public Jpegger()
	{
		
		// Create the opening screen.
		super("CMPUT 414 JPEG Encoding");
		setBounds(80, 80, 0, 0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		// original image panel
		container.add(getOriginalImagePanel(), BorderLayout.WEST);
		// button panel
		container.add(getButtonPanel(), BorderLayout.CENTER);
		// compressed image display
		container.add(getCompressedImagePanel(), BorderLayout.EAST);
		// Open close button panel
		container.add(getOpenCloseButtonPanel(), BorderLayout.SOUTH);
		
		container.add(getCompressionResults(), BorderLayout.NORTH);
	
		compressionManager = new CompressionManager(this);

		openB.requestFocus();
		pack();
		setVisible(true);
	}
	
	private JPanel getOriginalImagePanel()
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Original Image"));
		inputImage.setPreferredSize(new Dimension(400,300));
		//inputImage.setBounds(0, 0, 300, 250);
		panel.add(inputImage);
		return panel;
	}
	
	private Component getCompressedImagePanel()
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Compressed Image"));
		outputImage.setPreferredSize(new Dimension(400,300));
		//outputImage.setBounds(0, 0, 300, 250);
		panel.add(outputImage);
		return panel;
	}
	
	private Component getCompressionResults()
	{
		JPanel panel = new JPanel(new GridLayout(1,2));
		panel.setBorder(BorderFactory.createTitledBorder("Compression results"));
		panel.add(chromaCompressionJL);
		panel.add(totalCompressionJL);
		return panel;
	}

	private JPanel getOpenCloseButtonPanel()
	{
		JPanel panel = new JPanel(new GridLayout(1,3));
		panel.add(new JPanel());
		panel.add(new JPanel());
		// set the action listeners
		openB.addActionListener(this);
		closeB.addActionListener(this);
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		buttonPanel.add(closeB);
		buttonPanel.add(openB);
		panel.add(buttonPanel);
		return panel;
	}

	private JPanel getButtonPanel()
	{
		JPanel panel = new JPanel(new GridLayout(3,1));
		convertToYuvB.addActionListener(this);
		displayCosineTB.addActionListener(this);
		displayDecompressB.addActionListener(this);
		displayDiffB.addActionListener(this);
		JPanel panel1 = new JPanel(new GridLayout(4,1));
		panel1.add(convertToYuvB);
		panel1.add(displayCosineTB);
		panel1.add(displayDecompressB);
		panel1.add(displayDiffB);
		panel.add(new JPanel());
		panel.add(panel1);
		panel.add(new JPanel());
		return panel;
	}

	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    new Jpegger();
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		Object source = event.getSource();
		if (source == openB)
		{
//			setChromaCompressionLabel("50%");
//			JOptionPane.showMessageDialog(null, "I got that", "Message", JOptionPane.PLAIN_MESSAGE);
//			setVisible(true);
			// TODO replace this with an open file dialog box and while block for repeatedly opening 
			// invalid files.
			try
			{
				compressionManager.setCompressionImage(Jpegger.FILE_NAME);
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		else if (source == closeB)
		{
			cleanUp();
		}
		else if (source == convertToYuvB)
		{
			compressionManager.convertToYUV();
		}
		else if (source == displayCosineTB)
		{
			compressionManager.computeCosineTransform();
		}
		else if (source == displayDecompressB)
		{
			compressionManager.decompressImage();
		}
		else if (source == displayDiffB)
		{
			compressionManager.displayImageDifference();
		}
	}
	
	public void cleanUp()
	{
		// Do other cleanup activities.
		System.exit(0);
	}
	
	public void setChromaCompressionLabel(String message)
	{
		chromaCompressionJL.setText(Jpegger.CHROMA_COMPRESSION_LABEL + message);
	}
	
	/**
	 * Sets the message for the label
	 * @param message
	 */
	public void setTotalCompressionLable(String message)
	{
		totalCompressionJL.setText(Jpegger.TOTAL_COMPRESSION_LABEL + message);
	}

	public DisplayBuffer getInputDisplayBuffer()
	{
		return this.inputImage;
	}

	public DisplayBuffer getOutputDisplayBuffer()
	{
		return this.outputImage;
	}

}
