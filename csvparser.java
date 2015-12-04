
//Author : Ritesh Mokashi

package pack2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class csvparser {

  public static void main(String[] args) {

	  csvparser obj = new csvparser();
	obj.run();

  }

  public int[][] run() {

	String csvFile = "D:/slimemold/nodes2.csv";
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";
	int[][] conn = null;
	int row = 0;
	int size = 0;

	try {

		br = new BufferedReader(new FileReader(csvFile));
		line = br.readLine();
		String[] vals = line.trim().split(cvsSplitBy);
		size = vals.length;
		conn = new int[size-1][size-1];
		
		while ((line = br.readLine()) != null) {

		        // use comma as separator
			//String[] country = line.split(cvsSplitBy);
			vals = line.trim().split(cvsSplitBy);
			size = vals.length;
			
			for (int col = 1; col < size; col++) {
	        	   conn[row][col-1] = Integer.parseInt(vals[col]);
	           }

	           row++;
		}


	} catch (FileNotFoundException e) {
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
	
	
	/*String str = "";
    size = conn.length;

    if (conn != null) {
        for ( row = 0; row < size; row++) {
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

	System.out.println("Done");
	
	return conn;
  }

}
