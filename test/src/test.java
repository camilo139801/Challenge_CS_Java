import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import java.util.Arrays;

public class test {
	public static void main(String[] args) {
		boolean run = true;
		int i = 0;
		while (run) {
			try {
				File myObj = new File("C:\\Users\\jbenavides\\Desktop\\Challenge_1_Computer_science\\matrizp1.txt");
				Scanner myReader = new Scanner(myObj);

				String[] vectorSize = new String[2];

				if (myReader.hasNextLine())
					vectorSize = myReader.nextLine().split(",");

				int Numfilas=Integer.valueOf(vectorSize[0]), NumColumnas= Integer.valueOf(vectorSize[1]);
            
				String[][] matrix = new String[Numfilas][NumColumnas];
				int[][] matrixInt = new int[Numfilas][NumColumnas];
				

				while (myReader.hasNextLine() && i < Numfilas) {
					String data = myReader.nextLine();
					matrix[i] = data.split(","); 
					for (int j = 0; j < Numfilas; j++) {
						matrixInt[i][j] = Integer.valueOf(matrix[i][j]);
					}
					i++;
				}

				System.out.println("Matrix:");
				
				System.out.println(Arrays.deepToString(matrix).replace("],","\n").replace(",","\t").replace("[\\[\\]]|"," ")
                        .replaceAll("[\\[\\]]", " "));

				System.out.println("*********************************************");
				System.out.println("En esta matriz los datos son de tipo: "+((Object)matrix[1][2]).getClass().getSimpleName());
				System.out.println("*********************************************");

				System.out.println("MatrixInt:");
				
				System.out.println(Arrays.deepToString(matrixInt).replace("],","\n").replace(",","\t").replace("[\\[\\]]|"," ")
                        .replaceAll("[\\[\\]]", " "));

				System.out.println("*********************************************");
				System.out.println("En esta matriz los datos son de tipo: "+((Object)matrixInt[1][2]).getClass().getSimpleName());
				System.out.println("*********************************************");
				
				myReader.close();

			} catch (FileNotFoundException e) {
				System.out.println("The file does not exist");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				run = false;
			}
		}
	}
}