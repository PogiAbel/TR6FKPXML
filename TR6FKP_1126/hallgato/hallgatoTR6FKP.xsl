<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <!-- <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/> -->
    <xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Hallgatok adatai - for-each, value-of</title>
            </head>
            <body>
                <h1>Hallgatok adatai - for-each, value-of</h1>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Vezetéknév</th>
                        <th>Keresztnév</th>
                        <th>Becenév</th>
                        <th>Kor</th>
                        <th>Ösztöndíj</th>
                    </tr>
                    <xsl:for-each select="/class/student">
                        <tr>
                            <th><xsl:value-of select="@id"/></th>
                            <td><xsl:value-of select="vezeteknev"/></td>
                            <td><xsl:value-of select="keresztnev"/></td>
                            <td><xsl:value-of select="becenev"/></td>
                            <td><xsl:value-of select="kor"/></td>
                            <td><xsl:value-of select="osztondij"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>