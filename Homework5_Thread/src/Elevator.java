public class Elevator {
    private int floor;
    private boolean running = false; // 判断是否在上下移动的状态
    private boolean open = false; // initialized
    private boolean closed = true; //

    public Elevator(int initFloor) {
        floor = initFloor;
    }

    public int onWhichFloor() {
        return floor;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isClosed() {
        return closed;
    }

    public void onThisFloor(int floorIn) {
        floor = floorIn;
    }

    public void Run() {
        running = true;
    }

    public void Stop() {
        running = false;
    }

}
