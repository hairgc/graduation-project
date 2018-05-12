package com.github.mahui53541.graduation.vo;

import com.github.mahui53541.graduation.model.ThanksLetter;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/11 12:40
 * @Version: 1.0
 */
public class ThanksLetterVO extends ThanksLetter {

    private String editorName;

    private String thanksName;


    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public String getThanksName() {
        return thanksName;
    }

    public void setThanksName(String thanksName) {
        this.thanksName = thanksName;
    }
}
