import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static java.lang.Integer.parseInt;

public class ConfigReader {
    public SiteConfig lireConfig(File f){
        SiteConfig site;
        int p = 0;
        String fd = "";
        String cl = "";
        String cle = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(f));
            String ligne = bf.readLine();
            while (ligne != null) {
                String mot ="";
                int i =1;
                while(ligne.charAt(i)!='>') {
                    mot += ligne.charAt(i);
                    i++;
                }
                switch (mot){
                    case "port" : p = parseInt(ligne.substring(7, 11));
                    case "DocumentRoot" : fd = ligne.substring(15, 23);
                    case "Acceslog" : cl = ligne.substring(11, 25);
                    case "Errorlog" : cle = ligne.substring(11, 24);
                }
                ligne = bf.readLine();
            }
            site = new SiteConfig(p, fd, cl, cle);

        } catch (Exception e) {
            site = null;
        }
        return site;
    }
}
