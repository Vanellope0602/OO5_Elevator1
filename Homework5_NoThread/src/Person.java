public class Person {
    private int id;
    private int srcFloor;
    private int dstFloor;
    private boolean inElevator; // to mark person IN elevator or OUT

    public Person(int id, int src, int dst) {
        this.id = id;
        this.srcFloor = src;
        this.dstFloor = dst;
        this.inElevator = false;
        //System.out.println("This person's " + id
        //      + ": from " + src
        //    + " to " + dst);
    }

    public boolean isInElevator() {
        return inElevator;
    }

    public int getId() {
        return id;
    }

    public int getSrcFloor() {
        return srcFloor;
    }

    public int getDstFloor() {
        return dstFloor;
    }

    public void goIn() {
        this.inElevator = true;
    }

    public void goOut() {
        this.inElevator = false;
    }

}
