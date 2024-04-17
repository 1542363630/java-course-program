package com.example.courseprogram.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.courseprogram.model.DO.User;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.model.DTO.Token;
import com.example.courseprogram.model.DTO.UserInfo;
import com.example.courseprogram.repository.UserRepository;
import com.example.courseprogram.utils.JsonUtil;
import com.example.courseprogram.utils.JwtUtil;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService{

    @Autowired
    UserRepository userRepository;

    public DataResponse addUser(DataRequest dataRequest){
        User user = JsonUtil.prase(dataRequest.get("user"), User.class);
        user.setUserName(dataRequest.getString("userName"));
        user.setUserType(dataRequest.getString("userType"));


        User userExist = userRepository.findUserByUserName(user.getUserName());
        if(userExist != null){
            return DataResponse.failure(400,"该用户已存在！");
        }
        String encodedPassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());  //密码加密
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return DataResponse.ok();
    }

    public DataResponse deleteUser(DataRequest dataRequest){
        User user=JsonUtil.prase(dataRequest.get("user"), User.class);
        User userExist = userRepository.findUserByUserName(user.getUserName());
        if(userExist != null){
            return DataResponse.failure(400,"该用户不存在！");
        }
        userRepository.deleteById(user.getUserId());
        return DataResponse.ok();
    }

    public DataResponse login(DataRequest dataRequest){
//        User user1 = JSONObject.parseObject(JSON.toJSONString(dataRequest.get("user")),User.class);
        User user1 = JsonUtil.prase(dataRequest.get("user"),User.class);
        String userName= String.valueOf(user1.getUserName());
        String password=String.valueOf(user1.getPassword());
        User user= userRepository.findUserByUserName(userName);
        if(user == null){
            return DataResponse.notFound();
        }
        else {
            //String encodedPassword = BCrypt.hashpw(password,BCrypt.gensalt());
            UserInfo userInfo = new UserInfo(user);
            if(BCrypt.checkpw(password,user.getPassword())){
                return DataResponse.success(new Token(JwtUtil.generateToken(userName),userInfo));
            }
            else {
                return DataResponse.failure(401,"密码错误！");
            }
        }
    }

    public DataResponse updatePassword(DataRequest dataRequest){
        User userRe = JsonUtil.prase(dataRequest.get("user"), User.class);
        String userName = userRe.getUserName();
        String newPassword = userRe.getPassword();
        User user = userRepository.findUserByUserName(userName);
        if(user!=null){
            String encodedPassword = BCrypt.hashpw(newPassword,BCrypt.gensalt());
            user.setPassword(encodedPassword);
            userRepository.saveAndFlush(user);
            return DataResponse.ok();
        }
        else {
            return DataResponse.notFound();
        }

    }

    public DataResponse sduLogin(DataRequest dataRequest){
        User user = JsonUtil.prase(dataRequest.get("user"), User.class);

        String baseURL = "https://pass.sdu.edu.cn/";

        // 学号和密码填在此处
        String username = user.getUserName();
        String password = user.getPassword();
        // 第一个接口：获取ticket
        // 按理来说不应该用字符串拼接的方式作为formData
        // 但这个接口如果进行url encode将无法正确识别
        // 是学校平台的问题
        String ticket = Unirest.post(baseURL + "cas/restlet/tickets").
                body("username=" + username + "&password=" + password).asString().getBody();
//        System.out.println("ticket: " + ticket);
        // 如果密码不对，这步就会显示一个Bad Request界面，而非ticket；自行加入错误处理，如返回一个密码错误的消息
        if(!ticket.startsWith("TGT")){
            return DataResponse.failure(401,"密码错误");
//            throw new RuntimeException("ticket should start with TGT");
        }

        // 第二个接口，获取sTicket
        // body处填任何一个在统一认证登记的网站都行，如信息服务大厅、选课系统
        String sTicket = Unirest.post(baseURL + "cas/restlet/tickets/" + ticket).
                body("service=https://service.sdu.edu.cn/tp_up/view?m=up").asString().getBody();
//        System.out.println("sTicket: " + sTicket);
        if(!sTicket.startsWith("ST")){
            throw new RuntimeException("sTicket should start with ST");
        }

        // 第三个接口，获取姓名和学号
        // 结果是一个xml，需要自己从中提取需要的信息，Jsoup或正则均可
        String validationResult = Unirest.get(baseURL + "cas/serviceValidate").
                queryString("ticket", sTicket).
                queryString("service", "https://service.sdu.edu.cn/tp_up/view?m=up").asString().getBody();
//        System.out.println(validationResult);

        //如果数据库中没有这个人的信息，就添加到数据库中
        if(userRepository.findUserByUserName(username)==null){

        }

        Document document = Jsoup.parse(validationResult);
        String name = document.getElementsByTag("cas:USER_NAME").text();//获取学生姓名
//        System.out.println(name);

        return DataResponse.success(new Token(JwtUtil.generateToken(username),new UserInfo(user)));
    }


}
