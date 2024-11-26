<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes" />

    <xsl:template match="/">
        <html>
            <body>
                <h1>Feladat 6</h1>
                <table border="1">
                    <tr>
                        <th>Típus</th>
                        <th>Darab</th>
                    </tr>
                    <xsl:for-each-group select="/autok/auto" group-by="tipus">
                    <xsl:sort select="count(current-group())" order="descending"/>
                        <tr>
                            <td><xsl:value-of select="tipus" /></td>
                            <td><xsl:value-of select="count(current-group())" /></td>
                        </tr>
                    </xsl:for-each-group>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>