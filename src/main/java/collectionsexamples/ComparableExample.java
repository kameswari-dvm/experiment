package collectionsexamples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import collectionsexamples.Employee;

public class ComparableExample {

	public static void main(String[] args) {
		ArrayList<Employee> al = new ArrayList<Employee>();
		al.add(new Employee(387, "Amrutha"));
		al.add(new Employee(231, "Abhinarayan"));
		al.add(new Employee(214, "Akhil"));
		System.out.println("Before Sorting : ");
		printArrayList(al);
		System.out.println("---------------------");
		Collections.sort(al);
		System.out.println("After Sorting : ");
		printArrayList(al);
	}

	public static void printArrayList(ArrayList<Employee> al) {
		for (Employee e : al)
			System.out.println(e.eid + "->" + e.ename);
	}

}
