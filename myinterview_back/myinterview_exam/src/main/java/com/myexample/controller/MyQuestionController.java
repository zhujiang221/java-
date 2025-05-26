package com.myexample.controller;


import com.alibaba.fastjson.JSON;
import com.coze.openapi.client.chat.CreateChatReq;
import com.coze.openapi.client.chat.model.ChatEvent;
import com.coze.openapi.client.chat.model.ChatEventType;
import com.coze.openapi.client.connversations.message.model.Message;
import com.coze.openapi.service.auth.TokenAuth;
import com.coze.openapi.service.service.CozeAPI;

import com.myexample.beans.MyQuestion;
import com.myexample.mapper.MyQuestiopnMapper;
import com.myexample.service.MyQusetionService;
import io.reactivex.Flowable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin("http://localhost:8080/")
@RestController
@RequestMapping("/")
public class MyQuestionController {


    @Autowired
    private MyQuestiopnMapper carouselService;
    //办理数据显示轮播图、

    @Autowired
    private MyQusetionService myQusetionService;
    @RequestMapping("/list_question")
    public String listQuestion() {
        List<MyQuestion> mycarousel = carouselService.getQuestion();
        return JSON.toJSONString(mycarousel);
    }


    @RequestMapping("/save_question")
    public String get_question() {
            // Get an access_token through personal access token or oauth.
            String token = "pat_u4FSK3855xlxUzve7bW7LHGVjgN7WhkoQdqJYdpGcdByHXuq8mtcK3NGE3zaL5r7";
            String botID = "7507892647030194212";
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
            String qusstion = "请生成10个java面试选择题";

            CreateChatReq req =
                    CreateChatReq.builder()
                            .botID(botID)
                            .userID(userID)
                            .messages(Collections.singletonList(Message.buildUserQuestionText(qusstion)))
                            .build();

            Flowable<ChatEvent> resp = coze.chat().stream(req);
            StringBuilder sb = new StringBuilder();
            resp.blockingForEach(
                    event -> {
                        if (ChatEventType.CONVERSATION_MESSAGE_DELTA.equals(event.getEvent()))
                        {
                            System.out.println("Received a message delta:");
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

        String mystrs= sb.toString();

        List<Map<String,String>> questions = new ArrayList<>();

        // 按题目分割（两个换行分割题目）
        String[] questionBlocks = mystrs.split("\n\n");

        // 正则表达式匹配题目各部分
        Pattern pattern = Pattern.compile(
                "(?is)(\\d+)\\.\\s*(.*?)\\s+" +  // 题目编号和标题
                        "(A[.．]\\s*.*?)\\s+" +         // 完整A选项
                        "(B[.．]\\s*.*?)\\s+" +         // 完整B选项
                        "(C[.．]\\s*.*?)\\s+" +         // 完整C选项
                        "(D[.．]\\s*.*?)\\s+" +         // 完整D选项
                        "答案：\\s*([A-D])[.．]?\\s*" +  // 答案
                        "解析：\\s*(.*)"                 // 解析
        );

        for (String block : questionBlocks) {
            if (block.trim().isEmpty()) continue;

            Matcher matcher = pattern.matcher(block);
            if (matcher.find()) {
                Map<String, String> question = new HashMap<>();
                question.put("title", matcher.group(2));
                question.put("aoption", matcher.group(3));
                question.put("boption", matcher.group(4));
                question.put("coption", matcher.group(5));
                question.put("doption", matcher.group(6));
                question.put("answer", matcher.group(7));
                question.put("description", matcher.group(8));
                questions.add(question);
            }
        }

        for(Map<String,String> q : questions){
            MyQuestion question = new MyQuestion();
            question.setTitle(q.get("title"));
            question.setAoption(q.get("aoption"));
            question.setBoption(q.get("boption"));
            question.setCoption(q.get("coption"));
            question.setDoption(q.get("doption"));
            question.setAnswer(q.get("answer"));
            question.setDescription(q.get("description"));
            myQusetionService.getInsert(question);

        }
        Map<String,String> map = new HashMap<>();
        map.put("result","大模型产生的轮播图同时插入数据库");
        return questionBlocks.toString();

    };


}
