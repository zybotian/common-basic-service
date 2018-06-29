package org.open.source.controller;

import org.open.source.util.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tianbo
 * @date 2018-06-29 Friday 10:48
 */
@Controller
@RequestMapping("/file")
@Slf4j
public class FileController {

    @RequestMapping(value = "/download", method = {RequestMethod.GET, RequestMethod.POST})
    public void download(HttpServletResponse response) throws Exception {
        String fileContent = "// 自增主键\n" +
                "private long id;\n" +
                "// 小米id\n" +
                "private long userId;\n" +
                "// 奖品类型\n" +
                "private int type;\n" +
                "// 奖品名称\n" +
                "private String name;\n" +
                "// 奖品描述信息\n" +
                "private String description;\n" +
                "// 奖品数量\n" +
                "private int prizeCount;\n" +
                "// 奖品价值\n" +
                "private int prizeValue;\n" +
                "// 创建时间\n" +
                "private long createTime;\n" +
                "// 修改时间\n" +
                "private long updateTime;\n";

        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes(Charset.forName("UTF-8")));
        FileUtils.download(response, inputStream, "download.java", "application/octet-stream");
    }
}
