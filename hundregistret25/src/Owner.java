import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Comparator;

public class Owner {
    private String name;
    private Dog[] ownedDogs = new Dog[7];
    private boolean dogSyncActive = false;

    public Owner(String inputName) {
        this.name = inputName.toUpperCase();
    }

    public Owner(String inputName, Dog... dogs) {
        this(inputName);
        for (Dog dog : dogs) {
            addDog(dog);
        }
    }

    private String stringFormatter(String input) {
        return Arrays.stream(input.trim().toLowerCase().split("\\s+"))
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
    }
    // i found the method above at
    // https://stackoverflow.com/questions/1892765/how-to-capitalize-the-first-character-of-each-word-in-a-string
    // see futher description in dog.java

    public String getName() {
        return stringFormatter(name);
    }

    public String toString() {
        String dogs = "";
        for (Dog dog : ownedDogs) {
            if (dog != null) {
                dogs = dogs + " : " + dog.getName();
            }
        }
        return name + dogs;
    }

    public boolean ownsAnyDog() {
        for (Dog dog : ownedDogs) {
            if (dog != null) {
                return true;
            }
        }
        return false;
    }

    public boolean ownsMaxDogs() {
        for (int i = 0; i < ownedDogs.length; i++) {
            if (ownedDogs[i] == null) {
                return false;
            }
        }
        return true;
    }

    public boolean ownsDog(String name) {
        for (Dog dog : ownedDogs) {
            if (dog != null) {
                if (stringFormatter(name).equals(dog.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean ownsDog(Dog checkDog) {
        for (Dog dog : ownedDogs) {
            if (dog == checkDog) {
                return true;
            }
        }
        return false;
    }

    public boolean addDog(Dog dog) {
        if (dogSyncActive == true) {
            return true;
        }
        dogSyncActive = true;
        for (Dog ownedDog : this.getDogs()) {
            if (ownedDog == dog || ownedDog.getName().equals(dog.getName())) {
                dogSyncActive = false;
                return false;
            }
        }

        dogSyncActive = true;
        dog.setOwner(this);
        if (ownsDog(dog.getName())) {
            return false;
        }
        for (int i = 0; i < ownedDogs.length; i++) {
            if (ownedDogs[i] == null) {
                ownedDogs[i] = dog;
                ownedDogs[i].setOwner(this);
                dogSyncActive = false;
                return true;
            }
        }
        dogSyncActive = false;
        return false;
    }

    public Dog[] getDogs() {
        int counter = 0;
        for (Dog dog : ownedDogs) {
            if (dog == null) {
                counter += 1;
            }
        }
        Dog[] outputArray = new Dog[7 - counter];
        counter = 0;
        for (Dog dog : ownedDogs) {
            if (dog != null) {
                outputArray[counter] = dog;
                counter += 1;
            }
        }
        return DogSorter.sort(SortingAlgorithm.QUICK_SORT, Comparator.comparing(Dog::getName),outputArray);
    }

    public boolean removeDog(String dogName) {
        if (dogSyncActive == true) {
            return true;
        }
        dogSyncActive = true;
        for (int i = 0; i < ownedDogs.length; i++) {
            if (ownedDogs[i] != null)
                if (stringFormatter(dogName).equals(ownedDogs[i].getName())) {
                    this.ownedDogs[i].setOwner(null);
                    ownedDogs[i] = null;
                    dogSyncActive = false;
                    return true;
                }
        }
        dogSyncActive = false;
        return false;
    }

    public boolean removeDog(Dog checkDog) {
        if (dogSyncActive == true) {
            return true;
        }
        dogSyncActive = true;
        for (int i = 0; i < this.ownedDogs.length; i++) {
            if (checkDog == this.ownedDogs[i]) {
                this.ownedDogs[i].setOwner(null);
                ownedDogs[i] = null;
                dogSyncActive = false;
                return true;
            }
        }
        dogSyncActive = false;
        return false;
    }

    public void resetDogs() {
        this.ownedDogs = new Dog[7];
    }
}