import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Graph {
	int vertices,arg[],y;
    ArrayList<Integer>[] adjList;

	@SuppressWarnings("unchecked")//unnecessary warnings are suppressed

	//Here the graph is created
    public Graph(int vertices){
        this.vertices=vertices;
        adjList = new ArrayList[vertices];
        for(int i=0; i<vertices;i++){
           adjList[i] = new ArrayList<>();
        }
    }
   
	//here the edges of the graph are created
    void addEdge(int Nodemum,int Nodemum2, int Matrixnum, int Matrixnum2){
        adjList[Nodemum].add(Nodemum2);
    }


	//Here the nodes(matrixInt) that are interconnected 
	//by the edges with their corresponding 
	//indicator(matrixNodos) are printed
	void displayGraph(int matrix[][],int NF, int NC){
        int y=0;
		int arg[] = new int[NF*NC],p=0;

        this.arg=arg;
		this.y=y;

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

	//In this seccion are printed all the existing paths in the Graph 

	public void printAllPaths(int s, int d){
        boolean[] isVisited = new boolean[vertices];
        ArrayList<Integer> pathList = new ArrayList<>();
        pathList.add(s);
        printAllPathsUtil(s, d, isVisited, pathList);

    }
 
    public void printAllPathsUtil(Integer u, Integer d, boolean[] isVisited, List<Integer> localPathList){
        if (u.equals(d)) {
			System.out.println(localPathList);
			// Here is printed the number of the path
			System.out.println("El numero de nodos que posee el camino es de: "+(localPathList.size()));
			if(localPathList.size()>1){
				y=y+1;
				// Here is printed the count of the number of the Graph paths  
				System.out.println("La cantidad de caminos total hasta el momento es:"+y);
			}
			return;
        }

        isVisited[u] = true;
 
		for (int j=0;j<adjList[u].size();j++) {
            Integer i = adjList[u].get(j);

            if (isVisited[i]==false) {
                localPathList.add(i);
                printAllPathsUtil(i, d, isVisited, localPathList);
			
                localPathList.remove(i);
            };
		}
			
        isVisited[u] = false;

    }
    
	public static void main(String[] args) {
		boolean run = true;
		int i = 0, j=0, h=1;
        Graph g = new Graph(100000000);

		while (run) {
			// Here is done the extraction of the dates from de txt
			try {
				File myObj = new File("C:\\Users\\jbenavides\\Desktop\\Challenge_1_Computer_science\\matrizp1.txt");
				Scanner myReader = new Scanner(myObj);

				// We use this vector for getting the dommensions of the map
				String[] vectorSize = new String[2];

				if (myReader.hasNextLine())
					vectorSize = myReader.nextLine().split(" ");

				int Numfilas=Integer.valueOf(vectorSize[0]), NumColumnas= Integer.valueOf(vectorSize[1]);

				String[][] matrix = new String[Numfilas][NumColumnas];
				int[][] matrixInt = new int[Numfilas][NumColumnas];
				int[][] matrixNodos = new int[Numfilas][NumColumnas];// indicator array
				
				// We use this vector for getting the dommensions of the map

				/*
				 These two concatenated cycles are used to 
				 save the txt data in an array of type string, 
				 where the separation of the data was done 
				 through the split function, after this the 
				 conversion of these data from string to int is 
				 carried out to have the data stored in an array 
				 of integer type and finally the indicator array 
				 is created which is used to be able to differentiate 
				 the nodes that have equal integer values.
				 */
				while (myReader.hasNextLine() && i < Numfilas) {
					String data = myReader.nextLine();
					matrix[i] = data.split(" "); 
					for (j = 0; j < NumColumnas; j++) {
						matrixInt[i][j] = Integer.valueOf(matrix[i][j]);
						matrixNodos[i][j]=h;
						h++;
					}
					i++;
				}
				myReader.close();
                  
				
                //The three matrix are pinted in this section
                
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
                
                /*
				 These two concatenated cycles are used 
				 to create the edges of the graph where 
				 it is taken into account that a negative 
				 value cannot be connected, that the limits 
				 of the matrix are not exceeded and that
				  finally these edges can only be made 
				  vertically and horizontal.
				 */

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


                /* 
				 int s = 3;

                // arbitrary destination
                int d = 8;
 
                System.out.println("Following are all different paths from "+ s + " to " + d);
                g.printAllPaths(s, d);
				*/
				
				System.out.println();
                /*
				 these three concatenated loops are used to print all existing paths within the graph
				 */
				for(h=1; h<(NumColumnas*Numfilas+1);h++){
					for(i=0; i<Numfilas;i++){
						for(j=0; j<NumColumnas;j++){
							System.out.println("Following are all different paths from "+ h + " to " + matrixNodos[i][j]);
							g.printAllPaths(h, matrixNodos[i][j]);
						}
					}
				}

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
