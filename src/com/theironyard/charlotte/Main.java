package com.theironyard.charlotte;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static User user;
    static ArrayList<String> messageStore = new ArrayList<>();
    static HashMap m = new HashMap();



    public static void main(String[] args) {
        Spark.init();

        Spark.get(
                "/",
                ((request, response) -> {
                    if (user == null) {
                        return new ModelAndView(m, "index.html");
                    } else {
                        m.put("name", user.name);
                        return new ModelAndView(m, "messages.html");
                    }
                }),
                new MustacheTemplateEngine()
        );

        Spark.post(
                "/create-user",
                ((request, response) -> {
                    String name = request.queryParams("loginName");
                    String password = request.queryParams("password");

                    if (password != "1234") {
                        user = null;
                        response.redirect("/");
                    }
                    user = new User(name, password);
                    response.redirect("/");
                    return "";
                })
        );

        Spark.post(
                "/create-message",
                ((request, response) -> {
                    messageStore.add(request.queryParams("message"));
                    m.put("messageList", messageStore);
                    response.redirect("/");
                    return new ModelAndView(m, "messages.html");
                })
        );

//        Spark.post(
//                "/create-message",
//                ((request, response) -> {
//                    ArrayList<Message> messageStore = new ArrayList();
//                    String input = request.queryParams("message");
//                    Message stuff = new Message(user.name, user.password, input);
//                    messageStore.add(stuff);
//                    m.put("messageList", messageStore);
//                    response.redirect("/");
//                    return new ModelAndView(m, "messages.html");
//                })
//        );
    }
}
