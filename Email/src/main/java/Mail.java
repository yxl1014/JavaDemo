import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 邮件的状态机 已读 未读 已删除  现态 条件 动作 次态
 * @author: HammerRay
 * @date:2023/10/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail{

    private String mailId ;
    private String mailSenderId;
    private String mailReceiverId;
    private String mailSubject;
    private String mailMessage;
    /**
    * @decription: 使用Jackson库的JsonNode处理 JSON 数据
    * @author: GodHammer
    * @date: 2023-10-28 下午9:07
    * @version: v1.0
    */
    private JsonNode mailAttachment;
    private Date mailSentDate;
    private int mailType;

    /**
     * @decription: 未读NotRead(0x00)  已读Read(0x01) 已删除Deleted(-0x01)
     * @author: GodHammer
     * @date: 2023-10-28 下午8:54
     * @version: v1.0
     */
    private int mailStatus = 0x00;
    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getMailSenderId() {
        return mailSenderId;
    }

    public void setMailSenderId(String mailSenderId) {
        this.mailSenderId = mailSenderId;
    }

    public String getMailReceiverId() {
        return mailReceiverId;
    }

    public void setMailReceiverId(String mailReceiverId) {
        this.mailReceiverId = mailReceiverId;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailMessage() {
        return mailMessage;
    }

    public void setMailMessage(String mailMessage) {
        this.mailMessage = mailMessage;
    }

    public JsonNode getMailAttachment() {
        return mailAttachment;
    }

    public void setMailAttachment(JsonNode mailAttachment) {
        this.mailAttachment = mailAttachment;
    }

    public Date getMailSentDate() {
        return mailSentDate;
    }

    public void setMailSentDate(Date mailSentDate) {
        this.mailSentDate = mailSentDate;
    }

    public int getMailType() {
        return mailType;
    }

    public void setMailType(int mailType) {
        this.mailType = mailType;
    }

    public int getMailStatus() {
        return mailStatus;
    }

    public void setMailStatus(int mailStatus) {
        this.mailStatus = mailStatus;
    }

    public static void main(String[] args) throws JsonProcessingException {
        Mail mail = new Mail();
        long time = System.currentTimeMillis();
        String jsonString = "{\"name\":\"John\",\"age\":30}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);

        mail.setMailId(String.valueOf(UuidGenerator.getCustomUuid(time)));
        mail.setMailSenderId("111");
        mail.setMailReceiverId("222");
        mail.setMailSubject("这是一封打招呼邮件");
        mail.setMailMessage("你好你好");
        mail.setMailAttachment(jsonNode);
        mail.setMailType(1);
        mail.setMailSentDate(new Date(time));

        System.out.println("mailConcurrentStatus:"+mail.getMailStatus());
        mail.notReadToRead(mail);
        System.out.println("mailConcurrentStatus:"+mail.getMailStatus());
        mail.readToDeleted(mail);
        System.out.println("mailConcurrentStatus:"+mail.getMailStatus());
    }

    public int notReadToRead(Mail mail){


            mail.mailStatus &= 0xFF;
            if(mail.mailStatus != 0){
                //保证是未读状态 不是则不改变状态
                return mail.mailStatus;
            }else {
                mail.mailStatus |= 0x01;
            }
           return mail.mailStatus;
    }

    public int readToDeleted(Mail mail){

           mail.mailStatus &= 0xFF;
           if(mail.mailStatus != 1){
               return mail.mailStatus;
           }else {
               mail.mailStatus |= -0x01;
           }
           return mail.mailStatus;
    }
}
