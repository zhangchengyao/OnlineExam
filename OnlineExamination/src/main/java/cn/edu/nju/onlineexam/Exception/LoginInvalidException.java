package cn.edu.nju.onlineexam.Exception;

/**
 * Created by mac on 2017/12/13.
 */
public class LoginInvalidException extends Exception {

    public LoginInvalidException(){
        super();
    }

    public LoginInvalidException(String msg){
        super(msg);
    }
}
