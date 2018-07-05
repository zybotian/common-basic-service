package org.open.source.controller;

import org.open.source.util.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tianbo
 * @date 2018-06-29 Friday 10:48
 */
@Controller
@RequestMapping("/file")
@Slf4j
public class FileController {

    @RequestMapping(value = "/download", method = {RequestMethod.GET, RequestMethod.POST})
    public void download(@RequestParam("fileName") String fileName,
                         @RequestParam("fileContent") String fileContent,
                         HttpServletResponse response)throws Exception {
        log.info("download file, file name:{}, file content:{}", fileName, fileContent);
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes(Charset.forName("UTF-8")));
        FileUtils.download(response, inputStream, fileName, MediaType.APPLICATION_OCTET_STREAM.toString());
    }
}
