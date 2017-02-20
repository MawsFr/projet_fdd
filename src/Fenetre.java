import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class Fenetre implements Observer {

	public static final String FILE_LOADED = "FILE_LOADED";
	
	private JFrame frame;
	private JTextField txtDelimiter;
	private JTextField txtStringSurrounder;
	private JTextField txtEmptyReplacer;
	
	private File fichier;
	private JScrollPane scrollColumnToTransform;
	
	private Parser parser;
	private JTable tableColumnToTransform;
	private JTable tableCsv;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		final Parser parser = new Parser();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fenetre window = new Fenetre();
					window.parser = parser;
					parser.addObserver(window);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Fenetre() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 628, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblPremiereEtape = new JLabel("AVANT OUVERTURE DU FICHIER :");
		lblPremiereEtape.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblPremiereEtape = new GridBagConstraints();
		gbc_lblPremiereEtape.gridwidth = 2;
		gbc_lblPremiereEtape.insets = new Insets(0, 0, 5, 0);
		gbc_lblPremiereEtape.gridx = 0;
		gbc_lblPremiereEtape.gridy = 0;
		panel.add(lblPremiereEtape, gbc_lblPremiereEtape);
		
		JLabel lblDlimiteur = new JLabel("Délimiteur");
		GridBagConstraints gbc_lblDlimiteur = new GridBagConstraints();
		gbc_lblDlimiteur.insets = new Insets(0, 0, 5, 5);
		gbc_lblDlimiteur.anchor = GridBagConstraints.WEST;
		gbc_lblDlimiteur.gridx = 0;
		gbc_lblDlimiteur.gridy = 1;
		panel.add(lblDlimiteur, gbc_lblDlimiteur);
		
		txtDelimiter = new JTextField();
		txtDelimiter.setText(",");
		GridBagConstraints gbc_txtDelimiter = new GridBagConstraints();
		gbc_txtDelimiter.insets = new Insets(0, 0, 5, 0);
		gbc_txtDelimiter.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDelimiter.gridx = 1;
		gbc_txtDelimiter.gridy = 1;
		panel.add(txtDelimiter, gbc_txtDelimiter);
		txtDelimiter.setColumns(10);
		
		JCheckBox chkPremireLigne = new JCheckBox("Première ligne = en-tete");
		chkPremireLigne.setSelected(true);
		GridBagConstraints gbc_chkPremiereLigne = new GridBagConstraints();
		gbc_chkPremiereLigne.insets = new Insets(0, 0, 5, 0);
		gbc_chkPremiereLigne.anchor = GridBagConstraints.WEST;
		gbc_chkPremiereLigne.gridwidth = 2;
		gbc_chkPremiereLigne.gridx = 0;
		gbc_chkPremiereLigne.gridy = 2;
		panel.add(chkPremireLigne, gbc_chkPremiereLigne);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		panel.add(scrollPane, gbc_scrollPane);
		
		JLabel lblNewLabel = new JLabel("APRES OUVERTURE DU FICHIER :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JCheckBox chckbxSupprimerLesEspaces = new JCheckBox("Supprimer les espaces");
		chckbxSupprimerLesEspaces.setToolTipText("trim()");
		chckbxSupprimerLesEspaces.setSelected(true);
		GridBagConstraints gbc_chckbxSupprimerLesEspaces = new GridBagConstraints();
		gbc_chckbxSupprimerLesEspaces.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxSupprimerLesEspaces.anchor = GridBagConstraints.WEST;
		gbc_chckbxSupprimerLesEspaces.gridwidth = 2;
		gbc_chckbxSupprimerLesEspaces.gridx = 0;
		gbc_chckbxSupprimerLesEspaces.gridy = 5;
		panel.add(chckbxSupprimerLesEspaces, gbc_chckbxSupprimerLesEspaces);
		
		JLabel lblEntourerLesStrings = new JLabel("Entourer les Strings par :");
		GridBagConstraints gbc_lblEntourerLesStrings = new GridBagConstraints();
		gbc_lblEntourerLesStrings.anchor = GridBagConstraints.WEST;
		gbc_lblEntourerLesStrings.insets = new Insets(0, 0, 5, 5);
		gbc_lblEntourerLesStrings.gridx = 0;
		gbc_lblEntourerLesStrings.gridy = 6;
		panel.add(lblEntourerLesStrings, gbc_lblEntourerLesStrings);
		
		txtStringSurrounder = new JTextField();
		txtStringSurrounder.setText("\"");
		GridBagConstraints gbc_txtStringSurrounder = new GridBagConstraints();
		gbc_txtStringSurrounder.insets = new Insets(0, 0, 5, 0);
		gbc_txtStringSurrounder.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStringSurrounder.gridx = 1;
		gbc_txtStringSurrounder.gridy = 6;
		panel.add(txtStringSurrounder, gbc_txtStringSurrounder);
		txtStringSurrounder.setColumns(10);
		
		JLabel lblRemplacerLesVides = new JLabel("Remplacer les vides par :");
		GridBagConstraints gbc_lblRemplacerLesVides = new GridBagConstraints();
		gbc_lblRemplacerLesVides.anchor = GridBagConstraints.WEST;
		gbc_lblRemplacerLesVides.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemplacerLesVides.gridx = 0;
		gbc_lblRemplacerLesVides.gridy = 7;
		panel.add(lblRemplacerLesVides, gbc_lblRemplacerLesVides);
		
		txtEmptyReplacer = new JTextField();
		txtEmptyReplacer.setText("?");
		GridBagConstraints gbc_txtEmptyReplacer = new GridBagConstraints();
		gbc_txtEmptyReplacer.insets = new Insets(0, 0, 5, 0);
		gbc_txtEmptyReplacer.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmptyReplacer.gridx = 1;
		gbc_txtEmptyReplacer.gridy = 7;
		panel.add(txtEmptyReplacer, gbc_txtEmptyReplacer);
		txtEmptyReplacer.setColumns(10);
		
		JLabel lblColonnesTransformer = new JLabel("Colonnes à transformer");
		GridBagConstraints gbc_lblColonnesTransformer = new GridBagConstraints();
		gbc_lblColonnesTransformer.insets = new Insets(0, 0, 5, 0);
		gbc_lblColonnesTransformer.gridwidth = 2;
		gbc_lblColonnesTransformer.gridx = 0;
		gbc_lblColonnesTransformer.gridy = 8;
		panel.add(lblColonnesTransformer, gbc_lblColonnesTransformer);
		
		scrollColumnToTransform = new JScrollPane();
		GridBagConstraints gbc_scrollColumnToTransform = new GridBagConstraints();
		gbc_scrollColumnToTransform.insets = new Insets(0, 0, 5, 0);
		gbc_scrollColumnToTransform.gridwidth = 2;
		gbc_scrollColumnToTransform.fill = GridBagConstraints.BOTH;
		gbc_scrollColumnToTransform.gridx = 0;
		gbc_scrollColumnToTransform.gridy = 9;
		panel.add(scrollColumnToTransform, gbc_scrollColumnToTransform);
		
		tableColumnToTransform = new JTable();
		scrollColumnToTransform.setViewportView(tableColumnToTransform);
		
		JButton btnAjouterUneColonne = new JButton("Ajouter une colonne");
		btnAjouterUneColonne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnAjouterUneColonne = new GridBagConstraints();
		gbc_btnAjouterUneColonne.gridwidth = 2;
		gbc_btnAjouterUneColonne.insets = new Insets(0, 0, 5, 0);
		gbc_btnAjouterUneColonne.gridx = 0;
		gbc_btnAjouterUneColonne.gridy = 10;
		panel.add(btnAjouterUneColonne, gbc_btnAjouterUneColonne);
		
		JButton btnAppliquer = new JButton("Appliquer");
		btnAppliquer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnAppliquer = new GridBagConstraints();
		gbc_btnAppliquer.gridwidth = 2;
		gbc_btnAppliquer.gridx = 0;
		gbc_btnAppliquer.gridy = 11;
		panel.add(btnAppliquer, gbc_btnAppliquer);
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.SOUTH);
		
		JButton btnOuvrir = new JButton("Ouvrir");
		btnOuvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					fichier = chooser.getSelectedFile();
					parser.loadFile(fichier, txtDelimiter.getText().charAt(0), chkPremireLigne.isSelected());
					
				}
			}
		});
		panel_5.add(btnOuvrir);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_5.add(btnEnregistrer);
		
		JButton btnAide = new JButton("Aide");
		panel_5.add(btnAide);
		
		JScrollPane scrollCsv = new JScrollPane();
		panel_1.add(scrollCsv, BorderLayout.CENTER);
		
		tableCsv = new JTable();
		scrollCsv.setViewportView(tableCsv);
		splitPane.setDividerLocation(200);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg.equals(FILE_LOADED)) {
			tableCsv.setModel(new CSVTableModel(parser.getCsvFile()));
		}
	}

}
