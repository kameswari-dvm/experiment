package collectionsexamples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

public class ArrayListOfHashmap {

	public static void main(String[] args) {
		LinkedHashMap<String,String> hm = new LinkedHashMap<String,String>();
		hm.put("Title", "Car");
		hm.put("Location", "Bengaluru");
		hm.put("Price", "300k+");
		LinkedHashMap<String,LinkedHashMap<String,String>> hm1 = new LinkedHashMap<String,LinkedHashMap<String,String>>();
		hm1.put("Details", hm);
		System.out.println(hm1);

	}

}

/*ArrayList<LinkedHashMap<String,String>> al = new ArrayList<LinkedHashMap<String,String>>();
al.add(hm);
for(LinkedHashMap<String,String> details : al){
	Set<Entry<String, String>> set = details.entrySet();
	for(Entry<String,String> entry : set){
		System.out.println(entry.getKey()+"  "+entry.getValue());
	}
}*/