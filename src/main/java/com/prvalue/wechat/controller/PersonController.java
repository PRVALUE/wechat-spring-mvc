package com.prvalue.wechat.controller;

import com.prvalue.wechat.model.Person;
import com.prvalue.wechat.service.CoreService;
import com.prvalue.wechat.service.PersonService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Heisaman
 */
@Controller
@SessionAttributes("personObj")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
    private PersonService personService;

    @Autowired(required=true)
    @Qualifier(value="personService")
    public void setPersonService(PersonService ps){
        this.personService = ps;
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String listPersons(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }

    //For add and update person both
    @RequestMapping(value= "/person/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person p){

        if(p.getId() == 0){
            //new person, add it
            this.personService.addPerson(p);
        }else{
            //existing person, call update
            this.personService.updatePerson(p);
        }

        return "redirect:/persons";

    }

    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){

        this.personService.removePerson(id);
        return "redirect:/persons";
    }

    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }

    @RequestMapping("/approve/{id}")
    public String approvePerson(@PathVariable("id") int id, Model model){
        String access_token = CoreService.getDefaultAccessToken();
        Person p = this.personService.getPersonById(id);
        String url = CoreService.baseUrl+"user/authsucc?access_token="+access_token+"&userid="+p.getUserid();
        JSONObject json = sendWechatHttpRequest(url);
        try {
            if(String.valueOf(json.get("errmsg")).equals("ok")) {
                p.setStatus(1);
                this.personService.updatePerson(p);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return "redirect:/persons";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }
        return "login";
    }

    @RequestMapping(value = "/oauth2", method = RequestMethod.GET)
    public String oauth2(
            @RequestParam(value = "code", required = true) String code, Model model) {
        if (code != null) {
            Person p = new Person();
            String access_token = CoreService.getDefaultAccessToken();
            String url = CoreService.baseUrl+"user/getuserinfo?access_token="+access_token+"&code="+code+"&agentid=0";
            JSONObject json = sendWechatHttpRequest(url);
            logger.info(json.toString());
            try {
                String userId = String.valueOf(json.get("UserId"));
                p.setUserid(userId);
                url = CoreService.baseUrl+"user/get?access_token="+access_token+"&userid="+userId;
                json = sendWechatHttpRequest(url);
                logger.info(json.toString());
                if(String.valueOf(json.get("errmsg")).equals("ok")) {
                    String name = String.valueOf(json.get("name"));
                    model.addAttribute("name", name);
                    p.setName(name);
                    p.setPosition(String.valueOf(json.get("position")));
                    p.setGender(Integer.valueOf((String)json.get("gender")));
                    if(json.has("mobile")) {
                        p.setPhone(String.valueOf(json.get("mobile")));
                    }
                    if(json.has("email")) {
                        p.setEmail(String.valueOf(json.get("email")));
                    }
                    if(json.has("weixinid")) {
                        p.setWeixinid(String.valueOf(json.get("weixinid")));
                    }
                    if(json.has("avatar")) {
                        p.setAvatar(String.valueOf(json.get("avatar")));
                    }
                    p.setStatus((Integer)json.get("status"));
                } else {
                    model.addAttribute("error", "Sorry, we can't get your information!");
                }
            } catch (JSONException e){
                e.printStackTrace();
            }
            this.personService.addPerson(p);
            logger.info(p.toString());
            model.addAttribute("personObj", p);
        }
        return "oauth2";
    }

    @RequestMapping(value = "/formSubmitted", method = RequestMethod.GET)
    public String formSubmitted(
            @ModelAttribute("personObj") Person p,
            @RequestParam(value = "manager", required = true) String manager, Model model) {
        p.setManager(manager);
        this.personService.updatePerson(p);
        return "formSubmitted";
    }

    public JSONObject sendWechatHttpRequest(String url) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        JSONObject jsonObj = null;
        try {
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            HttpResponse response = httpClient.execute(request);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String json = reader.readLine();
            jsonObj = new JSONObject(json);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return jsonObj;
    }
}
