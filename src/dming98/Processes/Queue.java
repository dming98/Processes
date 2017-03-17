package dming98.Processes;

import java.util.LinkedList;

public class Queue {
	int maxSize;
	int currentSize;

	public class Node {
		Process item;
		Node next;

		public Node(Process value) {
			item = value;
		}

		public Node(Process value, Node next) {
			item = value;
			this.next = next;
		}
	}

	private Node head;
	private Node tail;

	public Queue() {
		this.maxSize = 50;
		currentSize = 0;
		head = null;
		tail = null;
	}

	public Queue(int maxSize) {
		if (maxSize <= 50) {
			this.maxSize = maxSize;
			currentSize = 0;
			head = null;
			tail = null;
		} else
			throw new IllegalArgumentException("The max size cannot be greater than 50.");
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public void priorityEnqueue(Process newProcess) {
		Node input = new Node(newProcess);
		if (isEmpty()) {
			head = input;
			tail = input;
			currentSize++;
		} else if (!isFull()) {
			if (head.item.getBurstTime() > input.item.getBurstTime()) {
				input.next = head;
				head = input;
				currentSize++;
			} else if (tail.item.getBurstTime() < input.item.getBurstTime()) {
				tail.next = input;
				tail = input;
				currentSize++;
			} else {
				Node curr = head.next;
				Node prev = head;
				while (curr.next != null) {
					if (curr.item.getBurstTime() > input.item.getBurstTime()) {
						input.next = curr;
						prev.next = input;
						currentSize++;
					}
					curr = curr.next;
					prev = prev.next;

				}
			}
		} else
			throw new IndexOutOfBoundsException("The queue is full.");
	}

	public void enqueue(Process newProcess) {
		Node input = new Node(newProcess);
		if (isEmpty()) {
			head = input;
			tail = input;
			currentSize++;
		} else if (!isFull()) {
			tail.next = input;
			tail = input;
			currentSize++;
		} else
			throw new IndexOutOfBoundsException("The queue is full.");
	}

	public Process dequeue() {
		Process begin = null;
		if (!isEmpty()) {
			begin = head.item;
			head = head.next;
			currentSize--;
			if (currentSize == 0) {
				tail = null;
			}
		} else
			throw new IndexOutOfBoundsException("The queue is empty.");
		return begin;
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
		System.out.printf("ID\tBurst Time\n");
		Node curr = head;
		while (curr.next != null) {
			Process current = curr.item;
			System.out.printf("%d\t%f\n", current.getID(), current.getBurstTime());
			curr = curr.next;
		}
	}
}
