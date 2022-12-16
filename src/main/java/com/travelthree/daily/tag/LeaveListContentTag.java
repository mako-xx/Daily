package com.travelthree.daily.tag;

import com.travelthree.daily.vo.LeaveVo;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

public class LeaveListContentTag extends SimpleTagSupport {

    private List<LeaveVo> list;

    private static final String contentTemplate = "<a href=\"%s\"\n" +
            "                                               class=\"col-md-12 col-sm-12 col-xs-12 list1-group-item list1-group-item-action\"><i\n" +
            "                                                    class=\"fa fa-book\" aria-hidden=\"true\"\n" +
            "                                                    style=\"float:left; margin-top:10px;\"></i>%s</a>";

    public List<LeaveVo> getList() {
        return list;
    }

    public void setList(List<LeaveVo> list) {
        this.list = list;
    }

    @Override
    public void doTag() throws IOException {
        StringBuilder builder = new StringBuilder();
        for (LeaveVo leaveVo : list) {
            builder.append(String.format(contentTemplate, "/admin/checkleaves?id=" + leaveVo.getId(), leaveVo.getName())).append("\n");
        }
        JspWriter out = getJspContext().getOut();
        out.write(builder.toString());
    }
}
