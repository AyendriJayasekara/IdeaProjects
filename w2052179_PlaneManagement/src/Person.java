public class Person {   // creating a class called Person

    //Declare variables to store values
    private String name;
    private String surname;
    private String email;

    public Person(String name,String surname,String email) {    // creating a constructor
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    
    //creating getters and setters
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
    public String getSurname(){
        return surname;
    }
    
    public void setSurname(String surname){
        this.surname= surname;
    }

    public String getEmail(){
        return email;

    }
    
    public void setEmail(String email){
        this.email= email;

    }

    //Method to print person's Informations
    public  void person_detail(){
        System.out.println("First Name: " + this.name);
        System.out.println("Surame: " + this.surname);
        System.out.println("Email Address: " + this.email);
    }


    //Method to return person's Informations
    public String getperson_details(){
        return ("Person's Information: "+ "\n" + "FullName: " + this.name + " " +
        this.surname+ "\n" +"Email Address: " + this.email+"\n");

    }

}
