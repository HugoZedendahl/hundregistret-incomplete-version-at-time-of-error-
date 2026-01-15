import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
public class DogRegister {

	private static final String EXIT_COMMAND = "EXIT";
	private static final String ADD_OWNER = "ADD OWNER";
	private static final String REMOVE_OWNER = "REMOVE OWNER";
	private static final String ADD_DOG = "ADD DOG";
	private static final String REMOVE_DOG = "REMOVE DOG";
	private static final String CHANGE_OWNER = "CHANGE OWNER";
	private static final String LIST_OWNERS = "LIST OWNERS";
	private static final String LIST_DOGS = "LIST DOGS";
	private static final String INCREASE_DOGS_AGE = "INCREASE DOGS AGE";
	
	private static final int MAX_DOGS_ALLOWED=7;

	private OwnerCollection ownerCollection = new OwnerCollection();
	private InputReader input = new InputReader();

	public void start() {
		setUp();
		runCommandLoop();
		shutDown();
	}

	private void setUp() {
		System.out.println("welcome to the DogRegister!");
	}

	private void runCommandLoop() {
		String command;
		do {
			command = readCommand();
			executeCommand(command);
		} while (!command.equals(EXIT_COMMAND));
	}

	private String readCommand() {

		System.out.println("please choose a option, write the command to execute");
		System.out.println("1: Add Owner");
		System.out.println("2: remove Owner");
		System.out.println("3: Add dog");
		System.out.println("4: remove dog");
		System.out.println("5: change owner");
		System.out.println("6: list owners");
		System.out.println("7: list dogs");
		System.out.println("8: increase dogs age");
		System.out.println("9: exit");
		return input.scanString("choose alternative:").toUpperCase().trim();
	}

	private void executeCommand(String command) {
		switch (command) {
		case ADD_OWNER:
			addOwner();
			break;
		case REMOVE_OWNER:
			removeOwner();
			break;
		case ADD_DOG:
			addDog();
			break;
		case REMOVE_DOG:
			removeDog();
			break;
		case CHANGE_OWNER:
			changeOwner();
			break;
		case LIST_OWNERS:
			listOwners();
			break;
		case LIST_DOGS:
			listDogs();
			break;
		case INCREASE_DOGS_AGE:
			increaseAge();
			break;
		case EXIT_COMMAND:
			// Förklaring till varför detta case är tomt...
			break;
		default:
			printErrorMessage("Wrong command, try again!");
		}
	}

	private boolean addOwner(){
		Owner newOwner = new Owner(input.scanString("enter name"));
		if (hasOwner(newOwner)){
			printErrorMessage("Error: Owner already exists");
			return false;
		}
		ownerCollection.addOwner(newOwner);
		return true;
	}

	private boolean removeOwner(){
		if (ownerCollection.getAllOwners().size()==0){
			printErrorMessage("Error: No owners in the register");
			return false;
		}
		String name = input.scanString("Enter owners name to remove:");
		if (!hasOwner(name)){
			printErrorMessage("Error: owner name is incorrect, try again");
			return true;
		}
		ownerCollection.removeOwner(name);
		return true;

		
	}

	private boolean changeOwner(){
		if (ownerCollection.getAllOwners().size()<2||dogCollection().size()<=0){
			printErrorMessage("Error: Not enough owners or dogs in the register");
			return false;
		}
		String fromOwner = input.scanString("Enter current owners name to move dog FROM:");
		if (!hasOwner(fromOwner)){
			printErrorMessage("Error: owner name is incorrect, try again");
			return true;
		}
		if (ownerCollection.getOwner(fromOwner).getDogs().length==0) {
			printErrorMessage("Error: Selected owner owns no dogs");
			return false;
		}
		String dogName = input.scanString("enter dog name to move dog:");
		if(!ownerCollection.getOwner(fromOwner).ownsDog(dogName)){
			printErrorMessage("Error: this owner does not own any dog byt that name");
			return false;
		}
		String toOwner = input.scanString("Enter new owners name to move dog TO:");
		if (!hasOwner(toOwner)||toOwner.equals(fromOwner)){
			printErrorMessage("Error: owner name is incorrect, try again");
			return true;
		}
		
		return dogSetter(toOwner, fromOwner, dogName);

	}

	private boolean dogSetter(String  toOwner,String fromOwner, String dogName){
	    if (ownerCollection.getOwner(toOwner).ownsDog(dogName)||
		    ownerCollection.getOwner(toOwner).equals(ownerCollection.getOwner(fromOwner))||
		    ownerCollection.getOwner(toOwner).ownsDog(dogName)) {
		    printErrorMessage("Error: Illegal operation");
		    return false;
		}
		for (Dog dog : dogCollection()){
			if (dog.getName().equals(stringFormatter(dogName))){
				dog.setOwner(ownerCollection.getOwner(toOwner));
			}
		}
		return true;
	}
    
	private boolean listOwners(){
		if (ownerCollection.getAllOwners().size()==0){
			System.out.println("Error: no owners in registry");
			return false;
		}
		System.err.println("===");
		System.err.println("||\t\tOwners\t\t:\t\tDogs");
		String dogOutput;
		for (Owner owner : ownerCollection.getAllOwners()){
			dogOutput="";
			for (Dog dog :owner.getDogs()){
				dogOutput = dogOutput + "\t" +dog.getName();
			}
			System.out.println("||\t\t"+owner.getName()+"\t\t:"+dogOutput);
		}
		System.err.println("===");
		return true;
	}

	private boolean addDog(){
		if (ownerCollection.getAllOwners().size()==0){
			printErrorMessage("Error: No owners in the register");
			return false;
		}
		String ownerName = input.scanString("enter owners name to add dog:");
		if (!hasOwner(ownerName)){
			printErrorMessage("Error: owner name is incorrect, try again");
			return true;
		}
		if (ownerCollection.getOwner(ownerName).getDogs().length==MAX_DOGS_ALLOWED) {
			printErrorMessage("Error: owner already has the maximum of allowed dogs");
			return false;
		}
		ownerCollection.getOwner(ownerName).addDog(dogCreator());
		return true;

	}

	private boolean removeDog(){
		if (ownerCollection.getAllOwners().size()==0){
			printErrorMessage("Error: No owners in the register");
			return false;
		}
		String ownerName = input.scanString("enter owners name to remove dog:");
		if (!hasOwner(ownerName)){
			printErrorMessage("Error: owner name is incorrect");
			return true;
		}
		if(ownerCollection.getOwner(ownerName).getDogs().length==0){
			printErrorMessage("Error: Owner has no dogs");
			return false;
		}
		String dogName = input.scanString("enter dog name to remove dog:");
		if (!ownerCollection.getOwner(ownerName).removeDog(dogName)){
			printErrorMessage("Error: Dog name is incorrect");
			return false;
		}
		return true;
		
	}

	private boolean listDogs(){
		if (dogCollection().size()==0){
			System.err.println("Error: no dogs to list");
			return false;
		}
		double minimumTailLength = input.scanDouble("enter minimum tail length");
		Dog[] dogs = new Dog[dogCollection().size()];
		for (int i = 0;i<dogs.length;i++){
			dogs[i] = dogCollection().get(i);
		}
		TailNameComparator myComparator = new TailNameComparator();
		DogSorter.sort(SortingAlgorithm.QUICK_SORT, myComparator , dogs);
		if (!printList(dogs, minimumTailLength)) {
			printErrorMessage("Error: no dogs with tails that long in register!");
		}
		return true;
	}

	private boolean printList(Dog[] dogs, double minimumTailLength){
	    boolean hasPrinted=false;
	    System.out.println("Dogs by tail length");
		System.out.println("===========================================================");
		System.out.println("Dog name\t|\tTail length\t|\tOwner");
		System.out.println("===========================================================");
	    for (Dog dog : dogs){
			if (dog.getTailLength()>=minimumTailLength) {
				System.out.println(dog.getName()+"\t\t|\t"+dog.getTailLength()+"\t\t|\t"+dog.getOwner().getName());
				hasPrinted = true;
			}
		}
		return hasPrinted;
	}

	private boolean increaseAge(){
		if (dogCollection().size()==0) {
			printErrorMessage("Error: no dogs in register");
			return false;
		}
		for(Dog dog : dogCollection()){
			dog.updateAge(1);
		}
		return true;
	}

	private boolean hasOwner(Owner owner){
		if (ownerCollection.getOwner(owner.getName()) != null){
			return true;
		}
		return false;
	}

	private boolean hasOwner(String owner){
		if (ownerCollection.getOwner(owner) != null){
			return true;
		}
		return false;
	}

	private Dog dogCreator(){
		Dog newDog = new Dog(input.scanString("enter dog name:")
										,input.scanString("enter dog breed:")
										,input.scanInt("enter dog age:")
										,input.scanDouble("enter dog weight:"));
		return newDog;
	}

	private String stringFormatter(String input) {
        return Arrays.stream(input.trim().toLowerCase().split("\\s+"))
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
    }
    // i found the method above at
    // https://stackoverflow.com/questions/1892765/how-to-capitalize-the-first-character-of-each-word-in-a-string
    // see futher description in dog.java

	private ArrayList<Dog> dogCollection(){
		ArrayList<Dog> dogCollection = new ArrayList<>();
		for (Owner owner: ownerCollection.getAllOwners()){
			for (Dog dog : owner.getDogs()){
			dogCollection.add(dog);
			}
		}
		return dogCollection;
	}
	
	private void printErrorMessage(String msg) {
		System.out.println(msg);
	}

	private void shutDown() {
		System.out.println("thank you for using the dog register!");
	}


	public static void main(String[] args) {
		new DogRegister().start();
	}
}