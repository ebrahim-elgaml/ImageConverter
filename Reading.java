package assignement2;

import java.awt.FlowLayout;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Reading extends JFrame implements ActionListener{

   	  private static final long serialVersionUID = 1L;
   	  private BufferedImage img = null;
   	  private BufferedImage img2 = null;
	  
	  JPanel buttonHolder = new JPanel();
	  JFileChooser chooser = new JFileChooser();
	  JFileChooser chooser2 = new JFileChooser();
	  
	  final JButton errorBtn = new JButton("Ok");
	  JFrame a ;
	  
	  final JButton chooseBtn = new JButton("Choose");
	  final JButton exitBtn = new JButton("Exit");
	 
	  public  Reading(){
		  chooser.setControlButtonsAreShown(false);
		  chooser2.setControlButtonsAreShown(false);
		  buttonHolder.setLayout(new FlowLayout());
		  chooseBtn.setEnabled(true);
		  exitBtn.setEnabled(true);
		  chooseBtn.addActionListener(this);
		  exitBtn.addActionListener(this);
		  buttonHolder.add(chooseBtn);
		  buttonHolder.add(exitBtn);  
		  chooser.setCurrentDirectory(new java.io.File("."));
		  chooser.setDialogTitle("Choose 1st Image");
		  chooser2.setCurrentDirectory(new java.io.File("."));
		  chooser2.setDialogTitle("Chooses 2nd Image");
		  getContentPane().add(chooser,"North");
		  getContentPane().add(chooser2,"Center");
		  getContentPane().add(buttonHolder,"South");
		  BufferedImage bf = null;
		  try {
			 bf =ImageIO.read(new File("guclogo.jpg"));
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		  this.setIconImage(bf);
		  this.setTitle("DMET501 Image Processor!");
		  setDefaultCloseOperation(EXIT_ON_CLOSE);
		  setSize(600,600);
		  setLocation(400,100);
		  setResizable(false);
		  setVisible(true);
	  }
	  public static void main  (String[]args){
		  new Reading();
	  }
	
	  private void readImage() {
		
	    try{
	    		
	    		File ImageSelected =chooser.getSelectedFile();
	    		img = ImageIO.read(ImageSelected);
	    		File ImageSelected2 =chooser2.getSelectedFile();
	    		img2 = ImageIO.read(ImageSelected2);
		        System.out.println(img);
		        System.out.println(img2);
	    	}catch (Exception e) {
	    		this.setVisible(false);
	    		a = new JFrame("Input Error");
	    		JLabel s = new JLabel();
	    		s.setText("Please Insert a correct Image");
	    		a.getContentPane().add(s,"Center");
	    		a.setTitle("Input Error");
	    		
	  		    errorBtn.addActionListener(this);
	  		    errorBtn.setEnabled(true);
	  		    a.add(errorBtn,"South");
	  		    
	  		    a.setSize(200,100);
	  		    a.setLocation(700,300);
	  		    a.setResizable(false);
	  		    a.setVisible(true); 
	  		    a.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    	}
		
	  }

	public BufferedImage getImg() {
		return img;
	}
	public void setImg(BufferedImage img) {
		this.img = img;
	}
	public BufferedImage getImg2() {
		return img2;
	}
	public void setImg2(BufferedImage img2) {
		this.img2 = img2;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Choose")){
			readImage();
			new ImageProject(img,img2);
			}
		
		else if(e.getActionCommand().equals("Exit")){
			System.exit(0);
		}
		else if(e.getActionCommand().equals("Ok")){

			a.setVisible(false);
			new Reading();
		}
	}

}
