package arraylist;

public class Message{
    // receiverid and senderid is already a accountid, so ı implement the extend Account
    private String messageid;
    private String senderid;
    private String receiverid;
    private String content;

    public Message(String messageid, String senderid, Account receiver, String content){
        this.messageid = messageid;
        this.senderid = senderid;
        this.receiverid = receiver.getAccountid();
        this.content = content;
    }
    public String getMessageid(){ return messageid; }
    public String getSenderid(){ return senderid; }
    public String getReceiverid(){ return receiverid; }
    public String getContent(){ return content; }


    
}