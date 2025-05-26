package com.myexample.controller;


import com.alibaba.fastjson.JSON;
import com.coze.openapi.service.config.Consts;
import com.myexample.beans.Carousel;
import com.myexample.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import com.coze.openapi.client.chat.CreateChatReq;
import com.coze.openapi.client.chat.model.ChatEvent;
import com.coze.openapi.client.chat.model.ChatEventType;
import com.coze.openapi.client.connversations.message.model.Message;
import com.coze.openapi.service.auth.TokenAuth;
import com.coze.openapi.service.service.CozeAPI;

import io.reactivex.Flowable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin("http://localhost:8080/")
@RestController
@RequestMapping("/")
public class CarouselController {


    @Autowired
    private CarouselService carouselService;
    //办理数据显示轮播图、
    @RequestMapping("/list_carousel")
    public String listCarousel() {
        List<Carousel> mycarousel = carouselService.selectCarousel();
        return JSON.toJSONString(mycarousel);
    }



    @RequestMapping("/save_carousel")
    public String get_save_carousel(String first, String second, String third, String temp) {


            // Get an access_token through personal access token or oauth.
            String token = "pat_u4FSK3855xlxUzve7bW7LHGVjgN7WhkoQdqJYdpGcdByHXuq8mtcK3NGE3zaL5r7";
            String botID = "7507513910720331776";
            String userID = "user10123325";

            TokenAuth authCli = new TokenAuth(token);

            // Init the Coze client through the access_token.
            CozeAPI coze =
                    new CozeAPI.Builder()
                            .baseURL("https://api.coze.cn/v3/chat/")
                            .auth(authCli)
                            .readTimeout(600000)
                            .connectTimeout(600000)
                            .build();
            ;

            /*
             * Step one, create chat
             * Call the coze.chat().stream() method to create a chat. The create method is a streaming
             * chat and will return a Flowable ChatEvent. Developers should iterate the iterator to get
             * chat event and handle them.
             * */
            CreateChatReq req =
                    CreateChatReq.builder()
                            .botID(botID)
                            .userID(userID)
                            .messages(Collections.singletonList(Message.buildUserQuestionText(first+";"+second+";"+third+";"+temp)))
                            .build();

            Flowable<ChatEvent> resp = coze.chat().stream(req);
            StringBuilder sb = new StringBuilder();
            resp.blockingForEach(
                    event -> {
                        if (ChatEventType.CONVERSATION_MESSAGE_DELTA.equals(event.getEvent()))
                        {
                            System.out.println("AAAA:");
                            sb.append(event.getMessage().getContent());
                            System.out.print(event.getMessage().getContent());
                        }
                        if (ChatEventType.CONVERSATION_CHAT_COMPLETED.equals(event.getEvent())) {
                            System.out.println("Token usage:" + event.getChat().getUsage().getTokenCount());
                        }
                    });
            System.out.println("done");
            coze.shutdownExecutor();
            System.out.println(sb.toString());;

        List<String> myimages = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\((https?://[^)]+)\\)");
        Matcher matcher = pattern.matcher(sb.toString());

        while (matcher.find()) {
            myimages.add(matcher.group(1));
        }

        for (String image : myimages) {
            Carousel carousel = new Carousel();
            carousel.setTitle("?????????");
            carousel.setPic(image);
            carouselService.insertCarousel(carousel);
            System.out.println(image);
        }
        Map<String,String> map = new HashMap<>();
        map.put("result","大模型产生的轮播图同时插入数据库");
        return JSON.toJSONString(map);

    };

    @RequestMapping("/selectCarousel")
    public List<Carousel> selectCarousel() {

        return carouselService.selectCarousel();

    };
}
