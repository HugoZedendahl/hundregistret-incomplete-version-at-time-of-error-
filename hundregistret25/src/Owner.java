import java.util.Comparator;

public class Owner {
    private static final int MAX_DOGS_ALLOWED = 7;
    private String name;
    private Dog[] ownedDogs = new Dog[MAX_DOGS_ALLOWED];
    private boolean dogSyncActive;

    private Owner(String inputName) {
        this.name = inputName.toUpperCase();
    }

    public Owner(String inputName, Dog... dogs) {
        this(inputName);
        for (Dog dog : dogs) {
            addDog(dog);
        }
    }

    public String getName() {
        StringFormatter formatter = new StringFormatter();
        return formatter.format(name);
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
        StringFormatter formatter = new StringFormatter();
        for (Dog dog : ownedDogs) {
            if (dog != null) {
                if (formatter.format(name).equals(dog.getName())) {
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
        if (dogSyncActive) {
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
        Dog[] outputArray = new Dog[MAX_DOGS_ALLOWED - counter];
        counter = 0;
        for (Dog dog : ownedDogs) {
            if (dog != null) {
                outputArray[counter] = dog;
                counter += 1;
            }
        }
        return DogSorter.sort(SortingAlgorithm.QUICK_SORT, Comparator.comparing(Dog::getName), outputArray);
    }

    public boolean removeDog(String dogName) {
        if (dogSyncActive) {
            return true;
        }
        StringFormatter formatter = new StringFormatter();
        dogSyncActive = true;
        for (int i = 0; i < ownedDogs.length; i++) {
            if (ownedDogs[i] != null)
                if (formatter.format(dogName).equals(ownedDogs[i].getName())) {
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
        if (dogSyncActive) {
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
}