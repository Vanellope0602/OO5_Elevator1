import com.oocourse.TimableOutput;
import com.oocourse.elevator1.PersonRequest;

//get requests and sign mission for elevator to RUN
// when there are more elevators, dispatcher should be efficient
public class Dispatch implements Runnable {
    private Elevator elevator;
    private Person person;

    private TaskQueue queue = new TaskQueue();

    public Dispatch(Elevator elevatorIn) {
        this.elevator = elevatorIn;
    }

    public void NewRequest(PersonRequest request) {
        queue.putIntoQueue(request); // add this request into queue.
    }

    /*开门 0.25s; 关门 0.25s; 走进出不花时间； 爬升或下降一层楼 0.5s */

    public synchronized void Work() throws Exception {
        // get taskes from queue
        PersonRequest request = queue.pushELm();
        person = new Person(request.getPersonId(),
                request.getFromFloor(), request.getToFloor());
        synchronized (elevator)
        {
            elevator.Run();
            int srcFloor = person.getSrcFloor();
            int lift = 0; // 计算爬升或下降楼层数，以便计算时间
            if (elevator.onWhichFloor() != srcFloor) { // 现在在人的始发楼层，直接接人
                lift = Math.abs(elevator.onWhichFloor() - srcFloor); // 间隔楼层

                Thread.sleep(lift * 500);
                //elevator.Stop();
                elevator.onThisFloor(srcFloor);
            }
            // 开门
            TimableOutput.println("OPEN-" + srcFloor);
            Thread.sleep(250);
            // 走进去
            TimableOutput.println("IN-" + person.getId() + "-" + srcFloor);
            // 关门
            Thread.sleep(250);
            TimableOutput.println("CLOSE-" + srcFloor);
            // 移动
            int dstFloor = person.getDstFloor();
            lift = Math.abs(srcFloor - dstFloor);
            elevator.Run();
            Thread.sleep(lift * 500);
            //elevator.Stop();
            // 开门
            TimableOutput.println("OPEN-" + dstFloor);
            Thread.sleep(250);
            // 走出来
            TimableOutput.println("OUT-" + person.getId() + "-" + dstFloor);
            // 关门
            Thread.sleep(250);
            TimableOutput.println("CLOSE-" + dstFloor);
            elevator.onThisFloor(dstFloor); // 改变电梯所在状态
            elevator.Stop();
            notifyAll();
        }
    }

    @Override
    public synchronized void run() {
        try {
            Work();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
