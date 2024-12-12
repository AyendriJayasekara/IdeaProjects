import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Creating a class called Ticket
public class Ticket {

    //Declare variables to store values
    private char rowletter;
    private int seatnumber;
    private int price;
    private Person person1;

    //create a constructor to initialize the ticket object
    public Ticket(char rowletter, int seatnumber, int price, Person person1) {
        this.rowletter = rowletter;
        this.seatnumber = seatnumber;
        this.price = price;
        this.person1 = person1;
    }

    //creating getters and setters
    public char getRowletter() {

        return rowletter;
    }
    
    public void setRowletter(char rowletter) {

        this.rowletter = rowletter;
    }

    public int getSeatnumber() {
        return seatnumber;

    }
    public void setSeatnumber(int seatnumber) {

        this.seatnumber = seatnumber;
    }

    public int getPrice() {

        return price;
    }
    public void setPrice(int price) {
        this.price = price;
        
    }
    public Person getPerson() {
        return person1;
        
    }
    public void setPerson(Person person1) {
        this.person1 = person1;
        
    }

    // method to return ticket information.
    public String getTicket_details(){
        return  getPerson().getperson_details()  +
                "Ticket Information:\n"+ "Seat Number: " + this.rowletter + this.seatnumber+"\n" + "Price of Ticket: £"+this.price + "\n";
    }

    // method to print ticket information.
    public void Ticket_details() {

        System.out.println (getTicket_details());
    }



    //Method to save ticket information to file
    public void save() {
        String filename = this.rowletter + String.valueOf(this.seatnumber) + ".txt"; //creating a file name

        File directory = new File("Ticket", filename);   //creating a file object with directory path & filename
        File file = directory.getParentFile();

        //check if the directory exists or not
        if (!file.exists()) file.mkdir();

        try {
            //wrirting ticket details to file
            FileWriter writer = new FileWriter(directory);
            writer.write(getTicket_details());
            writer.close();
            System.out.println("Ticket information successfully saved to file " + filename);
        }catch (IOException ex){
            System.out.println("An error occurred while creating a file.");
        }

    }

    //Method to delete the ticket file
    public void delete(){
        String filename = this.rowletter + String.valueOf(this.seatnumber) + ".txt";

        File file = new File("Ticket",filename);

        if (file.exists()){
            file.delete();
        }else {
            System.out.println("File not found");
        }


    }
}
