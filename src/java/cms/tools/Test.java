/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import java.io.File;
import java.io.UnsupportedEncodingException;
import jj.jjPicture;

/**
 *
 * @author Milad
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException {

        System.out.println(java.net.URLDecoder.decode("%DA%A9%D8%AF009%2C%DA%A9%D8%AF010%2C%DA%A9%D8%AF011%2C", "UTF-8"));
//        String s = "aaa.jpg";
//        ServerLog.Print(s.substring(s.lastIndexOf("."), s.length()));
//        ServerLog.Print(jjPicture.getMargin(new File("D:\\b.jpg")));//By Md
//        try {
//            if (Server.sendEmail(Server.emailAccount, "mrsalesi@gmail.com", "Test", "hiiii سلام", true, null)) {
//                ServerLog.Print("Email Ok send");
//            }else{
//                ServerLog.Print("EMAIL IS NOT SENT");
//            }
//        } catch (Exception ex) {
//            ServerLog.Print(ex);
//        }
    }
}
