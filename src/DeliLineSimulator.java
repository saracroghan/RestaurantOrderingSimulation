import java.util.LinkedList;
import java.util.Scanner;

public class DeliLineSimulator {
	private LinkedList<Customer> student_line;
	private LinkedList<Customer> prof_line;
	private int size_student;
	private int size_prof;
	private int total_time;
	private int num_served;
	
	public DeliLineSimulator() {
		this.student_line = new LinkedList<Customer>();
		this.prof_line = new LinkedList<Customer>();
		this.size_prof = 0;
		this.size_student = 0;
		this.total_time = 0;
		this.num_served = 0;
	}
	public void addStudent() {
		Scanner scanStudent = new Scanner(System.in);
		System.out.println("What is the customer's name?");
		String name = scanStudent.nextLine();
		System.out.println("What would you like to order?");
		String order = scanStudent.nextLine();
		Customer new_custy = new Customer(name, order);
		new_custy.setStartTime(System.nanoTime());
		student_line.add(new_custy);
		size_student ++;
		System.out.println("Added "+new_custy.getName()+" (Student) to the queue");
	}
	public void addProfessor() {
		Scanner scanProf = new Scanner(System.in);
		System.out.println("What is the customer's name?");
		String name = scanProf.nextLine();
		System.out.println("What would you like to order?");
		String order = scanProf.nextLine();
		Customer new_custy = new Customer(name, order);
		new_custy.setStartTime(System.nanoTime());
		prof_line.add(new_custy);
		size_prof ++;
		System.out.println("Added "+new_custy.getName()+" (Professor) to the queue");
	}
	public void serveNext() {
		if (size_prof!= 0) {
			Customer served = prof_line.remove();
			long end_time = System.nanoTime();
			Long elapsed_time = served.getElapsedTime(end_time);
			total_time += elapsed_time;
			num_served ++;
			size_prof --;
			System.out.println("Time spend in queue: "+elapsed_time+" seconds");
			System.out.println(served.getName()+" was served their "+served.getOrder());
		}
		else if (size_student != 0) {
			Customer served = student_line.remove();
			long end_time = System.nanoTime();
			Long elapsed_time = served.getElapsedTime(end_time);
			total_time += elapsed_time;
			num_served ++;
			size_student --;
			System.out.println("Time spend in queue: "+elapsed_time+" seconds");
			System.out.println(served.getName()+" was served their "+served.getOrder());
		}
		else {
			System.out.println("There are no customers in line to be served");
		}
	}
	public void removeFromQueue() {
		Scanner scanRemove = new Scanner(System.in);
		System.out.println("What customer is leaving the line?");
		String name = scanRemove.nextLine();
		for(Customer p: prof_line ) {
			if (name.equals(p.getName())) {
				prof_line.remove(p);
				size_prof --;
				long end_time = System.nanoTime();
				Long elapsed_time = p.getElapsedTime(end_time);
				System.out.println("Time spend in queue: "+elapsed_time);
			}
		}
			for(Customer s: student_line ) {
				if (name.equals(s.getName())) {
					student_line.remove(s);
					size_student --;
					long end_time = System.nanoTime();
					Long elapsed_time = s.getElapsedTime(end_time);
					System.out.println("Time spend in queue: "+elapsed_time);
				}
			}
	}
	public void printQueue() {
		System.out.println("Current Queue");
		System.out.println("--------------");
		int tally = 1;
		for(Customer p: prof_line) {
			System.out.println(tally+". "+p.printCustomer());
			tally ++;
		}
		for(Customer s: student_line) {
			System.out.println(tally+". "+s.printCustomer());
			tally++;
		}
		System.out.println();
	}
	public void printQueueStatistics() {
		System.out.println("Queue statistics");
		System.out.println("----------------");
		double avg_wait = total_time / num_served;
		System.out.println("Average wait time: "+avg_wait+" seconds.");
		System.out.println("Customers served: "+num_served);
		System.out.println();
	}
	public void printMenu() {
		System.out.println("1. View Current Queue");
		System.out.println("2. Add student to queue");
		System.out.println("3. Add professor to queue");
		System.out.println("4. Serve next customer");
		System.out.println("5. Remove customer from queue");
		System.out.println("6. Print Queue Statistics");
		System.out.println("7. Exit");
		System.out.println();
		System.out.println("Your choice?");
	}
	public void simulate() {
		Scanner scanMenu = new Scanner(System.in);
		printMenu();
		int choice = scanMenu.nextInt();
		if(choice == 1) {
			printQueue();
			simulate();
			return;
		}
		else if (choice == 2) {
			addStudent();
			simulate();
			return;
		}
		else if(choice == 3) {
			addProfessor();
			simulate();
			return;
		}
		else if(choice == 4) {
			serveNext();
			simulate();
			return;
		}
		else if (choice == 5) {
			removeFromQueue();
			simulate();
			return;
		}
		else if (choice == 6) {
			printQueueStatistics();
			simulate();
			return;
		}
		else if (choice == 7) {
			return;
		}
	}
	
	

	

}
