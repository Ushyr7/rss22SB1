<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl = "http://www.w3.org/1999/XSL/Transform" xmlns:p="http://univrouen.fr/rss22" 
xmlns:fn="http://www.w3.org/2005/xpath-function"
version="3.0">

	<xsl:output method="html" 
	doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
	doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
	omit-xml-declaration="yes"
	encoding="UTF-8"
	indent="yes"/>
        
	<xsl:template match="/">
		<xsl:element name="html">
			<xsl:element name="head">
				<xsl:element name="title"> RSS22SB1 </xsl:element> 
			</xsl:element>
			<xsl:element name="body">
				<xsl:element name="h1"> Flux RSS22 </xsl:element>
				<xsl:element name="p"> Le   <xsl:value-of select="fn:format-date(fn:current-date(),'[M01]/[D01]/[Y0001]')"/></xsl:element>
				<xsl:call-template name="header"/>
				<xsl:call-template name="summary"/>
			</xsl:element>
		</xsl:element>
	</xsl:template>
	
	<xsl:template name="header">
		<xsl:element name="h2">Description</xsl:element>
		<xsl:call-template name="createList"/>
	</xsl:template>
	
	<xsl:template name="createList">
		<xsl:if test="count(p:feed) > 0">
			<xsl:element name="ul">
				<xsl:element name="li">Contenu <xsl:value-of select="p:feed/p:title"/></xsl:element>
				<xsl:element name="li">Publié le <xsl:value-of select="p:feed/p:pubDate"/></xsl:element>
				<xsl:element name="li">Copyright <xsl:value-of select="p:feed/p:copyright"/></xsl:element>	
			</xsl:element>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="summary">
		<xsl:element name="h2">Sommaire</xsl:element>
		<xsl:element name="ol">
				<xsl:element name="li"><xsl:value-of select="p:feed/p:item/p:title"/></xsl:element>
		</xsl:element>
		<xsl:element name="div"> <xsl:value-of select="count(p:feed)"/> sujets</xsl:element>
	</xsl:template>
	
	
	
</xsl:stylesheet>