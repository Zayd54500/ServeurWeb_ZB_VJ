import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class ConfigReader {

    static public SiteConfig lireConfig(String path) {
        SiteConfig site;
        int p =0;
        String fd = "";
        String cl = "";
        String cle = "";
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(path);
            doc.getDocumentElement().normalize();

            // Port
            NodeList portList = doc.getElementsByTagName("port");
            if (portList.getLength() > 0) {
                p = (Integer.parseInt(portList.item(0).getTextContent().trim()));
            }

            // DefaultIndex
            NodeList indexList = doc.getElementsByTagName("DefaultIndex");
            if (indexList.getLength() > 0) {
                fd = (indexList.item(0).getTextContent().trim());
            }
            // AccesLog
            NodeList accesLogList = doc.getElementsByTagName("Acceslog");
            if (accesLogList.getLength() > 0) {
                cl = (accesLogList.item(0).getTextContent().trim());
            }
            // ErrorLog
            NodeList errorLogList = doc.getElementsByTagName("Errorlog");
            if (errorLogList.getLength() > 0) {
                cle = (errorLogList.item(0).getTextContent().trim());
            }
            site = new SiteConfig(p, fd, cl, cle);

        } catch (Exception e) {
            site = null;
        }


        return site;
    }
}