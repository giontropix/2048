public class Box {
    private int value;
    private boolean alreadyAdded;

    public Box(int value, boolean alreadyAdded){
        this.value = value;
        this.alreadyAdded = alreadyAdded;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isAlreadyAdded() {
        return alreadyAdded;
    }

    public void setAlreadyAdded(boolean alreadyAdded) {
        this.alreadyAdded = alreadyAdded;
    }
}
