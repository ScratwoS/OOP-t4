package presentations;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LoadImage {
	public BufferedImage loadImage(String fileName) {
		fileName = "img/" + fileName;
		BufferedImage im = null;
		try {
			im = ImageIO.read(getClass().getClassLoader().getResource(fileName));
		} catch (IOException e) {
			System.out.println("Error loading " + fileName);
		}
		return im;
	}
	public BufferedImage loadImage(URL url){
		BufferedImage im = null;
		try{
			im = ImageIO.read(url);
		}catch(IOException e){
			try {
				im = ImageIO.read(getClass().getClassLoader().getResource("img/not_found_image.jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return im;
	}
	public ImageIcon getIcon(JLabel label, BufferedImage image) {
		int x = label.getSize().width;
		int y = label.getSize().height;
		int ix = image.getWidth();
		int iy = image.getHeight();
		int dx, dy;
		if (x / y > ix / iy) {
			dy = y;
			dx = dy * ix / iy;
		} else {
			dx = x;
			dy = dx * iy / ix;
		}
		ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(dx, dy, image.SCALE_SMOOTH));
		return imageIcon;
	}
}
