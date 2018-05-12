package com.github.mahui53541.graduation.vo;

import com.github.mahui53541.graduation.model.Found;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/11 16:05
 * @Version: 1.0
 */
public class FoundVO extends Found {
    /**
     * Base64,图片
     */
    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
