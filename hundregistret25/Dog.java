import java.util.Arrays;
import java.util.stream.Collectors;

public class Dog {
    
    private static final double SPECIAL_BREED_TAIL = 3.7;
    
    private String dogName;
    private String dogBreed;
    private int dogAge;
    private double dogWeight;
    private Owner dogOwner;
    private boolean dogSyncActive;
    

    public Dog(String name, String breed, int age, double weight) {
        if (name.equals("") || name == null) {
            throw new IllegalArgumentException("invalid dog name");
        }
        this.dogName = name.toUpperCase();
        if (breed.equals("") || breed == null) {
            throw new IllegalArgumentException("invalid dog breed");
        }
        this.dogBreed = breed.toUpperCase();
        if (age < 0) {
            throw new IllegalArgumentException("invalid dog age");
        }
        this.dogAge = age;
        if (weight < 0) {
            throw new IllegalArgumentException("invalid dog weight");
        }
        this.dogWeight = weight;
    }

    public Dog(String name, String breed, int age, double weight, Owner owner) {
        this(name, breed, age, weight);
        if (owner == null || owner.getName().equals("")) {
            throw new IllegalArgumentException("invalid owner");
        }
        this.dogOwner = owner;
    }

    private String stringFormatter(String input) {
        return Arrays.stream(input.trim().toLowerCase().split("\\s+"))
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
    }

    // i found the method above at
    // https://stackoverflow.com/questions/1892765/how-to-capitalize-the-first-character-of-each-word-in-a-string
    // the arrays.stream splitting the strin into smaller strings using the "\\s+"
    // regex i found there i did modify it with .trim() and .toLowerCase() to suit
    // requirements
    // the map functions + merges the capitalized first character together with the
    // rest of the substring
    // the collect functions groups all strings back into a singular, properly
    // formatted string.

    public String getName() {
        if (dogName.isEmpty()) {
            return null;
        }
        return stringFormatter(dogName);
    }

    public String getBreed() {
        if (dogBreed.isEmpty()) {
            return null;
        }
        return stringFormatter(dogBreed);
    }

    public int getWeight() {
        return (int) dogWeight;
    }

    public int getAge() {
        return dogAge;
    }

    public double getTailLength() {
        if (dogBreed.equalsIgnoreCase("dachshund") || dogBreed.equalsIgnoreCase("tax")) {
            return SPECIAL_BREED_TAIL;
        }
        return (double) dogAge * dogWeight / 10;
    }

    public String toString() {
        if (dogOwner != null) {
            return (String) (dogName + " : " + dogBreed + " : " + dogAge + " : " + String.valueOf(dogWeight) + " : "
                    + getTailLength() + " : " + dogOwner.getName());
        }
        return (String) (dogName + " : " + dogBreed + " : " + dogAge + " : " + String.valueOf(dogWeight) + " : "
                + getTailLength());
    }

    public void updateAge(int age) {
        if (age > 0) {
            if (dogAge + age < 0) {
                dogAge = Integer.MAX_VALUE;
                return;
            }
            this.dogAge += age;
        }
    }

    public boolean setOwner(Owner newOwner) {
        if (this.dogSyncActive) {
            return true;
        }
        this.dogSyncActive = true;

        if (newOwner == this.dogOwner) {
            this.dogSyncActive = false;
            return false;
        }
        if (newOwner == null) {
            this.getOwner().removeDog(this);
            dogOwner = null;
            this.dogSyncActive = false;
            return true;
        }
        if (!syncChecker(newOwner)) {
            if (!newOwner.addDog(this)) {
                throw new IllegalStateException("owner already has max dogs");
            }
        }
        if (this.dogOwner != null) {
            this.dogOwner.removeDog(this);
        }
        this.dogOwner = newOwner;
        dogOwner.addDog(this);
        this.dogSyncActive = false;
        return true;
    }
    private boolean syncChecker(Owner owner){
        Dog[] sync = owner.getDogs();
        for (Dog dog : sync) {
            if (dog.getName().equals(stringFormatter(this.dogName))) {
                return true;
            }
        }
        return false;
    }

    public Owner getOwner() {
        if (this.dogOwner == null) {
            return null;
        } else {
            return dogOwner;
        }
    }
}
