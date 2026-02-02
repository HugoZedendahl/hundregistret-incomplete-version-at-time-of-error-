import java.util.Comparator;

public class TailNameComparator implements Comparator<Dog> {
    public int compare(Dog dogA, Dog dogB) {
        if (dogA.getTailLength() == dogB.getTailLength()) {
            return dogA.getName().compareTo(dogB.getName());
        } else if (dogA.getTailLength() < dogB.getTailLength()) {
            return -1;
        }
        return 1;
    }
}
