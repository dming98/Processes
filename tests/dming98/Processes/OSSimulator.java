package dming98.Processes;

import java.io.*;
import java.util.*;

public class OSSimulator {
	public static void main(String[] args) throws FileNotFoundException{
		String fileName;
		Scanner console = new Scanner(System.in);
		
		System.out.print("Please input the name of the file:  ");
		fileName = console.next();
		Queue elements = queueMaker(fileName);
		elements.printQueue();
	}
	
	public static Queue queueMaker(String fileName) throws FileNotFoundException{
		Queue elements = new Queue(50);
		Scanner file = null;
		FileReader in = null;
		Scanner console = new Scanner(System.in);
		
		try{
			file = new Scanner(new FileReader(fileName));
		} catch (FileNotFoundException e){
			throw new FileNotFoundException("You have provided an invalid file name.");
			
		}
		
		System.out.print("\nType regular for FIFO, or type priority for priority enqueue:   ");
		String type = console.next();
		if(type.trim().equalsIgnoreCase("regular")){
			while(file.hasNext()){
				int ID = file.nextInt();
				double burst = file.nextDouble();
				Process newProcess = new Process(ID,burst);
				elements.enqueue(newProcess);
			}
		} else if(type.trim().equalsIgnoreCase("priority")){
			while(file.hasNext()){
				int ID = file.nextInt();
				double burst = file.nextDouble();
				Process newProcess = new Process(ID,burst);
				elements.priorityEnqueue(newProcess);
			}
		} else
			throw new IllegalArgumentException("Invalid type choice.");
		return elements;
	}
}
