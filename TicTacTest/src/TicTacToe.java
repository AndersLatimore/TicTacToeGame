import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;  

public class TicTacToe 
{
	/**
	 * Class that contains the main function which creates the window for the game
	 * and implements the server/client socket
	 */
	
    @SuppressWarnings("deprecation")
	public static void main(String args[]) 
    {
    	int i = -1; // Variable to keep track of the result from the game
        
    	do // Do-while loop
    	{
    		
        try 
        {	 
           JFrame frame = getFrame(); // Create the frame 
           
           // Dialog to get the name from the player as string, put the string in a label
           String sname = returnNameString();
           JLabel label = new JLabel(sname);
           
           // Create the status bar panel and shove it down the bottom of the frame
           JPanel statusPanel = new JPanel();
           statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
           statusPanel.setBackground(Color.red);
           frame.add(statusPanel, BorderLayout.SOUTH);
           
           // Create a label, placed at the bottom of the frame
           JLabel statusLabel = new JLabel();
           statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
           statusPanel.add(statusLabel);
           Button clearButton = new Button("Clear"); // Creating a "clear" button
           clearButton.setSize(new Dimension(10, 20));
           statusPanel.add(label);
           statusPanel.add(clearButton); // Adding the button to the panel
           
           statusPanel.add(getTime()); // Adding the clock to the status panel

           frame.setVisible(true);
           
        	char ch;
        	if (args.length == 0)
        		ch = 'O';
        	else 
        		ch = 'X';
        	
           TicTacPanel ticTacPanel = new TicTacPanel(ch); // Create a panel for the game
           TicTacAction ticTacAction = new TicTacAction(ticTacPanel); // Handle actions in the game
           
           clearButton.addActionListener(ticTacAction);
           ticTacPanel.addMouseListener(ticTacAction);
           frame.add(ticTacPanel);
           frame.show();
           
           // Creating socket and I/O stream objects
           Socket s;
           ObjectOutputStream oops;
           ObjectInputStream oips;
          
           switch (ch) 
           {
              case 'O':
                 s = (new ServerSocket(7777)).accept();
                 oops = new ObjectOutputStream(s.getOutputStream());
                 oops.writeObject(ticTacPanel.ttt);
                 ticTacAction.ready = false;
                 break;
              case 'X':
              default:
                 s = new Socket(args[0],7777);
                 ticTacAction.ready = true;
           }
           while(true) // Infinite loop
           {
              oips = new ObjectInputStream(s.getInputStream());
              ticTacPanel.ttt = (TicTacGame)(oips.readObject());
              ticTacPanel.paint(ticTacPanel.getGraphics());
              ticTacAction.ready = true;
              while (ticTacAction.ready) 
              {
                 Thread.sleep(100);
              }
              oops = new ObjectOutputStream(s.getOutputStream());
              oops.writeObject(ticTacPanel.ttt);
              
              i = ticTacPanel.ttt.checkWin(); // Check if there's a winner
              if(i == 1) // A winner is declared
             	{
             		TicTacPanel.infoBox("The winner is " + sname + "!", "Game Over");
             		ticTacPanel.ttt.clearAll();
             		ticTacPanel.paint(ticTacPanel.getGraphics());
             	}
              else if( i == 0) // No winner, but every square is covered so the game is done
              {
            	  	TicTacPanel.infoBox("We have a tie!", "Game Over");
            	  	ticTacPanel.ttt.clearAll();
            	  	ticTacPanel.paint(ticTacPanel.getGraphics());
              }
           }
        }
           catch (Exception e) 
           { 
              System.out.println(e);
              e.printStackTrace();
              System.exit(1);
           }
    	}
    	while(i == -1);	// End of do-while loop
     }
	public class MenuItemListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) 
		{
			if (event.getActionCommand().equals("Exit"))
			{
				System.exit(0);
			}
			
		}	
	}
	public static JLabel getTime()
	{
		 //Here are some things to deal with the clock in the status panel
        final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");  
        final JLabel timeLabel = new JLabel(sdf.format(new java.util.Date()));  
          
        ActionListener al = new ActionListener()
        {  
          public void actionPerformed(ActionEvent ae)
          {  
            timeLabel.setText(sdf.format(new java.util.Date()));  
          }  
        };  
        new javax.swing.Timer(1000,al).start();
		return timeLabel;
	}
	public static String returnNameString()
	{    
	    Frame frame = new Frame("Enter name");
	    Object[] enterName = null;
	    String sname = (String)JOptionPane.showInputDialog(
	            frame,
	            "Enter your name:\n",
	            "Enter name",
	            JOptionPane.PLAIN_MESSAGE,
	            null, enterName, "name");
		return sname;
	}
	public static JFrame getFrame()
	{
		// Create the frame and control what happens when the window is closed
        JFrame frame = new JFrame("Tic Tac Toe 1.0"); // Create the frame for the game
        frame.setLocation(500, 200);
        frame.setSize(550,520);
        frame.addWindowListener(
                              new WindowAdapter() 
                              {
                                 public void windowClosing(WindowEvent we) 
                                 {
                                 	TicTacPanel.infoBox("The connection was terminated", "Connection failure");
                                    System.exit(-1);
                                 }
                              } ) ;
		return frame;
	}
  }
