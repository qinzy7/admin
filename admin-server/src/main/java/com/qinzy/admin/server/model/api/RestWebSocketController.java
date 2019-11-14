package com.qinzy.admin.server.model.api;

import com.qinzy.admin.server.bean.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author kejiang
 * @since 2019-11-14
 */
@RestController
@RequestMapping("websocket")
@Slf4j
public class RestWebSocketController {

    @GetMapping("sendEveryone")
    public String sendMessage() {
        String msg;
        try {
            WebSocketServer.broadCastInfo("==========群发消息测试==========");
            msg = "发送成功";
        } catch (IOException e) {
            msg = "发送失败";
            log.error("群发消息异常：", e);
        }
        return msg;
    }

    @GetMapping("sendOne")
    public String sendMessageBySessionId(String sessionId) {
        String msg;
        try {
            WebSocketServer.sendMessage("==========单发消息测试==========", sessionId);
            msg = "发送成功";
        } catch (IOException e) {
            msg = "发送失败";
            log.error("群发消息异常：", e);
        }
        return msg;
    }
}