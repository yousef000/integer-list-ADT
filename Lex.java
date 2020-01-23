//-----------------------------------------------------------------------------------------
// Lex.java
// Yousef Dost
// ydost@ ucsc.edu
// PA1
// A test client for the List ADT. It will take two command line arguments giving the names of an input file and an output file. The input can be any text file. The output file will contain the same lines as the input arranged in lexicographic (i.e. alphabetical) order. //-----------------------------------------------------------------------------------------
 
import java.io.*;
import java.util.Scanner;
public class Lex{
    public static void main(String[] args) throws IOException{
        
        Scanner in = null;
        PrintWriter out = null;
        int size = 0;                          //size for Array A
        
        
        if(args.length < 2){
            System.err.println("Usage: FileIO infile outfile");
            System.exit(1);
        }
        
        in = new Scanner(new File(args[0]));
        out = new PrintWriter(new FileWriter(args[1]));
        
        while( in.hasNextLine() ){
            size++;
            in.nextLine();
        }
        in.close();
        
        String A[] = new String[size];
        in = new Scanner(new File(args[0]));
        
        for(int i = 0; i < size; i++){
            A[i] = in.nextLine();
        }
      
        in.close();
    
    
        List myList = new List();
        for(int i = 0; i < size; i++){
            if(myList.isEmpty()){
                myList.append(i);
                myList.moveFront();

            }
            else{
                if(A[i].compareTo(A[myList.get()]) < 0){
                    myList.insertBefore(i);
                    myList.moveFront();
                }
                else{
                    for(myList.moveFront(); myList.index()>=0; myList.moveNext()){
                        if(A[i].compareTo(A[myList.get()]) < 0){
                            myList.insertBefore(i);
                            break;
                        }
                    }
                    if(myList.index() == -1){ //if cursor out of right bound means i > everything
                        myList.append(i);
                    }
                    myList.moveFront();
                }
            }
        }
        for(myList.moveFront(); myList.index()>=0; myList.moveNext()){
            out.println(A[myList.get()]);
        }
        out.close();
        
    }
    

}
