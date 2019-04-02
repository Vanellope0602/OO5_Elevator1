import com.oocourse.elevator1.PersonRequest;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskQueue {
    // 测评指令不会超过 30 条，队列开够人员请求的容量即可
    private Queue<PersonRequest> queue = new LinkedBlockingQueue<>(100);

    public void putIntoQueue(PersonRequest a) {
        queue.add(a);
    }

    public Queue<PersonRequest> getQueue() {
        return queue;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean isFull() {
        return (queue.size() >= 100);
    }
}
