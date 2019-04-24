package cn.sk.client.service;


import cn.sk.client.pojo.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace="http://sk.cn")
public interface IUserService {
    @WebMethod//标注该方法为webservice暴露的方法,用于向外公布，它修饰的方法是webservice方法，去掉也没影响的，类似一个注释信息。
    public User getUser(@WebParam(name = "userId") String userId);

    @WebMethod
    public @WebResult(name="String") String getUserName(@WebParam(name = "userId") String userId);

}
