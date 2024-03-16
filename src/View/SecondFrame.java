package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Font;

public class SecondFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panel;
    private JButton btnHngDn;
    private JButton btnLchS;
    private JLabel lblNewLabel;
    private JPanel panel_2;
    

    
   
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public SecondFrame() {
        setEnabled(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 611, 617);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        panel = new JPanel();
        panel.setBounds(0, 0, 597, 580);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(10, 117, 462, 452);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_3 = new JLabel(" Time");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_3.setBounds(512, 173, 49, 14);
        panel.add(lblNewLabel_3);
        
        panel_2 = new JPanel();
        panel_2.setBounds(527, 197, 17, 372);
        panel.add(panel_2);
        
        JLabel lblNewLabel_2 = new JLabel("130");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2.setBounds(538, 117, 49, 29);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_1 = new JLabel("Score:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(498, 117, 46, 29);
        panel.add(lblNewLabel_1);
       
    
        
        
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(0, 0, 597, 747);
        lblNewLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/View/icon/BgImage (5).png")));
        panel.add(lblNewLabel);
        
        
       
        
    }
}