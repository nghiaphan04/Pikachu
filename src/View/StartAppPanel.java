package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StartAppPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	MainFrame mainFrame;
	private JButton btnNewGameStart;
	private JButton btnHistory ;
	private JButton btnOk ;
	private String namePlayer;
	
	
	public StartAppPanel(MainFrame mainFrame) {
	    this.mainFrame = mainFrame;
	    setLayout(null);
	    setBounds(0, 0, 600, 660);

	    JLabel labelBg = new JLabel();
	    labelBg.setBounds(0, 0, 600, 660);
	    labelBg.setIcon(mainFrame.resizeImg("/View/icon/BgImage.png"));
	    add(labelBg);

	    btnHistory = new JButton("History");
    	btnHistory.setBounds(198, 326, 200, 38);
    	btnHistory.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	btnHistory.setFocusPainted(false);
    	btnHistory.setBackground(Color.red);
	    add(btnHistory);

        btnNewGameStart = new JButton("New Game");
        btnNewGameStart.setBounds(198, 265, 200, 38);
        btnNewGameStart.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNewGameStart.setFocusPainted(false);
        btnNewGameStart.setBackground(Color.red);
        btnNewGameStart.addActionListener(this);
        add(btnNewGameStart);
        
        labelBg.setOpaque(true); 
        add(labelBg, Integer.valueOf(Integer.MIN_VALUE));
	}
	public JDialog createInputDialog() {
	    JDialog dialog = new JDialog(mainFrame, "Enter Your Name", true);
	    dialog.setSize(300, 150);
	    dialog.setLayout(null);
	    dialog.setLocationRelativeTo(mainFrame); 

	    JTextField nameInput = new JTextField(10);
	    nameInput.setBounds(50, 20, 200, 30); 
	    dialog.add(nameInput);

	    btnOk = new JButton("OK");
	    btnOk.setBounds(90, 70, 120, 30);
	    btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    btnOk.setFocusPainted(false);
	    
	    btnOk.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            namePlayer = nameInput.getText();
	            dialog.dispose();
	            System.out.println(namePlayer);
	        }
	    });
	    dialog.add(btnOk);
	    return dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if(e.getSource()==btnNewGameStart) {
	        JDialog nameDialog = createInputDialog();
	        nameDialog.setVisible(true); 
	        mainFrame.newGame();
	        
	    }
	}
}
