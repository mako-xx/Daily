package com.travelthree.daily.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class FooterTag extends SimpleTagSupport {

    private static final String content = "<div>\n" +
            "            <div style=\"height: 50px;\"></div>\n" +
            "            <footer class=\"footer\">\n" +
            "                <div class=\"container-fluid\">\n" +
            "                    <div class=\"social-area pull-right\">\n" +
            "\n" +
            "                        <div class=\"btn-social  btn-pinterest btn-simple\">\n" +
            "                            <a href=\"https://github.com/mako-xx/Daily\" data-toggle=\"tooltip\" data-placement=\"left\"\n" +
            "                               title=\"项目地址\">\n" +
            "                                <div class=\"fa fa-github\"></div>\n" +
            "                            </a>\n" +
            "                        </div>\n" +
            "                        <div class=\"btn-social  btn-pinterest btn-simple\">\n" +
            "                            <a href=\"https://faustpromaxpx.github.io\" data-toggle=\"tooltip\" data-placement=\"left\"\n" +
            "                               title=\"开发者个人网站\">\n" +
            "                                <div class=\"fa fa-user-circle\"></div>\n" +
            "                            </a>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </footer>\n" +
            "        </div>";

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        out.println(content);
    }
}
