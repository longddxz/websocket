package cn.lyj.springbootwebsocket.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:lyj
 * @Date 2020/7/9 0:33
 * Version 1.0
 */
@ServerEndpoint(value = "/websocket/server")
@Component
public class WebSocketServer {
    private static ConcurrentHashMap<String, WebSocketServer> currSession = new ConcurrentHashMap<>();
    private Session session;
    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;
        currSession.put("lyj", this);
        System.out.println("open");
    }

    //发送信息
    @OnMessage
    public String onMessage(String message) {
        String msg = "onMessage->"+message;
        System.out.println(msg);
        return msg;
    }

//    @Message(maxMessageSize=6)
//    public void receiveMessage(String s) {
//        System.out.println("receiveMessage->"+s+";"+(num++));
//    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("onError->");
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println("onClose->");
    }

    /**
     * 实现服务器主动推送
     */
    public static void sendMessage(String message) throws IOException {
        currSession.get("lyj").session.getBasicRemote().sendText(message);
    }
}
