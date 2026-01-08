import java.util.Arrays;
import java.util.stream.Collectors;

public class Owner {
    private String name;
    private Dog[] ownedDogs = new Dog[7];
    public Owner(String inputName){
        this.name = inputName.toUpperCase();
    }
    public Owner(String inputName, Dog... dogs){
        this(inputName);
        for (Dog dog:dogs){
            addDog(dog);
        }


    }

    static String stringFormatter(String input){
        return Arrays.stream(input.trim().toLowerCase().split("\\s+"))
                     .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                     .collect(Collectors.joining(" "));
    }
            // i found the method above at https://stackoverflow.com/questions/1892765/how-to-capitalize-the-first-character-of-each-word-in-a-string 
            // however i did modify it with .trim() and .toLowerCase() to suit requirements
            // it functions by first trimming and setting the string to lowercase. it splits 
    public String getName(){
        return stringFormatter(name);
        }

    public String toString(){
        return  name;
    }
    public boolean ownsAnyDog(){
        for (Dog dog:ownedDogs){
            if (dog != null){
                return true;
            }
        }
        return false;
    }
    public boolean ownsMaxDogs(){
        for (int i=0;i<ownedDogs.length;i++){
            if (ownedDogs[i] == null){
                return false;
            }
        }
        return true;
    }

    public boolean ownsDog(String name){
        for (Dog dog: ownedDogs){
            if(dog!=null){
                if(stringFormatter(name).equals(dog.getName())){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean ownsDog(Dog checkDog){
        for (Dog dog: ownedDogs){
            if(dog==checkDog){
                return true;
            }
        }
        return false;
    }
    public boolean addDog(Dog dog){
        for (Dog ownedDog : ownedDogs){
            if (ownedDog == dog){
            return false;
            }
        }
        if (ownsDog(dog.getName())) {
            return false;
        }
        for (int i=0; i<ownedDogs.length;i++){
            if (ownedDogs[i]==null){
            ownedDogs[i] = dog;
            return true;
            }
        }
        return false;
    }
    public Dog[] getDogs(){
        int counter = 0;
        for (Dog dog : ownedDogs){
            if (dog==null){
                counter+=1;
            }
        }
        Dog[] outputArray = new Dog[7-counter];
        counter = 0;
        for (Dog dog : ownedDogs){
            if (dog!=null){
                outputArray[counter] = dog;
                counter+=1;
            }
        }
        return outputArray;
    }
    public boolean removeDog(String dogName){
        for (int i=0; i<ownedDogs.length;i++){
            if(ownedDogs[i]!=null)
                if(stringFormatter(dogName).equals(ownedDogs[i].getName())){
                    ownedDogs[i] = null;
                    return true;
                }
        }
        return false;
    }
    public boolean removeDog(Dog checkDog){
        for (int i=0; i<ownedDogs.length;i++){
            if(checkDog==ownedDogs[i]){
                ownedDogs[i] = null;
                return true;
            }
        }
        return false;
    }
}
