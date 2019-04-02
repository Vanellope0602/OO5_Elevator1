import com.oocourse.elevator1.ElevatorInput;
import com.oocourse.elevator1.PersonRequest;
import com.oocourse.TimableOutput;
//import java.util.Queue;
import java.util.Scanner;
//import java.util.concurrent.LinkedBlockingQueue;

public class MainClass {
    public static void main(String[] args) throws Exception {
        TaskQueue queue = new TaskQueue();
        Scanner s = new Scanner(System.in);
        // 初始化时间戳 , please MUST initialize start timestamp at the beginning,
        TimableOutput.initStartTimestamp();

        // 新建 并初始化电梯
        Elevator elevator = new Elevator(1);

        ElevatorInput elevatorInput = new ElevatorInput(System.in);
        while (true) { // 录入人员移动需求
            // new request and parse it
            PersonRequest request = elevatorInput.nextPersonRequest();
            // it means there are no more lines in stdin
            if (request == null) { // "command + D" on Mac OS to terminate
                break;
            } else {
                // a new valid request
                //System.out.println(request);
                queue.putIntoQueue(request); // add this request into queue.
            }
            // 这样取用人员信息，在request类当中都写好了
            Person man = new Person(request.getPersonId(),
                    request.getFromFloor(), request.getToFloor());
            Dispatch dispatch = new Dispatch(elevator,man);
            dispatch.Work();
            //System.out.println("Now queue has " + queue.getQueue());
        }
        elevatorInput.close();
        // 电梯请求输入完毕
        /*
        // sleep for a while, then print something again
        Thread.sleep(1000);
        TimableOutput.println(
                String.format("result of 2 / 7 is %.10f", 2.0 / 7));

        // sleep for a while, then print something again
        Thread.sleep(3000);
        TimableOutput.println(
                String.format("result of 3 / 7 is %.10f", 3.0 / 7));
        TimableOutput.println(
                String.format("result of 3 / 8 is %.10f", 3.0 / 8));
        TimableOutput.println(
                String.format("result of 3 / 8 is %.10f", 3.0 / 9));
        */
    }
}
