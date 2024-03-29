package Model;

import javax.swing.*;
import java.util.Random;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.MainFrame;
import Controller.MatrixImgIndex;

import View.StartAppPanel;
import database.connectDB;
public class ButtonEvents extends JPanel implements ActionListener {
	
	private Color backGroundColor = Color.lightGray;
    private final int row = 8;
    private final int col = 8;
    private JButton[][] btnMtx;
    private MatrixImgIndex mtx;
    private MainFrame Frame;
    private StartAppPanel xx;
    private boolean check;
    private int items = 32;
    private int tmp;
    private int score;
    private String name;
    private Point p1 = null;
    private Point p2 = null;
    
    StartAppPanel secondPanel;
	private int id;
	private String namePlayer;
	private String times;
	private int scores;
	private connectDB connect;
    
    
    public ButtonEvents(MainFrame frame) {
    	this.Frame =  frame;
    	mtx = new MatrixImgIndex(row, col);
        newGame();
    }

    private void newGame() {
    	this.score = 0;
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
    	int width = 54, height = 54;
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
    


	@Override
	public String toString() {
		return  String.valueOf(score);
	}
	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public JButton[][] getBtnMtx() {
        return btnMtx;
    }

    public void setBtnMtx(JButton[][] btnMtx) {
        this.btnMtx = btnMtx;
    }
 
    public void actionPerformed(ActionEvent e) {
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
            if (check) {
                mtx.getMatrix()[p1.x][p1.y] = 0;
                mtx.getMatrix()[p2.x][p2.y] = 0;
                execute(p1, p2);
                score += 10;
                Frame.updateScoreLabel(String.valueOf(this.score));
                items--;
                
            }
            btnMtx[p1.x][p1.y].setBorder(null);
            p1 = null;
            p2 = null;
            if (items == 0) {            
            	id = secondPanel.getNumber();
            	namePlayer = secondPanel.getNamePlayer();
            	times = String.valueOf(secondPanel.getTimes());
            	scores = Integer.valueOf(score);
            	connectDB newConnect = new connectDB();
            	newConnect.addDataToDB(id,namePlayer,scores,times );
                Frame.showDialogNewGame("You win, do you want to create a new game?", "Congratulation","new game");
            }
        }   
    }
}
