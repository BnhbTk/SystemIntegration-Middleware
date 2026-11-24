<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="3.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://w3.org"
    exclude-result-prefixes="xs">

    <!-- Instruct the processor to output valid JSON -->
    <xsl:output method="text" encoding="UTF-8" media-type="application/json"/>

    <xsl:template match="/students">
        <!-- Open the root JSON object -->
        {
            "students": [
                <xsl:for-each select="student">
                    {
                        "id": "<xsl:value-of select="id"/>",
                        "lname":"<xsl:value-of select="lname"/>",
                        "fname": "<xsl:value-of select="fname"/>",
                        "gender": "<xsl:value-of select="@gender"/>",
                        "birthday": "<xsl:value-of select="birthday"/>",
                        "address": "<xsl:value-of select="address"/>"
                    }
                    <!-- Add a comma if it's not the last element in the array -->
                    <xsl:if test="position() != last()">,</xsl:if>
                </xsl:for-each>
            ]
        }
    </xsl:template>

</xsl:stylesheet>
