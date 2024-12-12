import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Q3 {
   public static void main (String[]args){
       double[] array = {1.3, 5.6, 2.3, 9.0};
       String filename = "output.txt";
       File file = new File(filename);

       try {
           FileWriter writer = new FileWriter(filename);
           PrintWriter printWriter = new PrintWriter(writer);

           for (double value : array) {
               printWriter.println(value);
           }

           printWriter.close();
           System.out.println("Array has been written to the file successfully.");
       } catch (IOException e) {
           System.out.println("An error occurred: " + e.getMessage());
           e.printStackTrace();
       }
   }
}








