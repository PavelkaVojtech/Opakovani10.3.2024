import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class Demo extends JFrame {

    private JTextField textField1;
    private JCheckBox mameKoupenoCheckBox;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    private JPanel panelMain;
    private JButton predchoziBtn;
    private JButton ulozitBtn;
    private JButton dalsiBtn;
    private JMenuBar jMenuBar = new JMenuBar();
    private JMenuItem jMenuItem = new JMenuItem("Otevřít");
    private JMenu save = new JMenu("Uložit");
    private JMenuItem saveItem = new JMenuItem("Uložit");
    private JMenuItem saveAsItem = new JMenuItem("Uložit jako");
    private int indexAktualniDeskovky = 0;
    private EvidenceDeskovek evidenceDeskovek = new EvidenceDeskovek();
    private File file;

    public Demo(EvidenceDeskovek evidenceDeskovek) {
        this.evidenceDeskovek = evidenceDeskovek;
        ButtonGroup group = new ButtonGroup();
        setContentPane(panelMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        group.add(a1RadioButton);
        group.add(a2RadioButton);
        group.add(a3RadioButton);
        jMenuBar.add(jMenuItem);
        jMenuBar.add(save);
        save.add(saveItem);
        save.add(saveAsItem);
        setJMenuBar(jMenuBar);
        saveItem.addActionListener(e -> ulozeniSeznamuDeskovekDoTxt(file));
        saveAsItem.addActionListener(e -> ulozeniSeznamuDeskovekDoTxtJako());

        ukazDeskovku(indexAktualniDeskovky);

        pack();
    }

    private void ukazDeskovku(int indexAktualniDeskovky){
        if (evidenceDeskovek.getVelikostSeznamu() == 0){
            textField1.setText("");
            mameKoupenoCheckBox.setSelected(false);
            a1RadioButton.setSelected(true);
        }
        else{
            textField1.setText(evidenceDeskovek.getSeznamDeskovek().get(indexAktualniDeskovky).getNazevDeskovky());
            mameKoupenoCheckBox.setSelected(evidenceDeskovek.getSeznamDeskovek().get(indexAktualniDeskovky).isMameKoupenou());
            switch (evidenceDeskovek.getSeznamDeskovek().get(indexAktualniDeskovky).getOblibenost()){
                case 1:
                    a1RadioButton.setSelected(true);
                    break;
                case 2:
                    a2RadioButton.setSelected(true);
                    break;
                case 3:
                    a3RadioButton.setSelected(true);
                    break;
            }
        }

        predchoziBtn.setEnabled(true);
        if (indexAktualniDeskovky > 0){
            predchoziBtn.addActionListener(e -> ukazPrechozi());
        }
        else{
            predchoziBtn.setEnabled(false);
        }

        dalsiBtn.setEnabled(true);
        if (indexAktualniDeskovky < EvidenceDeskovek.seznamDeskovek.size() - 1){
            dalsiBtn.setEnabled(true);
            dalsiBtn.addActionListener(e -> ukazDalsi());
        }
        else{
            dalsiBtn.setEnabled(false);
        }
    }
    private void ukazPrechozi(){;
            indexAktualniDeskovky--;
            ukazDeskovku(indexAktualniDeskovky);
    }
    private void ukazDalsi(){
            indexAktualniDeskovky++;
            ukazDeskovku(indexAktualniDeskovky);
    }
    private void ulozDeskovku(){
        this.evidenceDeskovek.seznamDeskovek.get(indexAktualniDeskovky).setNazevDeskovky(textField1.getText());
        this.evidenceDeskovek.seznamDeskovek.get(indexAktualniDeskovky).setMameKoupenou(mameKoupenoCheckBox.isSelected());
        if (a1RadioButton.isSelected()){
            this.evidenceDeskovek.seznamDeskovek.get(indexAktualniDeskovky).setOblibenost(1);
        }
        else if (a2RadioButton.isSelected()){
            this.evidenceDeskovek.seznamDeskovek.get(indexAktualniDeskovky).setOblibenost(2);
        }
        else if (a3RadioButton.isSelected()){
            this.evidenceDeskovek.seznamDeskovek.get(indexAktualniDeskovky).setOblibenost(3);
        }
    }


    private void  ulozeniSeznamuDeskovekDoTxt(File file){
        try (
                FileWriter fileWriter = new FileWriter("text.txt");
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter);
                )
        {
            for (Deskovka deskovka : evidenceDeskovek.getSeznamDeskovek()){
                printWriter.println(deskovka.getNazevDeskovky() + ";" + deskovka.isMameKoupenou() + ";" + deskovka.getOblibenost());
            }
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    private void ulozeniSeznamuDeskovekDoTxtJako(){
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setFileFilter(new FileNameExtensionFilter("text", "txt"));
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
        }
        ulozeniSeznamuDeskovekDoTxt(file);
    }
}
