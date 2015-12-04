package pack1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

//import java.util.*;

public class Prims extends JFrame
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
   int start_node = 17; 
   int new_nuclei = 0;
   int[][] final_output = null;
   int [][]edge_connect = null;
   int [][]bridges = null;
   boolean []visited = null;
   int []min = null;
   int []conn1 = null;
   int[] mindex = null;
   int []articulate = null;
   int done = 0;
   
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
      
	   super("Slime-Mold Network");
		  // NNodes = x;
	       setSize(1500, 1500);
	       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       setLocationRelativeTo(null);

	   int i, j;

      NNodes = conn.length;

      
      
      Outputmat = new int[NNodes][NNodes];

     /* for ( i=0; i < NNodes; i++)
      {
         for ( j=0; j < NNodes; j++)
         {
            LinkCost[i][j] = conn[i][j];

            if ( LinkCost[i][j] == 0 )
               LinkCost[i][j] = infinite;
         }
      }*/
      
      for ( i=0; i < NNodes; i++)
      {
         for ( j=0; j < NNodes; j++)
         {
            Outputmat[i][j] = 0;
         }
      }


      /*for ( i=0; i < NNodes; i++)
      {
         for ( j=0; j < NNodes; j++)
            if ( LinkCost[i][j] < infinite )
               System.out.print( " " + LinkCost[i][j] + " " );
            else
               System.out.print(" 0 " );

         System.out.println();
      }*/
   }


   
   public int findMax()
   {
	   int max = 0;
	   for (int m = 0; m < NNodes; m++){
           for (int n = 0; n < NNodes; n++){
               if (conn[m][n] > max){
            	   max = conn[m][n];
            	  }
           }
       }
	   return max;

   }
   
   
   
   public int unReached(boolean[] r)
   {
      //boolean done = true;

      for ( int i = 0; i < r.length; i++ )
         if ( r[i] == false )
            return i;

      return -1;
   }


   public void Prim(int start)
   {
      int i, j, k, x, y;

      boolean[] Reached = new boolean[NNodes];	// Reach/unreach nodes
      int[] predNode = new int[NNodes];		// Remember min cost edge

      
      
      Reached[start] = true;

       

      for ( k = 0; k < NNodes; k++ )
      {
    	  if(k == start)
    	  {
    		  Reached[k] = true;
    	  }
    	  else
    	  {
    		  Reached[k] = false;
    	  }
      }

      predNode[start] = start;      

      
      //printReachSet( Reached );

      
      for (k = 0; k < NNodes; k++)
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

         /*System.out.println("Min cost edge: (" + 
				+ x + "," + 
				+ y + ")" +
				"cost = " + LinkCost[x][y]);*/
         
         totalcost = totalcost + LinkCost[x][y];
         //System.out.println("total" +totalcost);
         
         Outputmat[x][y] = LinkCost[x][y];
         Outputmat[y][x] = LinkCost[x][y];
         
         if(final_output[x][y] == 0)
         {
        	 final_output[x][y] = LinkCost[x][y];
             final_output[y][x] = LinkCost[x][y]; 
         }
         
         predNode[y] = x;          // Record the min cost link

	 
         Reached[y] = true;

         //printReachSet( Reached );     // Print state....
         //System.out.println();
      }

      final_output[0][0] = 0;
      
      
      //printMinCostEdges( predNode );
      //printOutput();
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

   /*void printReachSet(boolean[] Reached )
   {
      System.out.print("ReachSet = ");
      for (int i = 0; i < Reached.length; i++ )
         if ( Reached[i] )
           System.out.print( i + " ");
      System.out.println();
   }*/
   
   
   void slime_mold()
   {
	   int max;
	   int phase1,phase2,phase3,phase4,phase5,phase6,phase7,phase8,phase9,phase10;
	   int phase11,phase12,phase13,phase14,phase15,phase16,phase17,phase18,phase19,phase20;
	   final_output = new int[NNodes][NNodes];
	   edge_connect = new int[NNodes][NNodes];
	   bridges = new int[NNodes][NNodes];
	   visited = new boolean[NNodes];
	   start_node=findmid();
	   int total_cost = 0;
	   int no_phases = 20;
	   
	   max = findMax();
	   phase1 = max/20;
	   phase2 = 2*max/20;
	   phase3 = 3*max/20;
	   phase4 = 4*max/20;
	   phase5 = 5*max/20;
	   phase6 = 6*max/20;
	   phase7 = 7*max/20;
	   phase8 = 8*max/20;
	   phase9 = 9*max/20;
	   phase10 = 10*max/20;
	   phase11 = 11*max/20;
	   phase12 = 12*max/20;
	   phase13 = 13*max/20;
	   phase14 = 14*max/20;
	   phase15 = 15*max/20;
	   phase16 = 16*max/20;
	   phase17 = 17*max/20;
	   phase18 = 18*max/20;
	   phase19 = 19*max/20;
	   phase20 = max;
	   
	   
	   
	   createMatrix(phase1);
	   start_node = new_nuclei;
	   createMatrix(phase2);
	   start_node = new_nuclei;
	   createMatrix(phase3);
	   start_node = new_nuclei;
	   createMatrix(phase4);
	   start_node = new_nuclei;
	   createMatrix(phase5);
	   start_node = new_nuclei;
	   createMatrix(phase6);
	   start_node = new_nuclei;
	   createMatrix(phase7);
	   start_node = new_nuclei;
	   createMatrix(phase8);
	   start_node = new_nuclei;
	   createMatrix(phase9);
	   start_node = new_nuclei;
	   createMatrix(phase10);
	   start_node = new_nuclei;
	   createMatrix(phase11);
	   start_node = new_nuclei;
	   createMatrix(phase12);
	   start_node = new_nuclei;
	   createMatrix(phase13);
	   start_node = new_nuclei;
	   createMatrix(phase14);
	   start_node = new_nuclei;
	   createMatrix(phase15);
	   start_node = new_nuclei;
	   createMatrix(phase16);
	   start_node = new_nuclei;
	   createMatrix(phase17);
	   start_node = new_nuclei;
	   createMatrix(phase18);
	   start_node = new_nuclei;
	   createMatrix(phase19);
	   start_node = new_nuclei;
	   createMatrix(phase20);
	   
	   
	   for(int i = 0; i < NNodes; i++)
	   {
		   for(int j = 0; j < NNodes; j++)
		   {
			   edge_connect[i][j] = final_output[i][j];
		   }
	   }
	   
	   
	   initializeMatrix();
	   if(done == no_phases)
	   {
	   findBridges();
	   //ensure_connect();
	   articulations();
	   }

	   for(int i = 0; i < NNodes; i++)
	   {
		   for(int j = 0; j < NNodes; j++)
		   {
			   total_cost = total_cost + final_output[i][j];
		   }
	   }
	   System.out.println("total_cost ->" + total_cost/2 );
	   
	   int count=0;
	   for(int i = 0; i < NNodes; i++)
	   {
		   for(int j = 0; j < NNodes; j++)
		   {
			   if(final_output[i][j] != 0)
			   {
				   count++;
			   }
		   }
		 
	   }
	   System.out.println("count->" + count/2);
	   
   }
   
   private void articulations() {
	   
	   int [][]opcopy = new int[NNodes][NNodes];
	   int flag = 0;
	   articulate = new int[NNodes];
	   
	   for(int i = 0; i < NNodes; i++)
	   {
		   visited[i] = false;
	   }
	   
	   for(int m = 0; m < NNodes; m++)
	   {
		   for(int n = 0; n< NNodes; n++)
		   {
			   opcopy[m][n] = final_output[m][n];
		   }
	   }
	   
	   for(int m = 0; m < NNodes; m++)
	   {
		   for(int n = 0; n< NNodes; n++)
		   {
			   edge_connect[m][n] = opcopy[m][n];
		   }
	   }
	   
		   
		   
		   for(int i = 0; i < NNodes; i++)
		   {
			   for(int j = 0; j< NNodes; j++)
			   {
				   edge_connect[i][j] = 0;
			   }
				   if(i != (NNodes - 1))
				   {
					   dfs(i+1);
				   }
				   else
				   {
					   dfs(i-1);
				   }
				   
				   for(int k = 0; k < NNodes; k++)
				   {
					   if(visited[k] != true && k != i )
					   {
						   flag = 1;
					   }
				   }
				   
				   if(flag == 1)
				   {
					   //articulate[i] = 1;
					   beatarticulation(i);
				   }
				   
				   
				   
				   flag = 0;
				   for(int m = 0; m < NNodes; m++)
				   {
					   visited[m] = false;
				   }
				   
				   for(int j = 0; j< NNodes; j++)
				   {
					   edge_connect[i][j] = final_output[i][j];
				   }   
				   
				   
			   }
		   }

public void beatbridges(int i, int j)
{
	int length1 = 0, length2 = 0, k = 0;
	int min = infinite;
	int min1= 0,min2 = 0;
	
	for(int r = 0; r < NNodes; r++)
	{
		if(visited[r] == false && r != i)
		{
			length1++;
		}
		if(visited[r] == true && r != j)
		{
			length2++;
		}
		
	}
	
	int []visit = new int[length2];
	int []novisit = new int[length1];
	
	for(int m = 0; m < NNodes; m++)
	{

		if(visited[m] == false && m != i)
		{
			novisit[k] = m;
			k++;
		}
	}
	k = 0;
	
	for(int m = 0; m < NNodes; m++)
	{

		if(visited[m] == true && m != j)
		{
			visit[k] = m;
			k++;
		}
	}
	
	for(int m = 0; m < length2; m++)
	{
		for(int n = 0; n < length1; n++)
		{
			if(conn[(visit[m])][(novisit[n])] < min)
			{
				min = conn[(visit[m])][(novisit[n])];
				min1 = visit[m];
				min2 = novisit[n];
			}
		}

}
	
	final_output[min1][min2] = min;
	final_output[min2][min1] = min;
	
}


private void beatarticulation(int i) {
	int length1 = 0, length2 = 0, k = 0;
	int min = infinite;
	int min1= 0,min2 = 0;
	
	for(int j = 0; j < NNodes; j++)
	{
		if(visited[j] == false && j != i)
		{
			length1++;
		}
		if(visited[j] == true && j != i)
		{
			length2++;
		}
		
	}
	
	int []visit = new int[length2];
	int []novisit = new int[length1];
	
	for(int j = 0; j < NNodes; j++)
	{

		if(visited[j] == false && j != i)
		{
			novisit[k] = j;
			k++;
		}
	}
	k = 0;
	
	for(int j = 0; j < NNodes; j++)
	{

		if(visited[j] == true && j != i)
		{
			visit[k] = j;
			k++;
		}
	}
	
	for(int m = 0; m < length2; m++)
	{
		for(int n = 0; n < length1; n++)
		{
			if(conn[(visit[m])][(novisit[n])] < min)
			{
				min = conn[(visit[m])][(novisit[n])];
				min1 = visit[m];
				min2 = novisit[n];
			}
		}
	}
	
	final_output[min1][min2] = min;
	final_output[min2][min1] = min;
	
	
}



private void findBridges() {
	   int val;
	   int flag = 0;
	   boolean bexists;
	for(int i =0 ; i< NNodes ; i++)
	{
		for(int j = 0; j < NNodes; j++)
		{
			if(edge_connect[i][j] != 0)
			{
				val = edge_connect[i][j];
				edge_connect[i][j] = 0;
				edge_connect[j][i] = 0;
				dfs(start_node);
				for( int k = 0; k< NNodes; k++)
			      {
			    	  if(visited[k] == false)
			    	  {
			    		  beatbridges(i,j); 
			    		  flag = 1;
			    	  }
			      }
			      
				for( int k = 0; k< NNodes; k++)
			      {
						visited[k] = false;
			      }
				
				
			      if(flag == 1)
			      {
			    	  bexists = true;
			    	  flag = 0;
			      }
			      /*else
			    	  {
			    	  bexists = false;
			    	  }
				edge_connect[i][j] = val;
				edge_connect[j][i] = val;
				if(bexists)
				{
					bridges[i][j] = 1;
					bridges[j][i] = 1;
				}*/
			}
		}
	}
}



private void dfs(int i){
	      int j;
          

	      visited[i] = true;  // Mark node as "visited"

	     // printNode(i);

	      for ( j = 0; j < NNodes; j++ )
	      {
	         if ( edge_connect[i][j] > 0 && !visited[j] )       
	         {
	            dfs(j);       // Visit node
	         }
	      }
	      
	      
	   }




private void ensure_connect() {
	   int min = infinite;
	   int min_index = 0;
	   int []connect = new int[NNodes];
	   for (int m = 0; m < NNodes; m++){
           for (int n = 0; n < NNodes; n++){
        	   	if(bridges[m][n] == 1)
        	   	{
        	   		connect[m] = 1;
        	   	}
           }
	   }
	   
	   
	   for (int m = 0; m < NNodes; m++){
           for (int n = 0; n < NNodes; n++){
        	   if(conn[m][n] < min && conn[m][n] != 0 && final_output[m][n] == 0)
        	   {
        		   min = conn[m][n];
        		   min_index = n;
        	   }
        	   
        	   
           }
           if(connect[m] == 1)
           {
        	   final_output[m][min_index] = min;
        	   final_output[min_index][m] = min;
           }
           min = infinite;
           min_index = 0;
	   }
}



private int findmid() {
	 
	   int sum = 0,avg,min=infinite,min_index = 0;
	   int []dist = new int[NNodes];
 	   for (int m = 0; m < NNodes; m++){
           for (int n = 0; n < NNodes; n++){
            	   sum = sum + conn[m][n];
            	  }
           avg = sum /NNodes;
           dist[m] = avg;
           sum = 0;
           }
 	   
 	   		for(int m = 0; m < NNodes; m++)
 	   		{
 	   			if(dist[m] < min)
 	   			{
 	   				min = dist[m];
 	   				min_index = m;
 	   			}
 	   		}
 	   		
 	   		return min_index;
       }
   
   	   
	




void createMatrix(int phase)
   {
	
		done++;
	   conn1 = new int[NNodes];
	   LinkCost = new int[NNodes][NNodes];
	   int flag = 0;
	   min = new int[NNodes];
	   int minimum = 0;
	   /*
	   int []conn2 = new int[NNodes];
	   int []conn3 = new int[NNodes];
	   int []conn4 = new int[NNodes];
	   int []conn5 = new int[NNodes];*/
	   
	    minimum = infinite;
	   for (int m = 0; m < NNodes; m++){
		   if(conn[start_node][m] < phase && conn[start_node][m] != 0)
		   {
			   	conn1[m] = conn[start_node][m];
			   	flag = 1;
		   }
		   if(conn[start_node][m] < minimum && conn[start_node][m] != 0)
		   {
			   minimum = conn[start_node][m];
			   new_nuclei = m;
		   }
		   /*if(conn[start_node][m] < min && conn[start_node][m] != 0)
		   {
			   min = conn[start_node][m];
			   flag = 1;
		   }*/
	   }
	  
	   
	   for (int i=0; i < NNodes; i++)
	      {
	         for (int j=0; j < NNodes; j++)
	         {
	            if(conn1[i] != 0 && conn1[j] != 0)
	            {
	            	LinkCost[i][j] = conn[i][j];

	            		if ( LinkCost[i][j] == 0 )
	            			LinkCost[i][j] = infinite;
	            }
	            else
	            {
	            	LinkCost[i][j] = infinite;
	            }
	         }
	      }
	   
	   /*for ( int i=0; i < NNodes; i++)
	      {
	         for (int j=0; j < NNodes; j++)
	            if ( LinkCost[i][j] < infinite )
	               System.out.print( " " + LinkCost[i][j] + " " );
	            else
	               System.out.print(" 0 " );

	         System.out.println();
	      }*/
	   
	   /*for (int m = 0; m < NNodes; m++)
		   {
			   	//conn1[m] = conn[start_node][m]*10 + m;
		   conn1[m] = conn1[m]*10 + m;	
		   }*/
	   
	   if(flag == 1)
	   {
		   //Arrays.sort(conn1);
		   sort();
		   
		   
		   /*int min_index = 0;
		   for (int m = 0; m < NNodes; m++)
		   {
		   		if(conn1[m] != 0)
		   		{
		   			min[min_index] = conn1[m]%10;
		   			min_index++;
		   		}
		   }
	   */
	   
		   for (int m = 0; m < NNodes; m++)
		   {
			   if(mindex[m] != 0)
		   		{
		   	    	Prim(mindex[m]);
		   		}
		   }
		   
	   }
	   else
	   {
		   new_nuclei = start_node;
	   }
	  
	   
   }
   
   
   private void sort() {
	   int j = 0;
	   int length = 0;
	   int temp1,temp2;
	   mindex = new int[NNodes];
	   for(int i = 0; i < NNodes; i++)
	   {
		   if(conn1[i] != 0)
		   {
			  min[j] = conn1[i];
			  mindex[j] = i;
			  j++;
			  
		   }
	   }
	   
	   for(int i = 0; i < NNodes; i++)
	   {
		   if(min[i] != 0)
		   {
			   length++;
		   }
	   }
	   
	   for(int i = 0; i < length ; i++)
	   {
		   for( j = i ; j > 0 ; j--){
               if(min[j] < min[j-1]){
                   temp1 = min[j];
                   min[j] = min[j-1];
                   min[j-1] = temp1;
                   
                   temp2 = mindex[j];
                   mindex[j] = mindex[j-1];
                   mindex[j-1] = temp2;

               }
		

		   }	   
	   
	   }
	   
   }



void readCoordinares()
   {
	   String csvFile = "D:/slimemold/coordinates2.csv";
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
           node.name = Integer.toString(m);
           node.x = coord[m][0];
           node.y = coord[m][1];
           //System.out.println( node.x + " " + node.y);
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
           int ix = (int)(node.x * 3);
           int iy = (int)(node.y * 3);

           
        	   Stroke stroke = new BasicStroke(2f);
        	   g.setStroke(stroke);
        	   g.drawOval(ix - 10, iy - 10, 20, 20);
        	   g.setColor(Color.DARK_GRAY);
           
           /*else
           {
        	   g.setColor(Color.BLACK);
        	   g.fillOval(ix, iy, 7, 7);
           }*/
           g.drawString(node.name, ix, iy + 20);
       }

       //System.out.println("Track edges");
       //Create a nested for loop to see if there is an edge between vertices.
       for (int m = 0; m < NNodes; m++){
           for (int n = 0; n < NNodes; n++){
               if (final_output[m][n] != 0){
            	   //System.out.println(m + "->" + n);

                   Nodes nodeM = nodes.get(m);                    
                   Nodes nodeN = nodes.get(n);
                   int xm = (int)(nodeM.x * 3);
                   int ym = (int)(nodeM.y * 3);
                   int xn = (int)(nodeN.x * 3);
                   int yn = (int)(nodeN.y * 3);
                   Stroke stroke = new BasicStroke(4f);
                   g.setStroke(stroke);
                   g.draw(new Line2D.Double(xm , ym , xn , yn));
                   g.setColor(Color.BLACK);
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

              //G.Prim();
              G.slime_mold();

        	  G.setVisible(true);
          }
      });
  }
      
      //new DrawGraph();

   }

   



