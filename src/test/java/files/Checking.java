package files;

class Animal {
	void printMsg() {
		System.out.println("I am Animal, I am the super class for all Animals");
	}
	void eat() {
		System.out.println("eating");
	}
}

class Tiger extends Animal {
	void printMsg() {
		System.out.println("I am tiger");
	}
	
}
 class AnimalTrainer {
    public void teach(Animal anim) {
        anim.printMsg();
        anim.eat();
    }
}
public class Checking {

	public static void main(String[] args) {

		Tiger tiger = new Tiger();
		
		AnimalTrainer trainer = new AnimalTrainer();
		trainer.teach(tiger);
	}

}
