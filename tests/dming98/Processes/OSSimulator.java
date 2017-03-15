package dming98.Processes;

import java.io.*;
import java.util.*;

public class OSSimulator {
	public static void main(String[] args) throws FileNotFoundException {
		String fileName;
		Scanner console = new Scanner(System.in);

		System.out.print("Please input the name of the file:  ");
		fileName = console.next();
		System.out.print("\nType regular for FIFO, or type priority for priority enqueue:   ");
		String type = console.next();
		Queue elements = queueMaker(fileName, type);
		elements.printQueue();
		calculateTime(elements, type);
	}

	public static Queue queueMaker(String fileName, String type) throws FileNotFoundException {
		Queue elements = new Queue(50);
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
		} else if (type.trim().equalsIgnoreCase("priority")) {
			while (file.hasNext()) {
				int ID = file.nextInt();
				double burst = file.nextDouble();
				Process newProcess = new Process(ID, burst);
				elements.priorityEnqueue(newProcess);
			}
		} else
			throw new IllegalArgumentException("Invalid type choice.");
		return elements;
	}

	public static void calculateTime(Queue elements, String type){
		Process current;
		int clock=0;
		System.out.printf("\nID\tWait Time\tEnd Time\n");
		
		if(type.trim().equalsIgnoreCase("regular")){
			while(!elements.isEmpty()){
				current=elements.dequeue();
				if(current.getBurstRemain()<=(0.1)){
					current.setWaitTime(current.getWaitTime()+(clock-current.getEndTime()));
					clock=clock + (int) (1000*current.getBurstRemain());
					current.setEndTime(clock);
					current.setBurstRemain(0);
					System.out.printf("%d\t%f\t%f\n", current.getID(), current.getWaitTime(), current.getEndTime());
				} else {
					current.setWaitTime(current.getWaitTime()+(clock-current.getEndTime()));
					clock=clock + 100;
					current.setEndTime(clock);
					current.setBurstRemain(current.getBurstRemain()-.1);
					elements.enqueue(current);
				}
			}
	 
		} else if(type.trim().equalsIgnoreCase("priority")){
			while(!elements.isEmpty()){
				current=elements.dequeue();
				if(current.getBurstRemain()<=(0.1)){
					current.setWaitTime(current.getWaitTime()+(clock-current.getEndTime()));
					clock=clock + (int) (1000*current.getBurstRemain());
					current.setEndTime(clock);
					current.setBurstRemain(0);
					System.out.printf("%d\t%f\t%f\n", current.getID(), current.getWaitTime(), current.getEndTime());
				} else {
					current.setWaitTime(current.getWaitTime()+(clock-current.getEndTime()));
					clock=clock + 100;
					current.setEndTime(clock);
					current.setBurstRemain(current.getBurstRemain()-.1);
					elements.priorityEnqueue(current);
				}
			}
		} else
			throw new IllegalArgumentException("Invalid type choice.");
	}
}
