package Vues;

import Main.AlgoPlanarite;
import Modele.Graphe;
import Utils.Sortie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Clement on 30/11/2015.
 */
public class Fenetre extends JFrame{
    private JPanel pan = new JPanel();
    private JButton bouton = new JButton("Choix du graphe");
    private JFrame fenetre = this;
    private JLabel label = new JLabel("Choisir un fichier .graphe pour vérifier si il est planaire ou pas.");

    public Fenetre(){

        this.setTitle("Test de planarité");
        this.setSize(450, 100);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pan.add(label);
        pan.add(bouton);
        this.setContentPane(pan);

        //Bouton
        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                File workingDirectory = new File(System.getProperty("user.dir"));
                fileChooser.setCurrentDirectory(workingDirectory);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    int pos = selectedFile.getName().lastIndexOf(".");
                    String extention = selectedFile.getName().substring(pos + 1);
                    String result = "";

                    if(extention.equals("graphe")) {
                        Graphe g = AlgoPlanarite.algoPlanarite(selectedFile.toString());
                        if (g != null) {
                            result = "Le graphe est planaire.\n";
                            Sortie.todot(g);
                            result += "La liste des faces est : \n";
                            result += Sortie.faces(g);
                        } else {
                            result = "Le graphe n'est pas planaire.";
                        }
                    } else {
                        result = "Le fichier n'est pas un .graphe";
                    }
                    JOptionPane.showMessageDialog(fenetre, result);
                }
            }
        });

        this.setVisible(true);
    }

}
