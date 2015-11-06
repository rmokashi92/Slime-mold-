package pack1;

import java.io.PrintWriter;

//import java.util.*;

public class Prims
{
   /* ------------------------------------------
      Data structure used to represent a graph
      ------------------------------------------ */
   private static int infinite = 9999999;

   int[][]  LinkCost;
   int[][]  Outputmat;
   int      NNodes;


   /* -------------------------------
      Construct a graph of N nodes
      ------------------------------- */
   Prims(int[][] mat)
   {
      int i, j;

      NNodes = mat.length;

      LinkCost = new int[NNodes][NNodes];
      
      Outputmat = new int[NNodes][NNodes];

      for ( i=0; i < NNodes; i++)
      {
         for ( j=0; j < NNodes; j++)
         {
            LinkCost[i][j] = mat[i][j];

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

      // Start at a vertex, I picked the start node = 0

      Reached[0] = true;

      // Other vertices are not reached 

      for ( k = 1; k < NNodes; k++ )
      {
         Reached[k] = false;
      }

      predNode[0] = 0;      // No edge for node 0

      printReachSet( Reached );

      /* =====================================================
         UnReachSet will decreas by 1 node in each iteration
	 There are NNodes-1 unreached nodes; so we can loop
	 NNodes-1 times and UnReachSet will become empty !
	 =====================================================  */
      for (k = 1; k < NNodes; k++)
      {
         /* ================================================================
	    Find min. cost link between: reached node ---> unreached node
            ================================================================ */
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
         
         Outputmat[x][y] = LinkCost[x][y];
         Outputmat[y][x] = LinkCost[x][y];

         /* =================================
	    Add e (x,y) to Spanning tree
            ================================= */
         predNode[y] = x;          // Record the min cost link

	 /* ==========================================
	    ReachSet = ReachSet union {y}
	    UnReachSet = UnReachSet - {y}
	    ========================================== */
         Reached[y] = true;

         printReachSet( Reached );     // Print state....
         System.out.println();
      }

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
		   PrintWriter pw =new PrintWriter("D:\\output.txt");
		   for(int i=0; i<NNodes; i++){
			   for(int j=0; j<NNodes; j++){
//             System.out.print(matrix[i][j]+" ");
              
             pw.print(Outputmat[i][j]); 
                                     
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

}

