import java.util.ArrayList;
import java.util.Scanner;



public class poised {

	public static void main(String[] args) {

		// Creating an array to store the user's input.
		// An 'ArrayList' is used because the size of the list is not known and an ArrayList is resizable.
		ArrayList<Projects> projects = new ArrayList<>();

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);  // Creating instance for Scanner package.

		// Printing option list
		System.out.println("""

				===== M A I N   M E N U =====\n-----------------------------\n                               

				Select any choice below:\n========================\n
				1. Add a Project
				2. Update Deadline
				3. Update Fees
				4. Update Contractor details
				5. Display All Projects
				6. Finalize a Project
				7. View List of Incomplete Projects
				8. View List of Projects Past Due-Date
				9. Exit

				""");

		int choice = input.nextInt();  // Requesting user's choice

		// Using a while-loop to keep the program running until the user is finished/ inserts 9.
		while (choice != 9) {

			// Each user-defined method is passed the ArrayList value, 'projects'
			// which will be used to work with our created data.
			if (choice == 1) {
				projectHandler.createProject(projects);
			} else if (choice == 2) {
				projectHandler.updateDeadline();
			} else if (choice == 3) {
				projectHandler.updateFee();
			} else if (choice == 4) {
				projectHandler.updateContractor();
			} else if (choice == 5) {
				dbHandler.viewAllProjects();
			} else if (choice == 6) {
				projectHandler.finaliseProject();
			} else if (choice == 7) {
				dbHandler.viewIncompleteProjects();
			} else if (choice == 8) {
				dbHandler.viewPastDue();
			} else {
				System.out.println("Invalid Input");
			}

			System.out.println("""

					===== M A I N   M E N U =====\n-----------------------------\n                              

					Select any choice below:\n========================\n
					1. Add a Project
					2. Update Deadline
					3. Update Fees
					4. Update Contractor details
					5. Display All Projects
					6. Finalize a Project
					7. View List of Incomplete Projects
					8. View List of Projects Past Due-Date
					9. Exit
					""");

			choice = input.nextInt();  // Requesting user's choice
		}

	}

}
