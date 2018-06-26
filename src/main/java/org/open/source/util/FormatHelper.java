package org.open.source.util;

import org.apache.commons.lang3.StringUtils;
import org.open.source.factory.mapper.java.Line;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 14:22
 */
public class FormatHelper {

    private static final String TAB = "\t";

    private static final String NEW_LINE = "\n";

    private static final String SEMICOLON = ";";

    public static final String JAVA_LINE_FORMAT = "%s" + TAB + "%s" + SEMICOLON + NEW_LINE;

    private static final String SEPERATOR = "`";

    public static String formatToJavaLine(Line line) {
        String javaType = DataTypeMapper.getJavaType(line.getType());
        String columnName = line.getName();
        columnName = StringUtils.replaceAll(columnName, SEPERATOR, "");
        String fieldName = convertColumnNameToFieldName(columnName);
        return String.format(JAVA_LINE_FORMAT, javaType, fieldName);
    }

    private static String convertColumnNameToFieldName(String source) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            char ch = source.charAt(i);
            if (ch == '_') {
                ch = source.charAt(i + 1);
                sb.append(Character.toUpperCase(ch));
                i++;
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

}
