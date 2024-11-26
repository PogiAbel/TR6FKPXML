<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes" />

    <xsl:template match="/">
        <html>
            <body>
                <h1>4. feladat</h1>
                <table border="1">
                    <tr>
                        <th>Element darab</th>
                    </tr>
                    <tr><td> <xsl:value-of select="count(/autok/auto)"/></td></tr>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>