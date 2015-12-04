//Author : Ritesh Mokashi

package pack1;


/*import java.io.BufferedReader;
import java.io.FileReader;*/

public class Test
{

   public static void main(String[] args)
   {

//                        
// ===================================================
    /*  int[][] conn = {{0,3,0,2,0,0,0,0,4},  // 0         
                      {3,0,0,0,0,0,0,4,0},  // 1
                      {0,0,0,6,0,1,0,2,0},  // 2
                      {2,0,6,0,1,0,0,0,0},  // 3
                      {0,0,0,1,0,0,0,0,8},  // 4
                      {0,0,1,0,0,0,8,0,0},  // 5
                      {0,0,0,0,0,8,0,0,0},  // 6
                      {0,4,2,0,0,0,0,0,0},  // 7
                      {4,0,0,0,8,0,0,0,0}   // 8
                     };*/
	   
	   int[][] conn = null;
	   
	   csvparser parse = new csvparser();
	   conn = parse.run();
	   
	   
	   /*try
	   {   
		   BufferedReader buffer = new BufferedReader(new FileReader("D:\\file.txt"));   

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
*/	   
	   
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
	   
	   

      Prims G = new Prims(conn);

      G.Prim();

   }
}
