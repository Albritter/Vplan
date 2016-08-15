import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

/**
 * Created by albritter on 15.08.16.
 */
public class TimeTable extends JPanel {
    ArrayList<Component> components;

    public TimeTable() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0};
        gridBagLayout.rowHeights = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
        gridBagLayout.columnWeights = new double[]{0};
        gridBagLayout.rowWeights = new double[]{1, 1, 1, 1, 1, 1, 1, 1};
        setLayout(gridBagLayout);

        JLabel lblMontag = new JLabel("Montag");
        GridBagConstraints gridlblMontag = new GridBagConstraints();
        gridlblMontag.anchor = GridBagConstraints.NORTHWEST;
        gridlblMontag.gridx = 1;
        gridlblMontag.gridy = 0;
        gridlblMontag.insets = new Insets(5, 5, 5, 5);
        lblMontag.setPreferredSize(new Dimension(100, 20));
        lblMontag.setBorder(BorderFactory.createLineBorder(Color.black));
        add(lblMontag, gridlblMontag);

        JLabel lblDienstag = new JLabel("Dienstag");
        GridBagConstraints gridlblDienstag = new GridBagConstraints();
        gridlblDienstag.anchor = GridBagConstraints.NORTHWEST;
        gridlblDienstag.gridx = 2;
        gridlblDienstag.gridy = 0;
        gridlblDienstag.insets = new Insets(5, 5, 5, 5);
        lblDienstag.setPreferredSize(new Dimension(100, 20));
        lblDienstag.setBorder(BorderFactory.createLineBorder(Color.black));
        add(lblDienstag, gridlblDienstag);

        JLabel lblMitwoch = new JLabel("Mitwoch");
        GridBagConstraints gridlblMitwoch = new GridBagConstraints();
        gridlblMitwoch.anchor = GridBagConstraints.NORTHWEST;
        gridlblMitwoch.gridx = 3;
        gridlblMitwoch.gridy = 0;
        gridlblMitwoch.insets = new Insets(5, 5, 5, 5);
        lblMitwoch.setPreferredSize(new Dimension(100, 20));
        lblMitwoch.setBorder(BorderFactory.createLineBorder(Color.black));
        add(lblMitwoch, gridlblMitwoch);

        JLabel lblDonnerstag = new JLabel("Donnerstag");
        GridBagConstraints gridlblDonnerstag = new GridBagConstraints();
        gridlblDonnerstag.anchor = GridBagConstraints.NORTHWEST;
        gridlblDonnerstag.gridx = 4;
        gridlblDonnerstag.gridy = 0;
        gridlblDonnerstag.insets = new Insets(5, 5, 5, 5);
        lblDonnerstag.setPreferredSize(new Dimension(100, 20));
        lblDonnerstag.setBorder(BorderFactory.createLineBorder(Color.black));
        add(lblDonnerstag, gridlblDonnerstag);

        JLabel lblFreitag = new JLabel("Freitag");
        GridBagConstraints gridlblFreitag = new GridBagConstraints();
        gridlblFreitag.anchor = GridBagConstraints.NORTHWEST;
        gridlblFreitag.gridx = 5;
        gridlblFreitag.gridy = 0;
        gridlblFreitag.insets = new Insets(5, 5, 5, 5);
        lblFreitag.setPreferredSize(new Dimension(100, 20));
        lblFreitag.setBorder(BorderFactory.createLineBorder(Color.black));
        add(lblFreitag, gridlblFreitag);

        JLabel lbl = new JLabel("IT");
        JPanel pan = new JPanel();
        pan.add(lbl);
        GridBagConstraints test = new GridBagConstraints();
        test.anchor = GridBagConstraints.NORTHWEST;
        test.gridx = 1;
        test.gridy = 1;
        test.gridheight = 2;
        test.insets = new Insets(5, 5, 5, 5);
        lbl.setPreferredSize(new Dimension(100, 20));
        pan.setBorder(BorderFactory.createLineBorder(Color.black));
        add(pan, test);
    }

    public static void main(String[] argv) {
        JFrame f = new JFrame();
        f.add(new TimeTable());
        f.setVisible(true);
    }

}
