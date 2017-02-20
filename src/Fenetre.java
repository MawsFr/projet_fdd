import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.UIManager;

public class Fenetre {

	private JFrame frame;
	private JTable table;
	private JTextField txtDelimiter;
	private JTextField txtHeaderDelimiter;
	private JTextField txtStringSurrounder;
	private JTextField txtEmptyReplacer;
	
	private File fichier;
	private JTable table_1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fenetre window = new Fenetre();
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
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0};
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
		
		JCheckBox lblPremireLigne = new JCheckBox("Première ligne = en-tete");
		lblPremireLigne.setSelected(true);
		GridBagConstraints gbc_lblPremireLigne = new GridBagConstraints();
		gbc_lblPremireLigne.insets = new Insets(0, 0, 5, 0);
		gbc_lblPremireLigne.anchor = GridBagConstraints.WEST;
		gbc_lblPremireLigne.gridwidth = 2;
		gbc_lblPremireLigne.gridx = 0;
		gbc_lblPremireLigne.gridy = 2;
		panel.add(lblPremireLigne, gbc_lblPremireLigne);
		
		JLabel label = new JLabel("Délimiteur en-tête");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 3;
		panel.add(label, gbc_label);
		
		txtHeaderDelimiter = new JTextField();
		txtHeaderDelimiter.setText(",");
		txtHeaderDelimiter.setColumns(10);
		GridBagConstraints gbc_txtHeaderDelimiter = new GridBagConstraints();
		gbc_txtHeaderDelimiter.insets = new Insets(0, 0, 5, 0);
		gbc_txtHeaderDelimiter.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtHeaderDelimiter.gridx = 1;
		gbc_txtHeaderDelimiter.gridy = 3;
		panel.add(txtHeaderDelimiter, gbc_txtHeaderDelimiter);
		
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
		
		table_1 = new JTable();
		table_1.setDragEnabled(true);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Num Colonne", "Delimiter"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		GridBagConstraints gbc_table_1 = new GridBagConstraints();
		gbc_table_1.insets = new Insets(0, 0, 5, 0);
		gbc_table_1.gridwidth = 2;
		gbc_table_1.fill = GridBagConstraints.BOTH;
		gbc_table_1.gridx = 0;
		gbc_table_1.gridy = 9;
		panel.add(table_1, gbc_table_1);
		
		JButton btnAjouterUneColonne = new JButton("Ajouter une colonne");
		btnAjouterUneColonne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnAjouterUneColonne = new GridBagConstraints();
		gbc_btnAjouterUneColonne.gridwidth = 2;
		gbc_btnAjouterUneColonne.insets = new Insets(0, 0, 5, 5);
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
		
		table = new JTable();
		panel_1.add(table, BorderLayout.CENTER);
		splitPane.setDividerLocation(200);
	}

}
