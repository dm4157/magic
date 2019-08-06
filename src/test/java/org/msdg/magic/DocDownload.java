package org.msdg.magic;

import com.alibaba.fastjson.JSON;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.msdg.magic.util.LittleTrick;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;

import java.io.*;

/**
 * Created by msdg on 2019-08-06 19:14.
 * Look, there is a moon.
 */
public class DocDownload {

    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("/Users/mw4157/Desktop/cpxs.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

        Doc doc = null;
        String ref = "https://m.baidu.com/tcx?appui=alaxs&data=%7B%22fromaction%22:%22search_zhongjianye67%22,%22query%22:%22%E8%B6%85%E5%93%81%E7%9B%B8%E5%B8%88%22%7D&page=detail&gid=2807690714&sign=931592f6d4836af2bb687f5d22c98d3a&ts=1565052252&sourceurl=http://www.xqqxs.com/xs/0/19/&cid=2807690714%7C4058916684518006640&url=http%3A%2F%2Fread.qidian.com%2FBookReader%2F3079913%2C50681875.aspx";
        String url = "https://m.baidu.com/tcx?appui=alaxs&page=api/chapterContent&gid=2807690714&cid=2807690714|4182172857122540170&url=http://read.qidian.com/BookReader/3079913,50711914.aspx";
        OkHttpClient client = new OkHttpClient();
        do {
            final Request request = new Request.Builder().url(url).addHeader("Referer", ref).build();
            final Call call = client.newCall(request);
            Response response = call.execute();
            String respStr = response.body().string();
            doc = JSON.parseObject(respStr, Doc.class);

            if (doc.getErrno() != 0) {
                System.out.println(respStr);
                continue;
            }

            bufferedWriter.write(doc.getData().getTitle() + "\n");
            bufferedWriter.write(doc.getData().getContent() + "\n");

            ref = url;
            url = "https://m.baidu.com/tcx?appui=alaxs&page=api/chapterContent&gid=2807690714&cid=" + doc.getData().getPt().getNext_cid() + "&url=" + doc.getData().getPt().getNext_url();

            System.out.println(doc.getData().getTitle());
        } while (doc.getErrno() != 0 || LittleTrick.notEmpty(doc.getData().getPt().getNext_cid()));

        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
