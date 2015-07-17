package CreateOpportunity;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	protected static GUI gui;
    
    public Window()
    {
    	super("SIEBEL Regression Testing");
        //Add content to the window.
        gui = new GUI(this);
        add(gui);
        setLocation(500,400);
        //Display the window.
        pack();
        setVisible(true);
    }
    
}
