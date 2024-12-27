package learn.caojw.his.common.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
    private static final HanyuPinyinOutputFormat FORMAT = new HanyuPinyinOutputFormat();

    static {
        FORMAT.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        FORMAT.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        FORMAT.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    public static String toPinyin(String str) {
        StringBuilder pinyin = new StringBuilder();
        try {
            for (char c : str.toCharArray()) {
                pinyin.append(c >= 19968 && c <= 40869 ? PinyinHelper.toHanyuPinyinStringArray(c, FORMAT)[0].charAt(0) : c);
            }
        } catch (BadHanyuPinyinOutputFormatCombination ignored) {
            pinyin.setLength(0);
            pinyin.append(str);
        }
        return pinyin.toString();
    }
}
