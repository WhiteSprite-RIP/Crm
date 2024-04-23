package cn.ws.basics.security.utils;

import cn.ws.data.utils.WsNullUtils;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WhiteSprite
 */
@ApiOperation(value = "企微消息发送消息工具类")
public class WsWxNoticeUtils {

    private static final String BASE_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";

    private static final String USER_ID_ERR = "81013";

    @ApiModelProperty(value = "朗世企业ID")
    public static final String YH_CORPID = "wwf94bb44e76e308f8";

    @ApiOperation(value = "发送文本消息")
    public static String sendInputMessage(int company,String userId,String content,String token){
        if(content == null || WsNullUtils.isNull(content)) {
            return "NULL";
        }

        String json = JSON.toJSONString(new WsWeChatNoticeInput(userId,"text",YH_CORPID,new WsWeChatNoticeInputItem(content),0,1));
        String s= WeiChatUtils.httpsRequest(BASE_URL + token,"POST",json);
        System.out.println(s);
        JSONObject ans1 = JSONObject.parseObject(s);
        String jsonStr2 = ans1.getString("errcode");
        if(jsonStr2.equals("0")){
            return ans1.getString("msgid");
        } else if(jsonStr2.equals("81013")) {
            return USER_ID_ERR;
        }
        return "FAIL";
    }

    @ApiOperation(value = "企微上传文件")
    public static String uploadWeChatFile(String path, String fileType,String token) {
        try {
            JSONObject jsonObject = WeChatUploadMeidaUtils.UploadMeida(fileType,path,token);
            System.out.println(jsonObject.toJSONString());
            String errcode = jsonObject.getString("errcode");
            if(errcode.equals("0")) {
                System.out.println("文件上传成功");
                System.out.println(jsonObject.getString("media_id"));
                return jsonObject.getString("media_id");
            }
            return "文件上传失败";
        } catch (Exception e) {
            return "文件上传失败";
        }
    }

    @ApiOperation(value = "发送图片消息")
    public static String sendImageMessage(int company,String userId,String mediaId,String token) {
        WsWeiChatNoticeImage image = new WsWeiChatNoticeImage(userId,"image",YH_CORPID,new WsWeChatNoticeImageItem(mediaId),0,1);
        String json = JSON.toJSONString(image);
        String s= WeiChatUtils.httpsRequest(BASE_URL + token,"POST",json);
        JSONObject ans1 = JSONObject.parseObject(s);
        String errcode = ans1.getString("errcode");
        if(errcode.equals("0")){
            return ans1.getString("msgid");
        } else if(errcode.equals("81013")) {
            return USER_ID_ERR;
        }
        return "FAIL";
    }

    @ApiOperation(value = "发送视频消息")
    public static String sendVideoMessage(int company,String userId,String mediaId,String title,String description,String token) {
        WsWeiChatNoticeVideo video = new WsWeiChatNoticeVideo(userId,"video",YH_CORPID,new WsWeChatNoticeVideoItem(mediaId,title,description),0,1);
        String json = JSON.toJSONString(video);
        String s= WeiChatUtils.httpsRequest(BASE_URL + token,"POST",json);
        JSONObject ans1 = JSONObject.parseObject(s);
        String errcode = ans1.getString("errcode");
        if(errcode.equals("0")){
            return ans1.getString("msgid");
        } else if(errcode.equals("81013")) {
            return USER_ID_ERR;
        }
        return "FAIL";
    }

    @ApiOperation(value = "发送文件消息")
    public static String sendFileMessage(int company,String userId,String mediaId,String token) {
        WsWeiChatNoticeFile file = new WsWeiChatNoticeFile(userId,"file",YH_CORPID,new WsWeChatNoticeFileItem(mediaId),0,1);
        String json = JSON.toJSONString(file);
        String s= WeiChatUtils.httpsRequest(BASE_URL + token,"POST",json);
        JSONObject ans1 = JSONObject.parseObject(s);
        String errcode = ans1.getString("errcode");
        if(errcode.equals("0")){
            return ans1.getString("msgid");
        } else if(errcode.equals("81013")) {
            return USER_ID_ERR;
        }
        return "FAIL";
    }

    @ApiOperation(value = "发送文本卡片消息")
    public static String sendTextCardMessage(int company,String userId,String title,String description,String url,String btntxt,String token) {
        WsWeiChatNoticeTextCard file = new WsWeiChatNoticeTextCard(userId,"textcard",YH_CORPID,new WsWeChatNoticeTextCardItem(title,description,url,btntxt),0,1);
        String json = JSON.toJSONString(file);
        String s= WeiChatUtils.httpsRequest(BASE_URL + token,"POST",json);
        JSONObject ans1 = JSONObject.parseObject(s);
        String errcode = ans1.getString("errcode");
        if(errcode.equals("0")){
            return ans1.getString("msgid");
        } else if(errcode.equals("81013")) {
            return USER_ID_ERR;
        }
        return "FAIL";
    }

    @ApiOperation(value = "发送图文消息")
    public static String sendTuWenMessage(String userId,String title,String description,String url,String picUrl,String token) {
        List<WsWeChatNoticeTuWenItemValue> tuWenList = new ArrayList<>();
        tuWenList.add(new WsWeChatNoticeTuWenItemValue(title, description, url, picUrl));
        WsWeChatNoticeTuWen file = new WsWeChatNoticeTuWen(userId,"news","1000002",new WsWeChatNoticeTuWenItem(tuWenList),0,1);
        String json = JSON.toJSONString(file);
        String s= WeiChatUtils.httpsRequest(BASE_URL + token,"POST",json);
        JSONObject ans1 = JSONObject.parseObject(s);
        String errcode = ans1.getString("errcode");
        if(errcode.equals("0")){
            return ans1.getString("msgid");
        } else if(errcode.equals("81013")) {
            return USER_ID_ERR;
        }
        return "FAIL";
    }

    @ApiOperation(value = "发送Markdown消息")
    public static String sendMarkdownMessage(int company,String userId,String content,String token){
        if(content == null || WsNullUtils.isNull(content)) {
            return "NULL";
        }
        String json = JSON.toJSONString(new WsWeChatNoticeMarkdown(userId,"markdown",YH_CORPID,new WsWeChatNoticeMarkdownItem(content),0,1));
        String s= WeiChatUtils.httpsRequest(BASE_URL + token,"POST",json);
        System.out.println(s);
        JSONObject ans1 = JSONObject.parseObject(s);
        String jsonStr2 = ans1.getString("errcode");
        if(jsonStr2.equals("0")){
            return ans1.getString("msgid");
        } else if(jsonStr2.equals("81013")) {
            return USER_ID_ERR;
        }
        return "FAIL";
    }

    /**
     * Markdown消息B类
     */
    @Data
    @AllArgsConstructor
    private static class WsWeChatNoticeMarkdown {
        private String touser;
        private String msgtype;
        private String agentid;
        private WsWeChatNoticeMarkdownItem markdown;
        private int safe;
        private int enable_duplicate_check;
    }

    /**
     * Markdown消息A类
     */
    @Data
    @AllArgsConstructor
    private static class WsWeChatNoticeMarkdownItem {
        private String content;
    }

    /**
     * 图文消息B类
     */
    @Data
    @AllArgsConstructor
    private static class WsWeChatNoticeTuWen {
        private String touser;
        private String msgtype;
        private String agentid;
        private WsWeChatNoticeTuWenItem news;
        private int safe;
        private int enable_duplicate_check;
    }

    /**
     * 图文消息A类
     */
    @Data
    @AllArgsConstructor
    private static class WsWeChatNoticeTuWenItem {
        private List<WsWeChatNoticeTuWenItemValue> articles;
    }

    /**
     * 图文消息A类
     */
    @Data
    @AllArgsConstructor
    private static class WsWeChatNoticeTuWenItemValue {
        private String title;
        private String description;
        private String url;
        private String picurl;
    }

    /**
     * 文本卡片消息B类
     */
    @Data
    @AllArgsConstructor
    private static class WsWeiChatNoticeTextCard {
        private String touser;
        private String msgtype;
        private String agentid;
        private WsWeChatNoticeTextCardItem textcard;
        private int safe;
        private int enable_duplicate_check;
    }

    /**
     * 文本卡片消息A类
     */
    @Data
    @AllArgsConstructor
    private static class WsWeChatNoticeTextCardItem {
        private String title;
        private String description;
        private String url;
        private String btntxt;
    }

    /**
     * 文件消息B类
     */
    @Data
    @AllArgsConstructor
    private static class WsWeiChatNoticeFile {
        private String touser;
        private String msgtype;
        private String agentid;
        private WsWeChatNoticeFileItem file;
        private int safe;
        private int enable_duplicate_check;
    }

    /**
     * 文件消息A类
     */
    @Data
    @AllArgsConstructor
    private static class WsWeChatNoticeFileItem {
        private String media_id;
    }

    /**
     * 视频消息B类
     */
    @Data
    @AllArgsConstructor
    private static class WsWeiChatNoticeVideo {
        private String touser;
        private String msgtype;
        private String agentid;
        private WsWeChatNoticeVideoItem video;
        private int safe;
        private int enable_duplicate_check;
    }

    /**
     * 视频消息A类
     */
    @Data
    @AllArgsConstructor
    private static class WsWeChatNoticeVideoItem {
        private String media_id;
        private String title;
        private String description;
    }

    /**
     * 图片消息B类
     */
    @Data
    @AllArgsConstructor
    private static class WsWeiChatNoticeImage {
        private String touser;
        private String msgtype;
        private String agentid;
        private WsWeChatNoticeImageItem image;
        private int safe;
        private int enable_duplicate_check;
    }

    /**
     * 图片消息A类
     */
    @Data
    @AllArgsConstructor
    private static class WsWeChatNoticeImageItem {
        private String media_id;
    }

    /**
     * 普通文本B类
     */
    @Data
    @AllArgsConstructor
    private static class WsWeChatNoticeInput {
        private String touser;
        private String msgtype;
        private String agentid;
        private WsWeChatNoticeInputItem text;
        private int safe;
        private int enable_duplicate_check;
    }

    /**
     * 普通文本A类
     */
    @Data
    @AllArgsConstructor
    private static class WsWeChatNoticeInputItem {
        private String content;
    }
}
