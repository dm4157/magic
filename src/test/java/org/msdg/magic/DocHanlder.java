package org.msdg.magic;

import java.io.*;

/**
 * Created by msdg on 2018/11/21.
 * Look, there is a moon.
 */
public class DocHanlder {

    public static void main(String[] args) throws IOException {
        String folder = "/Users/mw4157/Desktop/";
        String file = "ddd";
        String newFile = "doc-new.json";
        String host = "wisdom";

        FileInputStream fileInputStream = new FileInputStream(folder + file);
        byte[] buff = new byte[1024*1024*10];
        int len = fileInputStream.read(buff);
        fileInputStream.close();

        String content = new String(buff, 0, len);
        content = content.replaceAll("yourhost", "{{host-" + host + "}}");
        content = content.replaceAll("X-Login-UserId:", "X-Login-UserId: {{uc-me}} ");
        content = content.replaceAll("X-Login-CompanyId:", "X-Login-CompanyId: 10001");

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(folder + newFile)));
        bufferedWriter.write(content);
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
