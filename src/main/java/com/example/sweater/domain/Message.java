package com.example.sweater.domain;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String text;
    private String tag;
    private String filename;
    private String createDate;

    @ManyToOne(fetch = FetchType.EAGER) //Маппинг хранения значений поля "Автор"
    @JoinColumn(name = "user_id") //Это нужно, чтобы в таблице это поле называлось "user_id" а не автор
    private User author;

    public Message(String text, String tag, User user, String createDate) {
        this.text = text;
        this.tag = tag;
        this.author = user;
        this.createDate = createDate;
    }

    public Message(){ //Обязательно создаем пуустой конструктор (Если Энтити, должен быть конструктор без  параметров)
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}