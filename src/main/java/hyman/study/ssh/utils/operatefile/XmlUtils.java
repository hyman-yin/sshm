package hyman.study.ssh.utils.operatefile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 读取和操作XML文件
 * 
 * @author Administrator
 *
 * 包括如下方法： 
 * 1，读取节点的值 
 * 2，新增节点：
 *      2.1，在/hyman/user节点下添加子节点:根据子节点名称和内容添加；根据子节点xml添加
 *      2.2，为/hyman/user/username节点添加兄弟节点/hyman/user/usercode
 *      2.3，为节点/hyman/user/username添加父节点/hyman/user/usermanage，那么原来的节点路径变为：/hyman/user/usermanage/username 
 * 3，删除节点 
 * 4，设置节点的值
 *      4.1，设置叶子节点/hyman/user/username的值 
 *      4.2，设置非叶子结点（即存在子节点）/hyman/user的值
 *      	4.2.1，删除原来的子节点和子节点的子节点，设置文本值 
 *      	4.2.2，删除原来的子节点和子节点的子节点，设置新的子节点
 * 5，把xml文件转化为string输出 
 * 6，把string转化为xml输出
 *
 */

public class XmlUtils {
	/**
	 * 根据文件路径获取document对象
	 * @param filePath xml文件路径
	 * @return
	 * @throws Exception
	 */
	public static Document getDocument(String filePath) {
		String realPath = FileUtils.getRealPath(filePath);
		SAXReader builder = new SAXReader();
		Document document=null;
		try {
			InputStream in = new FileInputStream(realPath);
			InputStreamReader reader = new InputStreamReader(in, "UTF-8");
			document = builder.read(reader);
		} catch (Exception e) {
			System.out.println("获取document对象出错！");
			e.printStackTrace();
		}
		return document;
	}

	
	/**
	 * 写入xml文件
	 * @param document  document对象
	 * @param filePath  xml文件路径
	 */
	public static void writeDocument(Document document,String filePath){
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		try {
			XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(FileUtils.getRealPath(filePath))), format);
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			System.out.println("写入xml文件失败");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 根据节点路径获取节点值
	 * @param filePath  xml文件路径
	 * @param nodePath  节点路径
	 * @return
	 */
	public static String getNodeValueByPath(String filePath, String nodePath) {
		Document document = getDocument(filePath);
		if(document==null){
			return null;
		}
		Element element = (Element) document.selectSingleNode(nodePath);
		System.out.println(nodePath + ": " + element.getText());
		return element.getText();
	}

	
	/**
	 * 添加子节点--传入子节点名称和内容进行添加，一次只能添加一个子节点，并且只能添加一层
	 * @param filePath  文件路径
	 * @param nodePath  要添加子节点的路径
	 * @param nodeName  添加的字节点名称
	 * @param text  添加的子节点内容
	 */
	public static void addChildNodeByNodeAndValue(String filePath, String nodePath, String nodeName,String text) {
		Document document = getDocument(filePath);
		if(document==null){
			return;
		}
		try {
			Element element = (Element) document.selectSingleNode(nodePath);
			element.addElement(nodeName).setText(text);
			writeDocument(document, filePath);
		} catch (Exception e) {
			System.out.println("添加子节点失败。。。");
			e.printStackTrace();
		}
	}

	
	public static void addChildNodeByNodeAndValue(Document document, String nodePath, String nodeName,String text) {
		try {
			Element element = (Element) document.selectSingleNode(nodePath);
			element.addElement(nodeName).setText(text);
		} catch (Exception e) {
			System.out.println("添加子节点失败。。。");
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加子节点--传入子节点xml字符串 进行添加，一次只能添加一个子节点，但是可以添加多层
	 * @param filePath
	 * @param nodePath
	 * @param childStr
	 */
	public static void addChildNodeByChildStr(String filePath, String nodePath, String childStr) {
		Document document = getDocument(filePath);
		if(document==null){
			return;
		}
		try {
			if(StringUtils.isBlank(childStr) || !childStr.startsWith("<")){
				return;
			}
			Element element = (Element) document.selectSingleNode(nodePath);
			Document childDocument = DocumentHelper.parseText(childStr);
			Node node=childDocument.getRootElement();
			element.add(node);
			
			writeDocument(document, filePath);
		} catch (Exception e) {
			System.out.println("添加子节点失败。。。");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 添加子节点--传入子节点xml字符串 进行添加，一次只能添加一个子节点，但是可以添加多层
	 * @param document  document对象
	 * @param nodePath  节点路径
	 * @param childStr  子节点字符串
	 * @throws DocumentException 
	 */
	public static void addChildNodeByChildStr(Document document, String nodePath, String childStr) throws DocumentException {
		if(StringUtils.isBlank(childStr) || !childStr.startsWith("<")){
			return;
		}
		Element element = (Element) document.selectSingleNode(nodePath);
		Document childDocument = DocumentHelper.parseText(childStr);
		Node node=childDocument.getRootElement();
		element.add(node);
	}
	
	
	/**
	 * 为指定节点添加兄弟节点
	 * @param filePath  xml文件路径
	 * @param nodePath  要添加兄弟节点的节点路径
	 * @param brotherStr  要添加的兄弟节点的xml字符串，例如：<usercode>12345678</usercode>
	 */
	public static void addBrotherNodeByNodeStr(String filePath, String nodePath, String brotherStr){
		Document document = getDocument(filePath);
		if(document==null){
			return;
		}
		try {
			if(StringUtils.isBlank(brotherStr) || !brotherStr.startsWith("<")){
				return;
			}
			if(StringUtils.isBlank(nodePath)){
				return;
			}
			Element element = (Element) document.selectSingleNode(nodePath);
			
			Document brotherDocument = DocumentHelper.parseText(brotherStr);
			Node node=brotherDocument.getRootElement();
			if(element.getParent()==null){
				System.out.println("不能为根节点添加兄弟节点");
				return;
			}
			element.getParent().add(node);
			
			writeDocument(document, filePath);
		} catch (Exception e) {
			System.out.println("添加子节点失败。。。");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 为指定节点添加父节点,原理类似链表插入节点
	 * @param filePath  xml文件路径
	 * @param nodePath  节点路径
	 * @param parentXmlStr  要添加的父节点的xml字符串
	 */
	public static void addParentNodeByParentXmlStr(String filePath,String nodePath,String parentXmlStr) {
		Document document = null;
		try {
			document = getDocument(filePath);
			if(document==null){
				return;
			}
			Element element = (Element) document.selectSingleNode(nodePath);
			Element formerParentElement = element.getParent();
			
			//代表是根节点
			if(formerParentElement==null){
				System.out.println("不能为根节点添加父节点。。。");
				return;
			} else {
				//创建新的父节点
				Element newParentElement=DocumentHelper.parseText(parentXmlStr).getRootElement();
				//删除当前节点和它的子节点
				element.detach();
				
				//新的父节点添加原来的节点
				newParentElement.add(element);
				//节点的原来的父节点添加新的父节点
				formerParentElement.add(newParentElement);
			}
		     
		     writeDocument(document, filePath);
		 } catch (Exception e){
		     e.printStackTrace();
		 }
	}

	
	/**
	 * 根据节点路径删除节点
	 * @param filePath  xml文件路径
	 * @param nodePath  节点路径
	 */
	public static void removeNodeByXPath(String filePath, String nodePath){
		Document document = getDocument(filePath);
		if(document==null){
			return;
		}
		try {
			if(StringUtils.isBlank(nodePath)){
				return;
			}
			Element element = (Element) document.selectSingleNode(nodePath);
			element.detach();
			
			writeDocument(document, filePath);
		} catch (Exception e) {
			System.out.println("删除子节点失败。。。");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 根据节点路径删除节点
	 * @param filePath  xml文件路径
	 * @param nodePath  节点路径
	 */
	public static void removeNodeByXPath(Document document, String nodePath){
		if(StringUtils.isBlank(nodePath)){
			return;
		}
		Element element = (Element) document.selectSingleNode(nodePath);
		element.detach();
	}
	
	
	
	/**
	 * 删除指定节点的所有子节点
	 * @param filePath
	 * @param nodePath
	 */
	public static void removeChildNodeByXPath(String filePath, String nodePath){
		Document document = getDocument(filePath);
		if(document==null){
			return;
		}
		try {
			if(StringUtils.isBlank(nodePath)){
				return;
			}
			Element element = (Element) document.selectSingleNode(nodePath);
			@SuppressWarnings("unchecked")
			List<Element> list = element.elements();
			if(list!=null){
				for(Element el : list){
					el.detach();
				}
			}
			
			writeDocument(document, filePath);
		} catch (Exception e) {
			System.out.println("添加子节点失败。。。");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除指定节点的所有子节点
	 * @param filePath
	 * @param nodePath
	 */
	public static void removeChildNodeByXPath(Document document, String nodePath){
		if(StringUtils.isBlank(nodePath)){
			return;
		}
		Element element = (Element) document.selectSingleNode(nodePath);
		@SuppressWarnings("unchecked")
		List<Element> list = element.elements();
		if(list!=null){
			for(Element el : list){
				el.detach();
			}
		}
	}
	
	/**
	 * 根据节点路径修改叶子节点内容
	 * 
	 * @param filePath
	 *            xml文件相对于根目录的路径,例如：xml/test.xml
	 * @param nodePath
	 *            节点路径，例如：/hyman/user/username
	 * @param text
	 *            要更新的节点内容
	 */
	public static void setLeafNodeValue(String filePath, String nodePath, String text) {
		Document document = getDocument(filePath);
		if(document==null){
			return;
		}

		try {
			Element element = (Element) document.selectSingleNode(nodePath);
			element.setText(text);

			writeDocument(document, filePath);
		} catch (Exception e) {
			System.out.println("设置节点值失败。。。nodepath: " + nodePath);
			e.printStackTrace();
		}
	}

	/**
	 * 根据节点路径修改叶子节点内容
	 * 
	 * @param filePath  xml文件路径
	 * @param nodePath  节点路径
	 * @param text  更新后的节点内容
	 */
	public static void setLeafNodeValue(Document document, String nodePath, String text) {
		Element element = (Element) document.selectSingleNode(nodePath);
		element.setText(text);
	}
	
	/**
	 * 根据节点路径和新增内容字符串修改非叶子节点内容,删除原来子节点，设置新的text值
	 * 
	 * @param filePath  xml文件路径
	 * @param nodePath  节点路径
	 * @param text  设置后的节点内容
	 */
	public static void setNonLeafNodeValueToText(String filePath, String nodePath, String text) {
		Document document = getDocument(filePath);
		if(document==null){
			return;
		}

		try {
			removeChildNodeByXPath(document, nodePath);
			setLeafNodeValue(document, nodePath, text);
			
			writeDocument(document, filePath);
		} catch (Exception e) {
			System.out.println("设置节点值失败。。。nodepath: " + nodePath);
			e.printStackTrace();
		}
	}

	
	/**
	 * 设置非叶子结点的值，删除原来的子节点，设置新的子节点
	 * @param filePath  xml文件路径
	 * @param nodePath  节点路径
	 * @param childStr  新的子节点的xml字符串
	 */
	public static void setNonLeafNodeValueToNewChild(String filePath, String nodePath, String childStr) {
		Document document = getDocument(filePath);
		if(document==null){
			return;
		}

		try {
			removeChildNodeByXPath(document, nodePath);
			addChildNodeByChildStr(document, nodePath, childStr);
			
			writeDocument(document, filePath);
		} catch (Exception e) {
			System.out.println("设置节点值失败。。。nodepath: " + nodePath);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * xml对象转化为字符串
	 * @param filePath
	 * @return
	 */
	public static String xmlToStr(String filePath){
		Document document = getDocument(filePath);
		if(document==null){
			return "";
		}
		
		return document.asXML();
	}
	
	
	/**
	 * 把xml格式的字符串转化为xml对象
	 * @param xmlStr
	 * @param filePath
	 * @return
	 */
	public static Document strToXml(String xmlStr,String filePath){
		xmlStr = preTreatXmlStr(xmlStr);
		Document document=null;
		try {
			document=DocumentHelper.parseText(xmlStr);
			writeDocument(document, filePath);
		} catch (Exception e) {
			System.out.println("字符串转化为Document对象失败");
			e.printStackTrace();
		}
		
		return document;
	}
	
	
	/**
	 * 预处理xml字符串，去掉乱码特殊字符，加上xml抬头
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static String preTreatXmlStr(String xmlStr) {
		if (StringUtils.isBlank(xmlStr) || "null".equalsIgnoreCase(xmlStr)) {
			xmlStr = "<hyman></hyman>";
		}
		// 去掉乱码
		xmlStr.replace("�", "");
		// 添加xml文件开头
		if (!xmlStr.startsWith("<?xml")) {
			xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + xmlStr;
		}

		return xmlStr;
	}

}
