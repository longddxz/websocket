package cn.lyj.springbootwebsocket.controller;

import cn.lyj.springbootwebsocket.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author:lyj
 * @Date 2020/7/9 0:29
 * Version 1.0
 */
@RestController
@RequestMapping(value = "sendMsgController")
public class SendMsgController {
    private int num=0;
    @GetMapping(value = "send")
    public String send(){
        String msg = ""+(num++);
        try {
            WebSocketServer.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "发送【"+msg+"】成功";
    }
}
