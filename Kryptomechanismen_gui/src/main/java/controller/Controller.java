package controller;

import gui.View;
import model.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller{
    private static final Logger LOG = LogManager.getLogger(Controller.class.getName());
    private Model model;
    private View view;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;

        this.initActionListener();
        LOG.info("con");
    }

    private void initActionListener(){
        LOG.info("init");
        view.b_symEncListener(new symEncButtonPress());
        view.b_symDecListener(new symDecButtonPress());
        view.b_asymEncListener(new asymEncButtonPress());
        view.b_asymDecListener(new asymDecButtonPress());
        view.b_b_hashListener(new hashButoonPress());
        view.b_dsListener(new digSigButtonPress());
        view.b_dsvListener(new digSigVerButtonPress());
    }


    class symEncButtonPress implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            LOG.info("press");
        }
    }

    class symDecButtonPress implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    class asymEncButtonPress implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    class asymDecButtonPress implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    class hashButoonPress implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    class digSigButtonPress implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    class digSigVerButtonPress implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }


}
