<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" />

    <xsl:template match="/">
        <autok>
            <xsl:for-each select="autok/auto">
                <xsl:sort select="ar" order="ascending" />
                <auto rez="{@rez}">
                    <ar><xsl:value-of select="ar"/></ar>
                </auto>
            </xsl:for-each>
        </autok>
    </xsl:template>
</xsl:stylesheet>