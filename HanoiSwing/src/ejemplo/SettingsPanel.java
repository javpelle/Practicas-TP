package ejemplo;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Allows the number of stacks & disks to be configured, and playback control
 */
public class SettingsPanel extends JPanel {
    private JSpinner jsStacks;
    private JSpinner jsDisks;
    private JButton jbApply;

    private TitledBorder controlBorder;
    private JSpinner jsSleepMs;
    private JToggleButton jtbPlay;
    private JButton jbReset;

    private PlayControlsListener controlsListener;

    private int lastStacks = 3;
    private int lastDisks = 4;

    public interface PlayControlsListener {
        void playPressed();
        void pausePressed();
        void resetPressed(int stacks, int disks);
    }

    public SettingsPanel(PlayControlsListener controlsListener) {
        this.controlsListener = controlsListener;
        initComponents();
    }

    public int getDelayMs() {
        return (Integer)jsSleepMs.getValue();
    }

    public void setStatusLabel(String text) {
        if (text == "") {
            controlBorder.setTitle("Playback Controls");
        } else {
            controlBorder.setTitle("Playback Controls : " + text);
        }
        repaint();
    }

    private void reset() {
        jtbPlay.setSelected(false);
        controlsListener.resetPressed(lastStacks, lastDisks);
    }

    private void initComponents() {
        jsStacks = new JSpinner(new SpinnerNumberModel(lastStacks, 3, 6, 1));
        jsDisks = new JSpinner(new SpinnerNumberModel(lastDisks, 1, 20, 1));
        jbApply = new JButton("Apply");
        jbApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastStacks = (Integer)jsStacks.getValue();
                lastDisks = (Integer)jsDisks.getValue();
                reset();
            }
        });

        JPanel jpConfig = new JPanel();
        jpConfig.setBorder(new TitledBorder("Configuration"));
        jpConfig.add(new JLabel("Stacks"));
        jpConfig.add(jsStacks);
        jsStacks.setToolTipText(
                "<html>Number of towers (stacks)");
        jpConfig.add(new JLabel("Disks"));
        jsDisks.setToolTipText(
                "<html>Number of disks of decreasing diameter<br>" +
                        "that will be placed on 1st stack");
        jpConfig.add(jsDisks);
        jpConfig.add(jbApply);
        jbApply.setToolTipText(
                "<html>Rebuild the interface with selected<br>" +
                        "numbers of stacks and disks, and <br>" +
                        "all disks placed at 1st stack");



        jsSleepMs = new JSpinner(new SpinnerNumberModel(100, 0, 2000, 100));
        jsSleepMs.setToolTipText(
                "<html>Number of milliseconds to sleep<br>" +
                        "after each move while <b>play</b> is enabled");
        jtbPlay = new JToggleButton("Play");
        jtbPlay.setToolTipText(
                "<html>Start solving a puzzle,<br>" +
                        "or pause if already solving,<br>" +
                        "or continue if previously paused");
        jtbPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jtbPlay.isSelected()) {
                    controlsListener.playPressed();
                } else {
                    controlsListener.pausePressed();
                }
            }
        });
        jbReset = new JButton("Reset");
        jbReset.setToolTipText(
                "<html>Restart this puzzle");
        jbReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        JPanel jpControls = new JPanel();
        jpControls.add(new JLabel("Delay (ms)"));
        jpControls.add(jsSleepMs);
        jpControls.add(jtbPlay);
        jpControls.add(jbReset);

        controlBorder = new TitledBorder("");
        setStatusLabel("");
        jpControls.setBorder(controlBorder);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(jpConfig);
        add(jpControls);
    }
}
