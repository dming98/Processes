package dming98.Processes;

public class Queue {
	int maxSize;
	int currentSize;
	Node<Process> head, tail;

	/**
	 * Inner Node class handling the links of our Process queue.
	 */
	public class Node<Process> {
		protected Node<Process> next;
		protected Process item;

		/**
		 * Constructor for default Node.
		 */
		public Node() {
			this.item = null;
			this.next = null;
		}

		/**
		 * Constructor for itemized Node.
		 * 
		 * @param item
		 *            the Process to set
		 */
		public Node(Process item) {
			this.item = item;
			this.next = null;
		}

		/**
		 * Constructor for itemized and linked Node.
		 * 
		 * @param item
		 *            the Process to set
		 * @param next
		 *            Node to point to
		 */
		public Node(Process item, Node<Process> next) {
			this.item = item;
			this.next = next;
		}
	}

	/**
	 * Constructor for default Queue.
	 */
	public Queue() {
		this.maxSize = 50;
		currentSize = 0;
		head = null;
		tail = null;
	}

	/**
	 * Constructor for sized Queue.
	 * 
	 * @param maxSize
	 *            the max size to be set
	 * @throws IllegalArgumentException
	 *             for maxSize > 50
	 */
	public Queue(int maxSize) {
		if (maxSize <= 50) {
			this.maxSize = maxSize;
			currentSize = 0;
			head = null;
			tail = null;
		} else
			throw new IllegalArgumentException("The max size cannot be greater than 50.");
	}

	/**
	 * Getter for the current size.
	 * 
	 * @return the current size
	 */
	public int getCurrentSize() {
		return currentSize;
	}

	/**
	 * Enqueue a process into the queue based on ascending burst time.
	 * 
	 * @param newProcess
	 *            the process to be enqueued
	 */
	public void priorityEnqueue(Process newProcess) {
		if (isEmpty()) {
			head = new Node<Process>(newProcess);
			tail = head;
			currentSize++;
		} else if (!isFull()) {
			if (head.item.getBurstTime() > newProcess.getBurstTime()) {
				Node<Process> input = new Node<Process>(newProcess, head);
				head = input;
				currentSize++;
			} else if (tail.item.getBurstTime() < newProcess.getBurstTime()) {
				Node<Process> input = new Node<Process>(newProcess);
				tail.next = input;
				tail = input;
				currentSize++;
			} else {
				Node<Process> input = new Node<Process>(newProcess);
				Node<Process> prev = head;
				Node<Process> curr = head.next;
				while (curr != null) {
					if (curr.item.getBurstTime() > newProcess.getBurstTime()) {
						prev.next = input;
						input.next = curr;
						break;
					}
					prev = curr;
					curr = curr.next;
				}

				currentSize++;
			}
		} else
			throw new IndexOutOfBoundsException("The queue is full.");
	}

	/**
	 * Enqueue a process to the tail.
	 * 
	 * @param newProcess
	 *            the process to be enqueued
	 */
	public void enqueue(Process newProcess) {
		if (isEmpty()) {
			head = new Node<Process>(newProcess);
			tail = head;
			currentSize++;
		} else if (!isFull()) {
			Node<Process> input = new Node<Process>(newProcess);
			tail.next = input;
			tail = input;
			currentSize++;
		} else
			throw new IndexOutOfBoundsException("The queue is full.");
	}

	/**
	 * Dequeues a process from the head of the queue.
	 * 
	 * @return the process at the head of the queue
	 */
	public Process dequeue() {
		Process top = head.item;
		head = head.next;
		currentSize--;
		return top;
	}

	/**
	 * Checks to see if queue is full.
	 * 
	 * @return true if queue is full
	 */
	public boolean isFull() {
		return (currentSize == maxSize);
	}

	/**
	 * Checks to see if queue is empty.
	 * 
	 * @return true if queue is empty
	 */
	public boolean isEmpty() {
		return (currentSize == 0);
	}

	/**
	 * Prints out the contents of the queue.
	 */
	public void printQueue() {
		System.out.printf("ID\tBurst Time\n");
		Node<Process> current = head;
		while (current != null) {
			System.out.printf("%d\t%f\n", current.item.getID(), current.item.getBurstTime());
			current = current.next;
		}
	}
}
