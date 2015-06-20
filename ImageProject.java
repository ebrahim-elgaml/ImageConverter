package assignement2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class ImageProject extends JFrame {
	  private static final long serialVersionUID = 1L;
	 
	  public  ImageProject(BufferedImage img1 ,BufferedImage img2){
		  this.setLayout(new GridLayout(2,2));
		  setDefaultCloseOperation(EXIT_ON_CLOSE);
		  Image newImage = img1.getScaledInstance(400, 400, Image.SCALE_FAST);
		  JLabel label1 = new JLabel(new ImageIcon(newImage));
		  //label1.setBounds(50, 20, 20, 20);
		  //label1.setSize(100, 100);
		  this.getContentPane().add(label1);
		  Image newImage2 = img2.getScaledInstance(400, 400, Image.SCALE_FAST);
		  JLabel label2 = new JLabel(new ImageIcon(newImage2));
		  //label2.setBounds(50, 20, 20, 20);
		  //label2.setSize(100, 100);
		  this.getContentPane().add(label2);
		  // negate image
		  Image newImage3 = negateImage(img1).getScaledInstance(400, 400, Image.SCALE_FAST);
		  JLabel label3 = new JLabel(new ImageIcon(newImage3));
		  //label3.setSize(100, 100);
		  this.getContentPane().add(label3);
		  //
		  //Blending
		  Image newImage4 = combine(img1,img2).getScaledInstance(400, 400, Image.SCALE_FAST);
		  JLabel label4 = new JLabel(new ImageIcon(newImage4));
		  //label4.setSize(100, 100);
		  this.getContentPane().add(label4);
		  //
		  BufferedImage bf = null;
		  try {
			 bf =ImageIO.read(new File("guclogo.jpg"));
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		  this.setIconImage(bf);
		  this.setTitle("DMET501 Image Processor!");
		  setSize(810,730);
		  setLocation(250,0);
		  setResizable(false);
		  setVisible(true);
	  }
	  public static BufferedImage deepCopy(BufferedImage bi) {
		  ColorModel cm = bi.getColorModel();
		  boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		  WritableRaster raster = bi.copyData(null);
		  return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
		 }
	  public static BufferedImage negateImage(BufferedImage img ){
		  BufferedImage newImage = deepCopy(img);
		  int w = newImage.getWidth();
		  int h = newImage.getHeight();
		  for(int i = 0;i<w;i++){
		  	for(int j = 0  ;j<h;j++){
		  		Color newColor = new Color (255-((newImage.getRGB(i, j)>>16)& 0xFF),255-((newImage.getRGB(i, j)>>8)& 0xFF),255-((newImage.getRGB(i, j)>>0)& 0xFF),255-((newImage.getRGB(i, j)>>24)& 0xFF));	
		  		newImage.setRGB(i,j,newColor.getRGB());
		    	}
		    }
		return newImage;
		  
	  }
	  public static BufferedImage combine(BufferedImage image1,BufferedImage image2){
		  BufferedImage img1 = deepCopy(image1);
		  BufferedImage img2 = deepCopy(image2);
		  Image x = img2.getScaledInstance(img1.getWidth(), img1.getHeight(), Image.SCALE_FAST);
		  img2=toBufferedImage(x);
		  BufferedImage newImage = deepCopy(image1);
		  
		  int w = img1.getWidth();
		  int h = img2.getHeight();
		  for(int i = 0;i<w;i++){
		  	for(int j = 0  ;j<h;j++){
		  		int newR = (int)((((img1.getRGB(i, j)>>16)& 0xFF)*0.8)+(((img2.getRGB(i, j)>>16)& 0xFF)*0.2));
		  		int newG = (int)((((img1.getRGB(i, j)>>8)& 0xFF)*0.8)+(((img2.getRGB(i, j)>>8)& 0xFF)*0.2));
		  		int newB = (int)((((img1.getRGB(i, j)>>0)& 0xFF)*0.8)+(((img2.getRGB(i, j)>>0)& 0xFF)*0.2));
		  		int newAlpha = (int)((((img1.getRGB(i, j)>>24)& 0xFF)*0.8)+(((img2.getRGB(i, j)>>24)& 0xFF)*0.2));
		  		Color newColor = new Color (newR,newG,newB,newAlpha);	
		  		newImage.setRGB(i,j,newColor.getRGB());
		    	}
		    }
		  return newImage;
		  
	  }
	  public static BufferedImage toBufferedImage(Image img)
	  {
	      if (img instanceof BufferedImage)
	      {
	          return (BufferedImage) img;
	      }

	      // Create a buffered image with transparency
	      BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	      // Draw the image on to the buffered image
	      Graphics2D bGr = bimage.createGraphics();
	      bGr.drawImage(img, 0, 0, null);
	      bGr.dispose();

	      // Return the buffered image
	      return bimage;
	  }
	  
}
