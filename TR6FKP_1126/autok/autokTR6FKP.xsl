<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes" />

    <xsl:template match="/">
        <html>
            <body>
                <h1>Autók rendszámai</h1>
                <table border="1">
                    <tr>
                        <th>Rendszám</th>
                    </tr>
                    <xsl:for-each select="autok/auto">
                        <tr><td>
                            <xsl:value-of select="@rez"></xsl:value-of>
                        </td></tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>


<!-- <autok>
    <auto rez="ABC-100">
        <tipus>Fiat</tipus>
        <ar>21233</ar>
        <szin>piros</szin>
        <tulaj>
            <nev>Zoli</nev>
            <varos>Eger</varos>
        </tulaj>
    </auto>
    
    <auto rez="ABC-101">
        <tipus>Skoda</tipus>
        <ar>44233</ar>
        <szin>fehér</szin>
        <tulaj>
            <nev>Peti</nev>
            <varos>Miskolc</varos>
        </tulaj>
    </auto>

    <auto rez="ABC-102">
        <tipus>Opel</tipus>
        <ar>31233</ar>
        <szin>szürke</szin>
        <tulaj>
            <nev>Tomi</nev>
            <varos>Miskolc</varos>
        </tulaj>
    </auto>

    <auto rez="ABC-103">
        <tipus>Opel</tipus>
        <ar>21233</ar>
        <szin>kék</szin>
        <tulaj>
            <nev>Sanyi</nev>
            <varos>Miskolc</varos>
        </tulaj>
    </auto>

</autok> -->