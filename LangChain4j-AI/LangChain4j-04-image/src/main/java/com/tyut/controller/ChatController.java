package com.tyut.controller;


import com.tyut.service.IServiceAssistant;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

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
    private IServiceAssistant iServiceAssistant;
    @Autowired
    private ChatLanguageModel chatLanguageModel;

    @PostMapping("/image")
    public String image(MultipartFile file, String message) throws IOException {
        InputStream inputStream = file.getInputStream();
        byte[] bytes = inputStream.readAllBytes();
        String base64 = Base64.getEncoder().encodeToString(bytes);
        UserMessage userMessage =
                UserMessage.from(
                        TextContent.from(message),
                        ImageContent.from(base64, "image/jpg")
                );
        return chatLanguageModel.generate(userMessage).content().text();
    }
}

