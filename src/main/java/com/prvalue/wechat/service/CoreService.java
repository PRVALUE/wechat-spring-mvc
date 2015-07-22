package com.prvalue.wechat.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

/**
 *
 * @author Heisaman
 */
public class CoreService {

    public static final String baseUrl = "https://qyapi.weixin.qq.com/cgi-bin/";
    public static final String corpid = "wxe577618fe713e475";
    public static final String corpsecret = "-NNtJkVkLsdpTAXGmp5J2g9mknGPYnyBMU4BvlVo3l_yYYSGfZyyGk_rG9hI1b-z";
    public static final String sToken = "UOCJDzA2bku9TEKUzLnBMGVM67";
    public static final String sCorpID = "wxe577618fe713e475";
    public static final String sEncodingAESKey = "BKa7REbgMlGiEjSAW564cLuAwvsrPoq2ycQwnxD1p2A";

    /*public static String displayEmployees(){
        StringBuilder builder = new StringBuilder();
        List<Employee> list = getContactsByDepartment(getAccessToken(corpid,corpsecret),"2","0");
        for(Employee e:list){
            builder.append(e.getId()).append("  ").append(e.getName()).append("<br>");
        }
        return builder.toString();
    }*/

    public static String getDefaultAccessToken(){
        return getAccessToken(corpid,corpsecret);
    }

    public static String getAccessToken(String id, String secret) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpGet request = new HttpGet(baseUrl + "gettoken?corpid=" + id + "&corpsecret=" + secret);
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            HttpResponse response = httpClient.execute(request);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String json = reader.readLine();
            JSONObject jsonObj = new JSONObject(json);
            return String.valueOf(jsonObj.get("access_token"));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return "";
    }

    /*public static List<Employee> getContactsByDepartment(String token, String departmentId, String status){
        HttpClient httpClient = HttpClientBuilder.create().build();
        List<Employee> list = new ArrayList<Employee>();
        try {
            HttpGet request = new HttpGet(baseUrl+"user/simplelist?access_token="+token+
                    "&department_id="+departmentId+"&fetch_child=&status="+status);
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            HttpResponse response = httpClient.execute(request);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String json = reader.readLine();
            JSONObject jsonObj = new JSONObject(json);
            JSONArray userList = jsonObj.getJSONArray("userlist");
            for(int i = 0; i < userList.length(); i++) {
                try {
                    list.add(new Employee((JSONObject) userList.get(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return list;
    }*/
}
