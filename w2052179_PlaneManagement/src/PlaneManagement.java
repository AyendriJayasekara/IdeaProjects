import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

//Creating a class called PlaneManagement
public class PlaneManagement {
    public static int[][] seats;  //  Declare 2D array to represent seats on plane
    public static Ticket[][] tickets; //  Declare 2D array to store tickets

    public static void main(String[] args) {

        // call the method deletetickets()
        deleteTicketsDir();
        System.out.println("Welcome to the Plane Management application");
        //call the method initializeseats()
        initializeseats();

        //Initialize ticket array with different dimensions.
        tickets = new Ticket[4][];
        tickets[0] = new Ticket[14];
        tickets[1] = new Ticket[12];
        tickets[2] = new Ticket[12];
        tickets[3] = new Ticket[14];

        Scanner scanner = new Scanner(System.in);  // create Scanner object to read inputs from user
        boolean validoption = false;
        while (!validoption) {

            //print menu option
            System.out.println("*".repeat(49));
            System.out.println("*                MENU  OPTIONS                  *");
            System.out.println("*".repeat(49));
            System.out.println("      1) Buy a seat ");
            System.out.println("      2) Cancel a seat ");
            System.out.println("      3) Find first available seat ");
            System.out.println("      4) Show seating plan ");
            System.out.println("      5) Print tickets information and total sales ");
            System.out.println("      6) Search tickets ");
            System.out.println("      0) Quit ");
            System.out.println("*************************************************");
            System.out.print("Please select an option: " );

            try{
                int option = scanner.nextInt();
                //switch to different cases based on user's input
                switch (option) {
                    case 0:
                        System.out.println("You're exiting the program");
                        validoption = true;
                        break;
                    case 1:
                        buy_seat(scanner);
                        break;
                    case 2:
                        cancel_seat(scanner);
                        break;
                    case 3:
                        find_first_available();
                        break;
                    case 4:
                        show_seating_plan();
                        break;
                    case 5:
                        print_tickets_info();
                        break;
                    case 6:
                        search_tickets(scanner);
                        break;
                    default:
                        System.out.println("Invalid option.please enter a valid option(0-6).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input. please enter a valid input(0-6).");
                scanner.nextLine();
            }
        }
    }

    //Method to Delete existing ticket directory
    public static void deleteTicketsDir(){
        File file = new File("Ticket");

        if(!file.exists()) return;

        try {
            String[] subFiles = file.list();
            for (String fileName : subFiles) {
                File subFile = new File(file.getPath(), fileName);
                subFile.delete();
            }
        }catch (NullPointerException e){
            System.out.println();
        }
    }

    //Method to initialize seats array with different dimensions
    public static void initializeseats() {
        seats = new int[4][];
        seats[0] = new int[14];
        seats[1] = new int[12];
        seats[2] = new int[12];
        seats[3] = new int[14];

        //set as all seats are available at start
        for (int[] row : seats) {
            for (int j = 0; j < row.length; j++) {
                row[j] = 0;
            }
        }

    }

    //Method to buy seats
    public static void buy_seat(Scanner scanner) {
        //Get rowindex and seatnumber from user input
        int rowindex = inputrowletter(scanner);
        int seatnumber = inputseatnumber(scanner, rowindex);
        int seatindex =seatnumber -1;

        //checks the availability of seats
        if (seats[rowindex][seatindex] == 0) {

            System.out.print("\n"+ "Enter your details: " + "\n");

            //getting information for ticket purchase
            System.out.print("Enter your Name: ");
            String name = scanner.next();
            while (!name.matches("[A-Za-z]+")) {     //Validate name if enter invalid input
                System.out.print("Invalid name.Enter your name again: ");
                name= scanner.next();

            }
            System.out.print("Enter your Surname: ");
            String surname = scanner.next();
            while (!surname.matches("[A-Za-z]+")) {  //Validate surname if enter invalid input
                System.out.print("Invalid surname.Enter your surname again: ");
                surname= scanner.next();

            }

            //validate email
            String email;
            while(true) {
                System.out.print("Enter your Email: ");
                email = scanner.next();
                if (!(email.contains("@"))) {
                    System.out.print("Invalid email enter an valid email.");
                    continue;
                }
                if (!(email.contains("."))) {
                    System.out.print("Invalid email enter an valid email.");
                    continue;
                }
                break;
            }

            Person person1 = new Person(name, surname, email);//create Person object

            int price = calculatePrice(rowindex, seatindex); //call the calculatePrice method

            Ticket ticket = new Ticket((char)(rowindex+ 'A'), seatnumber, price, person1);  //create Ticket object
            ticket.save();

            tickets[rowindex][seatindex] = ticket; //Add ticket to the ticket array

            // update seat array and confirmation messages
            seats[rowindex][seatindex] = 1;
            System.out.println("seat " + (char)(rowindex+ 'A') + seatnumber + " reserved successfully.");

        } else {
            System.out.println("Sorry.This seat is already taken.Please select an another seat.");
        }
    }

    //Method to calculate ticket price
    public static int calculatePrice(int rowindex,int seatindex) {
        if (seatindex >= 0 && seatindex < 5) {
            return 200;
        } else if (seatindex >= 5 && seatindex < 9) {
            return 150;
        } else {
            return 180;
        }
    }

    //Method to cancel seats
    public static void cancel_seat(Scanner scanner) {
        //Get rowindex and seatnumber from user input
        int rowindex = inputrowletter(scanner);
        int seatnumber = inputseatnumber(scanner, rowindex);
        int seatindex = seatnumber - 1;

        //check if seat is already reserved
        if (seats[rowindex][seatindex] == 1) {

            //Delete ticket information
            tickets[rowindex][seatindex].delete();
            tickets[rowindex][seatindex] = null;

            //update ticket array
            seats[rowindex][seatindex] = 0;

                System.out.println("seat " + (char)(rowindex+ 'A') + seatnumber + " has been successfully cancelled.");
                System.out.println("seat " + (char)(rowindex+ 'A') + seatnumber + " available now.");

        } else {
            System.out.println("Seat " + (char)(rowindex+ 'A') + seatnumber + " is not reserved yet. Please try again.");
        }
    }

    //Method to input rowindex
    public static int inputrowletter(Scanner scanner) {
        char rowletter = '0';  //initialize with the default value
        int rowindex =0;       //initialize with the default value
        boolean validrow = false;
        while (!validrow) {
            System.out.print("Enter the row letter(A-D): ");
            //Input rowletter
            rowletter = scanner.next().toUpperCase().charAt(0);
            //convert rowletter to index
            rowindex = rowletter - 'A';

            //checks the validity of rowindex
            if (rowindex >= 0 && rowindex < seats.length) {
                validrow = true;
            } else {
                System.out.println("Invalid Input. Please enter a letter (A-D)");
            }
        }
        return rowindex;
    }

    //Method to input seatnumber
    public static int inputseatnumber(Scanner scanner, int rowindex) {
        int seatnumber = 0;  //initialize with the default value
        boolean validseat = false;
        while (!validseat) {
            System.out.print("Enter the Seat Number(1-14 for A & D, 1-12 for B & C): ");
            try {
                //Input seatnumber
                seatnumber = scanner.nextInt();
                //convert seatnumber to seatindex
                int seatindex = seatnumber - 1;

                //checks the validity of seatnumber
                if (seatindex >= 0 && seatindex < seats[rowindex].length) {
                    validseat = true;
                } else {
                    System.out.println("Invalid seat number .please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input.Please try again.");
                scanner.nextLine();
            }
        }
        return seatnumber;
    }

    //Method to find the first available seat
    public static void find_first_available() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    char rowletter = (char) ('A' + i);
                    System.out.println("First available seat: " + rowletter + (j + 1));
                    return; // return after finding the first available seat
                }
            }
        }
    }
    // Method to show seating plan
    public static void show_seating_plan() {
        
        System.out.println("SEATING PLAN\n seats available are represent by '0'\n seats already booked are represent by 'X' ");
        for (int i = 0; i < seats.length; i++) {
            System.out.println(" ");
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    System.out.print("0 ");  //print 'O' for available seats
                } else {
                    System.out.print("X ");  //print 'X' for reserved seats
                }
            }
        }
        System.out.println();
    }

    //Method to print ticket information
    private static void print_tickets_info() {
        double totalPrice = 0;  //Initialize totalprice with default value

        //Iterate through ticket array to print ticket details
        for (int i = 0; i < tickets.length; i++) {
            for (int j = 0; j < tickets[i].length; j++) {
                Ticket ticket = tickets[i][j];
                if (tickets[i][j] != null) {
                    ticket.Ticket_details();

                    //calculate total price of tickets sold
                    totalPrice += tickets[i][j].getPrice();
                }
            }
        }
        //Print total price of tickets sold
        System.out.println("Total price of Tickets sold: £" + totalPrice);
    }

    //Method for search tickets
    public static void search_tickets(Scanner scanner) {

        //Get rowindex and seatnumber from user input
        int rowindex = inputrowletter(scanner);
        int seatnumber = inputseatnumber(scanner, rowindex);
        int seatindex = seatnumber - 1;

        //check if seat is reserved
        if (tickets[rowindex][seatindex] != null) {
            System.out.println("This seat is not available.");
            tickets[rowindex][seatindex].Ticket_details(); //Print person's details

        }else{
            System.out.println("This seat is available.");
        }
    }
}
































































