<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes" />

    <xsl:template match="/">
        <html>
            <body>
                <h1>Feladat 7</h1>
                <table border="1">
                    <tr>
                        <th>Város</th>
                        <th>Darab</th>
                        <th>Összár</th>
                    </tr>
                    <xsl:for-each-group select="/autok/auto" group-by="tulaj/varos">
                        <tr>
                            <td><xsl:value-of select="tulaj/varos" /></td>
                            <td><xsl:value-of select="count(current-group())" /></td>
                            <td><xsl:value-of select="sum(current-group()/ar)" /></td>
                        </tr>
                    </xsl:for-each-group>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>