package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;



public class ImagePanel extends JPanel {
	
	


    private Image backgroundImage;

    // Sử dụng đường dẫn ảnh tĩnh từ hệ thống
    public ImagePanel(String fileName) throws IOException {
        this.backgroundImage = ImageIO.read(new File(fileName));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}