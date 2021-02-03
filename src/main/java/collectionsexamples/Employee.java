package collectionsexamples;

public class Employee implements Comparable<Employee> {
	int eid;
	String ename;

	public Employee(int eid, String ename) {
		super();
		this.eid = eid;
		this.ename = ename;
	}

	//Comparing int data
	/*public int compareTo(Employee o) {
		if (eid == o.eid)
			return 0;
		else if (eid > o.eid)
			return 1;
		else
			return -1;
	}*/
	
	//Comparing String data
	
	public int compareTo(Employee o) {
		return ename.compareTo(o.ename);
	}

}
