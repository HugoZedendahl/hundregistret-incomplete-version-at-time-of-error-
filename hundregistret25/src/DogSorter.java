import java.util.Comparator;

public class DogSorter {

    private DogSorter(){
        System.out.println("Error, do not instanciate this class");
    }
    public static Dog[] sort(SortingAlgorithm algorithm, Comparator<Dog> comparator, Dog[] dogs) {

        switch (algorithm) {
            case BUBBLE_SORT:
                bubbleSort(comparator, dogs);
                break;

            case QUICK_SORT:
                quickSort(comparator, dogs, 0, dogs.length - 1);
                break;
        }
        return dogs;
    }

    private static void bubbleSort(Comparator<Dog> comparator, Dog[] dogs) {
        int outerLoop;
        int innerLoop;
        Dog temp;
        boolean check;
        for (outerLoop = 0; outerLoop < dogs.length - 1; outerLoop++) {
            check = false;
            for (innerLoop = 0; innerLoop < dogs.length - outerLoop - 1; innerLoop++) {
                if (comparator.compare(dogs[innerLoop + 1], dogs[innerLoop]) < 0) {
                    temp = dogs[innerLoop];
                    dogs[innerLoop] = dogs[innerLoop + 1];
                    dogs[innerLoop + 1] = temp;
                    check = true;
                }
            }
            if (!check) {
                break;
            }
        }
    }

    private static void quickSort(Comparator<Dog> comparator, Dog[] dogs, int low, int high) {
        if (low < high) {
            int piv = quickPartition(comparator, dogs, low, high);
            quickSort(comparator, dogs, low, piv - 1);
            quickSort(comparator, dogs, piv + 1, high);
        }
    }

    private static int quickPartition(Comparator<Dog> comparator, Dog[] dogs, int low, int high) {
        Dog pivot = dogs[high];
        int i = low - 1;
        for (int x = low; x <= high - 1; x++) {
            if (comparator.compare(dogs[x], pivot) < 0) {
                i++;
                quickSwap(dogs, i, x);
            }
        }
        quickSwap(dogs, i + 1, high);
        return i + 1;
    }

    private static void quickSwap(Dog[] dogs, int x, int y) {
        Dog temp = dogs[x];
        dogs[x] = dogs[y];
        dogs[y] = temp;
    }

    /*
     * 
     * vvvvvv test code for sorting functions vvvvvvv
     * public static void main(String[] args) {
     * Dog[] testDogs= new Dog[7];
     * testDogs[0] = new Dog("ett", "två", 1, 1);
     * testDogs[1] = new Dog("ettt", "två", 1, 2);
     * testDogs[2] = new Dog("etttt", "två", 1, 3);
     * testDogs[3] = new Dog("ettttt", "två", 1, 4);
     * testDogs[4] = new Dog("etttttt", "två", 1, 5);
     * testDogs[5] = new Dog("ettttttt", "två", 1, 30);
     * testDogs[6] = new Dog("etttttttt", "två", 1, 0);
     * 
     * Owner owner = new Owner("test",testDogs);
     * sort(owner);
     * for (Dog dog : owner.getDogs()){
     * System.err.println(dog.getName()+dog.getTailLength());
     * }
     * 
     * }
     * /*
     * public static void main(String[] args) {
     * Dog[] testDogs= new Dog[7];
     * testDogs[0] = new Dog("ett", "två", 1, 1);
     * testDogs[1] = new Dog("ettt", "två", 10, 2);
     * testDogs[2] = new Dog("etttt", "två", 1, 3);
     * testDogs[3] = new Dog("ettttt", "två", 100, 4);
     * testDogs[4] = new Dog("etttttt", "två", 112, 5);
     * testDogs[5] = new Dog("ettttttt", "två", 12, 30);
     * testDogs[6] = new Dog("etttttttt", "två", 1, 0);
     * 
     * Owner owner = new Owner("test",testDogs);
     * bubbleSortDogsAge(owner);
     * for (Dog dog : owner.getDogs()){
     * System.err.println(dog.getName()+dog.getAge());
     * }
     * 
     * }
     * 
     */
    /*
     * owner.resetDogs();
     * for (i=0; i<sorter.length;i++){
     * owner.addDog(sorter[i]);
     * }
     * } else {
     * owner.resetDogs();
     * for (i=0; i<sorter.length;i++){
     * owner.addDog(sorter[sorter.length-(i+1)]);
     * }
     * }
     * Dog[] dogs = owner.getDogs();
     * 
     * static void sortTailLength(Dog[] dogs){
     * quickSortDogsTailLength(dogs, 0, owner.getDogs().length-1);
     * owner.resetDogs();
     * for (int i=0; i<dogs.length;i++){
     * owner.addDog(dogs[i]);
     * }
     * 
     * }
     * 
     * private static dataSelector selectedData;
     * 
     * private static double dataSelector (Dog dog){
     * switch (selectedData) {
     * case DOG_AGE:
     * return (double)dog.getAge();
     * case DOG_WEIGHT:
     * return (double)dog.getWeight();
     * case DOG_TAILLENGTH:
     * return dog.getTailLength();
     * default:
     * return dog.getTailLength();
     * }
     * }
     */
}
