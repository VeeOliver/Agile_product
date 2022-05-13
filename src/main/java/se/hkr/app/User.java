package se.hkr.app;


public final class User {
    /**
     * User: Singleton instance for class User.
     */
    private static User singleInstance = null;
    /**
     * String: User personnummer.
     */
    private String personnummer;
    /**
     * String: User email address.
     */
    private String email;
    /**
     * String: Username.
     */
    private String name;

    /**
     * Contructor for User instance.
     * @param newPersonnummer
     * @param newEmail
     * @param newName
     */
    private User(final String newPersonnummer, final String newEmail,
            final String newName) {
        this.personnummer = newPersonnummer;
        this.email = newEmail;
        this.name = newName;
    }

    /**
     * Instantiate User instance if necessary and return singleton instance.
     * @param personnummer
     * @param email
     * @param name
     * @return Singleton User instance
     */
    public static User getInstance(final String personnummer,
            final String email, final String name) {
        if (singleInstance == null) {
            singleInstance = new User(personnummer, email, name);
        }
        return singleInstance;
    }

    /**
     * Reset User singleton instance to null.
     */
    public static void resetInstance() {
        User.singleInstance = null;
    }

    /**
     * Getter for singleton field instance.
     * @return Singleton user instance.
     */
    public static User getInstance() {
        return singleInstance;
    }

    /**
     * Getter for field personnummer.
     * @return Personnummer
     */
    public String getPersonnummer() {
        return personnummer;
    }

    /**
     * Getter for field email.
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for field email.
     * @param newEmail
     */
    public void setEmail(final String newEmail) {
        this.email = newEmail;
    }

    /**
     * Setter for field personnummer.
     * @param newPersonnummer
     */
    public void setPersonnummer(final String newPersonnummer) {
        this.personnummer = newPersonnummer;
    }

    /**
     * Getter for field name.
     * @return Username
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for field name.
     * @param newName
     */
    public void setName(final String newName) {
        this.name = newName;
    }
}
