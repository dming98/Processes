package dming98.Processes;

import java.util.LinkedList;

public class Queue {
	int maxSize;
	int currentSize;
	LinkedList<Process> elements = new LinkedList<Process>();

	public Queue() {
		this.maxSize = 50;
		currentSize = 0;
	}

	public Queue(int maxSize) {
		if (maxSize <= 50) {
			this.maxSize = maxSize;
			currentSize = 0;
		} else
			throw new IllegalArgumentException("The max size cannot be greater than 50.");
	}

	public int getCurrentSize(){
		return currentSize;
	}
	public void priorityEnqueue(Process newProcess) {
		if (isEmpty()) {
			elements.add(newProcess);
			currentSize++;
		} else if (!isFull()) {
			boolean changed = false;
			for (int i = 0; i < currentSize; i++) {
				Process current = elements.get(i);
				if ((newProcess.getBurstTime() < current.getBurstTime()) && !changed) {
					elements.add(i, newProcess);
					changed = true;
					currentSize++;
				}
			}
			if (!changed) {
				elements.add(currentSize, newProcess);
				changed = true;
				currentSize++;
			}
		} else
			throw new IndexOutOfBoundsException("The queue is full.");
	}

	public void enqueue(Process newProcess) {
		if (!isFull()) {
			elements.add(newProcess);
			currentSize++;
		}
	}

	public Process dequeue() {
		Process top = elements.remove();
		currentSize--;
		return top;
	}

	public boolean isFull() {
		if (currentSize == maxSize)
			return true;
		return false;
	}

	public boolean isEmpty() {
		if (currentSize == 0)
			return true;
		return false;
	}

	public void printQueue() {
		int i = 0;
		System.out.printf("ID\tBurst Time\n");
		while (i < currentSize) {
			Process current = elements.get(i);
			System.out.printf("%d\t%f\n", current.getID(), current.getBurstTime());
			i++;
		}
	}
}
