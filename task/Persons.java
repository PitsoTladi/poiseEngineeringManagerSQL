public class Persons {
	
	/*
	 *  Attributes
	 */
    private final String name;
    private final String surName;
    private final String telephoneNumber;
    private final String emailAddress;
    private final String physicalAddress;

    /**
     * Constructor method used to add a person based on the given data.
     */
    public Persons(String name, String surName, String telephoneNumber, String emailAddress, String physicalAddress) {
        this.name = name;
        this.surName = surName;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
        this.physicalAddress = physicalAddress;
    }

    /*
     *  toString method
     */
    public String toString() {

        String output = "\nName: " + name;
        output += "\nSurName: " + surName;
        output += "\nTelephone: " + telephoneNumber;
        output += "\nEmail Address: " + emailAddress;
        output += "\nPhysical Address: " + physicalAddress;

        return output;
    }

}
