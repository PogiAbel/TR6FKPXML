<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes" />

    <xsl:template match="/">
        <html>
            <body>
                <h1>3. feladat</h1>
                <table border="1">
                    <tr>
                        <th>Rendszám</th>
                        <th>Ár</th>
                    </tr>
                    <xsl:for-each select="autok/auto[ar>30000]">
                        <tr><td>
                            <xsl:value-of select="@rez"></xsl:value-of>
                        </td></tr>
                        <tr><td>
                            <xsl:value-of select="ar"></xsl:value-of>
                        </td></tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>