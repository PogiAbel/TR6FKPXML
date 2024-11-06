<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:strip-space elements="*"/>
    <xsl:output method="xml" encoding="UTF-8" indent="yes" standalone="yes"/>

    <!-- Identity template to copy all nodes and attributes -->
    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <!-- Template to add whitespace after each child of the root node, including comments -->
    <xsl:template match="/*/*|/*/comment()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
        <!-- Add newline after each child or comment of the root node -->
        <xsl:text>&#10;</xsl:text> <!-- Newline instead of a space -->
    </xsl:template>
</xsl:stylesheet>
