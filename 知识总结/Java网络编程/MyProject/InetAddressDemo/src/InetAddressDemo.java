
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chen
 */
public class InetAddressDemo {
    public static void main(String[] args) throws IOException {
        String hostName="localhost";
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("主机地址：");
        hostName=br.readLine();
        byte[] addr={(byte)202,115,(byte)144,(byte)151};
        InetAddress inet=InetAddress.getByAddress(addr);
        System.out.println("主机"
                + "地址："+inet.getHostAddress());
        System.out.println("主机名："+inet.getHostName());
        System.out.println("是否回环地址"+inet.isLoopbackAddress());
        
        
        
    }
    
}
