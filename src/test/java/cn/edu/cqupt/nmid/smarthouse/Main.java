package cn.edu.cqupt.nmid.smarthouse;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.common.models.RuntimeObject;
import com.aliyun.ocr.Client;
import com.aliyun.ocr.models.*;
import com.aliyun.tea.TeaException;
import com.aliyuncs.*;

import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * 测试发短信
 *
 * @author piwenjing
 * @description
 * @date 2020/2/16 12:05 PM
 */
@SpringBootTest
@Slf4j
public class Main {
    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FpFWu8DhHtyuy4JqJxJ", "IkcVbTyhCvDdEnwzbxEY5yiRjUhNeM");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "13452078118");
        request.putQueryParameter("SignName", "健康小卫");
        request.putQueryParameter("TemplateCode", "SMS_183795941");
        request.putQueryParameter("TemplateParam", "{\"code\":\"24234\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNormal() throws Exception {
        Config config = new Config();
        // 你的accessKeyId
        config.accessKeyId = "LTAI4FpFWu8DhHtyuy4JqJxJ";
        //你的accessKeySecret
        config.accessKeySecret = "IkcVbTyhCvDdEnwzbxEY5yiRjUhNeM";
        config.type = "access_key";
        config.regionId = "cn-shanghai";
        config.endpointType = "internal";
        Client client = new Client(config);
        try {
            /**
             *1 .这是本地识别,可识别位置
             */

//            RecognizeCharacterAdvanceRequest req = new RecognizeCharacterAdvanceRequest();
//
//            req.imageURLObject = new FileInputStream("/Users/piwenjing/Desktop/aa.jpg");
//            req.outputProbability = false;
//            req.minHeight = 10;
//            RecognizeCharacterResponse rep = client.recognizeCharacterAdvance(req, new RuntimeObject());
            /**
             * 这是拿到照片url后的识别
             */
            RecognizeCharacterRequest req = new RecognizeCharacterRequest();
            //图片地址
            req.imageURL = "https://img2-smarthouse.oss-cn-shanghai.aliyuncs.com/aa.jpg";
            req.minHeight = 10;
            req.outputProbability = false;
            RecognizeCharacterResponse rep = client.recognizeCharacter(req, new RuntimeObject());
//            System.out.println("学生卡识别=" + JSON.toJSONString(rep));
            String jsonString = JSON.toJSONString(rep);
            JSONArray jsonArray = JSON.parseObject(jsonString).getJSONObject("data").getJSONArray("results");
            JSONObject row = null;
            for (int i = 0; i < jsonArray.size(); i++) {
                row = jsonArray.getJSONObject(i);
                String text = (String) row.get("text");
                if (StringUtils.isNumber(text)) {
                    System.out.println(text);
                    break;
                }
            }
//            System.out.println("学生卡识别=" + JSON.toJSONString(rep));
        } catch (TeaException e) {
            System.out.println("学生卡识别异常了");
            System.out.println(JSON.toJSONString(e.getData()));
        }
    }
}
//    @Test
//    public void testNomal2() throws Exception{
//        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", "LTAI4FpFWu8DhHtyuy4JqJxJ", "IkcVbTyhCvDdEnwzbxEY5yiRjUhNeM");
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        RecognizeCharacterRequest request = new RecognizeCharacterRequest();
//        request.setImageURL("https://img2-smarthouse.oss-cn-shanghai.aliyuncs.com/aa.jpg");
//        request.setMinHeight(10);
//        request.setOutputProbability(false);
//
//        try {
//            RecognizeCharacterResponse response = client.getAcsResponse(request);
//            System.out.println(new Gson().toJson(response));
//        } catch (ClientException e) {
////            可以了
//            System.out.println("ErrCode:" + e.getErrCode());
//            System.out.println("ErrMsg:" + e.getErrMsg());
//            System.out.println("RequestId:" + e.getRequestId());
//        }
//    }
//    }
//
//    @Test
//    public void TestPhoto() throws Exception {
//
//        Config config = new Config();
//        // 你的accessKeyId
//        config.accessKeyId = "LTAI4FpFWu8DhHtyuy4JqJxJ";
//        //你的accessKeySecret
//        config.accessKeySecret = "IkcVbTyhCvDdEnwzbxEY5yiRjUhNeM";
//        config.type = "access_key";
//        config.regionId = "cn-shanghai";
//        config.endpointType = "internal";
//        Client client = new Client(config);
//
//        RecognizeBusinessLicenseAdvanceRequest request = new RecognizeBusinessLicenseAdvanceRequest();
////        RecognizeBankCardAdvanceRequest request = new RecognizeBankCardAdvanceRequest();
//        InputStream inputStream = new FileInputStream(new File("/Users/piwenjing/Desktop/bb.jpg"));
//        request.imageURLObject = inputStream;
////        request.setRegionId("cn-shanghai");
////        request.setImageURL("http://explorer-image.oss-cn-shanghai-internal.aliyuncs.com/1314974073534877/2A4CA7CAC18302003F840352660D63EE.jpg?OSSAccessKeyId=LTAI8BVg4NEcndZF&Expires=1582523398&Signature=tIA1uejBXe8hEGJSjoE%2BSu2L3y4%3D");
////        request.setMinHeight(10);
////        request.setOutputProbability(false);
////
//        try {
//            RecognizeBusinessLicenseResponse response = client.recognizeBusinessLicenseAdvance(request, new RuntimeObject());
////              RecognizeBankCardResponse response = client.recognizeBankCardAdvance(request, new RuntimeObject());
//            System.out.println(JSON.toJSONString(response));
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            System.out.println("ErrCode:" + e.getErrCode());
//            System.out.println("ErrMsg:" + e.getErrMsg());
//            System.out.println("RequestId:" + e.getRequestId());
//        }
//
//    }
//    @Test
//    public void TestRSI(){
//        String host = "https://tysbgpu.market.alicloudapi.com";
//        String path = "/api/predict/ocr_general";
//        String method = "POST";
//        String appcode = "bf06ab7872f64075ba895a7274ed20aa";
//        Map<String, String> headers = new HashMap<String, String>();
//        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//        headers.put("Authorization", "APPCODE " + appcode);
//        //根据API的要求，定义相对应的Content-Type
//        headers.put("Content-Type", "image/jpeg; charset=UTF-8");
//        Map<String, String> querys = new HashMap<String, String>();
//        String bodys = "{\"image\":\"https://smart-house-img.oss-cn-beijing.aliyuncs.com/aa.jpg\"，\"configure\":\"{\\\"min_size\\\":16,#图片中文字的最小高度，单位像素\\\"output_prob\\\":true,#是否输出文字框的概率\\\"output_keypoints\\\":false}\"#是否输出文字框角点}";
//
//
//        try {
//            /**
//             * 重要提示如下:
//             * HttpUtils请从
//             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
//             * 下载
//             *
//             * 相应的依赖请参照
//             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
//             */
//            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//            System.out.println("-----------------");
//            System.out.println(response.toString());
//            //获取response的body
//            //System.out.println(EntityUtils.toString(response.getEntity()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void RecognizeBankCardAdvance() throws Exception {
//        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", "LTAI4FpFWu8DhHtyuy4JqJxJ", "IkcVbTyhCvDdEnwzbxEY5yiRjUhNeM");
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        RecognizeBusinessLicenseRequest request = new RecognizeBusinessLicenseRequest();
//        request.setRegionId("cn-shanghai");
//        request.setImageURL("");
//
//        try {
//            RecognizeBusinessLicenseResponse response = client.getAcsResponse(request);
//            System.out.println(new Gson().toJson(response));
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            System.out.println("ErrCode:" + e.getErrCode());
//            System.out.println("ErrMsg:" + e.getErrMsg());
//            System.out.println("RequestId:" + e.getRequestId());
//        }
//
//    }
//    }

//    @Test
//    public void testUrlPhoto(){
//        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", "<accessKeyId>", "<accessSecret>");
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        RecognizeCharacterRequest request = new RecognizeCharacterRequest();
//        request.setRegionId("cn-shanghai");
//        request.setMinHeight(10);
//        request.setOutputProbability(false);
//
//        try {
//            RecognizeCharacterResponse response = client.getAcsResponse(request);
//            System.out.println(new Gson().toJson(response));
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            System.out.println("ErrCode:" + e.getErrCode());
//            System.out.println("ErrMsg:" + e.getErrMsg());
//            System.out.println("RequestId:" + e.getRequestId());
//        }
//    }

