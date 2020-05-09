import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

/**
 * 生产者
 */
public class Producer {
    private final static String EXCHANGE_NAME = "direct_exchange_test";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        for (int i = 0; i < 100; i++) {
            // 消息内容
            String message = "Hello World! - " + i;
            channel.basicPublish(EXCHANGE_NAME, "test1", null, ("test1 - " + message).getBytes());
            channel.basicPublish(EXCHANGE_NAME, "test2", null, ("test2 - " + message).getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            Thread.sleep(1000);
        }
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
