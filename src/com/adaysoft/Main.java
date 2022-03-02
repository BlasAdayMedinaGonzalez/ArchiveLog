package com.adaysoft;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Session session = null;

        ChannelExec channel = null;

        try {
            System.out.println("Enter host ip:");
            String hostIp = sc.nextLine();
            System.out.println("Enter port: ");
            int port = sc.nextInt();

            System.out.println("Enter username:");
            String username = sc.nextLine();

            System.out.println("Enter password:");
            String password = sc.nextLine();
            session.setPassword(password);

            session = new JSch().getSession(username,hostIp,port);


            session.connect();
            System.out.println("Connected...");





        } catch (JSchException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.disconnect();
            if (channel != null) channel.disconnect();
            if (sc != null) sc.close();
        }
    }
}
