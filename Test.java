import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

class Test extends JFrame
{
	Test()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      		this.setTitle("Test Sound Clip");
      		this.setSize(300, 200);
      		this.setVisible(true);
	}	

	public void compare()
	{
		BufferedImage imgA = null;
        BufferedImage imgB = null;
 
        try
        {
            File fileA = new File("C:\\Users\\SSTC\\Desktop\\road signs\\Cattle.jpg");
            File fileB = new File("C:\\Users\\SSTC\\Desktop\\road signs\\Cattle.jpg");
 
            imgA = ImageIO.read(fileA);
            imgB = ImageIO.read(fileB);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        int width1 = imgA.getWidth();
        int width2 = imgB.getWidth();
        int height1 = imgA.getHeight();
        int height2 = imgB.getHeight();
 
        if ((width1 != width2) || (height1 != height2))
            System.out.println("Error: Images dimensions"+
                                             " mismatch");
        else
        {
            long difference = 0;
            for (int y = 0; y < height1; y++)
            {
                for (int x = 0; x < width1; x++)
                {
                    int rgbA = imgA.getRGB(x, y);
                    int rgbB = imgB.getRGB(x, y);
                    int redA = (rgbA >> 16) & 0xff;
                    int greenA = (rgbA >> 8) & 0xff;
                    int blueA = (rgbA) & 0xff;
                    int redB = (rgbB >> 16) & 0xff;
                    int greenB = (rgbB >> 8) & 0xff;
                    int blueB = (rgbB) & 0xff;
                    difference += Math.abs(redA - redB);
                    difference += Math.abs(greenA - greenB);
                    difference += Math.abs(blueA - blueB);
                }
            }
 
            // Total number of red pixels = width * height
            // Total number of blue pixels = width * height
            // Total number of green pixels = width * height
            // So total number of pixels = width * height * 3
            double total_pixels = width1 * height1 * 3;
 
            // Normalizing the value of different pixels
            // for accuracy(average pixels per color
            // component)
            double avg_different_pixels = difference /
                                          total_pixels;
 
            // There are 255 values of pixels in total
            double percentage = (avg_different_pixels /
                                            255) * 100;
 
            System.out.println("Difference Percentage-->" +
                                                percentage);
        
		if(percentage>=10.0000)
		{
			beep_sound();
		}
	}
}
	public void beep_sound()
	{
		try {
         // Open an audio input stream.
         URL url = this.getClass().getClassLoader().getResource("Voice 001.wav");
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         // Get a sound clip resource.
         Clip clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
	}
	public static void main(String[] args) {
      new Test().compare();
   }
}