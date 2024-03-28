package View;
import java.time.LocalDateTime;

import java.awt.BorderLayout;
import Model.ButtonEvents;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import javax.swing.JToolBar;
import java.util.Random;
import database.connectDB;



public class MainFrame extends JFrame implements ActionListener,Runnable{
	private int width = 610,height =670;
	private JPanel mainPanel;

	private JProgressBar progressTime;
	private JLabel score;
	private JButton btnExitButton;
	private JButton btnNewGame ;

	StartAppPanel secondPanel;
	ButtonEvents btEvns;
	private int maxTime = 5;
	private int time = maxTime;
	private boolean pause;
	

	private Thread timerThread;
	
	private int id;
	private String namePlayer;
	private String times;
	private int scores;
	private connectDB connect;
	
    public static void main(String[] args) {
    	
                try {
                    new MainFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            
        
}

 
    public MainFrame() {
        
    	btEvns = new ButtonEvents(this);
    	pause=false;
    	
		setTitle("Pokemon Game");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
		
		showStartScreen();
    }
    private void showStartScreen() {
        secondPanel = new StartAppPanel(this);
        getContentPane().add(secondPanel);
        revalidate();
        repaint();
        
    }
    public void showMainPanel() {
    	
        getContentPane().removeAll(); 
        mainPanel = createMainPanel(); 
        getContentPane().add(mainPanel); 
        validate(); 
        repaint(); 
    }
    
 public void newGame() {
		showMainPanel();
		time = maxTime;
		mainPanel.setVisible(true);
		btEvns = new ButtonEvents(this);
		btnExitButton.addActionListener(this);
		btnNewGame.addActionListener(this); ;
		pause = false;
	    if (timerThread != null && timerThread.isAlive()) {
	    	timerThread.interrupt(); 
	        try {
	            timerThread.join(); 
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	           
	        }
	    }
	    
	    
	    timerThread = new Thread(this);
	    timerThread.start();                 
		
	}
    public JPanel createMainPanel() {
    	
    	JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        
        JLabel labelBg = new JLabel();
    	labelBg.setIcon(resizeImg("/View/icon/BgImage.png"));
    	labelBg.setBounds(0, 0, 600, 660);
    	
    	layeredPane.add(createExitPanel());
    	layeredPane.add(createControlPanel());
        layeredPane.add(createGraphicsPanel());
        layeredPane.add(labelBg);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
    	
		mainPanel.add(layeredPane);
        
		return mainPanel;
    }
    public ImageIcon resizeImg(String src) {
        ImageIcon icon = new ImageIcon(getClass().getResource(src));
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(600,660,java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        return icon;
    }
    public JPanel createGraphicsPanel() {
    	JPanel panel = new JPanel(new GridLayout(8,8));
    	for(int i=0;i<8;i++) {
    		for(int j =0 ;j<8;j++) {
    			panel.add(btEvns.getBtnMtx()[i][j]);
    		}
    	}
    	
		panel.setBackground(new Color(225, 239, 163));
		panel.setBounds(72,169 , 449, 450);
    	return panel;
    }
    public JPanel createControlPanel() {
	    JPanel Mainpanel = new JPanel(null);
	    JLabel tiltleScore = new JLabel("Score:");
	    score = new JLabel("0");
	    JLabel tiltleTime = new JLabel("Time:");
	    btnNewGame = new JButton("New game");
	    
	    progressTime = new JProgressBar(0, 100);
	    progressTime.setValue(100);
	    
	    score.setFont(new Font("Tahoma", Font.BOLD, 15));
	    tiltleScore.setFont(new Font("Tahoma", Font.BOLD, 15));
	    tiltleTime.setFont(new Font("Tahoma", Font.BOLD, 15));

	 
	    tiltleScore.setBounds(10, 10, 200, 20);
	    score.setBounds(65, 10, 200, 20);
	    tiltleTime.setBounds(10, 30, 200, 20);
	    progressTime.setBounds(55, 34, 200, 13);
	    btnNewGame.setBounds(350, 17, 100, 30);

	    
	    Mainpanel.add(tiltleScore);
	    Mainpanel.add(score);
	    Mainpanel.add(tiltleTime);
	    Mainpanel.add(progressTime);
	    Mainpanel.add(btnNewGame);

	    Mainpanel.setBackground(Color.yellow);
	    Mainpanel.setBounds(72,110 , 450, 60); 
	    
	    btnNewGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    btnNewGame.setFocusPainted(false);

	    
	    return Mainpanel;
    }
    public JPanel createExitPanel() {
    	JPanel panel = new JPanel(null);
    	
    	panel.setBackground(new Color(0,0,0,0));
	    panel.setBounds(530,603 , 63, 35);
	    
	    btnExitButton = new JButton("Exit");

	   
	    
	    btnExitButton.setBounds(0, 0, 60, 23);
	    btnExitButton.setFocusPainted(false);
	    btnExitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    panel.add(btnExitButton);
    	
    	return panel;
    }
    public void updateScoreLabel(String newScore) {
        score.setText(newScore);
    }
   
    @Override
    public void run() {
        while (!pause) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            time--;
            progressTime.setValue((int) ((double) time / maxTime * 100));
            if (time <= 0) {
            	id = secondPanel.getNumber();
            	namePlayer = secondPanel.getNamePlayer();
            	times = String.valueOf(secondPanel.getTimes());
            	scores = Integer.valueOf(score.getText());
            	connectDB newConnect = new connectDB();
            	newConnect.addDataToDB(id,namePlayer,scores,times );
            	System.out.println(scores);
            	System.out.println(id+ " " +namePlayer + " " + times);
                EventQueue.invokeLater(() -> showDialogNewGame("You lost, do you want create new game", "Warning", "lost game"));
                break; 
            }
        }
    }
    
    
    public void setInformationUser(int ID,String UserName,String Times) {
    	id= ID;
    	namePlayer = UserName;
    	times = Times;
    }
    
    public void showDialogNewGame(String message, String title, String action) {
        pause = true;

        int select = JOptionPane.showOptionDialog(this, message, title,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (select == 0) {
            if ("new game".equals(action) || "lost game".equals(action)) {
                if (timerThread != null && timerThread.isAlive()) {
                	try {
                        timerThread.join(); 
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                timerThread = new Thread(this);
                timerThread.start();
                newGame();
            } else if ("exit".equals(action)) {
                getContentPane().removeAll();
                showStartScreen();
            }
        } else { 
            if ("new game".equals(action)) {
                pause = false;
                timerThread = new Thread(this);
                timerThread.start();
            } else if ("lost game".equals(action)) {
                System.exit(1);
            } else if ("exit".equals(action)) {
            	pause = false;
                timerThread = new Thread(this);
                timerThread.start();
            }
        }
    }



	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnExitButton) {
            showDialogNewGame("Are you sure you want to exit?", "Exit","exit");
        }
		if(e.getSource()== btnNewGame) {
			System.out.println("he");
        	showDialogNewGame("Your game hasn't done. Do you want to create a new game?", "Warning","new game");
        }
	
		
		
	}
}