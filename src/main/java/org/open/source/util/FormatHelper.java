package org.open.source.util;

import com.google.common.base.CaseFormat;

import org.apache.commons.lang3.StringUtils;
import org.open.source.model.Line;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 14:22
 */
public class FormatHelper {

    private static final String SEPERATOR = "`";

    private static final String SINGLE_QUOTE = "'";

    public static void formatToJavaLine(Line line) {
        String javaType = DataTypeMapper.getJavaType(line.getType());
        String columnName = line.getName();
        columnName = StringUtils.replaceAll(columnName, SEPERATOR, "");
        String fieldName = convert2LowerCamel(columnName);
        String comment = StringUtils.replaceAll(line.getComment(), SINGLE_QUOTE, "");
        line.setType(javaType);
        line.setName(fieldName);
        line.setComment(comment);
    }

    /**
     * 将以下划线分割的字符串转换为小驼峰命名
     * @param source 输入示例 my_first_loan
     * @return 输出示例 myFirstLoan
     */
    public static String convert2LowerCamel(String source) {
       return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, source);
    }

    /**
     * 将以下划线分割的字符串转换为大驼峰命名
     * @param source 输入示例 my_first_loan
     * @return 输出示例 MyFirstLoan
     */
    public static String convert2UpperCamel(String source) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, source);
    }
}
