import java.util.Arrays;
import java.util.stream.Collectors;

public class Dog {
    private String dogName;
    private String dogBreed;
    private int dogAge;
    private double dogWeight;
    private Owner dogOwner;


    public Dog(String name, String breed, int age, double weight){
        if ( name == "" || name == null){
            throw new IllegalArgumentException("invalid dog name");
        }
        this.dogName = name;
        if (breed == "" || breed == null){
            throw new IllegalArgumentException("invalid dog breed");
        }
        this.dogBreed = breed;
        if (age < 0){
            throw new IllegalArgumentException("invalid dog age");
        }
        this.dogAge = age;
        if (weight < 0){
            throw new IllegalArgumentException("invalid dog weight");
        }
        this.dogWeight = weight;
    }
    public Dog(String name, String breed, int age, double weight, Owner owner){
        this(name, breed, age, weight);
        if (owner == null || owner.getName()==""){
            throw new IllegalArgumentException("invalid owner");
        }
        this.dogOwner = owner;
    }   

    private String stringFormatter(String input){
        return Arrays.stream(input.trim().toLowerCase().split("\\s+"))
                     .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                     .collect(Collectors.joining(" "));
    }
        // i found the method above at https://stackoverflow.com/questions/1892765/how-to-capitalize-the-first-character-of-each-word-in-a-string 
        // however i did modify it with .trim() and .toLowerCase() to suit requirements
    public String getName(){
        if (dogName.isEmpty()) {
        return null;
        }
        return stringFormatter(dogName);
    }
    public String getBreed(){
        if (dogBreed.isEmpty()) {
            return null;
        }
        return stringFormatter(dogBreed);
    }
    public int getWeight(){
        return (int)dogWeight;
    }
    public int getAge(){
        return dogAge;
    }
    public Double getTailLength(){
        return (dogAge-dogWeight)/10;
    }
    public void increaseDogAge(){
        dogAge +=1;
    }
    public boolean setOwner(Owner newOwner){
        if (newOwner == this.dogOwner){
            return false;
        } 
        if (newOwner == null){
            dogOwner = null;
            return true;
        }
        this.dogOwner = newOwner;
        return true;
    }
    public Owner getOwner(){
        if (this.dogOwner == null){
            return null;
        }
        else {
            return dogOwner;
        }
    }
}
