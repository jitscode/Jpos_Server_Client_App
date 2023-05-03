package com.example.server_app;

import org.jpos.iso.*;
import org.jpos.util.*;
import org.jpos.iso.channel.*;
import org.jpos.iso.packager.*;

import java.util.Date;

public class Client {
    public static void main(String[] args) throws Exception {

        DistributedSequence distributedSequence = new DistributedSequence();

        Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));
        ISOChannel channel = new ASCIIChannel("localhost",10000, new EuroPackager());
        ((LogSource) channel).setLogger(logger, "test-channel");
        channel.connect();
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.set(0, "0800");
        isoMsg.set(3, "389000");
        isoMsg.set(4, "000000000000");
        isoMsg.set(7, ISODate.formatDate(new Date(), "MMddHmss"));
        int stan = STANGenerator.generateSTAN();
        isoMsg.set(11, ISOUtil.zeropad(String.valueOf(stan), 6));
        channel.send(isoMsg);
        ISOMsg r = channel.receive();
        channel.disconnect();
    }
}


//        String ZK_ADDRESS = "35.223.9.119:2181";
//        int TIMEOUT = 5000;
//        String ZNODE_PATH = "/path/stan";
//        int BASE_SEQUENCE = 1000;
//            // Connect to ZooKeeper
//        ZooKeeper zooKeeper = new ZooKeeper(ZK_ADDRESS, TIMEOUT, new Watcher() {
//                public void process(WatchedEvent we) {
//                }});
//            // Check if the znode exists. If not, create it with the base sequence number.
//            if (zooKeeper.exists(ZNODE_PATH, false) == null) {
//                zooKeeper.create(ZNODE_PATH, String.valueOf(BASE_SEQUENCE).getBytes(),  ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
//            }
//            // Get the current sequence number and increment it using ZooKeeper's atomic increment feature.
//            byte[] sequenceBytes = zooKeeper.getData(ZNODE_PATH, true, null);
//            int sequence = Integer.parseInt(new String(sequenceBytes));
//            zooKeeper.setData(ZNODE_PATH, String.valueOf(sequence + 1).getBytes(), -1);


//      m.set(11, ISODate.formatDate(new Date(), "YYks"));
//        int stan=STANGenerator.generateSTAN();
//        m.set(11,ISOUtil.zeropad(String.valueOf(stan), 6));