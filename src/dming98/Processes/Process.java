package dming98.Processes;

public class Process{
	private	int ID;
	private double burstTime;
	private double burstRemain;
	private double waitTime;
	private double endTime;
	
	public Process(){
		this.ID=0;
		this.burstTime=0;
		this.endTime=0;
	}
	/**
	 * Getter for the process ID.
	 * 
	 * @param ID the ID to set
	 * @param burstTime the burstTime to set
	 */
	public Process(int ID, double burstTime){
		this.ID=ID;
		this.burstTime=burstTime;
		this.endTime=0;
		this.burstRemain=burstTime;
	}

	/**
	 * Getter for the process ID.
	 * 
	 * @return the ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Setter for the process ID.
	 * 
	 * @param ID the ID to set
	 */
	public void setID(int iD) {
		this.ID = ID;
	}

	/**
	 * Getter for the process burst time.
	 * 
	 * @return the burstTime
	 */
	public double getBurstTime() {
		return burstTime;
	}

	/**
	 * Setter for the process burst time.
	 * 
	 * @param burstTime the burstTime to set
	 */
	public void setBurstTime(double burstTime) {
		this.burstTime = burstTime;
	}
	/**
	 * Getter for the process burst remaining.
	 * 
	 * @return the burst time remaining
	 */
	public double getBurstRemain() {
		return burstRemain;
	}

	/**
	 * Setter for the process burst remaining.
	 * 
	 * @param burstTime the burstRemain to set
	 */
	public void setBurstRemain(double burstRemain) {
		this.burstRemain = burstRemain;
	}
	/**
	 * Getter for the process wait time.
	 * 
	 * @return the waitTime
	 */
	public double getWaitTime() {
		return waitTime;
	}

	/**
	 * Setter for the process wait time.
	 * 
	 * @param waitTime the waitTime to set
	 */
	public void setWaitTime(double waitTime) {
		this.waitTime = waitTime;
	}

	/**
	 * Getter for the process end time.
	 * 
	 * @return the endTime
	 */
	public double getEndTime() {
		return endTime;
	}

	/**
	 * Setter for the process end time.
	 * 
	 * @param endTime the endTime to set
	 */
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}
	
	
}
