package pack1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//import java.util.*;

public class Prims extends JFrame
{
   /* ------------------------------------------
      Data structure used to represent a graph
      ------------------------------------------ */
   private static int infinite = 9999999;

   int[][]  LinkCost;
   int[][]  Outputmat;
   int      NNodes;
   ArrayList<Nodes> nodes;
   int[][] coord = null;
   static int[][] conn = null;
   int totalcost = 0;

   /*public Prims()
   {
	   super("Lines Drawing Demo");
	  // NNodes = x;
       setSize(480, 200);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
	   
   }*/
   /* -------------------------------
      Construct a graph of N nodes
      ------------------------------- */
   Prims()
   {
      
	   super("Lines Drawing Demo");
		  // NNodes = x;
	       setSize(1500, 1500);
	       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       setLocationRelativeTo(null);

	   int i, j;

      NNodes = conn.length;

      LinkCost = new int[NNodes][NNodes];
      
      Outputmat = new int[NNodes][NNodes];

      for ( i=0; i < NNodes; i++)
      {
         for ( j=0; j < NNodes; j++)
         {
            LinkCost[i][j] = conn[i][j];

            if ( LinkCost[i][j] == 0 )
               LinkCost[i][j] = infinite;
         }
      }
      
      for ( i=0; i < NNodes; i++)
      {
         for ( j=0; j < NNodes; j++)
         {
            Outputmat[i][j] = 0;
         }
      }


      for ( i=0; i < NNodes; i++)
      {
         for ( j=0; j < NNodes; j++)
            if ( LinkCost[i][j] < infinite )
               System.out.print( " " + LinkCost[i][j] + " " );
            else
               System.out.print(" 0 " );

         System.out.println();
      }
   }


   public int unReached(boolean[] r)
   {
      //boolean done = true;

      for ( int i = 0; i < r.length; i++ )
         if ( r[i] == false )
            return i;

      return -1;
   }


   public void Prim( )
   {
      int i, j, k, x, y;

      boolean[] Reached = new boolean[NNodes];	// Reach/unreach nodes
      int[] predNode = new int[NNodes];		// Remember min cost edge

      
      int start_node = 0;
      Reached[start_node] = true;

       

      for ( k = 0; k < NNodes; k++ )
      {
    	  if(k == start_node)
    	  {
    		  Reached[k] = true;
    	  }
    	  else
    	  {
    		  Reached[k] = false;
    	  }
      }

      predNode[start_node] = start_node;      

      
      printReachSet( Reached );

      
      for (k = 1; k < NNodes; k++)
      {
                  x = y = 0;

         for ( i = 0; i < NNodes; i++ )
            for ( j = 0; j < NNodes; j++ )
            {
                if ( Reached[i] && !Reached[j] &&
                     LinkCost[i][j] < LinkCost[x][y] )
                {
		   x = i;
		   y = j;
                }
            }

         System.out.println("Min cost edge: (" + 
				+ x + "," + 
				+ y + ")" +
				"cost = " + LinkCost[x][y]);
         
         totalcost = totalcost + LinkCost[x][y];
         System.out.println("total" +totalcost);
         
         Outputmat[x][y] = LinkCost[x][y];
         Outputmat[y][x] = LinkCost[x][y];

         
         predNode[y] = x;          // Record the min cost link

	 
         Reached[y] = true;

         printReachSet( Reached );     // Print state....
         System.out.println();
      }

      initializeMatrix();
      printMinCostEdges( predNode );
      printOutput();
   }

   void printMinCostEdges( int[] a )
   {
      for ( int i = 0; i < NNodes; i++ )
          System.out.println( a[i] + " --> " + i );
   }
   
   void printOutput()
   {
	   
	   //Printing for checking
	   for (int i=0; i < NNodes; i++)
	      {
	         for (int j=0; j < NNodes; j++)
	         {
	        	 System.out.print(Outputmat[i][j]+" ");
	         }
	         System.out.println("");
	      }
	   try
	   {
		   PrintWriter pw =new PrintWriter("D:/slimemold/output.txt");
		   for(int i=0; i<NNodes; i++){
			   for(int j=0; j<NNodes; j++){
//             System.out.print(matrix[i][j]+" ");
              
             pw.print(Outputmat[i][j]);
             pw.append(" ");
                                     
         }
         pw.append('\n');
     }
     pw.flush();
	   }
	   catch(Exception io)
	   {
		  io.printStackTrace();
	   }
	      
	   
   }

   void printReachSet(boolean[] Reached )
   {
      System.out.print("ReachSet = ");
      for (int i = 0; i < Reached.length; i++ )
         if ( Reached[i] )
           System.out.print( i + " ");
      System.out.println();
   }
   
   void readCoordinares()
   {
	   String csvFile = "D:/slimemold/coordinates.csv";
      	BufferedReader br = null;
  		String line = "";
  		String cvsSplitBy = ",";
  		int size;
  		
  		
  		try {

  			br = new BufferedReader(new FileReader(csvFile));
  			line = br.readLine();
  			String[] vals = line.trim().split(cvsSplitBy);
  			size = vals.length;
  			coord = new int[NNodes][size];
  			int row = 0;
  			
  			do {

  			        // use comma as separator
  				//String[] country = line.split(cvsSplitBy);
  				vals = line.trim().split(cvsSplitBy);
  				size = vals.length;
  			
  					for (int col = 0; col < size; col++) {
  		        	   coord[row][col] = Integer.parseInt(vals[col]);
  		           }
  					row++;
  		           
  				
  			}while ((line = br.readLine()) != null);
  		}	

  		 catch (FileNotFoundException e) {
  			e.printStackTrace();
  		} catch (IOException e) {
  			e.printStackTrace();
  		} finally {
  			if (br != null) {
  				try {
  					br.close();
  				} catch (IOException e) {
  					e.printStackTrace();
  				}
  			}
  		}

	   
   }
   
   
   void initializeMatrix()
   {
       

       // Create the nodes
       	nodes= new ArrayList<Nodes>();
       	readCoordinares();   		

       
       for (int m = 0; m < Outputmat.length; m++)
       {
           Nodes node= new Nodes();
           node.name = Integer.toHexString(m);
           node.x = coord[m][0];
           node.y = coord[m][1];
           System.out.println( node.x + " " + node.y);
           nodes.add(node);
       }
   }
   
   
   public void paint(Graphics g) {
       super.paint(g);
       drawedges(g);
   }
   
   protected void drawedges(Graphics gr)
   {
       Graphics2D g = (Graphics2D) gr;

       for (int l = 0; l < NNodes; l++){

           // Compute the x- and y-coordinates that the
           // node will have in this component. (That's 
           // why the coordinates that are stored in 
           // the "Node" class should always be 
           // between 0 and 1!)

           Nodes node = nodes.get(l);
           int ix = (int)(node.x * 4);
           int iy = (int)(node.y * 4);

           g.fillOval(ix, iy, 7, 7);
           g.drawString(node.name.toString(), ix, iy + 20);
       }

       //Create a nested for loop to see if there is an edge between vertices.
       for (int m = 0; m < NNodes; m++){
           for (int n = 0; n < NNodes; n++){
               if (Outputmat[m][n] != 0){

                   Nodes nodeM = nodes.get(m);                    
                   Nodes nodeN = nodes.get(n);
                   int xm = (int)(nodeM.x * 4);
                   int ym = (int)(nodeM.y * 4);
                   int xn = (int)(nodeN.x * 4);
                   int yn = (int)(nodeN.y * 4);
                   g.draw(new Line2D.Double(xm , ym , xn , yn)); 
               }
           }
       }
    }
   
   
   
   public static void main(String[] args)
   {
 
	   
	   csvparser parse = new csvparser();
	   conn = parse.run();
	   
	   
	   try
	   {   
		   BufferedReader buffer = new BufferedReader(new FileReader("D:/slimemold/file.txt"));   

       String line;
       int row = 0;
       int size = 0;

       while ((line = buffer.readLine()) != null) {
           String[] vals = line.trim().split("\\s+");

           // Lazy instantiation.
           if (conn == null) {
               size = vals.length;
               conn = new int[size][size];
           }

           for (int col = 0; col < size; col++) {
        	   conn[row][col] = Integer.parseInt(vals[col]);
           }

           row++;
       }
	   }
	   catch(Exception io)
	   {
		   io.printStackTrace();
	   }

	   
	   //Printing for checking
	   //
	   
	   /*String str = "";
       int size = conn.length;

       if (conn != null) {
           for (int row = 0; row < size; row++) {
               str += " ";
               for (int col = 0; col < size; col++) {
                   str += String.format("%2d",  conn[row][col]);
                   if (col < size - 1) {
                       str += " | ";
                   }
               }
               if (row < size - 1) {
                   str += "\n";
                   for (int col = 0; col < size; col++) {
                       for (int i = 0; i < 4; i++) {
                           str += "-";
                       }
                       if (col < size - 1) {
                           str += "+";
                       }
                   }
                   str += "\n";
               } else {
                   str += "\n";
               }
           }
       }

       System.out.println(str);
	   */
	   
	   

            
      
      SwingUtilities.invokeLater(new Runnable() {
          @Override
          public void run() {
        	  Prims G = new Prims();

              G.Prim();

        	  G.setVisible(true);
          }
      });
  }
      
      //new DrawGraph();

   }

   



