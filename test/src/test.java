import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import java.util.Arrays;

public class test {
	
	@SuppressWarnings("deprecation")
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

                


				String[][] matrix = new String[new Integer(vectorSize[0])][new Integer(vectorSize[1])];
				
				while (myReader.hasNextLine() && i < new Integer(vectorSize[0])) {
					String data = myReader.nextLine();
					matrix[i] = data.split(","); 
					i++;
				}
				
				System.out.println("Matrix:");
				
				System.out.println(Arrays.deepToString(matrix).replace("],","\n").replace(",","\t|").replace("[\\[\\]]|"," ")
                        .replaceAll("[\\[\\]]", " "));

				System.out.println("********");
				System.out.println((matrix[1][2]).getClass());
				System.out.println("********");
				
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