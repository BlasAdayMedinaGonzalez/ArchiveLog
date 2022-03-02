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
            String hostIp = sc.next();
            System.out.println("Enter port: ");
            int port = sc.nextInt();

            System.out.println("Enter username:");
            String hostname = sc.next();

            session = new JSch().getSession(hostname,hostIp,port);

            System.out.println("Enter password:");
            String password = sc.next();
            session.setPassword(password);

            session.setConfig("StrictHostKeyChecking", "no");


            session.connect();
            System.out.println("Connected...");

            boolean fileStatus = true;
            do {
                channel = (ChannelExec) session.openChannel("exec");

                System.out.println("Enter file name: ");
                String fileName = sc.next();

                channel.setCommand("cat /var/log/" + fileName + ".log");

                ByteArrayOutputStream responseStream = new ByteArrayOutputStream();

                channel.setOutputStream(responseStream);

                channel.connect();

                while (channel.isConnected()) {
                    Thread.sleep(100);
                }

                String responseString = new String(responseStream.toByteArray());

                if (responseString.isEmpty()) {
                    System.out.println("File no exists");
                } else {
                    System.out.println(responseString);
                }

                System.out.println("Do you want to see another file? YES/NO ");
                String continues = sc.next();
                if (continues.equals("NO")) {
                    fileStatus = false;
                }

            } while (fileStatus);

            System.out.println("Finalizando programa...");

        } catch (JSchException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.disconnect();
            if (channel != null) channel.disconnect();
            if (sc != null) sc.close();
        }
    }
}
