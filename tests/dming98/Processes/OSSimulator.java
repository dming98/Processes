package dming98.Processes;


import java.io.*;
import java.util.*;

public class OSSimulator {
	public static void main(String[] args) throws FileNotFoundException {
		String fileName, type;
		Scanner console = new Scanner(System.in);

		System.out.print("Please input the name of the file:  ");
		fileName = console.next();

		type = "regular";
		Queue elementsRegular = queueMaker(fileName, type);
		elementsRegular.printQueue();
		calculateTime(elementsRegular, type);

		type = "priority";
		Queue elementsPriority = queueMaker(fileName, type);
		elementsPriority.printQueue();
		calculateTime(elementsPriority, type);
	}

	/**
	 * Makes the queue of processes
	 * 
	 * @param fileName
	 *            file to be read from
	 * @param type
	 *            type of enqueue to use
	 * @return Queue of values in file
	 * @throws FileNotFoundException
	 */
	public static Queue queueMaker(String fileName, String type) throws FileNotFoundException {
		Queue elements = new Queue(15);
		Scanner file = null;
		FileReader in = null;
		Scanner console = new Scanner(System.in);

		try {
			file = new Scanner(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("You have provided an invalid file name.");

		}

		if (type.trim().equalsIgnoreCase("regular")) {
			while (file.hasNext()) {
				int ID = file.nextInt();
				double burst = file.nextDouble();
				Process newProcess = new Process(ID, burst);
				elements.enqueue(newProcess);
			}
			System.out.println("REGULAR ENQUEUE");
		} else if (type.trim().equalsIgnoreCase("priority")) {
			while (file.hasNext()) {
				int ID = file.nextInt();
				double burst = file.nextDouble();
				Process newProcess = new Process(ID, burst);
				elements.priorityEnqueue(newProcess);
			}
			System.out.println("PRIORITY ENQUEUE");
		} else
			throw new IllegalArgumentException("Invalid type choice.");
		return elements;
	}

	/**
	 * Calculates the wait times and turn around times for a process queue.
	 * 
	 * @param elements
	 *            queue to be used
	 * @param type
	 *            type of enqueue to use
	 */
	public static void calculateTime(Queue elements, String type) {
		Process current;
		double totalWait = 0.0;
		double totalTurn = 0.0;
		int completed = 0;
		double clock = 0.0;
		System.out.printf("\nID\tWait Time\tEnd Time\n");

		if (type.trim().equalsIgnoreCase("regular")) {
			while (!elements.isEmpty()) {
				current = elements.dequeue();
				if (current.getBurstRemain() <= (0.1)) {
					current.setWaitTime(current.getWaitTime() + (clock - current.getEndTime()));
					clock = clock + 1000 * current.getBurstRemain();
					current.setEndTime(clock);
					current.setBurstRemain(0);
					System.out.printf("%d\t%f\t%f\n", current.getID(), current.getWaitTime(), current.getEndTime());
					completed++;
					totalWait += current.getWaitTime();
					totalTurn += current.getEndTime();
				} else {
					current.setWaitTime(current.getWaitTime() + (clock - current.getEndTime()));
					clock += 100;
					current.setEndTime(clock);
					current.setBurstRemain(current.getBurstRemain() - .1);
					elements.enqueue(current);
				}
			}

		} else if (type.trim().equalsIgnoreCase("priority")) {
			while (!elements.isEmpty()) {
				current = elements.dequeue();
				if (current.getBurstRemain() <= (0.1)) {
					current.setWaitTime(current.getWaitTime() + (clock - current.getEndTime()));
					clock = clock + 1000 * current.getBurstRemain();
					current.setEndTime(clock);
					current.setBurstRemain(0);
					System.out.printf("%d\t%f\t%f\n", current.getID(), current.getWaitTime(), current.getEndTime());
					completed++;
					totalWait += current.getWaitTime();
					totalTurn += current.getEndTime();
				} else {
					current.setWaitTime(current.getWaitTime() + (clock - current.getEndTime()));
					clock += 100;
					current.setEndTime(clock);
					current.setBurstRemain(current.getBurstRemain() - .1);
					elements.priorityEnqueue(current);
				}
			}

		} else
			throw new IllegalArgumentException("Invalid type choice.");
		System.out.println("The total execution time was: " + clock + " seconds.");
		System.out.print("The average wait time was: " + (totalWait / completed) + " seconds.\n");
		System.out.print("The average turnaround time was: " + (totalTurn / completed) + " seconds.\n\n");
	}
}
