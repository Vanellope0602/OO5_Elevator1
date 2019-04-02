import com.oocourse.elevator1.ElevatorInput;
import com.oocourse.elevator1.PersonRequest;
import com.oocourse.TimableOutput;
//import java.util.Queue;
import java.util.Scanner;
//import java.util.concurrent.LinkedBlockingQueue;

public class MainClass {
    public static void main(String[] args) throws Exception {

        Scanner s = new Scanner(System.in);
        // 初始化时间戳 , please MUST initialize start timestamp at the beginning,
        TimableOutput.initStartTimestamp();

        // 新建 并初始化电梯, 新建并初始化调度器
        Elevator elevator = new Elevator(1);
        Runnable dispatch = new Dispatch(elevator);
        // 输出构造器
        ElevatorInput elevatorInput = new ElevatorInput(System.in);
        while (true) { // 录入人员移动需求
            // new request and parse it
            PersonRequest request = elevatorInput.nextPersonRequest();
            // it means there are no more lines in stdin
            if (request == null) { // "command + D" on Mac OS to terminate
                break;
            } else {
                // a new valid request
                ((Dispatch) dispatch).NewRequest(request);
                Thread t = new Thread(dispatch);
                t.start();
            }
            //t.wait();
            //dispatch.run();
            //System.out.println("Now queue has " + queue.getQueue());
        }
        elevatorInput.close();
        // 电梯请求输入完毕

    }
}
