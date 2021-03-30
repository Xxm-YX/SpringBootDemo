package cn.itcast.util.Http;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author 78703
 * @version 1.0
 * @description: Http请求工具类
 * @date 2021/3/20 15:50
 */
public class HttpRequsetUtil {

    public static String getResponse(String url) throws IOException {
        String res = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        //返回值
        CloseableHttpResponse response = null;

        //配置信息
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)        //设置连接超时时间（毫秒）
                .setConnectionRequestTimeout(5000)      //设置请求超时时间
                .setSocketTimeout(5000)         //设置socket读写超时时间
                .setRedirectsEnabled(false).build();//不能重定向

        httpGet.setConfig(requestConfig);//配置信息
        response = httpClient.execute(httpGet);     //由客户端执行发送
        HttpEntity responseEntity = response.getEntity();   //从响应模型中获取响应实体

        System.out.println("响应状态为："+response.getStatusLine());

        if(responseEntity != null){
            res = EntityUtils.toString(responseEntity);
            System.out.println("响应内容长度："+ responseEntity.getContentLength());
            System.out.println("响应内容为："+res);
        }

        //释放资源
        if (httpClient != null) {
            httpClient.close();
        }
        if (response != null) {
            response.close();
        }
        return res;
    }
}
