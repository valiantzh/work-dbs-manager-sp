package com.dcdzsoft.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuthenticator  extends Authenticator{   
    String userName=null;   
    String password=null;   
        
    public EmailAuthenticator(){   
    }   
    public EmailAuthenticator(String username, String password) {    
        this.userName = username;    
        this.password = password;    
    }    
    public PasswordAuthentication getPasswordAuthentication(){   
        return new PasswordAuthentication(userName, password);   
    }  
}
