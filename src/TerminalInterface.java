import Airport.*;
import Airline.*;
import Client.Person;
import Client.User;
import Client.UserInterface;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class TerminalInterface {


    public static Airport buildSystem() {

        Scanner in = new Scanner(System.in);

        System.out.print("Name of airport : ");
        String nameOfAirport = in.nextLine();
        while( nameOfAirport ==null || nameOfAirport.equals("") ) {
            System.out.print("Name of airport : ");
            nameOfAirport = in.nextLine();
        }
        System.out.println();

        System.out.print("Admin Name : ");
        String nameOfAdmin = in.nextLine();
        while( nameOfAdmin ==null || nameOfAdmin.equals("") ) {
            System.out.print("Admin Name : ");
            nameOfAdmin = in.nextLine();
        }

        System.out.print("Admin Surname : ");
        String surnameOfAdmin = in.nextLine();
        while( surnameOfAdmin ==null || surnameOfAdmin.equals("") ) {
            System.out.print("Admin Surname : ");
            surnameOfAdmin = in.nextLine();
        }

        System.out.print("SSN of Admin : ");
        String SSNofAdmin = in.nextLine();
        while ( SSNofAdmin ==null || SSNofAdmin.equals("") ) {
            System.out.print("SSN Surname : ");
            SSNofAdmin = in.nextLine();
        }

        System.out.print("Password of Admin : ");
        String passwordOfAdmin = in.nextLine();
        while( passwordOfAdmin ==null || passwordOfAdmin.equals("") ) {
            System.out.print("Password of Admin : ");
            passwordOfAdmin = in.next();
        }

        System.out.println();
        try {
            User portAdmin = new User(nameOfAdmin,surnameOfAdmin,SSNofAdmin,passwordOfAdmin);
            Airport airport = new Airport( nameOfAirport , portAdmin);
            System.out.println("System has been initialized.");
            System.out.println();
            return airport;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static void main(String[] args) {

        try{

            Scanner in = new Scanner(System.in);
            Airport airport = buildSystem();

            boolean loginPageLoop = true;
            while(loginPageLoop){

                System.out.println("1- Login ");
                System.out.println("2- Enroll the system.");
                System.out.println("3- View Airport-Map");
                System.out.println("4- Exit");

                int loginPageChoice = in.nextInt();

                switch ( loginPageChoice ){

                    case 1:
                        System.out.print("Enter SSN:\t");
                        String logSSN = in.nextLine();
                        User logUser = airport.getAirportSystemStorage().getUserWithSSN(logSSN);
                        if(logUser!=null){
                            System.out.println("Enter password:\n");
                            String logPas = in.nextLine();
                            if(logPas.equals(logUser.getPassword())){
                                System.out.println("Logged in Successfully");
                                if(logUser instanceof Customer )
                                    ((Customer)logUser).menu();
                                else if(logUser instanceof  AirlinePersonnel)
                                    ((AirlinePersonnel)logUser).menu();
                                else if(logUser instanceof AirlineAdmin)
                                    ((AirlineAdmin)logUser).menu();
                                else if(logUser instanceof AirportPersonnel)
                                    ((AirportPersonnel)logUser).menu();
                                else if(logUser instanceof AirportAdmin)
                                    ((AirportAdmin)logUser).menu();
                                else
                                    throw (new Exception("There is no logged person for given SSN"));
                            }
                            else{
                                System.out.println("Wrong password or SSN number!");
                                break;
                            }
                        }

                    case 2:
                        System.out.print("Enter the name : ");
                        String name = in.nextLine();

                        while (  name == null || name.equals("") ) {
                            System.out.print("Enter the name : ");
                            name = in.nextLine();
                        }

                        System.out.print("Enter the surname : ");
                        String surname = in.nextLine();

                        while (  surname == null || surname.equals("") ) {
                            System.out.print("Enter the surname : ");
                            surname = in.nextLine();
                        }

                        System.out.print("Enter the SSN : ");
                        String SSN = in.nextLine();

                        while (  SSN == null || SSN.equals("") ) {
                            System.out.print("Enter the SSN : ");
                            SSN = in.nextLine();
                        }

                        User isExist = airport.getAirportSystemStorage().getUserWithSSN( SSN );

                        if( isExist != null ) {
                            System.out.println("There already exists an given SSN !");
                            System.out.print("Enter the SSN of admin : ");
                            SSN = in.nextLine();
                            while (  SSN == null || SSN.equals("") ) {
                                System.out.print("Enter the SSN : ");
                                SSN = in.nextLine();
                            }
                        }

                        System.out.print("Enter the password : ");
                        String password = in.nextLine();

                        while (  password == null || password.equals("") ) {
                            System.out.print("Enter the password : ");
                            password = in.nextLine();
                        }

                        airport.getAirportSystemStorage().getCustomers().put( SSN , new Customer( name ,surname , SSN , password , airport.getAirportSystemStorage()));
                        System.out.println("Enrollment have been done.");

                        break;

                    case 3:
                        break;

                    case 4: loginPageLoop = false;
                        break;
                    default: System.out.println("Error. Your input is invalid..");
                }
            }


        } catch ( Throwable overAllException ){
            overAllException.printStackTrace();
        }
    }
}
