
public class SiteConfig {
  private int port;
  private String documentRacine;
  private String fichierParDefaut;
  private String cheminLogs;
  private String cheminLogErreur;

  public SiteConfig(int p, String fd, String cl, String cle) {
    this.port = p;
    this.fichierParDefaut = fd;
    this.cheminLogs = cl;
    this.cheminLogErreur = cle;
  }

  public int getPort() {
    return this.port;
  }

  public String getDocumentRacine() {
    return this.documentRacine;
  }

  public String getFichierDefaut() {
    return this.documentRacine;
  }

  public String getCheminLog() {
    return this.cheminLogs;
  }

  public String getCheminLogErreur() {
    return this.cheminLogErreur;
  }
}
