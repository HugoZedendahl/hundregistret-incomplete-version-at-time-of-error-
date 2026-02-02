import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class OwnerCollection {
    private ArrayList<Owner> owners = new ArrayList<>();

    public boolean addOwner(Owner owner) {
        if (!containsOwner(owner)) {
            return owners.add(owner);
        }
        return false;
    }

    public boolean removeOwner(String ownerName) {
        for (Owner oldOwner : owners) {
            if (oldOwner.getName().equals(stringFormatter(ownerName))) {
                return owners.remove(oldOwner);
            }
        }
        return false;
    }

    public boolean removeOwner(Owner owner) {
        return owners.remove(owner);
    }

    public boolean containsOwner(String ownerName) {
        return ownerFinder(ownerName) != null;
    }

    public boolean containsOwner(Owner owner) {
        return owners.contains(owner);
    }

    public Owner getOwner(String ownerName) {
        Owner owner = ownerFinder(ownerName);
        if (owner != null) {
            return owner;
        }
        return null;
    }

    public ArrayList<Owner> getAllOwners() {
        ArrayList<Owner> output = new ArrayList<>();
        output.addAll(owners);
        output.sort(Comparator.comparing(Owner::getName));
        return output;
    }

    public int size() {
        return owners.size();
    }

    private Owner ownerFinder(String ownerName) {
        for (Owner owner : owners) {
            if (owner.getName().equals(stringFormatter(ownerName))) {
                return owner;
            }
        }
        return null;
    }

    private String stringFormatter(String input) {
        return Arrays.stream(input.trim().toLowerCase().split("\\s+"))
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
    }
    // i found the method above at
    // https://stackoverflow.com/questions/1892765/how-to-capitalize-the-first-character-of-each-word-in-a-string
    // see futher description in dog.java
}
