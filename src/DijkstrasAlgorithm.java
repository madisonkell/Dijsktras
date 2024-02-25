import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DijkstrasAlgorithm {
    public static void main(String[] args) throws FileNotFoundException {
        //select file
        File file = new File("src/DijkstrasInput.txt");
        //read file
        Scanner sc = new Scanner(file);
        //array list
        ArrayList<String[]> rows = new ArrayList<>();
        while(sc.hasNextLine()){
            //split row by spaces
            String[] numbers = sc.nextLine().split(" ");
            //add row to array list
            rows.add(numbers);
        }

        //determine size of matrix
        int[][] matrix = new int[rows.size()][rows.size()];

        //add the numbers to the matrix
        for (int i = 0; i < matrix.length; i++){
            //select row from array list
            String[] row = rows.get(i);
            //add numbers to the matrix
            for(int j = 0; j < matrix.length; j++){
                matrix[i][j] = Integer.parseInt(row[j]);
            }
        }
        //call function
        dijk(matrix);
    }

    public static void dijk(int matrix[][]){
        //find size of matrix
        int size = matrix.length;
        //to keep track of used vertices
        boolean[] used = new boolean[size];
        //to store weights
        int[] key = new int[size];
        //looked up how to find the largest value in java
        int min = Integer.MAX_VALUE;
        //setting index to -1 because 0 is a valid weight
        int index = -1;


        //looping through the matrix
        for(int i = 0; i < size; i++){
            //set all used vertices to false because none of them have been visited yet
            used[i] = false;
            //set first key value to 0 so we start there
            key[i] = min;
            //key of vertex to get to itself is 0
            key[0] = 0;
        }


        //looping the rows
        for(int y = 0; y < size; y++) {
            //looping through the columns
            for (int x = 0; x < size; x++) {
                //if the vertex has not been visited and the weight is less than current min
                if (!used[x] && key[x] < min) {
                    //set the min to the current weight
                    min = key[x];
                    //start the index to where it was found
                    index = x;
                }
            }

            //sets the vertex that was visited to true
            used[index] = true;
            //reset min to the max value
            min = Integer.MAX_VALUE;

            //find the min distance
            for (int i = 0; i < size; i++) {
                //if the vertex has a path to another vertex
                if (matrix[index][i] != 0) {
                    if (key[i] > key[index] + matrix[index][i] && !used[i]) {
                        //adding the weights to the lowest path
                        key[i] = key[index] + matrix[index][i];
                    }
                }
            }
        }

            //the output is printed
            char alphabet[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
            for (int i = 1; i < matrix.length; i++){
                System.out.println("Source -> Node" + alphabet[i-1] + ": " + key[i]);
            }
    }
}
