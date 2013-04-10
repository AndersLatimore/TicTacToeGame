 import java.awt.event.*;
 
   public class TicTacAction implements ActionListener, MouseListener
   {
	   /**
		 * Class to handle events in the game
		 */
	   
      TicTacPanel ttp;
      boolean ready;
      
      public TicTacAction(TicTacPanel p) 
      {
         ttp = p;
         ready = ttp.c == 'X';
      }
      
      public void actionPerformed(ActionEvent e)
      {
         if (ready) 
         {
            ttp.ttt.clearAll();
            ready = false;
         }
         ttp.paint(ttp.getGraphics());
      }
      
      public void mouseClicked(MouseEvent e)
      {
         if (ready) 
         {
            int x = e.getX();
            int y = e.getY();
            int w = ttp.getWidth();
            int h = ttp.getHeight();
            x = x / (w / 3);
            y = y / (h / 3);
            if (x >= 0 && x < 3 && y >= 0 && y < 3) 
            {
               switch (ttp.c) 
               {
                  case 'X': ttp.ttt.putX(x, y); 
                     break;
                  case 'O': ttp.ttt.putO(x, y);
               }
            }
            ready = false;
         }
         ttp.paint(ttp.getGraphics());
      }

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
      
   }