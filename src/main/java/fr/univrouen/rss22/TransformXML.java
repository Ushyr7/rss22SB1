package fr.univrouen.rss22;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

public class TransformXML {

	  public String transform (String xml) throws TransformerException
	    {
	            StringReader xmlReader = new StringReader(xml);
	            StreamSource xmlSource = new StreamSource(xmlReader);

	            StringReader xslReader = new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
	            		+ "<xsl:stylesheet xmlns:xsl = \"http://www.w3.org/1999/XSL/Transform\" xmlns:p=\"http://univrouen.fr/rss22\" \n"
	            		+ "xmlns:fn=\"http://www.w3.org/2005/xpath-function\"\n"
	            		+ "version=\"3.0\">\n"
	            		+ "\n"
	            		+ "	<xsl:output method=\"html\" \n"
	            		+ "	doctype-public=\"-//W3C//DTD XHTML 1.0 Strict//EN\"\n"
	            		+ "	doctype-system=\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"\n"
	            		+ "	omit-xml-declaration=\"yes\"\n"
	            		+ "	encoding=\"UTF-8\"\n"
	            		+ "	indent=\"yes\"/>\n"
	            		+ "        \n"
	            		+ "	<xsl:template match=\"/\">\n"
	            		+ "		<xsl:element name=\"html\">\n"
	            		+ "			<xsl:element name=\"head\">\n"
	            		+ "				<xsl:element name=\"title\"> RSS22SB1 </xsl:element> \n"
	            		+ "			</xsl:element>\n"
	            		+ "			<xsl:element name=\"body\">\n"
	            		+ "				<xsl:element name=\"h1\"> Flux RSS22 </xsl:element>\n"
	            		+ "				<xsl:element name=\"p\"> Le   <xsl:value-of select=\"fn:format-date(fn:current-date(),'[M01]/[D01]/[Y0001]')\"/></xsl:element>\n"
	            		+ "				<xsl:call-template name=\"header\"/>\n"
	            		+ "				<xsl:call-template name=\"summary\"/>\n"
	            		+ "			</xsl:element>\n"
	            		+ "		</xsl:element>\n"
	            		+ "	</xsl:template>\n"
	            		+ "	\n"
	            		+ "	<xsl:template name=\"header\">\n"
	            		+ "		<xsl:element name=\"h2\">Description</xsl:element>\n"
	            		+ "		<xsl:call-template name=\"createList\"/>\n"
	            		+ "	</xsl:template>\n"
	            		+ "	\n"
	            		+ "	<xsl:template name=\"createList\">\n"
	            		+ "		<xsl:if test=\"count(p:feed) > 0\">\n"
	            		+ "			<xsl:element name=\"ul\">\n"
	            		+ "				<xsl:element name=\"li\">Contenu <xsl:value-of select=\"p:feed/p:title\"/></xsl:element>\n"
	            		+ "				<xsl:element name=\"li\">Publié le <xsl:value-of select=\"p:feed/p:pubDate\"/></xsl:element>\n"
	            		+ "				<xsl:element name=\"li\">Copyright <xsl:value-of select=\"p:feed/p:copyright\"/></xsl:element>	\n"
	            		+ "			</xsl:element>\n"
	            		+ "		</xsl:if>\n"
	            		+ "	</xsl:template>\n"
	            		+ "	\n"
	            		+ "	<xsl:template name=\"summary\">\n"
	            		+ "		<xsl:element name=\"h2\">Sommaire</xsl:element>\n"
	            		+ "		<xsl:element name=\"ol\">\n"
	            		+ "				<xsl:element name=\"li\"><xsl:value-of select=\"p:feed/p:item/p:title\"/></xsl:element>\n"
	            		+ "		</xsl:element>\n"
	            		+ "		<xsl:element name=\"div\"> <xsl:value-of select=\"count(p:feed)\"/> sujets</xsl:element>\n"
	            		+ "	</xsl:template>\n"
	            		+ "	\n"
	            		+ "	\n"
	            		+ "	\n"
	            		+ "</xsl:stylesheet>");
	            StreamSource xslSource = new StreamSource(xslReader);

	            TransformerFactory factory  = TransformerFactory.newInstance();
	            Transformer transformer = factory.newTransformer(xslSource);

	            StringWriter resultWriter = new StringWriter();
	            transformer.transform(xmlSource, new javax.xml.transform.stream.StreamResult(resultWriter));
	            return resultWriter.toString();
	    }
	
	
}
