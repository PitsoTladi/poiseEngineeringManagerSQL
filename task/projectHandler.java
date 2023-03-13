import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class projectHandler {
	
	 static Projects proj;

	    /**
	     * Creating a method named 'createProject' that will be used to create new projects.
	     *
	     * @param projects An ArrayList of type Projects is needed here.
	     */
	    public static void createProject(ArrayList<Projects> projects) {

	        // Calling the 'connectionAttempt' method to connect to the 'poised_DB' database.
	        Connection connection = dbHandler.connectionAttempt();

	        @SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);  // Creating instance for Scanner package.

	        String projectNum = null;
	        String projectName = null;
	        String typeOfBuilding = null;
	        String physicalAddress = null;
	        String erfNum = null;
	        String totalFeeCharge = null;
	        String totalFeePaid = null;
	        String deadline = null;
	        String finalised = null;

	        String architectName = null;
	        String architecSurName = null;
	        String architectTele = null;
	        String architectEmail = null;
	        String architectAddress = null;

	        String contractorName = null;
	        String contractorSurName = null;
	        String contractorTele = null;
	        String contractorEmail = null;
	        String contractorAddress = null;

	        String customerName = null;
	        String customerSurname = null;
	        String customerTele = null;
	        String customerEmail = null;
	        String customerAddress = null;

	        // Using a try-catch-finally block to get the new book information from the user.
	        try {

	            // Requesting each needed piece of information and storing it in the appropriate variable.
	            System.out.print("\nInsert Project Number:\t");
	            projectNum = input.nextLine();
	            dbHandler.intTest(projectNum, " Project Number");  // Testing whether or not input is an integer.
	            dbHandler.convertToInt(projectNum);

	            System.out.print("\nInsert Project Name:\t");
	            projectName = input.nextLine();

	            System.out.print("\nInsert Building Type (Home, Office-Building, etc):\t");
	            typeOfBuilding = input.nextLine();

	            System.out.println("\nInsert Physical Address of Building:\t");
	            physicalAddress = input.nextLine();

	            System.out.print("\nInsert ERF Number:\t");
	            erfNum = input.nextLine();
	            dbHandler.intTest(erfNum, " ERF Number");  // Testing whether or not input is an integer.
	            dbHandler.convertToInt(erfNum);

	            System.out.print("\nInsert Total Fee for Project:\t");
	            totalFeeCharge = input.nextLine();
	            dbHandler.intTest(totalFeeCharge, " Total Fee");  // Testing whether or not input is an integer.
	            dbHandler.convertToInt(totalFeeCharge);

	            System.out.print("\nInsert Total Amount Already Paid:\t");
	            totalFeePaid = input.nextLine();
	            dbHandler.intTest(totalFeePaid, " Total Paid");  // Testing whether or not input is an integer.
	            dbHandler.convertToInt(totalFeePaid);

	            System.out.println("""
	                    Insert the new due-date that you would like to have for the project: 
	                    (Format: YYYY-MM-DD)(E.g: 2021-06-16)""");
	            deadline = input.nextLine();

	            finalised = "No";

	            // Making a string array for each person working on the project with
	            // the values gathered from the 'getDetails' method.

	            // An instance of "Architect" is then created with the values gathered in getDetails. ('Details' used here)
	            String[] details = getDetails("Architect");
	            Persons architect = new Persons(details[0], details[1], details[2], details[3], details[4]);

	            architectName = details[0];
	            architecSurName = details[1];
	            architectTele = details[2];
	            architectEmail = details[3];
	            architectAddress = details[4];

	            // An instance of "Contractor" is then created with the values gathered in getDetails. ('Details1' used here)
	            String[] details1 = getDetails("Contractor");
	            Persons contractor = new Persons(details1[0], details1[1], details1[2], details1[3], details[4]);

	            contractorName = details1[0];
	            contractorSurName = details1[1];
	            contractorTele = details1[2];
	            contractorEmail = details1[3];
	            contractorAddress = details1[4];

	            // An instance of "Customer" is then created with the values gathered in getDetails. ('Details2' used here)
	            String[] details2 = getDetails("Customer");
	            Persons customer = new Persons(details2[0], details2[1], details2[2], details2[3], details[4]);

	            customerName = details2[0];
	            customerSurname = details2[1];
	            customerTele = details2[2];
	            customerEmail = details2[3];
	            customerAddress = details2[4];

	            // Using an if-statement to determine whether or not the Project Name should be generated for the user.
	            if (projectName.equals("")) {

	                // If the Project Name variable is empty, then the Project Name will be equal to the
	                // Customer's first name and the type of building for the project.
	                String[] customerFullName = details2[3].split(" ");

	                projectName = typeOfBuilding + " " + customerFullName[0];
	            }

	            // Creating an instance of "Project" with all the information gathered in this method.
	            // The arguments given for the class are the attributes of this method
	            Projects proj = new Projects(projectNum, projectName, typeOfBuilding, physicalAddress, erfNum, totalFeeCharge,
	                    totalFeePaid, deadline, finalised, architect, contractor, customer);

	            projectHandler.proj = proj;

	            //  Adding the created 'proj' instance to the 'projects' arraylist.
	            projects.add(proj);

	            // Printing success message and displaying added project to the user.
	            System.out.println("\n\n------------------------------------------------------------------------------------");
	            System.out.println("\nProject Added:\n");
	            System.out.println(proj);

	        } catch (Exception e) {

	            // Error message
	            System.out.println("Incorrect Input");
	        }

	        // Using a try-catch-finally block to insert the user's input into the 'books' table within the 'ebookstore' database.
	        try {

	            //
	            String architectInsert = " insert into architect (name, surName, telephoneNumber, emailAddress, physicalAddress)" +
	                    " values (?, ?, ?, ?, ?)";

	            // Creating and using a PreparedStatement to insert the user's input into the database.
	            PreparedStatement preparedStmtArchi = connection.prepareStatement(architectInsert);
	            preparedStmtArchi.setString(1, architectName);
	            preparedStmtArchi.setString(2, architecSurName);
	            preparedStmtArchi.setString(3, architectTele);
	            preparedStmtArchi.setString(4, architectEmail);
	            preparedStmtArchi.setString(5, architectAddress);

	            // Executing the created statement
	            preparedStmtArchi.execute();

	            // ------------------------------- //

	            //
	            String contractorInsert = " insert into contractor (name, surName, telephoneNumber, emailAddress, physicalAddress)" +
	                    " values (?, ?, ?, ?, ?)";

	            // Creating and using a PreparedStatement to insert the user's input into the database.
	            PreparedStatement preparedStmtContr = connection.prepareStatement(contractorInsert);
	            preparedStmtContr.setString(1, contractorName);
	            preparedStmtContr.setString(2, contractorSurName);
	            preparedStmtContr.setString(3, contractorTele);
	            preparedStmtContr.setString(4, contractorEmail);
	            preparedStmtContr.setString(5, contractorAddress);

	            // Executing the created statement
	            preparedStmtContr.execute();

	            //
	            String customerInsert = " insert into customer (name, surName, telephoneNumber, emailAddress, physicalAddress)" +
	                    " values (?, ?, ?, ?, ?)";

	            // Creating and using a PreparedStatement to insert the user's input into the database.
	            PreparedStatement preparedStmtCust = connection.prepareStatement(customerInsert);
	            preparedStmtCust.setString(1, customerName);
	            preparedStmtCust.setString(2, customerSurname);
	            preparedStmtCust.setString(3, customerTele);
	            preparedStmtCust.setString(4, customerEmail);
	            preparedStmtCust.setString(5, customerAddress);

	            // Executing the created statement
	            preparedStmtCust.execute();

	            // ------------------------------- //

	            // SQL insert statement
	            String query = " insert into projects (projectNumber, projectName, deadline, finalised, " +
	                    "physicalAddress, totalFeeCharge, totalFeePaid, architectName," +//
	                    "contractorName, customerName) " +
	                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	            // Creating and using a PreparedStatement to insert the user's input into the database.
	            PreparedStatement preparedStmtProj = connection.prepareStatement(query);
	            preparedStmtProj.setString(1, projectNum);
	            preparedStmtProj.setString(2, projectName);
	            preparedStmtProj.setString(3, deadline);
	            preparedStmtProj.setString(4, finalised);
	            preparedStmtProj.setString(5, physicalAddress);
	            preparedStmtProj.setString(6, totalFeeCharge);
	            preparedStmtProj.setString(7, totalFeePaid);
	            preparedStmtProj.setString(8, architectName);
	            preparedStmtProj.setString(9, contractorName);
	            preparedStmtProj.setString(10, customerName);

	            // Executing the created statement
	            preparedStmtProj.execute();

	            // ------------------------------- //

	            String buildingInsert = " insert into buildingInfo (erfNumber, buildingType, fkProjectNumber)" +
	                    " values (?, ?, ?)";

	            // Creating and using a PreparedStatement to insert the user's input into the database.
	            PreparedStatement preparedStmtBuild = connection.prepareStatement(buildingInsert);
	            preparedStmtBuild.setString(1, erfNum);
	            preparedStmtBuild.setString(2, typeOfBuilding);
	            preparedStmtBuild.setString(3, projectNum);

	            // Executing the created statement
	            preparedStmtBuild.execute();

	            connection.close();
	        } catch (Exception e) {

	            // Error message
	            System.err.println("\nGot an exception! Could not add project details to database.");
	            System.err.println(e.getMessage());
	        }

	    }

	    /**
	     * Creating a method named 'getDetails' that will be used to get
	     * the details of each person involved in a project.
	     *
	     * @param role This parameter requires the role of the person.
	     * @return String[] This will return a list of projects read from the chosen person.
	     */
	    public static String[] getDetails(String role) {

	        @SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);  // Instance for Scanner package.

	        // Requesting each needed piece of information and storing it in the appropriate variable.
	        System.out.println("\nInsert Name of " + role + ":\t");
	        String name = input.nextLine();
	        
	        System.out.println("\nInsert surName of " + role + ":\t");
	        String surName = input.nextLine();

	        System.out.println("\nInsert Telephone Number of " + role + ":\t");
	        String telephone = input.nextLine();

	        System.out.println("\nInsert Email-Address of " + role + ":\t");
	        String emailAddress = input.nextLine();

	        System.out.println("\nInsert Physical Home Address of " + role + ":\t");
	        String physicalAddress = input.nextLine();

	        // Returning a string array with the information gathered in this method.
	        return new String[]{name, surName, telephone, emailAddress, physicalAddress};
	    }

	    /**
	     * Creating a method named 'updateDeadline' that will
	     * be used to update the deadline for a chosen project.
	     */
	    public static void updateDeadline() {

	        // Calling the 'connectionAttempt' method to connect to the 'ebookstore' database.
	        Connection connection = dbHandler.connectionAttempt();

	        @SuppressWarnings("resource")
			Scanner input2 = new Scanner(System.in);

	        PreparedStatement ps;
	        int id = 0;
	        String newDeadine = null;

	        // Using a try-catch block to get the new quantity for a chosen book from the user.
	        try {
	            // New Date Message
	            System.out.println("""
	                                    
	                    ----------------------------------------------------------------------------------------------                                
	                                    
	                    Insert the new due-date that you would like to have for the project:
	                    (Format: YYYY-MM-DD)(E.g: 2021-06-16)                            
	                                                   
	                    """);

	            newDeadine = input2.nextLine();

	            // Project Choice Message
	            System.out.println("""
	                                    
	                    ----------------------------------------------------------------------------------------------                                
	                                    
	                    Insert The Number Or Name Of The Project That You Want To Edit:
	                                                   
	                    """);

	            id = input2.nextInt();

	        } catch (Exception e) {

	            // Error message
	            System.out.println("\nIncorrect Input");
	        }

	        // Using a try-catch block to update the chosen record with the new quantity details for chosen book.
	        try {
	            // SQL update statement
	            String query = "UPDATE projects SET deadline = ? WHERE projectNumber = ? ";

	            // Using a PreparedStatement to change the details for the chosen record/ id.
	            ps = connection.prepareStatement(query);
	            ps.setString(1, newDeadine);
	            ps.setInt(2, id);

	            ps.executeUpdate();  // Executing statement

	            System.out.println("\n------------------------\n" +
	                    "Record updated successfully");

	            connection.close();
	        } catch (SQLException e) {

	            // Error message
	            e.printStackTrace();
	        }

	    }

	    /**
	     * Creating a method named 'updateFee' that will
	     * be used to update the amount of money paid by the customer to date.
	     */
	    public static void updateFee() {

	        // Calling the 'connectionAttempt' method to connect to the 'ebookstore' database.
	        Connection connection = dbHandler.connectionAttempt();

	        @SuppressWarnings("resource")
			Scanner input3 = new Scanner(System.in);

	        PreparedStatement ps;
	        int id = 0;
	        int newTotalPaid = 0;

	        // Using a try-catch block to get the new quantity for a chosen book from the user.
	        try {
	            // Project Choice Message
	            System.out.println("""
	                                    
	                    ----------------------------------------------------------------------------------------------                                
	                                    
	                    Insert The Number Or Name Of The Project That You Want To Edit:
	                                                   
	                    """);

	            id = input3.nextInt();


	            // Requesting the new amount paid by the customer to date.
	            System.out.println("""
	                                    
	                    ----------------------------------------------------------------------------------------------                                
	                                    
	                    Input New Amount Paid for Project Fee:
	                                                             
	                    """);

	            newTotalPaid = input3.nextInt();

	        } catch (Exception e) {

	            // Error message
	            System.out.println("\nIncorrect Input");
	        }

	        // Using a try-catch block to update the chosen record with the new quantity details for chosen book.
	        try {
	            // SQL update statement
	            String query = "UPDATE projects SET totalFeePaid = ? WHERE projectNumber = ? ";

	            // Using a PreparedStatement to change the details for the chosen record/ id.
	            ps = connection.prepareStatement(query);
	            ps.setInt(1, newTotalPaid);
	            ps.setInt(2, id);

	            ps.executeUpdate();  // Executing statement

	            System.out.println("\n------------------------\n" +
	                    "Record updated successfully");

	            connection.close();
	        } catch (SQLException e) {

	            // Error message
	            e.printStackTrace();
	        }

	    }

	    /**
	     * Creating a method named 'updateContractor' that will be
	     * used to update the contractor's contact details.
	     */
	    public static void updateContractor() {

	        // Calling the 'connectionAttempt' method to connect to the 'ebookstore' database.
	        Connection connection = dbHandler.connectionAttempt();

	        @SuppressWarnings("resource")
			Scanner input4 = new Scanner(System.in);

	        PreparedStatement psContractor;
	        PreparedStatement psProjects;
	        PreparedStatement psClose = null;
	        PreparedStatement psOpen = null;
	        String contractor = null;
	        String newName = null;
	        String newSurName = null;
	        String newNum = null;
	        String newEmail = null;
	        String newAddress = null;

	        // Using a try-catch block to get the new quantity for a chosen book from the user.
	        try {
	            // Project Choice Message
	            System.out.println("""
	                                    
	                    ----------------------------------------------------------------------------------------------                                
	                                    
	                    Insert The Name of the Contractor You Would Lie to Edit:
	                                                   
	                    """);

	            contractor = input4.nextLine();

	            // Asking user for new Contractor Name
	            System.out.println("""
	                                    
	                    Insert the new contact details for the contractor working on the project
	                                             
	                    ----------------------------------------------------------------------------------------------                                
	                                    
	                    Insert the name of the contractor working on the project:
	                                                   
	                    """);

	            newName = input4.nextLine();  // Requesting user's choice

	            // Asking user for new Contractor TelephoneNumber
	            System.out.println("""
	                                    
	                    ----------------------------------------------------------------------------------------------                                
	                                    
	                    Insert the telephoneNumber of the contractor working on the project:
	                                                   
	                    """);
	            
	            newSurName = input4.nextLine();  // Requesting user's choice

	            // Asking user for new Contractor TelephoneNumber
	            System.out.println("""
	                                    
	                    ----------------------------------------------------------------------------------------------                                
	                                    
	                    Insert the telephonenNumber of the contractor working on the project:
	                                                   
	                    """);

	            newNum = input4.nextLine();  // Requesting user's choice

	            // Asking user for new Contractor Email-Address
	            System.out.println("""
	                                    
	                    ----------------------------------------------------------------------------------------------                                
	                                    
	                    Insert the email-Address of the contractor working on the project:
	                                                   
	                    """);

	            newEmail = input4.nextLine();  // Requesting user's choice

	            // Asking user for new Contractor Physical-Address
	            System.out.println("""
	                                    
	                    ----------------------------------------------------------------------------------------------                                
	                                    
	                    Insert the physical address of the contractor working on the project:
	                                                   
	                    """);

	            newAddress = input4.nextLine();  // Requesting user's choice


	        } catch (Exception e) {

	            // Error message
	            System.out.println("\nIncorrect Input");
	        }

	        // Your problem is below here!

	        // Using a try-catch block to update the chosen record with the new quantity details for chosen book.
	        try {
	            // SQL update statement
	            String closeForeignChecks = "SET FOREIGN_KEY_CHECKS=0 ";

	            String openForeignChecks = "SET FOREIGN_KEY_CHECKS=0 ";

	            // SQL update statement
	            String updateContractor =
	                    "UPDATE contractor SET name = ?, telephone = ?, emailAddress = ?, physicalAddress = ?\n" +
	                            "WHERE contractor.name = ? ";



	            // SQL update statement
	            String updateProjects = "UPDATE projects SET contractorName = ?\n " +
	                    "WHERE projects.contractorName = ?";

	            // Using a PreparedStatement to change the details for the chosen record/ id.
	            psContractor = connection.prepareStatement(updateContractor);
	            psContractor.setString(1, newName);
	            psContractor.setString(2, newSurName);
	            psContractor.setString(3, newNum);
	            psContractor.setString(4, newEmail);
	            psContractor.setString(5, newAddress);
	            psContractor.setString(6, contractor);

	            // Using a PreparedStatement to change the details for the chosen record/ id.
	            psProjects = connection.prepareStatement(updateProjects);
	            psProjects.setString(1, newName);
	            psProjects.setString(2, contractor);

	            psClose = connection.prepareStatement(closeForeignChecks);
	            psClose.executeUpdate();

	            psContractor.executeUpdate();  // Executing statement
	            psProjects.executeUpdate();  // Executing statement

	            psOpen = connection.prepareStatement(openForeignChecks);
	            psOpen.executeUpdate();

	            System.out.println("\n------------------------\n" +
	                    "Record updated successfully");

	            connection.close();
	        } catch (SQLException e) {

	            // Error message
	            e.printStackTrace();
	        }
	    }

	    /**
	     * finaliseProject method used to finalise a chosen project, print it's invoice,
	     * and append the project to the 'Completed Projects' text-file.
	     */
	    public static void finaliseProject() {

	        // Calling the 'connectionAttempt' method to connect to the 'PoisePMS' database.
	        Connection connection = dbHandler.connectionAttempt();

	        @SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);

	        // Variables
	        int id = 0;
	        PreparedStatement ps_projects;
	        ResultSet rs = null;

	        int projectNumber = 0;
	        String projectName = null;
	        String deadline = null;
	        String finalised = null;
	        String physicalAddress = null;
	        int erf = 0;
	        String buildingType = null;
	        int totalFee = 0;
	        int totalPaid = 0;

	        String architectName = null;
	        @SuppressWarnings("unused")
			String architectSurName = null;
	        String architectTele = null;
	        String architectEmail = null;
	        String architectAddress = null;

	        String contractorName = null;
	        @SuppressWarnings("unused")
			String contractorSurName = null;
	        String contractorTele = null;
	        String contractorEmail = null;
	        String contractorAddress = null;

	        String customerName = null;
	        @SuppressWarnings("unused")
			String customerSurName = null;
	        String customerTele = null;
	        String customerEmail = null;
	        String customerAddress = null;

	        // Creating a string variable for the current date.
	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DATE, 1);
	        Date date = cal.getTime();
	        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	        String currentDate = null;
	        currentDate = format1.format(date);

	        // Using a try-catch block to get the new quantity for a chosen book from the user.
	        try {
	            // Project Choice Message
	            System.out.println("""
	                                    
	                    ----------------------------------------------------------------------------------------------                                
	                                    
	                    Insert The Number Or Name Of The Project That You Want To Finalize:
	                                                   
	                    """);

	            id = input.nextInt();

	        } catch (Exception e) {

	            // Error message
	            System.out.println("\nIncorrect Input");
	        }

	        // Using a try-catch block to update the chosen record with the new quantity details for chosen book.
	        try {
	            // SQL update statement
	            String query = "UPDATE projects SET finalised = 'Yes' WHERE projectNumber = ? ";

	            // Using a PreparedStatement to change the details for the chosen record/ id.
	            ps_projects = connection.prepareStatement(query);
	            ps_projects.setInt(1, id);

	            ps_projects.executeUpdate();  // Executing statement

	            System.out.println("\n------------------------\n" +
	                    "Record updated successfully");

	        } catch (SQLException e) {

	            // Error message
	            e.printStackTrace();
	        }

	        // SQL select statement
	        String query = "SELECT " +
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
	                "WHERE projectNumber = ?";
	        		//System.out.println(query);

	        try {
	            // Creating and using a PreparedStatement to insert the user's input into the database.
	            ps_projects = connection.prepareStatement(query);

	            ps_projects.setInt(1, projectNumber);

	            // Executing the created statement
	            rs = ps_projects.executeQuery();
	            System.out.println("check");

	            while (rs.next()) {    // Position the cursor

	                projectNumber = rs.getInt("projectNumber");
	                projectName = rs.getString("projectName");
	                deadline = rs.getString("deadline");
	                finalised = rs.getString("finalised");
	                physicalAddress = rs.getString("physicalAddress");
	                erf = rs.getInt("erfNumber");
	                buildingType = rs.getString("buildingType");
	                totalFee = rs.getInt("totalFeeCharge");
	                totalPaid = rs.getInt("totalFeePaid");

	                architectName = rs.getString("architectName");
	                architectSurName = rs.getString("architect_surName");
	                architectTele = rs.getString("architectTelephoneNumber");
	                architectEmail = rs.getString("architectEmailAddress");
	                architectAddress = rs.getString("architectPhysicalAddress");

	                contractorName = rs.getString("contractorName");
	                contractorSurName = rs.getString("contractorSurname");
	                contractorTele = rs.getString("contractorTelephoneNumber");
	                contractorEmail = rs.getString("contractorEmailAddress");
	                contractorAddress = rs.getString("contractorPhysicalAddress");

	                customerName = rs.getString("customerName");
	                customerSurName = rs.getString("customerSurname");
	                customerTele = rs.getString("customerTelephoneNumber");
	                customerEmail = rs.getString("customerEmailAddress");
	                customerAddress = rs.getString("customerPhysicalAddress");
	            }
	            
	            System.out.println("Project number: "+projectNumber);
	            

	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Unable to select from database.");
	        }
	        // ------------------------------- //

	        // SQL insert statement
	        query = " insert into finalisedProjects " +
	                "(projectNumber, projectName, deadline, dateFinalised, finalised, " +
	                "physicalAddress, erfNumber, buildingType, totalFeeCharge, totalFeePaid, " +
	                "architectName, architectTelephoneNumber, architectEmailAddress, architectPhysicalAddress, " +
	                "contractorName, contractorTelephoneNumber, contractorEmailAddress, contractorPhysicalAddress, " +
	                "customerName, customerTelephoneNumber, customerEmailAddress, customerPhysicalAddress) " +
	                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?,  ?, ?, ?, ?,  ?, ?, ?, ?);";

	        try {

	            // Creating and using a PreparedStatement to insert the project's data into the database.
	            ps_projects = connection.prepareStatement(query);

	            ps_projects.setInt(1, projectNumber);
	            ps_projects.setString(2, projectName);
	            ps_projects.setString(3, deadline);
	            ps_projects.setString(4, currentDate);
	            ps_projects.setString(5, finalised);
	            ps_projects.setString(6, physicalAddress);
	            ps_projects.setInt(7, erf);
	            ps_projects.setString(8, buildingType);
	            ps_projects.setInt(9, totalFee);
	            ps_projects.setInt(10, totalPaid);

	            ps_projects.setString(11, architectName);
	            ps_projects.setString(12, architectTele);
	            ps_projects.setString(13, architectEmail);
	            ps_projects.setString(14, architectAddress);

	            ps_projects.setString(15, contractorName);
	            ps_projects.setString(16, contractorTele);
	            ps_projects.setString(17, contractorEmail);
	            ps_projects.setString(18, contractorAddress);

	            ps_projects.setString(19, customerName);
	            ps_projects.setString(20, customerTele);
	            ps_projects.setString(21, customerEmail);
	            ps_projects.setString(22, customerAddress);

	            // Executing the created statement
	            ps_projects.execute();

	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Unable to select from database.");
	        }


	    }

}
