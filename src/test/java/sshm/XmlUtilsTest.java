package sshm;

import org.junit.Test;

import hyman.study.ssh.utils.operatefile.XmlUtils;

/**
 * XmlUtils测试类
 * @author hyman
 *
 */
public class XmlUtilsTest {

	@Test
	public void testGetNodeValueByPath() {
		String filePath = "xml/test.xml";
		String nodePath="/hyman/user/username";
		try {
			XmlUtils.getNodeValueByPath(filePath, nodePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddChildNode(){
		String filePath = "xml/test.xml";
		String nodePath = "hyman/user";
		String nodeName="mobile";
		String text="18888888888";
		try {
			XmlUtils.addChildNodeByNodeAndValue(filePath, nodePath, nodeName, text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testAddChildNodeByChildStr(){
		String filePath = "xml/test.xml";
		String nodePath = "hyman/user";
		String childStr="<teacher><name>zhangsan</name><mobile>18889898989</mobile></teacher>";
		try {
			XmlUtils.addChildNodeByChildStr(filePath, nodePath, childStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testAddBrotherNodeByNodeStr(){
		String filePath = "xml/test.xml";
		String nodePath = "hyman/user/username";
		String brotherStr="<usercode>12345678</usercode>";
		try {
			XmlUtils.addBrotherNodeByNodeStr(filePath, nodePath, brotherStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveNodeByXPath(){
		String filePath = "xml/test.xml";
		String nodePath = "hyman/user";
		try {
			XmlUtils.removeNodeByXPath(filePath, nodePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void testRemoveChildNodeByXPath(){
		String filePath = "xml/test.xml";
		String nodePath = "hyman/user";
		try {
			XmlUtils.removeChildNodeByXPath(filePath, nodePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void testSetLeafNodeValue(){
		String filePath = "xml/test.xml";
		String nodePath = "hyman/user/password";
		String text="hello world";
		XmlUtils.setLeafNodeValue(filePath, nodePath, text);
	}
	
	
	
	@Test
	public void testSetNonLeafNodeValueToText(){
		String filePath = "xml/test.xml";
		String nodePath = "hyman/user";
		String text="hello world";
		XmlUtils.setNonLeafNodeValueToText(filePath, nodePath, text);
	}
	
	
	
	@Test
	public void testSetNonLeafNodeValueToNewChild(){
		String filePath = "xml/test.xml";
		String nodePath = "hyman/user";
		String childStr="<userinfo><usercode>12345678</usercode><addr>beijing</addr></userinfo>";
		XmlUtils.setNonLeafNodeValueToNewChild(filePath, nodePath, childStr);
	}
	
	
	@Test
	public void testXmlToStr(){
		String filePath = "xml/test.xml";
		String xmlStr=XmlUtils.xmlToStr(filePath);
		System.out.println(xmlStr);
	}
	
	
	
	@Test
	public void testStrToXml(){
		String xmlStr="<hyman><user><userinfo><usercode>88888888</usercode><addr>beijing</addr></userinfo></user></hyman>";
		XmlUtils.strToXml(xmlStr,"xml/test2.xml");
	}
	
	
	@Test
	public void testAddParentNodeByXmlStr(){
		String filePath = "xml/test.xml";
		String nodePath = "hyman/user/username";
		String parentStr = "<userinfo></userinfo>";
		XmlUtils.addParentNodeByParentXmlStr(filePath, nodePath, parentStr);
	}
}
