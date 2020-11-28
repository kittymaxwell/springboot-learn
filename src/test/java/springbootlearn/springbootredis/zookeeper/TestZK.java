package springbootlearn.springbootredis.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import springbootlearn.springbootredis.zookeeper.ZKOperaDemo;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestZK {

    //1.测试连接是否成功
    @Test
    public void testConnection() throws IOException, InterruptedException {
        ZKOperaDemo zkOperaDemo = new ZKOperaDemo();
        ZooKeeper zooKeeper = zkOperaDemo.connectionZooKeeper();
        System.out.println("====================");
        System.out.println(zooKeeper);
        System.out.println("====================");
        Thread.sleep(Long.MAX_VALUE);
    }
    private ZKOperaDemo nodeOperation = new ZKOperaDemo();
    private ZooKeeper zooKeeper = null;
    {
        try {
            zooKeeper = nodeOperation.connectionZooKeeper();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //测试创建节点
    @Test
    public void testCreateZKNode() throws KeeperException, InterruptedException {
        String result = nodeOperation.createZKNode(zooKeeper, "/address", "ShenZhen");
        System.out.println(result);
    }
    //测试获取节点数据
    @Test
    public void testGetZKNodeData() throws KeeperException, InterruptedException {
        String result = nodeOperation.getZKNodeData(zooKeeper, "/address");
        System.out.println(result);
    }
    //测试设置节点数据
    @Test
    public void testSetZKNodeData() throws KeeperException, InterruptedException {
        Stat stat = nodeOperation.setZKNodeData(zooKeeper, "/address", "Shen Zhen update");
        System.out.println(stat);    //结果是二进制数据
        if(null != null)
            System.out.println(stat.getCversion());
    }
    //测试节点是否存在
    @Test
    public void testIsExitZKPath() throws KeeperException, InterruptedException {
        Stat stat = nodeOperation.isExitZKPath(zooKeeper, "/addressaa");
        System.out.println(stat);    //结果是二进制数据  如果节点不存在，则返回null
        if(null != null)
            System.out.println(stat.getCversion());
    }

    @Test
    public void testGetZKNodeData2() throws KeeperException, InterruptedException {
        nodeOperation.getZKNodeData2(zooKeeper, "/address");
    }
}
