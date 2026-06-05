import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConfigReader {
    public static SiteConfig lireConfig(String cheminFichier) {
        try {

        // Ca représente le fichier ça
        File fichier = new File(cheminFichier);

        // Ca prépare l'outil qui va lire le fichier XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Ca la, ca créer le le vrai lecteur, qui va s'occuper de lire le fichier dcp
        DocumentBuilder builder = factory.newDocumentBuilder();

        // ouverture du fichier et transfo en objet Java
        Document doc = builder.parse(fichier);

        // On récup la premiere balise (donc le <site>) et ça affiche Balise site  trouvée : site
        Element site = (Element) doc.getElementsByTagName("site").item(0);

        // récup la balsie <port> dans <site>
        // le .item ca sert en gros a prendre la première balise trouvé

        String portTexte = site.getElementsByTagName("port").item(0).getTextContent().trim();
        int port = Integer.parseInt(portTexte);

        String documentRoot = site.getElementsByTagName("DocumentRoot").item(0).getTextContent().trim();
        String defaultIndex = site.getElementsByTagName("DefaultIndex").item(0).getTextContent().trim();
        String accessLog = site.getElementsByTagName("Acceslog").item(0).getTextContent().trim();
        String errorLog = site.getElementsByTagName("Errorlog").item(0).getTextContent().trim();

        return new SiteConfig(port, documentRoot, defaultIndex, accessLog, errorLog);

        } catch (Exception e) {
            System.out.println("Erreur de lecture xml : " + e.getMessage());
            return null;
        }
    }
}
