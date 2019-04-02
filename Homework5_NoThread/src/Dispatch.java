import com.oocourse.TimableOutput;

//get requests and sign mission for elevator to RUN
// when there are more elevators, dispatcher should be efficient
public class Dispatch extends Thread {
    private Elevator elevator;
    private Person person;

    public Dispatch(Elevator elevatorIn, Person personIn) {
        this.person = personIn;
        this.elevator = elevatorIn;
    }
    /*开门 0.25s; 关门 0.25s; 走进出不花时间； 爬升或下降一层楼 0.5s */

    public void Work() throws Exception {
        //TimableOutput.initStartTimestamp();
        int srcFloor = person.getSrcFloor();
        int lift = 0; // 计算爬升或下降楼层数，以便计算时间
        if (elevator.onWhichFloor() != srcFloor) { // 现在不在人的始发楼层
            lift = Math.abs(elevator.onWhichFloor() - srcFloor); // 间隔楼层
            elevator.Run();
            Thread.sleep(lift * 500);
            elevator.Stop();
            elevator.onThisFloor(srcFloor);
        } // 现在已经到了该人的始发楼层，准备接人
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
        elevator.Stop();
        // 开门
        TimableOutput.println("OPEN-" + dstFloor);
        Thread.sleep(250);
        // 走出来
        TimableOutput.println("OUT-" + person.getId() + "-" + dstFloor);
        // 关门
        Thread.sleep(250);
        TimableOutput.println("CLOSE-" + dstFloor);
        elevator.onThisFloor(dstFloor); // 改变电梯所在状态
    }

}
