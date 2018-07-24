package com.wangqifan.toutiao.async.handler;

import com.wangqifan.toutiao.Models.Message;
import com.wangqifan.toutiao.Models.User;
import com.wangqifan.toutiao.Service.MessageService;
import com.wangqifan.toutiao.Service.UserService;
import com.wangqifan.toutiao.async.EventHandler;
import com.wangqifan.toutiao.async.EventModel;
import com.wangqifan.toutiao.async.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Component
public class LikeHandler implements EventHandler {
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Override
    public void doHandle(EventModel model) {
        Message message = new Message();
        User user = userService.getUser(model.getActorId());
        message.setToId(model.getEntityOwnerId());
        message.setContent("用户" + user.getName() +
                " 赞了你的资讯,http://127.0.0.1:8080/news/"
                + String.valueOf(model.getEntityId()));
        // SYSTEM ACCOUNT
        message.setFromId(3);
        message.setCreatedDate(new Date());
        messageService.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }
}
