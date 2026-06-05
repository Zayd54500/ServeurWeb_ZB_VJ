import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConfigReader {
    public static void testerLecture(String cheminFichier) {
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

        SiteConfig config = new SiteConfig(port, documentRoot, defaultIndex, accessLog, errorLog);

        System.out.println("Objet SiteConfig créé : ");
        System.out.println("Port trouvé : " + config.getPort());
        System.out.println("DocumentRoot : " + config.getDocumentRacine());
        System.out.println("DefaultIndex : " + config.getFichierDefaut());
        System.out.println("AccessLog : " + config.getCheminLog());
        System.out.println("ErrorLog : " + config.getCheminLogErreur());

        } catch (Exception e) {
            System.out.println("Erreur de lecture xml : " + e.getMessage());
        }
    }
}
