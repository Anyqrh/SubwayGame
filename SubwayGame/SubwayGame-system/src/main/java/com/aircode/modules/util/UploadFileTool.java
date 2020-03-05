package com.aircode.modules.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class UploadFileTool {


    /**
     *
     * @param file
     * @param path  上传文件服务器的路径
     * @return
     * @throws IOException
     */
    public static String upload(MultipartFile file, String path) throws IOException {
        if(file == null)
            return "";
        // 获取上传文件名称
        String fileName = file.getOriginalFilename();
        String suffix = getExtensionName(fileName);
        String uuid = UUID.randomUUID().toString().replace("-","");
        fileName = uuid+"."+suffix;
//
        Client client = Client.create();
        WebResource webResource = client.resource(path+fileName);

        webResource.put(String.class,file.getBytes());
//        file.transferTo(new File(path,fileName));

        return path+fileName;
    }

    /**
     *
     * @param url  文件所在的地址
     * @param localUrl  下载到本地的哪个位置
     * @return
     */
    public static File download(String url, String localUrl) throws IOException {

        File file = new File(localUrl);
        if(!file.exists()){
            file.mkdir();
        }
        FileOutputStream fileOutputStream = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        URL httpUrl = new URL(url);
        conn = (HttpURLConnection) httpUrl.openConnection();
//      conn.setRequestMethod("post");   // 默认为get请求
        conn.setDoInput(true);
        conn.setDoOutput(true);
        // post方式不能使用缓存
        conn.setUseCaches(true);
        // 连接指定的资源
        conn.connect();
        // 获取网络输入流
        inputStream = conn.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        // 判断文件的保存路径后面是否以/结尾
        if(!localUrl.endsWith("/")){
            localUrl+="/";
        }
        String uuid = UUID.randomUUID().toString().replace("-","");

        fileOutputStream = new FileOutputStream(localUrl+uuid+".png");
        BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
        byte[] buf = new byte[4096];
        int length = bis.read(buf);

        while(length != -1){
            bos.write(buf,0,length);
            length = bis.read(buf);
        }
        bos.close();
        bis.close();
        conn.disconnect();



        if(file == null)
            System.out.println("失败");
        else
            System.out.println("成功");
        return file;
    }

    /**
     * 获取文件扩展名，不带 .
     * 获取文件后缀不带 .
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    // 设置请求头及编码格式
    public static void doPost(HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
//        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
    }

}
