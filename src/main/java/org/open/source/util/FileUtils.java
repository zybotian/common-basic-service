package org.open.source.util;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tianbo
 * @date 2018-06-29 Friday 11:42
 */
@Slf4j
public class FileUtils {
    public static void download(HttpServletResponse response, InputStream inputStream, String filename, String
            contentType) throws IOException {
        OutputStream outputStream = null;
        try {
            response.setContentType(contentType);
            response.setHeader("Content-Disposition",
                    "attachment; filename*=UTF-8''" + URLEncoder.encode(filename, "utf-8"));
            outputStream = new BufferedOutputStream(response.getOutputStream());

            if (inputStream != null) {
                IOUtils.copy(inputStream, outputStream);
            } else {
                log.warn("copy input stream fail, param inputStream is null!");
            }
        } finally {
            IOUtils.closeQuietly(inputStream);
            // close方法会主动flush
            IOUtils.closeQuietly(outputStream);
        }
    }
}
