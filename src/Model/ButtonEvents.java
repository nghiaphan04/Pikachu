package Model;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.MainFrame;
import Controller.MatrixImgIndex;

public class ButtonEvents extends JPanel implements ActionListener {
	
	private Color backGroundColor = Color.lightGray;
    private final int row = 8;
    private final int col = 8;
    private JButton[][] btnMtx;
    private MatrixImgIndex mtx;
    private MainFrame Frame;
    
    private boolean check;
    private int items;
    private int score = 0;
    
    private Point p1 = null;
    private Point p2 = null;
    
    public ButtonEvents() {
    	mtx = new MatrixImgIndex(row, col);
        newGame();
    }

    private void newGame() {
        createArrImgBtn();
    }

    private void createArrImgBtn() {
        setLayout(new GridLayout(row, col));
        btnMtx = new JButton[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                btnMtx[i][j] = createBtnImg(i + "," + j);
                Icon icon = getImg(mtx.getMatrix()[i][j]);
                btnMtx[i][j].setIcon(icon);
                add(btnMtx[i][j]);
            }
        }
    }

    private Icon getImg(int indexImg) {
    	int width = 52, height = 52;
        Image img = null;
        String image = "/View/icon/" + indexImg + ".jpg";
        img = new ImageIcon(getClass().getResource(image)).getImage();
        Icon icon = new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
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
    
    
    public void execute(Point p1, Point p2) {
        setDisable(btnMtx[p1.x][p1.y]);
        setDisable(btnMtx[p2.x][p2.y]);
    }

    private void setDisable(JButton btn) {
        btn.setIcon(null);
        btn.setBackground(backGroundColor);
        btn.setEnabled(false);
    }
    

    public JButton[][] getBtnMtx() {
        return btnMtx;
    }

    public void setBtnMtx(JButton[][] btnMtx) {
        this.btnMtx = btnMtx;
    }
    
    public void actionPerformed(ActionEvent e) {
    	items = col * row / 2;
        String indexBtn = e.getActionCommand();
        int indexTrim = indexBtn.lastIndexOf(",");
        int x = Integer.parseInt(indexBtn.substring(0, indexTrim));
        int y = Integer.parseInt(indexBtn.substring(indexTrim + 1));
        if (p1 == null) {
			p1 = new Point(x, y);
			btnMtx[p1.x][p1.y].setBorder(new LineBorder(Color.red));
		} else {
			p2 = new Point(x, y);
			check = mtx.checkPoint(p1, p2);
			if (check != false) {
				mtx.getMatrix()[p1.x][p1.y] = 0;
				mtx.getMatrix()[p2.x][p2.y] = 0;
				execute(p1, p2);
				check = false;
				score += 10;
				items--;
			}
			btnMtx[p1.x][p1.y].setBorder(null);
			p1 = null;
			p2 = null;
			if (items == 0) {
				Frame.showDialogNewGame("Congratulation", "You win", 1);
			}
		}
    }
}
