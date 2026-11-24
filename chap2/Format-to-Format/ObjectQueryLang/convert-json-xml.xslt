<xsl:stylesheet version="3.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:fn="http://www.w3.org/2005/xpath-functions"
    exclude-result-prefixes="fn xs">

    <xsl:output method="xml" indent="yes"/>

    <xsl:param name="doc" as="xs:string" required="yes"/>

    <xsl:variable name="json"
        select="json-to-xml($doc, map{'liberal': true()})"/>

    <xsl:template name="main">
        <students>
        <xsl:for-each select="$json/fn:map/fn:array[@key='students']/fn:map">
                <student gender="{fn:string[@key='gender']}">
                    <id><xsl:value-of select="fn:string[@key='id']"/></id>
                    <lname><xsl:value-of select="fn:string[@key='lname']"/></lname>
                    <fname><xsl:value-of select="fn:string[@key='fname']"/></fname>
                    <birthday><xsl:value-of select="fn:string[@key='birthday']"/></birthday>
                    <address><xsl:value-of select="fn:string[@key='address']"/></address>
                </student>
            </xsl:for-each>
        </students>
    </xsl:template>

</xsl:stylesheet>
