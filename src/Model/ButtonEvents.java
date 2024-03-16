package Model;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.MatrixImgIndex;
public class ButtonEvents extends JPanel implements ActionListener {

    private int row = 8;
    private int col = 8;
    private int score;
    private JButton[][] btnMtx ;
    private MatrixImgIndex mtx ;

    public ButtonEvents() {
        mtx = new MatrixImgIndex(row, col);
        newGame();
    }
    private void newGame() {
    	createArrImgBtn();
    }
    private void createArrImgBtn() {
        btnMtx = new JButton[row][col];
        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
                btnMtx[i][j] = createBtnImg(i + "," + j);
                Icon icon = getImg(mtx.getMatrix()[i][j]);
                btnMtx[i][j].setIcon(icon);
                
                
                add(btnMtx[i][j]); // add button to the panel
            }
        }
    }
    private Icon getImg(int indexImg) {
    	int width = 53, height = 53;
		Image image = new ImageIcon(getClass().getResource(
				"/View/icon/BgImage (5).png")).getImage();
		Icon icon = new ImageIcon(image.getScaledInstance(width, height,
				image.SCALE_SMOOTH));
		return icon;
		
    }
    private JButton createBtnImg(String str) {
    	JButton btn = new JButton();
    	btn.setActionCommand(str);
		btn.setBorder(null);
		btn.addActionListener(this);
		btn.setContentAreaFilled(false); 
        btn.setFocusPainted(false); 
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return btn;
    	
    }
    
    public JButton[][] getBtnMtx() {
		return btnMtx;
	}
	public void setBtnMtx(JButton[][] btnMtx) {
		this.btnMtx = btnMtx;
	}
	@Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String[] position = command.split(",");
        int i = Integer.parseInt(position[0]);
        int j = Integer.parseInt(position[1]);

        // Set border color to red for the clicked button
        btnMtx[i][j].setBorder(BorderFactory.createLineBorder(Color.RED));
        // Now you know which button has been pressed because you have i and j
    }

    

}