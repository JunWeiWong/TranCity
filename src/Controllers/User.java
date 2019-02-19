package Controllers;

public class User {
    private String name;
    private String email;
    private String password;

    /**
     * Constructor of a user with his account info: name, email, and password.
     *
     * @param name  the username
     * @param email the linked email address
     * @param pass  the password
     */
    User(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.password = pass;
    }

    /**
     * Sets a new password of the User
     *
     * @param pass the new password to change
     */
    public void setPassword(String pass) {
        this.password = pass;
    }

    /**
     * Changes the name of the User.
     *
     * @param newName the changed name
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Changes the email of the User.
     *
     * @param newEmail the changed name
     */
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    /**
     * Returns the Name of the User
     *
     * @return String the name of the User
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the Email of the User
     *
     * @return String the email of the User
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the Access Level of the User * @param email user email
     *
     * @param password user password
     * @return boolean True if the strings email and password matches with user info
     */
    public boolean verifyUser(String email, String password) {
        boolean result = false;
        if (this.email.equals(email) && this.password.equals(password)) {
            result = true;
        }
        return result;
    }
}
