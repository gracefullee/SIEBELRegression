package CreateOpportunity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GUI extends JPanel{
	static private final String newline = "\n";
    protected JTextField username, password;
    String UN, PW;
    JLabel un, pw, link;
    JButton login;
    GridBagConstraints gbc;
    JFileChooser fc;
    protected File file;
    static JFrame frame;
    boolean header = false;
 
    public GUI(JFrame frame) {
        super(new GridBagLayout());
        this.frame = frame;
        setBackground(Color.WHITE);
 
        this.setBorder(new EmptyBorder(10,10,10,10));
        
        gbc = new GridBagConstraints();
        
        //Create the log first, because the action listeners
        //need to refer to it.
 
        //Create a file chooser
        fc = new JFileChooser();
        
        un = new JLabel("Username: ");
        un.setBorder(new EmptyBorder(5,0,5,0));
        pw = new JLabel("Password: ");
        pw.setBorder(new EmptyBorder(5,0,10,0));
        gbc.gridx=0;
        gbc.gridy=0;
        add(un, gbc);
        gbc.gridy=1;
        add(pw, gbc);
        
        username = new JTextField(20);
        password = new JTextField(20);

        
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth = 2;
        add(username, gbc);
        gbc.gridy=1;
        add(password, gbc);

        ImageIcon icon = new ImageIcon("login.png");
        Image img = icon.getImage();  
        Image newimg = img.getScaledInstance( 80, 35,  java.awt.Image.SCALE_SMOOTH ) ;
        
        login = new JButton(new ImageIcon(newimg));
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        login.setFocusPainted(false);
        login.setOpaque(false);
        login.setBorder(new EmptyBorder(0,0,0,0));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx=0;
        gbc.gridy=2;
        

  
        login.addActionListener(new ActionListener()
        {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(username.getText().equals("") || password.getText().equals(""))
				{
					JOptionPane.showMessageDialog(GUI.this, "Please fill out both username and password.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else{
					UN = username.getText();
					PW = password.getText();
					FileFilter filter = new FileNameExtensionFilter("Excel Spreadsheet", "xls");
					fc.setFileFilter(filter);
					boolean fileSelected = false;
					while(!fileSelected){
						int result = fc.showOpenDialog(GUI.this);
						if(result == JFileChooser.APPROVE_OPTION)
						{
							fileSelected = true;
							file = fc.getSelectedFile();
							int option = JOptionPane.showConfirmDialog(null, 
									   "Does your spreadsheet have a header?",null, JOptionPane.YES_NO_OPTION);
							if(option == JOptionPane.YES_OPTION) {
							    header = true;
							}
							frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
							
						}
					}
					
				}
			}
        	
        });
 
        //Add the buttons and the log to this panel.
        add(login, gbc);
        
        link = new JLabel("Click Here for Sample Spreadsheet!");
        link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        link.addMouseListener(new MouseAdapter() 
           {
            public void mouseClicked(MouseEvent e) 
            {
                if (e.getClickCount() > 0) 
                {
                    if (Desktop.isDesktopSupported()) 
                    {
                          Desktop desktop = Desktop.getDesktop();
                          try 
                          {
                              URI uri = new URI("https://docs.google.com/spreadsheets/d/1MYWS4sIrrGKuJPzRLtRwh3BbG1t1Ph3Rguu_a7jWaIs/edit?usp=sharing");
                              desktop.browse(uri);
                          } 
                          catch (IOException ex) 
                          {
                              ex.printStackTrace();
                          } 
                          catch (URISyntaxException ex) 
                          {
                              ex.printStackTrace();
                          }
                    }
                }
            }
          });
        link.setForeground(Color.BLUE);
        
        link.setBorder(new EmptyBorder(8,0,3,0));
        gbc.gridx=0;
        gbc.gridy=3;
        add(link, gbc);

    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */


    
}
