
public class  Projects {

	/*
	 *  Attributes
	 */
	private final String projectNumber;
	private final String projectName;
	private final String buildingType;
	private final String physicalAddress;
	private final String erfNumber;
	private final String totalFeeCharge;
	private final String totalFeePaid;
	private final String deadline;
	private final String finalised;

	private final Persons architect;
	private final Persons contractor;
	private final Persons customer;

	/**
	 * This is a constructor method used to create a new project based on the given parameters.
	 */
	public Projects(String projectNumber, String projectName, String buildingType, String physicalAddress, String erfNumber,
			String totalFeeCharge, String totalFeePaid, String deadline, String finalised, Persons architect, Persons contractor,
			Persons customer) {
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.buildingType = buildingType;
		this.physicalAddress = physicalAddress;
		this.erfNumber = erfNumber;
		this.totalFeeCharge = totalFeeCharge;
		this.totalFeePaid = totalFeePaid;
		this.deadline = deadline;
		this.finalised = finalised;
		this.architect = architect;
		this.contractor = contractor;
		this.customer = customer;
	}

	/**
	 * This is the toString method used to create a string result of the project.
	 *
	 * @return String This will return a String of the project.
	 */
	public String toString() {
		return "\nProject Number: " + projectNumber + "\n" +
				"Project Name: " + projectName + "\n" +
				"Type of building: " + buildingType + "\n" +
				"ERF Number: " + erfNumber + "\n" +
				"Physical Address: " + physicalAddress + "\n" +
				"Total fee charged for project: " + totalFeeCharge + "\n" +
				"Total amount already paid: " + totalFeePaid + "\n" +
				"Deadline of the project: " + deadline + "\n" +
				"Project Finalised: " + finalised + "\n" +
				"\nArchitect details: \n" + architect + "\n" + "\n" +
				"Contractor details: \n" + contractor + "\n" + "\n" +
				"Customer details: \n" + customer;
	}

}
