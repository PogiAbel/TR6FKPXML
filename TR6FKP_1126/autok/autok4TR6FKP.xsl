<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes" />

    <xsl:template match="/">
        <html>
            <body>
                <h1>Miskolci autok</h1>
                <table border="1">
                    <tr>
                        <th>Rendszám</th>
                        <th>Tulaj</th>
                        <th>Város</th>
                    </tr>
                    <xsl:for-each select="/autok/auto[tulaj/varos = 'Miskolc']">
                        <tr>
                            <td><xsl:value-of select="@rez" /></td>
                            <td><xsl:value-of select="tulaj/nev" /></td>
                            <td><xsl:value-of select="tulaj/varos" /></td>
                        </tr>
                    </xsl:for-each>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>