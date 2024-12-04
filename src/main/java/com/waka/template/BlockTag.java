package com.waka.template;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;

import static com.waka.template.BlockTagUtils.*;

public class BlockTag extends SimpleTagSupport {
    public static final PutType DEFAULT_PUT_TYPE = PutType.APPEND;

//    Block Name
    private String name;

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void doTag() throws JspException {
        PageContext pageContext = (PageContext)getJspContext();

        PutType putType = getPutType(pageContext);

        String bodyResult = null;
        try {
            bodyResult = getBodyResult(getJspBody());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String putContents = getPutContents(pageContext);

        try {
            putType.write(pageContext.getOut(), bodyResult, putContents);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PutType getPutType(PageContext pageContext) {
        PutType putType = (PutType) pageContext.findAttribute(getBlockTypeAttributeName(name));
        if (putType == null) {
            return DEFAULT_PUT_TYPE;
        }
        return putType;
    }

    private String getPutContents(PageContext pageContext) {
        String putContents = (String) pageContext.findAttribute(getBlockContentsAttributeName(name));
        if (putContents == null) {
            return "";
        }
        return putContents;
    }
}
