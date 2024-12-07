package com.tyut.controller;


import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * @version v1.0
 * @author OldGj 2024/12/6
 * @apiNote 控制器
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatLanguageModel chatLanguageModel;

    /**
     * AI视觉理解
     * @param file 上传的图片
     * @param message 理解的方向
     */
    @PostMapping("/image")
    public String image(MultipartFile file, String message) throws IOException {
        // 获得图片的base64编码
        InputStream inputStream = file.getInputStream();
        byte[] bytes = inputStream.readAllBytes();
        String base64 = Base64.getEncoder().encodeToString(bytes);
        // 构造用户数据
        UserMessage userMessage =
                UserMessage.from(
                        TextContent.from(message),
                        ImageContent.from(base64, "image/jpg")
                );
        return chatLanguageModel.generate(userMessage).content().text();
    }
}

