package com.mealer.mealer.Service;


import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class StaticImageService {
    private final String USER_AGENT = "Mozilla/5.0";
//
//    public static void main(String[] args) throws Exception {
//
//        StaticImageService http = new StaticImageService();
//
//        System.out.println("Testing 1 - Send Http GET request");
//        http.sendGet(55.735961,37.52741, 300, 300);
//
//
//    }

    // HTTP GET request
    public void sendGet(double lat, double lon, int width, int height) throws Exception {
        String url = "http://maps.google.com/maps/api/staticmap";
        url += "?center=Galeria+Krakowska,Krakow,KRK";
        url += "&zoom=15&size=600x300&maptype=roadmap";
        url += "&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318";
        url += "&key=AIzaSyDHXPXUTlFVgKQHHLcKn5EYt8PMtHzBJ10";

        byte[] imgBytes = null;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);


        InputStream in = con.getInputStream();

        BufferedImage image = ImageIO.read(in);
        ImageIO.write(image, "jpg",new File("src/main/resources/savedImages/out.jpg"));
//        JFrame frame = new JFrame();
//        JLabel label = new JLabel(new ImageIcon(image));
//        frame.getContentPane().add(label, BorderLayout.CENTER);
//        frame.pack();
//        frame.setVisible(true);
    }

    private static BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }
}