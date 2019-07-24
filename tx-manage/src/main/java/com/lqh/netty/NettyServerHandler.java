package com.lqh.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    private Map<String, List<String>> transactionTypeMap;
    private Map<String,Boolean> isEndMap;
    private Map<String,Integer> transactionCountMap;
    private List<Channel> channelGroup;


    @Override
    protected synchronized void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("接受数据:" + msg);

        JSONObject object = JSON.parseObject(msg);
        String command = object.getString("command");//create-创建事务组
        String groupId = object.getString("groupId");//事务组Id
        String transactionType = object.getString("transactionType");//子事务类型，commit/rollback
        Integer transactionCount = object.getInteger("transactionCount");//事务数量
        Boolean isEnd = object.getBoolean("isEnd");//是否是结束事务

        if ("create".equals(command)) {
            //创建事务组
            transactionTypeMap.put(groupId,new LinkedList<>());
        } else if ("add".equals(command)) {
            //加入事务组
            transactionTypeMap.get(groupId).add(transactionType);
            if (isEnd) {
                //结束事务
                isEndMap.put(groupId,true);
                transactionCountMap.put(groupId,transactionCount);
            }

            JSONObject result = new JSONObject();
            result.put("groupId",groupId);

            //如果已经接收到结束事务的标记，比较事务是否已经全部到达，如果全部到达则看是否需要回滚。
            if (isEndMap.get(groupId) && transactionCountMap.get(groupId).equals(transactionTypeMap.get(groupId).size())) {
                if (transactionTypeMap.get(groupId).contains("rollback")) {
                    result.put("command", "rollback");
                } else {
                    result.put("command","commit");
                }
                //TODO 发送 result
            }
        }


    }

    private void sendResult(JSONObject result) {
        for (Channel channel : channelGroup) {
            System.out.println("发送数据"+result.toJSONString());
            channel.writeAndFlush(result.toJSONString());
        }
    }
}
