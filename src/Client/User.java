package Client;

import java.util.Objects;

/**
 * Class that represents user of the system.
 */
public class User extends Person implements UserInterface  {

    private String SSN;
    private String password;

    /**
     * Constructor of the Person.
     * @param name    As the name of the user.
     * @param surname as the surname of the user.
     * @param SSN     as the SSN of user.
     * @param password as login password.
     */
    public User(String name, String surname, String SSN, String password ) throws Exception {
        super( name , surname );

        if( SSN == null ) {
            throw new Exception("SSN cannot be null.");
        }

        if( password == null ) {
            throw new Exception("Password cannot be null.");
        }

        this.SSN = SSN;
        this.password = password;
    }

    /**
     * Method for receiving SSN.
     * @return SSN as string.
     */
    @Override
    public String getSSN(){
        return SSN;
    }

    /**
     * Method for setting a new SSN.
     * @param SSN as a new SSN to assign.
     */
    @Override
    public void setSSN(String SSN) throws Exception {
        if( SSN == null ) {
            throw new Exception("SSN cannot be null.");
        }

        this.SSN = SSN;
    }

    /**
     * Method for receiving password.
     * @return password as String.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Method for setting a new password.
     * @param password as a new password to assign.
     */
    @Override
    public void setPassword(String password) throws Exception {
        if( password == null ) {
            throw new Exception("Password cannot be null.");
        }

        this.password = password;
    }

    /**
     * Equals.
     * @param o Object to compare.
     * @return true if user SSNs match with each other.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return SSN.equals(user.SSN);
    }

    /**
     * Hash Code.
     * @return hashCode of user.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), SSN);
    }

    /**
     * String representation of the user.
     * @return
     */
    @Override
    public String toString() {
        return super.toString()+" SSN:"+getSSN()+" Password:"+getPassword();
    }
}
