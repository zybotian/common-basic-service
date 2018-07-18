package org.open.source.util;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;

import org.apache.commons.lang3.StringUtils;
import org.open.source.model.Line;

import java.util.ArrayList;
import java.util.List;

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
     *
     * @param source 输入示例 my_first_loan
     * @return 输出示例 myFirstLoan
     */
    public static String convert2LowerCamel(String source) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, StringUtils.replace(source, SEPERATOR, ""));
    }

    /**
     * 将以下划线分割的字符串转换为大驼峰命名
     *
     * @param source 输入示例 my_first_loan
     * @return 输出示例 MyFirstLoan
     */
    public static String convert2UpperCamel(String source) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, StringUtils.replace(source, SEPERATOR, ""));
    }

    public static String formatToInsertColumns(List<Line> lines) {
        return Joiner.on(",").join(getNameTokens(lines, true));
    }

    private static List<String> getNameTokens(List<Line> lines, boolean wrapper) {
        List<String> nameTokens = new ArrayList<>();
        for (Line line : lines) {
            // `符号可能有, 也可能没有
            String token = StringUtils.replaceAll(line.getName(), SEPERATOR, "");
            nameTokens.add(wrapper ? StringUtils.join(SEPERATOR, token, SEPERATOR) : token);
        }
        return nameTokens;
    }

    public static String formatToSelectColumns(List<Line> lines) {
        return Joiner.on(",").join(getNameTokens(lines, true));
    }

    public static String formatToInsertValues(List<Line> lines) {
        List<String> nameTokens = getNameTokens(lines, false);
        StringBuilder sb = new StringBuilder();
        for (String token : nameTokens) {
            sb.append(":1." + convert2LowerCamel(token) + ", ");
        }
        return StringUtils.substringBeforeLast(sb.toString(), ",");
    }

    public static String formatToUpdateColumns(List<Line> lines) {
        List<String> nameTokens = getNameTokens(lines, false);
        StringBuilder sb = new StringBuilder();
        for (String token : nameTokens) {
            sb.append(token + "=:1." + convert2LowerCamel(token) + ", ");
        }
        return StringUtils.substringBeforeLast(sb.toString(), ",");
    }

    public static String formatToTableName(String objectName) {
        // `符号可能有可能没有,简单处理,先去掉再加上
        return StringUtils.join(SEPERATOR, StringUtils.replaceAll(objectName, SEPERATOR, ""), SEPERATOR);
    }
}
