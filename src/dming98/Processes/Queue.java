package dming98.Processes;

public class Queue {
	int maxSize;
	int currentSize;
	Process[] elements;

	public Queue() {
		this.maxSize = 50;
		elements = new Process[50];
		currentSize = 0;
	}

	public Queue(int maxSize) {
		this.maxSize = maxSize;
		elements = new Process[maxSize];
		currentSize = 0;
	}

	public void priorityEnqueue(Process newProcess) {
		if(isEmpty()){
			elements[0]=newProcess;
		} else if (!isFull()) {
			for (int i = 0; i < currentSize; i++) {
				if (newProcess.getBurstTime() < elements[i].getBurstTime()) {
					for (int j = currentSize - 1; j > i; j--) {
						elements[j] = elements[j - 1];
					}
					elements[i] = newProcess;
					break;
				}
			}
			currentSize++;
		} else
			throw new IndexOutOfBoundsException("The queue is full.");
	}

	public void enqueue(Process newProcess) {
		if (!isFull()) {
			elements[currentSize] = newProcess;
			currentSize++;
		}
	}

	public Process dequeue() {
		Process top = elements[0];
		for(int i = 1;i<currentSize;i++){
			elements[i-1]=elements[i];
		}
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
	
	public void printQueue(){
		for(int i = 0; i<currentSize;i++){
			System.out.println(elements[i].getID() + "\t" + elements[i].getBurstTime());
		}
	}
}
