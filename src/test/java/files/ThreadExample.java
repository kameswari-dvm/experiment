package files;

public class ThreadExample {

	public static void main(String[] args) {
		MyThread t1 = new MyThread(0,1000);
		t1.start();
		new MyThread(1001,2000).start();
	}

}

class MyThread extends Thread {
	int start,end;

	public MyThread(int start,int end) {
		this.start = start;
		this.end = end;
	}

	public void run() {

		System.out.println("Limit "+start+","+end);
	}
}
