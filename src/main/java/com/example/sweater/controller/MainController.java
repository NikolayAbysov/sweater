package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.FileHandler;

@Controller
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private MessageRepository messageRepository;

    @Value("${upload.path}") //Указываем спрингу, что хотим получить переменную (из пропертей). ${upload.path} - Spring rxpression language выдергивает значения из контекста
    private String uploadPath;

    @GetMapping("/")
    public String greeting( Map<String, Object> model) { //@RequestParam в длинной форме получает параметры ГЕТ запроса
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter , Model model){
        Iterable<Message> messages = messageRepository.findAll();

        if (filter != null && !filter.isEmpty()){
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add (@AuthenticationPrincipal User user,//@RequestParam( в короткой форме получает параметры из названий формы ПОСТ запроса
                       @RequestParam("file") MultipartFile file,
                       @RequestParam String text,
                       @RequestParam String tag,
                       Map<String, Object> model) throws IOException {
        Message message = new Message(text, tag, user); //Здесь сохраняем данные в БД

        if (file != null && !file.getOriginalFilename().isEmpty()){

            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" +resultFilename));

            message.setFilename(resultFilename);
        }

        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll(); //Достаем из БД
        model.put("messages", messages);
        return "main";
    }

    @GetMapping(value = "/img/{imageUrl}") //Для отображения картинок
    public @ResponseBody
    byte[] image(@PathVariable String imageUrl) throws IOException {
        String url = "C:/Users/Kirito/IdeaProjects/sweater/uploads/" + imageUrl; //здесь указываете СВОЙ путь к папке с картинками
        InputStream in = new FileInputStream(url);
        return IOUtils.toByteArray(in);
    }

/*    @PostMapping ("filter") //<form method="post" action="filter">
    public String filter (@RequestParam String filter, Map<String, Object> model){
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()){
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }
        model.put("messages", messages);
        return "main";
    }*/

}
