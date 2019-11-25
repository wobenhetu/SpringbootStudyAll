package com.springboot.business.controller;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@ServerEndpoint(value = "/websocket")
@Component

public class CommonWebsocketController {

	@OnOpen
	public void onOpen(Session session) throws IOException {
		sendMessage(session, "开启连接：" + session.getId());
	}

	@OnClose
	public void onClose(Session session) {
		String id = session.getId();
		System.out.println("关闭连接：" + id);
	}

	@OnMessage
	public void onMessage(Session session, String message) throws IOException, InterruptedException {
		sendMessage(session, "发送成功:" + message + ",服务器时间:" + System.currentTimeMillis());
	}

	private void sendMessage(Session session, String message) throws IOException {
		session.getBasicRemote().sendText(message);
	}


}

