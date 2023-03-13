import java.sql.*;
import java.util.Scanner;

public class dbHandler {

	/**
	 * <b>readProjectFile</b> method used to read the 'Projects' text-file and add each line to an object ArrayList.
	 * This method will return an ArrayList value that can be worked with.
	 *
	 * @return ArrayList This will return a list of projects read from the Projects text-file.
	 */


	// connectionAttempt method used for attempting to open a connection to the 'ebookstore' database
	public static Connection connectionAttempt() {

		Connection connection = null;

		try {
			// Connect to the library_db database, via the jdbc:mysql:channel on localhost (this PC)
			// Use username "clerk1", password "password".
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/poisepms?allowPublicKeyRetrieval=true&useSSL=false",
					"root", 
					"@KEndrick47"
					);
		} catch (SQLException e) {

			// We only want to catch a SQLException
			e.printStackTrace();

		}

		return connection;
	}

	/**
	 * <b>convertToInt</b> method used to convert a string value into an integer.
	 * This method will return an integer value that can be used for calculations.
	 *
	 * @param str String needed to convert
	 * @return int This will return the converted String.
	 */
	public static int convertToInt(String str) {

		int intConverter = 0;

		// Using a try-catch block to try and parse/ convert the given string value into an integer.
		try {
			intConverter = Integer.parseInt(str);
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}

		return intConverter; // Returning the integer value for calulating.
	}

	/**
	 * <b>intTest</b> method used to determine whether or not a user's input is equal to an integer value.
	 *
	 * @param numToConvert The number needed to convert.
	 * @param type         The type of input needed to print to user.
	 */
	public static void intTest(String numToConvert, String type) {

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);  // Creating instance for Scanner package.

		while (true) {  // While loop repeatedly re-prompts for input until correct.
			try {
				Integer.parseInt(numToConvert);
				break;

			} catch (NumberFormatException ex) {
				System.out.println("\nIncorrect entry. Please ensure you are inserting the correct format");  // Error message
				System.out.print("\nInsert" + type + ":\t");  // Prompting for input again
				numToConvert = input.nextLine();

			}
		}
	}

	/**
	 * <b>viewAllProjects</b> method used to view every project in the 'Projects' text-file.
	 */
	public static void viewAllProjects() {

		// Calling the 'connectionAttempt' method to connect to the 'poised_DB' database.
		Connection connection = connectionAttempt();

		// Using a try- catch block to print the data for a specific record in the 'poise' table.
		try {
			// Creating and using a normal statement to select the specific record in the database.
			Statement statement = connection.createStatement();

			// SQL select statement
			String query =  "SELECT " +
					"projects.projectNumber, projects.projectName, projects.deadline, projects.finalised, projects.physicalAddress, projects.architectName, \n" +
					"projects.contractorName, projects.customerName, projects.totalFeeCharge, projects.totalFeePaid,\n" +
					"architect.telephoneNumber, architect.emailAddress, architect.physicalAddress,\n" +
					"contractor.telephoneNumber, contractor.emailAddress, contractor.physicalAddress,\n" +
					"customer.telephoneNumber, customer.emailAddress, customer.physicalAddress,\n" +
					"buildingInfo.erfNumber, buildingInfo.buildingType\n" +
					"FROM projects\n" +
					"JOIN architect ON projects.architectName  = architect.name\n" +
					"JOIN contractor ON projects.contractorName  = contractor.name\n" +
					"JOIN customer ON projects.customerName  = customer.name\n" +
					"JOIN buildingInfo ON projects.projectNumber  = buildingInfo.fkProjectNumber";
			//System.out.println(query);

			ResultSet rs_projects = statement.executeQuery(query); // Executing statement

			if (rs_projects.next()) {
				do {
					String projectDetails = "\nProject Number: " + rs_projects.getString("projects.projectNumber") + "\n" +
							"Project Name: " + rs_projects.getString("projects.projectName") + "\n" +
							"Type of building: " + rs_projects.getString("buildingInfo.buildingType") + "\n" +
							"ERF Number: " + rs_projects.getString("buildingInfo.erfNumber") + "\n" +
							"Physical Address: " + rs_projects.getString("projects.physicalAddress") + "\n" +
							"Total fee charged for project: R" + rs_projects.getString("totalFeeCharge") + "\n" +
							"Total amount paid: R" + rs_projects.getString("totalFeePaid") + "\n" +
							"Deadline of the project: " + rs_projects.getString("projects.deadline") + "\n" +
							"Project Finalised: " + rs_projects.getString("projects.finalised") + "\n" +

                            "\nArchitect Name: " + rs_projects.getString("projects.architectName") + "\n" +
                            //"\nArchitect SurName: " + rs_projects.getString("projects.architect_surName") + "\n" +
                            "Architect Telephone Number: " + rs_projects.getString("architect.telephoneNumber") + "\n" +
                            "Architect Email-Address: " + rs_projects.getString("architect.emailAddress") + "\n" +
                            "Architect Physical Address: " + rs_projects.getString("architect.physicalAddress") + "\n" +

                            "\nContractor Name: " + rs_projects.getString("projects.contractorName") + "\n" +
                           // "\nContractor SurName: " + rs_projects.getString("projects.contractor_surName") + "\n" +
                            "Contractor Telephone Number: " + rs_projects.getString("contractor.telephoneNumber") + "\n" +
                            "Contractor Email-Address: " + rs_projects.getString("contractor.emailAddress") + "\n" +
                            "Contractor Physical Address: " + rs_projects.getString("contractor.physicalAddress") + "\n" +

                            "\nCustomer Name: " + rs_projects.getString("projects.customerName") + "\n" +
                            //"\nCustomer SurName: " + rs_projects.getString("projects.customer_surName") + "\n" +
                            "Customer Telephone Number: " + rs_projects.getString("customer.telephoneNumber") + "\n" +
                            "Customer Email-Address: " + rs_projects.getString("customer.emailAddress") + "\n" +
                            "Customer Physical Address: " + rs_projects.getString("customer.physicalAddress") + "\n" +
                            "\n----------------------------------\n";

					System.out.println(projectDetails);
				} while (rs_projects.next());
			}

			//connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * <b>viewIncompleteProjects</b> method used to view every incomplete project in the 'Projects' text-file.
	 */
	public static void viewIncompleteProjects() {

		// Calling the 'connectionAttempt' method to connect to the 'ebookstore' database.
		Connection connection = connectionAttempt();

		@SuppressWarnings("unused")
		PreparedStatement ps;

		// Using a try- catch block to print the data for a specific record in the 'books' table.
		try {
			// Creating and using a normal statement to select the specific record in the database.
			Statement statement = connection.createStatement();

			// SQL select statement
			String query =  "SELECT " +
					"projects.projectNumber, projects.projectName, projects.deadline, projects.finalised, projects.physicalAddress, projects.architectName, \n" +
					"projects.contractorName, projects.customerName, projects.totalFeeCharge, projects.totalFeePaid,\n" +
					"architect.telephoneNumber, architect.emailAddress, architect.physicalAddress,\n" +
					"contractor.telephoneNumber, contractor.emailAddress, contractor.physicalAddress,\n" +
					"customer.telephoneNumber, customer.emailAddress, customer.physicalAddress,\n" +
					"buildingInfo.erfNumber, buildingInfo.buildingType\n" +
					"FROM projects\n" +
					"JOIN architect ON projects.architectName  = architect.name\n" +
					"JOIN contractor ON projects.contractorName  = contractor.name\n" +
					"JOIN customer ON projects.customerName  = customer.name\n" +
					"JOIN buildingInfo ON projects.projectNumber  = buildingInfo.fkProjectNumber\n" +
					"WHERE finalised = 'No';";

			ResultSet rs_projects = statement.executeQuery(query); // Executing statement

			// Printing ResultSet
			while (rs_projects.next()) {
				String projectDetails = "\nProject Number: " + rs_projects.getString("projects.projectNumber") + "\n" +
						"Project Name: " + rs_projects.getString("projects.projectName") + "\n" +
						"Type of building: " + rs_projects.getString("buildingInfo.buildingType") + "\n" +
						"ERF Number: " + rs_projects.getString("buildingInfo.erfNumber") + "\n" +
						"Physical Address: " + rs_projects.getString("projects.physicalAddress") + "\n" +
						"Total fee charged for project: R" + rs_projects.getString("totalFeeCharge") + "\n" +
						"Total amount already paid: R" + rs_projects.getString("totalFeePaid") + "\n" +
						"Deadline of the project: " + rs_projects.getString("projects.deadline") + "\n" +
						"Project Finalised: " + rs_projects.getString("projects.finalised") + "\n" +

                        "\nArchitect Name: " + rs_projects.getString("projects.architectName") + "\n" +
                        //"\nArchitect SurName: " + rs_projects.getString("projects.architect_surName") + "\n" +
                        "Architect Telephone Number: " + rs_projects.getString("architect.telephoneNumber") + "\n" +
                        "Architect Email-Address: " + rs_projects.getString("architect.emailAddress") + "\n" +
                        "Architect Physical Address: " + rs_projects.getString("architect.physicalAddress") + "\n" +

                        "\nContractor Name: " + rs_projects.getString("projects.contractorName") + "\n" +
                        //"\nContractor SurName: " + rs_projects.getString("projects.contractor_surName") + "\n" +
                        "Contractor Telephone Number: " + rs_projects.getString("contractor.telephoneNumber") + "\n" +
                        "Contractor Email-Address: " + rs_projects.getString("contractor.emailAddress") + "\n" +
                        "Contractor Physical Address: " + rs_projects.getString("contractor.physicalAddress") + "\n" +

                        "\nCustomer Name: " + rs_projects.getString("projects.customerName") + "\n" +
                        //"\nCustomer SurName: " + rs_projects.getString("projects.customer_surName") + "\n" +
                        "Customer Telephone Number: " + rs_projects.getString("customer.telephoneNumber") + "\n" +
                        "Customer Email-Address: " + rs_projects.getString("customer.emailAddress") + "\n" +
                        "Customer Physical Address: " + rs_projects.getString("customer.physicalAddress") + "\n" +
                        "\n----------------------------------\n";

				System.out.println(projectDetails);
			}
			//connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * <b>viewPastDue</b> method used to view every project past due-date in the 'Projects' text-file.
	 */
	public static void viewPastDue() {

		// Calling the 'connectionAttempt' method to connect to the 'poisepms' database.
		Connection connection = connectionAttempt();

		@SuppressWarnings("unused")
		PreparedStatement ps;

		// Using a try-catch block to print the data for a specific record in the 'books' table.
		try {
			// Creating and using a normal statement to select the specific record in the database.
			Statement statement = connection.createStatement();

			// SQL select statement
			String query =  "SELECT " +
					"projects.projectNumber, projects.projectName, projects.deadline, projects.finalised, projects.physicalAddress, projects.architectName, \n" +
					"projects.contractorName, projects.customerName, projects.totalFeeCharge, projects.totalFeePaid,\n" +
					"architect.telephoneNumber, architect.emailAddress, architect.physicalAddress,\n" +
					"contractor.telephoneNumber, contractor.emailAddress, contractor.physicalAddress,\n" +
					"customer.telephoneNumber, customer.emailAddress, customer.physicalAddress,\n" +
					"buildingInfo.erfNumber, buildingInfo.buildingType\n" +
					"FROM projects\n" +
					"JOIN architect ON projects.architectName  = architect.name\n" +
					"JOIN contractor ON projects.contractorName  = contractor.name\n" +
					"JOIN customer ON projects.customerName  = customer.name\n" +
					"JOIN buildingInfo ON projects.projectNumber  = buildingInfo.fkProjectNumber\n" +
					"WHERE projects.deadline < DATE(NOW()) AND projects.finalised = 'No';";

			ResultSet rs_projects = statement.executeQuery(query); // Executing statement

			// Printing ResultSet
			while (rs_projects.next()) {
				String projectDetails = "\nProject Number: " + rs_projects.getString("projects.projectNumber") + "\n" +
						"Project Name: " + rs_projects.getString("projects.projectName") + "\n" +
						"Type of building: " + rs_projects.getString("buildingInfo.buildingType") + "\n" +
						"ERF Number: " + rs_projects.getString("buildingInfo.erfNumber") + "\n" +
						"Physical Address: " + rs_projects.getString("projects.physicalAddress") + "\n" +
						"Total fee charged for project: R" + rs_projects.getString("totalFeeCharge") + "\n" +
						"Total amount already paid: R" + rs_projects.getString("totalFeePaid") + "\n" +
						"Deadline of the project: " + rs_projects.getString("projects.deadline") + "\n" +
						"Project Finalised: " + rs_projects.getString("projects.finalised") + "\n" +

                        "\nArchitect Name: " + rs_projects.getString("projects.architectName") + "\n" +
                        //"\nArchitect SurName: " + rs_projects.getString("projects.architect_surNmae") + "\n" +
                        "Architect Telephone Number: " + rs_projects.getString("architect.telephoneNumber") + "\n" +
                        "Architect Email-Address: " + rs_projects.getString("architect.emailAddress") + "\n" +
                        "Architect Physical Address: " + rs_projects.getString("architect.physicalAddress") + "\n" +

                        "\nContractor Name: " + rs_projects.getString("projects.contractorName") + "\n" +
                        //"\nContractor SurName: " + rs_projects.getString("projects.contractor_surName") + "\n" +
                        "Contractor Telephone Number: " + rs_projects.getString("contractor.telephoneNumber") + "\n" +
                        "Contractor Email-Address: " + rs_projects.getString("contractor.emailAddress") + "\n" +
                        "Contractor Physical Address: " + rs_projects.getString("contractor.physicalAddress") + "\n" +

                        "\nCustomer Name: " + rs_projects.getString("projects.customerName") + "\n" +
                        //"\nCustomer SurNmae: " + rs_projects.getString("projects.customer_surName") + "\n" +
                        "Customer Telephone Number: " + rs_projects.getString("customer.telephoneNumber") + "\n" +
                        "Customer Email-ddress: " + rs_projects.getString("customer.emailAddress") + "\n" +
                        "Customer Physical Address: " + rs_projects.getString("customer.physicalAddress") + "\n" +
                        "\n----------------------------------\n";

				System.out.println(projectDetails);
			}
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}



	}

	/**
	 * <b>overwriteProjFile</b> method used to write a string value over the 'Projects' text-file.
	 *
	 * @param listString This is a string needed to write to the <b>Projects</b> text-file.
	 */


	/**
	 * <b>appendCompletedFile</b> method used to append a string value to the 'Projects' text-file.
	 *
	 * @param listString This is a string needed to write to the <b>Projects</b> text-file.
	 */


}
