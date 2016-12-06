package gui;

import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {

    private JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    private JButton b_symEnc = new JButton("symmetrische Verschlüsselung");
    private JButton b_symDec = new JButton("symmetrische Entschlüsselung");
    private JButton b_asymEnc = new JButton("asymmetrische Verschlüsselung");
    private JButton b_asymDec = new JButton("asymmetrische Entschlüsselung");
    private JButton b_hash = new JButton("Hashen");
    private JButton b_ds = new JButton("digital Signieren");
    private JButton b_dsv = new JButton("dig. Signatur verifizieren");

    private Model model;

    public View(Model model){
        this.model = model;
        this.init();
    }

    private void init(){
        this.setSize(600, 250);
        this.setName("Kryptomechanismen");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setComponents();
        this.setVisible(true);
    }

    private void setComponents(){
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        panel.add(b_symEnc, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(b_symDec, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(b_asymEnc, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(b_asymDec, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(b_hash, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(b_ds, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(b_dsv, gbc);

        this.add(panel);
    }

    public void b_symEncListener(ActionListener al){
        b_symEnc.addActionListener(al);
    }

    public void b_symDecListener(ActionListener al){
        b_symDec.addActionListener(al);
    }

    public void b_asymEncListener(ActionListener al){
        b_asymEnc.addActionListener(al);
    }

    public void b_asymDecListener(ActionListener al){
        b_asymDec.addActionListener(al);
    }

    public void b_b_hashListener(ActionListener al){
        b_hash.addActionListener(al);
    }

    public void b_dsListener(ActionListener al){
        b_ds.addActionListener(al);
    }

    public void b_dsvListener(ActionListener al){
        b_dsv.addActionListener(al);
    }
}
