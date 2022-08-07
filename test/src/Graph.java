import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Graph {
	int vertices,arg[];
    ArrayList<Integer>[] adjList;

	@SuppressWarnings("unchecked")

    public Graph(int vertices){
        this.vertices=vertices;
        adjList = new ArrayList[vertices];
        for(int i=0; i<vertices;i++){
           adjList[i] = new ArrayList<>();
        }
    }
   
    void addEdge(int Nodemum,int Nodemum2, int Matrixnum, int Matrixnum2){
	
        adjList[Nodemum].add(Nodemum2);
    }

	void displayGraph(int matrix[][],int NF, int NC){
        
		int arg[] = new int[NF*NC],p=0;

        this.arg=arg;

		for(int i=0; i<NF;i++){
            for(int j=0; j<NC;j++){
				arg[p]= matrix[i][j];
				p++;   
            }
         }
         
		 
		for(int i=0; i<vertices;i++){
            if(adjList[i].size()>0){
                System.out.println("vertex "+arg[i-1]+"("+i+")"+" is conected to: -");
                for(int j=0; j<adjList[i].size();j++){
                    System.out.print(arg[adjList[i].get(j)-1]+"("+adjList[i].get(j)+")"+" ");
                 }
                System.out.println();
            }
         }
         
    }

	public void printAllPaths(int s, int d){
        boolean[] isVisited = new boolean[vertices];
        ArrayList<Integer> pathList = new ArrayList<>();
        // add source to path[]
        pathList.add(s);
 
        // Call recursive utility
        printAllPathsUtil(s, d, isVisited, pathList);
    }
 
    private void printAllPathsUtil(Integer u, Integer d, boolean[] isVisited, List<Integer> localPathList){
        if (u.equals(d)) {
            System.out.println(localPathList);
			System.out.println("El numero de caminos es: "+(localPathList.size()-1));
        }

        isVisited[u] = true;
 
		for (int j=0;j<adjList[u].size();j++) {
            Integer i = adjList[u].get(j);

            if (isVisited[i]==false) {
                localPathList.add(i);
                printAllPathsUtil(i, d, isVisited, localPathList);
			
                localPathList.remove(i);
            }
			
        }
        

        isVisited[u] = false;

		
    }

	public static void main(String[] args) {
		boolean run = true;
		int i = 0, j=0, h=1;
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
				int[][] matrixNodos = new int[Numfilas][NumColumnas];
				

				while (myReader.hasNextLine() && i < Numfilas) {
					String data = myReader.nextLine();
					matrix[i] = data.split(","); 
					for (j = 0; j < NumColumnas; j++) {
						matrixInt[i][j] = Integer.valueOf(matrix[i][j]);
						matrixNodos[i][j]=h;
						h++;
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

				System.out.println("MatrixNodos:");
				
				System.out.println(Arrays.deepToString(matrixNodos).replace("],","\n").replace(",","\t").replace("[\\[\\]]|"," ")
                        .replaceAll("[\\[\\]]", " "));
			    
			
                i=0; 
				j=0;
                


				while (i<Numfilas) {
					while (j<NumColumnas) {
					    if(matrixInt[i][j]>0){
							if(j+1<NumColumnas){
								
								if(matrixInt[i][j]<matrixInt[i][j+1]){
								g.addEdge(matrixNodos[i][j],matrixNodos[i][j+1],matrixInt[i][j],matrixInt[i][j+1]);
								}
							}
			
							if(j-1>-1){
								if(matrixInt[i][j]<matrixInt[i][j-1]){
								g.addEdge(matrixNodos[i][j],matrixNodos[i][j-1],matrixInt[i][j],matrixInt[i][j-1]);
								}
							}
			
							if(i+1<NumColumnas){
								if(matrixInt[i][j]<matrixInt[i+1][j]){
								g.addEdge(matrixNodos[i][j],matrixNodos[i+1][j],matrixInt[i][j],matrixInt[i+1][j]);
								}
							}  
			
							if(i-1>-1){
								if(matrixInt[i][j]<matrixInt[i-1][j]){
								g.addEdge(matrixNodos[i][j],matrixNodos[i-1][j],matrixInt[i][j],matrixInt[i-1][j]);
								}
							}
						}
						
						j++;
					}
						System.out.println();
						j=0;
						i++;
				}
			
				g.displayGraph(matrixInt,Numfilas,NumColumnas);

				int s = 19;
 
                // arbitrary destination
                int d = 2;
 
                System.out.println("Following are all different paths from "+ s + " to " + d);
                g.printAllPaths(s, d);
				

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
