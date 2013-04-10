 import java.awt.Component;
import java.io.*;

import javax.swing.JOptionPane;

   public class TicTacGame extends ITicTacGame implements Serializable
   {
      /**
	 * Class to create the functions and graphics of the game
	 */
	private static final long serialVersionUID = 1L;
	char[] pos = {' ',' ',' ',' ',' ',' ',' ',' ',' '};
      
      public void clearAll()
      {
         for (int i = 0;i < 9;i++) 
            pos[i] = ' ';
      
      }
      public void clear(int i)
      {
         pos[i] = ' ';
      }
      public void clear(int x, int y) 
      {
         pos[x + y * 3]= ' ';
      }
      public void putX(int i) 
      {
    	  if(pos[i] == 'O' || pos[i] == 'X')
    	  {
      		Component frame = null;
  			//custom title, warning icon
      		  JOptionPane.showMessageDialog(frame,
      		      "Not a valid move. Next player's move.",
      		      null, JOptionPane.WARNING_MESSAGE);
      	  }
    	  else
    	  {
    		  pos[i] = 'X';
    	  }
         
      }
      public void putX(int x, int y) 
      {
    	  if(pos[x + y * 3] == 'O')
    	  {
      		Component frame = null;
  			//custom title, warning icon
      		  JOptionPane.showMessageDialog(frame,
      		      "Not a valid move. Next player's move.",
      		      null, JOptionPane.WARNING_MESSAGE);
      	  }
    	  else
    	  {
    		  pos[x + y * 3] = 'X';
    	  }
    	  
      }
   
      public void putO(int i) 
      {
    	  if(pos[i] == 'O' || pos[i] == 'X')
    	  {
    		Component frame = null;
			//custom title, warning icon
    		  JOptionPane.showMessageDialog(frame,
    		      "Not a valid move. Next player's move.",
    		      null, JOptionPane.WARNING_MESSAGE);
    	  }
    		  
    	  else
    	  {
    		  pos[i] = 'O';
    	  }
         
      }
      public void putO(int x, int y)
      {
    	  if(pos[x + y * 3] == 'X')
    	  {
      		Component frame = null;
  			//custom title, warning icon
      		  JOptionPane.showMessageDialog(frame,
      		      "Not a valid move. Next player's move.",
      		      null, JOptionPane.WARNING_MESSAGE);
      	  }
    	  else
    	  {
    		  pos[x + y * 3] = 'O';
    	  }
    		  
      }
      
      public char get(int i)
      {
         return pos[i];
      }
      
      public char get(int x, int y) 
      {
         return pos[x + 3 * y];
      }
      
      public int checkWin() // Checks every possibility where there can be a winner
      {
    	  int rtr = -1;
    	  
      			if(winHorizontal('X') || winHorizontal('O'))
      			{
      				rtr = 1;
      			}
      			else if(winVertical('X') || winVertical('O'))
      			{
      				rtr = 1;
      			}
      			else if(winDiagonal('X') || winDiagonal('O'))
      			{
      				rtr = 1;
      			}
      			else if(checkAllCovered())
      			{
      				rtr = 0;
      			}
      				
      		return rtr;
      }
      boolean winHorizontal(char who) 
      {
    	  boolean rtr = false;
    	  
    	if (pos[0] == who && pos[1] == who && pos[2] == who)    
      		rtr = true;
      		
      	else if (pos[3] == who && pos[4] == who && pos[5] == who)    
      		rtr = true;
      	
      	else if (pos[6] == who && pos[7] == who && pos[8] == who)    
      		rtr = true;
      	
    	return rtr;
      }
      boolean winVertical(char who) 
      {
    	  boolean rtr = false;
    	  
    	  if (pos[0] == who && pos[3] == who && pos[6] == who)    
      		rtr = true;
    	  
      	  else if (pos[1] == who && pos[4] == who && pos[7] == who)    
      		rtr = true;
      	
      	  else if (pos[2] == who && pos[5] == who && pos[8] == who)    
      		rtr = true;
      	
    	  return rtr;
      }
      boolean winDiagonal(char who)
      {
    	  boolean rtr = false;
    	  
    	  if (pos[0] == who && pos[4] == who && pos[8] == who)    
      		rtr = true;
    	  
      	  else if (pos[2] == who && pos[4] == who && pos[6] == who)    
      		rtr = true;
      	
    	  return rtr;
      }
      public boolean checkAllCovered()
      {
    	  boolean rtr = false;
    	  
    	  if (pos[0] != ' ' && pos[1] != ' ' && pos[2] != ' ' && pos[3] != ' '
                  && pos[4] != ' ' && pos[5] != ' ' && pos[6] != ' ' && pos[7] != ' ' && pos[8] != ' ') 
    	  {
    		  rtr = true;
    	  }
    	  
    	  return rtr;
      }
     
   }	