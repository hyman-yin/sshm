package hyman.study.ssh.dao.normal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unchecked")
public class IrisStringUtils {
	/**
	 * 用途：字符串是否为非空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean notEmpty(String str) {
		return str != null && str.length() > 0;
	}

	/**
	 * 用途：字符串除了空白字符外是否有其他字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean hasText(String str) {
		if (!notEmpty(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 用途：字符串是否包含空字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean containsWhitespace(String str) {
		if (!notEmpty(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 用途：删除字符串前后的空白字符
	 * 
	 * @param str
	 * @return
	 */
	public static String trimWhitespace(String str) {
		if (!notEmpty(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
			buf.deleteCharAt(0);
		}
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
	}

	/**
	 * 用途：删除字符串开头的空白字符
	 * 
	 * @param str
	 * @return
	 */
	public static String trimLeadingWhitespace(String str) {
		if (!notEmpty(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}

	/**
	 * 用途：删除字符串末尾的空白字符
	 * 
	 * @param str
	 * @return
	 */
	public static String trimTrailingWhitespace(String str) {
		if (!notEmpty(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
	}

	/**
	 * 用途：删除字符串中的所有空白字符
	 * 
	 * @param str
	 * @return
	 */
	public static String trimAllWhitespace(String str) {
		if (!notEmpty(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		int index = 0;
		while (buf.length() > index) {
			if (Character.isWhitespace(buf.charAt(index))) {
				buf.deleteCharAt(index);
			} else {
				index++;
			}
		}
		return buf.toString();
	}

	/**
	 * 用途：计算子字符串在字符串中出现的次数
	 * 
	 * @param str
	 *            字符串
	 * @param sub
	 *            子字符串
	 * @return
	 */
	public static int countOccurrencesOf(String str, String sub) {
		if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
			return 0;
		}
		int count = 0, pos = 0, idx = 0;
		while ((idx = str.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}

	/**
	 * 用途：字符串数组中添加字符串
	 * 
	 * @param array
	 * @param str
	 * @return
	 */
	public static String[] addStringToArray(String[] array, String str) {
		if (ObjectUtils.isEmpty(array)) {
			return new String[] { str };
		}
		// 数组长度不可变，因此需要新数组
		String[] newArr = new String[array.length + 1];
		System.arraycopy(array, 0, newArr, 0, array.length);
		newArr[array.length] = str;
		return newArr;
	}

	/**
	 * 用途：拼接两个字符串数组
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */
	public static String[] concatenateStringArrays(String[] array1, String[] array2) {
		if (ObjectUtils.isEmpty(array1)) {
			return array2;
		}
		if (ObjectUtils.isEmpty(array2)) {
			return array1;
		}
		String[] newArr = new String[array1.length + array2.length];
		System.arraycopy(array1, 0, newArr, 0, array1.length);
		System.arraycopy(array2, 0, newArr, array1.length, array2.length);
		return newArr;
	}

	/**
	 * 用途：拼接两个字符串数组，重复元素只出现一次
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */
	public static String[] mergeStringArrays(String[] array1, String[] array2) {
		if (ObjectUtils.isEmpty(array1)) {
			return array2;
		}
		if (ObjectUtils.isEmpty(array2)) {
			return array1;
		}

		Set<String> set = new HashSet<>();
		List<String> list1 = Arrays.asList(array1);
		List<String> list2 = Arrays.asList(array2);

		set.addAll(list1);
		set.addAll(list2);

		return toStringArray(set);
	}

	/**
	 * 用途：把集合转换成字符串数组
	 * 
	 * @param collection
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String[] toStringArray(Collection collection) {
		if (collection == null) {
			return null;
		}
		return (String[]) collection.toArray(new String[collection.size()]);
	}

	/**
	 * 用途：去掉字符串数组中的重复元素，重复元素只出现一次
	 * 
	 * @param array
	 * @return
	 */
	public static String[] removeDuplicateStrings(String[] array) {
		if (ObjectUtils.isEmpty(array)) {
			return array;
		}
		Set<String> set = new HashSet<>();
		for (String element : array) {
			set.add(element);
		}
		return toStringArray(set);
	}

	/**
	 * 用途：判断字符串是否为空，用于Strint.valueOf的情况，因为valueof会把null转化成字符串"null"
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrBlank(String str) {
		if (str == null || str.trim().equals("") || str.trim().equals("null")) {
			return true;
		}

		return false;
	}

	/**
	 * 用户String提供的split方法会过滤掉后面的空字符串，所以提供本方法，严格按照分隔符个数形成数组
	 * 
	 * 例如有一个字符串"1,2,3,4,,,"，使用原生string的split形成的字符串数组为{"1","2","3","4"}，
	 * 而用本函数则返回数组{"1","2","3","4","",""}
	 * 
	 * @param input
	 *            本分割的字符串或者StringBuffer对象
	 * @param delimiterPattern
	 *            分割字符
	 * @param limit
	 *            参数控制模式应用的次数，因此影响结果数组的长度。如果该限制 n 大于 0，则模式将被最多应用 n - 1
	 *            次，数组的长度将不会大于 n，而且数组的最后项将包含超出最后匹配的定界符的所有输入。如果 n
	 *            为非正，则模式将被应用尽可能多的次数，而且数组可以是任意长度。如果 n
	 *            为零，则模式将被应用尽可能多的次数，数组可有任何长度，并且结尾空字符串将被丢弃。
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String[] split(CharSequence input, String delimiterPattern, int limit) {
		int index = 0;
		boolean matchLimited = limit > 0;
		ArrayList matchList = new ArrayList();
		Pattern p = Pattern.compile(delimiterPattern);
		Matcher m = p.matcher(input);

		while (m.find()) {
			if (!matchLimited || matchList.size() < limit - 1) {
				String match = input.subSequence(index, m.start()).toString();
				matchList.add(match);
				index = m.end();
			} else if (matchList.size() == limit - 1) { // last one
				String match = input.subSequence(index, input.length()).toString();
				matchList.add(match);
				index = m.end();
			}
		}

		if (index == 0) {
			return new String[] { input.toString() };
		}

		if (!matchLimited || matchList.size() < limit) {
			matchList.add(input.subSequence(index, input.length()).toString());
		}

		int resultSize = matchList.size();
		String[] result = new String[resultSize];
		return (String[]) matchList.subList(0, resultSize).toArray(result);
	}

	/**
	 * 用途：判断一个字符串中是否包含一个子串,不区分大小写
	 * 
	 * @param str
	 * @param subString
	 * @return
	 */
	public static boolean isIncludeSubString(String str, String subString) {
		if (str == null || subString == null) {
			return false;
		}

		return str.toLowerCase().contains(subString.toLowerCase());
	}

	public static String toString(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	/**
	 * 用途：字符串替换
	 * 
	 * @param strSource
	 * @param strFrom
	 * @param strTo
	 * @return
	 */
	public static String replaceStr(String strSource, String strFrom, String strTo) {
		// 如果要替换的子串为空，则直接返回源串
		if (strFrom == null || strFrom.equals("")) {
			return strSource;
		}
		return strSource.replace(strFrom, strTo);
	}

	/**
	 * 用途：如果字符串不符合规定长度，在字符串左边补充指定字符
	 * 
	 * @param s
	 *            原字符串
	 * @param len
	 *            len字符串应有长度
	 * @param pad_ch
	 *            pad_ch指定补充字符
	 * @return
	 */
	public static String padLeft(String s, int len, char pad_ch) {
		if (s.length() >= len) {
			return s;
		} else {
			StringBuilder sb = new StringBuilder();
			int n = len - s.length();
			for (int i = 0; i < n; i++) {
				sb.append(pad_ch);
			}
			sb.append(s);
			return sb.toString();
		}
	}

	/**
	 * 用途：右边补齐
	 * 
	 * @param s
	 * @param len
	 * @param pad_ch
	 * @return
	 */
	public static String padRight(String s, int len, char pad_ch) {
		if (s.length() >= len) {
			return s;
		} else {
			StringBuilder sb = new StringBuilder();
			int n = len - s.length();
			sb.append(s);
			for (int i = 0; i < n; i++) {
				sb.append(pad_ch);
			}
			return sb.toString();
		}
	}

	/**
	 * 用途：字符串转化为list
	 * @param str
	 * @param regex
	 * @return
	 */
	public static List<String> splitStrToList(String str, String regex) {
		List<String> list = new ArrayList<String>();
		if (str == null) {
			return list;
		}
		if (regex == null) {
			regex = ",";
		}
		String[] strAry = str.split(regex);
		return Arrays.asList(strAry);
	}

	
	/**
	 * 用途：判断字符串是否只包含数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
}