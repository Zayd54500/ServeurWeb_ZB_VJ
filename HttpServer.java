import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

public class HttpServer {
  public static void main(String[] args) throws Exception{
    int port = 80;
    if (args.length >= 2) {
      if (args[0].equals("-p")) {
        port = Integer.parseInt(args[1]);
      }
    }
    System.out.println(port);

    ServerSocket ss = new ServerSocket(port);
    System.out.println("Serveur ouvert sur le port : " + port);

    while(true) {

    Socket client = ss.accept();
    System.out.println("Client connecté au serveur avec succès");

    BufferedReader bf = new BufferedReader(new InputStreamReader(client.getInputStream()));

    String premiereLigne = bf.readLine();
    System.out.println(premiereLigne);

    String morceau[] = premiereLigne.split(" ");
    String fichier = morceau[1];
    System.out.println("fichier demandé : " + fichier);
    if (fichier.equals("/")) {
      fichier = "/index.html";
    }
    String fichierLocal = fichier.substring(1);
    System.out.println("fichier en local : " + fichierLocal);

    String ligne = bf.readLine();
    while (ligne  != null && !ligne.equals("")) {
      System.out.println(ligne);
      ligne = bf.readLine();
    }

    File f = new File(fichierLocal);
    if (f.exists()) {
      System.out.println("Fichier trouvé");
      byte contenu[] = Files.readAllBytes(f.toPath());
      System.out.println("Taille du fichiers " + contenu.length + " Octets");
      OutputStream sortie = client.getOutputStream();
      String entete = "HTTP/1.1 200 OK\r\n" + "Content-Length: " + contenu.length + "\r\n" + "\r\n";
      sortie.write(entete.getBytes());
      sortie.write(contenu);
      sortie.flush();
    } else {
      System.out.println("Fichier introuvable");
      OutputStream sortieErreur = client.getOutputStream();
      String messageErreur = "Erreur 404 : Fichier introuvable";
      byte contenuErreur[] = messageErreur.getBytes();
      String enteteErreur = "HTTP/1.1 404 Not Found\r\n" + "Content-Length: " + contenuErreur.length + "\r\n" + "\r\n";
      sortieErreur.write(enteteErreur.getBytes());
      sortieErreur.write(contenuErreur);
      sortieErreur.flush();
    }

    client.close();
  }

  }
}
