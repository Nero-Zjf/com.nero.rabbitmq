import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

/**
 * 生产者
 */
public class Producer {
    private final static String QUEUE_NAME = "test";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 消息内容
        String message1 = "Hello World1!";
        String message2 = "Hello World2!";
        channel.basicPublish("", QUEUE_NAME, null, message1.getBytes());//不需要使用交换机，直接将消息发送到指定队列
        channel.basicPublish("", QUEUE_NAME, null, message2.getBytes());//不需要使用交换机，直接将消息发送到指定队列
        System.out.println(" [x] Sent '" + message1 + "'");
        System.out.println(" [x] Sent '" + message2 + "'");
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
