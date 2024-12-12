import java.io.File;
import java.io.IOException;
import java.util.Scanner;



public class Q1{
    public static void main(String[]args){
        File file = new File("C:\\Users\\USER\\IdeaProjects\\Tut9\\Exersice1.txt");
        try{
            Scanner file_reader = new Scanner(file);

            while (file_reader.hasNextLine()) {
                String text = file_reader.nextLine();
                System.out.println(text);
            }
        } catch (IOException ex) {
            System.out.println("ERROR: File could not be opened.");
        }
    }
}




