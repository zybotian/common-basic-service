package org.open.source.controller;

import org.apache.commons.lang3.StringUtils;
import org.open.source.exception.ErrorCode;
import org.open.source.exception.ServiceException;
import org.open.source.service.RedisService;
import org.open.source.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    RedisService redisService;

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
    }

    @RequestMapping(value = "/download", method = {RequestMethod.GET, RequestMethod.POST})
    public void download(@RequestParam("fileName") String fileName,
                         @RequestParam("fileContent") String fileContent,
                         HttpServletResponse response) throws Exception {
        log.info("download file, file name:{}, file content:{}", fileName, fileContent);
        InputStream inputStream = new ByteArrayInputStream(fileContent.getBytes(Charset.forName("UTF-8")));
        FileUtils.download(response, inputStream, fileName, MediaType.APPLICATION_OCTET_STREAM.toString());
    }

    @RequestMapping(value = "/download/v20190115174838519", method = {RequestMethod.GET})
    public void download(@RequestParam("uid") String uid,
                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StringUtils.isEmpty(uid)) {
            throw new ServiceException(ErrorCode.INVALID_PARAM_ERROR);
        }
        String fileName = redisService.get(uid);
        if (StringUtils.isEmpty(fileName)) {
            throw new ServiceException(ErrorCode.UNIQUE_CODE_EXPIRED);
        }
        String realPath = request.getServletContext().getRealPath("/files");
        String targetPath = realPath + File.separator + fileName;
        File file = new File(targetPath);
        if (!file.exists()) {
            throw new ServiceException(ErrorCode.FILE_NOT_FOUND);
        }
        InputStream inputStream = new FileInputStream(file);
        FileUtils.download(response, inputStream, fileName, MediaType.APPLICATION_OCTET_STREAM.toString());
    }
}
