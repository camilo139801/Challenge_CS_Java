import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
	int vertices;
    LinkedList<Integer> adjList[];
    
    public Graph(int vertices){
        this.vertices=vertices;
        adjList = new LinkedList[vertices];
        for(int i=0; i<vertices;i++){
           adjList[i] = new LinkedList<>();
        }
    }
   
    void addEdge(int source,int destination){
        adjList[source].add(destination);
    }

    void displayGraph(){
        for(int i=0; i<vertices;i++){
            if(adjList[i].size()>0){
                System.out.println("vertex "+i+" is conected to: -");
                for(int j=0; j<adjList[i].size();j++){
                    System.out.print(adjList[i].get(j)+" ");
                 }
                System.out.println();
            }
         }
    }
	public static void main(String[] args) {
		boolean run = true;
		int i = 0, j=0;
        Graph g = new Graph(100000);

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
					for (j = 0; j < NumColumnas; j++) {
						matrixInt[i][j] = Integer.valueOf(matrix[i][j]);
					}
					i++;
				}
				myReader.close();

				
                //Imprimir matriz e idetinficar que tipo de datos esta contenido dento de cada matriz

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
			
			   

                i=0; 
				j=0;
				while (i<Numfilas) {
					while (j<NumColumnas) {
						System.out.print(" "+matrixInt[i][j]);
					
						if(j+1<3 && matrixInt[i][j]>0){
							if(matrixInt[i][j]<matrixInt[i][j+1]){
							g.addEdge(matrixInt[i][j],matrixInt[i][j+1]);
							}
						}
		
						if(j-1>-1 && matrixInt[i][j]>0){
							if(matrixInt[i][j]<matrixInt[i][j-1]){
							g.addEdge(matrixInt[i][j],matrixInt[i][j-1]);
							}
						}
		
						if(i+1<3 && matrixInt[i][j]>0){
							if(matrixInt[i][j]<matrixInt[i+1][j]){
							g.addEdge(matrixInt[i][j],matrixInt[i+1][j]);
							}
						}  
		
						if(i-1>-1 && matrixInt[i][j]>0){
							if(matrixInt[i][j]<matrixInt[i-1][j]){
							g.addEdge(matrixInt[i][j],matrixInt[i-1][j]);
							}
						}
						j++;
					}
						System.out.println();
						j=0;
						i++;
				}
					System.out.println();
					g.displayGraph();
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
