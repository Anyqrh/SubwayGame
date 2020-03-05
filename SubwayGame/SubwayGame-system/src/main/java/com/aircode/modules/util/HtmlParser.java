package com.aircode.modules.util;


import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlParser {


    /**
     *
     * 获取html中的主题和所有回复节点
     *
     * @param url
     * @param ENCODE
     * @return
     */
    protected NodeList getNodelist(String url, String ENCODE) {

        try {
            NodeList nodeList = null;
            Parser parser = new Parser(url);
            parser.setEncoding(ENCODE);
            //定义一个Filter，过滤主题div
            NodeFilter filter = new NodeFilter() {
                @Override
                public boolean accept(Node node) {
                    if (node.getText().contains("style=\"border-bottom: 0px;\"")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            //定义一个Filter，过滤所有回复div
            NodeFilter replyfilter = new NodeFilter() {
                @Override
                public boolean accept(Node node) {
                    String containsString = "id=\"r_";
                    if (node.getText().contains(containsString)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            //组合filter
            OrFilter allFilter = new OrFilter(filter, replyfilter);

            nodeList = parser.extractAllNodesThatMatch(allFilter);
            return nodeList;

        } catch (ParserException e) {
            e.printStackTrace();
            return null;
        }

    }


//
//    public Forum parse2Thread(String url,String ENCODE) {
//        List<Reply> replylist = new ArrayList<Reply>();	//回复列表
//        Topic topic = new Topic();	//主题
//        NodeFilter divFilter = new NodeClassFilter(Div.class);//div过滤器
//        NodeFilter headingFilter = new NodeClassFilter(HeadingTag.class);//heading过滤器
//        NodeFilter tagFilter = new NodeClassFilter(TagNode.class);//heading过滤器
//
//        NodeList nodeList = this.getNodelist(url, ENCODE);
//
//        //解析node到帖子实体
//        for (int i = 0; i < nodeList.size(); i++) {
//            Node node = nodeList.elementAt(i);
//            if(node.getText().contains("style=\"border-bottom: 0px;\"")) {
//                //如果node是主题
//                NodeList list = node.getChildren();//node的子节点
//                //header div
//                Node headerNode = list.extractAllNodesThatMatch(new NodeClassFilter(Div.class)).elementAt(0);
//                //帖子主题
//                Node h1Node = headerNode.getChildren().extractAllNodesThatMatch(headingFilter).elementAt(0);
//                topic.setTopicName(h1Node.toPlainTextString());
//                //发帖人信息
//                NodeList headerChrildrens = headerNode.getChildren();
//                topic.setAnn_name(headerChrildrens.elementAt(15).toPlainTextString());
//                topic.setTopicDescribe(headerChrildrens.elementAt(16).toPlainTextString());
//                //发帖人头像链接
//                Node frNode = headerChrildrens.extractAllNodesThatMatch(divFilter).elementAt(0);
//                ImageTag imgNode = (ImageTag) frNode.getFirstChild().getFirstChild();
//                topic.setAnn_img(imgNode.getImageURL());
//
//                //cell div
//                Node cellNode = list.extractAllNodesThatMatch(divFilter).elementAt(1);
//                Node topic_content = cellNode.getChildren().extractAllNodesThatMatch(divFilter).elementAt(0);
//                Node markdown_body = topic_content.getChildren().extractAllNodesThatMatch(divFilter).elementAt(0);
//                topic.setTopicBody(markdown_body.toPlainTextString());//暂时不包含连接和图片纯文本
//
//            } else if(node.getText().contains("id=\"r_")){
//                //节点是回复
//                Reply reply = new Reply();
//
//                Node tableNode = node.getChildren().extractAllNodesThatMatch(tagFilter).elementAt(0);
//                Node trNode = tableNode.getChildren().extractAllNodesThatMatch(tagFilter).elementAt(0);
//                //回复的tagNodeList
//                NodeList tagList = trNode.getChildren().extractAllNodesThatMatch(tagFilter);
//                ImageTag reply_img = (ImageTag) tagList.elementAt(0).getChildren().extractAllNodesThatMatch(tagFilter).elementAt(0);
//                reply.setReply_img(reply_img.getImageURL());
//                //nodeList bodyNode = tagList;
//                replylist.add(reply);
//            }
//        }
//        System.out.println("-----------实体----------------");
//        Forum forum = new Forum(topic, replylist);
//        System.out.println(forum.toString());
//
//        return null;
//    }
    public static String parseHtml(String html,int length) {

        if (html == null || html == "") {
            return html = "空";
        } else {
            if (html.length() < length) {
                return html;
            } else {
            /*
             * <.*?>为正则表达式，其中的.表示任意字符，*?表示出现0次或0次以上，此方法可以去掉双头标签(双头针对于残缺的标签)
             * "<.*?"表示<尖括号后的所有字符，此方法可以去掉残缺的标签，及后面的内容
             * " "，若有多种此种字符，可用同一方法去除
             */
                html = html.replaceAll("<.*?>", " ").replaceAll("", "");
                html = html.replaceAll("<.*?", "");
                return (html.substring(0, length) + "...");
            }
        }
    }
public static void main(String[] args){
    String s ="<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>";
    System.out.println(parseHtml(s,150));
}

}
