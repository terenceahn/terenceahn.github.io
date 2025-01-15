public class MatrixSolver{

    public static double[][] read(){
        // Reads input from user to establish the matrix
        StdOut.println("Input matrix length: ");
        int length = StdIn.readInt();
        StdOut.println("Input matrix width: ");
        int width = StdIn.readInt();
        double[][] matrix = new double[length][width];
        for(int i = 0; i<matrix.length; i++){
            StdOut.println("Input elements for row " + i + ": ");
            for(int j = 0; j<matrix[i].length; j++){
                StdOut.println("Row " + i + ", Column " + j + ": ");
                matrix[i][j] = StdIn.readDouble();
            }
        }
        return matrix;
    }
    
    public static void makeZero(int rowNum, int colNum, int helperRow, double[][] array){
        // Checks if the lower triangle elements are zero. If they aren't, Gaussian elimination is applied.
        if(array[rowNum][colNum] == 0){
            System.out.println("Position " + rowNum + " " + colNum + " is already zero.");
            return;
        }
        else{
            double factor = array[rowNum][colNum] / array[helperRow][colNum];
            for(int i = 0; i<array[rowNum].length; i++){
                array[rowNum][i] -= (factor * array[helperRow][i]);
            }
            if(array[rowNum][colNum] == 0) System.out.println("Position " + rowNum + " " + colNum + " was made zero with changes.");
            printArray(array);
        }
    }

    public static double[] solve(double[][] triangular){
        // Solves the triangular matrix with back-substitution.
        double[] solutions = new double[triangular.length];
        for(int i = triangular.length-1; i>=0; i--){
            double c = triangular[i][triangular[i].length-1];
            for(int j = triangular[i].length-2; j>i; j--){
                c -= triangular[i][j] * solutions[j];
            }
            double solution = c / (double) (triangular[i][i]);
            solutions[i] = solution; 
        }
        return solutions;
    }

    public static double[] round(double[] solved){
        // Rounds the answers to two decimal places, since I was getting double output inaccuracies.
        for(int i = 0; i<solved.length; i++){
            solved[i] = Math.round(solved[i] * 100.0) / 100.0;
        }
        return solved;
    }
/*
    // I was thinking of solving the systems by diagonalizing, but I realized back-substitution would be easier.
    public static void makeOne(int rowNum, int colNum, int[][] array){
        if(array[rowNum][colNum] == 1){
            System.out.println("Position " + rowNum + " " + colNum + " is already one.");
            return;
        }
        else{
            for(int i = colNum; i<array[rowNum].length; i++){
                array[rowNum][i] /= (double) array[rowNum][colNum];
            }
            if(array[rowNum][colNum] == 1) System.out.println("Position " + rowNum + " " + colNum + " was made one with changes.");
            printArray(array);
        }
    }
*/  
    public static void printArray(double[][] array){
        // Prints the matrix in its current form.
        for(int i = 0; i<array.length; i++){
            for(int j = 0; j<array[i].length; j++){
                System.out.print(array[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void printArray(double[] array){
        // Prints the solutions.
        for(int i = 0; i<array.length; i++){
            if(i != array.length-1){
                System.out.print(array[i] + ", "); 
            }
            else{System.out.print(array[i]);}
        }
    }
    
    public static void main(String[] args){
        // double[][] arr = {
        //     {1, 2, 1, 2},
        //     {3, 8, 1, 12},
        //     {0, 4, 1, 2}
        // };
        // Gets the array from user input.
        double[][] arr = read();

        // Zeroing process for relevant elements.
        for(int j = 0; j<arr[0].length; j++){
            for(int i = j+1; i<arr.length; i++){
                // System.out.println(i + " " + j);
                makeZero(i, j, j, arr);
            }
        }
        
        System.out.println("The solutions are: ");
        printArray(round(solve(arr)));
    }
}