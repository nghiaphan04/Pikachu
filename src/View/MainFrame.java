package View;

import java.awt.BorderLayout;
import Model.ButtonEvents;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import javax.swing.JToolBar;

public class MainFrame extends JFrame implements ActionListener,Runnable{
	private int width = 610,height =670;
	private JPanel mainPanel;
	private JProgressBar progressTime;
	private JLabel score;
	private JButton btnExitButton;
	ButtonEvents btEvns;
	private int maxTime = 10;
	private int time = maxTime;
	private boolean pause;
    private boolean resume;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    Thread thread = new Thread(frame);
                    thread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
}

 
    public MainFrame() {
        
    	btEvns = new ButtonEvents(this);
    	pause=false;
        resume= false;
    	getContentPane().add(mainPanel = createMainPanel());
		setTitle("Pokemon Game");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
		btnExitButton.addActionListener(this);
		
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
	    JButton btnNewGame = new JButton("New game");
	    
	    btnNewGame.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            showDialogNewGame("Your game hasn't done. Do you want to create a new game?", "Warning", 0);
	        }
	    });
	    
	    progressTime = new JProgressBar(0, 100);
	    progressTime.setValue(100);
	    
	    score.setFont(new Font("Tahoma", Font.BOLD, 15));
	    tiltleScore.setFont(new Font("Tahoma", Font.BOLD, 15));
	    tiltleTime.setFont(new Font("Tahoma", Font.BOLD, 15));

	    // set bounds for your components
	    tiltleScore.setBounds(10, 10, 200, 20);
	    score.setBounds(65, 10, 200, 20);
	    tiltleTime.setBounds(10, 30, 200, 20);
	    progressTime.setBounds(55, 34, 200, 13);
	    btnNewGame.setBounds(350, 17, 100, 30);

	    // add all components to the panel
	    
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
    public void newGame() {
		
		mainPanel.removeAll();
		mainPanel.add(createMainPanel());
		time = maxTime;
		mainPanel.validate();
		mainPanel.setVisible(true);
		btEvns = new ButtonEvents(this);
		pause = false;
		score.setText("0");  
	    Thread thread = new Thread(this); 
	    thread.start();                  

		
		
	}
    public void run() {
        while (pause == false) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        time--;
        progressTime.setValue((int) ((double) time / maxTime * 100));
        }
     }

    public void showDialogNewGame(String message, String title, int t) {
        pause=true;
        resume=false;
		int select = JOptionPane.showOptionDialog(null, message, title,
		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);
		if (select == 0) {
			newGame();
		} else {
			if(t==1){
                    System.exit(0);
                    
                }else{
                    resume=true;
                }
      }
		
   }


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnExitButton) {
            showDialogNewGame("Are you sure you want to exit?", "Exit", 0);
        }
	}
}