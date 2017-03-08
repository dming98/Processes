package dming98.Processes;

import java.io.*;
import java.util.*;

public class OSSimulator {
	public static void main(String[] args) throws FileNotFoundException{
		String fileName;
		Scanner console = new Scanner(System.in);
		
		System.out.print("Please input the name of the file:\t");
		fileName = console.next();
		Queue elements = fileReader(fileName);
		elements.printQueue();
	}
	
	public static Queue fileReader(String fileName) throws FileNotFoundException{
		Queue elements = new Queue();
		Scanner file = null;
		FileReader in = null;
		
		try{
			file = new Scanner(new FileReader(fileName));
		} catch (FileNotFoundException e){
			e = new FileNotFoundException("You have provided an invalid file name.");
			throw e;
		}
		
		while(file.hasNext()){
			int ID = file.nextInt();
			double burst = file.nextDouble();
			Process newProcess = new Process(ID,burst);
			elements.priorityEnqueue(newProcess);
		}
		return elements;
	}
}
