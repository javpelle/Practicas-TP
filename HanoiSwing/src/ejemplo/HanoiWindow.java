package ejemplo;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * An interface to experiment with algorithms
 * for solving the Hanoi Towers puzzle.
 */
public class HanoiWindow extends JPanel implements SettingsPanel.PlayControlsListener {

    private HanoiUI ui;
    private SettingsPanel settings;
    private HanoiModel model;

    private Stepper stepper;

    public void initComponents() {
        ui = new HanoiUI();
        settings = new SettingsPanel(this);
        setLayout(new BorderLayout());
        add(ui, BorderLayout.CENTER);
        add(settings, BorderLayout.SOUTH);
    }

    public HanoiWindow() {
        initComponents();
        resetPressed(3, 4);
    }

    private class Stepper implements Runnable {
        private volatile boolean stopFlag = false;
        private volatile boolean pauseFlag = false;
        private int moveCounter = 0;

        private Iterator<HanoiSolver.Move> steps;
        private Stepper() {
            steps = HanoiSolver.solve(
                        model.getDiskCount(), 0, model.getStackCount()-1)
                    .iterator();
        }

        @Override
        public void run() {
            while ( ! stopFlag && steps.hasNext()) {
                try {
                    if ( ! pauseFlag) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                HanoiSolver.Move m = steps.next();
                                model.move(m.getSource(), m.getTarget());
                                settings.setStatusLabel("" + (++moveCounter));
                                ui.repaint();
                            }
                        });
                    }
                    Thread.sleep(Math.max(10, settings.getDelayMs()));
                } catch (IllegalArgumentException iae) {
                    System.err.println(iae);
                } catch (InterruptedException ie) {
                    System.err.println("Interrupted!");
                }
            }
        }
    }

    @Override
    public void playPressed() {
        if (stepper == null) {
            stepper = new Stepper();
            new Thread(stepper).start();
        } else if (stepper.pauseFlag) {
            stepper.pauseFlag = false;
        }
    }

    @Override
    public void pausePressed() {
        if (stepper != null) {
            stepper.pauseFlag = true;
        }
    }

    @Override
    public void resetPressed(int stacks, int disks) {
        if (stepper != null) {
            stepper.stopFlag = true;
            stepper = null;
        }

        model = new HanoiModel(stacks, disks);
        ui.setModel(model);
        ui.repaint();
    }
}
